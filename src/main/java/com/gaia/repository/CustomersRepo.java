package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CustomersEntity;

public interface CustomersRepo
		extends JpaRepository<CustomersEntity, Long>, JpaSpecificationExecutor<CustomersEntity> {

}
