package com.gaia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "gaia_tax")
@DynamicUpdate(true)
public class GlobalTax implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long taxId;

	@Column(name = "tax_percentage")
	private Long taxPercentage;

	@Column(name = "tax_label")
	private String taxLabel;

	@Column(name = "tax_origin")
	private Long taxOrigin;

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}

	public Long getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Long taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getTaxLabel() {
		return taxLabel;
	}

	public void setTaxLabel(String taxLabel) {
		this.taxLabel = taxLabel;
	}

	public Long getTaxOrigin() {
		return taxOrigin;
	}

	public void setTaxOrigin(Long taxOrigin) {
		this.taxOrigin = taxOrigin;
	}

}
