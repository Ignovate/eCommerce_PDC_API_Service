package com.gaia.domain;

import java.io.Serializable;
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

@Entity
@Table(name = "products")
@DynamicUpdate(true)
public class ProductMapEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3405232232964534464L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

//	@Column(name = "sku")
//	private String sku;
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id")
//	private ProductsType productsType;
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id")
//	private WebsitesEntity websites;
//
//	@Type(type = "org.hibernate.type.LocalDateTimeType")
//	@Column(name = "created_at")
//	private LocalDateTime createdAt;
//
//	@Type(type = "org.hibernate.type.LocalDateTimeType")
//	@Column(name = "updated_at")
//	private LocalDateTime updatedAt;
//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//
//	public String getSku() {
//		return sku;
//	}
//
//	public void setSku(String sku) {
//		this.sku = sku;
//	}
//
//	public ProductsType getProductsType() {
//		return productsType;
//	}
//
//	public void setProductsType(ProductsType productsType) {
//		this.productsType = productsType;
//	}
//
//	public WebsitesEntity getWebsites() {
//		return websites;
//	}
//
//	public void setWebsites(WebsitesEntity websites) {
//		this.websites = websites;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}

}
