package com.example.annotationdemo.test.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveThread implements Runnable {
	
	private DatagramSocket ds ;
	
	public ReceiveThread(DatagramSocket ds){
		this.ds = ds ; 
	}

	@Override
	public void run() {
		
		try {
			
			while(true){
				
				byte[] bytes = new byte[1024] ;
				int length = bytes.length ;
				
				DatagramPacket dp = new DatagramPacket(bytes, length) ;
				
				ds.receive(dp) ;
				
				InetAddress inetAddress = dp.getAddress() ;
				String ip = inetAddress.getHostAddress() ;
				
				byte[] resultBytes = dp.getData() ;
				int resultLength = dp.getLength() ;
				
				System.out.println(ip + "˵: " + new String(resultBytes , 0 , resultLength));
		
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
	}

}
