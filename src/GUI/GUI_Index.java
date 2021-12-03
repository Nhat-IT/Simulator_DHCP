package GUI;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // set form center loaction
		frame.add(panel);
		
		panel.setLayout(null);
		
		label = new JLabel("Ban La ???");
		label.setBounds(170, 50, 80, 25);
		panel.add(label);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(80, 100, 100, 30);
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Admin gui_admin = new GUI_Admin();
			}
		});
		panel.add(btnAdmin);
		
		btnKhach = new JButton("Khach");
		btnKhach.setBounds(200, 100, 100, 30);
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
