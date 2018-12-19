package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductDeals;

public interface ProductDealsRepo
		extends JpaRepository<ProductDeals, Long>, JpaSpecificationExecutor<ProductDeals> {

}
