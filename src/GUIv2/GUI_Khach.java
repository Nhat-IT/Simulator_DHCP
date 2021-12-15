package GUIv2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
		
		final JFrame frame = new JFrame("Trang Chu Khach");
		JPanel panel = new JPanel();
		frame.setSize(700, 400);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lblTitleKhach = new JLabel("HÃY CHỌN CHỨC NĂNG");
		lblTitleKhach.setFont(new Font("Monospaced", Font.BOLD, 25));
		lblTitleKhach.setBounds(210, 50, 300, 25);
		panel.add(lblTitleKhach);
		
		btn1 = new JButton("Cấp Tự Động");
		btn1.setFont(new Font("Monospaced", Font.BOLD, 12));
		btn1.setBounds(50, 120, 150, 50);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					GUI_Khach_ChucNang1.main(new String[1]);
				} catch (Exception e1) {

					e1.printStackTrace();
				}				
			}
		});
		panel.add(btn1);
		
		btn2 = new JButton("Cấp Bằng Tay");
		btn2.setFont(new Font("Monospaced", Font.BOLD, 12));
		btn2.setBounds(250, 120, 150, 50);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					GUI_Khach_ChucNang2.main(new String[1]);
				} catch (Exception e1) {

					e1.printStackTrace();
				}					
			}
		});
		panel.add(btn2);
		
		btn3 = new JButton("Trở Lại");
		btn3.setFont(new Font("Monospaced", Font.BOLD, 12));
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

