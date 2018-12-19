package com.gaia.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "categories")
@DynamicUpdate(true)
public class CategoriesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 139186188234519037L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "level")
	private Long level;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "product_count")
	private Long productCount;
	
	@Column(name="position")
	private Long position;
	
	@Column(name="in_menu")
	private Long inMenu;
	
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



	public Long getLevel() {
		return level;
	}



	public void setLevel(Long level) {
		this.level = level;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public Long getProductCount() {
		return productCount;
	}



	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}



	public Long getPosition() {
		return position;
	}



	public void setPosition(Long position) {
		this.position = position;
	}



	public Long getInMenu() {
		return inMenu;
	}



	public void setInMenu(Long inMenu) {
		this.inMenu = inMenu;
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



	@PrePersist
	public void oncreate() {
		setCreatedAt(Optional.ofNullable(this.createdAt).map(m->m).orElse(LocalDateTime.now()));
		setUpdatedAt(Optional.ofNullable(this.updatedAt).map(m->m).orElse(LocalDateTime.now()));
	}

}
