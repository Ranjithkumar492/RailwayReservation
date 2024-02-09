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
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Train {

	 JFrame TrainFrame;
	JList ToList;
	JList  FromList;
	JLabel label3;
	public static int year2;
	public static int month2;
	public static int da4;
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
		times();
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
		
		JLabel label1 = new JLabel("From:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 18));
		label1.setBounds(83, 46, 56, 20);
		TrainFrame.getContentPane().add(label1);
		
	    FromList = new JList();
	    FromList.setBackground(Color.BLACK);
	    FromList.setForeground(Color.WHITE);
		FromList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int indx1=FromList.getSelectedIndex();
				int indx2=ToList.getSelectedIndex();
				if(indx2>=0) {
				if(indx1!=indx2) {
					JOptionPane.showMessageDialog(FromList,"Route not available");
				}
				}
				}
		});
		FromList.setFont(new Font("Calibri", Font.BOLD, 15));
		FromList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		FromList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Chennai Egmore", "Sivaganga", "Trichy"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		FromList.setBounds(83, 74, 163, 63);
		TrainFrame.getContentPane().add(FromList);
		
		JLabel label2 = new JLabel("To:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 18));
		label2.setBounds(83, 169, 40, 20);
		TrainFrame.getContentPane().add(label2);
		
		JList list = new JList();
		list.setBounds(83, 239, 163, -38);
		TrainFrame.getContentPane().add(list);
		
		 ToList = new JList();
		 ToList.setForeground(Color.WHITE);
		 ToList.setBackground(Color.BLACK);
		ToList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int indx1=FromList.getSelectedIndex();
				int indx2=ToList.getSelectedIndex();
				if(indx1!=indx2) {
					JOptionPane.showMessageDialog(ToList,"Route not available");
				}
			}
		});
		ToList.setFont(new Font("Calibri", Font.BOLD, 15));
		ToList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Kanyakumari", "Karaikudi Jn", "Coimbatore"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ToList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ToList.setBounds(83, 200, 163, 63);
		TrainFrame.getContentPane().add(ToList);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(332, 74, 116, 20);
		TrainFrame.getContentPane().add(dateChooser);
		
		JButton btn1 = new JButton("Search Train");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check if user selected the route or not
				int indx1=FromList.getSelectedIndex();
				int indx2=ToList.getSelectedIndex();
				if(indx1>=0 && indx2>=0) {
			 //To check if the user selected the journey date
	       if(dateChooser.getDate()==null) {
	   		JOptionPane.showMessageDialog(btn1,"please select your Journey Date");
	       }else {
				//format the date in dd-MM-yyyy
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
				String date=dateFormat.format(dateChooser.getDate());
				
				//Today's date
				Date d=new Date();
				String s=dateFormat.format(d);
				
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
				int year=Integer.parseInt(y);
				int month=Integer.parseInt(m);
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
				 year2=Integer.parseInt(y2);
				 month2=Integer.parseInt(m2);
				 da4=Integer.parseInt(da3);
				
				LocalDate Td1=LocalDate.of(year,month,da2);
				LocalDate Ud2=LocalDate.of(year2,month2,da4);
				LocalDate d3=LocalDate.of(2024,06,01);
			
				//To check if the user's date is valid
				if(Ud2.equals(Td1) || Ud2.isAfter(Td1) && Ud2.isBefore(d3)) {
					
					String time=label3.getText();
					
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
					
					 int h=0;
					 if(FromList.getSelectedIndex()==0) 
						 h=6;
					 else if(FromList.getSelectedIndex()==1)
						 h=8;
					 else if(FromList.getSelectedIndex()==2)
						 h=10;
					 
					LocalTime Dt1=LocalTime.of(h,00);
					LocalTime Ut2=LocalTime.of(hour,minute);
					
					//not allow the user to book before departure time
					if(Ut2.isAfter(Dt1)) {
						JOptionPane.showMessageDialog(btn1, "Reservation Closed..");
					}else {
					try {
						Booking b=new Booking();
						b.checking(FromList.getSelectedValue(),ToList.getSelectedValue(),date);
					b.BookingFrame.setVisible(true);
					TrainFrame.dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					}
				}else 
					JOptionPane.showMessageDialog(btn1,"Date must be between "+s+" and 31-05-2024");
				}
			
				}else
					JOptionPane.showMessageDialog(btn1,"please select your Route");
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(332, 141, 116, 23);
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
		
	    label3 = new JLabel("");
	    label3.setForeground(Color.YELLOW);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(630, 46, 81, 20);
		TrainFrame.getContentPane().add(label3);
		
		JLabel Dlabel = new JLabel("Date:");
		Dlabel.setForeground(Color.WHITE);
		Dlabel.setFont(new Font("Calibri", Font.BOLD, 18));
		Dlabel.setBounds(332, 48, 56, 20);
		TrainFrame.getContentPane().add(Dlabel);
		
		JLabel ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\train3.jpg"));
		ilabel.setBounds(0, 0, 755, 390);
		TrainFrame.getContentPane().add(ilabel);
	}
		public void times() {
			Timer t=new Timer(0,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dt=new Date();
				SimpleDateFormat st=new SimpleDateFormat("hh:mm:ss");
				String tt=st.format(dt);
				label3.setText(tt);
			}
		});
		t.start();
		
		}
		
		/*TimerTask task=new TimerTask() {
			public void run() {
				System.out.println("chart preparation complete..");
			}
		};*/
		
		 
}
