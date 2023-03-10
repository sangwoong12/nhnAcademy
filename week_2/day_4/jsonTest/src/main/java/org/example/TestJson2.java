package org.example;

import org.json.JSONObject;

import java.io.*;

public class TestJson2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedWriter = new BufferedReader(new InputStreamReader(new FileInputStream("./info.json")));
        String line;
        StringBuilder info = new StringBuilder();
        while((line = bufferedWriter.readLine()) != null){
//            System.out.println(line);
            info.append(line);
            info.append("\n");
        }
        System.out.println(info);
        JSONObject jsonObject = new JSONObject(info.toString());
        System.out.println(jsonObject.toString(4));

        Object age = jsonObject.get("나이");
        System.out.println(age.toString());
        System.out.println(age.getClass().toString());

    }
}
