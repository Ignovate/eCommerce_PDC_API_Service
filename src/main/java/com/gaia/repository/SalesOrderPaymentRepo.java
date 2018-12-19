package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesOrderPayment;

public interface SalesOrderPaymentRepo
		extends JpaRepository<SalesOrderPayment, Long>, JpaSpecificationExecutor<SalesOrderPayment> {

}
