import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TrainList {

	JFrame ListFrame;
	JTable table;
	private JScrollPane scrollPane;
	private JButton btn1;
	private JButton btn2;
    public static  int g=0;
    private JLabel lblTrainName;
    private JTextField textField1;
    private JButton btn4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainList window = new TrainList();
					window.ListFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrainList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ListFrame = new JFrame();
		ListFrame.setBounds(100, 100, 786, 384);
		ListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ListFrame.setLocationRelativeTo(null);
		ListFrame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 112, 720, 69);
		ListFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel label1 = new JLabel("Train List");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Calibri", Font.BOLD, 22));
		label1.setBounds(303, 11, 89, 23);
		ListFrame.getContentPane().add(label1);
		
		btn1 = new JButton("Delete");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indx=table.getSelectedRow();
				if(indx>=0) {
					//To get the unique train id 
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			     int g=Integer.parseInt(model.getValueAt(indx, 0).toString());
			     
			     try {
			    	 //To check if the train has bookings
			    	 String qr="select count(*) from train"+g;
			    	 Connection con=DbConnection.getConnection();
			    	 PreparedStatement pst=con.prepareStatement(qr);
			    	 ResultSet rs=pst.executeQuery();
			    	 rs.next();
			    	 int count=0;
			    	 if(rs.getInt(1)>0) {
			    		 String qr2="select Train,Date1 from train"+g;
			    		 pst=con.prepareStatement(qr2);
			    		 rs=pst.executeQuery();
	
			    		 while(rs.next()) {
			    			 
			    			 String qr3="select id from id where d=? and Express=?";
			    			 pst=con.prepareStatement(qr3);
			    			 pst.setString(1,rs.getString(2));
			    			 pst.setString(2,rs.getString(1));
			    			 ResultSet rs2=pst.executeQuery();
			    			 rs2.next();
			    			 int k=rs2.getInt(1);
			    			 
			    			 String qr4="select count(*) from bookedlist"+k;
			    			 pst=con.prepareStatement(qr4);
			    			 rs2=pst.executeQuery();
			    			 rs2.next();
			    			  count=rs2.getInt(1);
			    			  
			    			 if(count>0) {
			    				 break;
			    			 }
			    		 }
			    	 }
			    	 //To delete the data related to the train
			    	 if(count==0) {
			    	 int response=JOptionPane.showConfirmDialog(btn1,"Are you sure, want to delete the train?");
			    	 if(response==JOptionPane.YES_OPTION) {
			    	 String q="drop table train"+g;
			    	 pst=con.prepareStatement(q);
			    	 pst.executeUpdate();
			    	 
			    	 String q2="drop table fare"+g;
			    	 pst=con.prepareStatement(q2);
			    	 pst.executeUpdate();
			    	 
			    	 String q3="delete from Train where id=?";
			    	 pst=con.prepareStatement(q3);
			    	 pst.setInt(1,g);
			    	 pst.executeUpdate();
			    	 
			    	 table.setModel(new DefaultTableModel());
			    	 
			    	 String q4="select * from Train";
		    		    pst=con.prepareStatement(q4);
		    		    rs=pst.executeQuery();
		    			 
		    			 ResultSetMetaData rsmd=rs.getMetaData();
		    		      model=(DefaultTableModel) table.getModel();
		    		    	
		    		    	int cols=rsmd.getColumnCount();
		    		    	String[] colName=new String[cols];
		    		    	for(int i=0;i<cols;i++)
		    		    		colName[i]=rsmd.getColumnName(i+1);
		    		    
		    		    	model.setColumnIdentifiers(colName);
		    		    	
		    		    	String id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare;
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
		    		    		
		    		    		String[] row= {id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare};
		    		    		
		    		    		model.addRow(row);
		    		    	}
		    		    	 JOptionPane.showMessageDialog(btn1,"Train gets deleted..");
			    	 }
			    		 
			    	 }else
			    		 JOptionPane.showMessageDialog(btn1,"Train contains bookings..check before delete");
			     }catch(Exception exp) {
			    	 System.out.println(exp);
			     }
			}else
				JOptionPane.showMessageDialog(btn1,"select the train that you want to delete");
			}
			
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(220, 262, 79, 23);
		ListFrame.getContentPane().add(btn1);
		
		btn2 = new JButton("Update");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indx=table.getSelectedRow();
				if(indx>=0) {
					//To get the unique id of train
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			      g=Integer.parseInt(model.getValueAt(indx, 0).toString());
			     
			     try {
			    	 //To check if the train has bookings
			    	 String qr="select count(*) from train"+g;
			    	 Connection con=DbConnection.getConnection();
			    	 PreparedStatement pst=con.prepareStatement(qr);
			    	 ResultSet rs=pst.executeQuery();
			    	 rs.next();
			    	 int count=0;
			    	 if(rs.getInt(1)>0) {
			    		 String qr2="select Train,Date1 from train"+g;
			    		 pst=con.prepareStatement(qr2);
			    		 rs=pst.executeQuery();
	
			    		 while(rs.next()) {
			    			 
			    			 String qr3="select id from id where d=? and Express=?";
			    			 pst=con.prepareStatement(qr3);
			    			 pst.setString(1,rs.getString(2));
			    			 pst.setString(2,rs.getString(1));
			    			 ResultSet rs2=pst.executeQuery();
			    			 rs2.next();
			    			 int k=rs2.getInt(1);
			    			 
			    			 String qr4="select count(*) from bookedlist"+k;
			    			 pst=con.prepareStatement(qr4);
			    			 rs2=pst.executeQuery();
			    			 rs2.next();
			    			  count=rs2.getInt(1);
			    			 if(count>0) {
			    				 break;
			    			 }
			    		 }
			    	 }
			    	 if(count==0) {
			    	//To set the details of train which is going to be updated
			    	 String q="select * from Train where id=?";
			    	 pst=con.prepareStatement(q);
			    	 pst.setInt(1,g);
			    	 rs=pst.executeQuery();
			    	 rs.next();
			    	 UpdateTrain a=new UpdateTrain();
			
			    	 a.TrainNo.setText(rs.getString(2));
			    	 a.name.setText(rs.getString(3));
			    	 a.from.setText(rs.getString(4));
			    	 a.to.setText(rs.getString(5));
			    	String s1= rs.getString(6);
			    	String Dh="",Dm="";
			    	for(int i=0;i<s1.length();i++) {
			    		if(s1.charAt(i)==':')
			    			continue;
			    		else if(Dh.length()!=2)
			    			Dh=Dh+s1.charAt(i);
			    		else 
			    			Dm=Dm+s1.charAt(i);
			    	}
			    	 a.comboBox1.setSelectedItem(Dh);
			    	 a.comboBox2.setSelectedItem(Dm);
			    	 
			    	 String s2=rs.getString(7);
			    	 String Rh="",Rm="";
				    	for(int i=0;i<s2.length();i++) {
				    		if(s2.charAt(i)==':')
				    			continue;
				    		else if(Rh.length()!=2)
				    			Rh=Rh+s2.charAt(i);
				    		else 
				    			Rm=Rm+s2.charAt(i);
				    	}
				    	 a.comboBox3.setSelectedItem(Rh);
				    	 a.comboBox4.setSelectedItem(Rm);
				    	 
			    	 a.lower.setSelectedItem(rs.getString(8));
			    	 a.middle.setSelectedItem(rs.getString(9));
			    	 a.upper.setSelectedItem(rs.getString(10));
			    	 a.rac.setSelectedItem(rs.getString(11));
			    	 a.wl.setSelectedItem(rs.getString(12));
			    	 a.fare.setText(rs.getString(13));
			    	 
			    	 SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
					 Date d=formatter.parse(rs.getString(14));
					 a.dateChooser.setDate(d);
			    	 a.frame.setVisible(true);
			    	 ListFrame.dispose();
			     }else
			    	 JOptionPane.showMessageDialog(btn2,"Train contain bookings..Data may change while updating \ncheck the train you want to update");
			     }catch(Exception exp) {
			    	 System.out.println(exp);
			     }
			}else
				JOptionPane.showMessageDialog(btn2,"select the train that You want to update");
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(374, 262, 89, 23);
		ListFrame.getContentPane().add(btn2);
		
		lblTrainName = new JLabel("Train Name:");
		lblTrainName.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTrainName.setBounds(36, 70, 79, 23);
		ListFrame.getContentPane().add(lblTrainName);
		
		textField1 = new JTextField();
		textField1.setBounds(116, 69, 152, 20);
		ListFrame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JButton btn3 = new JButton("Search");
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);
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
						
						String q3="select * from Train where name=?";
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
		    		    	
		    		    	String id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare;
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
		    		    		
		    		    		String[] row= {id,TrainNo,name,From,To,DTime,RTime,L,M,U,RAC,WL,Fare};
		    		    		
		    		    		model.addRow(row);
		    		    	}
						
					}else
						JOptionPane.showMessageDialog(btn3, "Train not available");
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}else
				JOptionPane.showMessageDialog(btn3, "Enter the train name");
			}
		});
		btn3.setFont(new Font("Calibri", Font.BOLD, 15));
		btn3.setBounds(323, 70, 79, 23);
		ListFrame.getContentPane().add(btn3);
		
		btn4 = new JButton("Back");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminpage p=new Adminpage();
				p.frame.setVisible(true);
				ListFrame.dispose();
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(10, 311, 79, 23);
		ListFrame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("Reset");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new DefaultTableModel());
					Connection con=DbConnection.getConnection();
				String q3="select * from Train";
    		    PreparedStatement pst=con.prepareStatement(q3);
    			ResultSet rs=pst.executeQuery();
    			 
    			 ResultSetMetaData rsmd=rs.getMetaData();
    		     DefaultTableModel model=(DefaultTableModel) table.getModel();
    		    	
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
		btn5.setForeground(Color.WHITE);
		btn5.setFont(new Font("Calibri", Font.BOLD, 15));
		btn5.setBackground(Color.BLACK);
		btn5.setBounds(446, 68, 79, 23);
		ListFrame.getContentPane().add(btn5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\trainlist.jpg"));
		lblNewLabel.setBounds(0, 0, 770, 345);
		ListFrame.getContentPane().add(lblNewLabel);
	}
}
