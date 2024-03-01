import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class myProfile {

  JFrame ProfileFrame;
  public static String name;
  public static String password;
  public static String email;
  public static String mobile;
  JTextField textField1;
  JTextField textField2;
  JTextField textField3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myProfile window = new myProfile();
					window.ProfileFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ProfileFrame = new JFrame();
		ProfileFrame.setBounds(100, 100, 728, 410);
		ProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ProfileFrame.setLocationRelativeTo(null);
		ProfileFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Name:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(247, 102, 46, 14);
		ProfileFrame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Email id:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(225, 145, 68, 14);
		ProfileFrame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Phone No:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(221, 189, 68, 14);
		ProfileFrame.getContentPane().add(label3);
		
		JButton btn1 = new JButton("Edit Profile");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfile p=new EditProfile();
				p.frame.setVisible(true);
				ProfileFrame.dispose();
				
				p.textField1.setText(name);
				p.textField3.setText(email);
				p.textField5.setText(mobile);
				p.passwordField1.setText(password);
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(320, 261, 106, 23);
		ProfileFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Back");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h=new Home();
				h.HomeFrame.setVisible(true);
				ProfileFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 337, 78, 23);
		ProfileFrame.getContentPane().add(btn2);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.BOLD, 15));
		textField1.setEditable(false);
		textField1.setBounds(303, 99, 141, 20);
		ProfileFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Calibri", Font.BOLD, 15));
		textField2.setEditable(false);
		textField2.setColumns(10);
		textField2.setBounds(303, 142, 141, 20);
		ProfileFrame.getContentPane().add(textField2);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Calibri", Font.BOLD, 15));
		textField3.setEditable(false);
		textField3.setColumns(10);
		textField3.setBounds(303, 186, 141, 20);
		ProfileFrame.getContentPane().add(textField3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\myprofile3.jpg"));
		lblNewLabel.setBounds(0, 0, 712, 371);
		ProfileFrame.getContentPane().add(lblNewLabel);
	}
	public void set(String email1) {
		try {
			Connection con=DbConnection.getConnection();
			String q="select * from registration where email=?";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1,email1);
			ResultSet rs=pst.executeQuery();
			rs.next();
			
			name=rs.getString(1);
			email=rs.getString(2);
			password=rs.getString(3);
			mobile=rs.getString(5);
			
			textField1.setText(name);
			textField2.setText(email);
			textField3.setText(mobile);
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
	}
}
