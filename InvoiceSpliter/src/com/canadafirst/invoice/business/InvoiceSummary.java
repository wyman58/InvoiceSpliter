package com.canadafirst.invoice.business;

import java.text.DecimalFormat;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

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
	
	public PdfPCell createInvoiceSummaryHeader(){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.BOTTOM);
		cell.addElement(new Phrase("Invoice Summary",new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		return cell;
	}
	
	public PdfPCell createInvoiceSummarySubtotalTableCell(double[] amount, double [] tax){
		
		PdfPCell cell = new PdfPCell();
		PdfPTable table = new PdfPTable(2);
		DecimalFormat numFormat = new DecimalFormat("'$'00.###");
		double subTotal = 0;
		int i = 0;
		
		for(i=0; i<=amount.length; i++){
			switch(i){
			case 0:
				if(amount[i] != 0){
					PdfPCell cell0 = new PdfPCell();
					cell0.setBorder(Rectangle.NO_BORDER);
					cell0.addElement(new Phrase("Freight Charges",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					table.addCell(cell0);
					cell0 = new PdfPCell();
					cell0.setBorder(Rectangle.NO_BORDER);
					cell0.addElement(new Phrase(numFormat.format(amount[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					subTotal += amount[i];
					table.addCell(cell0);
				}
				break;				
			case 1:
				if(amount[i] != 0){
					PdfPCell cell1 = new PdfPCell();
					cell1.setBorder(Rectangle.NO_BORDER);
					cell1.addElement(new Phrase("Fuel Surcharge",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));				
					table.addCell(cell1);
					cell1 = new PdfPCell();
					cell1.setBorder(Rectangle.NO_BORDER);
					cell1.addElement(new Phrase(numFormat.format(amount[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					subTotal += amount[i];
					table.addCell(cell1);
				}	
				break;
			case 2:
				if(amount[i] != 0){
					PdfPCell cell2 = new PdfPCell();
					cell2.setBorder(Rectangle.NO_BORDER);
					cell2.addElement(new Phrase("Residential Surcharge",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					table.addCell(cell2);
					cell2 = new PdfPCell();
					cell2.setBorder(Rectangle.NO_BORDER);
					cell2.addElement(new Phrase(numFormat.format(amount[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					subTotal += amount[i];
					table.addCell(cell2);
				}
				break;
			case 3:
				if (amount[i] != 0){
					PdfPCell cell3 = new PdfPCell();
					cell3.setBorder(Rectangle.NO_BORDER);
					cell3.addElement(new Phrase("Adjusted Charge",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					table.addCell(cell3);
					cell3 = new PdfPCell();
					cell3.setBorder(Rectangle.NO_BORDER);
					cell3.addElement(new Phrase(numFormat.format(amount[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
					subTotal += amount[i];
					table.addCell(cell3);
				}
				break;
			case 4:
				if (subTotal != 0){
					PdfPCell cell4 = new PdfPCell();
					cell4.setBorder(Rectangle.TOP);	
					cell4.addElement(new Phrase("Subtotal",new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
					table.addCell(cell4);
					cell4 = new PdfPCell();
					cell4.setBorder(Rectangle.TOP);
					cell4.addElement(new Phrase(numFormat.format(subTotal), new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
					table.addCell(cell4);
				}
				break;		
			}
		}
		
		for(i=0; i<=tax.length; i++){
			switch(i){
			case 0:
				PdfPCell cell0 = new PdfPCell();
				cell0.setBorder(Rectangle.NO_BORDER);
				cell0.addElement(new Phrase("GST",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
				table.addCell(cell0);
				cell0 = new PdfPCell();
				cell0.setBorder(Rectangle.NO_BORDER);
				cell0.addElement(new Phrase(numFormat.format(tax[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
				subTotal += tax[i];
				table.addCell(cell0);
				break;				
			case 1:
				PdfPCell cell1 = new PdfPCell();
				cell1.setBorder(Rectangle.NO_BORDER);
				cell1.addElement(new Phrase("HST",new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));				
				table.addCell(cell1);
				cell1 = new PdfPCell();
				cell1.setBorder(Rectangle.NO_BORDER);
				cell1.addElement(new Phrase(numFormat.format(tax[i]), new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL)));
				subTotal += tax[i];
				table.addCell(cell1);	
				break;
			case 2:
				PdfPCell cell2 = new PdfPCell();
				cell2.setBorder(Rectangle.TOP);	
				cell2.addElement(new Phrase("Total",new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
				table.addCell(cell2);
				cell2 = new PdfPCell();
				cell2.setBorder(Rectangle.TOP);
				cell2.addElement(new Phrase(numFormat.format(subTotal), new Font(Font.FontFamily.HELVETICA,9,Font.BOLD)));
				table.addCell(cell2);
				break;
			}
		}
		cell.addElement(table);
		return cell;
	}
}
