package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

}
