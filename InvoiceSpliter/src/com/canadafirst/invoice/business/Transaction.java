package com.canadafirst.invoice.business;

import java.util.Date;

public class Transaction {
	private Date invoiceDate;
	private Date pickupDate;
	private String trackingNumber;
	private String zone;
	private int packages;
	private int enteredWeight;
	private int auditedWeight;
	private String receiverName;
	private String receiverCompany;
	private String receiverAddress1;
	private String receiverAddress2;
	private String receiverCity;
	private String receiverState;
	private String receiverPostal;
	private String receiverCountry;
	private ChargeItem[] chargeItems;
	private double GST;
	private double HST;
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public int getPackages() {
		return packages;
	}
	public void setPackages(int packages) {
		this.packages = packages;
	}
	public int getEnteredWeight() {
		return enteredWeight;
	}
	public void setEnteredWeight(int enteredWeight) {
		this.enteredWeight = enteredWeight;
	}
	public int getAuditedWeight() {
		return auditedWeight;
	}
	public void setAuditedWeight(int auditedWeight) {
		this.auditedWeight = auditedWeight;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverCompany() {
		return receiverCompany;
	}
	public void setReceiverCompany(String receiverCompany) {
		this.receiverCompany = receiverCompany;
	}
	public String getReceiverAddress1() {
		return receiverAddress1;
	}
	public void setReceiverAddress1(String receiverAddress1) {
		this.receiverAddress1 = receiverAddress1;
	}
	public String getReceiverAddress2() {
		return receiverAddress2;
	}
	public void setReceiverAddress2(String receiverAddress2) {
		this.receiverAddress2 = receiverAddress2;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverPostal() {
		return receiverPostal;
	}
	public void setReceiverPostal(String receiverPostal) {
		this.receiverPostal = receiverPostal;
	}
	public String getReceiverCountry() {
		return receiverCountry;
	}
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}
	public ChargeItem[] getChargeItems() {
		return chargeItems;
	}
	public void setChargeItems(ChargeItem[] chargeItems) {
		this.chargeItems = chargeItems;
	}
	public double getGST() {
		return GST;
	}
	public void setGST(double gST) {
		GST = gST;
	}
	public double getHST() {
		return HST;
	}
	public void setHST(double hST) {
		HST = hST;
	}
}
