package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductPrice;

public interface ProductsPriceRepo
		extends JpaRepository<ProductPrice, Long>, JpaSpecificationExecutor<ProductPrice> {

}
