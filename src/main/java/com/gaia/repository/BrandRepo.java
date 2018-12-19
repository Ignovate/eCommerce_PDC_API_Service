package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

}
