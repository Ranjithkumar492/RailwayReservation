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
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class AddTrain {

    JFrame frame;
	 JTextField name;
	 JTextField TrainNo;
	 JTextField from;
	 JTextField to;
	 JTextField fare;
	 JDateChooser dateChooser;
	 JComboBox comboBox1;
	 JComboBox comboBox2;
	 JComboBox comboBox3;
	 JComboBox comboBox4;
	 JComboBox lower;
	 JComboBox middle;
	 JComboBox upper;
	 JComboBox rac;
	 JComboBox wl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTrain window = new AddTrain();
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
	public AddTrain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Add Train");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Calibri", Font.BOLD, 20));
		label1.setBounds(43, 41, 96, 25);
		frame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Name:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(114, 87, 46, 14);
		frame.getContentPane().add(label2);
		
		name = new JTextField();
		name.setBounds(170, 82, 140, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel label3 = new JLabel("Train no:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(395, 87, 60, 14);
		frame.getContentPane().add(label3);
		
		TrainNo = new JTextField();
		TrainNo.setColumns(10);
		TrainNo.setBounds(465, 82, 140, 20);
		frame.getContentPane().add(TrainNo);
		
		JLabel label4 = new JLabel("From:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(114, 130, 46, 14);
		frame.getContentPane().add(label4);
		
		from = new JTextField();
		from.setColumns(10);
		from.setBounds(170, 125, 140, 20);
		frame.getContentPane().add(from);
		
		JLabel label5 = new JLabel("To:");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(426, 130, 46, 14);
		frame.getContentPane().add(label5);
		
		to = new JTextField();
		to.setColumns(10);
		to.setBounds(465, 125, 140, 20);
		frame.getContentPane().add(to);
		
		JLabel label6 = new JLabel("Departure Time:");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(53, 173, 107, 14);
		frame.getContentPane().add(label6);
		
		JLabel label7 = new JLabel("Reach Time:");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(369, 173, 86, 14);
		frame.getContentPane().add(label7);
		
		JLabel label8 = new JLabel("Lower:");
		label8.setForeground(Color.WHITE);
		label8.setFont(new Font("Calibri", Font.BOLD, 15));
		label8.setBounds(114, 234, 46, 14);
		frame.getContentPane().add(label8);
		
		JLabel lblMiddle = new JLabel("Middle:");
		lblMiddle.setForeground(Color.WHITE);
		lblMiddle.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMiddle.setBounds(245, 232, 65, 14);
		frame.getContentPane().add(lblMiddle);
		
		JLabel lblUpper = new JLabel("Upper:");
		lblUpper.setForeground(Color.WHITE);
		lblUpper.setFont(new Font("Calibri", Font.BOLD, 15));
		lblUpper.setBounds(390, 232, 65, 14);
		frame.getContentPane().add(lblUpper);
		
		JLabel lblRac = new JLabel("RAC:");
		lblRac.setForeground(Color.WHITE);
		lblRac.setFont(new Font("Calibri", Font.BOLD, 15));
		lblRac.setBounds(114, 294, 46, 14);
		frame.getContentPane().add(lblRac);
		
		JLabel lblWl = new JLabel("WL:");
		lblWl.setForeground(Color.WHITE);
		lblWl.setFont(new Font("Calibri", Font.BOLD, 15));
		lblWl.setBounds(245, 292, 65, 14);
		frame.getContentPane().add(lblWl);
		
		JButton btn1 = new JButton("ADD");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			//check all the fields are completed
			if(name.getText().equals("")||TrainNo.getText().equals("")||from.getText().equals("")||to.getText().equals("")||fare.getText().equals("")||dateChooser.getDate()==null)
				JOptionPane.showMessageDialog(btn1, "select all required fields");
			else {
			Connection con=DbConnection.getConnection();
			//To check if the train is already exist or not
			String qr="select count(*) from Train where Tno=? or name=?";
			PreparedStatement pst=con.prepareStatement(qr);
			pst.setString(1,TrainNo.getText());
			pst.setString(2, name.getText());
			ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)>0)
				JOptionPane.showMessageDialog(btn1,"Train already exist, check the train name or \ntrain number");
			else {
				//To validate the name of train
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
				//To validate train number
				String no=TrainNo.getText();
				if(no.length()>5 || no.length()<5)
					JOptionPane.showMessageDialog(btn1, "Train number should've 5 digits");
				else {
				if(no.substring(0,4).equals("0000"))
					JOptionPane.showMessageDialog(btn1, "Train number should not start with more than 3 zeros");
				else {
					//To validate the From and To location
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
							//To get the DTime and RTime from comboBox
							String DTime=comboBox1.getSelectedItem().toString()+":"+comboBox2.getSelectedItem().toString();
							String RTime=comboBox3.getSelectedItem().toString()+":"+comboBox4.getSelectedItem().toString();
							
			 int l=lower.getSelectedIndex();
			 int m=middle.getSelectedIndex();
			 int u=upper.getSelectedIndex();
			//To check the proper ticket arrangement
			 if((l==m)&&(m==u)) {
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
		          
				//To generate unique id for train
				  String q="select count(*) from Train";
	    		     Statement st=con.createStatement();
	    		   rs=st.executeQuery(q);
	    		     rs.next();
	    		     int k=0;
	    		     int f=Integer.parseInt(fare.getText());
	    		     if(rs.getInt(1)>0) {
	    		    	 String qy2="select id from Train order by id DESC limit 1";
	    		    	 rs=st.executeQuery(qy2);
	    		    	 rs.next();
	    		    	 k=rs.getInt(1)+1;
	    		    	 
	    		    	 //insert the train details into table
	    		    	 String qy3="insert into Train values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    		    	pst=con.prepareStatement(qy3);
	    		    	 pst.setInt(1, k);
	    		    	 pst.setString(2,TrainNo.getText());
	    		    	 pst.setString(3,name.getText());
	    		    	 pst.setString(4,from.getText());
	    		    	 pst.setString(5,to.getText());
	    		    	 pst.setString(6,DTime);
	    		    	 pst.setString(7,RTime);
	    		    	 pst.setString(8,lower.getSelectedItem().toString());
	    		    	 pst.setString(9,middle.getSelectedItem().toString());
	    		    	 pst.setString(10,upper.getSelectedItem().toString());
	    		    	 pst.setString(11,rac.getSelectedItem().toString());
	    		    	 pst.setString(12,wl.getSelectedItem().toString());
	    		    	 pst.setInt(13, f);
	    		    	 pst.setString(14,date);
	    		    	 pst.executeUpdate();
	    		    	 
	    		    	 //create separate table for that train with unique id
	    		    	 String q2="create table train"+k+" (Train varchar(10),L int,M int,U int,RAC int,WL int,Date1 varchar(20) primary key)";
							pst=con.prepareStatement(q2);
							pst.executeUpdate();
							
						//create separate fare table for that train with unique id to calculate fare
							String q3="create table fare"+k+" (Fare int,date varchar(20),id varchar(40),cancel int)";
							pst=con.prepareStatement(q3);
							pst.executeUpdate();
	    		     }else {
	    		    	 k=1;
	    		    	 String qy="insert into Train values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    		    	 pst=con.prepareStatement(qy);
	    		    	 pst.setInt(1, k);
	    		    	 pst.setString(2,TrainNo.getText());
	    		    	 pst.setString(3,name.getText());
	    		    	 pst.setString(4,from.getText());
	    		    	 pst.setString(5,to.getText());
	    		    	 pst.setString(6,DTime);
	    		    	 pst.setString(7,RTime);
	    		    	 pst.setString(8,lower.getSelectedItem().toString());
	    		    	 pst.setString(9,middle.getSelectedItem().toString());
	    		    	 pst.setString(10,upper.getSelectedItem().toString());
	    		    	 pst.setString(11,rac.getSelectedItem().toString());
	    		    	 pst.setString(12,wl.getSelectedItem().toString());
	    		    	 pst.setInt(13, f);
	    		    	 pst.setString(14,date);
	    		    	  pst.executeUpdate();
	    		    	  
	    		    	  String q2="create table train"+k+" (Train varchar(10),L int,M int,U int,RAC int,WL int,Date1 varchar(20) primary key)";
							pst=con.prepareStatement(q2);
							pst.executeUpdate();
							
						String q3="create table fare"+k+" (Fare int,date varchar(20),id varchar(40),cancel int)";
						pst=con.prepareStatement(q3);
						pst.executeUpdate();
	    		     }
	    		     JOptionPane.showMessageDialog(btn1,"Train added successfully");
			
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
			
			}
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(519, 319, 75, 23);
		frame.getContentPane().add(btn1);
		
		JLabel lblFare = new JLabel("Fare:");
		lblFare.setForeground(Color.WHITE);
		lblFare.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFare.setBounds(526, 232, 65, 14);
		frame.getContentPane().add(lblFare);
		
		fare = new JTextField();
		fare.setColumns(10);
		fare.setBounds(519, 251, 86, 20);
		frame.getContentPane().add(fare);
		
		JButton btn2 = new JButton("Back");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminpage p=new Adminpage();
				p.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(10, 356, 75, 23);
		frame.getContentPane().add(btn2);
		
		comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox1.setBounds(181, 167, 54, 22);
		frame.getContentPane().add(comboBox1);
		
		 comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox2.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox2.setBounds(245, 167, 54, 22);
		frame.getContentPane().add(comboBox2);
		
		comboBox3 = new JComboBox();
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox3.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox3.setBounds(465, 167, 54, 22);
		frame.getContentPane().add(comboBox3);
		
		 comboBox4 = new JComboBox();
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox4.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBox4.setBounds(540, 167, 54, 22);
		frame.getContentPane().add(comboBox4);
		
		 lower = new JComboBox();
		lower.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		lower.setFont(new Font("Calibri", Font.BOLD, 15));
		lower.setBounds(114, 250, 54, 22);
		frame.getContentPane().add(lower);
		
		 middle = new JComboBox();
		middle.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		middle.setFont(new Font("Calibri", Font.BOLD, 15));
		middle.setBounds(245, 250, 54, 22);
		frame.getContentPane().add(middle);
		
		upper = new JComboBox();
		upper.setModel(new DefaultComboBoxModel(new String[] {"21", "12", "1"}));
		upper.setFont(new Font("Calibri", Font.BOLD, 15));
		upper.setBounds(386, 250, 54, 22);
		frame.getContentPane().add(upper);
		
		rac = new JComboBox();
		rac.setModel(new DefaultComboBoxModel(new String[] {"18", "2"}));
		rac.setFont(new Font("Calibri", Font.BOLD, 15));
		rac.setBounds(114, 319, 54, 22);
		frame.getContentPane().add(rac);
		
		 wl = new JComboBox();
		wl.setModel(new DefaultComboBoxModel(new String[] {"10", "2"}));
		wl.setFont(new Font("Calibri", Font.BOLD, 15));
		wl.setBounds(245, 317, 54, 22);
		frame.getContentPane().add(wl);
		
		JLabel lblBookingUpto = new JLabel("Booking upto:");
		lblBookingUpto.setForeground(Color.WHITE);
		lblBookingUpto.setFont(new Font("Calibri", Font.BOLD, 15));
		lblBookingUpto.setBounds(354, 294, 96, 14);
		frame.getContentPane().add(lblBookingUpto);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(354, 319, 101, 20);
		frame.getContentPane().add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Addtrain.jpg"));
		lblNewLabel.setBounds(0, 0, 736, 390);
		frame.getContentPane().add(lblNewLabel);
	}
}
