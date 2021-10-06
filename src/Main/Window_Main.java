package Main;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Ap1.Ap1;
import Ap2.Ap2;
import Ap3.Ap3;
import Ap4.Ap4;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window_Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Main frame = new Window_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_1;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JButton rdbtnNewRadioButton;
	private JButton rdbtnNewRadioButton_1;
	private JButton rdbtnNewRadioButton_2;
	private JButton rdbtnNewRadioButton_3;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JTextField textField_2;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;

	/**
	 * Create the frame.
	 */
	public Window_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Input Text");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		textField.setText("in.txt");
		
		JLabel lblNewLabel = new JLabel("Output Text");
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("out.txt");
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1);
		
		rdbtnNewRadioButton = new JButton("Ap1");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String in = textField.getText();
				String out = textField_1.getText();
				Ap1 a1 = new Ap1(in, out);
				a1.buclePrincipal();
				BufferedReader br = new BufferedReader(new FileReader(new File("out.txt")));
				String output = "", linea = "";
				while((linea = br.readLine())!=null)
					output = output + "\n" + linea;
				textField_2.setText(output);
				} catch(Exception ex) {
					//
				}
			}
		});
		splitPane_1.setLeftComponent(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JButton("Ap2");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String in = textField.getText();
				String out = textField_1.getText();
				Ap2 a2 = new Ap2(in, out);
				a2.buclePrincipal();
				BufferedReader br = new BufferedReader(new FileReader(new File("out.txt")));
				String output = "", linea = "";
				while((linea = br.readLine())!=null)
					output = output + "\n" + linea;
				textField_2.setText(output);
				} catch(Exception ex) {
					//
				}
			}
		});
		splitPane_1.setRightComponent(rdbtnNewRadioButton_1);
		
		splitPane = new JSplitPane();
		panel_1.add(splitPane);
		
		rdbtnNewRadioButton_2 = new JButton("Ap3");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String in = textField.getText();
				String out = textField_1.getText();
				Ap3 a3 = new Ap3(in, out);
				a3.buclePrincipal();
				BufferedReader br = new BufferedReader(new FileReader(new File("out.txt")));
				String output = "", linea = "";
				while((linea = br.readLine())!=null)
					output = output + "\n" + linea;
				textField_2.setText(output);
				} catch(Exception ex) {
					//
				}
			}
		});
		splitPane.setLeftComponent(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JButton("Ap4");
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String in = textField.getText();
				String out = textField_1.getText();
				Ap4 a4 = new Ap4(in, out);
				a4.buclePrincipal();
				
			    BufferedReader br = new BufferedReader(new FileReader(new File("out.txt")));
				String output = "", linea = "";
				while((linea = br.readLine())!=null)
					output = output + "\n" + linea;
				textField_2.setText(output);
				} catch(Exception ex) {
					//
				}
			}
		});
		splitPane.setRightComponent(rdbtnNewRadioButton_3);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		lblNewLabel_1 = new JLabel("Output: ");
		panel_2.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		//textField_2.setBounds(1000,1000,1000,1000);
		panel_2.add(textField_2);
		textField_2.setColumns(45);
		
//		try {
//			
//			BufferedImage image1 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			image1 = new BufferedImage(75,75, image1.getType());
//			JLabel picLabel1 = new JLabel(new ImageIcon(image1));
//			//getContentPane().add(picLabel1);
//			panel_4.add(picLabel1);
//			
//			BufferedImage image2 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			image2 = new BufferedImage(75,75, image2.getType());
//			JLabel picLabel2 = new JLabel(new ImageIcon(image2));
//			//getContentPane().add(picLabel2);
//			panel_5.add(picLabel2);
//			
//			BufferedImage image3 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			image3 = new BufferedImage(75,75, image3.getType());
//			JLabel picLabel3 = new JLabel(new ImageIcon(image3));
//			//getContentPane().add(picLabel3);
//			panel_6.add(picLabel3);
//			
//			BufferedImage image4 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			image4 = new BufferedImage(75,75, image4.getType());
//			JLabel picLabel4 = new JLabel(new ImageIcon(image4));
//			//getContentPane().add(picLabel4);
//			panel_7.add(picLabel4);
//			
//			BufferedImage image5 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			image5 = new BufferedImage(75,75, image5.getType());
//			image5 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
//			JLabel picLabel5 = new JLabel(new ImageIcon(image5));
//			//getContentPane().add(picLabel5);
//			panel_8.add(picLabel5);
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
