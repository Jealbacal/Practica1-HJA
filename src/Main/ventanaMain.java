package Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JSplitPane;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ventanaMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_1;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMain frame = new ventanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaMain() {
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
		
		JLabel lblNewLabel = new JLabel("Output Text");
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1);
		
		rdbtnNewRadioButton = new JRadioButton("Ap1");
		splitPane_1.setLeftComponent(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Ap2");
		splitPane_1.setRightComponent(rdbtnNewRadioButton_1);
		
		splitPane = new JSplitPane();
		panel_1.add(splitPane);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Ap3");
		splitPane.setLeftComponent(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Ap4");
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
		
		try {
			
			BufferedImage image1 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
			JLabel picLabel1 = new JLabel(new ImageIcon(image1));
			//getContentPane().add(picLabel1);
			panel_4.add(picLabel1);
			
			BufferedImage image2 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
			JLabel picLabel2 = new JLabel(new ImageIcon(image2));
			//getContentPane().add(picLabel2);
			panel_5.add(picLabel2);
			
			BufferedImage image3 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
			JLabel picLabel3 = new JLabel(new ImageIcon(image3));
			//getContentPane().add(picLabel3);
			panel_6.add(picLabel3);
			
			BufferedImage image4 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
			JLabel picLabel4 = new JLabel(new ImageIcon(image4));
			//getContentPane().add(picLabel4);
			panel_7.add(picLabel4);
			
			BufferedImage image5 = ImageIO.read(new File("PNG-cards-1.3/ace_of_spades.png"));
			JLabel picLabel5 = new JLabel(new ImageIcon(image5));
			//getContentPane().add(picLabel5);
			panel_8.add(picLabel5);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
