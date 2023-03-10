package org.example;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TestJson {
    public static void main(String[] args) throws IOException {
        JSONObject object = new JSONObject();

        object.put("name","xtra");
        object.put("주소","IT융합 2211");
        object.put("세부사항",new JSONObject());

        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./info.json")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        bufferedWriter.write(object.toString(4));
        bufferedWriter.flush();
        fileWriter.write(object.toString(4));
        fileWriter.flush();
    }
}
