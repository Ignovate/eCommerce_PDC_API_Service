package com.gaia.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "banner")
@DynamicUpdate(true)
public class BannerEntity implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "banner_id")
	private Long bannerId;

	@Column(name = "layout_name")
	private String layoutName;

	@Column(name = "path")
	private String path;

	@Column(name = "banner_type")
	private String bannerType;

	@Column(name = "is_active")
	private Boolean active;
	
	@OneToMany(mappedBy = "banner", fetch = FetchType.EAGER)
	private List<BannerImageEntity> bannerImages;

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<BannerImageEntity> getBannerImages() {
		return bannerImages;
	}

	public void setBannerImages(List<BannerImageEntity> bannerImages) {
		this.bannerImages = bannerImages;
	}
	
	

}
