package com.canadafirst.invoice.business;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

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
	
public PdfPCell createTransactionBasicInfoTableCell(String track, String zone, int packageNumber, int enteredWeight, int auditedWeight){
		
		PdfPCell cell = new PdfPCell();
		PdfPTable table = new PdfPTable(2);
				
		PdfPCell cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase("Tracking #:",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell0);
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase((track), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell0);
		
		PdfPCell cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(new Phrase("Zone:",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));				
		table.addCell(cell1);
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(new Phrase(zone, new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.addElement(new Phrase("Packages:",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell2);
		cell2 = new PdfPCell();
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.addElement(new Phrase(new Integer(packageNumber).toString(), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell();
		cell3.setBorder(Rectangle.NO_BORDER);
		cell3.addElement(new Phrase("Entered Weight",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell3);
		cell3 = new PdfPCell();
		cell3.setBorder(Rectangle.NO_BORDER);
		cell3.addElement(new Phrase(new Integer(enteredWeight).toString(), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		table.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell();
		cell4.setBorder(Rectangle.NO_BORDER);	
		cell4.addElement(new Phrase("Audited Weight",new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
		table.addCell(cell4);
		cell4 = new PdfPCell();
		cell4.addElement(new Phrase(new Integer(auditedWeight).toString(), new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
		table.addCell(cell4);
		
		cell.addElement(table);
		return cell;
	}

	public PdfPCell createReceiverAddressCell(String receiverName, String receiverCompany, String receiverAddress1, String receiverAddress2, String city, String state, String postcode, String country){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Phrase("Receiver",new Font(Font.FontFamily.HELVETICA,10,Font.UNDERLINE)));
		cell.addElement(new Phrase(receiverName,new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell.addElement(new Phrase(receiverCompany,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(receiverAddress1 + receiverAddress2,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(city + ' ' + state + ' ' + postcode,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(country,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		return cell;
	}
	
	
	
	public PdfPCell createPickupDateTableCell(Date pickupDate, int sequence){
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		PdfPCell cell = new PdfPCell();
		PdfPTable table = new PdfPTable(3);
		cell.setBorder(Rectangle.TOP);
		cell.setBorder(Rectangle.BOTTOM);
		
		PdfPCell cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase("Pickup Date",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		PdfPCell cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(new Phrase(format.format(pickupDate),new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.addElement(new Phrase(new Integer(sequence).toString(),new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell2);
		
		
		cell.addElement(table);
		return cell;
	}
	
	public PdfPCell createTransactionDetailTableCell(double discount, String itemDesc[],double item[], double HST, double GST){
		PdfPCell cell = new PdfPCell();
		PdfPTable table = new PdfPTable(4);
		DecimalFormat numFormat = new DecimalFormat("'$'00.##");
		DecimalFormat percentFormat = new DecimalFormat("#%");
		
		PdfPCell cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.BOTTOM);
		cell0.addElement(new Phrase("Description",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.BOTTOM);
		cell0.addElement(new Phrase("Published Charge",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.BOTTOM);
		cell0.addElement(new Phrase("Incentive Credit",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.BOTTOM);
		cell0.addElement(new Phrase("Billed Charge",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		double subtotal = 0;
		double total = 0;
		
		for (int i = 0 ; i < itemDesc.length ; i++){
			double incentiveCredit = (-1)*item[i]*discount;
			double billedCharge = item[i] + incentiveCredit;
			
			cell0 = new PdfPCell();
			cell0.setBorder(Rectangle.NO_BORDER);
			cell0.addElement(new Phrase(itemDesc[i],new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
			table.addCell(cell0);
			
			cell0 = new PdfPCell();
			cell0.setBorder(Rectangle.NO_BORDER);
			cell0.addElement(new Phrase(numFormat.format(item[i]),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
			table.addCell(cell0);
			
			cell0 = new PdfPCell();
			cell0.setBorder(Rectangle.NO_BORDER);
			cell0.addElement(new Phrase(numFormat.format(incentiveCredit),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
			table.addCell(cell0);
			
			cell0 = new PdfPCell();
			cell0.setBorder(Rectangle.NO_BORDER);
			cell0.addElement(new Phrase(numFormat.format(billedCharge),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
			table.addCell(cell0);
			subtotal = subtotal + billedCharge; 
		}
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.TOP);
		cell0.addElement(new Phrase("Subtotal",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.TOP);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.TOP);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.TOP);
		cell0.addElement(new Phrase(numFormat.format(subtotal),new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase("GST",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(percentFormat.format(GST),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(numFormat.format(subtotal * GST),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase("HST",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(percentFormat.format(HST),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(numFormat.format(subtotal * HST),new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase("Total",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(" ",new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL)));
		table.addCell(cell0);
		
		total = subtotal * (1 + GST) * (1 + HST);
		cell0 = new PdfPCell();
		cell0.setBorder(Rectangle.NO_BORDER);
		cell0.addElement(new Phrase(numFormat.format(total),new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		table.addCell(cell0);
		
		cell.addElement(table);
		return cell;
		
	}
}
