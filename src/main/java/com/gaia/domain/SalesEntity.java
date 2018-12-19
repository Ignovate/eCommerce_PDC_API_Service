package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "sales_order")
@DynamicUpdate(true)
public class SalesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2873385722503537883L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToMany(mappedBy="orderId", cascade=CascadeType.ALL)
	private List<SalesOrderAddress> salesOrderAdressEntity;
	
	@OneToMany(mappedBy="orderId", cascade=CascadeType.ALL)
	private List<SalesOrderItems> salesOrderItemsEntity;
	
	@OneToMany(mappedBy="orderId", cascade=CascadeType.ALL)
	private List<SalesOrderPayment> salesOrderPayEntity;
	

	@Column(name = "display_id")
	private String displayId;

	@Column(name = "customer_id")
	private Long customerId;
    
	@Column(name = "website_id")
	private Long websiteId;

	@Column(name = "currency")
	private String currency;

	@Column(name = "total_items")
	private Long totalItems;

	@Column(name = "total_items_qty")
	private Long totalItemsQty;

	@Column(name = "grand_total")
	private BigDecimal grandTotal;

	@Column(name = "shipping_amount")
	private BigDecimal shippingAmount;

	@Column(name = "tax_amount")
	private BigDecimal taxAmount;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "cod_charges")
	private BigDecimal codCharges;
	
	@Column(name = "payment_method")
	private String paymentMethod;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public List<SalesOrderAddress> getSalesOrderAdressEntity() {
		return salesOrderAdressEntity;
	}

	public void setSalesOrderAdressEntity(List<SalesOrderAddress> salesOrderAdressEntity) {
		this.salesOrderAdressEntity = salesOrderAdressEntity;
	}

	public List<SalesOrderItems> getSalesOrderItemsEntity() {
		return salesOrderItemsEntity;
	}

	public void setSalesOrderItemsEntity(List<SalesOrderItems> salesOrderItemsEntity) {
		this.salesOrderItemsEntity = salesOrderItemsEntity;
	}

	public List<SalesOrderPayment> getSalesOrderPayEntity() {
		return salesOrderPayEntity;
	}

	public void setSalesOrderPayEntity(List<SalesOrderPayment> salesOrderPayEntity) {
		this.salesOrderPayEntity = salesOrderPayEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getTotalItemsQty() {
		return totalItemsQty;
	}

	public void setTotalItemsQty(Long totalItemsQty) {
		this.totalItemsQty = totalItemsQty;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public BigDecimal getCodCharges() {
		return codCharges;
	}

	public void setCodCharges(BigDecimal codCharges) {
		this.codCharges = codCharges;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	public void oncreate() {
		setCreatedAt(Optional.ofNullable(this.createdAt).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedAt(Optional.ofNullable(this.updatedAt).map(m -> m).orElse(LocalDateTime.now()));
	}

}