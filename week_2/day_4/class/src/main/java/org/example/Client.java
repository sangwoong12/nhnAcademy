package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static Socket socket;
    public Client(int port) throws IOException {
        try {
            socket = new Socket("127.0.0.1", port);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ip 주소 형식을 벗어났습니다.");
        } catch (UnknownHostException e) {
            throw new UnknownHostException("알수없는 host 입니다.");
        }

        SendThread sendThread = new SendThread();
        ReceiveThread receiveThread = new ReceiveThread();
        sendThread.setSocket(socket);
        receiveThread.setSocket(socket);
        sendThread.start();
        receiveThread.start();
    }

    static class ReceiveThread extends Thread {
        Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                //inputStream 값을 한줄 씩읽고 콘솔에 출력
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receiveString;
                while (!Thread.interrupted()) {
                    receiveString = input.readLine();
                    output.write(receiveString);
                    output.newLine();
                    output.flush();
                    }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class SendThread extends Thread {
        Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                //콘솔 입력 값을 한줄 씩읽고 outputStream 적어서 전달
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String sendString;
                while (!Thread.interrupted()) {
                    sendString = input.readLine();
                    output.write(sendString);
                    output.newLine();
                    output.flush();
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
