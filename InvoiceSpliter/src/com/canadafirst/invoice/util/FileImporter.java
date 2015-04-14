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
	
	public void processFile() throws Exception{
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			if (this.validateFileExtension(file.getAbsolutePath())){
				Reader in = null;
				in = new FileReader(file.getAbsolutePath());
				Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
				for (CSVRecord record : records) {
					StringBuffer sql = new StringBuffer("insert into transaction values(");
					if (record.get(15) != ""){
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
				JDBC.closeConnection();
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
	
	private void insertRecord(String sql) throws SQLException{
		Connection conn = JDBC.getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);		
		stmt.close();
	}
	
	private String formatDate(String inputDate){
		SimpleDateFormat formatterDD = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterYYYY = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String outputDate = "";
		try{
			date = formatterDD.parse(inputDate);
			outputDate = formatterYYYY.format(date);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return outputDate;
	}
}
