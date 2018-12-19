package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "admin_user")
@DynamicUpdate(true)
public class AdminUserEntity implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "is_superadmin")
	private Long isSuperAdmin;

	@Column(name = "is_active")
	private Long isActive;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_at")
	private LocalDateTime createdDt;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedDt;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "last_logged_date")
	private LocalDateTime lastLogin;

	@Column(name = "login_attempts")
	private Long loginAttempts;

	@Column(name = "is_locked")
	private Long isLocked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Long isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public LocalDateTime getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(LocalDateTime updatedDt) {
		this.updatedDt = updatedDt;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Long getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Long loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Long getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Long isLocked) {
		this.isLocked = isLocked;
	}

}
