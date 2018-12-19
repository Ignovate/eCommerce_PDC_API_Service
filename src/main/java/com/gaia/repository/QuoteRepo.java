package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.QuoteEntity;

public interface QuoteRepo
		extends JpaRepository<QuoteEntity, Long>, JpaSpecificationExecutor<QuoteEntity> {

}
