package com.gaia.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.Constants;
import com.gaia.common.GaiaException;
import com.gaia.domain.CustomersEntity;
import com.gaia.service.UserService;
import com.gaia.web.rest.vm.LoginReqVm;
import com.gaia.web.rest.vm.LoginRespVm;
import com.gaia.web.rest.vm.RegisterUserReqVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping("signin")
	public ResponseEntity<LoginRespVm> login(@RequestBody LoginReqVm loginReq) throws GaiaException {
//		userService.login(loginReq);
//		return new ResponseEntity<LoginRespVm>(new LoginRespVm(Constants.VALIDATED_MSG), HttpStatus.OK);
		return new ResponseEntity<LoginRespVm>(userService.signin(loginReq), HttpStatus.OK);
	}
	
	@PostMapping("signup")
	public ResponseEntity<LoginRespVm> signup(@RequestBody CustomersEntity customer) throws GaiaException {
//		userService.signup(regUser);
//		return new ResponseEntity<LoginRespVm>(new LoginRespVm(Constants.REGISTERED_MSG), HttpStatus.OK);
		return new ResponseEntity<LoginRespVm>(userService.signup(customer), HttpStatus.OK);
	}
	

}
