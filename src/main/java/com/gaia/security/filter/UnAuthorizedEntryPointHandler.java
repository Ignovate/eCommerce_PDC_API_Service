package com.gaia.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class UnAuthorizedEntryPointHandler implements AuthenticationEntryPoint{
	
	private static final String UNAUTHORIZED_JSON = "{\"error\":\"401\", \"message\":\"Not Authorized\"}";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		PrintWriter out = response.getWriter();
		out.print(UNAUTHORIZED_JSON);
		out.flush();
		out.close();
		
	}

}
