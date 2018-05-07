package com.example.annotationdemo.test.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/5/6 12:18
 */

public class SocketServerTest {

    public static ArrayList<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(30000);
            while (true){
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                new ServerThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
