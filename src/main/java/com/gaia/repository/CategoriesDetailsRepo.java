package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.CategoriesDetailsEntity;

public interface CategoriesDetailsRepo
		extends JpaRepository<CategoriesDetailsEntity, Long>, JpaSpecificationExecutor<CategoriesDetailsEntity> {

}
