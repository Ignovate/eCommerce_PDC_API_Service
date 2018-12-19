package com.gaia.web.rest.vm;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SortProductVm {

	@JsonProperty("product_id")
	private Long prdId;
	@JsonProperty("SKU")
	private String sku;
	private String name;
	private String measurement;
	private String stock;
	@JsonProperty("stock_status")
	private String stockStatus;
	private BigDecimal price;
	@JsonProperty("special_price")
	private BigDecimal splPrice;
	private String image;

	public Long getPrdId() {
		return prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSplPrice() {
		return splPrice;
	}

	public void setSplPrice(BigDecimal splPrice) {
		this.splPrice = splPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
