package com.gaia.web.rest.vm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserVm {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String title;
	private Long supervisor;
	private Long region;
	private BigDecimal baseSalary;
	private String contract;
	private String workingDays;

	private LocalTime workHoursStart;

	private LocalTime workHoursEnd;
	private String profileImagePath;
	private Long createdBy;

	private LocalDateTime createdOn;
	private Long modifiedBy;

	private LocalDateTime modifiedOn;

	@JsonIgnoreProperties(allowGetters = true)
	private String password;

	private String pin;
	private String salesRepOverallRank;
	private String salesRepRegionalRank;
	private String positionLatitude;
	private String positionLongitude;
	private Long age;
	private String gender;
	private String ethinicity;

	private String middleName;
	private String phone;
	private String status;
	private String fcm;

	private LocalDate dateOfBirth;

	private String reasonOfReject;
	private String role;

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
