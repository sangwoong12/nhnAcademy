package org.example.pattern.builder;

import org.example.pattern.builder.Guitarist.Builder;

public class Guitarist {
    private final int guitaristNo;
    private final String name;
    private final String guitarType;
    private final String guitarMaker;
    private final String playingType;
    private final String genre;
    private final String teamName;
    private Guitarist(Builder builder){
        this.genre = builder.genre;
        this.guitaristNo = builder.guitaristNo;
        this.name = builder.name;
        this.teamName = builder.teamName;
        this.playingType = builder.playingType;
        this.guitarType = builder.guitarType;
        this.guitarMaker = builder.guitarMaker;
    }

    @Override
    public String toString() {
        return "Guitarist{" +
            "guitaristNo=" + guitaristNo +
            ", name='" + name + '\'' +
            ", guitarType='" + guitarType + '\'' +
            ", guitarMaker='" + guitarMaker + '\'' +
            ", playingType='" + playingType + '\'' +
            ", genre='" + genre + '\'' +
            ", teamName='" + teamName + '\'' +
            '}';
    }

    public static class Builder {
        private int guitaristNo;
        private String name;
        private String guitarType;
        private String guitarMaker;
        private String playingType;
        private String genre;
        private String teamName;

        public Builder(int guitaristNo, String name) {
            this.guitaristNo = guitaristNo;
            this.name = name;
        }

        public Builder guitarType(String guitarType) {
            this.guitarType = guitarType;
            return this;
        }

        public Builder guitarMaker(String guitarMaker) {
            this.guitarMaker = guitarMaker;
            return this;
        }

        public Builder playingType(String playingType) {
            this.playingType = playingType;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }
        public Guitarist build(){
            return new Guitarist(this);
        }
    }
}
class GuitarMain{

    public static void main(String[] args) {
        Guitarist build = new Builder(1, "SangWoong")
            .genre("genre")
            .guitarType("guitarType")
            .teamName("teamName")
            .playingType("playingType")
            .guitarMaker("guitarMaker")
            .build();
        System.out.println(build.toString());
    }
}
