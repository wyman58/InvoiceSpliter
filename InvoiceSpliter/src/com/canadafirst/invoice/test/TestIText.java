package com.canadafirst.invoice.test;

import com.canadafirst.invoice.business.Transaction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class TestIText {

	public static void main(String[] args) {
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, new FileOutputStream("C:/testdata/outputPDF/testPDF.pdf"));
			document.open();
		
			//document.add(new Paragraph("To create a new "));
			//document.setPageSize(PageSize.A4);
			//document.newPage();
			//document.add(new Paragraph("To create a new doc page 2"));
			//Table table = new Table(2,2);
			PdfPTable table = new PdfPTable(1);
			//table.setWidths(new float[] {2f,1f});
			String[] itemDesc = {"Standard","Fuel"};
			double[] item = {592.8,53.35};
			PdfPCell cell = new Transaction().createTransactionDetailTableCell(0.65, itemDesc , item, 0.15, 0.1);
			//PdfPCell cell2 = new PdfPCell();
			//cell2.setBorder(Rectangle.BOTTOM);
			//cell2.addElement(new Paragraph("Serice Invoice",new Font(Font.FontFamily.HELVETICA,11,Font.BOLD)));
			//cell2.addElement(new Paragraph("Invoice Date      " + "Jan 3,2015",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
			//cell2.addElement(new Paragraph("Invoice Number   " + "tom150103",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
			//cell2.addElement(new Paragraph("Shipper Number " + "cf.tom",new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL)));
			
			
			//PdfPTable nestedTable = new PdfPTable(2);
			
			//PdfPCell cell3 = new PdfPCell();
			//cell3.addElement(new Paragraph("P1"));
			//cell3.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell3);
			
			//PdfPCell cell4 = new PdfPCell();
			//cell4.addElement(new Paragraph("P1"));
			//cell4.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell4);
			
			//PdfPCell cell5 = new PdfPCell();
			//cell5.addElement(new Paragraph("P1"));
			//cell5.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell5);
			
			//PdfPCell cell6 = new PdfPCell();
			//cell6.addElement(new Paragraph("P1"));
			//cell6.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell6);
			
			//PdfPCell cell7 = new PdfPCell();
			//cell7.addElement(new Paragraph("P1"));
			//cell7.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell7);
			
			//PdfPCell cell8 = new PdfPCell();
			//cell8.addElement(new Paragraph("P1"));
			//cell8.setBorder(Rectangle.NO_BORDER);
			//nestedTable.addCell(cell8);
			
			//cell4.addElement(nestedTable);
			//cell4.setBorder(Rectangle.NO_BORDER);
			
			//table.addCell(cell1);
			table.addCell(cell);
			
			//table.addCell(new PdfPCell());
			
			//table.addCell(cell4);
			
			document.add(table);
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
