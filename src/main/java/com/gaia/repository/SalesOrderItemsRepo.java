package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesOrderItems;

public interface SalesOrderItemsRepo
		extends JpaRepository<SalesOrderItems, Long>, JpaSpecificationExecutor<SalesOrderItems> {

}
