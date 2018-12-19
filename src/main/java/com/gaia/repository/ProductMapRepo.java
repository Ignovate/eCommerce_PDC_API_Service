package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.ProductMapEntity;

public interface ProductMapRepo extends JpaRepository<ProductMapEntity, Long>, JpaSpecificationExecutor<ProductMapEntity>{

}
