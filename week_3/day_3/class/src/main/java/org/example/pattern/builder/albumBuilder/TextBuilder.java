package org.example.pattern.builder.albumBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextBuilder implements Builder{
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
        try (BufferedWriter br = new BufferedWriter(new FileWriter("album.txt"));){
            br.write(albumTitle + "\r\n");
            br.newLine();
            br.write(teamName + "\r\n");
            for (String song : songs) {
                br.write(song+"\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
