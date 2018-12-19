package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
@Entity
@Table(name = "user_master")
@DynamicUpdate(true)
public class UserMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8858410496366202520L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_groups")
	private String userGroups;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "user_status")
	private String userStatus;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "prev_user_status")
	private String prevUserStatus;

	@Column(name = "maker_id")
	private String makerId;
	
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "maker_dttm")
	private LocalDateTime makerDtTm;
	
	@PrePersist
	public void oncreate() {
//		setLastLoginDate(Optional.ofNullable(this.lastLoginDate).map(m->m).orElse(LocalDateTime.now()));
		setMakerDtTm(Optional.ofNullable(this.makerDtTm).map(m->m).orElse(LocalDateTime.now()));
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(String userGroups) {
		this.userGroups = userGroups;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrevUserStatus() {
		return prevUserStatus;
	}

	public void setPrevUserStatus(String prevUserStatus) {
		this.prevUserStatus = prevUserStatus;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public LocalDateTime getMakerDtTm() {
		return makerDtTm;
	}

	public void setMakerDtTm(LocalDateTime makerDtTm) {
		this.makerDtTm = makerDtTm;
	}
}
