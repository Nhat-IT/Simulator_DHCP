package GUIv2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class createNew extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JLabel lblNewLabel_4;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JLabel lblNewLabel_5;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	
	ResultSet rs;
	getDB db = new getDB();
	String name = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createNew frame = new createNew(args[0]);
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
	public createNew(String user) {
		this.name = user;
		setResizable(false);
		setTitle("Tạo Mới");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Thêm Lần Đầu");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 27));
		lblNewLabel.setBounds(107, 11, 206, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Địa chỉ đầu");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 62, 95, 32);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(137, 69, 54, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(201, 69, 54, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(265, 69, 54, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(329, 69, 54, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(137, 100, 54, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(201, 100, 54, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(265, 100, 54, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(329, 100, 54, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Địa chỉ cuối");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 100, 104, 19);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("IP Máy Chủ");
		lblNewLabel_3.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 130, 95, 20);
		contentPane.add(lblNewLabel_3);
		
		textField_8 = new JTextField();
		textField_8.setBounds(137, 131, 54, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(201, 131, 54, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(265, 131, 54, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(329, 131, 54, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Default Gateway");
		lblNewLabel_4.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 160, 125, 20);
		contentPane.add(lblNewLabel_4);
		
		textField_12 = new JTextField();
		textField_12.setBounds(137, 161, 54, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(201, 162, 54, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(265, 161, 54, 20);
		contentPane.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(329, 162, 54, 20);
		contentPane.add(textField_15);
		textField_15.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Subnet Mask");
		lblNewLabel_5.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(10, 191, 125, 20);
		contentPane.add(lblNewLabel_5);
		
		textField_16 = new JTextField();
		textField_16.setBounds(137, 191, 54, 20);
		contentPane.add(textField_16);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(201, 192, 54, 20);
		contentPane.add(textField_17);
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setBounds(265, 192, 54, 20);
		contentPane.add(textField_18);
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setBounds(329, 192, 54, 20);
		contentPane.add(textField_19);
		textField_19.setColumns(10);
		
		JButton btnNewButton = new JButton("Trở Lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GUI_Admin gui_admin = new GUI_Admin();
			}
		});
		btnNewButton.setBounds(79, 249, 112, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tạo Mới");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Boolean check = true;
					String FH = Integer.parseInt(textField.getText()) + "." + Integer.parseInt(textField_1.getText()) + "." + Integer.parseInt(textField_2.getText()) + "." + Integer.parseInt(textField_3.getText()) ;
					String LH = Integer.parseInt(textField_4.getText()) + "." + Integer.parseInt(textField_5.getText()) + "." + Integer.parseInt(textField_6.getText()) + "." + Integer.parseInt(textField_7.getText());
					String IP = Integer.parseInt(textField_8.getText()) + "." + Integer.parseInt(textField_9.getText()) + "." + Integer.parseInt(textField_10.getText()) + "." + Integer.parseInt(textField_11.getText());
					String DG = Integer.parseInt(textField_12.getText()) + "." + Integer.parseInt(textField_13.getText()) + "." + Integer.parseInt(textField_14.getText()) + "." + Integer.parseInt(textField_15.getText());
					String SM = Integer.parseInt(textField_16.getText()) + "." + Integer.parseInt(textField_17.getText()) + "." + Integer.parseInt(textField_18.getText()) + "." + Integer.parseInt(textField_19.getText());
					check = checkInput(FH, LH, IP, DG, SM, SM, 1);
					if(check == false) {
						JOptionPane.showMessageDialog(contentPane, "Lỗi nhập liệu, vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Tạo mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//						System.out.println("insert into iptable(firstIP, lastIP, subnetMask, defaultGateway, username) values(" + "\"" + FH + "\"," + "\"" + LH + "\"," + "\"" + SM + "\"," + "\"" + DG + "\"," + "\"" + name + "\"" + ");");
						db.updateQuery("insert into iptable(firstIP, lastIP, subnetMask, defaultGateway, username) values(" + "\"" + FH + "\"," + "\"" + LH + "\"," + "\"" + SM + "\"," + "\"" + DG + "\"," + "\"" + name + "\"" + ");");
						String id = getID(FH, LH, IP, DG, SM, user);
						db.updateQuery("insert into ipinfor(iD, iP, port, timeRemain, ipAdmin, macAddress) values (" + "\"" + id + "\"" + ",\"" + IP + "\"" + ",\"5000\", \"-1\", 1, \"AA-AA-AA-AA-AA\"" + ")");
						setVisible(false);
						GUI_Admin gui_admin = new GUI_Admin();
					}
				}catch (Exception ee) {
					ee.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Lỗi nhập liệu, vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(229, 249, 112, 47);
		contentPane.add(btnNewButton_1);
	}
	
	public Boolean checkInput(String FH, String LH, String IP, String DG, String SM, String name, int choose) {
//		System.out.println(FH + " " + LH + " " + IP + " " + DG + " " + SM + " " + name + " " + choose);
		String[] arrCheck = new String[]{FH, LH, IP, DG, SM};
		List<String> listCheck = Arrays.asList(arrCheck);
		
		try {
			for (String item : listCheck) {
				String[] octect = item.split("\\.");
				if(octect.length != 4) return false;
				for (String i : octect) {
					Integer a = Integer.parseInt(i);
					if(a < 0 || a > 255) return false;
				}
			}
			String[] SMs = SM.split("\\.");
			for (String item : SMs) {
				if(Integer.parseInt(item) != 0 && Integer.parseInt(item) != 255) {
					return false;
				}
			}
			String[] FHs = FH.split("\\.");
			String[] LHs = LH.split("\\.");
			for(int i = 0; i< FHs.length ; i++) {
				if(Integer.parseInt(FHs[i]) > Integer.parseInt(LHs[i])) {
					return false;
				}
			}
			if(choose == 2) {
				rs = db.executeQuery("select * from iptable where firstIP = "+ "\"" + FH + "\"" +" and lastIP = " + "\"" + LH + "\"" + " and defaultGateway = " + "\"" + DG + "\"" + " and subnetMask = " + "\"" + SM + "\"" + " and username = " + "\"" +  name + "\"");
				int dem = 0;
				while(rs.next()) {
					dem++;
				}
				if(dem != 0) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	public String getID(String FH, String LH, String IP, String DG, String SM, String name) throws	Exception {
		String id = "";
		rs = db.executeQuery("select * from iptable where firstIP = "+ "\"" + FH + "\"" +" and lastIP = " + "\"" + LH + "\"" + " and defaultGateway = " + "\"" + DG + "\"" + " and subnetMask = " + "\"" + SM + "\"" + " and username = " + "\"" +  name + "\"");
//		System.out.println("select * from iptable where firstIP = "+ "\"" + FH + "\"" +" and lastIP = " + "\"" + LH + "\"" + " and defaultGateway = " + "\"" + DG + "\"" + " and subnetMask = " + "\"" + SM + "\"" + " and username = " + "\"" +  name + "\"");
//		System.out.println(rs == null);
		while(rs.next()) {
			id = rs.getString(1);
		}
		return id;
	}
}
