import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class TicketsDAO1 {

	 JFrame TicketsFrame;
   public static String train;
   public static int kid;
   public static String date;
   public static String Express;
   public static int total;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketsDAO1 window = new TicketsDAO1();
					window.TicketsFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicketsDAO1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TicketsFrame = new JFrame();
		
		TicketsFrame.getContentPane().addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				
			}
		});
		TicketsFrame.setBounds(100, 100, 745, 510);
		TicketsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TicketsFrame.setLocationRelativeTo(null);
		TicketsFrame.getContentPane().setLayout(null);
		
		JButton btn2 = new JButton("Back");
		btn2.setBackground(Color.LIGHT_GRAY);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking b=new Booking();
				b.BookingFrame.setVisible(true);
				TicketsFrame.dispose();
				
				String q3="select * from "+train+" where Date1=?";
				try {
				Connection con=DbConnection.getConnection();
    			PreparedStatement pst2=con.prepareStatement(q3);
    			pst2.setString(1,date);
    			ResultSet rs2=pst2.executeQuery();
    		
    			 ResultSetMetaData rsmd=rs2.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) b.table.getModel();
    		    	
    		    	int cols=rsmd.getColumnCount();
    		    	String[] colName=new String[cols];
    		    	for(int i=0;i<cols;i++)
    		    		colName[i]=rsmd.getColumnName(i+1);
    		    
    		    	model.setColumnIdentifiers(colName);
    		    	
    		    	String tr,L,M,U,RAC,WL,Date;
    		    	while(rs2.next()) {
    		    		tr=rs2.getString(1);
    		    		L=rs2.getString(2);
    		    		M=rs2.getString(3);
    		    		U=rs2.getString(4);
    		    		RAC=rs2.getString(5);
    		    		WL=rs2.getString(6);
    		    		Date=rs2.getString(7);
    		    		String[] row= {tr,L,M,U,RAC,WL,Date};
    		    		
    		    		model.addRow(row);
    		    	}
    		    	
    		        con=DbConnection.getConnection();
    	    		String qr="select * from Train where Tno=?";
    	    		PreparedStatement pst=con.prepareStatement(qr);
    	    		pst.setString(1,Train.number);
    	    		ResultSet r=pst.executeQuery();
    	    		r.next();
    	    		
    		    	String exp=r.getString(2);
    	    		b.label2.setText(exp+" "+r.getString(3));
    	    		b.label3.setText(r.getString(6)+" "+r.getString(4));
    	    		b.label5.setText(r.getString(7)+" "+r.getString(5));
    	    		b.label6.setText("Fair: "+r.getInt(13));
    		    	
    		    	Booking.label10.setText("Total Fare: "+total+"rs");
    		    	
				}catch(Exception exc) {
					System.out.println(exc);
				}
				
				
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 437, 72, 23);
		TicketsFrame.getContentPane().add(btn2);
		
		JLabel Blabel = new JLabel("");
		Blabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\TicketDAO2.jpg"));
		Blabel.setBounds(0, 61,728,410);
		TicketsFrame.getContentPane().add(Blabel);
		
		JButton btn1 = new JButton("Home");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgressBar p=new ProgressBar();
				p.PFrame.setVisible(true);
				TicketsFrame.dispose();
				}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setForeground(Color.BLACK);
		btn1.setBackground(Color.LIGHT_GRAY);
		btn1.setBounds(647, 11, 72, 23);
		TicketsFrame.getContentPane().add(btn1);
		
		JLabel label1 = new JLabel("Welcome to Booking");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 25));
		label1.setBounds(240, 9, 225, 27);
		TicketsFrame.getContentPane().add(label1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 728, 61);
		TicketsFrame.getContentPane().add(panel);
		

		 
	}
	//To book the waiting list ticket
	public static void bookingWl(String name,int age,String berth,int cap4,int pos,String email,String gender,JButton btn1,int kid,String train,String date) throws Exception {
		
		String q3="insert into Waiting_list"+kid+" values(?,?,?,?,?,?)";
      Connection con= DbConnection.getConnection(); 
      PreparedStatement pst=con.prepareStatement(q3);
      pst.setString(1,name);
      pst.setString(2,berth);
      pst.setInt(3,age);
      pst.setInt(4,pos);
      pst.setString(5,email);
      pst.setString(6,gender);
      pst.executeUpdate();
 
       int k=cap4-1;
       String q7="update "+train+" set WL=? where Date1=?";
        pst=con.prepareStatement(q7);
       pst.setInt(1, k);
       pst.setString(2,date);;
       pst.executeUpdate();
 
      String q8="delete from WL"+kid+" where WL="+pos;
      Statement st=con.createStatement();
       st.executeUpdate(q8);
       JOptionPane.showMessageDialog(btn1,"Available WL"+pos+" booked successfully.."); 
       fare(train,email);
	}
	//To book the rac ticket
	public static void bookingRac(String name,int age,String berth,int cap4,String email,String gender,JButton btn1,boolean flag,int kid,String train,String date) throws Exception {
		 String q5="select * from RAC"+kid+" limit 1";
		 Connection con= DbConnection.getConnection(); 
		   Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(q5);
		  rs.next();
		  int pos=rs.getInt(1);
		  
		String q3="insert into Rac_list"+kid+" values(?,?,?,?,?,?)";
		 PreparedStatement pst=con.prepareStatement(q3);
		 pst.setString(1,name);
		 pst.setString(2,berth);
		 pst.setInt(3,age);
		 pst.setInt(4,pos);
		 pst.setString(5,email);
		 pst.setString(6,gender);
		 pst.executeUpdate();
		 
		 int k=cap4-1;
		  String q7="update "+train+" set RAC=? where Date1=?";
		   pst=con.prepareStatement(q7);
		  pst.setInt(1, k);
		  pst.setString(2,date);
		  pst.executeUpdate();
		 
		  String q8="delete from RAC"+kid+" where RAC="+pos;
		     st=con.createStatement();
		     st.executeUpdate(q8);
		     
		     if(flag==true) {
		     JOptionPane.showMessageDialog(btn1,"Available RAC"+pos+" booked successfully.."); 
		     fare(train,email);
		     }
		     
	}
	//To book the available berths
	public static void booking2(String name,int age,String berth,int cap,JButton btn1,String email,String gender,boolean flag,String train,String date,int kid) throws Exception {
		 
		 Connection con= DbConnection.getConnection(); 
		 String q5="select * from "+berth+kid+" limit 1";
		  Statement st=con.createStatement();
		 ResultSet  rs=st.executeQuery(q5);
		  rs.next();
		  int pos=rs.getInt(1);
		
		  bookedList(name,age,pos,berth,email,gender,kid,date);
		  
		  int k=cap-1;
		  String q7="update "+train+" set "+berth+"=? where Date1=?";
		 PreparedStatement pst=con.prepareStatement(q7);
		  pst.setInt(1, k);
		  pst.setString(2,date);
		  pst.executeUpdate();
		 
		  String q8="delete from "+berth+kid+" where "+berth+"="+pos;
		     st=con.createStatement();
		     st.executeUpdate(q8);
		 
		     if(flag==true) {
		     JOptionPane.showMessageDialog(btn1,"Available "+berth+pos+" berth booked successfully.."); 
		     fare(train,email);
		     }
		   
		     if(flag==false) {

		    	 String qy="select Train from "+train+" where Date1=?";
		    	 pst=con.prepareStatement(qy);
		    	 pst.setString(1,date);
		    	 rs=pst.executeQuery();
		    	 rs.next();
		    	 String Express=rs.getString(1);
		    	 
		    	 String qy2="select name from Train where Tno=?";
		    	 pst=con.prepareStatement(qy2);
		    	 pst.setString(1, Express);
		    	 rs=pst.executeQuery();
		    	 rs.next();
		    	 Express=Express+" "+rs.getString(1);
		    	 
				   String berth2=berth+pos;
				   Email t=new Email();
				   t.sendMail(name,berth2,email,date,Express,gender);
			   }
		  }
	
	//insert the user's data into table
public static void bookedList(String name,int age,int pos,String alloted,String email,String gender,int kid,String date) throws Exception {
	//To generate unique id for passengers
	
                String q7="select count(id) from bookedList"+kid;
			 Connection con= DbConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q7);
			rs.next();
			int count=rs.getInt(1);
			int pId=0;
			if(count>0) {
			String q8="select id from bookedList"+kid+" order by id DESC Limit 1";
	       rs=st.executeQuery(q8);
			rs.next();
			int g=rs.getInt(1)+1;
		      pId=g;
			}else {
				pId=1;
			}
			
		String q6="insert into bookedList"+kid+" values(?,?,?,?,?,?,?)";
		con= DbConnection.getConnection();
		 PreparedStatement pst=con.prepareStatement(q6);
		 pst.setInt(1,pId);
		 pst.setString(2,name);
		 pst.setInt(3,age);
		 pst.setInt(4, pos);
		 pst.setString(5,alloted);
		 pst.setString(6,email);
		 pst.setString(7,gender);
		 pst.executeUpdate();
		 
		}
	
   public static void booking(String name,int age,String berth,String email,String gender,JButton btn1,boolean flag) throws Exception {
	 
	   if(flag==true) {
	   train=Booking.tr;
	   kid=Booking.k;
	   date=Booking.date2;
	   }
	   if(flag==false) {
		   train=CancelTicket.train;
		   kid=CancelTicket.kid;
		   date=CancelTicket.date;
	   }
	   //check if tickets available for user preference
	  String query="select "+berth+" from "+train+" where Date1=?";
	  Connection con= DbConnection.getConnection();
	  PreparedStatement pst=con.prepareStatement(query);
	 pst.setString(1,date);
	 ResultSet rs=pst.executeQuery();
	 rs.next();
	 int cap=rs.getInt(1);
	  if(cap>0) {
	
			  con= DbConnection.getConnection(); 
			  String q5="select * from "+berth+kid+" limit 1";
			  Statement st=con.createStatement();
			   rs=st.executeQuery(q5);
			  rs.next();
			  int pos=rs.getInt(1);
			 
			  //call the bookedlist function to insert the users data into table
			  bookedList(name,age,pos,berth,email,gender,kid,date);
			  
			  //reduce the capacity for booked ticket
			  int k=cap-1;
			  String q7="update "+train+" set "+berth+"=? where Date1=?";
			  pst=con.prepareStatement(q7);
			  pst.setInt(1, k);
			  pst.setString(2,date);
			  pst.executeUpdate();
			 
			  //delete the booked position from berth table
			  String q8="delete from "+berth+kid+" where "+berth+"="+pos;
			     st=con.createStatement();
			     st.executeUpdate(q8);
			     
			     if(flag==true) {
			     JOptionPane.showMessageDialog(btn1,"Ticket booked successfully as your preference "+berth+pos);
			     
			     //call the function fare
			     fare(train,email);
			     }
			     //To send the mail for booking confirmation 
			     if(flag==false) {
			    	 //To send the mail for user who moved to the berth
			    	 String qy="select Train from "+train+" where Date1=?";
			    	 pst=con.prepareStatement(qy);
			    	 pst.setString(1,date);
			    	 rs=pst.executeQuery();
			    	 rs.next();
			    	 String Express=rs.getString(1);
			    	 
			    	 String qy2="select name from Train where Tno=?";
			    	 pst=con.prepareStatement(qy2);
			    	 pst.setString(1, Express);
			    	 rs=pst.executeQuery();
			    	 rs.next();
			    	 Express=Express+" "+rs.getString(1);
			    	 
					   String berth2=berth+pos;
					   Email t=new Email();
					   t.sendMail(name,berth2,email,date,Express,gender);
				   }
			    
	  }
	 else {
		  //check the capacity of available preference
		
		  String query2="select * from "+train+" where Date1=?";
		PreparedStatement pst2=con.prepareStatement(query2);
		 pst2.setString(1,date);
		   ResultSet rs2=pst2.executeQuery();
		 rs2.next();
			int cap2=rs2.getInt(2);
		  String s1="L";
		  
		  int cap3=rs2.getInt(3);
		  String s2="M";
		  
		  int cap4=rs2.getInt(4);
		  String s3="U";
		  
		 if(cap2>0 || cap3>0 || cap4>0) {
			 if(flag==true) {
			 int response2=JOptionPane.showConfirmDialog(btn1,"Sorry preference not available..Want to book available berth?","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			 
			 if(response2==JOptionPane.YES_OPTION) {
			 
		  if(cap2>0) 
			  //call the booking2 function to insert the user's data into table
			  booking2(name,age,s1,cap2,btn1,email,gender,flag,train,date,kid);
		  else if(cap3>0) 
			  booking2(name,age,s2,cap3,btn1,email,gender,flag,train,date,kid);
		  else if(cap4>0) 
			  booking2(name,age,s3,cap4,btn1,email,gender,flag,train,date,kid);
		  
			 }else if(response2==JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(btn1,"oops! sorry");
			 }
		 }else if(flag==false) {
			 if(cap2>0 ) 
				  booking2(name,age,s1,cap2,btn1,email,gender,flag,train,date,kid);
			 else if(cap3>0) 
				  booking2(name,age,s2,cap3,btn1,email,gender,flag,train,date,kid);
			  else if(cap4>0)
				  booking2(name,age,s3,cap4,btn1,email,gender,flag,train,date,kid);
			  
		 }
		 } else {
			 //check if the availability of rac tickets
			  String q8="select RAC from "+train+" where Date1=?";
			  pst2=con.prepareStatement(q8);
			  pst2.setString(1,date);
			  ResultSet rs3=pst2.executeQuery();
			  rs3.next();
			  int cap5=rs3.getInt(1);
			  
			  if(cap5>0) {
				  int response3=JOptionPane.showConfirmDialog(btn1,"Sorry berths not available..Want to book RAC ticket?","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				  if(response3==JOptionPane.YES_OPTION) {
				//call the bookingRAC function to book the rac ticket
				  bookingRac(name,age,berth,cap5,email,gender,btn1,true,kid,train,date);
				  }else if(response3==JOptionPane.NO_OPTION) {
					  JOptionPane.showMessageDialog(btn1,"oops! sorry");
				  }
			  } else {
				  //check if the waiting list tickets are available
				  String q9="select WL from "+train+" where Date1=?";
				  pst2=con.prepareStatement(q9);
				  pst2.setString(1,date);
				  rs3=pst2.executeQuery();
				  rs3.next();
				  
				  int cap6=rs3.getInt(1);
				  if(cap6>0) {
					  int response4=JOptionPane.showConfirmDialog(btn1,"Sorry berths fulled..RAC not available..Want to book Waiting List ticket?","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);			  
					  if(response4==JOptionPane.YES_OPTION) {
					  String q5="select * from WL"+kid+" limit 1";
					  Statement st=con.createStatement();
					   rs3=st.executeQuery(q5);
					  rs3.next();
					  int pos=rs3.getInt(1);
					  //call the bookingWl function to book waiting list ticket
					  bookingWl(name,age,berth,cap6,pos,email,gender,btn1,kid,train,date);
					  }else if(response4==JOptionPane.NO_OPTION) {
						  JOptionPane.showMessageDialog(btn1,"oops! sorry");
					  }
				  }else {
					  //if all the tickets were booked
					  JOptionPane.showMessageDialog(btn1, "Sorry tickets not available..");
					  
				  }
				  
			  } 
		  }
	  
	  } 
   }
   //To calculate the fare
   public static void fare(String train,String em) throws Exception {
	   Connection con=DbConnection.getConnection();
	   
	   int tb=Train.id;
	  String qr="select fare from Train where Fr=? and T=?";
	  PreparedStatement pst=con.prepareStatement(qr);
	  pst.setString(1,Train.from);
	  pst.setString(2,Train.to);
	   ResultSet rs=pst.executeQuery();
	   rs.next();
	   int fr=rs.getInt(1);
	   
	   String q="select count(*) from bookedlist"+kid+" where email='"+em+"'";
   	Statement st=con.createStatement();
    rs=st.executeQuery(q);
   	rs.next();
   	int f=rs.getInt(1);
   	
   	String q2="select count(*) from rac_list"+kid+" where email='"+em+"'";
   	 st=con.createStatement();
   	 rs=st.executeQuery(q2);
   	rs.next();
   	int s=rs.getInt(1);
   	
   	String qy3="select count(*) from waiting_list"+kid+" where email='"+em+"'";
   	 st=con.createStatement();
   	 rs=st.executeQuery(qy3);
   	rs.next();
   	int t=rs.getInt(1);
   	
   	//To calculate the total fare
   	 total=(f*fr)+(s*fr)+(t*fr);
   	
   	String qy1="select count(*) from fare"+tb+" where id='"+em+"' and date='"+date+"'";
   	 st=con.createStatement();
   	 rs=st.executeQuery(qy1);
   	rs.next();
   	if(rs.getInt(1)>0) {
   		//update the total fare
   		String qy4="update fare"+tb+" set Fare=? where id=? and date=?";
   		 pst=con.prepareStatement(qy4);
   		pst.setInt(1, total);
   		pst.setString(2,em);
   		pst.setString(3,date);
   		pst.executeUpdate();
   		
   	}else{
   		String qy2="insert into fare"+tb+" values(?,?,?,?)";
   		 pst=con.prepareStatement(qy2);
   		pst.setInt(1,fr);
   		pst.setString(2,date);
   		pst.setString(3,em);
   		pst.setInt(4,0);  		
   		pst.executeUpdate();
   	}

   }
	}
