package org.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * nc 프로그램과 유사하게 동작하는 simple-nc.
 */
public class snc {
    static Options options = null;
    static Socket socket = null;
    static ServerSocket serverSocket = null;
    static InetAddress ia = null;
    static CommandLine cmd = null;

    /**
     * 인자가 없을경우 usage를 보여주고 아닐경우 옵션이 l 뿐이기 때문에 간단하게 if-else 로 처리
     * @param args : [option] [hostname] [port]
     * @throws IOException : IO 예외처리
     * @throws ParseException : parse 예외처리
     */
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineParser parser = getCommandLineParser();

        if (args.length == 0) {
            usage();
        } else {
            try {
                cmd = parser.parse(options, args);
            } catch (ParseException e) {
                throw new ParseException("parse 단계 오류");
            }
            if (cmd.hasOption("l")) {
                server(cmd.getOptionValue("l"));
                socket = serverSocket.accept();
            } else {
                client(args);
            }
            SendThread sendThread = new SendThread();
            ReceiveThread receiveThread = new ReceiveThread();
            sendThread.setSocket(socket);
            receiveThread.setSocket(socket);
            sendThread.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            receiveThread.start();
        }
    }

    private static CommandLineParser getCommandLineParser() {
        options = new Options();
        options.addOption(Option.builder("l")
                .hasArg()
                .argName("port")
                .desc("서버 모드로 동작, 입력 받은 포트로 listen")
                .build());
        return new DefaultParser();
    }

    /**
     * serverSocket 생성을 담당한다. Port 형식을 벗어나면 예외처리를 한다.
     *
     * @param value : Port
     * @throws IOException : NumberFormatException 예외 처리
     */
    public static void server(String value) throws IOException {
        int port;
        try {
            port = Integer.parseInt(value);
            serverSocket = new ServerSocket(port);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 port 번호 입니다.");
        }
    }

    /**
     * socket 생성을 담당한다. IP 주소 형식을 벗어나면 예외처리를 한다.
     *
     * @param args : IP주소, 포트번호
     * @throws IOException : 예외 처리
     */
    public static void client(String[] args) throws IOException {
        try {
            ia = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            socket = new Socket(ia, port);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ip 주소 형식을 벗어났습니다.");
        } catch (UnknownHostException e) {
            throw new UnknownHostException("알수없는 host 입니다.");
        }
    }

    public static void usage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ant [option] \nOptions:", options);
    }

    static class ReceiveThread extends Thread {
        Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                //inputStream 값을 한줄 씩읽고 콘솔에 출력
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String receiveString;
                while (!Thread.interrupted()) {
                    receiveString = input.readLine();
                    System.out.println(receiveString);
                    System.out.println(this.isInterrupted());
                    if(receiveString.equals("ctrl-c")){
                        socket.close();
                        Thread.currentThread().interrupt();
                    }
                    else{
                        output.write(receiveString);
                        output.newLine();
                        output.flush();
                    }
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class SendThread extends Thread {
        Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                //콘솔 입력 값을 한줄 씩읽고 outputStream 적어서 전달
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter output = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
                String sendString;
                while (!Thread.interrupted()) {
                    sendString = input.readLine();
                    output.write(sendString);
                    output.newLine();
                    output.flush();

                    if(sendString.equals("ctrl-c")) {
                        socket.close();
                        Thread.currentThread().interrupt();
                    }
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
