package com.gaia.web.rest.vm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gaia.common.Constants;

public class ProductVm {

	private Long categoryId;
	private Long productId;
	private String sku;
	private String name;
	private BigDecimal price;
	private BigDecimal specialPrice;
	private LocalDateTime splPriceStartDate;
	private LocalDateTime splPriceEndDate;
	private String imageUrl;
	private String imageLabel;
	private Long stock;
	private String measurement;
	private Long stockStatus;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getImageUrl() {
		return Constants.APP_PATH + imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(String imageLabel) {
		this.imageLabel = imageLabel;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public Long getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Long stockStatus) {
		this.stockStatus = stockStatus;
	}

}
