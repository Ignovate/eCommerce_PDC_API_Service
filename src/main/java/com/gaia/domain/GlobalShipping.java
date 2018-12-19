package com.gaia.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "gaia_shipconfig")
@DynamicUpdate(true)
public class GlobalShipping implements Serializable {

	private static final long serialVersionUID = 7164864047865501455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long shippingId;

	@Column(name = "ship_label")
	private String shipLabel;

	@Column(name = "ship_amount")
	private BigDecimal shipAmount;

	@Column(name = "ship_origin")
	private Long shipOrigin;

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShipLabel() {
		return shipLabel;
	}

	public void setShipLabel(String shipLabel) {
		this.shipLabel = shipLabel;
	}

	public BigDecimal getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(BigDecimal shipAmount) {
		this.shipAmount = shipAmount;
	}

	public Long getShipOrigin() {
		return shipOrigin;
	}

	public void setShipOrigin(Long shipOrigin) {
		this.shipOrigin = shipOrigin;
	}

}
