package com.google.example.repertoire;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class MusicPiece implements Comparable<MusicPiece> {

    private String name;
    private String opus;
    private String composer;

    public MusicPiece() {
        name = "";
        opus = "";
        composer = "";
    }

    public MusicPiece(String name, String opus, String composer) {
        this.name = name;
        this.opus = opus;
        this.composer = composer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {

        return composer;
    }

    public String getOpus() {
        return opus;
    }

    public void setOpus(String opus) {
        this.opus = opus;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int compareTo(MusicPiece other) {
        return name.compareTo(other.getName());
    }

    public boolean equals(MusicPiece other) {
        return name.equals(other.getName());
    }


}
