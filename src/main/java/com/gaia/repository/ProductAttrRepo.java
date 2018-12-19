package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductAttributes;

public interface ProductAttrRepo
		extends JpaRepository<ProductAttributes, Long>, JpaSpecificationExecutor<ProductAttributes> {

}
