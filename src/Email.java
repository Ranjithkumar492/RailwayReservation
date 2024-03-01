import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.Date;

public class Email {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	JTextArea jtextAreaContent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Email window = new Email();
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
	public Email() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lable1 = new JLabel("To:");
		lable1.setFont(new Font("Calibri", Font.BOLD, 15));
		lable1.setBounds(79, 50, 48, 14);
		frame.getContentPane().add(lable1);
		
		JLabel lable2 = new JLabel("Subject:");
		lable2.setFont(new Font("Calibri", Font.BOLD, 15));
		lable2.setBounds(58, 91, 69, 14);
		frame.getContentPane().add(lable2);
		
		JLabel lable3 = new JLabel("Content:");
		lable3.setFont(new Font("Calibri", Font.BOLD, 15));
		lable3.setBounds(58, 131, 69, 14);
		frame.getContentPane().add(lable3);
		
		textField1 = new JTextField();
		textField1.setBounds(138, 45, 194, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(137, 86, 195, 20);
		frame.getContentPane().add(textField2);
		
		 jtextAreaContent = new JTextArea();
		jtextAreaContent.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		jtextAreaContent.setBounds(138, 124, 280, 151);
		frame.getContentPane().add(jtextAreaContent);
	}
		public void register(String email,String name,String gender) {
			
			String g="";
			if(gender.equals("Male"))
				g="Mr.";
			else if(gender.equals("Female"))
				g="Ms.";
				
		textField1.setText(email);
		textField2.setText("Online Rail Ticket Reservation User Registration Confirmation");
		jtextAreaContent.setText("Dear "+g+name+",Thank you for your registration at our Online Rail Ticket Reservation.Book your berths, Happy Journey!!");
         
		try {
			//store the properties needed to access SMTP server
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			//sender's user name(email) and password
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}


	}
	public void sendMail(String name,String berth,String email,String date,String Express,String gender) {
		String g="";
		if(gender.equals("Male"))
			g="Mr.";
		else if(gender.equals("Female"))
			g="Ms.";
			
			textField1.setText(email);
			textField2.setText("Regarding Confirmation for your berth");
			jtextAreaContent.setText("Train: "+Express+" \nDate: "+date+" \nHi "+g+name+", ticket is confirmed for you!..You are moved from RAC to "+berth+". check your bookedlist for detail..Thank You!");
		
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}

	}
	public  void confirmMail(String email,String s1) {
		textField1.setText(email);
		textField2.setText("Final Confirmation for your bookings");
		jtextAreaContent.setText(s1);
	    
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}

	}
	public void setName(String email,String oname,String Nname) {
		textField1.setText(email);
		textField2.setText("Regarding the profile changes");
		jtextAreaContent.setText("Hi "+oname+", your name was changed to "+Nname+" as your preference..");
	    
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}

	}
	public void setEmail(String s1,String s2,String name) {
		textField1.setText(s2);
		textField2.setText("Regarding the profile changes");
		jtextAreaContent.setText("Hi "+name+", your email was changed to "+s1+" as your preference..");
	    
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}
	}
	public void setMobile(String name,String email,String mobile) {
		textField1.setText(email);
		textField2.setText("Regarding the profile changes");
		jtextAreaContent.setText("Hi "+name+", your mobile number was changed to "+mobile+" as your preference..");
	    
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}

	}
	public void setPassword(String email,String name) {
		textField1.setText(email);
		textField2.setText("Regarding the profile changes");
		jtextAreaContent.setText("Hi "+name+", your password was changed as your preference..");
	    
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			String username="railresv492@gmail.com";
			String password="xrvomffgkmzsgtcl";
			Session session=Session.getDefaultInstance(properties,new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message=new MimeMessage(session);
			message.setSubject(textField2.getText());
			message.setContent(jtextAreaContent.getText(),"text/plain");
			message.setFrom(new InternetAddress(textField1.getText()));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(textField1.getText(), false));
			message.setSentDate(new Date());
		     
			Transport.send(message);
			}catch(Exception t) {
				System.out.println(t);
			}

	}
}
