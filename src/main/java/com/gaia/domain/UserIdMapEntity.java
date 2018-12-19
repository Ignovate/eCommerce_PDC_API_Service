package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_id_mapping")
@DynamicUpdate(true)
public class UserIdMapEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6535171826066014395L;
		
	@Id
	@Column(name = "login_user_id")
	private String loginUserId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_prev_password")
	private String prevPassword;
	
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;
	
	@Column(name = "incorrect_passwd_cnt")
	private int incorrectPasswdCnt;
	
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

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrevPassword() {
		return prevPassword;
	}

	public void setPrevPassword(String prevPassword) {
		this.prevPassword = prevPassword;
	}

	public int getIncorrectPasswdCnt() {
		return incorrectPasswdCnt;
	}

	public void setIncorrectPasswdCnt(int incorrectPasswdCnt) {
		this.incorrectPasswdCnt = incorrectPasswdCnt;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public LocalDateTime getMakerDtTm() {
		return makerDtTm;
	}

	public void setMakerDtTm(LocalDateTime makerDtTm) {
		this.makerDtTm = makerDtTm;
	}

	

	

}
