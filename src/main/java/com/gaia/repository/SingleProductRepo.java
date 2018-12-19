package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.SingleProductEntity;

public interface SingleProductRepo extends JpaRepository<SingleProductEntity, Long>, JpaSpecificationExecutor<SingleProductEntity>{

}
