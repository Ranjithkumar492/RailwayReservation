import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MyBookings {

	 JFrame MyBookingsFrame;
	private JTable table;
	private JTable table2;
	private JTable table3;
    public static String email;
    JList list;
	JButton btn1;
    JDateChooser dateChooser;
     String exp;
     String train;
     int kid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBookings window = new MyBookings();
					window.MyBookingsFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyBookings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MyBookingsFrame = new JFrame();
		MyBookingsFrame.getContentPane().setForeground(Color.WHITE);
		MyBookingsFrame.setBackground(Color.BLACK);
		MyBookingsFrame.setBounds(100, 100,793,550);
		MyBookingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyBookingsFrame.setLocationRelativeTo(null);
		MyBookingsFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("My Bookings");
		label1.setForeground(new Color(50, 205, 50));
		label1.setFont(new Font("Calibri", Font.BOLD, 20));
		label1.setBounds(306, 8, 118, 26);
		MyBookingsFrame.getContentPane().add(label1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 206, 684, 88);
		MyBookingsFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btn3 = new JButton("Home");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgressBar p=new ProgressBar();
				p.PFrame.setVisible(true);
				
				MyBookingsFrame.dispose();
				
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(625, 8, 77, 23);
		MyBookingsFrame.getContentPane().add(btn3);
		
		JLabel label3 = new JLabel("Berth List:");
		label3.setForeground(Color.CYAN);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(57, 187, 68, 20);
		MyBookingsFrame.getContentPane().add(label3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 330, 684, 64);
		MyBookingsFrame.getContentPane().add(scrollPane_1);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
		JLabel label4 = new JLabel("RAC List:");
		label4.setForeground(Color.CYAN);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(57, 315, 68, 14);
		MyBookingsFrame.getContentPane().add(label4);
		
		JLabel lable5 = new JLabel("Waiting List:");
		lable5.setForeground(Color.CYAN);
		lable5.setFont(new Font("Calibri", Font.BOLD, 15));
		lable5.setBounds(57, 415, 86, 20);
		MyBookingsFrame.getContentPane().add(lable5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(47, 436, 684, 64);
		MyBookingsFrame.getContentPane().add(scrollPane_2);
		
		table3 = new JTable();
		scrollPane_2.setViewportView(table3);
		
	    btn1 = new JButton("show");
	    btn1.setBackground(Color.BLACK);
	    btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					show(btn1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(292, 147, 68, 23);
		MyBookingsFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("clear");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				table2.setModel(new DefaultTableModel());
				table3.setModel(new DefaultTableModel());
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(389, 147, 68, 23);
		MyBookingsFrame.getContentPane().add(btn2);
		
		JLabel label5 = new JLabel("Train:");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(156, 47, 52, 14);
		MyBookingsFrame.getContentPane().add(label5);
		
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
		list.setBounds(156, 72, 165, 64);
		MyBookingsFrame.getContentPane().add(list);
		
		JLabel label6 = new JLabel("Date:");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(372, 45, 52, 14);
		MyBookingsFrame.getContentPane().add(label6);
		
	    dateChooser = new JDateChooser();
	    dateChooser.setForeground(Color.BLACK);
	    dateChooser.setBackground(Color.WHITE);
		dateChooser.setBounds(382, 72, 118, 20);
		MyBookingsFrame.getContentPane().add(dateChooser);
		
		JLabel ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\mbook.jpg"));
		ilabel.setBounds(0, 0, 777, 511);
		MyBookingsFrame.getContentPane().add(ilabel);
	}
	
	public void show(JButton btn1) throws Exception {
		int indx=list.getSelectedIndex();
		//To check if the user selected the train
		if(indx>=0) {
			//To check if the user selected the date
			if(dateChooser.getDate()==null)
				JOptionPane.showMessageDialog(btn1,"Choose your date");
			else {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		String date=dateFormat.format(dateChooser.getDate());
		
		email=Login.textField1.getText();
		
		//To check if the user selected the available train
		String qy1="select count(*) from id where d=? and Express=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(qy1);
		pst.setString(1,date);
		pst.setString(2,exp);
		ResultSet rs=pst.executeQuery();
		rs.next();
		if(rs.getInt(1)>0) {
			
		String qy2="select id from id where d=? and Express=?";
		 con=DbConnection.getConnection();
		 pst=con.prepareStatement(qy2);
		pst.setString(1,date);
		pst.setString(2,exp);
		rs=pst.executeQuery();
		rs.next();
		int kid=rs.getInt(1);
		
		String q1="select count(*) from bookedList"+kid+" where email="+"'"+email+"'";
		String q2="select count(*) from rac_list"+kid+" where email="+"'"+email+"'";
		String q3="select count(*) from waiting_list"+kid+" where email="+"'"+email+"'";
		
	    con=DbConnection.getConnection();
    	Statement st=con.createStatement();
        rs=st.executeQuery(q1);
    	rs.next();
    	int k=rs.getInt(1);
    	rs=st.executeQuery(q2);
    	rs.next();
    	int g=rs.getInt(1);
    	rs=st.executeQuery(q3);
    	rs.next();
    	int h=rs.getInt(1);
    	if(k==0 && g==0 && h==0) {
    		 JOptionPane.showMessageDialog(btn1,"oops! you've not booked yet..");
    	}else {
    		if(k>0){
    		String q4="select * from bookedlist"+kid+" where email="+"'"+email+"'";
    		rs=st.executeQuery(q4);
    		//it contains information of no of rows,columns and column names
    	ResultSetMetaData rsmd=rs.getMetaData();
    	//To add rows and columns to our table at run time
    	DefaultTableModel model=(DefaultTableModel) table.getModel();
    	
    	//It will give the column count of the table
    	int cols=rsmd.getColumnCount();
    	String[] colName=new String[cols];
    	for(int i=0;i<cols;i++)
    		colName[i]=rsmd.getColumnName(i+1);
    	//we set column names to our table
    	model.setColumnIdentifiers(colName);
    	
    	String id,name,age,pos,alloted,email2,gender;
    	while(rs.next()) {
    		id=rs.getString(1);
    		name=rs.getString(2);
    		age=rs.getString(3);
    		pos=rs.getString(4);
    		alloted=rs.getString(5);
    		email2=rs.getString(6);
    		gender=rs.getString(7);
    		
    		String[] row= {id,name,age,pos,alloted,email2,gender};
    		//add the rows to our table
    		model.addRow(row);
    	}
		}
    	if(g>0) {
			String q5="select * from rac_list"+kid+" where email="+"'"+email+"'";
    		rs=st.executeQuery(q5);
    		ResultSetMetaData rsmd=rs.getMetaData();
    	
    	DefaultTableModel model=(DefaultTableModel) table2.getModel();
    	
    	int cols=rsmd.getColumnCount();
    	String[] colName=new String[cols];
    	for(int i=0;i<cols;i++)
    		colName[i]=rsmd.getColumnName(i+1);
    	model.setColumnIdentifiers(colName);
    	
    	String name,berth,age,pos,email2,gender;
    	while(rs.next()) {
    		name=rs.getString(1);
    		berth=rs.getString(2);
    		age=rs.getString(3);
    	    pos=rs.getString(4);
    		email2=rs.getString(5);
    		gender=rs.getString(6);
    		
    		String[] row= {name,berth,age,pos,email2,gender};
    		model.addRow(row);
    	
		}
	}
    	if(h>0) {
    		String q6="select * from waiting_list"+kid+" where email="+"'"+email+"'";
    		rs=st.executeQuery(q6);
    		ResultSetMetaData rsmd=rs.getMetaData();
    	
    	DefaultTableModel model=(DefaultTableModel) table3.getModel();
    	
    	int cols=rsmd.getColumnCount();
    	String[] colName=new String[cols];
    	for(int i=0;i<cols;i++)
    		colName[i]=rsmd.getColumnName(i+1);
    	model.setColumnIdentifiers(colName);
    	
    	String name,berth,age,pos,email2,gender;
    	while(rs.next()) {
    		name=rs.getString(1);
    		berth=rs.getString(2);
    		age=rs.getString(3);
    	    pos=rs.getString(4);
    		email2=rs.getString(5);
    		gender=rs.getString(6);
    		
    		String[] row= {name,berth,age,pos,email2,gender};
    		model.addRow(row);
    	
		}
    	}
    	}
	}else 
		JOptionPane.showMessageDialog(btn1,"check your train and date that you've booked");
	}
	}else
		JOptionPane.showMessageDialog(btn1,"choose your train");
	}
}
