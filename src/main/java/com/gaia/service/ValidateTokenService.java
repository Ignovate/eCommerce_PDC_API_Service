package com.gaia.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gaia.common.Constants;
import com.gaia.intr.ValidationInterface;
import com.gaia.web.rest.vm.ValidateResponse;

@Service
public class ValidateTokenService implements ValidationInterface {

	@Override
	public ValidateResponse validateToken(String token, String authmanUrl) {
		MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<String, String>();
		queryParam.add(Constants.HEADER_TOKEN, token);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> reqEntity = new HttpEntity<Object>(headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(authmanUrl);
		builder.queryParams(queryParam);
		ResponseEntity<ValidateResponse> response = new RestTemplate().exchange(builder.build().encode().toUri(),
				HttpMethod.GET, reqEntity, ValidateResponse.class);
		return response.getBody();
	}

}
