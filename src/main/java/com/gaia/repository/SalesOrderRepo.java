package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesOrder;

public interface SalesOrderRepo
		extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {

}
