import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminProfile {

	 JFrame frame;
	JTextField textField1;
	 JTextField textField2;
	 JTextField textField3;
	 JTextField textField4;
     JPasswordField passwordField1;
     JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminProfile window = new AdminProfile();
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
	public AdminProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 714, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Admin Profile");
		label1.setFont(new Font("Calibri", Font.BOLD, 20));
		label1.setBounds(294, 27, 120, 23);
		frame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Name:");
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(63, 88, 54, 31);
		frame.getContentPane().add(label2);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(120, 91, 138, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel label3 = new JLabel("New Name:");
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(308, 88, 78, 23);
		frame.getContentPane().add(label3);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(396, 91, 138, 20);
		frame.getContentPane().add(textField2);
		
		JLabel label4 = new JLabel("Email:");
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(63, 181, 54, 23);
		frame.getContentPane().add(label4);
		
		JButton btn1 = new JButton("Change");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !textField2.getText().equals("")) {
					String s=textField2.getText();
					if(s.equals(textField1.getText()))
						JOptionPane.showMessageDialog(btn1, "you've already registered with same name");
					else {
					boolean flag=true;
					for(int i=0;i<s.length();i++) {
						if(s.charAt(i)>=65 && s.charAt(i)<=90)
							continue;
						else if(s.charAt(i)>=97 && s.charAt(i)<=122)
							continue;
						else if(s.charAt(i)==' ')
							continue;
						else{
							flag=false;
							break;
						}
					}
					if(flag==false) 
						JOptionPane.showMessageDialog(btn1,"Name should contain only lowercase,uppercase letters");
					else {
					
				try {
					Connection con=DbConnection.getConnection();
					String q="update admin set name=?";
					PreparedStatement pst=con.prepareStatement(q);
					pst.setString(1,textField2.getText());
					pst.executeUpdate();
					textField1.setText(textField2.getText());
				   JOptionPane.showMessageDialog(btn1, "Name get changed..");
					
				}catch(Exception exp) {
					System.out.println(exp);
				}
					}
					}
				}else
					JOptionPane.showMessageDialog(btn1, "Enter the name to change");
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(422, 134, 92, 23);
		frame.getContentPane().add(btn1);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setColumns(10);
		textField3.setBounds(120, 180, 138, 20);
		frame.getContentPane().add(textField3);
		
		JLabel label5 = new JLabel("New Email:");
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(308, 181, 78, 23);
		frame.getContentPane().add(label5);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(396, 180, 138, 20);
		frame.getContentPane().add(textField4);
		
		JButton btn2 = new JButton("Change");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField4.getText().equals("")) {
					String s1=textField4.getText();
					if(s1.equals(textField3.getText()))
						JOptionPane.showMessageDialog(btn2,"You've already registered with same email");
					else {
					boolean flag=true;
					if(s1.contains("@gmail.com")) {
						for(int i=0;i<s1.length()-10;i++) {
							if(s1.charAt(0)=='.' || s1.charAt(s1.length()-1)=='.') {
								flag=false;
								break;
							}
								if(s1.charAt(i)>=97 && s1.charAt(i)<=122) {
									continue;
								}else if(s1.charAt(i)>=48 && s1.charAt(i)<=57) {
									continue;
								}else {
									flag=false;
									break;
								}
							}
						}else
							flag=false;
							
					if(flag==true) {

				try {
					Connection con=DbConnection.getConnection();
					String q="update admin set email=?";
					PreparedStatement pst=con.prepareStatement(q);
					pst.setString(1,textField4.getText());
					pst.executeUpdate();
					textField3.setText(textField4.getText());
				   JOptionPane.showMessageDialog(btn2, "Email get changed..");
					
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}else
			  JOptionPane.showMessageDialog(btn2,"Email shall contains lowercase letters,numbers, \nand ends with suffix (@gmail.com)");
					}
				}else
					JOptionPane.showMessageDialog(btn2, "Enter the email to change");
					}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(422, 220, 92, 23);
		frame.getContentPane().add(btn2);
		
		JLabel label6 = new JLabel("Password:");
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(38, 259, 78, 23);
		frame.getContentPane().add(label6);
		
		JLabel label7 = new JLabel("New Password:");
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(294, 259, 107, 23);
		frame.getContentPane().add(label7);
		
		JButton btn3 = new JButton("Change");
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw=passwordField1.getText();
				if(!passwordField2.getText().equals("")) {
					String pw1=passwordField2.getText();
					
					if(pw.equals(pw1))
						JOptionPane.showMessageDialog(btn3,"you've already registered with same password");
					else {
					boolean flag2=true; int count1=0,count2=0,count3=0;
					
					if(pw1.length()<8) 
						flag2=false;
					else {
					for(int i=0;i<pw1.length();i++) {
						
							if(pw1.charAt(i)>=65 && pw1.charAt(i)<=90) {
								count1++;
							}else if(pw1.charAt(i)>=97 && pw1.charAt(i)<=122) {
								count2++;
							}else if(pw1.charAt(i)>48 && pw1.charAt(i)<=57) {
								count3++;
							}else if(pw1.charAt(i)==' ') {
								flag2=false;
								break;
							}
						}
					}
					if(flag2==false || count1<1 ||count2<1 || count3<1) {
						JOptionPane.showMessageDialog(btn3,"Use atleast 8 characters one uppercase letter one lowercase letter \none number and must not contain space");
					}else {
					
				try {
					Connection con=DbConnection.getConnection();
					String q="update admin set password=?";
					PreparedStatement pst=con.prepareStatement(q);
					pst.setString(1,pw1);
					pst.executeUpdate();
					 passwordField1.setText( passwordField2.getText());
				   JOptionPane.showMessageDialog(btn3, "Password get changed..");
					
				}catch(Exception exp) {
					System.out.println(exp);
				}
					}
					}
				}else
					 JOptionPane.showMessageDialog(btn3, "Enter the password to change");
				
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(422, 289, 92, 23);
		frame.getContentPane().add(btn3);
		
		passwordField1 = new JPasswordField();
		passwordField1.setEditable(false);
		passwordField1.setBounds(120, 258, 137, 20);
		frame.getContentPane().add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(397, 254, 137, 20);
		frame.getContentPane().add(passwordField2);
		
		JButton btn4 = new JButton("Back");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminpage a=new Adminpage();
				a.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(10, 335, 79, 23);
		frame.getContentPane().add(btn4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Adminprofile.jpg"));
		lblNewLabel.setBounds(0, 0, 698, 369);
		frame.getContentPane().add(lblNewLabel);
	}
}
