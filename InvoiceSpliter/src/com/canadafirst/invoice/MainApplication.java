package com.canadafirst.invoice;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;

import com.canadafirst.invoice.business.ChargeItem;
import com.canadafirst.invoice.business.Customer;
import com.canadafirst.invoice.business.Group;
import com.canadafirst.invoice.business.InvoiceSummary;
import com.canadafirst.invoice.business.Transaction;
import com.canadafirst.invoice.dao.JDBC;
import com.canadafirst.invoice.util.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MainApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("CanadaFirst Invoice Processor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		
		JButton importUSBtn = new JButton("Import US");
		importUSBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FileImporter fi = new FileImporter();
				try{
					String acc = new String("E47R07");
					fi.processFile(acc);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton importCABtn = new JButton("Import CA");
		importCABtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FileImporter fi = new FileImporter();
				try{
					String acc = new String("ER4855");
					fi.processFile(acc);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton genInvoiceBtn = new JButton("Generate Invoice");
		genInvoiceBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				try{
					Group group = createGroup();
					
					generateInvoice(group);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		frame.getContentPane().add(importUSBtn);
		frame.getContentPane().add(importCABtn);
		frame.getContentPane().add(genInvoiceBtn);
	}
	
	private void generateInvoice(Group group) throws Exception{
		
		PdfPCell groupInfoCellHST = group.createGroupInfoCell(group, true);
		PdfPCell groupInfoCell = group.createGroupInfoCell(group, false);
		PdfPCell groupAddressCell = group.createGroupAddressCell(group);
		
		Statement stmt = JDBC.getConnection().createStatement();
		StringBuffer sql = new StringBuffer("select * from transaction where ");
		sql.append("TXINVD = " + '"' + "2015-01-24" + '"');
		sql.append(" and TXLDNM like " + '"' + "%E47R07%" + '"');
		sql.append(" order by TXSHR1,TXTNDT,TXLDNM;");
		
		ResultSet rs = stmt.executeQuery(sql.toString());
		String shipmentRef = null;
		String trackingNumber = null;
		Customer customer = null;
		PdfPCell customerAddressCellWithBillingAddress = null;
		PdfPCell customerAddressCell = null;
		PdfPCell serviceInvoiceCell = null;
		PdfPCell transactionHeadingCell = null;
		PdfPCell pickupDateCell = null;
		PdfPCell transactionBasicInfoCell = null;
		PdfPCell receiveAddressCell = null;
		PdfPCell transactionDetailCell = null;
		double totalFreight = 0;
		double totalFuelSurcharge = 0;
		double totalResidentialSurcharge = 0;
		Document document = null;
		PdfPCell transactionArray[] = new PdfPCell [100];
		String transactionDesc[] = new String [10];
		double transactionAmount[] =  new double [10];
		int j = 0;
		
		while(rs.next()){
			
			if (shipmentRef.length() != 0 && shipmentRef != rs.getString("TXSHR1")){
				document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream("C:/testdata/outputPDF/" + shipmentRef + ".pdf"));
				document.open();
				shipmentRef = rs.getString("TXSHR1");
				
				customer = createCustomer(shipmentRef);
				customerAddressCellWithBillingAddress = customer.createCustomerAddressCell(customer, true);
				customerAddressCell = customer.createCustomerAddressCell(customer, false);
				serviceInvoiceCell = customer.createServiceInvoiceCell(rs.getDate("TXINVD"), customer);
				transactionHeadingCell = customer.createTransactionHeaderCell(this.convertToCountry(rs.getString("TXLDNM")));
				
				//process Summary
				groupInfoCellHST
				serviceInvoiceCell
				customerAddressCellWithBillingAddress
				//createInvoiceSummaryHeader
				//createInvoiceSummarysubTotalTableCell
				groupInfoCell
				serviceInvoiceCell
				customerAddressCell
				groupAddressCell
				
				//process Transaction
				//loop transaction cell array
				for (int i = 0 ; i < transactionArray.length; i++){					
					if (i % 3 == 0){
						document.newPage();
						//populate header
						groupInfoCell
						serviceInvoiceCell
						transactionHeadingCell
					}
					//populate transaction cell
					
				}
				
				
				document.close();
				//reset
				transactionArray = null;
				totalFreight = 0;
				totalFuelSurcharge = 0;
				totalResidentialSurcharge = 0;
			}
			
		
			ChargeItem chargeItem = new ChargeItem();
			chargeItem.setItemDescription(rs.getString("TXCHGD"));
			chargeItem.setPublishedCharge(rs.getDouble("TXICAM") + rs.getDouble("TXNTAM"));
			double rate = getDiscountRate(shipmentRef, convertToCountry(rs.getString("TXLDNM")), rs.getString("TXCHGD"), rs.getString("TXCTTY"), rs.getString("TXZNCD"));
			chargeItem.setIncentiveCredit(chargeItem.getPublishedCharge() * rate * (-1));
			double billedCharge = chargeItem.getPublishedCharge() + chargeItem.getIncentiveCredit();
			chargeItem.setBilledCharge(billedCharge);
			
			transactionDesc[j] = chargeItem.getItemDescription(); 
			transactionAmount[j] = chargeItem.getPublishedCharge();
			j += 1;

			//sum up for the summary page
			if (rs.getString("TXCCCD").equalsIgnoreCase("FRT")){		
				totalFreight += chargeItem.getBilledCharge();
			}else if(rs.getString("TXCCCD").equalsIgnoreCase("FSC")){
				totalFuelSurcharge += chargeItem.getBilledCharge();
			}else if(rs.getString("TXCHGD").equalsIgnoreCase("Residential Surcharge")){
			    totalResidentialSurcharge += chargeItem.getBilledCharge();
			}
			
			if (trackingNumber.length() != 0 && trackingNumber != rs.getString("TXLDNM")){
				Transaction transaction = new Transaction();
				pickupDateCell = transaction.createPickupDateTableCell(rs.getDate("TXTNDT"), sequence);
				transactionBasicInfoCell = transaction.createTransactionBasicInfoTableCell(rs.getString("TXLDNM"), rs.getString("TXZNCD"), rs.getInt("TXPKQT"), rs.getInt("TXENWT"), rs.getInt("TXBLWT"));
				receiveAddressCell = transaction.createReceiverAddressCell(rs.getString("TXRVNM"), rs.getString("TXRCNM"), rs.getString("TXRAL1"), rs.getString("TXRAL2"), rs.getString("TXRVCT"), rs.getString("TXRVST"), rs.getString("TXRVPC"), rs.getString("TXRCTC"));
				transactionDetailCell = transaction.createTransactionDetailTableCell(rate, transactionDesc, transactionAmount, 0, 0);
				j = 0;
				//create transaction array cell here
				PdfPTable table = new PdfPTable(2);
				table.addCell(pickupDateCell);
				table.addCell(new PdfPCell());
				table.addCell(transactionBasicInfoCell);
				table.addCell(transactionDetailCell);
				table.addCell(receiveAddressCell);
			}
			

			
		
		}
		stmt.close();
		JDBC.closeConnection();
	}
	
	private Group createGroup() throws Exception{
		
		Statement stmt = JDBC.getConnection().createStatement();
		StringBuffer sql = new StringBuffer("select * from group where ");
		sql.append("GRID = " + '"' + "CFLC" + '"' + ';');
		Group group = null;
		ResultSet rs = stmt.executeQuery(sql.toString());
		if(rs.next()){
		  group = new Group();
		  group.setGroupName(rs.getString("GRNAME"));
		  group.setHST(rs.getString("GRHST"));
		  group.setAddress1(rs.getString("GRADDR1"));
		  group.setAddress2(rs.getString("GRADDR2"));
		  group.setPostcode(rs.getString("GRPSCD"));
		  group.setProvince(rs.getString("GRPROV"));
		  group.setSuburb(rs.getString("GRSUBR"));
		}
		stmt.close();
		JDBC.closeConnection();
		return group;
	}
	
	private Customer createCustomer(String customerName) throws Exception{
		
		Statement stmt = JDBC.getConnection().createStatement();
		StringBuffer sql = new StringBuffer("select * from customer where ");
		sql.append("CMTRNM = " + '"' + customerName + '"' + ';');
		Customer customer = null;
		ResultSet rs = stmt.executeQuery(sql.toString());
		if (rs.next()){
		  customer = new Customer();
		  customer.setAbbreviation(rs.getString("CMABBR"));
		  customer.setAddress1(rs.getString("CMBAD1"));
		  customer.setAddress2(rs.getString("CMBAD2"));
		  customer.setLegalName(rs.getString("CMLGNM"));
		  customer.setPostcode(rs.getString("CMPTCD"));
		  customer.setProvince(rs.getString("CMPROV"));
		  customer.setSuburb(rs.getString("CMSUBR"));
		  customer.setTradeName(rs.getString("CMTRNM"));
		}
		stmt.close();
		JDBC.closeConnection();
		return customer;
	}
	
	private double getDiscountRate(String customerName, String country, String chargeDesc, String containerCode, String zone) throws Exception{
		
		double rate = 0;
		Statement stmt = JDBC.getConnection().createStatement();
		StringBuffer sql = new StringBuffer("select CTRATE from contract where ");
		sql.append("CTCUST = " + '"' + customerName + '"');
		sql.append(" and CTCTCD = " + '"' + country + '"');
		sql.append(" and CTSVDS = " + '"' + chargeDesc + '"');
		sql.append(" and CTCNCD = " + '"' + containerCode + '"');
		sql.append(" and CTZONE = " + '"' + zone + '"' + ';');
		
		ResultSet rs = stmt.executeQuery(sql.toString());
		if (rs.next()){
			rate = rs.getDouble("CTRATE");
		}
		
		return rate;
	}
	
	private String convertToCountry(String trackingNumber){
		String country = null;
		if (trackingNumber.indexOf("E47R07") > -1){
			country = "US";
		}else if (trackingNumber.indexOf("ER4855") > -1){
			country = "CA";
		}
		
		return country;
			
		
	}
}
