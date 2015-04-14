package com.canadafirst.invoice;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import com.canadafirst.invoice.util.*;

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
		frame.getContentPane().setLayout(null);
		
		JButton importBtn = new JButton("Import");
		importBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FileImporter fi = new FileImporter();
				try{
					fi.processFile();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		importBtn.setBounds(160,102,121,43);
		frame.getContentPane().add(importBtn);
	}

}
