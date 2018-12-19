package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CustomersAddrEntity;

public interface CustomersAddrRepo
		extends JpaRepository<CustomersAddrEntity, Long>, JpaSpecificationExecutor<CustomersAddrEntity> {

}
