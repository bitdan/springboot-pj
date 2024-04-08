package com.pj.system.server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @description UDPServer
 * @date 2024/4/8 10:33:12
 */
public class UDPServer {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        byte[] buf = new byte[1024];

        try {

            socket = new DatagramSocket(8077);
            packet = new DatagramPacket(buf, buf.length);

            System.out.println("Server is running...");
            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received Message: " + received);
                writeToFile(received);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void writeToFile(String message) {
        try {
            // 获取用户的主目录
            String userHome = System.getProperty("user.home");
            String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            String fileName = userHome + "\\Desktop\\ReceivedMessages_" + timestamp + ".txt";

            PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
            out.println(message);
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}
