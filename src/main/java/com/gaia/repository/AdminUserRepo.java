package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.AdminUserEntity;

public interface AdminUserRepo
		extends JpaRepository<AdminUserEntity, Long>, JpaSpecificationExecutor<AdminUserEntity> {

}
