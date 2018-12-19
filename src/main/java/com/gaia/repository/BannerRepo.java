package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.BannerEntity;

public interface BannerRepo
		extends JpaRepository<BannerEntity, Long>, JpaSpecificationExecutor<BannerEntity> {

}
