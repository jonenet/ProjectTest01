package com.example.annotationdemo.test.socket;

import java.io.IOException;
import java.net.DatagramSocket;

public class ChatRoom {
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket sendDs = new DatagramSocket() ;
		DatagramSocket receiveDs = new DatagramSocket(10086) ;
		
		new Thread(new SendThread(sendDs)).start() ;
		new Thread(new ReceiveThread(receiveDs)).start() ;
		
	}

}
