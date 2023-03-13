package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * request 를 받아 response 를 만드는 역할.
 */
public class Service extends Thread {
    Request request;
    Response response;
    StringBuilder body;
    StringBuilder responseHeader;

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
        if (request.method == null) {
            Thread.currentThread().interrupt();
        } else if (request.method.equals("GET") && request.path.equals("/")) {
            getPage();
            makeResponseHeader();
        } else if (request.method.equals("GET")) {
            String[] split = request.path.split("/");
            if (!split[split.length - 1].contains(".")) {
                getPage();
                makeResponseHeader();
            } else {
                makeGetResponseBody();
                makeResponseHeader();
            }
        } else if (request.method.equals("DELETE")) {
            makeDeleteResponseBody();
            makeResponseHeader();
        } else if (request.method.equals("POST")) {
            makePostResponseBody();
            makeResponseHeader();
        } else {
            responseHeader.append("HTTP/1.1 403 Forbidden \r\n");
            body.append("Forbidden ");
        }
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
            String[] filenames = dir.list();
            boolean same = false;
            //겹치는지 확인
            assert filenames != null;
            for (String filename : filenames) {
                if (filename.equals(split[split.length - 1].replace("\"", ""))) {
                    same = true;
                    break;
                }
            }
            if (same) {
                responseHeader.append("HTTP/1.1 409 Conflict\r\n");
                body.append("Conflict");
            } else {
                File file = new File(request.currentPath + request.path
                        + split[split.length - 1].replace("\"", ""));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    if(!file.exists()) {
                        file.createNewFile();
                    }
                    // 파일에 쓰기
                    writer.write(request.date.toString());
                    writer.newLine();
                    // 버퍼 및 스트림 뒷정리
                    writer.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
                responseHeader.append("HTTP/1.1 200 OK \r\n");
                body.append("Upload");
            }
        } else {
            responseHeader.append("HTTP/1.1 405 Method Not Allowed\r\n");
            body.append("Method Not Allowed");
        }
    }

    private void makeDeleteResponseBody() {
        //위치에 파일 여부 확인
        Map<String, String> bodyData = request.bodyData;
        String s = bodyData.get("Content-Disposition");
        String[] split = s.split("=");
        String fileName = request.currentPath + request.path
                + split[split.length - 1].replace("\"", "");
        File file = new File(fileName);

        //가능 여부
        if (file.exists()) {
            //Post man test 시 오래걸림
            boolean delete = file.delete();
            if(delete){
                responseHeader.append("HTTP/1.1 204 NO Content\r\n");
                body.append("NO Content");
            }
        } else if (file.canWrite() && !file.canRead()) {
            responseHeader.append("HTTP/1.1 403 Forbidden \r\n");
            body.append("Forbidden");
        } else {
            responseHeader.append("HTTP/1.1 404 NOT Found\r\n");
            body.append("NOT Found");
        }
    }

    private void makeGetResponseBody() {
        String fileName = request.currentPath + request.path;
        File file = new File(fileName);
        try (FileReader reader = new FileReader(file)) {
            //파일이 존재하지 않거나 읽기 불가능 하면 FileNotFoundException 발생
            int line;

            responseHeader.append("HTTP/1.1 200 OK\r\n");

            while ((line = reader.read()) != -1) {
                body.append((char) line);
            }
        } catch (FileNotFoundException e) {
            if (file.canWrite()) { //쓰는게 가능하다면 파일은 있지만 읽기 권한이 없다는 걸 의미
                responseHeader.append("HTTP/1.1 403 Forbidden\r\n");
                body.append("Forbidden");
            } else {
                responseHeader.append("HTTP/1.1 404 Not Found\r\n");
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
        responseHeader.append("Date: ").append(getServerTime()).append("\r\n");
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

        assert filenames != null;
        for (String filename : filenames) {
            body.append("<p><a href=\"").append(request.path.replace("/", ""))
                    .append("/").append(filename).append("\">").append(filename).append("</a></p>");
        }
        body.append("</body>");

        responseHeader.append("HTTP/1.1 200 OK\r\n");
    }

    private String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return dateFormat.format(calendar.getTime());
    }

}
