package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CategoriesProductEntity;

public interface CategoriesProductRepo extends JpaRepository<CategoriesProductEntity, Long>, JpaSpecificationExecutor<CategoriesProductEntity>{

}
