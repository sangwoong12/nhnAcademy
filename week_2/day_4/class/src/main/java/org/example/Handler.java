package org.example;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Handler extends Thread {
    Socket socket;
    BufferedWriter outputSocket;
    BufferedReader inputSocket;

    //공유 자원을 static으로 하면된다.
    static final List<Handler> handlers = new LinkedList<>();
    String ThreadName;
    private static int count = 0;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized void start() {
        this.setThreadName();

        System.out.println("make new Handler");
        System.out.println("접속 :"+this.getThreadName());

        try {
            this.inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }

        super.start();
    }

    public String getThreadName() {
        return ThreadName;
    }

    public void setThreadName () {
        this.ThreadName = String.valueOf(++count);
    }
    @Override
    public void run () {
        handlers.add(this);
        System.out.println(": 접속자" + handlers.size());
        try {
            String sendString;
            while (!Thread.interrupted()) {
                sendString = inputSocket.readLine();
                System.out.println(sendString);
                if(sendString == null){
                    Thread.currentThread().interrupt();
                    handlers.remove(this);
                }
                else {
                    if(sendString.contains("->")){
                        String[] split = sendString.split("->");
                        directMessage(split[0],split[1]);
                    }
                    else{
                        broadcast(sendString);
                    }
                }
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void directMessage (String id, String message) throws IOException {
        synchronized (Handler.handlers) {
            for (Handler handler : Handler.handlers) {
                if (handler.getThreadName().equals(id)) {
                    handler.outputSocket.write(this.ThreadName+"->"+message);
                    handler.outputSocket.newLine();
                    handler.outputSocket.flush();
                    break;
                }
            }
        }
    }
    public void broadcast (String message) throws IOException {
        synchronized (Handler.handlers) {
            for (Handler handler : Handler.handlers) {
                if(!this.ThreadName.equals(handler.ThreadName)){
                    handler.outputSocket.write(this.ThreadName+" :"+message);
                }else {
                    handler.outputSocket.write("나 :"+message);
                }
                handler.outputSocket.newLine();
                handler.outputSocket.flush();
            }
        }
    }
}


