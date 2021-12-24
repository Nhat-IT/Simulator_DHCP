package GUIv2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		
		JFrame frame = new JFrame("Đăng Nhập Quản Trị");
		frame.setResizable(false);
		JPanel panel = new JPanel();
		frame.setSize(450, 300);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitle = new JLabel("Đăng Nhập");
		lblTitle.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblTitle.setBounds(130, 10, 200, 50);
		panel.add(lblTitle);
		
		userLabel = new JLabel("Tên Người Dùng");
		userLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
		userLabel.setBounds(30, 70, 130, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(180, 70, 200, 25);
		userText.setFont(new Font("Monospaced", Font.BOLD, 13));
		userText.setText("admin2");
		panel.add(userText);
		
		passwordLabel = new JLabel("Mật Khẩu");
		passwordLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
		passwordLabel.setBounds(30, 120, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
		passwordText.setBounds(180, 120, 200, 25);
		passwordText.setText("admin2");
		panel.add(passwordText);
		
		btnBack = new JButton("<= Quay Lại");
		btnBack.setBounds(30, 170, 115, 50);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Index gui_index = new GUI_Index();
			}
		});
		
		
		btnLogin = new JButton("Đăng Nhập =>");
		btnLogin.setBounds(265, 170, 115, 50);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkLogin(userText.getText(), String.valueOf(passwordText.getPassword()))) {
					if(!checkDataInAccount(userText.getText(), String.valueOf(passwordText.getPassword()))) {
						JOptionPane.showMessageDialog(panel, "Tài khoản không chứa dải mạng nào, nhấn \"OK\" để thêm mới", "Thông Báo", JOptionPane.WARNING_MESSAGE);
						frame.setVisible(false);
						String[] args = {userText.getText()};
						createNew.main(args);
					}
					else {
						frame.setVisible(false);
						GUI_Admin_Select guiAdminSelect = new GUI_Admin_Select(userText.getText());
					}
				}
				else {
					JOptionPane.showMessageDialog(panel, "Tên tài khoản hoặc mật khẩu không đúng. Vui lòng nhập lại !", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnReset = new JButton("Làm Mới");
		btnReset.setBounds(155, 170, 100, 50);
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
			rs = db.executeQuery("select * from account where username = \""+ username + "\" and password = \"" + password + "\" and fullControl = 0;");
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
	
	public Boolean checkDataInAccount(String username, String pass) {
		int dem = 0;
		ResultSet rs;
		getDB db = new getDB();
		try {
			rs = db.executeQuery("select * from iptable where username = \""+ username + "\" ");
			while(rs.next()) {
				dem++;
			}
			if(dem != 0) return true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
