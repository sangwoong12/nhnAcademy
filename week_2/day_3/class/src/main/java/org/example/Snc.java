package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Snc{
  static Socket socket = null;
  static ServerSocket serverSocket = null;
  static InetAddress ia = null;
  public static void main(String[] args) throws IOException {

    if (hasNotArgs(args)) {
      usage();
    } else {
      if (Objects.equals(args[0], "-l")) {
        int port = Integer.parseInt(args[1]);
        try {
          serverSocket = new ServerSocket(port);
        } catch (IOException e) {
          System.out.println("올바르지 않은 port값");
        }
        socket = serverSocket.accept();
      }
      else {
        try {
          client(args);
        } catch (IOException ignored) {

        }
      }
      SendThread sendThread = new SendThread();
      ReceiveThread receiveThread = new ReceiveThread();
      sendThread.setSocket(socket);
      receiveThread.setSocket(socket);
      sendThread.start();
      receiveThread.start();
    }
  }

  private static void client(String[] args) throws IOException {
    ia = InetAddress.getByName(args[0]);
    int port = Integer.parseInt(args[1]);
    socket = new Socket(ia, port);
  }

  private static void usage() {
    System.out.println("Usage: snc [option] [hostname] [port]\n" +
            "Options:\n" +
            "-l   <port>     서버 모드로 동작, 입력 받은 포트로 listen");
  }
  private static boolean hasNotArgs(String[] args) {
    return args.length == 0;
  }
}
class SendThread extends Thread{
  public void setSocket(Socket _socket){
    socket = _socket;
  }
  Socket socket;

  @Override
  public void run() {
    super.run();
    try {
      BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter sendWriter = new PrintWriter(socket.getOutputStream());
      String sendString;
      while (true){
        sendString = tmpbuf.readLine();
        sendWriter.println(sendString);
        sendWriter.flush();
      }
    }catch (IOException e){}
  }
}
class ReceiveThread extends Thread{
  public void setSocket(Socket _socket){
    socket = _socket;
  }
  Socket socket;

  @Override
  public void run() {
    super.run();
    try {
      BufferedReader tmpbuf2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String receiveString;
      while (true){
        receiveString = tmpbuf2.readLine();
        System.out.println(receiveString);
      }
    }catch (IOException e){}
  }
}
