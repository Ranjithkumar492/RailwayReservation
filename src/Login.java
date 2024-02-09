import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
public class Login {

	 JFrame LoginFrame;
	public static JTextField textField1;
	private JButton btn2;
	private JPasswordField passwordField;
	private JLabel ilabel;
     
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.LoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginFrame = new JFrame();
		LoginFrame.setForeground(Color.BLACK);
		LoginFrame.setBackground(Color.BLACK);
		LoginFrame.getContentPane().setBackground(Color.WHITE);
		LoginFrame.setBounds(100, 100,740,493);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrame.setLocationRelativeTo(null);
		LoginFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Email Id:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(208, 177, 66, 20);
		LoginFrame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Password:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(198, 224, 76, 20);
		LoginFrame.getContentPane().add(label2);
		
		textField1 = new JTextField();
		textField1.setForeground(SystemColor.desktop);
		textField1.setBackground(SystemColor.text);
		textField1.setBounds(279, 177, 130, 20);
		LoginFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JButton btn1 = new JButton("Login");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			// To check if all the fields are filled
				String username=textField1.getText();	
				String password=passwordField.getText();
				if(username.equals("") && password.equals("")) {
					JOptionPane.showMessageDialog(btn1,"Please Enter Username and Password");
				}else if(username.equals(""))
					JOptionPane.showMessageDialog(btn1,"Please Enter Username");
				else if(password.equals(""))
					JOptionPane.showMessageDialog(btn1,"Please Enter Password");
				else {
					//To check if the user got registered
				String s1=textField1.getText();
				String s2=passwordField.getText();
				String query="select * from registration where email=? and password=?";
				try {
					Connection con=DbConnection.getConnection();
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1,s1);
					pst.setString(2,s2);
					 ResultSet rs=pst.executeQuery();
					 if(rs.next()) {
						 String em=rs.getString(2);
						 String ps=rs.getString(3);
						 if(em.equals(s1) && ps.equals(s2)) {
						 JOptionPane.showMessageDialog(btn1,"Logged in Successfully..");
						 ProgressBar p=new ProgressBar();
							p.PFrame.setVisible(true);
							LoginFrame.dispose();
						 }else {
							 if(!em.equals(s1))
							 JOptionPane.showMessageDialog(btn1,"User not Available..Register to continue..");
							 else
								 JOptionPane.showMessageDialog(btn1,"Enter Correct Password");
						 }
					 }else {
						 String query2="select * from registration where email=?";
						 pst=con.prepareStatement(query2);
						 pst.setString(1,s1);
						  rs=pst.executeQuery();
						 if(rs.next()) 
							 JOptionPane.showMessageDialog(btn1,"Enter Correct Password");
						 else 
						 JOptionPane.showMessageDialog(btn1,"User not Available..Register to continue..");
					 }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(289, 270, 89, 23);
		LoginFrame.getContentPane().add(btn1);
		
		btn2 = new JButton("Back");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegister u=new UserRegister();
				u.RFrame.setVisible(true);
				LoginFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 420, 76, 23);
		LoginFrame.getContentPane().add(btn2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(279, 222, 130, 20);
		LoginFrame.getContentPane().add(passwordField);
		
		ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Login.jpg"));
		ilabel.setBounds(0, 0, 724, 454);
		LoginFrame.getContentPane().add(ilabel);
	}
}
