package GUIv2;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI_Khach2 extends JFrame implements Runnable {
	private InetAddress host;
	private String IP;
	private int port;
	private int index;
	private String name;
	private static String macAddress;
	Scanner sc;
	workWithData wk = new workWithData();
	DatagramSocket ds;
	
	private static JTextArea textArea;

	public static void main(String[] args) throws Exception {
		
		new Thread(new GUI_Khach2(Integer.parseInt(args[0]), args[1], args[2])).start();
	}

	public GUI_Khach2(int index, String mac, String ip) throws Exception {
		this.host = InetAddress.getByName("10.0.0.205");
		this.port = 5000;
		this.index = index;
		this.name = new RandomStringExmple().randomAlphaNumeric(5);
		this.macAddress = mac;
		this.IP = ip;
		ds = new DatagramSocket();
		ds.send(wk.createPacket("Request*"+name + "#" + macAddress, index, this.host, this.port));
		XuLyKhach xuly = new XuLyKhach(ds, this.host, this.port);
		xuly.start();

		JTextField textField, textField_1;
		JPanel contentPane,panel;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setTitle("Khách");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 466, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 0, 58, 54);
		panel.add(lblNewLabel);
		
		JLabel lbIP = new JLabel("IP:");
		lbIP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbIP.setBounds(275, 0, 30, 54);
		panel.add(lbIP);
		
		JTextField txtIP = new JTextField(IP);
		txtIP.setBounds(310, 0, 140, 54);
		txtIP.setEditable(false);
		txtIP.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(txtIP);
		
		textField = new JTextField();
		textField.setText(name);
		textField.setEditable(false);
		textField.setBackground(SystemColor.control);
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(45, 5, 177, 43);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 67, 466, 417);
		contentPane.add(panel_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(51, 204, 153));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 18));
		textArea.setColumns(40);
		textArea.setRows(16);
		panel_1.add(textArea);
		
		JScrollPane scMess = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scMess);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 494, 466, 59);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textField_1.setBounds(10, 10, 326, 39);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Gửi");
		btnNewButton.setBounds(346, 10, 110, 39);
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = textField_1.getText();
				System.out.println(message);
				textField_1.setText("");
				try {
					textArea.append(name + "> " + message + "\n");
					ds.send(wk.createPacket("fsdgjkviy" + name + "> " + message, -1, host, port));
				}catch (Exception ee) {
					ee.printStackTrace();
				}				
			}
		});
		
		setVisible(true);
	}

	@Override
	public void run() {
		try {
			while (true) {
				//&faflqrwpafaf&
				String data = wk.receiveData(ds);
				String upToNCharacters = data.substring(0, Math.min(data.length(), 10));
//				System.out.println(upToNCharacters);
				System.out.println(data.contains("qwerasadf"));
				if(data.contains("qwerasadf")) {	
					data = data.replace("_qwerasadf", "").replace("_hkdsfghjlksg", "");
					textArea.append(data);
				}
				else {
					data = data.replace("_&faflqrwpafaf&", "");
					data = data.substring(10);
					textArea.append(data);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

class RandomStringExmple {
	 
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static final String ALL = alpha + alphaUpperCase + digits + specials;
 
    private static Random generator = new Random();
    
    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}

class XuLyKhach extends Thread {
	private DatagramSocket client;
	private InetAddress host;
	private int port;

	public XuLyKhach(DatagramSocket client, InetAddress host, int port) {
		this.client = client;
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		while (true) {
			try {
				client.send(createPacket("alive", 1));
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public DatagramPacket createPacket(String request, int iD) {
		String str = request + "_" + String.valueOf(iD);
		byte[] arrData = str.getBytes();
		return new DatagramPacket(arrData, arrData.length, host, port);
	}
}
