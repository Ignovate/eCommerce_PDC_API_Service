package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sales_quote")
@DynamicUpdate(true)
public class SalesQuote implements Serializable {

	private static final long serialVersionUID = -2873385722503537883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "is_active")
	private boolean active;

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
	private LocalDateTime createdAt;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "saleQuote", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SalesQuoteItems> quoteOrderItems;

	@OneToOne(mappedBy = "saleQuote", fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	private SalesQuoteAddress quoteAddress;

	@JsonIgnore
	@OneToMany(mappedBy = "saleQuote", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SalesQuotePayment> quotePayments;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public List<SalesQuoteItems> getQuoteOrderItems() {
		return quoteOrderItems;
	}

	public void setQuoteOrderItems(List<SalesQuoteItems> quoteOrderItems) {
		this.quoteOrderItems = quoteOrderItems;
	}

	public SalesQuoteAddress getQuoteAddress() {
		return quoteAddress;
	}

	public void setQuoteAddress(SalesQuoteAddress quoteAddress) {
		this.quoteAddress = quoteAddress;
	}

	public Set<SalesQuotePayment> getQuotePayments() {
		return quotePayments;
	}

	public void setQuotePayments(Set<SalesQuotePayment> quotePayments) {
		this.quotePayments = quotePayments;
	}
	
//	public List<SalesQuotePayment> getQuotePayment() {
//		return quotePayments.stream().filter(payment -> payment.getStatus().compareTo("PR") == 0).findAny().orElse(null);
//	}

	@PrePersist
	public void oncreate() {
		setCreatedAt(Optional.ofNullable(this.createdAt).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedAt(Optional.ofNullable(this.updatedAt).map(m -> m).orElse(LocalDateTime.now()));
	}

	@PreUpdate
	public void preUpdate() {
		setUpdatedAt(LocalDateTime.now());
	}

}