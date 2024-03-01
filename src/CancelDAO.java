import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
public class CancelDAO {

	 JFrame CancelDAOFrame;
      public static String tr;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelDAO window = new CancelDAO();
					window.CancelDAOFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelDAO() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CancelDAOFrame = new JFrame();
		CancelDAOFrame.setBounds(100, 100,910,582);
		CancelDAOFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CancelDAOFrame.setLocationRelativeTo(null);
		CancelDAOFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Cancellation");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Calibri", Font.BOLD, 30));
		label1.setBounds(382, 71, 159, 31);
		CancelDAOFrame.getContentPane().add(label1);
		
		JButton btn1 = new JButton("Home");
		btn1.setForeground(Color.RED);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgressBar p=new ProgressBar();
				p.PFrame.setVisible(true);
				CancelDAOFrame.dispose();
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(806, 11, 78, 23);
		CancelDAOFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Back");
		btn2.setForeground(Color.RED);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelTicket c=new CancelTicket();
				c.CancelFrame.setVisible(true);
				CancelDAOFrame.dispose();
				
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
		btn2.setBounds(10, 509, 70, 23);
		CancelDAOFrame.getContentPane().add(btn2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\CancelDAO.jpg"));
		lblNewLabel.setBounds(0, 0, 894, 543);
		CancelDAOFrame.getContentPane().add(lblNewLabel);
	}
	
	//To check the availability of waitinglist ticket
	public static void checkWaitingList(int rac_cap,JButton btn1,int kid,String date) throws Exception {
		
		String q1="select count(*) from waiting_list"+kid;
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(q1);
		rs.next();
		int cap=rs.getInt(1);
		if(cap>0) {
			String q2="select * from waiting_list"+kid+" limit 1";
			rs=st.executeQuery(q2);
			rs.next();
			String name=rs.getString(1);
			String berth=rs.getString(2);
			int age=rs.getInt(3);
			int wl_pos=rs.getInt(4);
			String email=rs.getString(5);
			String gender=rs.getString(6);
			
			//call the bookingRac function to book the rac ticket for passenger from waiting list
			TicketsDAO1.bookingRac(name, age, berth,rac_cap,email,gender,btn1,false,kid,tr,date);
			
			String qy="select WL from "+tr+" where Date1=?";
			PreparedStatement pst=con.prepareStatement(qy);
			pst.setString(1,date);
			rs=pst.executeQuery();
			rs.next();
			int f=rs.getInt(1)+1;
			
			String q3="update "+tr+" set WL=? where Date1=?";
			pst=con.prepareStatement(q3);
			pst.setInt(1,f);
			pst.setString(2,date);
			pst.executeUpdate();
			
			String q4="delete from waiting_list"+kid+" where Wl_pos="+wl_pos;
			st.executeUpdate(q4);
			System.out.println("deleted from waiting list..");
			
			//Update the waiting list table after booking the rac ticket for wl passenger
			String q5="select count(*) from waiting_list"+kid;
			rs=st.executeQuery(q5);
			rs.next();
			int count=rs.getInt(1);
			if(count>0) {
				String q6="select * from waiting_list"+kid;
				rs=st.executeQuery(q6);
				while(rs.next()) {
					int g=rs.getInt(4);
					int m=g-1;
					String q7="update waiting_list"+kid+" set Wl_pos="+m+" where Wl_pos="+g;
					Statement st2=con.createStatement();
					st2.executeUpdate(q7);
				}
				String q8="select Wl_pos from waiting_list"+kid+" order by Wl_pos desc limit 1";
				 pst=con.prepareStatement(q8);
				ResultSet rs2=pst.executeQuery();
				rs2.next();
				int k=rs2.getInt(1);
				int h=k+1;
				//insert the available WL position into wl table
				String q9="insert into wl"+kid+" values("+h+")";
				pst=con.prepareStatement(q9);
				pst.executeUpdate(q9); 
			}else {
				//insert the available WL position into wl table
			String q10="insert into wl"+kid+" values("+wl_pos+")";
			st.executeUpdate(q10);
			}
			
	}
	}
	//check the availability of rac ticket
	public static void checkRac(JButton btn1,String date,int kid) throws Exception {
		String q1="select count(*) from Rac_list"+kid;
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(q1);
		rs.next();
		
		int cap=rs.getInt(1);
		if(cap>0) {
			
			String q2="select * from Rac_list"+kid+" limit 1";
			rs=st.executeQuery(q2);
			rs.next();
			String name=rs.getString(1);
			String berth=rs.getString(2);
			int age=rs.getInt(3);
			int rac_pos=rs.getInt(4);
			String email=rs.getString(5);
			String gender=rs.getString(6);
			
			//call the booking function to book the berth for passenger from rac list
			TicketsDAO1.booking( name, age, berth,email,gender,btn1,false);
			
			String q3="delete from rac_list"+kid+" where Rac_pos="+rac_pos;
			st.executeUpdate(q3);
			
			String qy="select RAC from "+tr+" where Date1=?";
			PreparedStatement pst=con.prepareStatement(qy);
			pst.setString(1,date);
			rs=pst.executeQuery();
			rs.next();
			int h=rs.getInt(1)+1;
			
			String q4="update "+tr+" set RAC=? where Date1=?";
			 pst=con.prepareStatement(q4);
			 pst.setInt(1,h);
			pst.setString(2,date);
			pst.executeUpdate();
			
			//Update the raclist after booking the berth for rac passenger
			String q5="select count(*) from Rac_list"+kid;
			rs=st.executeQuery(q5);
			rs.next();
			int count=rs.getInt(1);
			if(count>0) {
				String q6="select * from Rac_list"+kid;
				rs=st.executeQuery(q6);
				while(rs.next()) {
					int g=rs.getInt(4);
					int m=g-1;
					String q7="update  Rac_list"+kid+" set Rac_pos="+m+" where Rac_pos="+g;
					Statement st2=con.createStatement();
					st2.executeUpdate(q7);
				}
			
				String q8="select Rac_pos from Rac_list"+kid+" order by Rac_pos desc limit 1";
				 pst=con.prepareStatement(q8);
				ResultSet rs2=pst.executeQuery();
				rs2.next();
				int k=rs2.getInt(1);
				int f=k+1;
				
				//insert the available RAC position to rac table
				String q9="insert into rac"+kid+" values ("+f+")";
				pst=con.prepareStatement(q9);
				pst.executeUpdate();
				System.out.println("inserted into rac");
				
			}else {
				//insert the available RAC position to rac table
			String q10="insert into rac"+kid+" values("+rac_pos+")";
			st.executeUpdate(q10);
			}
			String q11="select RAC from "+tr+" where Date1=?";
			pst=con.prepareStatement(q11);
			pst.setString(1,date);
			rs=pst.executeQuery();
			rs.next();
			int rac_cap =rs.getInt(1);
			
			//call the checkWaitingList function to check the availability of waiting list ticket
			checkWaitingList(rac_cap,btn1,kid,date);
			}
		}
	//To cancel the ticket
	public static void cancelling(int T_id,int pos,String al,JButton btn1,String date,int kid) throws Exception {
		tr=CancelTicket.train;
		//update the capacity of berth which is going to be cancelled
		String qy="select "+al+" from "+tr+" where Date1=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(qy);
		pst.setString(1,date);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int h=rs.getInt(1)+1;
		
		String q2="update "+tr+" set "+al+"="+h+" where Date1=?";
		con=DbConnection.getConnection();
		 pst=con.prepareStatement(q2);
		pst.setString(1,date);
		pst.executeUpdate();
		
		//insert the cancelled position into berth table
		String q3="insert into "+al+kid+" values ("+pos+")";
		Statement st=con.createStatement();
		st.executeUpdate(q3);
		
		//To update the fare after cancellation
		String q5="select email from bookedlist"+kid+" where id=?";
		pst=con.prepareStatement(q5);
		pst.setInt(1,T_id);
		ResultSet r=pst.executeQuery();
		r.next();
		String em=r.getString(1);

		String qr="select fare from Train where id=?";
		pst=con.prepareStatement(qr);
		pst.setInt(1,CancelTicket.g);
		rs=pst.executeQuery();
		rs.next();
		int u=rs.getInt(1);
		int fr=CancelTicket.g;
		
		String q6="Update fare"+fr+" set Fare=Fare-? where date=? and id=?";
		pst=con.prepareStatement(q6);
		pst.setInt(1, u);
		pst.setString(2,date);
		pst.setString(3,em);
		pst.executeUpdate();
		
		//store the amount which have been cancelled
		String q7="update fare"+fr+" set cancel=cancel+? where date=? and id=?";
		pst=con.prepareStatement(q7);
		pst.setInt(1, u);
		pst.setString(2,date);
		pst.setString(3, em);
		pst.executeUpdate();
		
		System.out.println("updated..");
		
		//delete the passenger details from bookedlist table
		String q4="delete from bookedlist"+kid+" where id=?";
		pst=con.prepareStatement(q4);
		pst.setInt(1,T_id);
		pst.executeUpdate();
		JOptionPane.showMessageDialog(btn1,"Ticket cancelled successfully..");
		
		//call the checkRac function to check the availability of rac ticket
		checkRac(btn1,date,kid);
		
	}
	
}
