package com.example.annotationdemo.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/5/6 15:43
 */

public class ServerThread extends Thread {

    private Socket socket;
    private final BufferedReader bufferedReader;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        super.run();
        try {
            String line = bufferedReader.readLine();
            ArrayList<Socket> socketList = SocketServerTest.socketList;
            socket.getOutputStream();
            for (Socket socketOut : socketList) {
                PrintStream printStream = new PrintStream(socketOut.getOutputStream());
                printStream.println(line);
            }
        } catch (IOException e) {
            SocketServerTest.socketList.remove(socket);
            e.printStackTrace();
        }
    }
}
