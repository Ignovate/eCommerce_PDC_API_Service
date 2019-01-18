package com.gaia.web.rest.vm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentRespVm {

	private String status;
	private String errorCode;
	private String erroMessage;
	private BigDecimal amount;
	private String currency;
	private String transactionType;
	private String paymentModeType;
	private String merchantOrderNumber;
	private String cardType;
	private String gatewayTraceNumber;
	private LocalDateTime transactionDate;
	private String cardEnrollmentResponse;
	private String eciIndicator;
	private String authCode;
	private String referenceNumber;
	private String dccConverted;
	private String customerId;
	private String quoteId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getPaymentModeType() {
		return paymentModeType;
	}

	public void setPaymentModeType(String paymentModeType) {
		this.paymentModeType = paymentModeType;
	}

	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}

	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getGatewayTraceNumber() {
		return gatewayTraceNumber;
	}

	public void setGatewayTraceNumber(String gatewayTraceNumber) {
		this.gatewayTraceNumber = gatewayTraceNumber;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCardEnrollmentResponse() {
		return cardEnrollmentResponse;
	}

	public void setCardEnrollmentResponse(String cardEnrollmentResponse) {
		this.cardEnrollmentResponse = cardEnrollmentResponse;
	}

	public String getEciIndicator() {
		return eciIndicator;
	}

	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getDccConverted() {
		return dccConverted;
	}

	public void setDccConverted(String dccConverted) {
		this.dccConverted = dccConverted;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

}
