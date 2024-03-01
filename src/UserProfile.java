import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class UserProfile {

	 JFrame frame;
	  JTable table1;
	  JTable table2;
	  JTable table3;
	  JTable table4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfile window = new UserProfile();
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
	public UserProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 766, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Users Request");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel.setBounds(300, 11, 153, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Change Name:");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 55, 105, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 89, 305, 95);
		frame.getContentPane().add(scrollPane);
		
		table1 = new JTable();
		table1.setBackground(Color.WHITE);
		scrollPane.setViewportView(table1);
		
		JButton btn1 = new JButton("Update");
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Connection con=DbConnection.getConnection();
					 //To check if request received or not
					 String qy="select count(*) from name";
					 PreparedStatement pst=con.prepareStatement(qy);
					 ResultSet rs=pst.executeQuery();
					 rs.next();
					 if(rs.getInt(1)==0) {
						 JOptionPane.showMessageDialog(btn1, "Not received any requests yet");
					 }
					 else {
					int indx=table1.getSelectedRow();
					
					if(indx>=0) {
					DefaultTableModel model=(DefaultTableModel) table1.getModel();
				     String oname=model.getValueAt(indx, 0).toString();
				     String Nname=model.getValueAt(indx, 1).toString();
				     
				     String email=model.getValueAt(indx, 2).toString();
				     
				     //To change the name
				     String q2="update registration set name=? where email=?";
				     pst=con.prepareStatement(q2);
				     pst.setString(1,Nname);
				     pst.setString(2,email);
				     pst.executeUpdate();
				     
				     String q3="delete from name where email=?";
				     pst=con.prepareStatement(q3);
				     pst.setString(1,email);
				     pst.executeUpdate();
				     
				     String q5="select count(*) from name";
				     pst=con.prepareStatement(q5);
				     rs=pst.executeQuery();
				     rs.next();
				     if(rs.getInt(1)==0) {
				    	 table1.setModel(new DefaultTableModel());
				    	 Adminpage.label1.setText("");
						 EditProfile.msg="";
				     }else {
				    	 table1.setModel(new DefaultTableModel());
				     String q4="select old_name,new_name from name";
						 con=DbConnection.getConnection();
					     pst=con.prepareStatement(q4);
				         rs=pst.executeQuery();
						
			    	ResultSetMetaData rsmd=rs.getMetaData();
			    	 model=(DefaultTableModel) table1.getModel();
			    	
			    	int cols=rsmd.getColumnCount();
			    	String[] colName=new String[cols];
			    	for(int i=0;i<cols;i++)
			    		colName[i]=rsmd.getColumnName(i+1);
			    	
			    	model.setColumnIdentifiers(colName);
			    	
			    	String old_name,new_name;
			    	while(rs.next()) {
			    		old_name=rs.getString(1);
			    		new_name=rs.getString(2);
			    		
			    		String[] row= {old_name,new_name};
			    		
			    		model.addRow(row);
			    	}
			    	
				     }
				     JOptionPane.showMessageDialog(btn1, "Name get changed");
				     Email em=new Email();
				     em.setName(email, oname, Nname);
				}else
					 JOptionPane.showMessageDialog(btn1,"choose the name to change");
					 }
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(158, 195, 89, 23);
		frame.getContentPane().add(btn1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Change Email:");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(20, 239, 105, 23);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 262, 305, 89);
		frame.getContentPane().add(scrollPane_1);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
		JButton btn2 = new JButton("Update");
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 try {
                	 Connection con=DbConnection.getConnection();
					 String qy="select count(*) from u_email";
					 PreparedStatement pst=con.prepareStatement(qy);
					 ResultSet rs=pst.executeQuery();
					 rs.next();
					 if(rs.getInt(1)==0) {
						 JOptionPane.showMessageDialog(frame, "Not received any requests yet");
					 }else {
					int indx=table2.getSelectedRow();
					
					if(indx>=0) {
					DefaultTableModel model=(DefaultTableModel) table2.getModel();
				     String oemail=model.getValueAt(indx, 0).toString();
				     String nemail=model.getValueAt(indx, 1).toString();
				     
				     //To check if the same user send other request or not
				     String qr="select count(*) from name where email=?";
				     pst=con.prepareStatement(qr);
				     pst.setString(1,oemail);
				     rs=pst.executeQuery();
				     rs.next();
				     int n=rs.getInt(1);
				     
				     String qr2="select count(*) from mobile where email=?";
				     pst=con.prepareStatement(qr2);
				     pst.setString(1,oemail);
				     rs=pst.executeQuery();
				     rs.next();
				     int m=rs.getInt(1);
				     
				     String qr3="select count(*) from password where email=?";
				     pst=con.prepareStatement(qr3);
				     pst.setString(1,oemail);
				     rs=pst.executeQuery();
				     rs.next();
				     int p=rs.getInt(1);
				     
				     if(n>0 || m>0 || p>0) {
				    	 JOptionPane.showMessageDialog(frame,"There are other requests from same user..\nchange that before");
				     }else {
				    	 
				    String qu1="select count(*) from Train";
				    pst=con.prepareStatement(qu1);
				     rs=pst.executeQuery();
				    rs.next();
				    int c1=rs.getInt(1);
				    
				    String qu="select id from Train";
				    pst=con.prepareStatement(qu);
				    ResultSet rt=pst.executeQuery();
				    
				    for(int i=0;i<c1;i++) {
				    	rt.next();
				    	int id=rt.getInt(1);
				    	
				    	String qu2="select count(*) from train"+id;
				    	pst=con.prepareStatement(qu2);
				    	ResultSet r=pst.executeQuery();
				    	r.next();
				    	int c2=r.getInt(1);
				    	if(c2>0) {
				    		String qu3="select Train,Date1 from train"+id;
				    		pst=con.prepareStatement(qu3);
				    		r=pst.executeQuery();
				    		
				    		for(int j=0;j<c2;j++) {
				    			r.next();
				    			String qu4="select id from id where d=? and Express=?";
				    			pst=con.prepareStatement(qu4);
				    			pst.setString(1,r.getString(2));
				    			pst.setString(2, r.getString(1));
				    			rs=pst.executeQuery();
				    			rs.next();
				    			int k=rs.getInt(1);
				    			
				    			String qu5="select count(*) from bookedlist"+k+" where email=?";
				    			pst=con.prepareStatement(qu5);
				    			pst.setString(1,oemail);
				    			rs=pst.executeQuery();
				    			rs.next();
				    			
				    			if(rs.getInt(1)>0) {
				    				String qu6="update bookedlist"+k+" set email=? where email=?";
				    				pst=con.prepareStatement(qu6);
				    				pst.setString(1,nemail);
				    				pst.setString(2,oemail);
				    				pst.executeUpdate();
				    			}
				    			
				    			String qu6="select count(*) from rac_list"+k+" where email=?";
				    			pst=con.prepareStatement(qu6);
				    			pst.setString(1,oemail);
				    			rs=pst.executeQuery();
				    			rs.next();
				    			if(rs.getInt(1)>0) {
				    				String qu7="update rac_list"+k+" set email=? where email=?";
				    				pst=con.prepareStatement(qu7);
				    				pst.setString(1,nemail);
				    				pst.setString(2,oemail);
				    				pst.executeUpdate();
				    			}
				    			
				    			String qu8="select count(*) from waiting_list"+k+" where email=?";
				    			pst=con.prepareStatement(qu8);
				    			pst.setString(1,oemail);
				    			rs=pst.executeQuery();
				    			rs.next();
				    			if(rs.getInt(1)>0) {
				    				String qu9="update waiting_list"+k+" set email=? where email=?";
				    				pst=con.prepareStatement(qu9);
				    				pst.setString(1,nemail);
				    				pst.setString(2,oemail);
				    				pst.executeUpdate();
				    			}
				    		}
				    		
				    	}
				    	String qu10="select count(*) from fare"+id+" where id=?";
				    	pst=con.prepareStatement(qu10);
				    	pst.setString(1,oemail);
				    	rs=pst.executeQuery();
				    	rs.next();
				    	if(rs.getInt(1)>0) {
				    		String qu11="update fare"+id+" set id=? where id=?";
				    		pst=con.prepareStatement(qu11);
				    		pst.setString(1,nemail);
				    		pst.setString(2,oemail);
				    		pst.executeUpdate();
				    	}
				    }
				    
				     String q1="select name from u_email where old_email=?";
				     pst=con.prepareStatement(q1);
				     pst.setString(1,oemail);
				     rs=pst.executeQuery();
				     rs.next();
				     String name=rs.getString(1);
				     
				     String q2="update registration set email=? where email=?";
				     pst=con.prepareStatement(q2);
				     pst.setString(1,nemail);
				     pst.setString(2,oemail);
				     pst.executeUpdate();
				     
				     String q3="delete from u_email where old_email=?";
				     pst=con.prepareStatement(q3);
				     pst.setString(1,oemail);
				     pst.executeUpdate();
				     
				     String q5="select count(*) from u_email";
				     pst=con.prepareStatement(q5);
				     rs=pst.executeQuery();
				     rs.next();
				     if(rs.getInt(1)==0) {
				    	 table2.setModel(new DefaultTableModel());
				    	 Adminpage.label2.setText("");
						 EditProfile.msg2="";
				     }else {
				    	 table2.setModel(new DefaultTableModel());
				     String q4="select old_email,new_email from u_email";
						 con=DbConnection.getConnection();
					     pst=con.prepareStatement(q4);
				         rs=pst.executeQuery();
						
			    	ResultSetMetaData rsmd=rs.getMetaData();
			    	 model=(DefaultTableModel) table2.getModel();
			    	
			    	int cols=rsmd.getColumnCount();
			    	String[] colName=new String[cols];
			    	for(int i=0;i<cols;i++)
			    		colName[i]=rsmd.getColumnName(i+1);
			    	
			    	model.setColumnIdentifiers(colName);
			    	
			    	String old_email,new_email;
			    	while(rs.next()) {
			    		old_email=rs.getString(1);
			    		new_email=rs.getString(2);
			    		
			    		String[] row= {old_email,new_email};
			    		
			    		model.addRow(row);
			    	}
			    	
				     }
				     JOptionPane.showMessageDialog(frame, "Email get changed");
				     Email em=new Email();
				     em.setEmail(nemail,oemail, name);
				}
					}else
					 JOptionPane.showMessageDialog(frame,"choose the email to change");
					 }
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(158, 362, 89, 23);
		frame.getContentPane().add(btn2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Change MobileNo:");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(405, 57, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(425, 90, 296, 89);
		frame.getContentPane().add(scrollPane_2);
		
		table3 = new JTable();
		scrollPane_2.setViewportView(table3);
		
		JButton btn3 = new JButton("Update");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 Connection con=DbConnection.getConnection();
					 String qr="select count(*) from mobile";
					 PreparedStatement pst=con.prepareStatement(qr);
					 ResultSet rs=pst.executeQuery();
					 rs.next();
					 if(rs.getInt(1)==0) {
						 JOptionPane.showMessageDialog(btn3, "Not received any requests yet");
					 }else {
						int indx=table3.getSelectedRow();
						
						if(indx>=0) {
						DefaultTableModel model=(DefaultTableModel) table3.getModel();
					     String omobile=model.getValueAt(indx, 0).toString();
					     String nmobile=model.getValueAt(indx, 1).toString();
					     String email=model.getValueAt(indx, 2).toString();
					     
					     String q1="select name from mobile where old_mobileNo=?";
					     pst=con.prepareStatement(q1);
					     pst.setString(1,omobile);
					     rs=pst.executeQuery();
					     rs.next();
					     String name=rs.getString(1);
					     
					     String q2="update registration set mobile=? where email=?";
					     pst=con.prepareStatement(q2);
					     pst.setString(1,nmobile);
					     pst.setString(2,email);
					     pst.executeUpdate();
					     
					     String q3="delete from mobile where email=?";
					     pst=con.prepareStatement(q3);
					     pst.setString(1,email);
					     pst.executeUpdate();
					     
					     String q5="select count(*) from mobile";
					     pst=con.prepareStatement(q5);
					     rs=pst.executeQuery();
					     rs.next();
					     if(rs.getInt(1)==0) {
					    	 table3.setModel(new DefaultTableModel());
					    	 Adminpage.label3.setText("");
							 EditProfile.msg3="";
					     }else {
					    	 table3.setModel(new DefaultTableModel());
					     String q4="select old_mobileNo,new_mobileNo from mobile";
							 con=DbConnection.getConnection();
						     pst=con.prepareStatement(q4);
					         rs=pst.executeQuery();
							
				    	ResultSetMetaData rsmd=rs.getMetaData();
				    	 model=(DefaultTableModel) table3.getModel();
				    	
				    	int cols=rsmd.getColumnCount();
				    	String[] colName=new String[cols];
				    	for(int i=0;i<cols;i++)
				    		colName[i]=rsmd.getColumnName(i+1);
				    	
				    	model.setColumnIdentifiers(colName);
				    	
				    	String old_mobileNo,new_mobileNo;
				    	while(rs.next()) {
				    		old_mobileNo=rs.getString(1);
				    		new_mobileNo=rs.getString(2);
				    		
				    		String[] row= {old_mobileNo,new_mobileNo};
				    		
				    		model.addRow(row);
				    	}
				    	
					     }
					     JOptionPane.showMessageDialog(btn3, "Mobile number get changed");
					     Email em=new Email();
					     em.setMobile(name, email, nmobile);
					}else
						 JOptionPane.showMessageDialog(btn3,"choose the Mobile number to change");
					 }
					}catch(Exception exp) {
						System.out.println(exp);
					}

			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(510, 190, 89, 23);
		frame.getContentPane().add(btn3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Change Password:");
		lblNewLabel_1_2_1.setForeground(Color.RED);
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(405, 239, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(425, 261, 296, 90);
		frame.getContentPane().add(scrollPane_3);
		
		table4 = new JTable();
		scrollPane_3.setViewportView(table4);
		
		JButton btn4 = new JButton("Update");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 Connection con=DbConnection.getConnection();
					 String qr="select count(*) from password";
					 PreparedStatement pst=con.prepareStatement(qr);
					 ResultSet rs=pst.executeQuery();
					 rs.next();
					 if(rs.getInt(1)==0) {
						 JOptionPane.showMessageDialog(frame, "Not received any requests yet");
					 }else {
						int indx=table4.getSelectedRow();
						
						if(indx>=0) {
						DefaultTableModel model=(DefaultTableModel) table4.getModel();
					     String opassword=model.getValueAt(indx, 0).toString();
					     String npassword=model.getValueAt(indx, 1).toString();
					     String email=model.getValueAt(indx, 2).toString();
					     
					     String q1="select name from password where old_password=?";
					     pst=con.prepareStatement(q1);
					     pst.setString(1,opassword);
					     rs=pst.executeQuery();
					     rs.next();
					     String name=rs.getString(1);
					     
					     String q2="update registration set password=? where email=?";
					     pst=con.prepareStatement(q2);
					     pst.setString(1,npassword);
					     pst.setString(2,email);
					     pst.executeUpdate();
					     
					     String q3="delete from password where email=?";
					     pst=con.prepareStatement(q3);
					     pst.setString(1,email);
					     pst.executeUpdate();
					     
					     String q5="select count(*) from password";
					     pst=con.prepareStatement(q5);
					     rs=pst.executeQuery();
					     rs.next();
					     if(rs.getInt(1)==0) {
					    	 table4.setModel(new DefaultTableModel());
					    	 Adminpage.label4.setText("");
							 EditProfile.msg4="";
					     }else {
					    	 table4.setModel(new DefaultTableModel());
					     String q4="select old_password,new_password from password";
							 con=DbConnection.getConnection();
						     pst=con.prepareStatement(q4);
					         rs=pst.executeQuery();
							
				    	ResultSetMetaData rsmd=rs.getMetaData();
				    	 model=(DefaultTableModel) table4.getModel();
				    	
				    	int cols=rsmd.getColumnCount();
				    	String[] colName=new String[cols];
				    	for(int i=0;i<cols;i++)
				    		colName[i]=rsmd.getColumnName(i+1);
				    	
				    	model.setColumnIdentifiers(colName);
				    	
				    	String old_password,new_password;
				    	while(rs.next()) {
				    		old_password=rs.getString(1);
				    		new_password=rs.getString(2);
				    		
				    		String[] row= {old_password,new_password};
				    		
				    		model.addRow(row);
				    	}
				    	
					     }
					     JOptionPane.showMessageDialog(frame, "Password get changed");
					     Email em=new Email();
					     em.setPassword(email, name);
					}else
						 JOptionPane.showMessageDialog(frame,"choose the Password to change");
					 }
					}catch(Exception exp) {
						System.out.println(exp);
					}

			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(510, 360, 89, 23);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("Back");
		btn5.setBackground(Color.BLACK);
		btn5.setForeground(Color.WHITE);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminpage p=new Adminpage();
				p.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn5.setFont(new Font("Calibri", Font.BOLD, 15));
		btn5.setBounds(10, 377, 89, 23);
		frame.getContentPane().add(btn5);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\userprofile.jpg"));
		lblNewLabel_2.setBounds(0, 0, 750, 411);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
