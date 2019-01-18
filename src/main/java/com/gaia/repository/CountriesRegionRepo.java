package com.gaia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CountriesRegionEntity;

public interface CountriesRegionRepo extends JpaRepository<CountriesRegionEntity, Long>, JpaSpecificationExecutor<CountriesRegionEntity>{

	public List<CountriesRegionEntity> findByCountryId(Long countryId);
	
	public void deleteByCountryId(Long countryId);
}
