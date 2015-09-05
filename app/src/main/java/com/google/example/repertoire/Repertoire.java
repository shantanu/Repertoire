package com.google.example.repertoire;

/**
 * Created by Yanjun on 9/5/2015.
 */

import android.content.Context;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repertoire {

    public Repertoire() {
        musicPieces = new ArrayList<>();
    }

    public Repertoire(ArrayList<MusicPiece> musicPieces) {
        this.musicPieces = musicPieces;
    }

    public ArrayList<MusicPiece> getMusicPieces() {
        return musicPieces;
    }

    private ArrayList<MusicPiece> musicPieces;

}
