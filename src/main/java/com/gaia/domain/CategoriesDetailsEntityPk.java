package com.gaia.domain;

import java.io.Serializable;

import javax.persistence.Basic;

public class CategoriesDetailsEntityPk implements Serializable {

	private static final long serialVersionUID = -1809892913018873056L;

	@Basic
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}