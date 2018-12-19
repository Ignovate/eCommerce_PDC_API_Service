package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesQuoteAddress;

public interface SalesQuoteAdrsRepo extends JpaRepository<SalesQuoteAddress, Long>, JpaSpecificationExecutor<SalesQuoteAddress>  {

}
