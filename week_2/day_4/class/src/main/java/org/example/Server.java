package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    int port;
    public Server(int port) {
        this.port = port;
    }


    @Override
    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(!Thread.interrupted()){
                Socket accept = serverSocket.accept();
                new Handler(accept).start();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
