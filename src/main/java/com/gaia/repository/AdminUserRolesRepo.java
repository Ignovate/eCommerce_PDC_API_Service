package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.AdminUserRolesEntity;

public interface AdminUserRolesRepo
		extends JpaRepository<AdminUserRolesEntity, Long>, JpaSpecificationExecutor<AdminUserRolesEntity> {

}
