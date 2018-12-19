package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "sales_order")
@DynamicUpdate(true)
public class SalesOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2873385722503537883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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

	@Column(name = "sub_total")
	private BigDecimal subTotal;

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
	private LocalDateTime createdDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedDate;

	@OneToMany(mappedBy = "saleOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SalesOrderItems> saleOrderItems;

	@OneToOne(mappedBy = "saleOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	private SalesOrderAddress saleAddress;

	@OneToOne(mappedBy = "saleOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	private SalesOrderPayment salePayment;

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

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
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

	public List<SalesOrderItems> getSaleOrderItems() {
		return saleOrderItems;
	}

	public void setSaleOrderItems(List<SalesOrderItems> saleOrderItems) {
		this.saleOrderItems = saleOrderItems;
	}

	public SalesOrderAddress getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(SalesOrderAddress saleAddress) {
		this.saleAddress = saleAddress;
	}

	public SalesOrderPayment getSalePayment() {
		return salePayment;
	}

	public void setSalePayment(SalesOrderPayment salePayment) {
		this.salePayment = salePayment;
	}

	@PrePersist
	public void oncreate() {
		setCreatedDate(Optional.ofNullable(this.createdDate).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedDate(Optional.ofNullable(this.updatedDate).map(m -> m).orElse(LocalDateTime.now()));
	}

}