package org.example;

import java.io.*;
import java.net.Socket;

public class Exam1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.64.2", 12345)) {

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread inputThread = new Thread(() -> {
                try{
                    while(!Thread.interrupted()){
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                }catch (IOException e){
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            });

            inputThread.start();

            while (!Thread.interrupted()){
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();
                }

//
//
//            String message = "0123\n";
//            output.write(message);
//            output.newLine();
//            output.flush();
//
//            String value = input.readLine();
//            System.out.println("Received :" + value);
        } catch (IOException ignore) {
            System.err.println("연결을 실패하였습니다.");
        }
    }
}
