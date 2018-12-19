package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductImage;

public interface ProductImageRepo
		extends JpaRepository<ProductImage, Long>, JpaSpecificationExecutor<ProductImage> {

}
