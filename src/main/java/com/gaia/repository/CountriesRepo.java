package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CountriesEntity;

public interface CountriesRepo extends JpaRepository<CountriesEntity, Long>, JpaSpecificationExecutor<CountriesEntity>{

	public CountriesEntity findByName(String name);
}
