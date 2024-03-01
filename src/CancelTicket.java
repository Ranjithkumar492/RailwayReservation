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
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CancelTicket {

	 JFrame CancelFrame;
	private JTextField textField3;
	String exp;
	public static String date;
	JDateChooser dateChooser;
	public static String s1;
	public static String train;
	public static int kid;
   public static JTable table;
   public static int indx;
   public static int g;
   private JTextField textField1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelTicket window = new CancelTicket();
					window.CancelFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelTicket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CancelFrame = new JFrame();
		CancelFrame.setBounds(100, 100,910,617);
		CancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CancelFrame.setLocationRelativeTo(null);
		CancelFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Cancellation Ticket");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Calibri", Font.BOLD, 25));
		label1.setBounds(343, 29, 205, 30);
		CancelFrame.getContentPane().add(label1);
		
		JLabel label4 = new JLabel("Enter Ticket Id:");
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Calibri", Font.BOLD, 18));
		label4.setBounds(256, 334, 124, 20);
		CancelFrame.getContentPane().add(label4);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(380, 333, 152, 20);
		CancelFrame.getContentPane().add(textField3);
		
	
		JButton btn1 = new JButton("Cancel");
		btn1.setForeground(Color.RED);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s1=Login.textField1.getText();
				//check if all the fields are filled
				if( textField3.getText().equals(""))
					JOptionPane.showMessageDialog(btn1, "please complete required fields");
				else {
				//check if the ticket id is valid
				String s2=textField3.getText();
				boolean flag=true;
				for(int i=0;i<s2.length();i++) {
					if(s2.charAt(i)>=47 && s2.charAt(i)<=58)
						continue;
					else {
						flag=false;
						break;
					}
				}
				if(flag==true) {
				int id=Integer.parseInt(s2);
				
				try {
				  //call the check function to validate
				check(id,s1,btn1);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else 
		JOptionPane.showMessageDialog(btn1,"Enter ticket id as integer");
				
				}
				}
			}
		);
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(411, 396, 91, 23);
		CancelFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Home");
		btn2.setForeground(Color.RED);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgressBar p=new ProgressBar();
				p.PFrame.setVisible(true);
				CancelFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(809, 11, 75, 23);
		CancelFrame.getContentPane().add(btn2);
		
		JLabel label2 = new JLabel("Train:");
		label2.setBackground(Color.WHITE);
		label2.setForeground(Color.RED);
		label2.setFont(new Font("Calibri", Font.BOLD, 18));
		label2.setBounds(280, 142, 45, 20);
		CancelFrame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Date:");
		label3.setForeground(Color.RED);
		label3.setFont(new Font("Calibri", Font.BOLD, 18));
		label3.setBounds(325, 282, 45, 14);
		CancelFrame.getContentPane().add(label3);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(380, 282, 168, 20);
		CancelFrame.getContentPane().add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(279, 173, 408, 84);
		CancelFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		scrollPane.setViewportView(table);
		
		JLabel lblTrainName = new JLabel("Train Name:");
		lblTrainName.setForeground(Color.RED);
		lblTrainName.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTrainName.setBackground(Color.WHITE);
		lblTrainName.setBounds(166, 97, 104, 20);
		CancelFrame.getContentPane().add(lblTrainName);
		
		textField1 = new JTextField();
		textField1.setBounds(280, 96, 167, 20);
		CancelFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JButton btn3 = new JButton("Search");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField1.getText().equals("")) {
					String q="select count(*) from Train where name=?";
					try {
						Connection con=DbConnection.getConnection();
						PreparedStatement pst=con.prepareStatement(q);
						pst.setString(1,textField1.getText());
						ResultSet rs=pst.executeQuery();
						rs.next();
						if(rs.getInt(1)>0) {
							table.setModel(new DefaultTableModel());
							
							String q3="select id,Tno,name from Train where name=?";
			    		    pst=con.prepareStatement(q3);
			    		    pst.setString(1, textField1.getText());
			    			 rs=pst.executeQuery();
			    			 
			    			 ResultSetMetaData rsmd=rs.getMetaData();
			    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
			    		    	
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
							
						}else
							JOptionPane.showMessageDialog(btn3, "Train not available");
					}catch(Exception exp) {
						System.out.println(exp);
					}
					
				}else
					JOptionPane.showMessageDialog(btn3,"Enter the train name");
			}
		});
		btn3.setForeground(Color.RED);
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBackground(Color.BLACK);
		btn3.setBounds(470, 97, 91, 23);
		CancelFrame.getContentPane().add(btn3);
		
		JButton btn4 = new JButton("Reset");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				String q="select id,Tno,name from Train";
				try {
				Connection con=DbConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(q);
				ResultSet rs=pst.executeQuery();
				
				 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
    		    	
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
		btn4.setForeground(Color.RED);
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBackground(Color.BLACK);
		btn4.setBounds(596, 97, 91, 23);
		CancelFrame.getContentPane().add(btn4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Cancel.jpg"));
		lblNewLabel.setBounds(0, 0, 894, 578);
		CancelFrame.getContentPane().add(lblNewLabel);
		  
	}
	//To validate the user's input
	public  void check(int id,String s1,JButton btn1) throws Exception {
		int indx=table.getSelectedRow();
		//To check if the user selected the train that he wanted to cancel the ticket
		if(indx>=0) {
			DefaultTableModel model=(DefaultTableModel) table.getModel();
		     g=Integer.parseInt(model.getValueAt(indx, 0).toString());
			train="train"+g;
			exp=model.getValueAt(indx,1).toString();
			
			//To check if user choose the date or not
			if(dateChooser.getDate()==null)
				JOptionPane.showMessageDialog(btn1,"Choose your date");
			else {
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
				date=dateFormat.format(dateChooser.getDate());
				
				//To check if the train is available on the user's given date
				String qy1="select count(*) from id where d=? and Express=?";
				Connection con = DbConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(qy1);
			   pst.setString(1, date);
			   pst.setString(2,exp);
			   ResultSet rs=pst.executeQuery();
			   rs.next();
			   if(rs.getInt(1)>0) {
				   //To get the unique id for that train
				      String qy2="select id from id where d=? and Express=?";
				      pst=con.prepareStatement(qy2);
				      pst.setString(1,date);
				      pst.setString(2, exp);
				      rs=pst.executeQuery();
				      rs.next();
				      kid=rs.getInt(1);
				      
				      //To check if the user booked the berths
				      String qy3="select count(*) from bookedlist"+kid+" where email='"+s1+"'";
				      Statement st=con.createStatement();
				      rs=st.executeQuery(qy3);
				      rs.next();
				      if(rs.getInt(1)>0) {
				    	  
				    	  //To check if the passenger is unknown
				    	  String q2="select count(*) from bookedlist"+kid+" where id="+id;
							 st=con.createStatement();
							 rs=st.executeQuery(q2);
							 rs.next();
							 int count=rs.getInt(1);
							 if(count==0) {
								 JOptionPane.showMessageDialog(btn1,"Passenger Unknown..");
							 }else {
								//To check if user enter a valid ticket id
								 String q3="select count(*) from bookedlist"+kid+" where email=? and id=?";
								 pst=con.prepareStatement(q3);
								 pst.setString(1,s1);
								 pst.setInt(2,id);
								 rs=pst.executeQuery();
								 rs.next();
								 int g=rs.getInt(1);
								 if(g>0) {
									 String q4="select * from bookedlist"+kid+" where id="+id;
									 rs=st.executeQuery(q4);
									 rs.next();
									 int T_id=rs.getInt(1);
									 int pos=rs.getInt(4);
									 String al=rs.getString(5);
									
									 CancelDAO c=new CancelDAO();
									 c.CancelDAOFrame.setVisible(true);
										CancelFrame.dispose();
										//call the cancelling function to cancel the ticket
									 CancelDAO.cancelling(T_id, pos, al, btn1,date,kid);
										
							 }else {
								 JOptionPane.showMessageDialog(btn1,"please check your ticket id that you want to cancel..");
							 }
							 }
				
				      }else
				    	  JOptionPane.showMessageDialog(btn1,"You're not allowed to cancel the ticket");
			   }else
				   JOptionPane.showMessageDialog(btn1,"Check your train and date that you've booked");
		
			}
		}else
			JOptionPane.showMessageDialog(btn1,"Select your train");
		
		

	}
}
