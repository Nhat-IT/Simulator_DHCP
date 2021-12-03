package GUIv2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class workWithData {
	public String receiveData(DatagramSocket server) throws IOException {
		byte[] temp = new byte[1024];
		DatagramPacket receive_Packet = new DatagramPacket(temp, temp.length);
		server.receive(receive_Packet);
		return new String(receive_Packet.getData()).trim();
	}//client use

//	public DatagramPacket createPacket(String value, InetAddress host, int port) {
//		byte[] arrData = new byte[1024];
//		return new DatagramPacket(arrData, arrData.length, host, port);
//	}
	
	public String getMacDevice(String addr) throws Exception {
	       String ma=null; 
	       InetAddress address = InetAddress.getByName(addr);	       
	       NetworkInterface ni=NetworkInterface.getByInetAddress(address);				       
	       byte[]mac=ni.getHardwareAddress();
	       StringBuilder sb=new StringBuilder();				       
	       for(int i=0;i<mac.length;i++){				           
	           sb.append(String.format("%02X%s", mac[i],(i<mac.length-1)?"-":""));
	       }
	       ma=sb.toString();
	       return ma;
	}

	public DatagramPacket createPacket(String request, int iD, InetAddress host, int port) {
		String str = request +"_"+ String.valueOf(iD);
		byte[] arrData = str.getBytes();
		return new DatagramPacket(arrData, arrData.length, host, port);
	}// Client use
//	public void sendData(int value, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException
//	{
//		byte[] temp = new byte[1024];
//		temp = String.valueOf(value).getBytes();
//		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
//		server.send(send_result_Packet);
//	}	
	
	public void sendData(String response, String ip, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException
	{
		String value = response + "_" + ip;
		byte[] temp = new byte[1024];
		temp = value.getBytes();
		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
		server.send(send_result_Packet);
	} //Server use
}
