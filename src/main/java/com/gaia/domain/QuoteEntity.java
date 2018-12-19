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
@Table(name = "sales_quote")
@DynamicUpdate(true)
public class QuoteEntity implements Serializable {

	private static final long serialVersionUID = -2873385722503537883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToMany(mappedBy="quoteId", cascade=CascadeType.ALL)
	private List<SalesQuoteAddress> quoteAddress;
	
	@OneToMany(mappedBy="quoteId", cascade=CascadeType.ALL)
	private List<SalesQuoteItems> quoteItem;
	
	@OneToMany(mappedBy="quoteId", cascade=CascadeType.ALL)
	private List<SalesQuotePayment> quotePayment;


	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "is_active")
	private Long isActive;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
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
	
	public List<SalesQuoteAddress> getQuoteAddress() {
		return quoteAddress;
	}

	public void setQuoteAddress(List<SalesQuoteAddress> quoteAddress) {
		this.quoteAddress = quoteAddress;
	}

	public List<SalesQuoteItems> getQuoteItem() {
		return quoteItem;
	}

	public void setQuoteItem(List<SalesQuoteItems> quoteItem) {
		this.quoteItem = quoteItem;
	}

	public List<SalesQuotePayment> getQuotePayment() {
		return quotePayment;
	}

	public void setQuotePayment(List<SalesQuotePayment> quotePayment) {
		this.quotePayment = quotePayment;
	}

	@PrePersist
	public void oncreate() {
		setCreatedAt(Optional.ofNullable(this.createdAt).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedAt(Optional.ofNullable(this.updatedAt).map(m -> m).orElse(LocalDateTime.now()));
	}

}