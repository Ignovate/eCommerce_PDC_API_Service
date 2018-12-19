package com.gaia.configuration;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GaiaConfiguration {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		return new DozerBeanMapper(Arrays.asList("dozer-config.xml"));
	}
}
