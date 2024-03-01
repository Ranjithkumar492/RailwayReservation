import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class BookingList {

    JFrame frame;
    public static JTable table;
    public static JTextField textField1;
    public static JDateChooser dateChooser;
    public static JTable table2;
    public static JTable table3;
    public static JTable table4;
    public static String Tno;
    public static String date;
    public static  String name;
    public static int k;
    public static int Tid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingList window = new BookingList();
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
	public BookingList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Booking List:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(51, 11, 87, 20);
		frame.getContentPane().add(label1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 107, 710, 79);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblTrainName = new JLabel("Train Name:");
		lblTrainName.setForeground(Color.WHITE);
		lblTrainName.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTrainName.setBounds(51, 42, 87, 20);
		frame.getContentPane().add(lblTrainName);
		
		textField1 = new JTextField();
		textField1.setBounds(148, 40, 131, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel label3 = new JLabel("Date:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(350, 42, 42, 20);
		frame.getContentPane().add(label3);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(403, 42, 111, 20);
		frame.getContentPane().add(dateChooser);
		
		JButton btn1 = new JButton("search");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  name=textField1.getText();
				if(!name.equals("")) {
				if(dateChooser.getDate()==null)
					JOptionPane.showMessageDialog(btn1,"Choose your date");
				else {
	
				try {
					//To check if the train is available or not
					String q1="select count(*) from Train where name=?";
					Connection con=DbConnection.getConnection();
					PreparedStatement pst=con.prepareStatement(q1);
					pst.setString(1, name);
					ResultSet rs=pst.executeQuery();
					rs.next();
					if(rs.getInt(1)>0) {
						String q2="select Tno,id from Train where name=?";
						 pst=con.prepareStatement(q2);
						pst.setString(1, name);
						 rs=pst.executeQuery();
						 rs.next();
						 Tno=rs.getString(1);
						 Tid=rs.getInt(2);
						 
						 SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
						 
							 date=dateFormat.format(dateChooser.getDate());
						
							 //To check if the train has bookings
						 String q3="select count(id) from id where d=? and Express=?";
						 pst=con.prepareStatement(q3);
							pst.setString(1,date);
							pst.setString(2,Tno);
							 rs=pst.executeQuery();
							 rs.next();
							 if(rs.getInt(1)>0) {
								 String q4="select id from id where d=? and Express=?";
								 pst=con.prepareStatement(q4);
									pst.setString(1,date);
									pst.setString(2,Tno);
									 rs=pst.executeQuery();
									 rs.next();
									  k=rs.getInt(1);
									 
									  //To check if the train has bookings
									 String q5="select count(*) from bookedlist"+k;
									 pst=con.prepareStatement(q5);
									 rs=pst.executeQuery();
									 rs.next();
									 if(rs.getInt(1)>0) {
										 
										 //To show berth list 
										 String qy="select * from bookedlist"+k;
								    		pst=con.prepareStatement(qy);
								    		rs=pst.executeQuery();
								    		
								    		//it contains information of no of rows,columns and column names
								    	ResultSetMetaData rsmd=rs.getMetaData();
								    	//To add rows and columns to our table at run time
								    	DefaultTableModel model=(DefaultTableModel) table2.getModel();
								    	
								    	//It will give the column count of the table
								    	int cols=rsmd.getColumnCount();
								    	String[] colName=new String[cols];
								    	for(int i=0;i<cols;i++)
								    		colName[i]=rsmd.getColumnName(i+1);
								    	//we set column names to our table
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
								    		//add the rows to our table
								    		model.addRow(row);
								    	}
										
										
									    	//create separate table to extract unique email id
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
											
											//To check if RAC has bookings
											 String q10="select count(*) from rac_list"+k;
											 pst=con.prepareStatement(q10);
											 rs=pst.executeQuery();
											 rs.next();
											 if(rs.getInt(1)>0) {
												 
												 String qy2="select * from rac_list"+k;
												 pst=con.prepareStatement(qy2);
										    		rs=pst.executeQuery();
										    	
										    		rsmd=rs.getMetaData();
										    	 model=(DefaultTableModel) table3.getModel();
										    	
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
											 
											 //To check if waiting list has bookings
											 String q12="select count(*) from waiting_list"+k;
											 pst=con.prepareStatement(q12);
											 rs=pst.executeQuery();
											 rs.next();
											 if(rs.getInt(1)>0) {
												 
												 String q6="select * from waiting_list"+k;
												 pst=con.prepareStatement(q6);
										    		rs=pst.executeQuery();
										    		
										    		rsmd=rs.getMetaData();
										     model=(DefaultTableModel) table4.getModel();
										    	
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
											 //To get unique email id from all over the bookings
											 String qr="select distinct(email) from email2";
											 pst=con.prepareStatement(qr);
											 rs=pst.executeQuery();
											 
											 while(rs.next()) {
												 String s1=rs.getString(1);
												 //To get the name of the user
												 String qr2="select name from registration where email=?";
												 pst=con.prepareStatement(qr2);
												 pst.setString(1,s1);
												 ResultSet rs2=pst.executeQuery();
												 rs2.next();
												 String Name1=rs2.getString(1);
												
												 //To calculate adult,child
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
												 
												 //To calculate adult,child,total member
												 String Total_Member=Integer.toString(child+adult);
												  String Child1=Integer.toString(child);
												 String Adult1=Integer.toString(adult);
												
												 //To calculate Total fare of the use
												 String qr9="select Fare from fare"+Tid+" where id=? and date=?";
												 pst=con.prepareStatement(qr9);
												 pst.setString(1,s1);
												 pst.setString(2,date);
												 rs2=pst.executeQuery();
												 rs2.next();
												String Total_Amount=Integer.toString(rs2.getInt(1));
												
												//To set the details into table
												  model=(DefaultTableModel) table.getModel();
											    	
											    	String[] colName2= {"Name","TrainName","TrainNo","Date","Adult","Child","TotalMember","TotalAmount"};
											    	//we set column names to our table
											    	model.setColumnIdentifiers(colName2);
												//To add rows and columns to our table at run time
												 String[] row= {Name1,name,Tno,date,Adult1,Child1,Total_Member,Total_Amount};
										    		//add the rows to our table
										    		model.addRow(row);
										    	
											 }
											 //drop the table which created to get unique email id
											 String qr10="drop table email2";
									    		pst=con.prepareStatement(qr10);
									    		pst.executeUpdate();
											 
									    		
									 }else
										 JOptionPane.showMessageDialog(btn1, "Not booked yet..");
							 }else
								 JOptionPane.showMessageDialog(btn1, "Not booked yet..");
					}else
						JOptionPane.showMessageDialog(btn1, "Train not available");
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
			}else
				JOptionPane.showMessageDialog(btn1, "choose the train");
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(298, 73, 87, 23);
		frame.getContentPane().add(btn1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 228, 710, 79);
		frame.getContentPane().add(scrollPane_1);
		
		table2 = new JTable();
		table2.setForeground(Color.WHITE);
		table2.setBackground(Color.BLACK);
		scrollPane_1.setViewportView(table2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(41, 341, 710, 73);
		frame.getContentPane().add(scrollPane_3);
		
		table3 = new JTable();
		table3.setForeground(Color.WHITE);
		table3.setBackground(Color.BLACK);
		scrollPane_3.setViewportView(table3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(41, 443, 710, 73);
		frame.getContentPane().add(scrollPane_2);
		
		table4 = new JTable();
		table4.setForeground(Color.WHITE);
		table4.setBackground(Color.BLACK);
		scrollPane_2.setViewportView(table4);
		
		JLabel label4 = new JLabel("Berth List:");
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(51, 207, 87, 20);
		frame.getContentPane().add(label4);
		
		JLabel label5 = new JLabel("Rac_list:");
		label5.setForeground(Color.RED);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(51, 318, 87, 20);
		frame.getContentPane().add(label5);
		
		JLabel label6 = new JLabel("Waiting_list:");
		label6.setForeground(Color.RED);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(51, 425, 87, 20);
		frame.getContentPane().add(label6);
		
		JButton btn2 = new JButton("Clear");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				table2.setModel(new DefaultTableModel());
				table3.setModel(new DefaultTableModel());
				table4.setModel(new DefaultTableModel());
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(413, 73, 87, 23);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("Availablity");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textField1.getText().equals("")) {
					if(dateChooser.getDate()==null)
						JOptionPane.showMessageDialog(frame,"Choose your date");
					else {
						 SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
						 date=dateFormat.format(dateChooser.getDate());
						try {
							Connection con=DbConnection.getConnection();
							//To check if the train is available or not
							String q1="select count(*) from Train where name=?";
							
							PreparedStatement pst=con.prepareStatement(q1);
							pst.setString(1,textField1.getText());
							ResultSet rs=pst.executeQuery();
							rs.next();
							if(rs.getInt(1)>0) {
								
							 String q2="select Tno from Train where name=?";
							pst=con.prepareStatement(q2);
							pst.setString(1, textField1.getText());
							 rs=pst.executeQuery();
							 rs.next();
							 Tno=rs.getString(1);
							
							 String q3="select count(id) from id where d=? and Express=?";
							 pst=con.prepareStatement(q3);
								pst.setString(1,date);
								pst.setString(2,Tno);
								  rs=pst.executeQuery();
								 rs.next();
						if(rs.getInt(1)>0) {
							
							String q4="select id from id where d=? and Express=?";
							 pst=con.prepareStatement(q4);
								pst.setString(1,date);
								pst.setString(2,Tno);
								 rs=pst.executeQuery();
								 rs.next();
								  k=rs.getInt(1);
							
					 Availability a=new Availability();
					 a.available(Tid,Tno,date);
				     a.frame.setVisible(true);
					frame.dispose();
						}else 
							JOptionPane.showMessageDialog(frame, "check the train and date");
						
							}else
								JOptionPane.showMessageDialog(frame, "Train not available");
				}catch(Exception exp) {
					System.out.println(exp);
				}
					}
			}else
				JOptionPane.showMessageDialog(frame, "choose the train");
				}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(640, 22, 111, 23);
		frame.getContentPane().add(btn3);
		
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
		btn4.setBounds(10, 527, 73, 23);
		frame.getContentPane().add(btn4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\bookinglist.jpg"));
		lblNewLabel.setBounds(0, 0, 977, 561);
		frame.getContentPane().add(lblNewLabel);
	}
}
