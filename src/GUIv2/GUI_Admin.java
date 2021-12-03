package GUIv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;

import javax.swing.Action;
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
		JLabel userLabel, passwordLabel, lblTitle;
		JTextField userText;
		JPasswordField passwordText;
		JButton btnLogin, btnReset, btnBack;
		
		JFrame frame = new JFrame("Login");
		JPanel panel = new JPanel();
		frame.setSize(450, 300);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitle = new JLabel("LOGIN");
		lblTitle.setBounds(170, 10, 80, 25);
		panel.add(lblTitle);
		
		userLabel = new JLabel("Username");
		userLabel.setBounds(50, 50, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(180, 50, 200, 25);
		userText.setText("admin1");
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 100, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(180, 100, 200, 25);
		passwordText.setText("admin1");
		panel.add(passwordText);
		
		btnBack = new JButton("Quay lai");
		btnBack.setBounds(50, 170, 100, 30);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Index gui_index = new GUI_Index();
			}
		});
		
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 160, 100, 50);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkLogin(userText.getText(), String.valueOf(passwordText.getPassword()))) {
					frame.setVisible(false);
					GUI_Admin_Select guiAdminSelect = new GUI_Admin_Select(userText.getText());
				}
			}
		});
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(290, 170, 100, 30);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passwordText.setText("");
			}
		});
		
		frame.setVisible(true);
	}
	
	public Boolean checkLogin(String username, String password) {		
		int dem = 0;
		ResultSet rs;
		getDB db = new getDB();
		try {
			rs = db.executeQuery("select * from account where username = \""+ username + "\" and password = \"" + password + "\";");
			while(rs.next()) {
				dem++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dem != 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
