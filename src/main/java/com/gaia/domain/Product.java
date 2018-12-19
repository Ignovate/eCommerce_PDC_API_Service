package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "products")
@DynamicUpdate(true)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691632954303452398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sku")
	private String sku;

	@Column(name = "type_id")
	private Long typeId;

	@Column(name = "website_id")
	private Long websiteId;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "created_at")
	private LocalDateTime createdDate;

	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(name = "updated_at")
	private LocalDateTime updatedDate;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductAttributes attribute;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductDeals deal;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductImage image;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductInventory inventory;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductPrice price;

	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ProductStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
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

	public ProductAttributes getAttribute() {
		return attribute;
	}

	public void setAttribute(ProductAttributes attribute) {
		this.attribute = attribute;
	}

	public ProductDeals getDeal() {
		return deal;
	}

	public void setDeal(ProductDeals deal) {
		this.deal = deal;
	}

	public ProductImage getImage() {
		return image;
	}

	public void setImage(ProductImage image) {
		this.image = image;
	}

	public ProductInventory getInventory() {
		return inventory;
	}

	public void setInventory(ProductInventory inventory) {
		this.inventory = inventory;
	}

	public ProductPrice getPrice() {
		return price;
	}

	public void setPrice(ProductPrice price) {
		this.price = price;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	@PrePersist
	public void oncreate() {
		setCreatedDate(Optional.ofNullable(this.createdDate).map(m -> m).orElse(LocalDateTime.now()));
		setUpdatedDate(Optional.ofNullable(this.updatedDate).map(m -> m).orElse(LocalDateTime.now()));
	}

}
