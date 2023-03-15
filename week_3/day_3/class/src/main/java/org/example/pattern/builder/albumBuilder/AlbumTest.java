package org.example.pattern.builder.albumBuilder;

public class AlbumTest {

    public static void main(String[] args) {
        Album album = new Album(new HTMLBuilder());
        album.construct();

        Album album2 = new Album(new TextBuilder());
        album2.construct();
    }
}
