package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductInventory;

public interface ProductInventoryRepo
		extends JpaRepository<ProductInventory, Long>, JpaSpecificationExecutor<ProductInventory> {
}
