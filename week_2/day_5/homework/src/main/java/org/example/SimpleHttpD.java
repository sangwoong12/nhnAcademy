package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket 연결 하는 장소 port 를 80으로 설정
 * POST, DELETE 는 postman으로 테스트.
 * 읽기전용 파일을 만들경우 압축이 되지 않아 삭제
 *
 */
public class SimpleHttpD {
    static Socket socket = null;

    /**
     * 접속시도시 새로운 service를 생성하여 요청에 대한 응답처리.
     *
     * @param args : port 번호를 받아 ServerSocket 생성
     */
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.interrupted()) {
                socket = serverSocket.accept();
                new Service(socket).start();
            }
        } catch (IOException e) {
            System.err.println("ServerSocket Error");
        }
    }
}
