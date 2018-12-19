package com.gaia.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "countries")
@DynamicUpdate(true)
public class CountriesManyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1463349192247945256L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	
	@OneToMany(mappedBy="countryID", cascade=CascadeType.ALL)
	private List<CountriesRegionEntity> countriesRegionEntity;
	
	public List<CountriesRegionEntity> getCountriesRegionEntity() {
		return countriesRegionEntity;
	}

	public void setCountriesRegionEntity(List<CountriesRegionEntity> countriesRegionEntity) {
		this.countriesRegionEntity = countriesRegionEntity;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

}
