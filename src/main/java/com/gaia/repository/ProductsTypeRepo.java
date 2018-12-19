package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductsType;

public interface ProductsTypeRepo
		extends JpaRepository<ProductsType, Long>, JpaSpecificationExecutor<ProductsType> {

}
