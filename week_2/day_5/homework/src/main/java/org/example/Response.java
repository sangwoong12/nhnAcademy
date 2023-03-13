package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Service 로 부터 받은 header와 body를 Socket으로 전달
 */
public class Response {
    Socket socket;
    BufferedWriter outSocket;
    StringBuilder body;
    StringBuilder responseHeader;

    /**
     * 생성자.
     *
     * @param so : socket 주입
     * @throws IOException : 예외 처
     */

    public Response(Socket so) throws IOException {
        socket = so;
        outSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        responseHeader = new StringBuilder();
        body = new StringBuilder();
    }

    /**
     * outSocket 에 작성하여 응답.
     */
    public void run() {
        try {
            System.out.println("Response : ");
            System.out.println(responseHeader);
            outSocket.write(responseHeader.toString());
            outSocket.newLine();
            outSocket.write(body.toString());
            outSocket.newLine();
            outSocket.flush();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public StringBuilder getResponseHeader() {
        return responseHeader;
    }

    public StringBuilder getBody() {
        return body;
    }
}
