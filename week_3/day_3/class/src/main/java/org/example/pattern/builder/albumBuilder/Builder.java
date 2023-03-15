package org.example.pattern.builder.albumBuilder;

import java.util.ArrayList;

public interface Builder {
    void albumTitle(String albumTitle);
    void teamName(String title);
    void makeItems(ArrayList<String> items);
    void close();
    void makeFile();
}
