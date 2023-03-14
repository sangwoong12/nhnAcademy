package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * request 를 받아 response 를 만드는 역할.
 */
public class Service extends Thread {
    Request request;
    Response response;
    StringBuilder body;
    StringBuilder responseHeader;
    Status status;

    /**
     * 생성자.
     *
     * @param socket : main에서 주입.
     * @throws IOException : 예외처리
     */

    public Service(Socket socket) throws IOException {
        this.request = new Request(socket);
        this.response = new Response(socket);
        this.body = response.getBody();
        this.responseHeader = response.getResponseHeader();
    }

    @Override
    public void run() {
        request.run();
        if (request.method.equals("GET")) {
            if (request.path.equals("/")) {
                status = Status.OK;
                request.path = "";
                getPage();
            } else {
                String[] split = request.path.split("/");
                if (!split[split.length - 1].contains(".")) {
                    status = Status.OK;
                    getPage();
                } else {
                    makeGetResponseBody();
                }
            }
        } else if (request.method.equals("DELETE")) {
            makeDeleteResponseBody();
        } else if (request.method.equals("POST")) {
            makePostResponseBody();
        } else {
            status = Status.FORBIDDEN;
            body.append("Forbidden ");
        }

        makeResponseHeader();
        response.run();
    }

    private void makePostResponseBody() {
        Map<String, String> requestHeader = request.requestHeader;
        Map<String, String> bodyData = request.bodyData;
        //multipart 요청여부 확인
        if (requestHeader.get("Content-Type").contains("multipart")) {
            String s = bodyData.get("Content-Disposition");
            String[] split = s.split("=");

            File dir = new File(request.currentPath + request.path);
            boolean same = false;
            //겹치는지 확인
            String[] filenames = dir.list();
            for (String filename : Objects.requireNonNull(filenames)) {
                if (filename.equals(split[split.length - 1].replace("\"", ""))) {
                    same = true;
                    break;
                }
            }
            if (same) {
                status = Status.CONFLICT;
                body.append("Conflict");
            } else {
                if (request.path.equals("/")) {
                    request.path = "";
                }
                File file = new File(request.currentPath + request.path,
                        split[split.length - 1].replace("\"", ""));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // 파일에 쓰기
                    writer.write(request.date.toString());
                    writer.newLine();
                    // 버퍼 및 스트림 뒷정리
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                status = Status.OK;
                body.append("Upload");
            }
        } else {
            status = Status.METHOD_NOT_ALLOWED;
            body.append("Method Not Allowed");
        }
    }

    private void makeDeleteResponseBody() {
        if (request.path.equals("/")) {
            request.path = "";
        }
        //위치에 파일 여부 확인
        Map<String, String> bodyData = request.bodyData;
        String s = bodyData.get("Content-Disposition");
        String[] split = s.split("=");
        File file = new File(request.currentPath + request.path,
                split[split.length - 1].replace("\"", ""));

        //가능 여부
        if (file.exists()) {
            //Post man test 시 오래걸림
            boolean delete = file.delete();
            if (delete) {
                status = Status.NO_CONTENT;
                body.append("NO Content");
            }
        } else if (file.canWrite() && !file.canRead()) {
            status = Status.FORBIDDEN;
            body.append("Forbidden");
        } else {
            status = Status.NOT_FOUND;
            body.append("NOT Found");
        }
    }

    private void makeGetResponseBody() {
        String fileName = request.currentPath + request.path;
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //파일이 존재하지 않거나 읽기 불가능 하면 FileNotFoundException 발생
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(new String(line.getBytes(), StandardCharsets.UTF_8));
                body.append("\r\n");
            }
            status = Status.OK;
        } catch (FileNotFoundException e) {
            if (file.canWrite()) { //쓰는게 가능하다면 파일은 있지만 읽기 권한이 없다는 걸 의미
                status = Status.FORBIDDEN;
                body.append("Forbidden");
            } else {
                status = Status.NOT_FOUND;
                body.append("Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 기본적인 헤더 제작.
     */
    public void makeResponseHeader() {
        responseHeader.append("HTTP/1.1 ").append(status.getCode()).append("\r\n");
        responseHeader.append("Date: ").append(new Date()).append("\r\n");
        responseHeader.append("Content-Type: text/html; charset=utf-8\r\n");
        responseHeader.append("Content-Length: ").append(body.toString().length()).append("\r\n");
        responseHeader.append("Server: java\r\n");
    }

    /**
     * 페이지 요청시 처리.
     */
    public void getPage() {
        body.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>File List</title>")
                .append("</head>")
                .append("<body>");
        File dir = new File(request.currentPath + request.path);
        String[] filenames = dir.list();

        for (String filename : Objects.requireNonNull(filenames)) {
            body.append("<p><a href=\"").append(request.path)
                    .append("/").append(filename).append("\">").append(filename).append("</a></p>");
        }
        body.append("</body>");
    }
}
