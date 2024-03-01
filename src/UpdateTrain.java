import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class UpdateTrain {

    JFrame frame;
	JTextField name;
    JTextField TrainNo;
    JTextField from;
    JTextField to;
	JTextField fare;
	JComboBox comboBox1;
	JComboBox comboBox2;
	JComboBox comboBox3;
	JComboBox comboBox4;
	JComboBox lower;
	JComboBox middle;
	JComboBox upper;
	JComboBox rac;
	JComboBox wl;
	JDateChooser dateChooser;
	private JButton btnNewButton;
	private JLabel lblUpdateTrain;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateTrain window = new UpdateTrain();
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
	public UpdateTrain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 707, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setBounds(160, 68, 128, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		TrainNo = new JTextField();
		TrainNo.setColumns(10);
		TrainNo.setBounds(433, 68, 128, 20);
		frame.getContentPane().add(TrainNo);
		
		from = new JTextField();
		from.setColumns(10);
		from.setBounds(160, 120, 128, 20);
		frame.getContentPane().add(from);
		
		to = new JTextField();
		to.setColumns(10);
		to.setBounds(433, 120, 128, 20);
		frame.getContentPane().add(to);
		
		 comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "19", "20", "21", "22", "23"}));
		comboBox1.setBounds(163, 165, 50, 22);
		frame.getContentPane().add(comboBox1);
		
		 comboBox2 = new JComboBox();
		comboBox2.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox2.setBounds(238, 165, 50, 22);
		frame.getContentPane().add(comboBox2);
		
	    comboBox3 = new JComboBox();
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox3.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox3.setBounds(434, 165, 50, 22);
		frame.getContentPane().add(comboBox3);
		
	    comboBox4 = new JComboBox();
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox4.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox4.setBounds(513, 165, 50, 22);
		frame.getContentPane().add(comboBox4);
		
		lower = new JComboBox();
		lower.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		lower.setFont(new Font("Calibri", Font.BOLD, 15));
		lower.setBounds(94, 242, 50, 22);
		frame.getContentPane().add(lower);
		
		 middle = new JComboBox();
		middle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		middle.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		middle.setBounds(238, 242, 50, 22);
		frame.getContentPane().add(middle);
		
		 upper = new JComboBox();
		upper.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		upper.setFont(new Font("Calibri", Font.BOLD, 15));
		upper.setBounds(365, 242, 50, 22);
		frame.getContentPane().add(upper);
		
	    rac = new JComboBox();
		rac.setFont(new Font("Calibri", Font.BOLD, 15));
		rac.setModel(new DefaultComboBoxModel(new String[] {"18", "2"}));
		rac.setBounds(94, 300, 50, 22);
		frame.getContentPane().add(rac);
		
		 wl = new JComboBox();
		wl.setModel(new DefaultComboBoxModel(new String[] {"10", "2"}));
		wl.setFont(new Font("Calibri", Font.BOLD, 15));
		wl.setBounds(238, 300, 50, 22);
		frame.getContentPane().add(wl);
		
		fare = new JTextField();
		fare.setBounds(499, 244, 62, 20);
		frame.getContentPane().add(fare);
		fare.setColumns(10);
		
	    dateChooser = new JDateChooser();
		dateChooser.setBounds(340, 300, 102, 20);
		frame.getContentPane().add(dateChooser);
		
		JButton btn1 = new JButton("UPDATE");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//To check if all fields are completed
				if(name.getText().equals("")||TrainNo.getText().equals("")||from.getText().equals("")||to.getText().equals("")||fare.getText().equals("")||dateChooser.getDate()==null)
					JOptionPane.showMessageDialog(btn1, "select all required fields");
				else {
				try {
					//To check if any other the train is available with same name or number
					String qu2="select count(*) from Train where (Tno=? or name=?) and not id=?";
					Connection con=DbConnection.getConnection();
					PreparedStatement pst2=con.prepareStatement(qu2);
					pst2.setString(1,TrainNo.getText());
					pst2.setString(2,name.getText());
					pst2.setInt(3,TrainList.g);
					ResultSet rs2=pst2.executeQuery();
					rs2.next();
					if(rs2.getInt(1)>0) 
							JOptionPane.showMessageDialog(btn1,"Train already exist, check the train name or \ntrain number");
					else {
						String tname=name.getText();
						boolean f1=true;
						for(int i=0;i<tname.length();i++) {
							if(tname.charAt(i)>=65 && tname.charAt(i)<=90) 
								continue;
							else if(tname.charAt(i)==' ')
								continue;
							else {
								f1=false;
								break;	
							}
						}
						if(f1==false)
							JOptionPane.showMessageDialog(btn1, "Train name should be in proper format (ex: KANYAKUMARI EXP)");
						else {
							String no=TrainNo.getText();
							if(no.length()>5 || no.length()<5)
								JOptionPane.showMessageDialog(btn1, "Train number should've 5 numbers");
							else {
							if(no.substring(0,4).equals("0000"))
								JOptionPane.showMessageDialog(btn1, "Train number should not start with more than 3 zeros");
							else {
								String fr=from.getText();
								String t=to.getText();
								boolean f2=true;
								for(int i=0;i<fr.length();i++) {
									if(fr.charAt(i)>=65 && fr.charAt(i)<=90)
										continue;
									if(fr.charAt(i)>=97 && fr.charAt(i)<=122)
										continue;
									else {
										f2=false;
										break;
									}
								}
								if(f2==false)
									JOptionPane.showMessageDialog(btn1,"Enter the from location in proper format");
								else {
									boolean f3=true;
									for(int i=0;i<t.length();i++) {
										if(t.charAt(i)>=65 && t.charAt(i)<=90)
											continue;
										if(t.charAt(i)>=97 && t.charAt(i)<=122)
											continue;
										else {
											f3=false;
											break;
										}
									}
									if(f3==false)
										JOptionPane.showMessageDialog(btn1,"Enter the To location in proper format");
									else {
										 int lo=lower.getSelectedIndex();
										 int m=middle.getSelectedIndex();
										 int u=upper.getSelectedIndex();
										//To check the proper ticket arrangement
										 if((lo==m)&&(m==u)) {
											 int fe=Integer.parseInt(fare.getText());
											 if(fe<10)
												 JOptionPane.showMessageDialog(btn1,"Ticket price must be atleast \ngreater than or equal to 10rs");
											 else {
												 SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
													String date=dateFormat.format(dateChooser.getDate());
													
													//Today's date
													Date d=new Date();
													String s=dateFormat.format(d);
													
													String y="",da="",mo="";
													for(int i=0;i<s.length();i++) {
														if(s.charAt(i)=='-') 
															continue;
														else if(da.length()!=2)
															da=da+s.charAt(i);
														else if(mo.length()!=2)
															mo=mo+s.charAt(i);
														else if(y.length()!=4)
															y=y+s.charAt(i);
													}
													int year=Integer.parseInt(y);
													int month=Integer.parseInt(mo);
													int da2=Integer.parseInt(da);
													
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
													int year2=Integer.parseInt(y2);
													int month2=Integer.parseInt(m2);
													int da4=Integer.parseInt(da3);

												 LocalDate Td1=LocalDate.of(year,month,da2);
													LocalDate Ud2=LocalDate.of(year2,month2,da4);
													
													//To set the date upto user can booking
													if(Ud2.isAfter(Td1)) {
									         
					String dTime=comboBox1.getSelectedItem().toString()+":"+comboBox2.getSelectedItem().toString();
					String rTime=comboBox3.getSelectedItem().toString()+":"+comboBox4.getSelectedItem().toString();
					
					//select all the details from train
					String qr="select * from Train where id=?";
					pst2=con.prepareStatement(qr);
					pst2.setInt(1,TrainList.g);				
					rs2=pst2.executeQuery();
					rs2.next();
					//To check if the train is already registered with same data
	    if(rs2.getString(2).equals(TrainNo.getText())&& rs2.getString(3).equals(name.getText())&& rs2.getString(4).equals(from.getText())&&
	     rs2.getString(5).equals(to.getText())&& rs2.getString(6).equals(dTime)&& rs2.getString(7).equals(rTime)&& rs2.getString(8).equals(lower.getSelectedItem().toString())&&
	     rs2.getString(9).equals( middle.getSelectedItem().toString())&&rs2.getString(10).equals( upper.getSelectedItem().toString())&&
	     rs2.getString(11).equals( rac.getSelectedItem().toString())&&rs2.getString(12).equals(wl.getSelectedItem().toString())&&rs2.getString(13).equals(fare.getText())&&
	     rs2.getString(14).equals(date)) {
	    	JOptionPane.showMessageDialog(btn1,"Already registered with same data");
	    }else {
	    	//To get the unique id of the train
	    	int id=rs2.getInt(1);
	    	
	    	//To check if the train is alloted with seat position or not
	    	String q1="select count(*) from train"+id;
	    	pst2=con.prepareStatement(q1);
	    	ResultSet r=pst2.executeQuery();
	    	r.next();
	    	int c=r.getInt(1);
	    	if(c>0) {
	    			String qe3="select Train,Date1 from train"+id;
		    		pst2=con.prepareStatement(qe3);
			    	 rs2=pst2.executeQuery();
			    	 
		    	  for(int j=0;j<c;j++) {
			    	 rs2.next();
			    String exp=rs2.getString(1);
                String d1=rs2.getString(2);
                
			    	 String qe2="select id from id where d=? and Express=?";
			    		pst2=con.prepareStatement(qe2);
			    		pst2.setString(1,d1);
			    		pst2.setString(2,exp);
				    	ResultSet rs3=pst2.executeQuery();
				    	rs3.next();
				    	int k=rs3.getInt(1);
				    	
	    		String q2="delete from L"+k;
	    		pst2=con.prepareStatement(q2);
	    		pst2.executeUpdate();
	    		
	    		String q3="delete from M"+k;
	    		pst2=con.prepareStatement(q3);
	    		pst2.executeUpdate();
	    		
	    		String q4="delete from U"+k;
	    		pst2=con.prepareStatement(q4);
	    		pst2.executeUpdate();
	    		
	    		String q5="delete from RAC"+k;
	    		pst2=con.prepareStatement(q5);
	    		pst2.executeUpdate();
	    		
	    		String q6="delete from WL"+k;
	    		pst2=con.prepareStatement(q6);
	    		pst2.executeUpdate();
	    		
	    		//To update seat postion
	    		int l=Integer.parseInt(lower.getSelectedItem().toString());
	    		
	    		  for(int i=1;i<=l;i++) {
	    		    	String q7="insert into L"+k+" values(?)";
	    		    	pst2=con.prepareStatement(q7);
	    		    	pst2.setInt(1,i);
	    		    	pst2.executeUpdate();
	    		    }
	    		  
	    		  for(int i=1;i<=l;i++) {
	    		    	String q7="insert into M"+k+" values(?)";
	    		    	pst2=con.prepareStatement(q7);
	    		    	pst2.setInt(1,i);
	    		    	pst2.executeUpdate();
	    		    }
	    		  for(int i=1;i<=l;i++) {
	    		    	String q7="insert into U"+k+" values(?)";
	    		    	pst2=con.prepareStatement(q7);
	    		    	pst2.setInt(1,i);
	    		    	pst2.executeUpdate();
	    		    }
	    		  int ra=Integer.parseInt(rac.getSelectedItem().toString());
	    		  for(int i=1;i<=ra;i++) {
	    		    	String q7="insert into RAC"+k+" values(?)";
	    		    	pst2=con.prepareStatement(q7);
	    		    	pst2.setInt(1,i);
	    		    	pst2.executeUpdate();
	    		    }
	    		  
	    		  int w=Integer.parseInt(wl.getSelectedItem().toString());
	    		  for(int i=1;i<=w;i++) {
	    		    	String q7="insert into WL"+k+" values(?)";
	    		    	pst2=con.prepareStatement(q7);
	    		    	pst2.setInt(1,i);
	    		    	pst2.executeUpdate();
	    		    }
	    		  String q8="update train"+id+" set L=?,M=?,U=?,RAC=?,WL=? where Train=? and Date1=?";
		    		pst2=con.prepareStatement(q8);
		    		pst2.setInt(1, l);
		    		pst2.setInt(2, l);
		    		pst2.setInt(3, l);
		    		pst2.setInt(4, ra);
		    		pst2.setInt(5, w);
		    		pst2.setString(6, exp);
		    		pst2.setString(7, d1);
		    		pst2.executeUpdate();
		    	  }
		     //To update the data into table
		  	String qy="update Train set Tno=?,name=?,Fr=?,T=?,DTime=?,RTime=?,L=?,M=?,U=?,RAC=?,WL=?,Fare=?,date=? where id=?";
			pst2=con.prepareStatement(qy);
			pst2.setString(1, TrainNo.getText());
			pst2.setString(2, name.getText());
			pst2.setString(3, from.getText());
			pst2.setString(4, to.getText());
			pst2.setString(5, dTime);
			pst2.setString(6, rTime);
			pst2.setString(7, lower.getSelectedItem().toString());
			pst2.setString(8, middle.getSelectedItem().toString());
			pst2.setString(9, upper.getSelectedItem().toString());
			pst2.setString(10, rac.getSelectedItem().toString());
			pst2.setString(11, wl.getSelectedItem().toString());
			pst2.setString(12, fare.getText());
			pst2.setString(13,date);
			pst2.setInt(14,TrainList.g);
			pst2.executeUpdate();
			
			JOptionPane.showMessageDialog(btn1, "updated successfully");
	    	
	    	}else {
				String qy="update Train set Tno=?,name=?,Fr=?,T=?,DTime=?,RTime=?,L=?,M=?,U=?,RAC=?,WL=?,Fare=?,date=? where id=?";
				pst2=con.prepareStatement(qy);
				pst2.setString(1, TrainNo.getText());
				pst2.setString(2, name.getText());
				pst2.setString(3, from.getText());
				pst2.setString(4, to.getText());
				pst2.setString(5, dTime);
				pst2.setString(6, rTime);
				pst2.setString(7, lower.getSelectedItem().toString());
				pst2.setString(8, middle.getSelectedItem().toString());
				pst2.setString(9, upper.getSelectedItem().toString());
				pst2.setString(10, rac.getSelectedItem().toString());
				pst2.setString(11, wl.getSelectedItem().toString());
				pst2.setString(12, fare.getText());
				pst2.setString(13,date);
				pst2.setInt(14,TrainList.g);
				pst2.executeUpdate();
				
				JOptionPane.showMessageDialog(btn1, "updated successfully");
	    }
	    }
					}else
						JOptionPane.showMessageDialog(btn1,"Date must be after "+s);
											 }
										 }else
						JOptionPane.showMessageDialog(btn1,"select the appropriate ticket arrangement");
									}
								}
							}
					}
						}
					}
			
									
			}catch(Exception exp) {
				System.out.println(exp);
			}
			}

			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(469, 300, 92, 23);
		frame.getContentPane().add(btn1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(87, 71, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTrainNo = new JLabel("Train No:");
		lblTrainNo.setForeground(Color.WHITE);
		lblTrainNo.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTrainNo.setBounds(350, 71, 61, 14);
		frame.getContentPane().add(lblTrainNo);
		
		JLabel lblDepartureTime = new JLabel("Departure Time:");
		lblDepartureTime.setForeground(Color.WHITE);
		lblDepartureTime.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDepartureTime.setBounds(43, 171, 101, 14);
		frame.getContentPane().add(lblDepartureTime);
		
		JLabel lblReachTime = new JLabel("Reach Time:");
		lblReachTime.setForeground(Color.WHITE);
		lblReachTime.setFont(new Font("Calibri", Font.BOLD, 15));
		lblReachTime.setBounds(340, 171, 84, 14);
		frame.getContentPane().add(lblReachTime);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFrom.setBounds(87, 123, 46, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTo.setBounds(365, 123, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel label8 = new JLabel("Lower:");
		label8.setForeground(Color.WHITE);
		label8.setFont(new Font("Calibri", Font.BOLD, 15));
		label8.setBounds(94, 225, 46, 14);
		frame.getContentPane().add(label8);
		
		JLabel lblMiddle = new JLabel("Middle:");
		lblMiddle.setForeground(Color.WHITE);
		lblMiddle.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMiddle.setBounds(237, 225, 62, 14);
		frame.getContentPane().add(lblMiddle);
		
		JLabel lblUpper = new JLabel("Upper:");
		lblUpper.setForeground(Color.WHITE);
		lblUpper.setFont(new Font("Calibri", Font.BOLD, 15));
		lblUpper.setBounds(365, 225, 61, 14);
		frame.getContentPane().add(lblUpper);
		
		JLabel lblFare = new JLabel("Fare:");
		lblFare.setForeground(Color.WHITE);
		lblFare.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFare.setBounds(499, 225, 61, 14);
		frame.getContentPane().add(lblFare);
		
		JLabel lblRac = new JLabel("RAC:");
		lblRac.setForeground(Color.WHITE);
		lblRac.setFont(new Font("Calibri", Font.BOLD, 15));
		lblRac.setBounds(94, 286, 61, 14);
		frame.getContentPane().add(lblRac);
		
		JLabel lblWl = new JLabel("WL:");
		lblWl.setForeground(Color.WHITE);
		lblWl.setFont(new Font("Calibri", Font.BOLD, 15));
		lblWl.setBounds(238, 286, 61, 14);
		frame.getContentPane().add(lblWl);
		
		JLabel lblBookingUpto = new JLabel("Booking Upto:");
		lblBookingUpto.setForeground(Color.WHITE);
		lblBookingUpto.setFont(new Font("Calibri", Font.BOLD, 15));
		lblBookingUpto.setBounds(340, 286, 92, 14);
		frame.getContentPane().add(lblBookingUpto);
		
		btnNewButton = new JButton("Back");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton.setBounds(10, 377, 68, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblUpdateTrain = new JLabel("UPDATE TRAIN");
		lblUpdateTrain.setForeground(Color.WHITE);
		lblUpdateTrain.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUpdateTrain.setBounds(269, 11, 133, 24);
		frame.getContentPane().add(lblUpdateTrain);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\UpdateTrain.jpg"));
		lblNewLabel_1.setBounds(0, 0, 691, 411);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
