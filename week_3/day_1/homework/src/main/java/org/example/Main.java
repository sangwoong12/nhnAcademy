package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * read word.
 */
public class Main {
    /**
     * 파일을 읽어서 key : 단어 , value : 뜻 을 map에 저장하여 호출한다.
     *
     * @param args : null
     */
    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String key = null;
                String value = null;
                Pattern wordPattern = Pattern.compile("[ ](.*)[a-zA-Z]\\s");
                Matcher word = wordPattern.matcher(line);
                while (word.find()) {
                    key = word.group().trim();
                }
                Pattern asPattern = Pattern.compile("[.](.*)");
                Matcher as = asPattern.matcher(line.replace(key, ""));
                while (as.find()) {
                    value = as.group().replace(".", "").trim();
                }
                maps.put(key, value);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("파일이 존재하지 않습니다.");
        } catch (IOException e) {
            throw new RuntimeException("readLine 오류");
        }
        List<String> a = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean system = true;
//        Set<String> keySet = maps.keySet();
//        for (String key : keySet) {
//            System.out.println(key + " : " + maps.get(key));
//        }
        while (system) {
            System.out.println("검색한 단어를 입력하세요");
            String input = scanner.nextLine();
            if (input.equals("exit()")) {
                system = false;
            } else if (maps.get(input).isEmpty()) {
                System.out.println("없는 단어 입니다.");
            } else {
                System.out.println(maps.get(input));
            }
        }

    }
}