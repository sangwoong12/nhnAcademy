package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class snc {
    public static void main(String[] args) {
        if(args[0].equals("-l")){

        }
        else{

        }
    }
    public void server(String[] args){
        int port = Integer.parseInt(args[1]);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void client(String[] args){
        try {
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream out = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
                if(line.contains("Enter your name:"));
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
