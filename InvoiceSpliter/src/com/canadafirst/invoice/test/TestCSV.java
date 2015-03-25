package com.canadafirst.invoice.test;

import java.io.*;
import org.apache.commons.csv.*;


public class TestCSV {

	public static void main(String[] args) {
		Reader in = null;
		try{
			in = new FileReader("c:/testdata/000000E47R07015.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				String lastName = record.get(0);
				String firstName = record.get(3);
				System.out.print(lastName + firstName);
			}
		}catch(IOException io){
		
		}finally{
		}
	}

}
