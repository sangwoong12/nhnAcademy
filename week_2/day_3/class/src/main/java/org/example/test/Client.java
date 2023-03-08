package org.example.test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread{
    Socket socket;

    public Client(Socket socket) {
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
                output.write(console.readLine());
                output.flush();
                System.out.println("a");
            }
        } catch (IOException e){}
    }

    public static void main(String[] args) throws UnknownHostException {
        try(Socket socket = new Socket("localhost",10000)){
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
