package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Khach_ChucNang2 {
	public static void main(String[] args) {
		new GUI_Khach_ChucNang2();
	}
	
	public GUI_Khach_ChucNang2() {
		JLabel userLabel, passwordLabel, lblTitle;
		JLabel lblIP, lblSM, lblDG;
		JTextField userText, txtIP, txtSM, txtDG;
		JPasswordField passwordText;
		JButton btnOK, btnReset, btnQuayLai;
		JLabel success;
		
		JFrame frame = new JFrame("2. Cap Bang Tay");
		JPanel panel = new JPanel();
		frame.setSize(550, 600);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitle = new JLabel("MOI BAN NHAP: ");
		lblTitle.setBounds(250, 50, 200, 25);
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
		lblIP.setBounds(90, 120, 165, 25);
		panel.add(lblIP);
		
		lblSM = new JLabel("Subnet Mask:");
		lblSM.setBounds(90, 190, 165, 25);
		panel.add(lblSM);
		
		lblDG = new JLabel("Default Gateway:");
		lblDG.setBounds(90, 260, 165, 25);
		panel.add(lblDG);
		
		txtIP = new JTextField();
		txtIP.setBounds(200, 120, 200, 25);
		panel.add(txtIP);
		
		txtSM = new JTextField();
		txtSM.setBounds(200, 190, 200, 25);
		panel.add(txtSM);
		
		txtDG = new JTextField();
		txtDG.setBounds(200, 260, 200, 25);
		panel.add(txtDG);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(50, 400, 100, 25);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_Khach2 gui_khach2 = new GUI_Khach2();				
			}
		});
		panel.add(btnOK);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(200, 400, 100, 25);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtIP.setText("");
				txtSM.setText("");
				txtDG.setText("");
			}
		});
		panel.add(btnReset);
		
		btnQuayLai = new JButton("Quay Lai");
		btnQuayLai.setBounds(350, 400, 100, 25);
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Khach khach = new GUI_Khach();
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
