import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;


import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Train {

	 JFrame TrainFrame;
	public static int year2;
	public static int month2;
	public static int da4;
	private JTextField textField1;
	private JTextField textField2;
	public static JTable table;
	public static String from;
	public static String to;
	public static int id;
	public static int rtime;
	public static int year;
	public static int month;
	public static int da2;
	public static String s;
	public static  String number;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Train window = new Train();
					window.TrainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Train() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TrainFrame = new JFrame();
		TrainFrame.setBounds(100, 100, 771, 429);
		TrainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TrainFrame.setLocationRelativeTo(null);
		TrainFrame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(83, 239, 163, -38);
		TrainFrame.getContentPane().add(list);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(323, 213, 116, 20);
		TrainFrame.getContentPane().add(dateChooser);
		
		 JButton btn1 = new JButton("Search Train");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check if user selected the route or not
				int indx=table.getSelectedRow();
				if(indx>=0) {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			      number=model.getValueAt(indx, 0).toString();
			     
			 //To check if the user selected the journey date
	       if(dateChooser.getDate()==null) {
	   		JOptionPane.showMessageDialog(btn1,"please select your Journey Date");
	       }else {
				//format the date in dd-MM-yyyy
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
				String date=dateFormat.format(dateChooser.getDate());
				
				//Today's date
				Date d=new Date();
				 s=dateFormat.format(d);
				
				String y="",da="",m="";
				for(int i=0;i<s.length();i++) {
					if(s.charAt(i)=='-') 
						continue;
					else if(da.length()!=2)
						da=da+s.charAt(i);
					else if(m.length()!=2)
						m=m+s.charAt(i);
					else if(y.length()!=4)
						y=y+s.charAt(i);
				}
				 year=Integer.parseInt(y);
				 month=Integer.parseInt(m);
				 da2=Integer.parseInt(da);
				
				String y2="",da3="",m2="";
				for(int i=0;i<date.length();i++) {
					if(date.charAt(i)=='-') 
						continue;
					else if(da3.length()!=2)
						da3=da3+date.charAt(i);
					else if(m2.length()!=2)
						m2=m2+date.charAt(i);
					else if(y2.length()!=4)
						y2=y2+date.charAt(i);
				}
				 year2=Integer.parseInt(y2);
				 month2=Integer.parseInt(m2);
				 da4=Integer.parseInt(da3);
				 
				
				LocalDate Td1=LocalDate.of(year,month,da2);
				LocalDate Ud2=LocalDate.of(year2,month2,da4);
				try {
					String qr="select date from Train where Tno=?";
					Connection con=DbConnection.getConnection();
					PreparedStatement pst=con.prepareStatement(qr);
					pst.setString(1,number);
					ResultSet rs=pst.executeQuery();
					rs.next();
					String d1=rs.getString(1);
					
					String x1="",y1="",z1="";
					for(int i=0;i<d1.length();i++) {
						if(d1.charAt(i)=='-') 
							continue;
						else if(x1.length()!=2)
							x1=x1+d1.charAt(i);
						else if(y1.length()!=2)
							y1=y1+d1.charAt(i);
						else if(z1.length()!=4)
							z1=z1+d1.charAt(i);
					}
					 int uy=Integer.parseInt(z1);
					 int um=Integer.parseInt(y1);
					 int ud=Integer.parseInt(x1);
				LocalDate d3=LocalDate.of(uy,um,ud);
			
				//To check if the user's date is valid
				if(Ud2.equals(Td1) || Ud2.isAfter(Td1) && Ud2.isBefore(d3)) {
					
					 dateFormat=new SimpleDateFormat("HH:mm:ss");
					String time=dateFormat.format(d);
					
					String hr="",min="";
					for(int i=0;i<time.length();i++) {
						if(time.charAt(i)==':')
							continue;
						else if(hr.length()!=2)
							hr=hr+time.charAt(i);
						else if(min.length()!=2)
							min=min+time.charAt(i);
			
					}
					int hour=Integer.parseInt(hr);
					int minute=Integer.parseInt(min);
					
					
						String q="select id,Fr,T,DTime from Train where Tno=?";
						con=DbConnection.getConnection();
						 pst=con.prepareStatement(q);
						pst.setString(1,number);
						 rs=pst.executeQuery();
						rs.next();
						
						id=rs.getInt(1);
						from=rs.getString(2);
						to=rs.getString(3);
						
						String s2=rs.getString(4);
						String rt="";
						for(int i=0;i<2;i++) {
							if(rt.length()!=2)
								rt=rt+s2.charAt(i);
						}
					 rtime=Integer.parseInt(rt);
						if(rtime==0) 
							rtime=23;
						else
							rtime=rtime-1;
						
					
					LocalTime Dt1=LocalTime.of(rtime,00);
					
					LocalTime Ut2=LocalTime.of(hour,minute);
					
					//not allow the user to book before departure time
					int day=0;
			        int mo=0;
					 if( rtime==23 && da4==1) {
						 LocalDate localD=LocalDate.parse(s,DateTimeFormatter.ofPattern("d-M-yyyy"));
						 localD=localD.withDayOfMonth(localD.getMonth().length(localD.isLeapYear()));
						 day=localD.getDayOfMonth();
						 mo=month2-1;
					 }else if(rtime==23) {
							day=da4-1;
							mo=month2;
						}
					
					if(rtime==23 && s.equals(date)) {
							JOptionPane.showMessageDialog(btn1, "Reservation Closed..");
							
					}else {
					if((Ut2.isAfter(Dt1)&& s.equals(date)) || (rtime==23 && Ut2.isAfter(Dt1)&& da2==day&& mo==month )) {
						JOptionPane.showMessageDialog(btn1, "Reservation Closed..");
					}else {
						Booking b=new Booking();
						b.checking(date,id,number);
					b.BookingFrame.setVisible(true);
					TrainFrame.dispose();
					} 
					}
				}else 
					JOptionPane.showMessageDialog(btn1,"Date must be between "+s+" and "+d1);
				
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}				
				}else
					JOptionPane.showMessageDialog(btn1,"please choose the train");
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(323, 329, 116, 23);
		TrainFrame.getContentPane().add(btn1); 
		
		JButton btn2 = new JButton("Back");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h=new Home();
				h.HomeFrame.setVisible(true);
				TrainFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 356, 70, 23);
		TrainFrame.getContentPane().add(btn2);
		
		JLabel Dlabel = new JLabel("Date:");
		Dlabel.setForeground(Color.WHITE);
		Dlabel.setFont(new Font("Calibri", Font.BOLD, 18));
		Dlabel.setBounds(267, 213, 56, 20);
		TrainFrame.getContentPane().add(Dlabel);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFrom.setBounds(37, 33, 56, 20);
		TrainFrame.getContentPane().add(lblFrom);
		
		textField1 = new JTextField();
		textField1.addMouseListener(new MouseAdapter() {
			@Override
				public void mouseExited(MouseEvent e) {
				if(!textField1.getText().equals("")) {
					String from=textField1.getText();
					
					try {
					Connection con=DbConnection.getConnection();
					String q="select count(*) from Train where Fr=?";
					PreparedStatement pst=con.prepareStatement(q);
					pst.setString(1,from);
					ResultSet rs=pst.executeQuery();
					rs.next();
					if(rs.getInt(1)>0) {
						table.setModel(new DefaultTableModel());
						String q2="select  Tno,name,Fr,T,DTime,RTime from Train where Fr=?";
						pst=con.prepareStatement(q2);
						pst.setString(1,from);
						rs=pst.executeQuery();
						
						 ResultSetMetaData rsmd=rs.getMetaData();
		    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
		    		    	
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

					}else {
						JOptionPane.showMessageDialog(lblFrom,"Train not available for this Route");
					}
					
					
					}catch(Exception exp) {
						System.out.println(exp);
					}

				}
			}
		});
		textField1.setBounds(95, 32, 126, 20);
		TrainFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTo.setBounds(280, 35, 56, 20);
		TrainFrame.getContentPane().add(lblTo);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(318, 32, 121, 20);
		TrainFrame.getContentPane().add(textField2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 735, 107);
		TrainFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		
		scrollPane.setViewportView(table);
		
		JButton btn3 = new JButton("Search");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField1.getText().equals("") && !textField2.getText().equals("")) {
				String from=textField1.getText();
				String to=textField2.getText();
				try {
				Connection con=DbConnection.getConnection();
				String q="select count(*) from Train where Fr=? and T=?";
				PreparedStatement pst=con.prepareStatement(q);
				pst.setString(1,from);
				pst.setString(2, to);
				ResultSet rs=pst.executeQuery();
				rs.next();
				if(rs.getInt(1)>0) {
					table.setModel(new DefaultTableModel());
					String q2="select  Tno,name,Fr,T,DTime,RTime from Train where Fr=? and T=?";
					pst=con.prepareStatement(q2);
					pst.setString(1,from);
					pst.setString(2, to);
					rs=pst.executeQuery();
					
					 ResultSetMetaData rsmd=rs.getMetaData();
	    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
	    		    	
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

				}else {
					JOptionPane.showMessageDialog(lblFrom,"Train not available for this Route");
				}
				
				
				}catch(Exception exp) {
					System.out.println(exp);
				}


			}else
				JOptionPane.showMessageDialog(lblFrom,"Choose the route");
			}
		});
		btn3.setForeground(Color.WHITE);
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBackground(Color.BLACK);
		btn3.setBounds(463, 31, 81, 23);
		TrainFrame.getContentPane().add(btn3);
		
		JButton btn4 = new JButton("Reset");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new DefaultTableModel());
					Connection con=DbConnection.getConnection();
				String q3="select Tno,name,Fr,T,DTime,RTime from Train";
    		    PreparedStatement pst=con.prepareStatement(q3);
    			ResultSet rs=pst.executeQuery();
    			 
    			 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
    		    	
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
		btn4.setForeground(Color.WHITE);
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBackground(Color.BLACK);
		btn4.setBounds(572, 31, 81, 23);
		TrainFrame.getContentPane().add(btn4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\train3.jpg"));
		lblNewLabel.setBounds(0, 0, 755, 390);
		TrainFrame.getContentPane().add(lblNewLabel);
	}
		
}
