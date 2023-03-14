package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * socket 으로 부터 request 받는 곳
 */
public class Request {
    Socket socket;
    BufferedReader inSocket;
    String currentPath;
    String method;
    String path;
    String boundary = "";
    Map<String, String> requestHeader;
    Map<String, String> bodyData;
    StringBuilder date;

    /**
     * 생성자.
     *
     * @param so : socket 주입
     * @throws IOException : 예외처리
     */
    public Request(Socket so) throws IOException {
        socket = so;
        requestHeader = new HashMap<>();
        bodyData = new HashMap<>();
        inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        date = new StringBuilder();

    }

    /**
     * socket 의 Request 를 받아 데이터를 활용할 수 있게 전처리 과정을 거침.
     */
    public void run() {
        try {
            currentPath = Paths.get("").toAbsolutePath().toString();

            System.out.println("First Line");
            String firstLine = inSocket.readLine();
            System.out.println(firstLine);
            String[] firstLines = firstLine.split(" ");
            method = firstLines[0].trim();
            path = firstLines[1].trim();
            System.out.println("Method :" + method + ": path:" + path);
            String line;
            System.out.println("\r\nRequest Header");
            while ((line = inSocket.readLine()) != null) {
                if (line.contains("boundary")) {
                    String[] split = line.split("=");
                    boundary = split[1];
                }
                //앞의 조건은 POST ,DELETE 에 해당 뒤의 조건은 GET에 해당
                if (line.equals("--" + boundary) || (method.equals("GET")
                        && line.trim().length() == 0)) {
                    break;
                }
                if (line.trim().length() != 0) {
                    System.out.println(line);
                    String[] split = line.split(": ");
                    requestHeader.put(split[0], split[1]);
                }
            }
            //GET 일 경우 스킵
            if (!method.equals("GET")) {
                System.out.println("\r\nRequest Body Header");
                while ((line = inSocket.readLine()) != null) {
                    if (line.trim().length() == 0) {
                        break;
                    }
                    String[] split = line.split(": ");
                    bodyData.put(split[0], split[1]);
                    System.out.println(line);
                }
                System.out.println("\r\nRequest Body");
                while ((line = inSocket.readLine()) != null) {
                    if (line.equals("--" + boundary + "--")) {
                        break;
                    }
                    date.append(line).append("\r\n");
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
