package com.gaia.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gaia.intr.ValidationInterface;
import com.gaia.security.filter.HeaderAuthenticationFilter;
import com.gaia.security.filter.UnAuthorizedEntryPointHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${authman.disable:/test}")
	private String[] url;

	@Value("${authman.url}")
	private String authmanUrl;

	@Autowired
	private ValidationInterface valIntr;

	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(url);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(new UnAuthorizedEntryPointHandler()).and().authorizeRequests().anyRequest()
				.authenticated();
	}

	private Filter authenticationFilter() {
		HeaderAuthenticationFilter filter = new HeaderAuthenticationFilter(valIntr, authmanUrl);
		return filter;
	}

}
