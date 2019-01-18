package com.gaia.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.web.rest.vm.OtpReqVm;
import com.gaia.web.rest.vm.ResponseVm;

@Service
public class OtpService {

	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private SmsAlertService smsAlertService;
	
	@Autowired
	private MailAlertService mailAlertService;
	
	@Value("${sms.otp.template:otp}")
	private String smsotp;
	
	@Value("${email.otp.text:your otp is :}")
	private String otpEmailText;
	
	
	public ResponseVm generateOtp(OtpReqVm otpReq)
	{
		int otp = ThreadLocalRandom.current().nextInt(100000, 999999);
		cacheManager.getCache("otp").put(otpReq.getRefNo(), otp);
		smsAlertService.sendSmsOTP(otpReq.getPhone(), otp);
		mailAlertService.sendEmail(otpReq.getEmail(), "One Time Passowrd", otpEmailText + otp);
		return ResponseVm.getSuccessVm();
	}
	
	public ResponseVm validateOtp(OtpReqVm otpReq) throws GaiaException
	{
		int otp = cacheManager.getCache("otp").get(otpReq.getRefNo(),Integer.class);
		
		if(otp==otpReq.getOtp())
		return ResponseVm.getSuccessVm();
		else
			throw new GaiaException(ErrorCodes.CODE_INVALID_OTP,ErrorCodes.MSG_INVALID_OTP);
	}
	
	
}
