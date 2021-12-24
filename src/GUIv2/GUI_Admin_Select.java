package GUIv2;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class GUI_Admin_Select {
	private String userName;
	ResultSet rs;
	getDB db = new getDB();
	Process p;
	String valueIndex; //Lưu giá trị ở cbClass	
	JLabel lblIP, lblSM, lblDG, lbFirstHost, lbLastHost, lbClass, lbTitle, lbSetting;
	JTextField txtIP;
	JTextField txtSM;
	final JTextField txtDG;
	JTextField txtFH;
	final JTextField txtLH;
	List<String> listID;
	int indexSetting;
	Boolean ktraVaoMang = true;
	
	public GUI_Admin_Select(String name) {
		this.userName = name;

		JComboBox<String> cbClass;
		JButton btnQuayLai, btnJoin, btnSubmit;
		JComboBox<String> cbSetting;
		List<String> setting;
		
		JFrame frame = new JFrame("Đăng Nhập Thành Công");
		JPanel panel = new JPanel();
		frame.setSize(430, 550);
		
		frame.setLocationRelativeTo(null); // set form center loaction
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		panel.setLayout(null);
		
		lbTitle = new JLabel("Lựa Chọn Mạng");
		lbTitle.setFont(new Font("Monospaced", Font.BOLD, 25));
		lbTitle.setBounds(110, 10, 250, 50);
		panel.add(lbTitle);
		
		lbClass = new JLabel("ID");
		lbClass.setBounds(50, 70, 165, 25);
		panel.add(lbClass);
		
		lbFirstHost = new JLabel("Địa Chỉ Mạng Đầu");
		lbFirstHost.setBounds(50, 120, 165, 25);
		panel.add(lbFirstHost);
		
		lbLastHost = new JLabel("Địa Chỉ Mạng Cuối");
		lbLastHost.setBounds(50, 170, 165, 25);
		panel.add(lbLastHost);
		
		lblIP = new JLabel("Địa Chỉ IP");
		lblIP.setBounds(50, 220, 165, 25);
		panel.add(lblIP);
		
		lblSM = new JLabel("Subnet Mask");
		lblSM.setBounds(50, 270, 165, 25);
		panel.add(lblSM);
		
		lblDG = new JLabel("Default Gateway");
		lblDG.setBounds(50, 320, 165, 25);
		panel.add(lblDG);
		
		lbSetting = new JLabel("Cài đặt");
		lbSetting.setBounds(50, 370, 165, 25);
		panel.add(lbSetting);
		
		listID = new ArrayList<String>();
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
		txtFH.setBounds(180, 120, 200, 25);
		txtFH.setEditable(false);
		panel.add(txtFH);
		
		txtLH = new JTextField();
		txtLH.setBounds(180, 170, 200, 25);
		txtLH.setEditable(false);
		panel.add(txtLH);
		
		txtIP = new JTextField();
		txtIP.setBounds(180, 220, 200, 25);
		txtIP.setEditable(false);
		panel.add(txtIP);
		
		txtSM = new JTextField();
		txtSM.setBounds(180, 270, 200, 25);
		txtSM.setEditable(false);
		panel.add(txtSM);
		
		txtDG = new JTextField();
		txtDG.setBounds(180, 320, 200, 25);
		txtDG.setEditable(false);
		panel.add(txtDG);
		
		cbClass = new JComboBox(listID.toArray());
		cbClass.setBounds(180, 70, 200, 25);
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
				valueIndex = cbClass.getSelectedItem().toString();
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
		
		setting = new ArrayList<String>();
		setting.add("Mặc định");
		setting.add("Sửa Mạng");
		setting.add("Thêm Mạng");
		
		cbSetting = new JComboBox(setting.toArray());
		cbSetting.setBounds(180, 370, 130, 30);
		cbSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexSetting = cbSetting.getSelectedIndex();
				if(indexSetting == 0) ktraVaoMang = true;
				else if(indexSetting == 1 || indexSetting == 2) ktraVaoMang = false;
				System.out.println(indexSetting);
				if(indexSetting == 0) {
					txtFH.setEditable(false);
					txtLH.setEditable(false);
					txtIP.setEditable(false);
					txtDG.setEditable(false);
					txtSM.setEditable(false);
					cbClass.setEditable(false);
					cbClass.setEnabled(true);
					cbClass.getEditor().setItem(listID.toArray());
					loadGUI();
				}
				else if(indexSetting == 1) {
					cbClass.setEditable(true);
					txtFH.setEditable(true);
					txtLH.setEditable(true);
					txtIP.setEditable(true);
					txtDG.setEditable(true);
					txtSM.setEditable(true);
					cbClass.setEnabled(false);
				}
				else if(indexSetting == 2) {
					cbClass.setEditable(true);
					cbClass.getEditor().setItem("-1");
					txtFH.setEditable(true);
					txtFH.setText("");
					txtLH.setEditable(true);
					txtLH.setText("");
					txtIP.setEditable(true);
					txtIP.setText("");
					txtDG.setEditable(true);
					txtDG.setText("");
					txtSM.setEditable(true);
					txtSM.setText("");
					cbClass.setEnabled(false);
				}
				
			}
			
		});
		panel.add(cbSetting);
		
		btnSubmit = new JButton("Nhập");
		btnSubmit.setBounds(310, 370, 70, 30);
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Boolean check = checkInput(txtFH.getText(), txtLH.getText(), txtIP.getText(), txtDG.getText(), txtSM.getText(), userName, indexSetting);
				if(check == false) {
					ktraVaoMang = false;
					JOptionPane.showMessageDialog(panel, "Vui Lòng Nhập Lại !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					String firstIP = txtFH.getText();
					String lastIP = txtLH.getText();
					String subnetMask = txtSM.getText();
					String defaultGateway = txtDG.getText();
					String ipAdmin = txtIP.getText();
					try {
						if(indexSetting == 1) {		
							ktraVaoMang = true;
							db.updateQuery(("update iptable set firstIP = "+ "\"" +firstIP + "\"" + " ,lastIP = "+ "\"" + lastIP + "\"" + " ,subnetMask = " + "\"" + subnetMask + "\"" + " ,defaultGateway = " + "\"" + defaultGateway + "\"" +" where iD = " + cbClass.getSelectedItem().toString()));
							db.updateQuery("update ipinfor set iP = " +"\""+ ipAdmin + "\""+ " where iD = " + cbClass.getSelectedItem().toString() + " and ipAdmin = 1");
							System.out.println("update ipinfor set iP = " +"\""+ ipAdmin + "\""+ " where iD = " + cbClass.getSelectedItem().toString() + " and ipAdmin = 1");
							JOptionPane.showMessageDialog(panel, "Cập Nhật Thành Công", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
							cbSetting.setSelectedIndex(0);
						}
						else if (indexSetting == 2) {
							ktraVaoMang = true;
							db.updateQuery("insert into iptable(firstIP,lastIP,subnetMask,defaultGateway,username) values (" + "\"" + firstIP + "\",\"" + lastIP + "\",\"" + subnetMask + "\",\"" + defaultGateway + "\",\"" + userName + "\"" +")");
							String id = getID(firstIP, lastIP, ipAdmin, defaultGateway, subnetMask, userName);
							db.updateQuery("insert into ipinfor(iD, iP, port, timeRemain, ipAdmin, macAddress) values (" + "\"" + id + "\"" + ",\"" + ipAdmin + "\"" + ",\"5000\", \"-1\", 1, \"AA-AA-AA-AA-AA\"" + ")");
							JOptionPane.showMessageDialog(panel, "Thêm Thành Công", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
							listID.add(id);
							DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listID.toArray());
							cbClass.setModel( model );
							cbSetting.setSelectedIndex(0);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			
		});
		panel.add(btnSubmit);
		
		
		btnQuayLai = new JButton("Thoát Mạng  (X)");
		btnQuayLai.setBounds(50, 420, 150, 45);
		btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame.setVisible(false);
				GUI_Index gui_index = new GUI_Index();
			}
		});
		panel.add(btnQuayLai);
		
		btnJoin = new JButton("Vào Mạng  =>");
		btnJoin.setBounds(230, 420, 150, 45);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			String valueIndex = cbClass.getSelectedItem().toString();
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ktraVaoMang == true) {
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
						
						String[] args = {valueIndex, userName};
						Server.main(args);
						frame.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(panel, "Chọn \"Nhập\" để kiểm tra dữ liệu đầu vào", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				loadGUI();
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
	public void loadGUI() {
		String firstIP = null;
		String lastIP = null;
		String subnetMask = null;
		String defaultGateway = null;
		String ipAdmin = null;
		try {
			System.out.println("hihi");
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
	public Boolean checkInput(String FH, String LH, String IP, String DG, String SM, String name, int choose) {
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
					System.out.println("sai SM");
					return false;
				}
			}
			String[] FHs = FH.split("\\.");
			String[] LHs = LH.split("\\.");
			for(int i = 0; i< FHs.length ; i++) {
				if(Integer.parseInt(FHs[i]) > Integer.parseInt(LHs[i])) {
					System.out.println("sai FH");
					return false;
				}
			}
			if(choose == 2) {
				rs = db.executeQuery("select * from iptable where firstIP = "+ "\"" + FH + "\"" +" and lastIP = " + "\"" + LH + "\"" + " and defaultGateway = " + "\"" + DG + "\"" + " and subnetMask = " + "\"" + SM + "\"" + " and username = " + "\"" +  name + "\"");
				int dem = 0;
				while(rs.next()) {
					System.out.println("check: " + rs.getString(1));
					dem++;
				}
				if(dem != 0) {
					return false;
				}
			}
		} catch (Exception e) {
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
