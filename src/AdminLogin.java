import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
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

public class AdminLogin {

	 JFrame frame;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
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
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 714,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Login");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 20));
		label1.setBounds(300, 91, 56, 25);
		frame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Email id:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(204, 182, 62, 20);
		frame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("password:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(195, 225, 71, 20);
		frame.getContentPane().add(label3);
		
		email = new JTextField();
		email.setBounds(276, 180, 128, 20);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		JButton btn1 = new JButton("Login");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=DbConnection.getConnection();
					String q1="select email,password from admin";
					PreparedStatement pst=con.prepareStatement(q1);
					ResultSet rs=pst.executeQuery();
					rs.next();
					String em=rs.getString(1);
					String p=rs.getString(2);
					if(email.getText().equals("") && password.getText().equals(""))
						JOptionPane.showMessageDialog(btn1,"Enter the username and password");
					else if(email.getText().equals(""))
						JOptionPane.showMessageDialog(btn1,"Enter the username");
					else if(password.getText().equals(""))
						JOptionPane.showMessageDialog(btn1,"Enter the password");
					else {
					if(em.equals(email.getText()) && p.equals(password.getText())) {
						Adminpage a=new Adminpage();
						a.frame.setVisible(true);
						frame.dispose();
					}else if(em.equals(email.getText()))
						JOptionPane.showMessageDialog(btn1, "Enter Correct Password");
					else
						JOptionPane.showMessageDialog(btn1, "Acces for admin only");
					}
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(300, 284, 71, 23);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Back");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegister u=new UserRegister();
				u.RFrame.setVisible(true);
				frame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 377, 71, 23);
		frame.getContentPane().add(btn2);
		
		password = new JPasswordField();
		password.setBounds(276, 225, 128, 22);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\alogin.jpg"));
		lblNewLabel.setBounds(0, 0, 698, 411);
		frame.getContentPane().add(lblNewLabel);
	}
}
