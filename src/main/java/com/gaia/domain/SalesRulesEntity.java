package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sales_rules")
@DynamicUpdate(true)
public class SalesRulesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7021983157165636768L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_active")
	private Long isActive;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "coupon_code")
	private String couponCode;
	
	@Column(name = "discount_amount")
	private BigDecimal discountAmount;
	
	@Column(name = "discount_percentage")
	private Long discountPercentage;
	
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "coupon_limit")
	private Long couponLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Long getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Long discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Long getCouponLimit() {
		return couponLimit;
	}

	public void setCouponLimit(Long couponLimit) {
		this.couponLimit = couponLimit;
	}
	
	@PrePersist
	public void oncreate() {
		setStartDate(Optional.ofNullable(this.startDate).map(m -> m).orElse(LocalDateTime.now()));
		setEndDate(Optional.ofNullable(this.endDate).map(m -> m).orElse(LocalDateTime.now()));
	}

}
