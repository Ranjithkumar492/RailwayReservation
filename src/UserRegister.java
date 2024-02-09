import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.sql.*
;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JRadioButton;public class UserRegister {

	 JFrame RFrame;
	private JTextField textField2;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField textField1;
	private JPasswordField cpasswordField;
	private JPasswordField cpasswordField_1;
	private JPasswordField pass2;
	JRadioButton r1;
	JRadioButton r2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegister window = new UserRegister();
					window.RFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public UserRegister() {
		initialize();
		try {
			check();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		RFrame = new JFrame();
		RFrame.getContentPane().setBackground(Color.WHITE);
		RFrame.setBounds(100, 100, 736, 460);
		RFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RFrame.setLocationRelativeTo(null);
		RFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("USER REGISTRATION");
		label1.setForeground(Color.BLACK);
		label1.setFont(new Font("Calibri", Font.BOLD, 25));
		label1.setBounds(251, 78, 214, 25);
		RFrame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Name:");
		label2.setForeground(Color.RED);
		label2.setBackground(Color.BLACK);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(238, 121, 47, 14);
		RFrame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Email Id:");
		label3.setForeground(Color.RED);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(223, 146, 66, 14);
		RFrame.getContentPane().add(label3);
		
		JLabel label4 = new JLabel("Password:");
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(219, 183, 66, 14);
		RFrame.getContentPane().add(label4);
		
		JLabel label5 = new JLabel("Confirm Password:");
		label5.setForeground(Color.RED);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(166, 209, 123, 25);
		RFrame.getContentPane().add(label5);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(294, 147, 162, 20);
		RFrame.getContentPane().add(textField2);
		
		passwordField1=new JPasswordField();
		passwordField1.setBounds(294, 178, 162, 20);
		RFrame.getContentPane().add(passwordField1);
		 
	
		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(294, 116, 162, 20);
		RFrame.getContentPane().add(textField1);
		
		JButton btn1 = new JButton("SIGN UP");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// To check all the fields are filled
	if(textField1.getText().equals("")||textField2.getText().equals("")||passwordField1.getText().equals("")||pass2.getText().equals("")) 
					JOptionPane.showMessageDialog(RFrame,"Complete the required fields");
				else {
					if(r1.isSelected() || r2.isSelected()) {
					// To check if the user already registered
				String s1=textField2.getText();
				String q1="select count(*) from registration where email="+"'"+s1+"'";
				try {
					boolean flag=true;
					Connection con=DbConnection.getConnection();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(q1);
					rs.next();
					int g=rs.getInt(1);
					if(g>0)
						JOptionPane.showMessageDialog(RFrame,"Already registered!");
					else {
					// To check if the email got valid format
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
						
				if(flag==false) {
					 JOptionPane.showMessageDialog(btn1,"Email shall contains lowercase letters,numbers, and ends with suffix (@gmail.com)");
				}else {
					//To check if the password got valid format
					String pw1=passwordField1.getText();
					boolean flag2=true; int count1=0,count2=0,count3=0;
					for(int i=0;i<pw1.length();i++) {
						if(pw1.length()<8) {
							flag2=false;
							break;
						}else {
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
						JOptionPane.showMessageDialog(btn1,"Use atleast 8 characters one uppercase letter one lowercase letter one number and must not contain space");
					}else {
						// insert the user into registration table in database
						String name=textField1.getText();
						String gender="";
						if(r1.isSelected())
							gender="Male";
						else if(r2.isSelected())
							gender="Female";
						
						String query="insert into registration values(?,?,?,?)";
						
						     con=DbConnection.getConnection();
							PreparedStatement pst=con.prepareStatement(query);
							pst.setString(1,name);
							pst.setString(2,s1);
							pst.setString(3,pw1);
							pst.setString(4,gender);
							int rows=pst.executeUpdate();
							if(rows>0)
							JOptionPane.showMessageDialog(btn1,"Registered Successfully..");
							//To send the registered user, confirmation mail
							
						Email t=new Email();
						t.register(s1,name,gender);
					
					}
				}
					}
					}
				catch(Exception y) {
					System.out.println(y);
				}
				}else
					JOptionPane.showMessageDialog(btn1,"Choose the gender");
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 12));
		btn1.setBounds(294, 303, 79, 23);
		RFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("LOGIN");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.LoginFrame.setVisible(true);
				RFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 12));
		btn2.setBounds(383, 303, 73, 23);
		RFrame.getContentPane().add(btn2);
		
		pass2 = new JPasswordField();
		pass2.addMouseListener(new MouseAdapter() {
		     // To check the confirm password
			public void mouseExited(MouseEvent e) {
				String pw1=passwordField1.getText();
				String pw2=pass2.getText();
				if(!pw1.equals(pw2)) {
					JOptionPane.showMessageDialog(btn2, "Incorrect password");
				}
			}
		});
		pass2.setBounds(294, 209, 163, 20);
		RFrame.getContentPane().add(pass2);
		
		 r1 = new JRadioButton("Male");
		 r1.setForeground(Color.WHITE);
		 r1.setBackground(Color.BLACK);
		r1.setFont(new Font("Calibri", Font.BOLD, 15));
		r1.setBounds(294, 249, 66, 23);
		RFrame.getContentPane().add(r1);
		
		 r2 = new JRadioButton("Female");
		 r2.setForeground(Color.WHITE);
		 r2.setBackground(Color.BLACK);
		r2.setFont(new Font("Calibri", Font.BOLD, 15));
		r2.setBounds(383, 249, 73, 23);
		RFrame.getContentPane().add(r2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		JLabel label6 = new JLabel("Gender:");
		label6.setForeground(Color.RED);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(223, 245, 62, 25);
		RFrame.getContentPane().add(label6);
		
		JLabel ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\UserRegister.jpg"));
		ilabel.setBounds(0, 0, 720, 421);
		RFrame.getContentPane().add(ilabel);
		
	}
	public void check() throws Exception {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		Date d=new Date();
		String s=dateFormat.format(d);
	
		Connection con=DbConnection.getConnection();
        String q1="select count(*) from id where d=?";
		PreparedStatement pst=con.prepareStatement(q1);
		pst.setString(1,s);
		ResultSet rs=pst.executeQuery();
		rs.next();
		if(rs.getInt(1)>0) {
			String q2="select Express from id where d=?";
			pst=con.prepareStatement(q2);
			pst.setString(1,s);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				String express=rs.getString(1);
				Booking b=new Booking();
				b.time(express,s,false);
			}
		}
	}
}
