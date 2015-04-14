package com.canadafirst.invoice.business;

public class InvoiceSummary {
	private double freightCharge;
	private double fuelSurcharge;
	private double residentialSurcharge;
	private double adjustedCharge;
	public double getFreightCharge() {
		return freightCharge;
	}
	public void setFreightCharge(double freightCharge) {
		this.freightCharge = freightCharge;
	}
	public double getFuelSurcharge() {
		return fuelSurcharge;
	}
	public void setFuelSurcharge(double fuelSurcharge) {
		this.fuelSurcharge = fuelSurcharge;
	}
	public double getResidentialSurcharge() {
		return residentialSurcharge;
	}
	public void setResidentialSurcharge(double residentialSurcharge) {
		this.residentialSurcharge = residentialSurcharge;
	}
	public double getAdjustedCharge() {
		return adjustedCharge;
	}
	public void setAdjustedCharge(double adjustedCharge) {
		this.adjustedCharge = adjustedCharge;
	}
}
