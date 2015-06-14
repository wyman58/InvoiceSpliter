package com.canadafirst.invoice.business;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class Group {
	private String groupName;
	private String HST;
	private String address1;
	private String address2;
	private String suburb;
	private String province;
	private String postcode;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getHST() {
		return HST;
	}
	public void setHST(String hST) {
		HST = hST;
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
	
	public PdfPCell createGroupInfoCell(Group group, boolean hasHST){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Phrase("Canada First",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		cell.addElement(new Phrase("Logistical Consulting Inc.",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		if (hasHST){
			cell.addElement(new Phrase(" "));
			cell.addElement(new Phrase("HST # " + group.getHST(),new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
		}
		return cell;
	}
	
	public PdfPCell createGroupAddressCell(Group group){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Phrase("Please make cheque payable to CFL Consulting",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell.addElement(new Phrase("CFL Consulting",new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(group.getAddress1() + group.getAddress2(),new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(group.getSuburb() + "," + group.getProvince(),new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell.addElement(new Phrase(group.getPostcode(),new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		return cell;
	}
}
