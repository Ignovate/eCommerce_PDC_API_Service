package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CountriesRegionAreaEntity;

public interface CountriesRegionAreaRepo extends JpaRepository<CountriesRegionAreaEntity, Long>, JpaSpecificationExecutor<CountriesRegionAreaEntity>{

}
