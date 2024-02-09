import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Home {

	 JFrame HomeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.HomeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		HomeFrame = new JFrame();
		HomeFrame.getContentPane().setBackground(Color.WHITE);
		HomeFrame.setBounds(100, 100,826, 464);
		HomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HomeFrame.setLocationRelativeTo(null);
		HomeFrame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Train Resevation System");
		label1.setFont(new Font("Calibri", Font.BOLD, 25));
		label1.setBounds(255, 28, 264, 36);
		HomeFrame.getContentPane().add(label1);
		
		JButton btn1 = new JButton("Book Ticket");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the booking class
				Train t=new Train();
				t.TrainFrame.setVisible(true);
				HomeFrame.dispose();
			}
		});
		btn1.setFont(new Font("Calibri", Font.BOLD, 15));
		btn1.setBounds(236, 173, 150, 36);
		HomeFrame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Cancel Ticket");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the cancel ticket class
				CancelTicket c=new CancelTicket();
				c.CancelFrame.setVisible(true);
				HomeFrame.dispose();
			}
		});
		btn2.setFont(new Font("Calibri", Font.BOLD, 15));
		btn2.setBounds(411, 173, 134, 36);
		HomeFrame.getContentPane().add(btn2);
		
		JButton btn4 = new JButton("My Bookings");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the mybookings class
				MyBookings m=new MyBookings();
				m.MyBookingsFrame.setVisible(true);
				HomeFrame.dispose();
			}
		});
		btn4.setFont(new Font("Calibri", Font.BOLD, 15));
		btn4.setBounds(341, 236, 122, 36);
		HomeFrame.getContentPane().add(btn4);
		
		JButton btn7 = new JButton("Back");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.LoginFrame.setVisible(true);
				HomeFrame.dispose();
			}
		});
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.BLACK);
		btn7.setFont(new Font("Calibri", Font.BOLD, 15));
		btn7.setBounds(10, 391, 70, 23);
		HomeFrame.getContentPane().add(btn7);
		
		JLabel ilabel = new JLabel("");
		ilabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Downloads\\Home.jpg"));
		ilabel.setBounds(0, 0, 810, 425);
		HomeFrame.getContentPane().add(ilabel);
	}
}
