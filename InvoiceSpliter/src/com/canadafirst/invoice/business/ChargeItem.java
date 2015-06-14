package com.canadafirst.invoice.business;

public class ChargeItem {
	private String itemDescription;
	private double publishedCharge;
	private double incentiveCredit;
	private double billedCharge;
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getPublishedCharge() {
		return publishedCharge;
	}
	public void setPublishedCharge(double publishedCharge) {
		this.publishedCharge = publishedCharge;
	}
	public double getIncentiveCredit() {
		return incentiveCredit;
	}
	public void setIncentiveCredit(double incentiveCredit) {
		this.incentiveCredit = incentiveCredit;
	}
	public double getBilledCharge() {
		return billedCharge;
	}
	public void setBilledCharge(double billedCharge) {
		this.billedCharge = billedCharge;
	}
	public double getContractRate(){
		double rate = 0;
		
		return rate;
	}
}
