package DHCP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;

public class Server {
	private int port;
	private InetAddress clientIP;
	private NetworkInterface network;
	private int clientPort;
	public static Map<Data, Integer> listSK;
	public Server(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		Server.listSK = new HashMap<Data, Integer>();
		Server server = new Server(5000);
		server.execute();
	}
	
	private void execute() throws Exception {
		DatagramSocket server = new DatagramSocket(port);
		System.out.println(server);
		System.out.println("Server is listening..."); 
		ClientAlive clientAlive = new ClientAlive();
		clientAlive.start();
		while (true) {
			String data = receiveData(server);
			System.out.println(data);
			String[] arrData = data.split("_");
			if(data.equals("alive")) {
				for (Map.Entry<Data, Integer> item : listSK.entrySet()) {
					InetAddress hostTemp = item.getKey().getHost();
					int portTemp = item.getKey().getPort();
					if (hostTemp.equals(clientIP) && portTemp == clientPort) {
						item.getKey().setTimeRemain(0);
						System.out.println(clientIP + " " + clientPort + " alive");
					}
				}
			}
			else if(checkExistClient(clientIP, clientPort) == false) {
				sendData(menu(), server, clientIP, clientPort);
				Data dataTemp = new Data(clientIP,clientPort);
				listSK.put(dataTemp, -1);
				System.out.println("Da ket noi voi" + clientIP + " " + clientPort);
			}
			else if(arrData.length == 2){
				int key = Integer.parseInt(arrData[1]);
				int value = Integer.parseInt(arrData[0]);
				switch (key) {
				case 1:
					int numberRand = (int)(Math.random() * 255);
					if(listSK.containsValue(numberRand) == false) {
						
						sendData(numberRand, server, clientIP, clientPort);
						for(Map.Entry<Data, Integer> item : listSK.entrySet()) {
							InetAddress hostTemp = item.getKey().getHost();
							int portTemp = item.getKey().getPort();
							if(hostTemp.equals(clientIP) && portTemp == clientPort) {
								item.setValue(numberRand);
							}
						}
						break;
					}
					break;
				case 2:
					System.out.println(key + " " + value);
					if(value < 0 || value > 255) {

						sendData(-1, server, clientIP, clientPort);						
					}else if(listSK.containsValue(value)) {

						sendData(-2, server, clientIP, clientPort);
					}else {

						sendData(-3, server, clientIP, clientPort);
						
						for(Map.Entry<Data, Integer> item : listSK.entrySet()) {
							InetAddress hostTemp = item.getKey().getHost();
							int portTemp = item.getKey().getPort();
							if(hostTemp.equals(clientIP) && portTemp == clientPort) {
								item.setValue(value);
							}
						}
					}
					break;
				case 3:
					for (Data item : listSK.keySet()) {
						if(item.getHost().equals(clientIP) && item.getPort() == clientPort) {
							listSK.remove(item);
							System.out.println("Da ngat ket noi voi " + clientIP + "" + clientPort);
						}
					}
					break;

				default:
					break;
				}
			}
		}
	}
	
	private String menu() {
		return "1.Cap tu dong\n2.Cap bang tay\n3.Thoat\n";
	}
	
	private String receiveData(DatagramSocket server) throws Exception {
		byte[] temp = new byte[1024];
		DatagramPacket receive_Packet = new DatagramPacket(temp,temp.length);
		server.receive(receive_Packet);
		clientIP = receive_Packet.getAddress();
		
		clientPort = receive_Packet.getPort();
		return new String(receive_Packet.getData()).trim();
	}
	
	private void sendData(int value, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException
	{
		byte[] temp = new byte[1024];
		temp = String.valueOf(value).getBytes();
		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
		server.send(send_result_Packet);
	}
	
	
	private void sendData(String value, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException
	{
		byte[] temp = new byte[1024];
		temp = value.getBytes();
		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
		server.send(send_result_Packet);
	}
	
	private boolean checkExistClient(InetAddress clientIP, int clientPort) {
		for(Data item: listSK.keySet()) {
			if(item.getHost().equals(clientIP) && item.getPort() == clientPort) {
				return true;
			}
		}
		return false;
	}
}

class Data{
	private InetAddress host;
	private int port;
	private int timeRemain;
	public Data(InetAddress host, int port) {
		this.host = host;
		this.port = port;
		this.timeRemain = 0;
	}
	public InetAddress getHost() {
		return host;
	}
	public void setHost(InetAddress host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeRemain() {
		return timeRemain;
	}
	public void setTimeRemain(int timeRemain) {
		this.timeRemain = timeRemain;
	}	
}

class ClientAlive extends Thread{
	@Override
	public void run() {
		while (true) {
			try {
				for(Map.Entry<Data, Integer> item : Server.listSK.entrySet()) {
					int timeRemain = item.getKey().getTimeRemain();
					if(timeRemain > 10) {
						Server.listSK.remove(item);
					}
					else {
						item.getKey().setTimeRemain(timeRemain + 1);
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}






