package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Khach2 {
	public static void main(String[] args) {
		new GUI_Khach2();
	}
	
	public GUI_Khach2() {
		JLabel userLabel, passwordLabel, lblTitle;
		JLabel lblIP, lblSM, lblDG;
		JTextField userText, txtIP, txtSM, txtDG;
		JPasswordField passwordText;
		
		JButton btnQuayLai, btnThoat;
		JLabel success;
		
		JFrame frame = new JFrame("Thanh Cong");
		JPanel panel = new JPanel();
		frame.setSize(500, 500);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitle = new JLabel("DAY LA DAY DIA CHI BAN DUOC CAP");
		lblTitle.setBounds(120, 30, 250, 25);
		panel.add(lblTitle);
		
//		userLabel = new JLabel("User");
//		userLabel.setBounds(50, 50, 80, 25);
//		panel.add(userLabel);
//		
//		userText = new JTextField();
//		userText.setBounds(200, 50, 200, 25);
//		panel.add(userText);
//		
//		passwordLabel = new JLabel("Password");
//		passwordLabel.setBounds(50, 100, 80, 25);
//		panel.add(passwordLabel);
//		
//		passwordText = new JPasswordField();
//		passwordText.setBounds(200, 100, 200, 25);
//		panel.add(passwordText);
//		
//		
		
		lblIP = new JLabel("IP:");
		lblIP.setBounds(50, 120, 165, 25);
		panel.add(lblIP);
		
		lblSM = new JLabel("Subnet Mask:");
		lblSM.setBounds(50, 190, 165, 25);
		panel.add(lblSM);
		
		lblDG = new JLabel("Default Gateway:");
		lblDG.setBounds(50, 260, 165, 25);
		panel.add(lblDG);
		
		txtIP = new JTextField();
		txtIP.setBounds(200, 120, 200, 25);
		txtIP.setEditable(false);
		panel.add(txtIP);
		
		txtSM = new JTextField();
		txtSM.setBounds(200, 190, 200, 25);
		txtSM.setEditable(false);
		panel.add(txtSM);
		
		txtDG = new JTextField();
		txtDG.setBounds(200, 260, 200, 25);
		txtDG.setEditable(false);
		panel.add(txtDG);
		
		btnQuayLai = new JButton("Quay Lai");
		btnQuayLai.setBounds(100, 350, 100, 25);
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		panel.add(btnQuayLai);
		
		btnThoat = new JButton("Thoat");
		btnThoat.setBounds(250, 350, 100, 25);
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnThoat);
		
//		//acion
//		success = new JLabel("");
//		success.setBounds(10, 110, 300, 25);
//		panel.add(success);
		
		frame.setVisible(true);
	}
}
