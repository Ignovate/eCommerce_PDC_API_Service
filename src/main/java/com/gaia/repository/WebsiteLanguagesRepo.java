package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.WebsiteLanguagesEntity;

public interface WebsiteLanguagesRepo
		extends JpaRepository<WebsiteLanguagesEntity, Long>, JpaSpecificationExecutor<WebsiteLanguagesEntity> {

}
