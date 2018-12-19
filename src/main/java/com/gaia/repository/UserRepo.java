package com.gaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gaia.domain.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

}
