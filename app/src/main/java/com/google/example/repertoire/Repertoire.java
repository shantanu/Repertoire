package com.google.example.repertoire;

/**
 * Created by Yanjun on 9/5/2015.
 */

import android.content.Context;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.util.*;

public class Repertoire {

    public Repertoire() {
        appContext = null;
        musicPieces = new ArrayList<>();
    }

    public boolean loadData() {
        File saveFile = new File(appContext.getFilesDir(), FILENAME);

        SAXBuilder xmlBuilder = new SAXBuilder();
        Document xmlDoc = null;
        try {
            xmlDoc = (Document) xmlBuilder.build(saveFile);
        } catch (JDOMException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        Element xmlRoot = xmlDoc.getRootElement();
        List<Element> xmlMusicPieceList = xmlRoot.getChildren(MUSIC_PIECE);

        for (Element e : xmlMusicPieceList) {
            String name = e.getChildText(NAME);
            String composer = e.getChildText(COMPOSER);

            MusicPiece temp = new MusicPiece(name, composer);
            musicPieces.add(temp);
        }

        return true;
    }

    public boolean saveData() {
        File saveFile = new File(appContext.getFilesDir(), FILENAME);

        Element xmlRoot = new Element(ROOT);

        for (MusicPiece mp : musicPieces) {
            Element temp = new Element(MUSIC_PIECE);

            Element name = new Element(NAME);
            name.addContent(mp.getName());

            Element composer = new Element(COMPOSER);
            name.addContent(mp.getComposer());

            temp.addContent(name);
            temp.addContent(composer);

            xmlRoot.addContent(temp);
        }

        XMLOutputter xmlWriter = new XMLOutputter();
        try {
            Document tempDoc = new Document(xmlRoot);
            xmlWriter.output(tempDoc, new FileOutputStream((saveFile));
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    public void setContext(Context context) {
        appContext = context;
    }

    public Context getContext() {
        return appContext;
    }

    public ArrayList<MusicPiece> getMusicPieces() {
        return musicPieces;
    }

    private Context appContext;
    private ArrayList<MusicPiece> musicPieces;
    private static final String FILENAME = "savefile.xml";
    private static final String ROOT = "Root";
    private static final String MUSIC_PIECE = "MusicPiece";
    private static final String NAME = "Name";
    private static final String COMPOSER = "Composer";

}
