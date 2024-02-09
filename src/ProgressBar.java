import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class ProgressBar  implements Runnable {

	 JFrame PFrame;
	JProgressBar progressBar1;
	JLabel mylable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgressBar window = new ProgressBar();
					window.PFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProgressBar() {
		initialize();
		progressBar1.setForeground(Color.blue);
		
		Thread t=new Thread(this);
		t.start();
		
	}
	public void run() {
		for(int i=1;i<=100;i++) {
			mylable.setText(i+" %");
			progressBar1.setValue(i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Home h=new Home();
		h.HomeFrame.setVisible(true);
		PFrame.setVisible(false);
		}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PFrame = new JFrame();
		PFrame.setBounds(100, 100, 282, 170);
		PFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PFrame.setLocationRelativeTo(null);
		PFrame.getContentPane().setLayout(null);
		
		 progressBar1 = new JProgressBar();
		progressBar1.setBounds(55, 74, 146, 22);
		PFrame.getContentPane().add(progressBar1);
		
		mylable = new JLabel("");
		mylable.setBounds(109, 49, 46, 14);
		PFrame.getContentPane().add(mylable);
		
	}
 

}
