package com.gaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication()
public class GaiaApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaiaApiServiceApplication.class, args);
	}
}
