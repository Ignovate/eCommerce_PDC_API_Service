package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesOrderAddress;

public interface SalesOrderAddressRepo
		extends JpaRepository<SalesOrderAddress, Long>, JpaSpecificationExecutor<SalesOrderAddress> {

}
