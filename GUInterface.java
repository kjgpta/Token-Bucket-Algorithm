package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class GUInterface extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUInterface frame = new GUInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUInterface() {
		setTitle("Welcome to Token Bucket Algorithm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		PrintStream ps = System.out;
		
		JLabel lblNewLabel = new JLabel("No of Maximum Token");
		lblNewLabel.setBounds(38, 66, 146, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoOfExternasl = new JLabel("No of External Devices");
		lblNoOfExternasl.setBounds(38, 103, 146, 13);
		contentPane.add(lblNoOfExternasl);
		
		t1 = new JTextField();
		t1.setBounds(258, 63, 96, 19);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(258, 100, 96, 19);
		contentPane.add(t2);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int token = Integer.parseInt(t1.getText());
				int devices = Integer.parseInt(t2.getText());
			    InternalDevice b = new InternalDevice(token);
				TokenBucketAlgorithmUser user = new TokenBucketAlgorithmUser(token,devices);
				
				user.runThreads(b);
			}
			
		});
		btnNewButton.setBounds(38, 142, 85, 21);
		contentPane.add(btnNewButton);
		
		CapturePane capturePane = new CapturePane();
		capturePane.setBounds(49, 173, 305, 84);
		contentPane.add(capturePane);
		System.setOut(new PrintStream(new StreamCapturer("", capturePane, ps)));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExternalDevice.flag = false;	
				System.out.println("Number of packets with tokens successfully transmitted :" + InternalDevice.packetsSent_Success);
				System.out.println("Number of packets with tokens Lost :" + InternalDevice.packetsSent_Lost);
			
			}
		});
		btnClose.setBounds(258, 142, 85, 21);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_1 = new JLabel("   Token Bucket Algorithm");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_1.setBounds(38, 24, 305, 32);
		contentPane.add(lblNewLabel_1);
	}
}
