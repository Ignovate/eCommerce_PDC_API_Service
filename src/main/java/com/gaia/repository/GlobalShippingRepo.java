package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.GlobalShipping;

public interface GlobalShippingRepo
		extends JpaRepository<GlobalShipping, Long>, JpaSpecificationExecutor<GlobalShipping> {

}
