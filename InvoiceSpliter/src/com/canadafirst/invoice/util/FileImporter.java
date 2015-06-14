package com.canadafirst.invoice.util;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import org.apache.commons.csv.*;

import com.canadafirst.invoice.dao.*;

public class FileImporter {
	
	JFileChooser fileChooser = new JFileChooser();
    
	public void processFile(String acc) throws Exception{
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			if (this.validateFileExtension(file.getAbsolutePath()) && this.validateFileName(file.getName(), acc)){
				Reader in = null;
				in = new FileReader(file.getAbsolutePath());
				Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
				boolean controlFileUpdated = false;
				String invoiceDateString = null;
				
				for (CSVRecord record : records) {
					if (record.get(15).length() != 0){
						
						if (controlFileUpdated == false){
							invoiceDateString = this.formatDate(record.get(4));
							if (this.isFileUploaded(invoiceDateString, acc)){
								System.out.println("File with date:" + invoiceDateString + ", account:" +acc+ " was already uploaded");
								break;
							}
							StringBuffer sql = new StringBuffer("insert into upload values(");
							sql.append('"' + invoiceDateString + '"' + ',');
							sql.append('"' + acc +'"' + ',');
							sql.append('"' + "R" + '"' + ");");
							this.insertRecord(sql.toString());
							controlFileUpdated = true;
						}
						
						StringBuffer sql = new StringBuffer("insert into transaction values(");
						sql.append('"' + this.formatDate(record.get(4))+'"' + ',');
						sql.append('"' + record.get(15)+'"' + ',');
						sql.append('"' + this.formatDate(record.get(11))+'"' + ',');
						sql.append('"' + record.get(13)+'"' + ',');
						sql.append('"' + record.get(18)+'"' + ',');
						sql.append('"' + record.get(20)+'"' + ',');
						sql.append('"' + record.get(26)+'"' + ',');
						sql.append('"' + record.get(27)+'"' + ',');
						sql.append('"' + record.get(28)+'"' + ',');
						sql.append('"' + record.get(29)+'"' + ',');
						sql.append('"' +record.get(30)+'"' + ',');
						sql.append('"' +record.get(33)+'"' + ',');
						sql.append('"' +record.get(43)+'"' + ',');
						sql.append('"' +record.get(44)+'"' + ',');
						sql.append('"' +record.get(45)+'"' + ',');
						sql.append('"' +record.get(47)+'"' + ',');
						sql.append('"' +record.get(48)+'"' + ',');
						sql.append('"' +record.get(51)+'"' + ',');
						sql.append('"' +record.get(52)+'"' + ',');
						sql.append('"' +record.get(66)+'"' + ',');
						sql.append('"' +record.get(67)+'"' + ',');
						sql.append('"' +record.get(68)+'"' + ',');
						sql.append('"' +record.get(69)+'"' + ',');
						sql.append('"' +record.get(70)+'"' + ',');
						sql.append('"' +record.get(71)+'"' + ',');
						sql.append('"' +record.get(72)+'"' + ',');
						sql.append('"' +record.get(73)+'"' + ',');
						sql.append('"' +record.get(74)+'"' + ',');
						sql.append('"' +record.get(75)+'"' + ',');
						sql.append('"' +record.get(76)+'"' + ',');
						sql.append('"' +record.get(77)+'"' + ',');
						sql.append('"' +record.get(78)+'"' + ',');
						sql.append('"' +record.get(79)+'"' + ',');
						sql.append('"' +record.get(80)+'"' + ',');
						sql.append('"' +record.get(81)+'"' + ',');
						sql.append('"' +record.get(82)+'"'+");");
						this.insertRecord(sql.toString());
					}
				}
				if (controlFileUpdated == true){
					StringBuffer sql = new StringBuffer("update upload set status=" + "'" + "C" + "'" +" where ");
					sql.append("invoiceDate = " + '"' + invoiceDateString +'"' + " and " );
					sql.append("account =" + '"' + acc + '"' + ';');
					this.insertRecord(sql.toString());
					System.out.println("File with date:" + invoiceDateString + ", account:" +acc+ " was uploaded successfully");
				}
				JDBC.closeConnection();
			}else{
				System.out.println("Invalid File");
			}
		}else{
			System.out.println("No file is selected");
		}
		
	}
	
	private boolean validateFileExtension(String fileName){
		boolean isCSV = false;
		if (fileName.toLowerCase().endsWith(".csv")){
			isCSV = true;
		}
		return isCSV;
	}
	
	private boolean validateFileName(String fileName, String account){
		boolean isValid = false;
		String temp = fileName.toUpperCase().substring(6,12);
		if (temp.equals(account)){
			isValid = true;
		}
		return isValid;
	}
	
	private void insertRecord(String sql) throws SQLException{
		
		Statement stmt = JDBC.getConnection().createStatement();
		stmt.executeUpdate(sql);		
		stmt.close();
	}
	
	private boolean isFileUploaded(String invoiceDate, String account) throws SQLException{
		boolean isFileUploaded = false;
		Statement stmt = JDBC.getConnection().createStatement();
		StringBuffer sql = new StringBuffer("select * from upload where ");
		sql.append("invoiceDate = " + '"' + invoiceDate + '"' + " and " );
		sql.append("account =" + '"' + account + '"' + ';');
		ResultSet rs = stmt.executeQuery(sql.toString());
		isFileUploaded = rs.next();
		stmt.close();
		return isFileUploaded;
	}
	
	private String formatDate(String inputDate){
		SimpleDateFormat formatterDD1 = new SimpleDateFormat("d/MM/yyyy");
		SimpleDateFormat formatterDD2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatterYYYY = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String outputDate = "";
		try{
			date = formatterDD1.parse(inputDate);
		}catch(Exception e1){
			try{
				date = formatterDD2.parse(inputDate);
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		outputDate = formatterYYYY.format(date);
		return outputDate;
	}
}
