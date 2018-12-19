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
@Table(name = "sales_quote_payment")
@DynamicUpdate(true)
public class SalesQuotePayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594717147997484862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "quote_id")
	private Long quoteId;

	@Column(name = "method")
	private String method;

	@Column(name = "cc_type")
	private String ccType;

	@Column(name = "cc_last4")
	private String ccLast4;

	@Column(name = "cc_owner")
	private String ccOwner;

	@Column(name = "cc_exp_month")
	private Long ccExpMonth;

	@Column(name = "cc_exp_year")
	private Long ccExpYear;

	@Column(name = "additional_data")
	private String additionalData;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_at")
	private LocalDateTime createdDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedDate;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quote_id", insertable = false, updatable = false)
	private SalesQuote saleQuote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcLast4() {
		return ccLast4;
	}

	public void setCcLast4(String ccLast4) {
		this.ccLast4 = ccLast4;
	}

	public String getCcOwner() {
		return ccOwner;
	}

	public void setCcOwner(String ccOwner) {
		this.ccOwner = ccOwner;
	}

	public Long getCcExpMonth() {
		return ccExpMonth;
	}

	public void setCcExpMonth(Long ccExpMonth) {
		this.ccExpMonth = ccExpMonth;
	}

	public Long getCcExpYear() {
		return ccExpYear;
	}

	public void setCcExpYear(Long ccExpYear) {
		this.ccExpYear = ccExpYear;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
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

	public SalesQuote getSaleQuote() {
		return saleQuote;
	}

	public void setSaleQuote(SalesQuote saleQuote) {
		this.saleQuote = saleQuote;
	}

	@PrePersist
	public void oncreate() {
		setCreatedDate(Optional.ofNullable(this.createdDate).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedDate(Optional.ofNullable(this.updatedDate).map(m -> m).orElse(LocalDateTime.now()));
	}

}
