package com.example.annotationdemo.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/5/6 12:18
 */

public class SocketClientTest {

    public static void main(String[] args) {
        InputStreamReader ir =null;
        BufferedReader bufferedReader = null;
        try {
            Socket socket = new Socket("127.0.0.1", 30000);

            new ClientThread(socket).start();
            while (true){
                ir   = new InputStreamReader(System.in);
                bufferedReader =  new BufferedReader(ir);
                //从客户端输入数据
                String s = bufferedReader.readLine();
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.println(s);
            }
            //把数据发送到服务器

            // 处理服务器返回的数据
        } catch (IOException e) {
            e.printStackTrace();
            try {
                assert bufferedReader != null;
                bufferedReader.close();
                ir.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
