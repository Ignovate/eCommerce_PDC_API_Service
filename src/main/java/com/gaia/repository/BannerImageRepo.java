package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.BannerImageEntity;

public interface BannerImageRepo
		extends JpaRepository<BannerImageEntity, Long>, JpaSpecificationExecutor<BannerImageEntity> {

}
