package GUIv2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI_Index implements ActionListener {
	public static void main(String[] args) {
		new GUI_Index();
	}
	
	public GUI_Index() {
		JLabel label;
		JButton btnAdmin, btnKhach;
		
		JFrame frame = new JFrame("Index");
		JPanel panel = new JPanel();
		frame.setSize(400, 300);	
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // set form center loaction
		frame.add(panel);
		
		panel.setLayout(null);
		
		label = new JLabel("Bạn là:");
		label.setFont(new Font("Monospaced", Font.BOLD, 30));
		label.setBounds(140, 50, 130, 45);
		panel.add(label);
		
		btnAdmin = new JButton("Người quản trị");
		btnAdmin.setFont(new Font("Monospaced", Font.BOLD, 12));
		btnAdmin.setBounds(40, 120, 150, 50);
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Admin gui_admin = new GUI_Admin();
			}
		});
		panel.add(btnAdmin);
		
		btnKhach = new JButton("Người sử dụng");
		btnKhach.setFont(new Font("Monospaced", Font.BOLD, 12));
		btnKhach.setBounds(200, 120, 150, 50);
		btnKhach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Khach gui_khach = new GUI_Khach();
			}
		});
		panel.add(btnKhach);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
