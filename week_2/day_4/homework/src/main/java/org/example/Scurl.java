package org.example;

import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Simple curl 동작 구현.
 *
 */
public class Scurl {

    static Options options = null;
    static Map<String, String> optionsMap = null;
    static URL url = null;
    static DataOutputStream dos = null;
    static BufferedReader in = null;
    static BufferedWriter out = null;

    static CommandLine parse = null;
    static boolean header = false;
    static StringBuilder requestHeader = null;
    static StringBuilder responseHeader = null;
    static StringBuilder data = null;
    static int count = 1;
    static String location = null;
    static String status = "";

    /**
     * 필요한 인스턴스 초기화를 해주고 옵션에 따라 맵에 등록하여 처리 할수있도록 함.
     *
     * @param args : 인자 값에 따라 결과가 다름
     * @throws IOException : 예외 처리.
     * @throws ParseException : 예외 처리.
     */
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineParser parser = getCommandLineParser();
        //초기 설정
        optionsMap = new HashMap<>();
        data = new StringBuilder();

        try {
            url = new URL(args[args.length - 1]);
        } catch (MalformedURLException | NullPointerException e) {
            usage();
        }

        try (Socket socket = new Socket(url.getHost(), 80)) {
            dos = new DataOutputStream(socket.getOutputStream());
            out = new BufferedWriter(new OutputStreamWriter(dos));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            parse = parser.parse(options, args);


            while (count > 0) {
                requestHeader = new StringBuilder();
                responseHeader = new StringBuilder();
                if (!status.matches("^(301|302|307|308)")){
                    count = 0;
                }
                makeRequestHeader();
                print(header);
                count--;
            }
            out.close();
            in.close();
        } catch (IOException e) {
            throw new IOException(e);
        } catch (ParseException e) {
            throw new ParseException("parse error");
        }
    }

    /**
     * 요청할때 필요한 헤더를 만드는 곳.
     *
     * @throws IOException : 예외 처리.
     */
    public static void makeRequestHeader() throws IOException {
        if (optionsMap.containsKey("X")) {
            String commend = parse.getOptionValue("X")
                    .isEmpty() ? "GET" : parse.getOptionValue("X");
            requestHeader.append(commend).append(" /")
                    .append(commend.toLowerCase()).append(" HTTP/1.1 \r\n");
        } else if (url.getPath().equals("/post")) {
            requestHeader.append("POST").append(" ")
                    .append(url.getPath()).append(" HTTP/1.1\r\n");
        } else {
            if (location != null) {
                requestHeader.append("GET").append(" ")
                        .append(location).append(" HTTP/1.1 \r\n");
            } else {
                requestHeader.append("GET").append(" ")
                        .append(url.getPath()).append(" HTTP/1.1\r\n");
            }
        }
        requestHeader.append("Host: ").append(url.getHost()).append("\r\n");
        requestHeader.append("User-Agent: Java\r\n");
        requestHeader.append("Accept: */*\r\n");
        if (optionsMap.containsKey("H")) {
            requestHeader.append(parse.getOptionValue("H")).append("\r\n");
        }
        if (optionsMap.containsKey("d")) {
            requestHeader.append("Content-Length: ").append(parse.getOptionValue("d")
                    .length()).append("\r\n\r\n");
            data.append(parse.getOptionValue("d")).append("\r\n");
        }
        if (optionsMap.containsKey("F")) {
            String boundaryTime = Long.toHexString(System.currentTimeMillis());
            requestHeader.append("Connection: Keep-Alive\r\n");
            fileUpload(parse.getOptionValues("F"));
        }
        if (optionsMap.containsKey("L")) {
            count = 5;
        }
    }

    private static synchronized void print(boolean isAll) throws IOException {
        out.write(requestHeader.toString());
        if (data.length() != 0) {
            out.write(data.toString());
        }
        out.write("\r\n");
        out.flush();

        //필수 내용
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("location: ")) {
                location = line.replace("location: ", "");
            }
            if (line.contains("HTTP")) {
                status = line;
            }
            responseHeader.append(line);
            responseHeader.append("\n");
            if (line.trim().length() == 0) {
                break;
            }
        }
        if (isAll) {
            System.out.println("Request :");
            System.out.println(requestHeader);
            System.out.println("Response :");
            System.out.println(responseHeader);
        }
        StringBuilder info = new StringBuilder();

        makeInfo(info);

        System.out.println(info);
    }

    /**
     * 옵션 F에서 Upload 처리하는 곳.
     *
     * @param optionValues : F value
     * @throws IOException : 예외처리.
     */
    public static void fileUpload(String[] optionValues) throws IOException {
        String fileName = optionValues[0];
        String path = optionValues[1];
        File file = new File(path);

        String boundaryTime = Long.toHexString(System.currentTimeMillis());
        //data 길이 측정을 위해 별도 String Builder
        try (BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(file))) {
            data.append("--").append(boundaryTime).append("\r\n");
            data.append("Content-Disposition: form-data; name=\"").append(fileName)
                    .append("\"; filename=\"").append(path).append("\"\r\n");
            data.append("Content-Type: application/x-www-form-urlencoded\r\n");
            data.append("Content-Transfer-Encoding: base64\r\n\r\n");
            data.append(Base64.getMimeEncoder().encodeToString(inFile.readAllBytes()));
            data.append("\r\n");
            data.append("--").append(boundaryTime).append("--\r\n");
            requestHeader.append("Content-Length: ").append(data.length()).append("\r\n");
            requestHeader.append("Content-Type: multipart/form-data; boundary=")
                    .append(boundaryTime).append("\r\n\r\n");
        }
    }

    private static void makeInfo(StringBuilder info) throws IOException {
        while (in.ready()) {
            info.append(in.readLine());
            info.append("\n");
        }

    }

    /**
     * 옵션의 정보 등록.
     *
     * @return : 기본 파셔
     */

    public static CommandLineParser getCommandLineParser() {
        options = new Options();
        options.addOption(org.apache.commons.cli.Option.builder("v")
                .desc("verbose, 요청, 응답 헤더를 출력합니다.")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("H")
                .desc("임의의 헤더를 서버로 전송합니다.")
                .hasArg()
                .argName("line")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("d")
                .desc("POST, PUT 등에 데이타를 전송합니다.")
                .hasArg()
                .argName("data")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("X")
                .desc("사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.")
                .hasArg()
                .argName("command")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("L")
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
        formatter.printHelp(" [options] url\nOptions", options);
    }
}
