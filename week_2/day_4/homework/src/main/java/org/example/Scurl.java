package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Scurl {
    static Options options = null;
    static Socket socket = null;
    static URL url = null;
    static BufferedReader in = null;
    static BufferedWriter out = null;
    static String request = null;

    public static void main(String[] args) throws IOException {
        CommandLineParser parser = getCommandLineParser();
        if(args == null){
            usage();
        }
        else{
            url = new URL(args[args.length - 1]);
        }
        boolean optionD = false;
        String optionDValue = null;
        boolean optionX = true;
        boolean optionH = false;
        String optionHValue = null;
        boolean optionV = false;


        try (Socket socket = new Socket(url.getHost(), 80)){

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            CommandLine parse = parser.parse(options, args);
            for (Option option : parse.getOptions()) {
                if (option.getOpt().equals("X")) {
                    optionX(parse.getOptionValue("X").isEmpty() ? "/GET" : "/" + parse.getOptionValue("X"), url.getHost());
                    optionX = false;
                } else if (option.getOpt().equals("H")) {
                    optionH = true;
                    optionHValue = parse.getOptionValue("H");
                } else if (option.getOpt().equals("d")) {
                    optionD = true;
                    optionDValue = parse.getOptionValue("d");
                } else if (option.getOpt().equals("v")) {
                    optionV = true;
                } else if (option.getOpt().equals("F")){
                    String[] fs = parse.getOptionValues("F");
                    optionF(fs);
                } else if (option.getOpt().equals("L")){

                }
            }
            //header 머리
            if (optionX) {
                optionX(url.getPath(), url.getHost());
            }
            // 추가 요청 헤더
            if(optionH){
                optionH(optionHValue);
            }
            // POST PUT
            if (optionD) {
                optionD(optionDValue);
            }
            // 프린트
            print(optionV);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    public static void optionF(String[] optionValues) throws IOException {

        String upload = optionValues[0];
        String path = optionValues[1];
        String[] split = path.split("/");
        String fileName = split[split.length-1];
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        out.write("Content-Type: application/json");

        int bytesAvailable = fileInputStream.available();
        int maxBufferSize = 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        while (bytesRead > 0)
        {
            dos.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }
        dos.writeBytes("\r\n");
        fileInputStream.close();

        dos.flush();
    }
    public static void optionL(){

    }
    public static void optionD(String optionValue) throws IOException {
        out.write("Content-Length: " + optionValue.length() + "\r\n\r\n");
        out.write(optionValue);
        out.write("\r\n");
    }

    public static void optionH(String optionValue) throws IOException {
        out.write(optionValue + "\r\n");
    }

    public static void optionX(String command, String host) throws IOException {
        if (command.equals("/get") || command.equals("/GET")) {
            request = "GET /get HTTP/1.1";
        } else if (command.equals("/post") || command.equals("/POST")) {
            request = "POST /post HTTP/1.1";
        } else if (command.equals("/head") || command.equals("/HEAD")){
            request = "HEAD /head HTTP/1.1";
        } else if (command.equals("/put") || command.equals("/PUT")){
            request = "PUT /put HTTP/1.1";
        } else if (command.equals("/delete") || command.equals("/DELETE")){
            request = "DELETE /delete HTTP/1.1";
        } else throw new IOException("Option -X 값이 잘못되었습니다.");
        out.write(request + "\r\n");
        out.write("Host: " + host + "\r\n");
        out.write("User-Agent: Java" + "\r\n");
    }

    private static void print(boolean isAll) throws IOException {
        out.write("\r\n");
        out.flush();

        String line;
        //응답
        StringBuilder responseInfo = new StringBuilder();
        makeResponseInfo(responseInfo);
        //필수 내용
        StringBuilder info = new StringBuilder();
        makeInfo(info);
        //요청
        StringBuilder requestInfo = new StringBuilder();
        makeRequestInfo(requestInfo);

        if (isAll) {
            System.out.println(responseInfo);
            System.out.println(requestInfo);
        }
        System.out.println(info);

        in.close();
        out.close();
    }

    private static void makeResponseInfo(StringBuilder header) throws IOException {
        String line;
        while ((line = in.readLine()) != null && !line.equals("")) {
            header.append(line);
            header.append("\n");
        }
    }

    private static void makeInfo(StringBuilder info) throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            info.append(line);
            info.append("\n");
            if (!in.ready()) {
                break;
            }
        }
    }

    private static void makeRequestInfo(StringBuilder requestInfo) throws UnknownHostException {
        requestInfo.append("*   Trying" + InetAddress.getByName(url.getHost()) + "...");
        requestInfo.append("* Connected to " + url.getHost() + "("
                + InetAddress.getByName(url.getHost()) + ") port"  + url.getPort());
        requestInfo.append(request);
        requestInfo.append("Host: " + url.getHost());
        requestInfo.append("User-Agent: java\r\n");
    }

    private static CommandLineParser getCommandLineParser() {
        options = new Options();
        options.addOption(Option.builder("v")
                .desc("verbose, 요청, 응답 헤더를 출력합니다.")
                .build());
        options.addOption(Option.builder("H")
                .desc("임의의 헤더를 서버로 전송합니다.")
                .hasArg()
                .argName("line")
                .build());
        options.addOption(Option.builder("d")
                .desc("POST, PUT 등에 데이타를 전송합니다.")
                .hasArg()
                .argName("data")
                .build());
        options.addOption(Option.builder("X")
                .desc("사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.")
                .hasArg()
                .argName("command")
                .build());
        options.addOption(Option.builder("L")
                .desc("서버의 응딥이 30x 계열이면 다음 응답을 따라 갑니다.")
                .build());
        options.addOption(Option.builder("F")
                .desc("multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.")
                .hasArgs()
                .valueSeparator('=')
                .argName("name=content")
                .build());
        return new DefaultParser();
    }

    public static void usage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("scurl [options] url\nOptions", options);
    }
}
