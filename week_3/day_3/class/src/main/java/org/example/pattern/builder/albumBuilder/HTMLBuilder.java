package org.example.pattern.builder.albumBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLBuilder implements Builder{
    private String albumTitle;
    private String teamName;
    private List<String> songs;
    @Override
    public void albumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    @Override
    public void teamName(String title) {
        this.teamName = title;
    }

    @Override
    public void makeItems(ArrayList<String> items) {
        this.songs = items;
    }

    @Override
    public void close() {
        makeFile();
    }

    @Override
    public void makeFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("album.html"));){
            br.write("<!DOCTYPE html>\r\n");
            br.write("<html>\r\n");
            br.write("\t<head>\r\n\t\t<title>" + albumTitle + "</title>\r\n</head>\r\n");
            br.write("\t<body>\r\n");
            br.write("\t\t<h1>" + teamName + "</h1>\r\n");
            for (String song : songs) {
                br.write("\t\t<li>"+song+"</li>\r\n");
            }
            br.write("\t</body>\r\n");
            br.write("</html>\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
