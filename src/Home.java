import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Home {

	 JFrame HomeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.HomeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		HomeFrame = new JFrame();
		HomeFrame.getContentPane().setBackground(Color.WHITE);
		HomeFrame.setBounds(100, 100,826, 464);
		HomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HomeFrame.setLocationRelativeTo(null);
		HomeFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Train Resevation System");
		label1.setFont(new Font("Calibri", Font.BOLD, 25));
		label1.setBounds(255, 28, 264, 36);
		HomeFrame.getContentPane().add(label1);
		
		JButton btn1 = new JButton("Book Ticket");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the booking class
				Train t=new Train();
				t.TrainFrame.setVisible(true);
				HomeFrame.dispose();
				try {
					Connection con=DbConnection.getConnection();
				String q3="select Tno,name,Fr,T,DTime,RTime from Train";
    		    PreparedStatement pst=con.prepareStatement(q3);
    			ResultSet rs=pst.executeQuery();
    			 
    			 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) Train.table.getModel();
    		    	
    		    	int cols=rsmd.getColumnCount();
    		    	String[] colName=new String[cols];
    		    	for(int i=0;i<cols;i++)
    		    		colName[i]=rsmd.getColumnName(i+1);
    		    
    		    	model.setColumnIdentifiers(colName);
    		    	
    		    	String TrainNo,name,From,To,DTime,RTime;
    		    	while(rs.next()) {
    		    		TrainNo=rs.getString(1);
    		    	     name=rs.getString(2);
    		    		From=rs.getString(3);
    		    		To=rs.getString(4);
    		    		DTime=rs.getString(5);
    		    		RTime=rs.getString(6);
 
    		    		String[] row= {TrainNo,name,From,To,DTime,RTime};
    		    		
    		    		model.addRow(row);
    		    	}
				}catch(Exception exp) {
					System.out.print(exp);
				}
				
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(236, 173, 150, 36);
		HomeFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Cancel Ticket");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the cancel ticket class
				CancelTicket c=new CancelTicket();
				c.CancelFrame.setVisible(true);
				HomeFrame.dispose();
				
				String q="select id,Tno,name from Train";
				try {
				Connection con=DbConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(q);
				ResultSet rs=pst.executeQuery();
				
				 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) CancelTicket.table.getModel();
    		    	
    		    	int cols=rsmd.getColumnCount();
    		    	String[] colName=new String[cols];
    		    	for(int i=0;i<cols;i++)
    		    		colName[i]=rsmd.getColumnName(i+1);
    		    
    		    	model.setColumnIdentifiers(colName);
    		    	
    		    	String id,TrainNo,name;
    		    	while(rs.next()) {
    		    		id=rs.getString(1);
    		    		TrainNo=rs.getString(2);
    		    	     name=rs.getString(3);
    		    		
    		    		String[] row= {id,TrainNo,name};
    		    		
    		    		model.addRow(row);
    		    	}

				
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(411, 173, 134, 36);
		HomeFrame.getContentPane().add(btn2);
		
		JButton btn4 = new JButton("My Bookings");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//call the mybookings class
				MyBookings m=new MyBookings();
				m.MyBookingsFrame.setVisible(true);
				HomeFrame.dispose();
				
				String q="select id,Tno,name from Train";
				try {
				Connection con=DbConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(q);
				ResultSet rs=pst.executeQuery();
				
				 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) MyBookings.Btable.getModel();
    		    	
    		    	int cols=rsmd.getColumnCount();
    		    	String[] colName=new String[cols];
    		    	for(int i=0;i<cols;i++)
    		    		colName[i]=rsmd.getColumnName(i+1);
    		    
    		    	model.setColumnIdentifiers(colName);
    		    	
    		    	String id,TrainNo,name;
    		    	while(rs.next()) {
    		    		id=rs.getString(1);
    		    		TrainNo=rs.getString(2);
    		    	     name=rs.getString(3);
    		    		
    		    		String[] row= {id,TrainNo,name};
    		    		
    		    		model.addRow(row);
    		    	}

				
				}catch(Exception exp) {
					System.out.println(exp);
				}

				
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(341, 236, 122, 36);
		HomeFrame.getContentPane().add(btn4);
		
		JButton btn7 = new JButton("Back");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.LoginFrame.setVisible(true);
				HomeFrame.dispose();
			}
		});
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.BLACK);
		btn7.setFont(new Font("Calibri", Font.BOLD, 15));
		btn7.setBounds(10, 391, 70, 23);
		HomeFrame.getContentPane().add(btn7);
		
		JButton btn5 = new JButton("Profile");
		btn5.setBackground(Color.BLACK);
		btn5.setForeground(Color.WHITE);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=Login.textField1.getText();
				
					myProfile m=new myProfile();
					m.ProfileFrame.setVisible(true);
					
					HomeFrame.dispose();
					
					m.set(email);
					
			}
		});
		btn5.setFont(new Font("Calibri", Font.BOLD, 15));
		btn5.setBounds(716, 11, 84, 23);
		HomeFrame.getContentPane().add(btn5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Home.jpg"));
		lblNewLabel.setBounds(0, 0, 810, 425);
		HomeFrame.getContentPane().add(lblNewLabel);
	}
}
