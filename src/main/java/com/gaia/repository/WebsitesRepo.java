package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.WebsitesEntity;

public interface WebsitesRepo
		extends JpaRepository<WebsitesEntity, Long>, JpaSpecificationExecutor<WebsitesEntity> {

}
