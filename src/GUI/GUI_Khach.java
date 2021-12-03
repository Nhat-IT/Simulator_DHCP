package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Khach {
	public static void main(String[] args) {
		new GUI_Khach();
	}
	
	public GUI_Khach() {
		JLabel lblTitleKhach;
		JTextField txt2;
		JLabel passwordLabel;
		JPasswordField passwordText;
		JButton btn1, btn2, btn3;
		JLabel success;
		
		JFrame frame = new JFrame("Trang Chu Khach");
		JPanel panel = new JPanel();
		frame.setSize(700, 400);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitleKhach = new JLabel("HAY CHON CHUC NANG");
		lblTitleKhach.setBounds(270, 50, 200, 25);
		panel.add(lblTitleKhach);
		
		btn1 = new JButton("1. Cap Tu Dong");
		btn1.setBounds(50, 120, 150, 50);
		//btn1.addActionListener(new GUI_Login());
		panel.add(btn1);
		
		btn2 = new JButton("2. Cap Bang Tay");
		btn2.setBounds(250, 120, 150, 50);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Khach_ChucNang2 gui_khach_chucnang2 = new GUI_Khach_ChucNang2();				
			}
		});
		panel.add(btn2);
		
		btn3 = new JButton("3. Tro Lai");
		btn3.setBounds(450, 120, 150, 50);
		panel.add(btn3);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				GUI_Index index = new GUI_Index();
			}
		});
		
		
		
//		txt2 = new JTextField("");
//		txt2.setBounds(180, 120, 200, 25);
//		panel.add(txt2);
		
		frame.setVisible(true);
	}
}
