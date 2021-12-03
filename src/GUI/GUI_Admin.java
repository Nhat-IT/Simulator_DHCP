package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Admin {
	public static void main(String[] args) {
		new GUI_Admin();
	}
	
	public GUI_Admin() {
		JLabel userLabel, passwordLabel, lblTitle, lbClass;
		JLabel lblIP, lblSM, lblDG, lblDash, lbFirstHost, lbLastHost;
		JTextField userText, txtIP, txtSM, txtDG, txtFH, txtLH;
		JPasswordField passwordText;
		JComboBox<String> cbClass;
		JButton btnLogin, btnReset, btnQuayLai;
		JLabel success;
		
		JFrame frame = new JFrame("Login");
		JPanel panel = new JPanel();
		frame.setSize(650, 700);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitle = new JLabel("LOGIN");
		lblTitle.setBounds(270, 10, 80, 25);
		panel.add(lblTitle);
		
		userLabel = new JLabel("Username");
		userLabel.setBounds(150, 50, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(280, 50, 200, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(150, 100, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(280, 100, 200, 25);
		panel.add(passwordText);
		
		lblDash = new JLabel(".............................................."
				+ "........................................................."
				+ "........................................................."
				+ ".........................................................");
		lblDash.setBounds(50, 160, 500, 25);
		panel.add(lblDash);
		
		lbClass = new JLabel("ID");
		lbClass.setBounds(150, 220, 165, 25);
		panel.add(lbClass);
		
		lbFirstHost = new JLabel("FirstHost");
		lbFirstHost.setBounds(150, 270, 165, 25);
		panel.add(lbFirstHost);
		
		lbLastHost = new JLabel("LastHost");
		lbLastHost.setBounds(150, 320, 165, 25);
		panel.add(lbLastHost);
		
		lblIP = new JLabel("IP:");
		lblIP.setBounds(150, 370, 165, 25);
		panel.add(lblIP);
		
		lblSM = new JLabel("Subnet Mask:");
		lblSM.setBounds(150, 420, 165, 25);
		panel.add(lblSM);
		
		lblDG = new JLabel("Default Gateway:");
		lblDG.setBounds(150, 470, 165, 25);
		panel.add(lblDG);
		
		cbClass = new JComboBox<String>();
		cbClass.setBounds(280, 220, 200, 25);
		panel.add(cbClass);
		
		txtFH = new JTextField();
		txtFH.setBounds(280, 270, 200, 25);
		panel.add(txtFH);
		
		txtLH = new JTextField();
		txtLH.setBounds(280, 320, 200, 25);
		panel.add(txtLH);
		
		txtIP = new JTextField();
		txtIP.setBounds(280, 370, 200, 25);
		panel.add(txtIP);
		
		txtSM = new JTextField();
		txtSM.setBounds(280, 420, 200, 25);
		panel.add(txtSM);
		
		txtDG = new JTextField();
		txtDG.setBounds(280, 470, 200, 25);
		panel.add(txtDG);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(100, 550, 100, 25);
		//button.addActionListener(new);
		panel.add(btnLogin);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(250, 550, 100, 25);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtIP.setText("");
				txtSM.setText("");
				txtDG.setText("");
				userText.setText("");
				passwordText.setText("");
				//userText.setFocusCycleRoot(true);
			}
		});
		panel.add(btnReset);
		
		btnQuayLai = new JButton("Quay Lai");
		btnQuayLai.setBounds(400, 550, 100, 25);
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Index index = new GUI_Index();
			}
		});
		panel.add(btnQuayLai);
		
//		//acion
//		success = new JLabel("");
//		success.setBounds(10, 110, 300, 25);
//		panel.add(success);
		
		frame.setVisible(true);
	}
}
