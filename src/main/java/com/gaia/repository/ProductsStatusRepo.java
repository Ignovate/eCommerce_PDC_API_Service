package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductStatus;

public interface ProductsStatusRepo
		extends JpaRepository<ProductStatus, Long>, JpaSpecificationExecutor<ProductStatus> {

}
