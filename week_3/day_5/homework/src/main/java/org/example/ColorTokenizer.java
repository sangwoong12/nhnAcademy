package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.color.BlueToken;
import org.example.color.Token;
import org.example.color.NormalToken;
import org.example.color.OrangeToken;
import org.example.color.YellowToken;
import org.example.item.BlueType;
import org.example.item.OrangeType;
import org.example.item.YellowType;
import org.example.visitor.HtmlVisit;
import org.example.visitor.Visitor;

public class ColorTokenizer {
	static String r = "\r\n";
	static List<String> tokens = new ArrayList<>();
	static StringBuilder htmlStart = new StringBuilder();
	static StringBuilder htmlBody = new StringBuilder();
	static StringBuilder htmlEnd = new StringBuilder();

//	static File file = new File("src/main/java/org/example/Test.java");
	static String fileName;

	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		fileName = file.getName();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = br.readLine()) != null) {
				tokens.add(str);
			}
		}
		makeBody();
		print();
	}
	public static void print() {
		htmlStart.append("<!DOCTYPE html>").append(r)
			.append("<html lang=\"en-US\">").append(r)
			.append("<head>").append(r)
			.append("<title>").append(fileName).append("</title>").append(r)
			.append("</head>").append(r);
		htmlEnd.append("</html>").append(r);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.replace(".java",".") + "html"))) {
			bw.write(htmlStart.toString());
			bw.write(htmlBody.toString());
			bw.write(htmlEnd.toString());
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	static void makeBody() {
		Visitor visitor = new HtmlVisit();
		Token color;
		htmlBody.append("<body style=\"background-color:#2b2b2b\">").append(r);
		htmlBody.append("<span class=\"tokens\" style=\"color:white\">").append(r);
		for (String token : tokens) {
			for (BlueType blue : BlueType.values()) {
				token = token.replace(blue.str, " " + blue.str + " ");
			}
			token = token.replace("\t", "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ");
			String[] strs = token.split(" ");

			for (String str : strs) {
				str = str.trim();

				if(BlueType.containToken(str)) {
					color = new BlueToken(str);
				} else if(OrangeType.containToken(str)) {
					color = new OrangeToken(str);
				} else if(YellowType.containToken(str)) {
					color = new YellowToken(str);
				} else {
					color = new NormalToken(str);
				}

				String accept = color.accept(visitor);
				htmlBody.append(accept).append("&nbsp&nbsp");
			}
			htmlBody.append("<br>").append("\r\n");
		}
		htmlBody.append("</span>").append(r);
	}
}

