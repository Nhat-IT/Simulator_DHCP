package GUIv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Admin_Select {
	private String userName;
	ResultSet rs;
	getDB db = new getDB();
	Process p;
	
	public GUI_Admin_Select(String name) {
		this.userName = name;
		
		JLabel lblIP, lblSM, lblDG, lbFirstHost, lbLastHost, lbClass;
		final JTextField txtIP;
		final JTextField txtSM;
		final JTextField txtDG;
		JTextField txtFH;
		final JTextField txtLH;
		JPasswordField passwordText;
		JComboBox<String> cbClass;
		JButton btnQuayLai, btnJoin;
		
		JFrame frame = new JFrame("Login Succes");
		JPanel panel = new JPanel();
		frame.setSize(430, 500);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		lbClass = new JLabel("ID");
		lbClass.setBounds(50, 50, 165, 25);
		panel.add(lbClass);
		
		lbFirstHost = new JLabel("FirstHost");
		lbFirstHost.setBounds(50, 100, 165, 25);
		panel.add(lbFirstHost);
		
		lbLastHost = new JLabel("LastHost");
		lbLastHost.setBounds(50, 150, 165, 25);
		panel.add(lbLastHost);
		
		lblIP = new JLabel("IP:");
		lblIP.setBounds(50, 200, 165, 25);
		panel.add(lblIP);
		
		lblSM = new JLabel("Subnet Mask:");
		lblSM.setBounds(50, 250, 165, 25);
		panel.add(lblSM);
		
		lblDG = new JLabel("Default Gateway:");
		lblDG.setBounds(50, 300, 165, 25);
		panel.add(lblDG);
		
		List<String> listID = new ArrayList<String>();
		try {
			rs = db.executeQuery("select iD from iptable where username = \"" + userName + "\";");
			while (rs.next()) {
				listID.add(rs.getString(1));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		txtFH = new JTextField();
		txtFH.setBounds(180, 100, 200, 25);
		txtFH.setEditable(false);
		panel.add(txtFH);
		
		txtLH = new JTextField();
		txtLH.setBounds(180, 150, 200, 25);
		txtLH.setEditable(false);
		panel.add(txtLH);
		
		txtIP = new JTextField();
		txtIP.setBounds(180, 200, 200, 25);
		txtIP.setEditable(false);
		panel.add(txtIP);
		
		txtSM = new JTextField();
		txtSM.setBounds(180, 250, 200, 25);
		txtSM.setEditable(false);
		panel.add(txtSM);
		
		txtDG = new JTextField();
		txtDG.setBounds(180, 300, 200, 25);
		txtDG.setEditable(false);
		panel.add(txtDG);
		
		cbClass = new JComboBox(listID.toArray());
		cbClass.setBounds(180, 50, 200, 25);
		panel.add(cbClass);
		cbClass.addActionListener(new ActionListener() {
			String firstIP = null;
			String lastIP = null;
			String subnetMask = null;
			String defaultGateway = null;
			String ipAdmin = null;
			@SuppressWarnings("null")
			@Override
			public void actionPerformed(ActionEvent e) {
				String valueIndex = cbClass.getSelectedItem().toString();
				try {
					ResultSet result = db.executeQuery("select firstIP, lastIP, subnetMask, defaultGateway from iptable where iD = "+ valueIndex);
					while(result.next()) {
						firstIP = result.getString(1);
						lastIP = result.getString(2);
						subnetMask = result.getString(3);
						defaultGateway = result.getString(4);
					}
					ResultSet rs = db.executeQuery("select iP from ipinfor where iD = " + valueIndex + " and ipAdmin = true;");
					while(rs.next()) {
						ipAdmin = rs.getString(1);
					}
					txtFH.setText(firstIP);
					txtLH.setText(lastIP);
					txtDG.setText(defaultGateway);
					txtSM.setText(subnetMask);
					txtIP.setText(ipAdmin);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
		
		btnQuayLai = new JButton("Thoat");
		btnQuayLai.setBounds(50, 350, 150, 45);
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		panel.add(btnQuayLai);
		
		btnJoin = new JButton("Join Vao Mang");
		btnJoin.setBounds(230, 350, 150, 45);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			String valueIndex = cbClass.getSelectedItem().toString();
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] args = {valueIndex};
					Server.main(args);
					frame.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				String firstIP = null;
				String lastIP = null;
				String subnetMask = null;
				String defaultGateway = null;
				String ipAdmin = null;
				try {
					ResultSet result = db.executeQuery("select firstIP, lastIP, subnetMask, defaultGateway from iptable where iD = "+ listID.get(0));
					while(result.next()) {
						firstIP = result.getString(1);
						lastIP = result.getString(2);
						subnetMask = result.getString(3);
						defaultGateway = result.getString(4);
					}
					ResultSet rs = db.executeQuery("select iP from ipinfor where iD = " + 1 + " and ipAdmin = true;");
					while(rs.next()) {
						ipAdmin = rs.getString(1);
					}
					txtIP.setText(ipAdmin);
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
}
