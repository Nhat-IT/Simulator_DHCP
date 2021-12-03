package DHCP;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener {

	private InetAddress host;
	private int port;
	private Scanner sc = new Scanner(System.in);
	Process p;
	
//	private String iP;
//	private String subnetMask;
//	private String defaultGateway;

	public Client(InetAddress host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client(InetAddress.getByName("localhost"), 5000);
		client.execute();
	}
	int choose = -1;

	@SuppressWarnings("deprecation")
	private void execute() throws Exception {
		this.setTitle("DHCP Client");
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(3);
		Font font1 = new Font("SansSerif", Font.BOLD, 20);		

		JLabel lb1 = new JLabel("Menu");
		lb1.setBounds(490, 0, 100, 50);
		lb1.setFont(font1);
		this.add(lb1);

		JLabel lb2 = new JLabel("Client");
		lb2.setBounds(155, 250, 100, 50);
		lb2.setFont(font1);
		this.add(lb2);
		
		JLabel lb3 = new JLabel("Nhap lua chon");
		lb3.setBounds(65, 300, 200, 50);
		lb3.setFont(font1);
		this.add(lb3);

		JLabel lb4 = new JLabel("IP cua ban");
		lb4.setBounds(50, 550, 150, 50);
		lb4.setFont(font1);
		this.add(lb4);
		
		JLabel lb5 = new JLabel("Manager");
		lb5.setBounds(650, 250, 150, 50);
		lb5.setFont(font1);
		this.add(lb5);
		
		JLabel lb6 = new JLabel("ID");
		lb6.setBounds(470, 340, 150, 50);
		lb6.setFont(font1);
		this.add(lb6);
		
		JLabel lb7 = new JLabel("Pass");
		lb7.setBounds(470, 400, 150, 50);
		lb7.setFont(font1);
		this.add(lb7);
		
		JLabel lb8 = new JLabel("Subnet Mask");
		lb8.setBounds(350, 550, 150, 50);
		lb8.setFont(font1);
		this.add(lb8);
		
		JLabel lb9 = new JLabel("Default Gateway");
		lb9.setBounds(650, 550, 200, 50);
		lb9.setFont(font1);
		this.add(lb9);

		JTextField teF1 = new JTextField();// nhap lua chon
		teF1.setBounds(65, 350, 250, 100);
		teF1.setFont(font1);
		this.add(teF1);

		JTextField teF2 = new JTextField(); // hien thi ip
		teF2.setBounds(50, 600, 250, 50);
		teF2.setFont(font1);
		this.add(teF2);
		
		JTextField teF3 = new JTextField(); // Dung cho manager nhap id
		teF3.setBounds(550, 340, 350, 50);
		teF3.setFont(font1);
		this.add(teF3);
		
		JTextField teF4 = new JTextField(); // Dung cho manager nhap pass
		teF4.setBounds(550, 400, 350, 50);
		teF4.setFont(font1);
		this.add(teF4);
		
		JTextField teF5 = new JTextField(); // hien thi Subnetmask
		teF5.setBounds(350, 600, 250, 50);
		teF5.setFont(font1);
		this.add(teF5);
		
		JTextField teF6 = new JTextField(); // hien thi Default gateway
		teF6.setBounds(650, 600, 250, 50);
		teF6.setFont(font1);
		this.add(teF6);

		JButton btn1 = new JButton("Xac nhan");// Xac nhan
		btn1.setBounds(65, 470, 100, 50);
		this.add(btn1);

		JButton btn2 = new JButton("Reset");// Reset
		btn2.setBounds(215, 470, 100, 50);
		this.add(btn2);
		
		JButton btn3 = new JButton("Xac nhan");// Xac nhan manager
		btn3.setBounds(570, 470, 100, 50);
		this.add(btn3);

		JButton btn4 = new JButton("Reset");// Reset manager
		btn4.setBounds(750, 470, 100, 50);
		this.add(btn4);

		DatagramSocket client = new DatagramSocket();
		client.send(createPacket("connect"));
		String menu = receiveData(client);

		JTextArea texA = new JTextArea();
		texA.setBounds(50, 50, 900, 200);
		texA.setFont(font1);
		this.add(texA);
		this.setLayout(null);
		this.setVisible(true);

//		Alive alive = new Alive(client, host, port);
//		alive.start();

		while (true) {
			//this.repaint();
			texA.setText(menu);
			// choose = input("Nhap lua chon");
			choose = -1;
			btn1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						choose = Integer.parseInt(teF1.getText());
					} catch (Exception e1) {
						// TODO: handle exception
					}

				}
			});
			Thread.sleep(1000);
			if (choose <= 3 || choose >=1) {
				switch (choose) {
				case 1:
					client.send(createPacket(-1, 1));
					String dataReceive = receiveData(client);
					teF2.setText("10.0.0." + dataReceive);
					teF5.setText("255.0.0.0");
					teF6.setText("10.0.0.0");
			    	try {
			    		System.out.println("changeIP");
			    		p = Runtime.getRuntime().exec("cmd");
			            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			                PrintWriter stdin = new PrintWriter(p.getOutputStream());
			                stdin.println("netsh int ip set address "+"Ethernet"+" static "+"10.0.0." + dataReceive+" "+"255.0.0.0"+" "+"10.0.0.1");
			                    stdin.close();
			                p.waitFor();
			        	} catch (Exception e) {
			     		e.printStackTrace();
			    	}
					break;
				case 2: {
					boolean flag = true;
					do {
						texA.setText("Nhap ip ban muon: ");
						int number = input("Nhap: ");
						client.send(createPacket(number, 2));
						int result = Integer.parseInt(receiveData(client));
						if (result == -1) {
							texA.setText("Ip phai nam trong khoang 0->255 (0-255). Vui long nhap lai dia chi ip!");
						} else if (result == -2) {
							texA.setText(" IP nay da duoc su dung. Vui long chon IP khac!");
						} else if (result == -3) {
							teF2.setText("10.0.0." + number);
							teF5.setText("255.0.0.0");
							teF6.setText("10.0.0.0");
							flag = false;
					    	try {
					    		System.out.println("changeIP");
					    		p = Runtime.getRuntime().exec("cmd");
					            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
					                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
					                PrintWriter stdin = new PrintWriter(p.getOutputStream());
					                stdin.println("netsh int ip set address "+"Ethernet"+" static "+"10.0.0." + number+" "+"255.0.0.0"+" "+"10.0.0.1");
					                    stdin.close();
					                p.waitFor();
					        	} catch (Exception e) {
					     		e.printStackTrace();
					    	}	
						}
					} while (flag);
					break;
				}
				case 3:
					client.send(createPacket(-1, 3)); // key ==3 danh dau gui menu tuy chon 3
//					alive.stop();
					texA.setText("Ket thuc");
					break;
				}
				if (choose == 3)
					break;
			}
		}
	}
	
//    @Override
//    public void paint(Graphics g) 
//    {
//    	g.setColor(Color.black);
//        g.drawRect(47, 290, 290, 300);
//        g.drawRect(360, 290, 590, 300);
//    }

	private int input(String request) {
		int number = 0;
		boolean flag = true;
		do {
			try {
				System.out.println(request);
				number = Integer.parseInt(sc.nextLine());
				flag = false;
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (flag);
		return number;
	}

	private String receiveData(DatagramSocket server) throws IOException {
		byte[] temp = new byte[1024];
		DatagramPacket receive_Packet = new DatagramPacket(temp, temp.length);
		server.receive(receive_Packet);
		return new String(receive_Packet.getData()).trim();
	}

	private DatagramPacket createPacket(String value) {
		byte[] arrData = new byte[1024];
		return new DatagramPacket(arrData, arrData.length, host, port);
	}

	private DatagramPacket createPacket(int value, int index) {
		String str = String.valueOf(value) + "_" + index;
		byte[] arrData = str.getBytes();
		return new DatagramPacket(arrData, arrData.length, host, port);
	}

	public void actionPerformed(ActionEvent e) {
	}

}

//class Alive extends Thread {
//	private DatagramSocket client;
//	private InetAddress host;
//	private int port;
//
//	public Alive(DatagramSocket client, InetAddress host, int port) {
//		this.client = client;
//		this.host = host;
//		this.port = port;
//	}
//
//	@Override
//	public void run() {
//		while (true) {
//			try {
//				client.send(createPacket("alive"));
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	private DatagramPacket createPacket(String value) {
//		byte[] arrData = value.getBytes();
//		return new DatagramPacket(arrData, arrData.length, host, port);
//	}
//}