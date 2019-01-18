package com.gaia.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.common.Constants;
import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.CustomersEntity;
import com.gaia.domain.SalesQuote;
import com.gaia.domain.UserIdMapEntity;
import com.gaia.domain.UserMasterEntity;
import com.gaia.repository.UserIdMapRepo;
import com.gaia.repository.UserMasterRepo;
import com.gaia.web.rest.vm.LoginReqVm;
import com.gaia.web.rest.vm.LoginRespVm;
import com.gaia.web.rest.vm.RegisterUserReqVm;

@Service
public class UserService {

	@Autowired
	private UserMasterRepo userMasterRepo;
	@Autowired
	private UserIdMapRepo userIdMapRepo;
	@Autowired
	private CustomersService customersService;
	@Autowired
	private SalesQuoteService salesQuoteService;
	@Autowired
	SmsAlertService smsService;
	@Autowired
	MailAlertService mailService;

	public LoginRespVm signin(LoginReqVm loginReq) {
		LoginRespVm loginRes = new LoginRespVm();
		CustomersEntity customer = customersService.getCustomer(loginReq.getEmail(), loginReq.getPassword(), "email");
		if (customer == null)
			customer = customersService.getCustomer(loginReq.getUsername(), loginReq.getPassword(), "username");

		if (customer == null) {
			loginRes.setMessage("Invalid Email/Password");
		} else if (!customer.isActive()) {
			loginRes.setMessage("Account is not active");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", customer.getCustomerId());
			map.put("active", true);
			SalesQuote salesQuote = salesQuoteService.getSalesQuote(map);
			if (salesQuote != null) {
				customer.setQuoteId(salesQuote.getId());
				customer.setCartItemCount(salesQuote.getTotalItems());
			}
			customersService.addCustomers(customer);

			loginRes.setCustomer(customer);
		}
		return loginRes;
	}

	public LoginRespVm signup(CustomersEntity customer) {
		LoginRespVm loginRes = new LoginRespVm();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", customer.getEmail());
		CustomersEntity cusEntity = customersService.getCustomer(map);
		if (cusEntity != null) {
			loginRes.setMessage("Email id already Registered");
			return loginRes;
		}
		map.clear();
		map.put("username", customer.getUsername());
		cusEntity = customersService.getCustomer(map);
		if (cusEntity != null) {
			loginRes.setMessage("Username already Registered");
			return loginRes;
		}

		customersService.addCustomers(customer);
		loginRes.setCustomer(customer);
		mailService.sendEmail(customer.getEmail(), "Registration Success", "You are successfully registered");
		smsService.sendSms(customer.getMobile(), "You are successfully registered");

		return loginRes;
	}

	public void login(LoginReqVm loginReq) throws GaiaException {
		UserIdMapEntity userEntity = getuserlogindetails(loginReq.getUsername());
		if (userEntity == null)
			throw new GaiaException(ErrorCodes.CODE_INCORRECT_USER, ErrorCodes.MSG_INCORRECT_USER);

		UserMasterEntity userMaster = getUsermaster(userEntity.getUserId());

		if (Constants.BLOCK.equals(userMaster.getUserStatus())) {
			throw new GaiaException(ErrorCodes.CODE_USER_BLOCK, ErrorCodes.MSG_USER_BLOCK);
		}
		if (Constants.LOCK.equals(userMaster.getUserStatus())) {
			throw new GaiaException(ErrorCodes.CODE_USER_LOCKED, ErrorCodes.MSG_USER_LOCKED);
		}

		if (!loginReq.getPassword().equals(userEntity.getPassword())) {
			int count = userEntity.getIncorrectPasswdCnt();
			userEntity.setIncorrectPasswdCnt(count + 1);
			userIdMapRepo.save(userEntity);
			throw new GaiaException(ErrorCodes.CODE_INVALID_CRED, ErrorCodes.MSG_INVALID_CRED);
		}

		if (Constants.NEW.equals(userMaster.getUserStatus())) {
			userMaster.setUserStatus(Constants.ACTIVE);
			userMaster.setPrevUserStatus(Constants.NEW);
			userMasterRepo.save(userMaster);
		}

		userEntity.setLastLoginDate(LocalDateTime.now());
		if (userEntity.getIncorrectPasswdCnt() != 0) {
			userEntity.setIncorrectPasswdCnt(0);
		}
		userIdMapRepo.save(userEntity);

	}

	private UserIdMapEntity getuserlogindetails(String username) {
		Specification<UserIdMapEntity> specification = new Specification<UserIdMapEntity>() {
			@Override
			public Predicate toPredicate(Root<UserIdMapEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("loginUserId"), username));
			}
		};

		UserIdMapEntity userEntity = userIdMapRepo.findOne(specification).orElse(null);
		return userEntity;
	}

	public UserMasterEntity getUsermaster(Long userId) {
		Specification<UserMasterEntity> specification = new Specification<UserMasterEntity>() {
			@Override
			public Predicate toPredicate(Root<UserMasterEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("userId"), userId));
			}
		};

		return userMasterRepo.findOne(specification).orElse(null);
	}

	public void register(RegisterUserReqVm regUser) throws GaiaException {
		UserIdMapEntity userEntity = getuserlogindetails(regUser.getUsername());
		if (userEntity != null) {
			throw new GaiaException(ErrorCodes.CODE_USERNAME_PRESENT, ErrorCodes.MSG_USERNAME_PRESENT);
		}
		if (!regUser.getPassword().equals(regUser.getConfirmPassword())) {
			throw new GaiaException(ErrorCodes.CODE_CONFIRM_PASS_MISMATCH, ErrorCodes.MSG_CONFIRM_PASS_MISMATCH);
		}
		UserMasterEntity userMaster = new UserMasterEntity();
		userMaster.setUserName(regUser.getName());
		userMaster.setUserGroups(regUser.getUserGroup());
		userMaster.setUserType(regUser.getUserType());
		userMaster.setUserStatus(Constants.NEW);
		userMaster.setEmail(regUser.getEmail());
		userMaster.setMobile(regUser.getMobile());
		userMaster.setMakerId(regUser.getUsername());

		userMaster = userMasterRepo.save(userMaster);

		userEntity = new UserIdMapEntity();
		userEntity.setUserId(userMaster.getUserId());
		userEntity.setLoginUserId(regUser.getUsername());
		userEntity.setPassword(regUser.getPassword());
		userEntity.setMakerId(regUser.getUsername());
		userEntity.setMakerDtTm(userMaster.getMakerDtTm());
		userEntity.setIncorrectPasswdCnt(0);

		userIdMapRepo.save(userEntity);
	}

}
