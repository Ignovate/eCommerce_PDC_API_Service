package com.gaia.domain;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaia.common.Constants;

@Entity
@Table(name = "products_images")
@DynamicUpdate(true)
public class ProductImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2873385722503537883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "image")
	private String image;

	@Column(name = "image_label")
	private String imageLabel;

	@Column(name = "thumbnail1")
	private String thumbnail1;

	@Column(name = "thumbnail_label1")
	private String thumbnailLabel1;

	@Column(name = "thumbnail2")
	private String thumbnail2;

	@Column(name = "thumbnail_label2")
	private String thumbnailLabel2;

	@Column(name = "thumbnail3")
	private String thumbnail3;

	@Column(name = "thumbnail_label3")
	private String thumbnailLabel3;

	@Column(name = "thumbnail4")
	private String thumbnail4;

	@Column(name = "thumbnail_label4")
	private String thumbnailLabel4;

	@Column(name = "small_image")
	private String smallImage;

	@Column(name = "small_image_label")
	private String smallImageLabel;

	@Column(name = "position")
	private Long position;

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

	public String getImage() {
		return Constants.APP_PATH + image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(String imageLabel) {
		this.imageLabel = imageLabel;
	}

	public String getThumbnail1() {
		return Constants.APP_PATH + thumbnail1;
	}

	public void setThumbnail1(String thumbnail1) {
		this.thumbnail1 = thumbnail1;
	}

	public String getThumbnailLabel1() {
		return thumbnailLabel1;
	}

	public void setThumbnailLabel1(String thumbnailLabel1) {
		this.thumbnailLabel1 = thumbnailLabel1;
	}

	public String getThumbnail2() {
		return Constants.APP_PATH + thumbnail2;
	}

	public void setThumbnail2(String thumbnail2) {
		this.thumbnail2 = thumbnail2;
	}

	public String getThumbnailLabel2() {
		return Constants.APP_PATH + thumbnailLabel2;
	}

	public void setThumbnailLabel2(String thumbnailLabel2) {
		this.thumbnailLabel2 = thumbnailLabel2;
	}

	public String getThumbnail3() {
		return Constants.APP_PATH + thumbnail3;
	}

	public void setThumbnail3(String thumbnail3) {
		this.thumbnail3 = thumbnail3;
	}

	public String getThumbnailLabel4() {
		return Constants.APP_PATH + thumbnailLabel4;
	}

	public void setThumbnailLabel4(String thumbnailLabel4) {
		this.thumbnailLabel4 = thumbnailLabel4;
	}

	public String getSmallImage() {
		return Constants.APP_PATH + smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getSmallImageLabel() {
		return smallImageLabel;
	}

	public void setSmallImageLabel(String smallImageLabel) {
		this.smallImageLabel = smallImageLabel;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getThumbnailLabel3() {
		return thumbnailLabel3;
	}

	public void setThumbnailLabel3(String thumbnailLabel3) {
		this.thumbnailLabel3 = thumbnailLabel3;
	}

	public String getThumbnail4() {
		return thumbnail4;
	}

	public void setThumbnail4(String thumbnail4) {
		this.thumbnail4 = thumbnail4;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}