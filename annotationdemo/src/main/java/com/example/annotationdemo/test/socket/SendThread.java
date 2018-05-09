package com.example.annotationdemo.test.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable {
	
	private DatagramSocket ds ;
	
	public SendThread(DatagramSocket ds){
		this.ds = ds ;
	}

	@Override
	public void run() {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
			
			InetAddress inetAddress = InetAddress.getByName("192.168.3.255") ;
			
			String line = null ;
			while((line = br.readLine()) != null){
				
				byte[] bytes = line.getBytes() ;
				int length = bytes.length ;
				
				DatagramPacket dp = new DatagramPacket(bytes, length, inetAddress, 10086) ;
				
				ds.send(dp) ;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}

	}

}
