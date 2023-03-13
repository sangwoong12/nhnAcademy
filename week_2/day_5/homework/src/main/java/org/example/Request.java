package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * socket 에서 보낸 요청을 필요에 따라 데이터 구조화 하는곳.
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
     * socket 에서 보낸 데이터를 사용할 수 있게 전처리.
     */
    public void run() {
        try {
            currentPath = Paths.get("").toAbsolutePath().toString();

            //method path 정보 얻기
            String firstLine = inSocket.readLine();
            if (firstLine != null) {
                String[] s = firstLine.split(" ");
                method = s[0];
                path = s[1];
            }
            String line;

            //header 정보 얻기
            while ((line = inSocket.readLine()) != null) {
                //boundary 라인이면 빠져나옴
                if ((line.contains(boundary) && !boundary.equals(""))
                        || line.trim().length() == 0) {
                    break;
                }
                if (!line.isEmpty()) {
                    try {
                        if (line.contains("Content-Type:")) {
                            String[] split = line.split("=");
                            boundary = split[1];
                        }
                        makeMaps(line, requestHeader);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }

            //GET 일경우 data 가 존재하지 않기때문에 skip
            if (method == null) {
                Thread.currentThread().interrupt();
            } else {
                if (!method.equals("GET")) {
                    //data 정보 저장
                    String line2;
                    while ((line2 = inSocket.readLine()) != null) {
                        if (line2.trim().length() == 0) {
                            break;
                        }
                        if (!line2.contains(boundary)) {
                            try {
                                makeMaps(line2, bodyData);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //data 내용 저장
                    String line3;
                    while ((line3 = inSocket.readLine()) != null) {
                        if (line3.contains(boundary)) {
                            break;
                        }
                        date.append(line3).append("\r\n");

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeMaps(String line2, Map<String, String> bodyData) {
        String[] split = line2.split(": ");
        bodyData.put(split[0], split[1]);
    }
}
