import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class test {

  //$ scurl <http://httpbin.org/get>
  //$ scurl -X GET <http://httpbin.org/get>
  //$ scurl -v <http://httpbin.org/get>
  //$ scurl -v -H "X-Custom-Header: NA" -v <http://httpbin.org/get>
  //$ scurl -v -X POST -d \\"{"hello":"world"}\\" -H "Content-Type:application/json"  <http://httpbin.org/post>
  //$ scurl -L <http://httpbin.org/status/302>
  //$ scurl -F "upload=@/Users/ASUS/sample.txt" <http://httpbin.org/post>
  public static void main(String[] args) throws Exception {
    Map<String,String> options = new HashMap<>();
    String urlInfo = null;
    if (hasNotArgs(args)) {
      usage();
    }
    // 아무것도 없는 기본 옵션
    else if(args[0].contains("http://")){
      urlInfo = args[0];
    }
    else {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-v") || args[i].equals("-L"))
          options.put(args[i], "null");
        else if (args[i].contains("http://")) {
          urlInfo = args[i];
        } else {
          options.put(args[i], args[i + 1]);
          i += 1;
        }
      }
    }
    URL url = new URL(urlInfo);
    HttpURLConnection con = (HttpURLConnection)url.openConnection();

    //요청 header
    if(Objects.equals(options.get("-L"), "null")) option_L(con);
    if(Objects.equals(options.get("-v"), "null")) option_v(con);
    if(options.get("-X") != null) option_X(options.get("-X"),con);
    if(options.get("-H") != null) option_H(options.get("-H"),con);
    if(options.get("-d") != null) option_d(options.get("-d"),con);
    if(options.get("-F") != null) option_F(options.get("-F"),con);
    con.getResponseCode();
    //응답 header
    if(Objects.equals(options.get("-v"), "null")) option_v(con);
    printCon(con);


    for(String s : options.keySet()){
      System.out.println(s + ": " + options.get(s));
    }
    System.out.println(urlInfo);
  }

  private static void option_F(String optionValue, HttpURLConnection con) throws IOException {
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "------------------------a58665e03e052f33";

    String[] options = optionValue.split("@");
    String[] filenames = options[1].split("/");
    String upload = options[0];
    String filename = filenames[filenames.length-1];
    String filePath = options[1];
    File file = new File(filePath);
    FileInputStream fileInputStream = new FileInputStream(file);

    con.setDoInput(true);
    con.setDoOutput(true);
    con.setUseCaches(false);
    con.setRequestMethod("POST");
    con.setRequestProperty("Connection","Keep-Alive");
    con.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
    DataOutputStream dos = new DataOutputStream(con.getOutputStream());
    dos.writeBytes(twoHyphens + boundary + "\r\n");
    dos.writeBytes("Content-Disposition: form-data; name=\""+upload+"\";filename=\""+ filename +"\"" + "\r\n");
    dos.writeBytes(lineEnd);

    int bytesAvailable = fileInputStream.available();
    int maxBufferSize = 1024;
    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
    byte[] buffer = new byte[bufferSize];

    int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
    while (bytesRead > 0)
    {
      dos.write(buffer, 0, bufferSize);
      bytesAvailable = fileInputStream.available();
      bufferSize = Math.min(bytesAvailable, maxBufferSize);
      bytesRead = fileInputStream.read(buffer, 0, bufferSize);
    }
    dos.writeBytes(lineEnd);
    dos.writeBytes("--" + boundary + "--" + lineEnd);
    fileInputStream.close();

    dos.flush();
  }
  private static void option_L(HttpURLConnection con) throws IOException {
    int responseCode = con.getResponseCode();
    System.out.println(responseCode);
  }

  private static void option_d(String optionValue, HttpURLConnection con) throws IOException {
    optionValue = optionValue.replace("\"","");
    con.setDoOutput(true);
    OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
    writer.write(optionValue);
    writer.flush();
    writer.close();
    con.getOutputStream().close();
  }

  private static void option_H(String optionValue, HttpURLConnection con) {
    String[] options = optionValue.split(":");
    con.setRequestProperty(options[0],options[1]);
  }

  private static void option_v(HttpURLConnection con) throws IOException {
    Map<String, List<String>> headerFields = con.getHeaderFields();
    for(String key : headerFields.keySet()){
      if(key == null);
      else System.out.print(key+": ");
      List<String> stringList = headerFields.get(key);
      for(int i = 0; i < stringList.size(); i++){
        System.out.println(stringList.get(i));
      }
    }
  }

  private static void option_X(String optionValue, HttpURLConnection con) throws ProtocolException {
    con.setRequestMethod(optionValue);
  }

  private static void printCon(HttpURLConnection con) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine+"\n");
    }
    in.close();
    System.out.println(response);
  }
  private static boolean hasNotArgs(String[] args) {
    return args.length == 0;
  }
  private static void usage() {
    System.out.println("Usage: scurl [options] url\n"
        + "Options:\n"
        + "-v                 verbose, 요청, 응답 헤더를 출력합니다.\n"
        + "-H <line>          임의의 헤더를 서버로 전송합니다.\n"
        + "-d <data>          POST, PUT 등에 데이타를 전송합니다.\n"
        + "-X <command>       사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.\n"
        + "-L                 서버의 응딥이 30x 계열이면 다음 응답을 따라 갑니다.\n"
        + "-F <name=content>  multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.");
  }
}
