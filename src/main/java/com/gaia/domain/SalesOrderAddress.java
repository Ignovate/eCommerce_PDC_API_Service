package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sales_order_address")
@DynamicUpdate(true)
public class SalesOrderAddress implements Serializable {

	private static final long serialVersionUID = -2873385722503537883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "street")
	private String street;

	@Column(name = "country")
	private String country;

	@Column(name = "region")
	private String region;

	@Column(name = "area")
	private String area;

	@Column(name = "postcode")
	private String postCode;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_at")
	private LocalDateTime createdDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedDate;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private SalesOrder saleOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public SalesOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SalesOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	@PrePersist
	public void oncreate() {
		setCreatedDate(Optional.ofNullable(this.createdDate).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedDate(Optional.ofNullable(this.updatedDate).map(m -> m).orElse(LocalDateTime.now()));
	}

}