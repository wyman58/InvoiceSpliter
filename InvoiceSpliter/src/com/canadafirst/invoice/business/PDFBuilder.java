package com.canadafirst.invoice.business;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder {
	
	
	private void createPDF(String fileName){
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			createSummarySection(document);
			createDetailSection(document);
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void createSummarySection(Document document){
		
		//document.add(createCompanyHeaderTable(invoiceDate, customer, group));
		//document.add(createAddressTable(customer));
		//tear off image
		
		
		
	}
	
	private PdfPTable createCompanyHeaderTable(Date invoiceDate, Customer customer, Group group) throws Exception{
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new float[] {2f,1f});
		
		PdfPCell companyNameCell = new PdfPCell();
		companyNameCell.setBorder(Rectangle.NO_BORDER);
		companyNameCell.addElement(new Paragraph("Canada First",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
		companyNameCell.addElement(new Paragraph("Logistical Consulting Inc.",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.addElement(new Paragraph("Serice Invoice",new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)));
		cell2.addElement(new Paragraph("Invoice Date      " + "Jan 3,2015",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell2.addElement(new Paragraph("Invoice Number   " + "tom150103",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		cell2.addElement(new Paragraph("Shipper Number " + "cf.tom",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
		
		table.addCell(companyNameCell);
		table.addCell(cell2);
		
		return table;
	}
	
	private PdfPTable createAddressTable(Customer customer){
		PdfPTable table = new PdfPTable(1);
		
		
		return table;
	}
	
	
	private void createDetailSection(Document document){
		//document.add(table);
	}
	
	
}
