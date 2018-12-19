package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesOrderPayment;
import com.gaia.domain.SalesQuotePayment;

public interface SalesQuotePaymentRepo extends JpaRepository<SalesQuotePayment, Long>, JpaSpecificationExecutor<SalesQuotePayment> {

}
