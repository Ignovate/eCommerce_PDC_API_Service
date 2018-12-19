package com.gaia.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.UserEntity;
import com.gaia.service.MailAlertService;
import com.gaia.service.SmsAlertService;
import com.gaia.service.UserService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private DozerBeanMapper beanMapper;
	
	@Autowired
	private UserService userEntityService;
	
	@Autowired
	private SmsAlertService smsAlertService;
	
	@Autowired
	private MailAlertService mailAlertService;
	
	@Value("${sms.register.template:Sign Up Success}")
	private String smsRegister;
	
	@Value("${sms.reset.template:reset}")
	private String update;
	
	
	@Value("${email.register.text:your account created in lectrefy}")
	private String registerEmailText;
	
	@Value("${email.register.subject:Account created}")
	private String registerEmailSubject;
	
	@Value("${email.update.text:your account updated in lectrefy}")
	private String updateEmailText;
	
	@Value("${email.update.subject:Account updated}")
	private String updateEmailSubject;
	

	/*@PostMapping("users")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
		UserEntity user=userEntityService.addUser(userEntity);
		Map<String, String> formData=new HashMap<>();
		formData.put("email", user.getEmail());
		smsAlertService.sendSms(user.getPhone(), smsRegister, formData);
		mailAlertService.sendEmail(user.getEmail(),registerEmailText,registerEmailSubject);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<UserEntity> getUsers(@PathVariable long id) {
		//UserVm userVm=beanMapper.map(userEntityService.getUser(id), UserVm.class);
		return new ResponseEntity<UserEntity>(userEntityService.getUser(id), HttpStatus.OK);
	}
	
	@GetMapping("users/filter")
	public ResponseEntity<List<UserEntity>> getUsersWithFilter(@RequestParam Map<String, String> params) {
		return new ResponseEntity<List<UserEntity>>(userEntityService.getUser(params), HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<List<UserEntity>> getUsers() {
		return new ResponseEntity<List<UserEntity>>(userEntityService.getAllUser(), HttpStatus.OK);
	}

	@DeleteMapping("users/{id}")
	public ResponseEntity<ResponseVm> deleteUsers(@PathVariable long id) {
		userEntityService.deleteUser(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<ResponseVm> updateUser(@RequestBody UserEntity user, @PathVariable long id) throws GaiaException {
		UserEntity userOld = userEntityService.getUser(id);
		if (userOld == null)
			throw new GaiaException(ErrorCodes.CODE_NO_DATA,ErrorCodes.MSG_NO_DATA);
		user.setUserId(id);
		userEntityService.addUser(user);
		Map<String, String> formData=new HashMap<>();
		formData.put("email", user.getEmail());
		smsAlertService.sendSms(user.getPhone(), update, formData);
		mailAlertService.sendEmail(user.getEmail(), updateEmailText, updateEmailSubject);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("users/reset/{id}")
	public ResponseEntity<ResponseVm> resetPassword(@RequestBody UserEntity user, @PathVariable long id) throws GaiaException{
		UserEntity userOld = userEntityService.getUser(id);
		if (userOld == null)
			throw new GaiaException(ErrorCodes.CODE_NO_DATA,ErrorCodes.MSG_NO_DATA);		user.setUserId(id);
		userEntityService.addUser(user);
		Map<String, String> formData=new HashMap<>();
		formData.put("email", user.getEmail());
		smsAlertService.sendSms(user.getPhone(), update, formData);
		mailAlertService.sendEmail(user.getEmail(), updateEmailText, updateEmailSubject);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}*/

	
}
