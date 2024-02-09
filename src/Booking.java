import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Booking {


	 JFrame BookingFrame;
	 public static String tr;
	 public static String exp;
	 public static int k;
	 public static String date2;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JButton btn2;
	public static String email;
    JTable table;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JRadioButton r1;
    JRadioButton r2;
	private JButton btn3;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	public static JLabel label10;
	private JLabel ilabel;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking window = new Booking();
					window.BookingFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Booking() {
		initialize();
	}
  
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		BookingFrame = new JFrame();
		BookingFrame.setBounds(100, 100,850,550);
		BookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BookingFrame.setLocationRelativeTo(null);
		BookingFrame.getContentPane().setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(352, 210, 125, 20);
		BookingFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(352, 241, 125, 20);
		BookingFrame.getContentPane().add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(352, 283, 125, 20);
		BookingFrame.getContentPane().add(textField3);
		
		
		JButton btn1 = new JButton("Book");
		
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//To check if all the fields are filled
			if(textField1.getText().equals("") || textField2.getText().equals("")||textField3.getText().equals(""))
				JOptionPane.showMessageDialog(btn1,"please complete required fields");
			else {
				String name=textField1.getText();
				//To check if the age is valid
				String a=textField2.getText();
				boolean flag=true;
                 for(int i=0;i<a.length();i++) {
                	if(a.charAt(i)>=47 && a.charAt(i)<=58) {
                		continue;
                	}else {
                		JOptionPane.showMessageDialog(btn1,"Age not valid, enter as integer");
                		flag=false;
                		break;
                	}
                 }
                 if(flag==true) {
                	
				int age=Integer.parseInt(a);
				String gender="";
				if(r1.isSelected() || r2.isSelected()) {
				if(r1.isSelected())
					gender="Male";
				else if(r2.isSelected())
					gender="Female";
				
				//To check if the berth is valid
				String berth=textField3.getText();
			    if((berth.length()==1)&& (berth.charAt(0)=='L' || berth.charAt(0)=='M'|| berth.charAt(0)=='U')) {
			    	
			    	//set the user email by which he/she is going to book tickets
			     email=Login.textField1.getText();
					try {
						//calling the booking function in TicketsDAO1 class
						TicketsDAO1 t=new TicketsDAO1();
						t.TicketsFrame.setVisible(true);
						BookingFrame.dispose();
						TicketsDAO1.booking(name,age,berth,email,gender,btn1,true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
						}else {
								JOptionPane.showMessageDialog(btn1,"Please enter berth as L (Lower),M (Middle) or U (Upper)");
							}
                 }else
                	 JOptionPane.showMessageDialog(btn1,"Choose the gender");
                 }
                	 
			}
			}
		});
		btn1.setBounds(370, 338, 89, 23);
		BookingFrame.getContentPane().add(btn1);
     
		btn2 = new JButton("Home");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgressBar p=new ProgressBar();
				p.PFrame.setVisible(true);
				BookingFrame.setVisible(false);
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(695, 11, 79, 23);
		BookingFrame.getContentPane().add(btn2);
		
		JLabel lblAvailableTrain = new JLabel("Available Train");
		lblAvailableTrain.setForeground(Color.WHITE);
		lblAvailableTrain.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAvailableTrain.setBounds(342, 5, 135, 33);
		BookingFrame.getContentPane().add(lblAvailableTrain);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 102, 776, 79);
		BookingFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		btn3 = new JButton("Back");
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Train t=new Train();
				t.TrainFrame.setVisible(true);
				BookingFrame.dispose();
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(10, 477, 71, 23);
		BookingFrame.getContentPane().add(btn3);
		
		 label2 = new JLabel("");
		 label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 18));
		label2.setBounds(33, 49, 249, 20);
		BookingFrame.getContentPane().add(label2);
		
		 label3 = new JLabel("");
		 label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(33, 71, 156, 20);
		BookingFrame.getContentPane().add(label3);
		
		 label4 = new JLabel("");
		 label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 12));
		label4.setBounds(181, 71, 79, 20);
		BookingFrame.getContentPane().add(label4);
		
		 label5 = new JLabel("");
		 label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(270, 71, 156, 20);
		BookingFrame.getContentPane().add(label5);
		
		label6 = new JLabel("");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(695, 71, 71, 20);
		BookingFrame.getContentPane().add(label6);
		
		label7 = new JLabel("Name:");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(296, 215, 46, 14);
		BookingFrame.getContentPane().add(label7);
		
		label8 = new JLabel("Age:");
		label8.setForeground(Color.WHITE);
		label8.setFont(new Font("Calibri", Font.BOLD, 15));
		label8.setBounds(296, 246, 46, 14);
		BookingFrame.getContentPane().add(label8);
		
		label9 = new JLabel("Berth:");
		label9.setForeground(Color.WHITE);
		label9.setFont(new Font("Calibri", Font.BOLD, 15));
		label9.setBounds(296, 288, 46, 14);
		BookingFrame.getContentPane().add(label9);
		
		label10 = new JLabel("");
		label10.setForeground(Color.WHITE);
		label10.setFont(new Font("Calibri", Font.BOLD, 18));
		label10.setBounds(593, 460, 150, 33);
		BookingFrame.getContentPane().add(label10);
		
	    r1 = new JRadioButton("Male");
	    r1.setForeground(Color.WHITE);
	    r1.setBackground(Color.BLACK);
		r1.setFont(new Font("Calibri", Font.BOLD, 15));
		r1.setHorizontalAlignment(SwingConstants.TRAILING);
		r1.setBounds(496, 231, 64, 23);
		BookingFrame.getContentPane().add(r1);
		
		JLabel label11 = new JLabel("Gender:");
		label11.setForeground(Color.WHITE);
		label11.setFont(new Font("Calibri", Font.BOLD, 15));
		label11.setBounds(509, 210, 64, 14);
		BookingFrame.getContentPane().add(label11);
		
	    r2 = new JRadioButton("Female");
	    r2.setBackground(Color.BLACK);
	    r2.setForeground(Color.WHITE);
		r2.setHorizontalAlignment(SwingConstants.TRAILING);
		r2.setFont(new Font("Calibri", Font.BOLD, 15));
		r2.setBounds(582, 231, 79, 23);
		BookingFrame.getContentPane().add(r2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\bookt2.jpg"));
		ilabel.setBounds(0, 0, 834, 511);
		BookingFrame.getContentPane().add(ilabel);
		
	}
	//To check the train on user's given date
	 public  void checking(Object from,Object To, String date) throws Exception {
		 
		 //To set the details about train
	    	if(from.equals("Chennai Egmore")) {
	    		exp="12633";
	    		tr="train1";
	    		label2.setText("12633 KANYAKUMARI EXP");
	    		label3.setText("19:00 Chennai Egmore");
	    		label4.setText("---10h 35m---");
	    		label5.setText("05:35 Kanyakumari");
	    		label6.setText("Fair: 400rs");
	    	}else if(from.equals("Sivaganga")){
	    		exp="16182";
	    		tr="train2";
	    		label2.setText("16182 SILAMBU EXPRESS");
	    		label3.setText("20:45 Sivaganga");
	    		label4.setText("---45min---");
	    		label5.setText("21:30 Karaikudi");
	    		label6.setText("Fair: 150rs");
	    	}else if(from.equals("Trichy")) {
	    		exp="16615";
	    		tr="train3";
	    		label2.setText("16615 CHENMOZHI EXP");
	    		label3.setText("23:00 Trichy");
	    		label4.setText("---5h 45m---");
	    		label5.setText("4:45 Coimbatore");
	    		label6.setText("Fair: 300rs");
	    	}
	    	try {
	    		//To check if the train is available on particular date
	    		String q1="select count(*) from "+tr+" where Date1=?";
	    		Connection con=DbConnection.getConnection();
	    		PreparedStatement pst=con.prepareStatement(q1);
	    		pst.setString(1, date);
	    		ResultSet rs=pst.executeQuery();
	    		rs.next();
	    		int g=rs.getInt(1);
	
	    		if(g>0) {
	    			//list the train details to the user
	    			String q3="select * from "+tr+" where Date1=?";
	    			PreparedStatement pst2=con.prepareStatement(q3);
	    			pst2.setString(1,date);
	    			ResultSet rs2=pst2.executeQuery();
	    		
	    			 ResultSetMetaData rsmd=rs2.getMetaData();
	    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
	    		    	
	    		    	int cols=rsmd.getColumnCount();
	    		    	String[] colName=new String[cols];
	    		    	for(int i=0;i<cols;i++)
	    		    		colName[i]=rsmd.getColumnName(i+1);
	    		    
	    		    	model.setColumnIdentifiers(colName);
	    		    	
	    		    	String train,L,M,U,RAC,WL,Date;
	    		    	while(rs2.next()) {
	    		    		train=rs2.getString(1);
	    		    		L=rs2.getString(2);
	    		    		M=rs2.getString(3);
	    		    		U=rs2.getString(4);
	    		    		RAC=rs2.getString(5);
	    		    		WL=rs2.getString(6);
	    		    		Date=rs2.getString(7);
	    		    		String[] row= {train,L,M,U,RAC,WL,Date};
	    		    		
	    		    		model.addRow(row);
	    		    	}
	    		    	//To get the unique id created for train
	    		    	String q="select id from id where d=? and Express=?";
	    		    	pst=con.prepareStatement(q);
	    		    	pst.setString(1,date);
	    		    	pst.setString(2, exp);	    		    	
	    		    	rs=pst.executeQuery();
	    		    	rs.next();
	    		    	k=rs.getInt(1);
	    		    	 	
	    		}else {
	    			
	               //To create new train on user's given date
	    			String q2="insert into "+tr+" values (?,1,1,1,2,2,?)";
	    			pst=con.prepareStatement(q2);
	    			pst.setString(1,exp);
	    			pst.setString(2,date);
	    			pst.executeUpdate();
	    			
	    			String q3="select * from "+tr+" where Date1=?";
	    			pst=con.prepareStatement(q3);
	    			pst.setString(1,date);
	    			 rs=pst.executeQuery();
	    			 
	    			 ResultSetMetaData rsmd=rs.getMetaData();
	    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
	    		    	
	    		    	int cols=rsmd.getColumnCount();
	    		    	String[] colName=new String[cols];
	    		    	for(int i=0;i<cols;i++)
	    		    		colName[i]=rsmd.getColumnName(i+1);
	    		    
	    		    	model.setColumnIdentifiers(colName);
	    		    	
	    		    	String train,L,M,U,RAC,WL,Date;
	    		    	while(rs.next()) {
	    		    		train=rs.getString(1);
	    		    		L=rs.getString(2);
	    		    		M=rs.getString(3);
	    		    		U=rs.getString(4);
	    		    		RAC=rs.getString(5);
	    		    		WL=rs.getString(6);
	    		    		Date=rs.getString(7);
	    		    		String[] row= {train,L,M,U,RAC,WL,Date};
	    		    		
	    		    		model.addRow(row);
	    		    	}
	    		    	
	    		    	//To create unique id for the train
	    		     String q="select count(*) from id";
	    		     Statement st=con.createStatement();
	    		    rs=st.executeQuery(q);
	    		     rs.next();
	    		     if(rs.getInt(1)>0) {
	    		    	 String qy2="select id from id order by id DESC limit 1";
	    		    	 rs=st.executeQuery(qy2);
	    		    	 rs.next();
	    		    	 k=rs.getInt(1)+1;
	    		    	 
	    		    	 String qy3="insert into id values(?,?,?)";
	    		    	 pst=con.prepareStatement(qy3);
	    		    	 pst.setInt(1, k);
	    		    	 pst.setString(2,date);
	    		    	 pst.setString(3, exp);
	    		    	 pst.executeUpdate();
	    		     }else {
	    		    	 k=1;
	    		    	 String qy="insert into id values(1,?,?)";
	    		    	 pst=con.prepareStatement(qy);
	    		    	 pst.setString(1,date);
	    		    	 pst.setString(2, exp);;
	    		    	  pst.executeUpdate();
	    		     }
	    		     
	    		     //create all the required tables at runtime
	    		    String q5="create table L"+k+" (L int primary key)";
	    		    st=con.createStatement();
	    		    st.executeUpdate(q5);
	    		    
	    		    for(int i=1;i<2;i++) {
	    		    	String q6="insert into L"+k+" values(?)";
	    		    	pst=con.prepareStatement(q6);
	    		    	pst.setInt(1,i);
	    		    	pst.executeUpdate();
	    		    }
	    		    
	    		    String q6="create table M"+k+" (M int primary key)";
	    		     st=con.createStatement();
	    		    st.executeUpdate(q6);
	    		    
	    		    for(int i=1;i<2;i++) {
	    		    	String q7="insert into M"+k+" values(?)";
	    		    	pst=con.prepareStatement(q7);
	    		    	pst.setInt(1,i);
	    		    	pst.executeUpdate();
	    		    }
	    		    
	    		    String q8="create table U"+k+" (U int primary key)";
	    		     st=con.createStatement();
	    		    st.executeUpdate(q8);
	    		    
	    		    for(int i=1;i<2;i++) {
	    		    	String q9="insert into U"+k+" values(?)";
	    		    	pst=con.prepareStatement(q9);
	    		    	pst.setInt(1,i);
	    		    	pst.executeUpdate();
	    		    }
	    		    
	    		    String q10="create table RAC"+k+" (RAC int primary key)";
	    		    st=con.createStatement();
	    		    st.executeUpdate(q10);
	    		    
	    		    for(int i=1;i<3;i++) {
	    		    	String q11="insert into RAC"+k+" values(?)";
	    		    	pst=con.prepareStatement(q11);
	    		    	pst.setInt(1,i);
	    		    	pst.executeUpdate();
	    		    }
	    		    
	    		    String q12="create table WL"+k+" (WL int primary key)";
	    		     st=con.createStatement();
	    		    st.executeUpdate(q12);
	    		    
	    		    for(int i=1;i<3;i++) {
	    		    	String q13="insert into WL"+k+" values(?)";
	    		    	pst=con.prepareStatement(q13);
	    		    	pst.setInt(1,i); 
	    		    	pst.executeUpdate();
	    		    }
	    		     
	    		    String q13="create table bookedlist"+k+"(id int primary key,name varchar(30),age int,pos int, alloted varchar(20),email varchar(40),gender varchar(20))";
                     st=con.createStatement();
                     st.executeUpdate(q13);
                     
                   String q14="create table Rac_list"+k+"(name varchar(30),berth varchar(20),age int,Rac_pos int primary key,email varchar(40),gender varchar(20))";
                   st=con.createStatement();
                   st.executeUpdate(q14);
                   
                   String q15="create table waiting_list"+k+"(name varchar(30),berth varchar(20),age int,Wl_pos int primary key,email varchar(40),gender varchar(20))";
	               st=con.createStatement();
	               st.executeUpdate(q15);
	               
	               //call the find function to set the time
	               find();	 
		}
	    
	 }catch(Exception e) {
 		System.out.println(e);
 	}
	    	date2=date;
	    	  
	 }
	 //function to set the timer
	 public void time(String train,String date1,boolean flag) {
		 
		 Timer timer=new Timer();
		 TimerTask task=new TimerTask() {
			 public void run(){
				 try {
					Connection con=DbConnection.getConnection();
					
							String q3="select id from id where d=? and Express=?";
							PreparedStatement pst=con.prepareStatement(q3);
							pst.setString(1,date1);
							pst.setString(2, train);
							ResultSet rs2=pst.executeQuery();
							rs2.next();
							int kid=rs2.getInt(1);
							
							String q4="select count(*) from bookedlist"+kid;
							pst=con.prepareStatement(q4);
							ResultSet rs3=pst.executeQuery(q4);
							rs3.next();
							int b=rs3.getInt(1);
							
							String q5="select count(*) from rac_list"+kid;
							pst=con.prepareStatement(q5);
							rs3=pst.executeQuery();
							rs3.next();
							int r=rs3.getInt(1);
							
							String q6="select count(*) from waiting_list"+kid;
							PreparedStatement pst2=con.prepareStatement(q6);
							ResultSet r1=pst2.executeQuery();
							 r1.next();
							int w=r1.getInt(1);
							
							if(b>0 || r>0 || w>0) {
								//call the method unique
								unique(b,r,w,kid,train,date1);
							}else
								drop(train,kid,date1,false);
				
		
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			 }
		 };
		 
		 //To set the timer for final confirmation
		 int year,month,da2=0;
		if(flag==false) {
			String y="",da="",m="";
			for(int i=0;i<date1.length();i++) {
				if(date1.charAt(i)=='-') 
					continue;
				else if(da.length()!=2)
					da=da+date1.charAt(i);
				else if(m.length()!=2)
					m=m+date1.charAt(i);
				else if(y.length()!=4)
					y=y+date1.charAt(i);
			}
			 year=Integer.parseInt(y);
			 month=Integer.parseInt(m);
			 da2=Integer.parseInt(da);
			
		}else {
			year=Train.year2;
			month=Train.month2;
			da2=Train.da4;
		}
		 
		 if(train.equals("12633")) {
			 
		 int k=month-1;
		 Calendar date=Calendar.getInstance();
		 date.set(Calendar.YEAR,year);
		 date.set(Calendar.MONTH,k);
		 date.set(Calendar.DAY_OF_MONTH,da2);
		 date.set(Calendar.HOUR,6);
		 date.set(Calendar.MINUTE,0);
		 date.set(Calendar.SECOND,0);
		 date.set(Calendar.MILLISECOND,0);
		 
		 System.out.println(date.getTime());
		 
		 //execute the task on particular time which we set
		 timer.schedule(task,date.getTime());
		 
		 }else if(train.equals("16182")) {
			 int k=month-1;
			 Calendar date=Calendar.getInstance();
			 date.set(Calendar.YEAR,year);
			 date.set(Calendar.MONTH,k);
			 date.set(Calendar.DAY_OF_MONTH,da2);
			 date.set(Calendar.HOUR,8);
			 date.set(Calendar.MINUTE,0);
			 date.set(Calendar.SECOND,0);
			 date.set(Calendar.MILLISECOND,0);
			 
			 System.out.println(date.getTime());
			 timer.schedule(task,date.getTime());
			 
		 }else if(train.equals("16615")) {
			 int k=month-1;
			 Calendar date=Calendar.getInstance();
			 date.set(Calendar.YEAR,year);
			 date.set(Calendar.MONTH,k);
			 date.set(Calendar.DAY_OF_MONTH,da2);
			 date.set(Calendar.HOUR,10);
			 date.set(Calendar.MINUTE,0);
			 date.set(Calendar.SECOND,0);
			 date.set(Calendar.MILLISECOND,0);
			 
			 System.out.println(date.getTime());
			 timer.schedule(task,date.getTime());
		 }
	 }
	 public void unique(int b,int r,int w,int kid,String train,String date) throws Exception {
		 Connection con2=DbConnection.getConnection();
			if(b>0) {
				String q7="create table email1(email varchar(40))";
				
				PreparedStatement pst=con2.prepareStatement(q7);
				pst.executeUpdate();
				
				String q8="select count(distinct(email)) from bookedlist"+kid;
				pst=con2.prepareStatement(q8);
				ResultSet rs5=pst.executeQuery();
				rs5.next();
				int l=rs5.getInt(1);
				
				//To get the unique email from bookedlist
				String qy9="select distinct(email) from bookedlist"+kid;
				pst=con2.prepareStatement(qy9);
				rs5=pst.executeQuery();
				
				for(int i=0;i<l;i++) {
					if(rs5.next()) {
						String qy10="insert into email1 values(?)";
						pst=con2.prepareStatement(qy10);
						pst.setString(1, rs5.getString(1));
						pst.executeUpdate();
					}
				}
			}
			if(r>0) {
				String qr1="select count(distinct(email)) from rac_list"+kid;
				PreparedStatement pst2=con2.prepareStatement(qr1);
				ResultSet r1=pst2.executeQuery();
				r1.next();
				int m=r1.getInt(1);
				
				//To get the unique email from the rac_list
				String q9="select distinct(email) from rac_list"+kid;
			 pst2=con2.prepareStatement(q9);
				ResultSet rs2=pst2.executeQuery();
				
				for(int i=0;i<m;i++) {
					if(rs2.next()) {
					String r2="insert into email1 values(?)";
					pst2=con2.prepareStatement(r2);
					pst2.setString(1,rs2.getString(1));
					pst2.executeUpdate();
					}
				}
			}
			if(w>0) {
				String qr3="select count(distinct(email)) from waiting_list"+kid;
				PreparedStatement pst3=con2.prepareStatement(qr3);
				ResultSet r2=pst3.executeQuery();
				r2.next();
				int n=r2.getInt(1);
				
				//To get the unique email from the waiting_list
				String q11="select distinct(email) from waiting_list"+kid;
				 pst3=con2.prepareStatement(q11);
				ResultSet rs3=pst3.executeQuery();
				
				for(int i=0;i<n;i++) {
					if(rs3.next()) {
						String qr="insert into email1 values(?)";
						pst3=con2.prepareStatement(qr);
						pst3.setString(1, rs3.getString(1));
						pst3.executeUpdate();
					}
				}
			}
	         String q14="select count(distinct(email)) from email1";
	        PreparedStatement pst3=con2.prepareStatement(q14);
	       ResultSet rs4= pst3.executeQuery();
	       rs4.next();
	       int count=rs4.getInt(1);
	       
	       //To get the unique the emails from overall
			String q13="select distinct(email) from email1";
			 pst3=con2.prepareStatement(q13);
			 rs4=pst3.executeQuery();
			 
			 //generate the final confirmation for each unique mail id
			for(int i=0;i<count;i++) {
				rs4.next();
				String email=rs4.getString(1);
				
				//To get detail about user
				String qy3="select name,gender from registration where email=?";
				pst3=con2.prepareStatement(qy3);
				pst3.setString(1,email);
				ResultSet r3=pst3.executeQuery();
				r3.next();
				String name=r3.getString(1);
				String gender=r3.getString(2);
				String gd="";
				if(gender.equals("Male"))
					gd="Mr.";
				else if(gender.equals("Female"))
					gd="Ms.";
				
				//set the train details
				String exp="";
				int fr=0;
				if(train.equals("12633")) {
					 exp="12633 KANYAKUMARI EXP";
					 fr=1;
				}else if(train.equals("16182")) {
					exp="16182 SILAMBU EXPRESS";
					fr=2;
				}else if(train.equals("16615")) {
					exp="16615 CHENMOZHI EXP";
					fr=3;
				}
				String s1="Hi "+gd+name+", final status of your bookings \nTrain: "+exp+" \nDate: "+date+" \n";
				
				String qy="select count(*) from bookedlist"+kid+" where email=?";
				pst3=con2.prepareStatement(qy);
				pst3.setString(1,email);
				ResultSet r2=pst3.executeQuery();
				r2.next();
				if(r2.getInt(1)>0) {
                    //final confirmation for berths
					String qy2="select * from bookedlist"+kid+" where email=?";
					pst3=con2.prepareStatement(qy2);
					pst3.setString(1,email);
					r2=pst3.executeQuery();
					
					while(r2.next()) {
						String n1=r2.getString(2);
						int pos=r2.getInt(4);
						String al=r2.getString(5);
						String gr=r2.getString(7);
						String g="";
						if(gr.equals("Male"))
							g="Mr.";
						else if(gr.equals("Female"))
							g="Ms.";
						
						s1=s1+"berth "+al+pos+" is confirmed for "+g+n1+"\n";
					}
				}
				
				//To check if the user booked rac ticket
				String qy4="select count(*) from rac_list"+kid+" where email='"+email+"'";
				PreparedStatement ps=con2.prepareStatement(qy4);
				ResultSet rt=ps.executeQuery();
				rt.next();
				if(rt.getInt(1)>0) {
					//final confirmation of rac ticket
					String qy5="select * from rac_list"+kid+" where email=?";
					ps=con2.prepareStatement(qy5);
					ps.setString(1,email);
					rt=ps.executeQuery();
					while(rt.next()) {
						String n1=rt.getString(1);
						int pos=rt.getInt(4);
						String gr=rt.getString(6);
						String g="";
						if(gr.equals("Male"))
							g="Mr.";
						else if(gr.equals("Female"))
							g="Ms.";
						
						s1=s1+"Rac"+pos+" is confirmed for "+g+n1+"\n";
					}
				}
				
				//To check if the user booked waiting list ticket
				String qy5="select count(*) from waiting_list"+kid+" where email=?";
				PreparedStatement ps2=con2.prepareStatement(qy5);
				ps2.setString(1,email);
				ResultSet rt2=ps2.executeQuery();
				rt2.next();
				int c1=rt2.getInt(1);
				if(rt2.getInt(1)>0) {
					String qy6="select * from waiting_list"+kid+" where email=?";
					ps2=con2.prepareStatement(qy6);
					ps2.setString(1,email);
					rt2=ps2.executeQuery();
					while(rt2.next()) {
						String n1=rt2.getString(1);
						String gr=rt2.getString(6);
						String g="";
						if(gr.equals("Male"))
							g="Mr.";
						else if(gr.equals("Female"))
							g="Ms.";
						
						s1=s1+"sorry, ticket didn't get booked for "+g+n1+"..your payment will be refunded\n";
					}
				}
				
				//To get the fare for user
				String q="select Fare,cancel from fare"+fr+" where id=? and date=?";
				ps2=con2.prepareStatement(q);
				ps2.setString(1,email);
				ps2.setString(2,date);
				rt2=ps2.executeQuery();
				rt2.next();
				int c=rt2.getInt(2);
				int m=0;
				if(fr==1) 
					m=400;
				else if(fr==2)
					m=150;
				else if(fr==3)
					m=300;
				
				int fare=rt2.getInt(1)-c1*m;
				int rf=0;
				s1=s1+"Total Fare: "+fare+"rs\n";
				
				if(c>0) {
					s1=s1+"Refunded Amount of Cancellation: "+c+"rs";
				}
				if(c1>0) {
					 rf=c1*m;
					 s1=s1+"\nRefunded Amount of waiting_list: "+rf+"rs";
				}
				System.out.println(s1);
				
				//To send the mail for user
				Email em=new Email();
				em.confirmMail(email,s1);
			}
			
			drop(train,kid,date,true);
		  }
 

   public void find() throws Exception {
	   String date="";
	   //To get the Today's date
  	   if(Train.month2<=9 && Train.da4<=9)
  			 date="0"+Train.da4+"-0"+Train.month2+"-"+Train.year2;
         else if(Train.da4<=9)
        	 date="0"+Train.da4+"-"+Train.month2+"-"+Train.year2;
         else if(Train.month2<=9)
        	 date=Train.da4+"-0"+Train.month2+"-"+Train.year2;
         else 
        	 date=Train.da4+"-"+Train.month2+"-"+Train.year2;

  	   
  	   //To check if the train is available on today's date
  	   String q1="select count(*) from train1 where Date1=?";
  	   Connection con=DbConnection.getConnection();
  	   PreparedStatement pst=con.prepareStatement(q1);
  	   pst.setString(1,date);
  	   ResultSet rs=pst.executeQuery();
  	   rs.next();
 
  	   if((rs.getInt(1)>0)) {
  		   //call the time function to set timer
  		   time("12633",date,true);
  	   }
       
  	 String q2="select count(*) from train2 where Date1=?";
	    con=DbConnection.getConnection();
	    pst=con.prepareStatement(q2);
	   pst.setString(1,date);
	    rs=pst.executeQuery();
	   rs.next();
	   if((rs.getInt(1)>0)) {
		   time("16182",date,true);
	   }
	   
	   String q3="select count(*) from train3 where Date1=?";
	    con=DbConnection.getConnection();
	    pst=con.prepareStatement(q3);
	   pst.setString(1,date);
	    rs=pst.executeQuery();
	   rs.next();
	   if((rs.getInt(1)>0)) {
		   time("16615",date,true);
	   }
	   
   }
   
   public void drop(String train,int kid,String date,boolean flag) throws Exception {
	 //here we drop the tables that no longer in use
	   
	   String qy2="drop table bookedlist"+kid;
	   Connection con2=DbConnection.getConnection();
	   PreparedStatement pst=con2.prepareStatement(qy2);
	   pst.executeUpdate();
	   
	   String qy3="drop table rac_list"+kid;
	   pst=con2.prepareStatement(qy3);
	   pst.executeUpdate();
	   
	   String qy4="drop table waiting_list"+kid;
	   pst=con2.prepareStatement(qy4);
	   pst.executeUpdate();
	   
	   String qy5="drop table l"+kid;
	   pst=con2.prepareStatement(qy5);
	   pst.executeUpdate();
	   
	   String qy6="drop table m"+kid;
	   pst=con2.prepareStatement(qy6);
	   pst.executeUpdate();
	   
	   String qy7="drop table u"+kid;
	   pst=con2.prepareStatement(qy7);
	   pst.executeUpdate();
	   String qy8="drop table rac"+kid;
	   pst=con2.prepareStatement(qy8);
	   pst.executeUpdate();
	   String qy9="drop table wl"+kid;
	   pst=con2.prepareStatement(qy9);
	   pst.executeUpdate();
	   
	   int fe=0;
	   if(train.equals("12633")) {
		   fe=1;
		   String qy10="delete from train1 where Date1=?";
		   pst=con2.prepareStatement(qy10);
		   pst.setString(1,date);
		   pst.executeUpdate();
	   }else if(train.equals("16182")) {
		   fe=2;
		   String qy11="delete from train2 where Date1=?";
		   PreparedStatement pst1=con2.prepareStatement(qy11);
		   pst1.setString(1,date);
		   pst1.executeUpdate();
	   }else if(train.equals("16615")) {
		   fe=3;
		   String qy12="delete from train3 where Date1=?";
		   PreparedStatement pst2=con2.prepareStatement(qy12);
		   pst2.setString(1,date);
		   pst2.executeUpdate();
	   }
	   String qy11="delete from id where id="+kid;
	   pst=con2.prepareStatement(qy11);
	   pst.executeUpdate(); 
	   
	   if(flag==true) {
		   String qy="drop table email1";
		   pst=con2.prepareStatement(qy);
		   pst.executeUpdate();
		   
	   String qy12="delete from fare"+fe+" where date=?";
	   pst=con2.prepareStatement(qy12);
	   pst.setString(1,date);
	   pst.executeUpdate();
	   }
	   
	   System.out.println("Task is completed");
   }
}