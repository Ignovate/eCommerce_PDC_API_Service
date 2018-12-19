package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@DynamicUpdate(true)
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "title")
	private String title;
	@Column(name = "supervisor")
	private Long supervisor;
	@Column(name = "region_id")
	private Long region;
	@Column(name = "base_salary")
	private BigDecimal baseSalary;
	@Column(name = "contract")
	private String contract;
	@Column(name = "working_days")
	private String workingDays;

	@Type(type = "org.hibernate.type.LocalTimeType")
	@Column(name = "work_hours_start")
	private LocalTime workHoursStart;

	@Type(type = "org.hibernate.type.LocalTimeType")
	@Column(name = "work_hours_end")
	private LocalTime workHoursEnd;
	@Column(name = "profile_image_path")
	private String profileImagePath;
	@Column(name = "created_by")
	private Long createdBy;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "modified_by")
	private Long modifiedBy;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "password")
	private String password;

	@Column(name = "pin")
	private String pin;
	@Column(name = "sales_rep_overall_rank")
	private String salesRepOverallRank;
	@Column(name = "sales_rep_regional_rank")
	private String salesRepRegionalRank;
	@Column(name = "position_latitude")
	private String positionLatitude;
	@Column(name = "position_longitude")
	private String positionLongitude;
	@Column(name = "age")
	private Long age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "ethinicity")
	private String ethinicity;

	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "phone")
	private String phone;
	@Column(name = "status")
	private String status;
	@Column(name = "fcm")
	private String fcm;

	@Type(type = "org.hibernate.type.LocalDateType")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "reason_of_reject")
	private String reasonOfReject;
	@Column(name = "role")
	private String role;

	@PrePersist
	public void oncreate() {
		setCreatedOn(Optional.ofNullable(this.createdOn).map(m->m).orElse(LocalDateTime.now()));
		setModifiedOn(Optional.ofNullable(this.modifiedOn).map(m->m).orElse(LocalDateTime.now()));
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public BigDecimal getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public LocalTime getWorkHoursStart() {
		return workHoursStart;
	}

	public void setWorkHoursStart(LocalTime workHoursStart) {
		this.workHoursStart = workHoursStart;
	}

	public LocalTime getWorkHoursEnd() {
		return workHoursEnd;
	}

	public void setWorkHoursEnd(LocalTime workHoursEnd) {
		this.workHoursEnd = workHoursEnd;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getSalesRepOverallRank() {
		return salesRepOverallRank;
	}

	public void setSalesRepOverallRank(String salesRepOverallRank) {
		this.salesRepOverallRank = salesRepOverallRank;
	}

	public String getSalesRepRegionalRank() {
		return salesRepRegionalRank;
	}

	public void setSalesRepRegionalRank(String salesRepRegionalRank) {
		this.salesRepRegionalRank = salesRepRegionalRank;
	}

	public String getPositionLatitude() {
		return positionLatitude;
	}

	public void setPositionLatitude(String positionLatitude) {
		this.positionLatitude = positionLatitude;
	}

	public String getPositionLongitude() {
		return positionLongitude;
	}

	public void setPositionLongitude(String positionLongitude) {
		this.positionLongitude = positionLongitude;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthinicity() {
		return ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFcm() {
		return fcm;
	}

	public void setFcm(String fcm) {
		this.fcm = fcm;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getReasonOfReject() {
		return reasonOfReject;
	}

	public void setReasonOfReject(String reasonOfReject) {
		this.reasonOfReject = reasonOfReject;
	}

}
