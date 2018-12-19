package com.gaia.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.service.OtpService;
import com.gaia.web.rest.vm.OtpReqVm;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0/otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OtpController {

	@Autowired
	private OtpService otpService;

	@PostMapping("generate")
	public ResponseEntity<ResponseVm> generate(@RequestBody OtpReqVm otpReqVm) {
		return new ResponseEntity<ResponseVm>(otpService.generateOtp(otpReqVm), HttpStatus.OK);
	}
	
	@PostMapping("validate")
	public ResponseEntity<ResponseVm> validate(@RequestBody OtpReqVm otpReqVm) throws GaiaException {
		return new ResponseEntity<ResponseVm>(otpService.validateOtp(otpReqVm), HttpStatus.OK);
	}
}
