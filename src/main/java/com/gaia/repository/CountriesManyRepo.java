package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CountriesManyEntity;

public interface CountriesManyRepo extends JpaRepository<CountriesManyEntity, Long>, JpaSpecificationExecutor<CountriesManyEntity>{

}
