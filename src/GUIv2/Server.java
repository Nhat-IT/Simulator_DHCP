package GUIv2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;



public class Server extends JFrame implements Runnable {
	static Map<clientChat, String> client;
	private String name = "Server";
	private String idServer;
	private int port;
	private InetAddress clientIP;
	private int clientPort;
	private String macAddress;
	static String doanChat;
	ResultSet rs;
	getDB db = new getDB();
	static workWithData wk = new workWithData();
	static DatagramSocket server;
	List<String> chiSos;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	static JTextArea textArea;
	List<String> dataTable;
	DefaultTableModel dtm;
	
	String userName;
	
	public static void main(String[] args) throws Exception {
		client = new HashMap<clientChat, String>();
		new Thread(new Server(5000,args[0], args[1])).start();
		new ClientAlive(args[0],Server.server).start();	
	}
	
	public Server(int port, String id, String username) throws Exception {
		this.port = port;
		this.idServer = id;
		this.doanChat = "";
		this.server = new DatagramSocket(5000);
		this.userName = username;
		
		System.out.println("Server is listening..."); 
		
		rs = db.executeQuery("select iD from iptable where iD IN " + getID());
		
		chiSos = new ArrayList<String>();
		chiSos.add("Tất Cả");
		while(rs.next()) {
			String chiSo = rs.getString(1);
			chiSos.add(chiSo);
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("QUẢN LÍ NGƯỜI DÙNG");
		setBounds(100, 100, 1110, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ NGƯỜI DÙNG");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblNewLabel.setBounds(430, 10, 350, 50);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 60, 626, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CHỌN ID: ");
		lblNewLabel_1.setBounds(10, 17, 85, 19);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Monospaced", Font.BOLD, 15));
		textField.setBounds(318, 14, 159, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm Kiếm");
		btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 15));
		btnNewButton.setBounds(483, 14, 114, 24);
		panel.add(btnNewButton);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 110, 626, 397);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		
		dataTable = new ArrayList<String>();
		String[] head = {"IP", "Port", "TimeRemain", "macAddress"};
		dtm = new DefaultTableModel(null,head);
		
		table = new JTable(dtm);
		table.setBounds(10, 20, 579, 367);
		panel_1.add(table);
		
		JScrollPane scTable = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scTable, BorderLayout.CENTER);
		getContentPane().add(panel_1);
		
		JComboBox<String> comboBox = new JComboBox(chiSos.toArray());
		comboBox.setFont(new Font("Monospaced", Font.BOLD, 15));
		comboBox.setBounds(95, 14, 135, 24);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String valueIndex = comboBox.getSelectedItem().toString();
				try {
					for(int i = dtm.getRowCount() - 1; i>=0 ; i--) {
						dtm.removeRow(i);
					}
					clientChat cli;
					if(valueIndex.equals("Tất Cả")) {
						rs = db.executeQuery("select iP, port, timeRemain, macAddress from ipinfor where iD IN " + getID() +" order by ipAdmin DESC");
//						System.out.println("select iP, port, timeRemain, macAddress from ipinfor where iD IN " + getID() +" order by ipAdmin DESC");
					}
					else {
						rs = db.executeQuery("select iP, port, timeRemain, macAddress from ipinfor where iD = " + valueIndex + " order by ipAdmin DESC");
					}

					while(rs.next()) {
						cli = new clientChat(InetAddress.getByName(rs.getString(1)), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),rs.getString(4));
						dtm.addRow(new Object[] {cli.getClientIP().toString(),String.valueOf(cli.getClientPort()),String.valueOf(cli.getTimeRemain()),cli.getMacAddress()});
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
		});
		panel.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(641, 60, 445, 397);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 17));
		textArea.setBackground(SystemColor.info);
		textArea.setColumns(41);
		textArea.setRows(14);
		textArea.setEditable(false);
		panel_2.add(textArea);
		
		JScrollPane scText = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel_2.add(scText);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(646, 447, 440, 60);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 10, 334, 40);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Gửi");
		btnNewButton_1.setBounds(344, 10, 85, 40);
		panel_3.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				doanChat +=  name + "> " + textField_1.getText() + "\n";
				for(clientChat cc : client.keySet()) {
					wk.sendData("_qwerasadf" + name + "> " + textField_1.getText() + "\n", "hkdsfghjlksg", server, cc.getClientIP(), cc.getClientPort());
				}
				textArea.append(name + "> " + textField_1.getText() + "\n");
				textField_1.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				try {
					for(int i = dtm.getRowCount() - 1; i>=0 ; i--) {
						dtm.removeRow(i);
					}
					clientChat cli;
					rs = db.executeQuery("select iP, port, timeRemain, macAddress from ipinfor where iD IN " + getID() +" order by ipAdmin DESC");
					while(rs.next()) {
						cli = new clientChat(InetAddress.getByName(rs.getString(1)), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),rs.getString(4));
						dtm.addRow(new Object[] {cli.getClientIP().toString(),String.valueOf(cli.getClientPort()),String.valueOf(cli.getTimeRemain()),cli.getMacAddress()});
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		setVisible(true);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String data = receiveDataServer(server); // set clientIP va clientPort trong ham receiveDataServer
				String[] arrData = data.split("_");
				String yeucau = arrData[0];
				int id = Integer.parseInt(arrData[1]);
//				teA.append(data);
				if(data.contains("fsdgjkviy")) {
					String mess = data.replace("_-1", "").replace("fsdgjkviy", "");
					doanChat += mess + "\n";
					textArea.append(mess + "\n");
					for(clientChat cc : client.keySet()) {
						if(!(cc.getClientIP().equals(clientIP) && cc.getClientPort() == clientPort))
							wk.sendData(mess + "\n","", server, cc.getClientIP(), cc.getClientPort());
					}
				}
				else if(yeucau.equals("alive")) {
					for(clientChat c: client.keySet()) {
						if(c.getClientIP().equals(clientIP) && c.getClientPort() == clientPort) {
							c.setTimeRemain(2);
						}
					}
					db.updateQuery("UPDATE ipinfor SET timeRemain = 2 where iD = " + id + " and iP = " + "\"" +clientIP.toString().substring(1) + "\"" + ";");
				}
				else if(yeucau.contains("Request")) {
					String nameClient = yeucau.split("\\*")[1].split("\\#")[0];
					String mac = yeucau.split("\\*")[1].split("\\#")[1];
					macAddress = mac;
					System.out.println(yeucau);
					clientChat cc = new clientChat(clientIP, clientPort, 2, macAddress);
					if(!client.containsKey(cc)) {
						System.out.println("hello1");
						client.put(cc, nameClient);
						System.out.println("insert into ipinfor (iD,iP,port,timeRemain,ipAdmin,macAddress) values (" + id + ",\"" + clientIP.toString().substring(1) + "\"," + clientPort + "," + "2," + "0" + ",\"" + macAddress + "\"" +");");
						db.updateQuery("insert into ipinfor (iD,iP,port,timeRemain,ipAdmin,macAddress) values (" + id + ",\"" + clientIP.toString().substring(1) + "\"," + clientPort + "," + "2," + "0" + ",\"" + macAddress + "\"" +");");
						doanChat += nameClient + " đã tham gia vào mạng" +"\n";
						textArea.append(nameClient + " đã tham gia vào mạng" +"\n");
						for(clientChat CC : client.keySet()) {
							if(!(CC.getClientIP().equals(clientIP) && CC.getClientPort() == clientPort)) {
								wk.sendData("jhiuyfjhg " + nameClient + " đã tham gia vào mạng" +"\n","&faflqrwpafaf&", server, CC.getClientIP(), CC.getClientPort());
							}
							else {
								wk.sendData("adgdsfggf " + doanChat,"&faflqrwpafaf&", server, clientIP, clientPort);
							}
							
						}
					}
					else {
						return;
					}					
				}
				else if(yeucau.equals("joined")) {
					wk.sendData("Acknowledge", "-1", server, clientIP, clientPort);
					System.out.println("Da ket noi voi" + clientIP + "_" + clientPort);
				}
				
				else if(yeucau.contains("DiscoveryDynamic")){
					System.out.println("Da ket noi " + clientIP +" "+ clientPort);
					String noithem = "";
					do {
						noithem = "";
						int dem = 4;
						int numberRand = (int)(Math.random() * 255);
						
						rs = db.executeQuery("select firstIP, lastIP, subnetMask from iptable where iD = "+ id);
						String[] firstIP = null;
						String[] lastIP = null;
						String[] sm = null;
						while(rs.next()) {
							firstIP = rs.getString(1).split("\\.");
							lastIP = rs.getString(2).split("\\.");
							sm = rs.getString(3).split("\\.");
						}
						for(String i:sm) {
							if(i.equals("255")) {
								dem--;
							}
						}
						System.out.println(dem);
						int conlai = 4 - dem;
						for(int i = 0;i<conlai;i++) {
							noithem += "." + firstIP[i];
						}
						for(int i = 0;i<dem;i++) {
							numberRand = (int)(Math.random() * 255);
							noithem += "." + String.valueOf(numberRand);
						}
					}while(checkExistClient(id, noithem));
					wk.sendData("Offer", noithem.substring(1), server, clientIP, clientPort);
				}
				
				else if(yeucau.contains("DiscoveryStatic")) {
					String[] yeucaus = yeucau.split("&");
					if(checkExistClient(id, yeucaus[1])) {
						System.out.println("Da ket noi " + clientIP +" "+ clientPort);
						String noithem = "";
						do {
							noithem = "";
							int dem = 4;
							int numberRand = (int)(Math.random() * 255);
							
							rs = db.executeQuery("select firstIP, lastIP, subnetMask from iptable where iD = "+ id);
							String[] firstIP = null;
							String[] lastIP = null;
							String[] sm = null;
							while(rs.next()) {
								firstIP = rs.getString(1).split("\\.");
								lastIP = rs.getString(2).split("\\.");
								sm = rs.getString(3).split("\\.");
							}
							for(String i:sm) {
								if(i.equals("255")) {
									dem--;
								}
							}
							System.out.println(dem);
							int conlai = 4 - dem;
							for(int i = 0;i<conlai;i++) {
								noithem += "." + firstIP[i];
							}
							for(int i = 0;i<dem;i++) {
								numberRand = (int)(Math.random() * 255);
								noithem += "." + String.valueOf(numberRand);
							}
						}while(checkExistClient(id, noithem));
						wk.sendData("Offer", noithem.substring(1), server, clientIP, clientPort);
					}
					else {
						wk.sendData("Accept", yeucaus[1], server, clientIP, clientPort);
					}
				}	
				else {
					return;
				}
			} //end while
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getID() {
		String s = "(";
		String a = "";
		try {
			rs = db.executeQuery("select iD from iptable where username = " + "\"" + userName + "\"");
			while(rs.next()) {
				a += "," + "\"" +rs.getString(1) + "\"";
			}
			a = a.substring(1);
			s += a + ")";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}
	
	public String receiveDataServer(DatagramSocket server) throws Exception {
		byte[] temp = new byte[1024];
		DatagramPacket receive_Packet = new DatagramPacket(temp,temp.length);
		server.receive(receive_Packet);
		clientIP = receive_Packet.getAddress();		
		clientPort = receive_Packet.getPort();
		
		return new String(receive_Packet.getData()).trim();
	}	
	
	private boolean checkExistClient(int id, String ip) {
		int size = 0;
		try {
			rs = db.executeQuery("select * from ipinfor where iD = " + id + " and iP = " + "\"" + ip + "\"" + ";");
			while(rs.next()) {
				size ++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (size != 0) {
			return true;
		}
		return false;
	}
}

class clientChat{
	private InetAddress clientIP;
	private int clientPort;
	private int timeRemain;
	private String macAddress;
	public clientChat(InetAddress addr, int port, int time, String mac) {
		this.clientIP = addr;
		this.clientPort = port;
		this.timeRemain = time;
		this.macAddress = mac;
	}

	public InetAddress getClientIP() {
		return clientIP;
	}

	public void setClientIP(InetAddress clientIP) {
		this.clientIP = clientIP;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	public int getTimeRemain() {
		return timeRemain;
	}
	public void setTimeRemain(int timeRemain) {
		this.timeRemain = timeRemain;
	}
	public void setMacAddress(String mac) {
		this.macAddress = mac;
	}
	public String getMacAddress() {
		return this.macAddress;
	}
}

class ClientAlive extends Thread{
	private String iD;
	private DatagramSocket ds;
	getDB db = new getDB();
	public ClientAlive(String iD, DatagramSocket ds) {
		this.iD = iD;
		this.ds = ds;
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				db.updateQuery("update ipinfor set timeRemain = timeRemain - 2 WHERE ipAdmin = 0 and iD = " + iD);
				db.updateQuery("delete from ipinfor where timeRemain < 0 and ipAdmin = 0 and iD = "+ iD);
				for(clientChat cc: Server.client.keySet()) {
					if(cc.getTimeRemain() < 0) {
						String name = Server.client.get(cc);
						Server.textArea.append(Server.client.get(cc) + " đã rời khỏi" + "\n");
						Server.doanChat += Server.client.get(cc) + " đã rời khỏi" + "\n";
						Server.client.remove(cc);
						for(clientChat c: Server.client.keySet()) {
							Server.wk.sendData("jhiuyfjhg " + name + " đã tham gia vào mạng" +"\n","&faflqrwpafaf&", ds, c.getClientIP(), c.getClientPort());
						}
					}
					else {
						cc.setTimeRemain(cc.getTimeRemain() - 2);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}






