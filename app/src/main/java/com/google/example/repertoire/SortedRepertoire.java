package com.google.example.repertoire;

import java.util.Comparator;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class SortedRepertoire extends Repertoire {
    public SortedRepertoire() {
        super();

        sortOption = SortOption.NONE;
    }

    public enum SortOption {
        NONE,
        NAME,
        NAME_REVERSED,
        COMPOSER,
        COMPOSER_REVERSED
    }

    public static final Comparator<MusicPiece> NAME = new Comparator<MusicPiece>() {
        public int compare(MusicPiece a, MusicPiece b) {
            return a.getName().compareTo(b.getName());
        }
    };

    public static final Comparator<MusicPiece> NAME_REVERSED = new Comparator<MusicPiece>() {
        public int compare(MusicPiece a, MusicPiece b) {
            return -1 * a.getName().compareTo(b.getName());
        }
    };

    public static final Comparator<MusicPiece> COMPOSER = new Comparator<MusicPiece>() {
        public int compare(MusicPiece a, MusicPiece b) {
            return a.getComposer().compareTo(b.getComposer());
        }
    };

    public static final Comparator<MusicPiece> COMPOSER_REVERSED = new Comparator<MusicPiece>() {
        public int compare(MusicPiece a, MusicPiece b) {
            return -1 * a.getComposer().compareTo(b.getComposer());
        }
    };

    private SortOption sortOption;
}
