package GUIv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.concurrent.TimeUnit;

public class GUI_Khach_ChucNang1 implements Runnable {
	private InetAddress host;
	JTextField txtIP;
	private int port;
	Scanner sc;
	Process p;
	workWithData wk = new workWithData();
	DatagramSocket client;
	

	public static void main(String[] args) throws Exception {
		new Thread(new GUI_Khach_ChucNang1()).start();
	}

	public GUI_Khach_ChucNang1() throws Exception {
		try {
			this.host = InetAddress.getByName("10.0.0.205");
			this.port = 5000;
			this.client = new DatagramSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getDB db = new getDB();
		ResultSet rs;
		rs = db.executeQuery("select iD from iptable");
		List<String> listID = new ArrayList<String>();
		while (rs.next()) {
			String iD = rs.getString(1);
			listID.add(iD);
		}

		JLabel lblTitle, lbFirstHost, lbLastHost, lbClass;
		JLabel lblIP, lblSM, lblDG;
		final JTextField txtFH;
		final JTextField txtSM;
		final JTextField txtDG;
		final JTextField txtLH;
		JButton btnReset, btnQuayLai, btnJoin, btnRefresh;
		final JComboBox<String> cbClass;

		JFrame frame = new JFrame("Cap Tu Dong");
		JPanel panel = new JPanel();
		frame.setSize(650, 600);

		frame.setLocationRelativeTo(null); // set form center loaction
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);

		lblTitle = new JLabel("Chon ID Mang Ban Muon Duoc Cap");
		lblTitle.setBounds(220, 30, 200, 25);
		panel.add(lblTitle);

		lbClass = new JLabel("ID");
		lbClass.setBounds(150, 100, 165, 25);
		panel.add(lbClass);

		lbFirstHost = new JLabel("FirstHost");
		lbFirstHost.setBounds(150, 150, 165, 25);
		panel.add(lbFirstHost);

		lbLastHost = new JLabel("LastHost");
		lbLastHost.setBounds(150, 200, 165, 25);
		panel.add(lbLastHost);

		lblIP = new JLabel("IP:");
		lblIP.setBounds(150, 250, 165, 25);
		panel.add(lblIP);

		lblSM = new JLabel("Subnet Mask:");
		lblSM.setBounds(150, 300, 165, 25);
		panel.add(lblSM);

		lblDG = new JLabel("Default Gateway:");
		lblDG.setBounds(150, 350, 165, 25);
		panel.add(lblDG);

		txtFH = new JTextField();
		txtFH.setBounds(280, 150, 200, 25);
		txtFH.setEditable(false);
		panel.add(txtFH);

		txtLH = new JTextField();
		txtLH.setBounds(280, 200, 200, 25);
		txtLH.setEditable(false);
		panel.add(txtLH);

		txtIP = new JTextField();
		txtIP.setBounds(280, 250, 200, 25);
		txtIP.setEditable(false);
		panel.add(txtIP);

		txtSM = new JTextField();
		txtSM.setBounds(280, 300, 200, 25);
		txtSM.setEditable(false);
		panel.add(txtSM);

		txtDG = new JTextField();
		txtDG.setBounds(280, 350, 200, 25);
		txtDG.setEditable(false);
		panel.add(txtDG);

		cbClass = new JComboBox(listID.toArray());
		cbClass.setBounds(280, 100, 200, 25);
		panel.add(cbClass);
		cbClass.addActionListener(new ActionListener() {
			String firstIP = null;
			String lastIP = null;
			String subnetMask = null;
			String defaultGateway = null;

			@SuppressWarnings("null")
			@Override
			public void actionPerformed(ActionEvent e) {
				String valueIndex = cbClass.getSelectedItem().toString();
				try {
					ResultSet result = db.executeQuery(
							"select firstIP, lastIP, subnetMask, defaultGateway from iptable where iD = " + valueIndex);
					while (result.next()) {
						firstIP = result.getString(1);
						lastIP = result.getString(2);
						subnetMask = result.getString(3);
						defaultGateway = result.getString(4);
					}
					txtFH.setText(firstIP);
					txtLH.setText(lastIP);
					txtDG.setText(defaultGateway);
					txtSM.setText(subnetMask);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnJoin = new JButton("Join!!!");
		btnJoin.setBounds(450, 420, 150, 35);
		btnJoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				txtIP.setText("");
//				txtSM.setText("");
//				txtDG.setText("");
				int valueIndex = Integer.parseInt(cbClass.getSelectedItem().toString());
				try {
					p = Runtime.getRuntime().exec("cmd");
					new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
					new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
					PrintWriter stdin = new PrintWriter(p.getOutputStream());
					stdin.println("netsh int ip set address " + "Ethernet" + " static " + txtIP.getText() + " "
							+ txtSM.getText() + " " + txtDG.getText());
					stdin.close();
					p.waitFor();
					p.destroyForcibly();
					

					TimeUnit.SECONDS.sleep(4);
					String index = String.valueOf(valueIndex);
					String[] args = {index, wk.getMacDevice(txtIP.getText())};
					GUI_Khach2.main(args);
					frame.setVisible(false);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		panel.add(btnJoin);

		btnQuayLai = new JButton("Quay Lai");
		btnQuayLai.setBounds(50, 420, 150, 35);
		btnQuayLai.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GUI_Khach khach = new GUI_Khach();
			}
		});
		panel.add(btnQuayLai);

		btnRefresh = new JButton("Lam Moi");
		btnRefresh.setBounds(490, 250, 100, 25);
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(cbClass.getSelectedItem().toString());
				System.out.println(value + "value");
				try {
					client.send(wk.createPacket("DiscoveryDynamic", value, host, port));
					System.out.println("hello");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnRefresh);

		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				String firstIP = null;
				String lastIP = null;
				String subnetMask = null;
				String defaultGateway = null;
				try {
					ResultSet result = db.executeQuery(
							"select firstIP, lastIP, subnetMask, defaultGateway from iptable where iD = " + 1);
					while (result.next()) {
						firstIP = result.getString(1);
						lastIP = result.getString(2);
						subnetMask = result.getString(3);
						defaultGateway = result.getString(4);
					}
					txtFH.setText(firstIP);
					txtLH.setText(lastIP);
					txtDG.setText(defaultGateway);
					txtSM.setText(subnetMask);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

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
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}


	@Override
	public void run() {
		try {
			while (true) {
				String data = wk.receiveData(client);
				if(data.contains("Offer")) {
					String dataReceive = data.split("_")[1];
					System.out.println(dataReceive);
					txtIP.setText(dataReceive);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
}
