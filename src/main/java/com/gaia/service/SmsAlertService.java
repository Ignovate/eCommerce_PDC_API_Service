package com.gaia.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsAlertService {

	private static Logger LOGGER = LoggerFactory.getLogger(SmsAlertService.class);

	@Value("${sms.sender.id:LECTRE}")
	private String senderId;

	@Value("${sms.url:http://2factor.in/API/V1/b37b6fb1-cba9-11e8-a895-0200cd936042/ADDON_SERVICES/SEND/TSMS}")
	private String url;

	@Value("${sms.otp.url:https://2factor.in/API/V1/b37b6fb1-cba9-11e8-a895-0200cd936042/SMS/}")
	private String otpUrl;

	
	public void sendSms(String mobile, String template, Map<String, String> formData) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> multipartRequest = new LinkedMultiValueMap<>();

		formData.forEach((k, v) -> multipartRequest.add(k, v));
		multipartRequest.add("TemplateName", template);
		multipartRequest.add("To", mobile);
		multipartRequest.add("From", senderId);
		LOGGER.info("multipartRequest {}",multipartRequest);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multipartRequest, header);
		try {
			String result = new RestTemplate().postForObject(url, requestEntity, String.class);
			LOGGER.info("result {}", result);
		} catch (HttpClientErrorException e) {
			LOGGER.info("message {} response {}", e.getMessage(), e.getResponseBodyAsString());
		}catch(Exception e) {
			LOGGER.error("Unable to send sms error ",e);
		}
	}
	
	public void sendSmsOTP(String mobile, int otp) {
		
		String url=otpUrl+"/"+mobile+"/"+otp;
		try {
			LOGGER.info("OTP URL :{}",url);
			String result = new RestTemplate().getForObject(url, String.class);
			LOGGER.info("result {}", result);
		} catch (HttpClientErrorException e) {
			LOGGER.info("message {} response {}", e.getMessage(), e.getResponseBodyAsString());
		}catch(Exception e) {
			LOGGER.error("Unable to send sms error ",e);
		}
	}
	
	
	
}
