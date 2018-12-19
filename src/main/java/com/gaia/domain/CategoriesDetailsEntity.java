package com.gaia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "categories_details")
@DynamicUpdate(true)
public class CategoriesDetailsEntity implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "url_key")
	private String urlKey;

	@Lob
	@Column(name = "image")
	private byte[] img;

	@Lob
	@Column(name = "thumbnail")
	private byte[] thumnail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public byte[] getThumnail() {
		return thumnail;
	}

	public void setThumnail(byte[] thumnail) {
		this.thumnail = thumnail;
	}

}
