package com.gaia.web.rest.vm;

import com.gaia.domain.CustomersEntity;

public class LoginRespVm {

	private String message;
	private CustomersEntity customer;

	public LoginRespVm() {

	}

	public LoginRespVm(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomersEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomersEntity customer) {
		this.customer = customer;
	}

}
