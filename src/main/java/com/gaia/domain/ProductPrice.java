package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products_price")
@DynamicUpdate(true)
public class ProductPrice implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "cost")
	private BigDecimal cost;

	@Column(name = "special_price")
	private BigDecimal specialPrice;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "special_price_start_date")
	private LocalDateTime splPriceStartDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "special_price_end_date")
	private LocalDateTime splPriceEndDate;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSpecialPrice() {
		LocalDateTime today = LocalDateTime.now();
		if (specialPrice != null && splPriceStartDate != null && splPriceStartDate.isBefore(today)
				&& splPriceEndDate != null && splPriceEndDate.isAfter(today))
			return specialPrice;

		return null;
	}

	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	public LocalDateTime getSplPriceStartDate() {
		return splPriceStartDate;
	}

	public void setSplPriceStartDate(LocalDateTime splPriceStartDate) {
		this.splPriceStartDate = splPriceStartDate;
	}

	public LocalDateTime getSplPriceEndDate() {
		return splPriceEndDate;
	}

	public void setSplPriceEndDate(LocalDateTime splPriceEndDate) {
		this.splPriceEndDate = splPriceEndDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
