package com.gaia.domain;

import java.io.Serializable;
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
@Table(name = "products")
@DynamicUpdate(true)
public class SingleProductEntity implements Serializable{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691632954303452398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
//
//	@Column(name = "sku")
//	private String sku;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductAttributes> attrEntity;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductDeals> dealsEntity;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductImage> imageEntity;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductInventory> inventoryEntity;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductPrice> priceEntity;
//	
//	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
//	private List<ProductStatus> statusEntity;
//	
//
//	@Column(name = "type_id")
//	private Long typeID;
//
//	@Column(name = "website_id")
//	private Long websiteID;
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
//	public Long getTypeID() {
//		return typeID;
//	}
//
//	public void setTypeID(Long typeID) {
//		this.typeID = typeID;
//	}
//
//	public Long getWebsiteID() {
//		return websiteID;
//	}
//
//	public void setWebsiteID(Long websiteID) {
//		this.websiteID = websiteID;
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
//	
//	public List<ProductAttributes> getAttrEntity() {
//		return attrEntity;
//	}
//
//	public void setAttrEntity(List<ProductAttributes> attrEntity) {
//		this.attrEntity = attrEntity;
//	}
//
//	public List<ProductDeals> getDealsEntity() {
//		return dealsEntity;
//	}
//
//	public void setDealsEntity(List<ProductDeals> dealsEntity) {
//		this.dealsEntity = dealsEntity;
//	}
//
//	public List<ProductImage> getImageEntity() {
//		return imageEntity;
//	}
//
//	public void setImageEntity(List<ProductImage> imageEntity) {
//		this.imageEntity = imageEntity;
//	}
//
//	public List<ProductInventory> getInventoryEntity() {
//		return inventoryEntity;
//	}
//
//	public void setInventoryEntity(List<ProductInventory> inventoryEntity) {
//		this.inventoryEntity = inventoryEntity;
//	}
//
//	public List<ProductPrice> getPriceEntity() {
//		return priceEntity;
//	}
//
//	public void setPriceEntity(List<ProductPrice> priceEntity) {
//		this.priceEntity = priceEntity;
//	}
//
//	public List<ProductStatus> getStatusEntity() {
//		return statusEntity;
//	}
//
//	public void setStatusEntity(List<ProductStatus> statusEntity) {
//		this.statusEntity = statusEntity;
//	}
//
//	@PrePersist
//	public void oncreate() {
//		setCreatedAt(Optional.ofNullable(this.createdAt).map(m -> m).orElse(LocalDateTime.now()));
//		setUpdatedAt(Optional.ofNullable(this.updatedAt).map(m -> m).orElse(LocalDateTime.now()));
//	}

}
