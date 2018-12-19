package com.gaia.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.gaia.common.Constants;
import com.gaia.intr.ValidationInterface;
import com.gaia.web.rest.vm.ValidateResponse;

public class HeaderAuthenticationFilter extends GenericFilterBean {

	@Autowired
	private ValidationInterface valIntr;

	private final String authmanUrl;

	public HeaderAuthenticationFilter(ValidationInterface valIntr, String authmanUrl) {
		this.valIntr = valIntr;
		this.authmanUrl = authmanUrl;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(HeaderAuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader(Constants.HEADER_TOKEN);
		boolean checkToken = false;
		LOGGER.info("Header Token [{}] HTTP method [{}]", token, httpRequest.getMethod());
		SecurityContext contextBefChainExecution = createSecurityContext(token, checkToken);
		try {
			SecurityContextHolder.setContext(contextBefChainExecution);
			LOGGER.info("do filter");
			chain.doFilter(request, response);
		} finally {
			SecurityContextHolder.clearContext();
		}
	}

	private SecurityContext createSecurityContext(final String token, final boolean checkToken) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

		ValidateResponse response = valIntr.validateToken(token, authmanUrl);
		if (response != null && Constants.SUCCESS_CODE.equals(response.getResponseCode())) {
			LOGGER.info("Validated");
			securityContext = new SecurityContextImpl();
			PreAuthenticatedAuthenticationToken pt = new PreAuthenticatedAuthenticationToken(response.getToken(), "",
					null);
			securityContext.setAuthentication(pt);
		}
		return securityContext;
	}

}
