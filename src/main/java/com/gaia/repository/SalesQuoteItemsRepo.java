package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SalesQuoteItems;

public interface SalesQuoteItemsRepo extends JpaRepository<SalesQuoteItems, Long>, JpaSpecificationExecutor<SalesQuoteItems> {

}
