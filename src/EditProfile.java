import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class EditProfile {

	 JFrame frame;
	 JTextField textField1;
	private JTextField textField2;
	 JTextField textField3;
	private JTextField textField4;
	JTextField textField5;
	private JTextField textField6;
	 JPasswordField passwordField1;
	private JPasswordField passwordField2;
  public static String msg="";
  public static String msg2="";
  public static String msg3="";
  public static String msg4="";
  public static String uname;
  public static String uemail;

 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile window = new EditProfile();
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
	public EditProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 761, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Old Name:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(39, 64, 70, 14);
		frame.getContentPane().add(label1);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.BOLD, 15));
		textField1.setEditable(false);
		textField1.setBounds(108, 61, 146, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel label2 = new JLabel("New Name:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(286, 64, 87, 14);
		frame.getContentPane().add(label2);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Calibri", Font.BOLD, 15));
		textField2.setColumns(10);
		textField2.setBounds(369, 61, 146, 20);
		frame.getContentPane().add(textField2);
		
		JButton btn1 = new JButton("change");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( !textField2.getText().equals("")) {
				
						String s=textField2.getText();
						//To check if the name is already registered
						if(s.equals(textField1.getText()))
							JOptionPane.showMessageDialog(btn1, "you're already registered with same name");
						else {
							//To check if name is in proper format
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
								//To if the user already send request or not
								String q1="select count(*) from name where email=?";
								PreparedStatement pst=con.prepareStatement(q1);
								pst.setString(1,myProfile.email);				
								ResultSet rs=pst.executeQuery();
								rs.next();
								if(rs.getInt(1)>0) 
									JOptionPane.showMessageDialog(btn1,"Your request has already been sent");
								else {
									//insert the details into the table
								String q2="insert into name values(?,?,?)";
							    pst=con.prepareStatement(q2);
								pst.setString(1,textField1.getText());
								pst.setString(2,s);
								pst.setString(3,Login.textField1.getText());
								pst.executeUpdate();
							
                   JOptionPane.showMessageDialog(btn1,"Your request has been send to admin..\nyou'll be notified once the name get changed");
                           //create string message to notify the admin
							  msg="!! 1.you have request from user regarding to change the name";
						}
							}catch(Exception exp) {
								System.out.println(exp);
							}
						}
						}
				}else
					JOptionPane.showMessageDialog(btn1,"Enter the name to change");
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(394, 100, 93, 23);
		frame.getContentPane().add(btn1);
		
		JLabel label3 = new JLabel("Old Email:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(39, 151, 70, 14);
		frame.getContentPane().add(label3);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Calibri", Font.BOLD, 15));
		textField3.setEditable(false);
		textField3.setColumns(10);
		textField3.setBounds(108, 146, 146, 20);
		frame.getContentPane().add(textField3);
		
		JLabel label4 = new JLabel("New Email:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(286, 149, 87, 14);
		frame.getContentPane().add(label4);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Calibri", Font.BOLD, 15));
		textField4.setColumns(10);
		textField4.setBounds(369, 146, 146, 20);
		frame.getContentPane().add(textField4);
		
		JButton btn2 = new JButton("change");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textField4.getText().equals("")) {
						String s1=textField4.getText();
						if(s1.equals(textField3.getText()))
							JOptionPane.showMessageDialog(btn2,"You're already registered with same email");
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
								String q1="select count(*) from u_email where old_email=?";
								PreparedStatement pst=con.prepareStatement(q1);
								pst.setString(1,myProfile.email);				
								ResultSet rs=pst.executeQuery();
								rs.next();
								if(rs.getInt(1)>0) 
									JOptionPane.showMessageDialog(btn2,"Your request has already been sent");
								else {
								String q2="insert into u_email values(?,?,?)";
				                pst=con.prepareStatement(q2);
								pst.setString(1,textField3.getText());
								pst.setString(2, s1);
								pst.setString(3,myProfile.name);
								pst.executeUpdate();
							
							JOptionPane.showMessageDialog(btn1,"Your request has been send to admin..\nyou'll be notified once the email get changed");
							msg2="!! 2.you have request from user regarding to change the email";
                            }
								
							}catch(Exception exp) {
								System.out.println(exp);
							}
						}else
							JOptionPane.showMessageDialog(btn2,"Email shall contains lowercase letters,numbers, \nand ends with suffix (@gmail.com)");
						}
				}else
					JOptionPane.showMessageDialog(btn2,"Enter the email to change");
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(394, 177, 93, 23);
		frame.getContentPane().add(btn2);
		
		JLabel label5 = new JLabel("Old No:");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(50, 227, 70, 14);
		frame.getContentPane().add(label5);
		
		textField5 = new JTextField();
		textField5.setFont(new Font("Calibri", Font.BOLD, 15));
		textField5.setEditable(false);
		textField5.setColumns(10);
		textField5.setBounds(108, 222, 146, 20);
		frame.getContentPane().add(textField5);
		
		JLabel label6 = new JLabel("New No:");
		label6.setForeground(Color.WHITE);
		label6.setHorizontalAlignment(SwingConstants.TRAILING);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(286, 225, 70, 14);
		frame.getContentPane().add(label6);
		
		textField6 = new JTextField();
		textField6.setFont(new Font("Calibri", Font.BOLD, 15));
		textField6.setColumns(10);
		textField6.setBounds(369, 222, 146, 20);
		frame.getContentPane().add(textField6);
		
		JButton btn3 = new JButton("change");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( !textField6.getText().equals("")) {
						String m=textField6.getText();
						if(m.equals(textField5.getText()))
							JOptionPane.showMessageDialog(btn3, "you're already registered with same mobile number");
						else {
						if(m.charAt(0)=='6' || m.charAt(0)=='7' || m.charAt(0)=='8'||m.charAt(0)=='9') {
							if(m.length()==10) {
								try {
									Connection con=DbConnection.getConnection();
									
									String q1="select count(*) from mobile where email=?";
									PreparedStatement pst=con.prepareStatement(q1);
									pst.setString(1,myProfile.email);				
									ResultSet rs=pst.executeQuery();
									rs.next();
									if(rs.getInt(1)>0) 
										JOptionPane.showMessageDialog(btn3,"Your request has already been sent");
									else {
									String q2="insert into mobile values(?,?,?,?)";
								    pst=con.prepareStatement(q2);
									pst.setString(1,textField5.getText());
									pst.setString(2, m);
									pst.setString(3,myProfile.name);
									pst.setString(4,myProfile.email);
									pst.executeUpdate();
									
								JOptionPane.showMessageDialog(btn1,"Your request has been send to admin..\nyou'll be notified once the mobile number get changed");
								msg3="!! 3.you have request from user regarding to change the mobile number";
									}
								}catch(Exception exp) {
									System.out.println(exp);
								}
							}else
								JOptionPane.showMessageDialog(btn3, "mobile number should've 10 numbers");
						}else
							JOptionPane.showMessageDialog(btn3, "mobile number should be in correct format");
						}
				}else
					JOptionPane.showMessageDialog(btn3, "Enter the mobile number to change");
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(394, 253, 93, 23);
		frame.getContentPane().add(btn3);
		
		JLabel label7 = new JLabel("Old Password:");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(10, 300, 93, 14);
		frame.getContentPane().add(label7);
		
		passwordField1 = new JPasswordField();
		passwordField1.setEditable(false);
		passwordField1.setBounds(108, 295, 146, 20);
		frame.getContentPane().add(passwordField1);
		
		JLabel label8 = new JLabel("New Password:");
		label8.setForeground(Color.WHITE);
		label8.setFont(new Font("Calibri", Font.BOLD, 15));
		label8.setBounds(264, 300, 108, 14);
		frame.getContentPane().add(label8);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(369, 295, 146, 20);
		frame.getContentPane().add(passwordField2);
		
		JButton btn4 = new JButton("change");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw=passwordField1.getText();
				if(!passwordField2.getText().equals("")) {
					String pw1=passwordField2.getText();
					
					if(pw.equals(pw1))
						JOptionPane.showMessageDialog(btn4,"you've already registered with same password");
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
						JOptionPane.showMessageDialog(btn4,"Use atleast 8 characters one uppercase letter one lowercase letter \none number and must not contain space");
					}else {
						try {
							Connection con=DbConnection.getConnection();
							String q1="select count(*) from password where email=?";
							PreparedStatement pst=con.prepareStatement(q1);
							pst.setString(1,myProfile.email);				
							ResultSet rs=pst.executeQuery();
							rs.next();
							if(rs.getInt(1)>0) 
								JOptionPane.showMessageDialog(btn4,"Your request has already been sent");
							else {
							String q2="insert into password values(?,?,?,?)";
						    pst=con.prepareStatement(q2);
							pst.setString(1,pw);
							pst.setString(2,pw1);
							pst.setString(3,myProfile.name);
							pst.setString(4,myProfile.email);
							pst.executeUpdate();
							
						JOptionPane.showMessageDialog(btn1,"Your request has been send to admin..\nyou'll be notified once the password get changed");
						msg4="!! 4.you have request from user regarding to change the password";
							}
						}catch(Exception exp) {
							System.out.println(exp);
						}
					}
					}
				}else
					JOptionPane.showMessageDialog(btn4, "Enter the password to change");
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(394, 326, 93, 23);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("Back");
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myProfile m=new myProfile();
				m.ProfileFrame.setVisible(true);
				frame.dispose();
				
				m.textField1.setText(myProfile.name);
				m.textField2.setText(myProfile.email);
				m.textField3.setText(myProfile.mobile);
			}
		});
		btn5.setFont(new Font("Calibri", Font.BOLD, 15));
		btn5.setBounds(10, 388, 77, 23);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new JButton("Login Page");
		btn6.setBackground(Color.BLACK);
		btn6.setForeground(Color.WHITE);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.LoginFrame.setVisible(true);
				frame.dispose();
			}
		});
		btn6.setFont(new Font("Calibri", Font.BOLD, 15));
		btn6.setBounds(620, 11, 115, 23);
		frame.getContentPane().add(btn6);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\EditProfile3.jpg"));
		lblNewLabel.setBounds(0, 0, 745, 411);
		frame.getContentPane().add(lblNewLabel);
	}
}
