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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.toedter.calendar.JDateChooser;

public class CancelTicket {

	 JFrame CancelFrame;
	private JTextField textField3;
	String exp;
	public static String date;
	JList list;
	JDateChooser dateChooser;
	public static String s1;
	public static String train;
	public static int kid;
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
		label1.setBounds(343, 42, 205, 30);
		CancelFrame.getContentPane().add(label1);
		
		JLabel label4 = new JLabel("Enter Ticket Id:");
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Calibri", Font.BOLD, 18));
		label4.setBounds(254, 300, 124, 20);
		CancelFrame.getContentPane().add(label4);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(380, 299, 152, 20);
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
		btn1.setBounds(412, 342, 91, 23);
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
		label2.setBounds(325, 162, 45, 20);
		CancelFrame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Date:");
		label3.setForeground(Color.RED);
		label3.setFont(new Font("Calibri", Font.BOLD, 18));
		label3.setBounds(325, 258, 45, 14);
		CancelFrame.getContentPane().add(label3);
		
		 list = new JList();
		 list.setForeground(Color.WHITE);
		 list.setBackground(Color.BLACK);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int indx=list.getSelectedIndex();
				if(indx==0) {
					exp="12633";
					train="train1";
				}else if(indx==1) {
					exp="16182";
					train="train2";
				}else if(indx==2) {
					exp="16615";
					train="train3";
				}
				}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Calibri", Font.BOLD, 15));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"12633  KANYAKUMARI EXP", "16182  SILAMBU EXPRESS", "16615  CHENMOZHI EXP"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(380, 162, 168, 66);
		CancelFrame.getContentPane().add(list);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(380, 252, 168, 20);
		CancelFrame.getContentPane().add(dateChooser);
		
		JLabel ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Cancel.jpg"));
		ilabel.setBounds(0, 0, 894, 578);
		CancelFrame.getContentPane().add(ilabel);
		  
	}
	//To validate the user's input
	public  void check(int id,String s1,JButton btn1) throws Exception {
		int indx=list.getSelectedIndex();
		//To check if the user selected the train that he wanted to cancel the ticket
		if(indx>=0) {
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
