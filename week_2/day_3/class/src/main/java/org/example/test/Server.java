package org.example.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!Thread.interrupted()){
            }
        } catch (IOException e){}

    }
    public static void main(String[] args){
        try (ServerSocket serverSocket = new ServerSocket(10000)){
            System.out.println("대기");
            Server server = new Server(serverSocket.accept());
            server.start();
            System.out.println("접속");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
