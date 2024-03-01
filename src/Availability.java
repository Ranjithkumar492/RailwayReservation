import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Availability {

   JFrame frame;
    JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Availability window = new Availability();
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
	public Availability() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 759, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Available Tickets");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Calibri", Font.BOLD, 20));
		label1.setBounds(288, 48, 148, 25);
		frame.getContentPane().add(label1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 84, 648, 74);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		scrollPane.setViewportView(table);
		
		JButton btn1 = new JButton("Back");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingList b=new BookingList();
				b.frame.setVisible(true);
				frame.dispose();
				try {

					int k=BookingList.k;
				 String q5="select count(*) from bookedlist"+k;
				 Connection con=DbConnection.getConnection();
				 PreparedStatement pst=con.prepareStatement(q5);
				 ResultSet rs=pst.executeQuery();
				 rs.next();
				 if(rs.getInt(1)>0) {
					
					 String qy="select * from bookedlist"+k;
			    		pst=con.prepareStatement(qy);
			    		rs=pst.executeQuery();
			    		
			    	ResultSetMetaData rsmd=rs.getMetaData();
			   
			    	DefaultTableModel model=(DefaultTableModel) BookingList.table2.getModel();
			    	
			    	int cols=rsmd.getColumnCount();
			    	String[] colName=new String[cols];
			    	for(int i=0;i<cols;i++)
			    		colName[i]=rsmd.getColumnName(i+1);
			    	
			    	model.setColumnIdentifiers(colName);
			    	
			    	String id,Name,age,pos,alloted,email2,gender;
			    	while(rs.next()) {
			    		id=rs.getString(1);
			    		Name=rs.getString(2);
			    		age=rs.getString(3);
			    		pos=rs.getString(4);
			    		alloted=rs.getString(5);
			    		email2=rs.getString(6);
			    		gender=rs.getString(7);
			    		
			    		String[] row= {id,Name,age,pos,alloted,email2,gender};
			    	
			    		model.addRow(row);
			    	}
			    	
			    	 String q7="create table email2(email varchar(40))";
	                    pst=con.prepareStatement(q7);
						pst.executeUpdate();
						
						//To get the unique email from bookedlist
						String qy9="select distinct(email) from bookedlist"+k;
						pst=con.prepareStatement(qy9);
						rs=pst.executeQuery();
						while(rs.next()) {
							String qy10="insert into email2 values(?)";
							pst=con.prepareStatement(qy10);
							pst.setString(1, rs.getString(1));
							pst.executeUpdate();
						}
					
						 String q10="select count(*) from rac_list"+k;
						 pst=con.prepareStatement(q10);
						 rs=pst.executeQuery();
						 rs.next();
						 if(rs.getInt(1)>0) {
							 
							 String qy2="select * from rac_list"+k;
							 pst=con.prepareStatement(qy2);
					    		rs=pst.executeQuery();
					    	
					    		rsmd=rs.getMetaData();
					    	 model=(DefaultTableModel) BookingList.table3.getModel();
					    	
					    	int rcols=rsmd.getColumnCount();
					    	String[] rcolName=new String[rcols];
					    	for(int i=0;i<rcols;i++)
					    		rcolName[i]=rsmd.getColumnName(i+1);
					    	model.setColumnIdentifiers(rcolName);
					    	
					    	String berth;
					    	while(rs.next()) {
					    		Name=rs.getString(1);
					    		berth=rs.getString(2);
					    		age=rs.getString(3);
					    	    pos=rs.getString(4);
					    		email2=rs.getString(5);
					    		gender=rs.getString(6);
					    		
					    		String[] row= {Name,berth,age,pos,email2,gender};
					    		model.addRow(row);
					    	
							}
					    	 String qy11="select distinct(email) from rac_list"+k;
								pst=con.prepareStatement(qy11);
								rs=pst.executeQuery();
								while(rs.next()) {
									String qy10="insert into email2 values(?)";
									pst=con.prepareStatement(qy10);
									pst.setString(1, rs.getString(1));
									pst.executeUpdate();
								}
							
						 }
						 String q12="select count(*) from waiting_list"+k;
						 pst=con.prepareStatement(q12);
						 rs=pst.executeQuery();
						 rs.next();
						 if(rs.getInt(1)>0) {
							 
							 String q6="select * from waiting_list"+k;
							 pst=con.prepareStatement(q6);
					    		rs=pst.executeQuery();
					    		
					    		rsmd=rs.getMetaData();
					     model=(DefaultTableModel) BookingList.table4.getModel();
					    	
					    	int wcols=rsmd.getColumnCount();
					    	String[] wcolName=new String[wcols];
					    	for(int i=0;i<wcols;i++)
					    		wcolName[i]=rsmd.getColumnName(i+1);
					    	model.setColumnIdentifiers(wcolName);
					    	
					    String berth;
					    	while(rs.next()) {
					    		Name=rs.getString(1);
					    		berth=rs.getString(2);
					    		age=rs.getString(3);
					    	    pos=rs.getString(4);
					    		email2=rs.getString(5);
					    		gender=rs.getString(6);
					    		
					    		String[] row= {Name,berth,age,pos,email2,gender};
					    		model.addRow(row);
					    	
							}
					    	 String qy11="select distinct(email) from waiting_list"+k;
								pst=con.prepareStatement(qy11);
								rs=pst.executeQuery();
								while(rs.next()) {
									String qy10="insert into email2 values(?)";
									pst=con.prepareStatement(qy10);
									pst.setString(1, rs.getString(1));
									pst.executeUpdate();
								}
						
						 }
						 
						 String qr="select distinct(email) from email2";
						 pst=con.prepareStatement(qr);
						 rs=pst.executeQuery();
						 
						 while(rs.next()) {
							 
							 String s1=rs.getString(1);
							 String qr2="select name from registration where email=?";
							 pst=con.prepareStatement(qr2);
							 pst.setString(1,s1);
							 ResultSet rs2=pst.executeQuery();
							 rs2.next();
							 String Name1=rs2.getString(1);
							 
							 int child,adult=0;
							 String qr3="select count(age) from bookedlist"+k+" where age<18 and email=?";
							 pst=con.prepareStatement(qr3);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 child=rs2.getInt(1);
							 
							 String qr4="select count(age) from bookedlist"+k+" where age>18 and email=?";
							 pst=con.prepareStatement(qr4);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 adult=rs2.getInt(1);
							 
							 String qr5="select count(age) from rac_list"+k+" where age<18 and email=?";
							 pst=con.prepareStatement(qr5);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 child=child+rs2.getInt(1);
							 
							 String qr6="select count(age) from rac_list"+k+" where age>18 and email=?";
							 pst=con.prepareStatement(qr6);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 adult=adult+rs2.getInt(1);
							 
							 String qr7="select count(age) from waiting_list"+k+" where age<18 and email=?";
							 pst=con.prepareStatement(qr7);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 child=child+rs2.getInt(1);
							 
							 String qr8="select count(age) from waiting_list"+k+" where age>18 and email=?";
							 pst=con.prepareStatement(qr8);
							 pst.setString(1,s1);
							 rs2=pst.executeQuery();
							 rs2.next();
							 adult=adult+rs2.getInt(1);
							 
							 String Total_Member=Integer.toString(child+adult);
							  String Child1=Integer.toString(child);
							 String Adult1=Integer.toString(adult);
							 
							 String qr9="select Fare from fare"+BookingList.Tid+" where id=? and date=?";
							 pst=con.prepareStatement(qr9);
							 pst.setString(1,s1);
							 pst.setString(2,BookingList.date);
							 rs2=pst.executeQuery();
							 rs2.next();
							String Total_Amount=Integer.toString(rs2.getInt(1));
							 
							 
							  model=(DefaultTableModel) BookingList.table.getModel();
						    	
						    	String[] colName2= {"Name","TrainName","TrainNo","Date","Adult","Child","TotalMember","TotalAmount"};
						    	//we set column names to our table
						    	model.setColumnIdentifiers(colName2);
							//To add rows and columns to our table at run time
							 String[] row= {Name1,BookingList.name,BookingList.Tno,BookingList.date,Adult1,Child1,Total_Member,Total_Amount};
					    		//add the rows to our table
					    		model.addRow(row);
					    	
						 }
						 String qr10="drop table email2";
				    		pst=con.prepareStatement(qr10);
				    		pst.executeUpdate();
				    		
						 BookingList.textField1.setText(BookingList.name);
						 
						SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
					
						Date d=formatter.parse(BookingList.date);
						 BookingList.dateChooser.setDate(d);
						 
						
						
				 }
				 }catch(Exception exp) {
					 System.out.println(exp);
				 }
				
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(10, 298, 79, 23);
		frame.getContentPane().add(btn1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\availability.jpg"));
		lblNewLabel.setBounds(0, 0, 743, 332);
		frame.getContentPane().add(lblNewLabel);
	}
	public void available(int Tid,String Tno,String date) {
		try {
			//To show the availability of seats
		String q="select * from train"+Tid+" where Train=? and Date1=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(q);
		pst.setString(1,Tno);
		pst.setString(2,date);
		 ResultSet rs=pst.executeQuery();
		
		ResultSetMetaData rsmd=rs.getMetaData();
   	DefaultTableModel model=(DefaultTableModel) table.getModel();
   	
   	int cols=rsmd.getColumnCount();
   	String[] colName=new String[cols];
   	for(int i=0;i<cols;i++)
   		colName[i]=rsmd.getColumnName(i+1);
   	
   	model.setColumnIdentifiers(colName);
   	
   	String Train,L,M,U,RAC,WL,Date1;
   	while(rs.next()) {
   		
   		Train=rs.getString(1);
   		L=rs.getString(2);
   		M=rs.getString(3);
   	    U=rs.getString(4);
   		RAC=rs.getString(5);
   		WL=rs.getString(6);
   		Date1=rs.getString(7);
   		
   		String[] row= {Train,L,M,U,RAC,WL,Date1};
   		//add the rows to our table
   		model.addRow(row);
   	}
		}catch(Exception exp) {
			System.out.println(exp);
		}
   	
	}
}
