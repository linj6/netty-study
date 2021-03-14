package com.lnjecit.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * 客户端
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write((new Date() + ": hello world").getBytes());
                    Thread.sleep(2000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
