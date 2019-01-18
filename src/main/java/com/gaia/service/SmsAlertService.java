package com.gaia.service;

import java.util.LinkedHashMap;
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

	@Value("${sms.url}")
	private String url;

	@Value("${sms.sender.id}")
	private String senderId;

	@Value("${sms.sender.username}")
	private String username;

	@Value("${sms.sender.password}")
	private String password;

	public void sendSms(String mobile, String message) {
		// Map<String, String> requestParams = new LinkedHashMap<String,
		// String>();
		// requestParams.put("username", username);
		// requestParams.put("password", password);
		// requestParams.put("to", (mobile != null && mobile.length() == 10) ?
		// "91" + mobile : mobile);
		// requestParams.put("from", senderId);
		// requestParams.put("text", message);
		String param = "?username=" + username + "&password=" + password + "&to="
				+ ((mobile != null && mobile.length() == 10) ? "91" + mobile : mobile) + "&from=" + senderId + "&text="
				+ message;
		try {
			String result = new RestTemplate().getForObject(url + param, String.class);
			LOGGER.info("SMS to:{}, result:{}", mobile, result);
		} catch (HttpClientErrorException e) {
			LOGGER.info("SMS to:{}, exception:{}, response:{}", mobile, e.getMessage(),
					e.getResponseBodyAsString());
		} catch (Exception e) {
			LOGGER.info("SMS to:{}, exception:{}", mobile, e.getMessage());
		}
	}

	public void sendSms(String mobile, String template, Map<String, String> formData) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> multipartRequest = new LinkedMultiValueMap<>();

		formData.forEach((k, v) -> multipartRequest.add(k, v));
		multipartRequest.add("TemplateName", template);
		multipartRequest.add("To", mobile);
		multipartRequest.add("From", senderId);
		LOGGER.info("multipartRequest {}", multipartRequest);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multipartRequest, header);
		try {
			String result = new RestTemplate().postForObject(url, requestEntity, String.class);
			LOGGER.info("result {}", result);
		} catch (HttpClientErrorException e) {
			LOGGER.info("message {} response {}", e.getMessage(), e.getResponseBodyAsString());
		} catch (Exception e) {
			LOGGER.error("Unable to send sms error ", e);
		}
	}

	public void sendSmsOTP(String mobile, int otp) {

		String url = this.url + "/" + mobile + "/" + otp;
		try {
			LOGGER.info("OTP URL :{}", url);
			String result = new RestTemplate().getForObject(url, String.class);
			LOGGER.info("result {}", result);
		} catch (HttpClientErrorException e) {
			LOGGER.info("message {} response {}", e.getMessage(), e.getResponseBodyAsString());
		} catch (Exception e) {
			LOGGER.error("Unable to send sms error ", e);
		}
	}

}
