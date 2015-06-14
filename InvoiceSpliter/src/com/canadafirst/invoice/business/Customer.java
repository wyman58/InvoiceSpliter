package com.canadafirst.invoice.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class Customer {
	private String tradeName;
	private String legalName;
	private String abbreviation;
	private String address1;
	private String address2;
	private String suburb;
	private String province;
	private String postcode;
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public PdfPCell createServiceInvoiceCell(Date invoiceDate, Customer customer){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Phrase("Serice Invoice", new Font(Font.FontFamily.HELVETICA,11,Font.BOLD)));
		
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cell11 = new PdfPCell();
		cell11.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Invoice Date",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell12 = new PdfPCell();
		cell12.setBorder(Rectangle.NO_BORDER);
		String pattern = "MMMMM d,yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		cell.addElement(new Paragraph(format.format(invoiceDate),new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell21 = new PdfPCell();
		cell21.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Invoice Number",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell22 = new PdfPCell();
		cell22.setBorder(Rectangle.NO_BORDER);
		pattern = "yyMMdd";
		format = new SimpleDateFormat(pattern);
		cell.addElement(new Paragraph(customer.getAbbreviation() + format.format(invoiceDate),new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell31 = new PdfPCell();
		cell31.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Shipper Number",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell32 = new PdfPCell();
		cell32.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("cf." + customer.getAbbreviation(),new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		table.addCell(cell11);
		table.addCell(cell12);
		table.addCell(cell21);
		table.addCell(cell22);
		table.addCell(cell31);
		table.addCell(cell32);
		
		cell.addElement(table);
		return cell;
	}
	
	public PdfPCell createCustomerAddressCell(Customer customer,boolean ba){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		if (ba){
			cell.addElement(new Phrase("Billing Address", new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		}
		if (customer.getLegalName().length() > 0){
			cell.addElement(new Phrase(customer.getLegalName(), new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		}
		cell.addElement(new Phrase(customer.getTradeName(), new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell.addElement(new Phrase(customer.getAddress1() + " " + customer.getAddress2(), new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell.addElement(new Phrase(customer.getSuburb() + "." + customer.getProvince(),new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell.addElement(new Phrase(customer.getPostcode(),new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		return cell;
	}
	
	public PdfPCell createTransactionHeaderCell(String country){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Phrase(country + " Shipments",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		return cell;
	}
}
