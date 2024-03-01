import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Adminpage {

	 JFrame frame;
	public static JLabel label1;
	public static JLabel label2;
	public static JLabel label3;
	public static JLabel label4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminpage window = new Adminpage();
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
	public Adminpage() {
		initialize();
		name(EditProfile.msg);
		email(EditProfile.msg2);
		mobile(EditProfile.msg3);
		password(EditProfile.msg4);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 764, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btn1 = new JButton("Add Train");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTrain a=new AddTrain();
				a.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(179, 62, 112, 23);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Train List");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 TrainList t=new TrainList();
	             t.ListFrame.setVisible(true);
	             frame.dispose();
				try {
					//To list all the train which are available
					Connection con=DbConnection.getConnection();
				String q3="select * from Train";
    		    PreparedStatement pst=con.prepareStatement(q3);
    			ResultSet rs=pst.executeQuery();
    			 
    			 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) t.table.getModel();
    		    	
    		    	int cols=rsmd.getColumnCount();
    		    	String[] colName=new String[cols];
    		    	for(int i=0;i<cols;i++)
    		    		colName[i]=rsmd.getColumnName(i+1);
    		    
    		    	model.setColumnIdentifiers(colName);
    		    	
    		    	String id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare,Date;
    		    	while(rs.next()) {
    		    		
    		    		id=rs.getString(1);
    		    		TrainNo=rs.getString(2);
    		    	     name=rs.getString(3);
    		    		From=rs.getString(4);
    		    		To=rs.getString(5);
    		    		DTime=rs.getString(6);
    		    		RTime=rs.getString(7);
    		    		L=rs.getString(8);
    		    		M=rs.getString(9);
    		    		U=rs.getString(10);
    		    		RAC=rs.getString(11);
    		    		WL=rs.getString(12);
    		    		Fare=rs.getString(13);
    		    		Date=rs.getString(14);
    		    		
    		    		String[] row= {id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare,Date};
    		    		
    		    		model.addRow(row);
    		    	}
				}catch(Exception exp) {
					System.out.print(exp);
				}
            
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(365, 62, 129, 23);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("Back");
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin a=new AdminLogin();
				a.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(10, 477, 74, 23);
		frame.getContentPane().add(btn3);
		
		JButton btn4 = new JButton("Bookings");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingList b=new BookingList();
				b.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(179, 117, 112, 23);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("Users Request");
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=DbConnection.getConnection();
					PreparedStatement pst;
					ResultSet rs;
					ResultSetMetaData rsmd;
					UserProfile u=new UserProfile();
					DefaultTableModel model;
					
					//To check if user send request regarding to change name
					if(!EditProfile.msg.equals("")) {
						
					String q1="select old_name,new_name,email from name";
				     pst=con.prepareStatement(q1);
					 rs=pst.executeQuery();
					
		    		//it contains information of no of rows,columns and column names
		    	rsmd=rs.getMetaData();
		    	//To add rows and columns to our table at run time
		    	model=(DefaultTableModel) u.table1.getModel();
		    	
		    	//It will give the column count of the table
		    	int cols=rsmd.getColumnCount();
		    	String[] colName=new String[cols];
		    	for(int i=0;i<cols;i++)
		    		colName[i]=rsmd.getColumnName(i+1);
		    	//we set column names to our table
		    	model.setColumnIdentifiers(colName);
		    	
		    	String old_name,new_name,email;
		    	while(rs.next()) {
		    		old_name=rs.getString(1);
		    		new_name=rs.getString(2);
		    		email=rs.getString(3);
		    		
		    		String[] row= {old_name,new_name,email};
		    		//add the rows to our table
		    		model.addRow(row);
		    	}
					}
					
		    	if(!EditProfile.msg2.equals("")) {
		    	String q2="select old_email,new_email from u_email";
				pst=con.prepareStatement(q2);
				 rs=pst.executeQuery();
				
	    		 rsmd=rs.getMetaData();
	    	 model=(DefaultTableModel) u.table2.getModel();
	    	
	    	int ecols=rsmd.getColumnCount();
	    	String[] ecolName=new String[ecols];
	    	for(int i=0;i<ecols;i++)
	    		ecolName[i]=rsmd.getColumnName(i+1);
	    	
	    	model.setColumnIdentifiers(ecolName);
	    	
	    	String old_email,new_email;
	    	while(rs.next()) {
	    		old_email=rs.getString(1);
	    		new_email=rs.getString(2);
	    		
	    		String[] row= {old_email,new_email};
	    		model.addRow(row);
	    	}
		    	}
		    	
	    	if(!EditProfile.msg3.equals("")) {
	    	String q3="select old_mobileNo,new_mobileNo,email from mobile";
			pst=con.prepareStatement(q3);
			 rs=pst.executeQuery();
			
    		 rsmd=rs.getMetaData();
    	 model=(DefaultTableModel) u.table3.getModel();
    	
    	int mcols=rsmd.getColumnCount();
    	String[] mcolName=new String[mcols];
    	for(int i=0;i<mcols;i++)
    		mcolName[i]=rsmd.getColumnName(i+1);
    	
    	model.setColumnIdentifiers(mcolName);
    	
    	String old_mobileNo,new_mobileNo,email;
    	while(rs.next()) {
    		old_mobileNo=rs.getString(1);
    		new_mobileNo=rs.getString(2);
    		email=rs.getString(3);
    		
    		String[] row= {old_mobileNo,new_mobileNo,email};
    		model.addRow(row);
    	}
	    	}
    	if(!EditProfile.msg4.equals("")) {
    	String q4="select old_password,new_password,email from password";
		pst=con.prepareStatement(q4);
		 rs=pst.executeQuery();
		
		 rsmd=rs.getMetaData();
	 model=(DefaultTableModel) u.table4.getModel();
	
	int pcols=rsmd.getColumnCount();
	String[] pcolName=new String[pcols];
	for(int i=0;i<pcols;i++)
		pcolName[i]=rsmd.getColumnName(i+1);
	
	model.setColumnIdentifiers(pcolName);
	
	String old_password,new_password,email;
	while(rs.next()) {
		old_password=rs.getString(1);
		new_password=rs.getString(2);
		email=rs.getString(3);
		
		String[] row= {old_password,new_password,email};
		model.addRow(row);
	}
    	}
			u.frame.setVisible(true);
			frame.dispose();
				}catch(Exception exp) {
					System.out.println(exp);
				}
				
			}
		});
		btn5.setFont(new Font("Calibri", Font.BOLD, 15));
		btn5.setBounds(365, 117, 129, 23);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new JButton("My Profile");
		btn6.setBackground(Color.BLACK);
		btn6.setForeground(Color.WHITE);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=DbConnection.getConnection();
					String q="select * from admin";
					PreparedStatement pst=con.prepareStatement(q);
					ResultSet rs=pst.executeQuery();
					rs.next();
					String name=rs.getString(1);
					String email=rs.getString(2);
					String password=rs.getString(3);
					AdminProfile ap= new AdminProfile();
					ap.textField1.setText(name);
					ap.textField3.setText(email);
				    ap.passwordField1.setText(password);
					
					
					ap.frame.setVisible(true);
					frame.dispose();
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn6.setFont(new Font("Calibri", Font.BOLD, 15));
		btn6.setBounds(638, 11, 100, 23);
		frame.getContentPane().add(btn6);
		
		 label1 = new JLabel("");
		 label1.setFont(new Font("Calibri", Font.BOLD, 15));
		 label1.setForeground(Color.WHITE);
		label1.setBounds(137, 184, 441, 32);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("");
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setForeground(Color.WHITE);
		label2.setBounds(132, 253, 441, 32);
		frame.getContentPane().add(label2);
		
		 label3 = new JLabel("");
		 label3.setFont(new Font("Calibri", Font.BOLD, 15));
		 label3.setForeground(Color.WHITE);
		label3.setBounds(132, 311, 474, 32);
		frame.getContentPane().add(label3);
		
	    label4 = new JLabel("");
	    label4.setFont(new Font("Calibri", Font.BOLD, 15));
	    label4.setForeground(Color.WHITE);
		label4.setBounds(132, 354, 441, 32);
		frame.getContentPane().add(label4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\admin3.jpg"));
		lblNewLabel.setBounds(0, 0, 748, 511);
		frame.getContentPane().add(lblNewLabel);
	}
	public void name(String msg) {
		label1.setText(msg);
	}
	public void email(String msg) {
		label2.setText(msg);
	}
	public void mobile(String msg) {
		label3.setText(msg);
	}
	public void password(String msg) {
		label4.setText(msg);
	}
}
