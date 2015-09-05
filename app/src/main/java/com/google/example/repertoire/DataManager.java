package com.google.example.repertoire;

import android.content.Context;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class DataManager {
    private Repertoire rep;
    private Schedule schedule;

    public DataManager() {
        rep = new Repertoire();
        schedule = new Schedule();
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
            int opus = Integer.parseInt(e.getChildText(OPUS));
            int number = Integer.parseInt(e.getChildText(NUMBER));

            MusicPiece temp = new MusicPiece(name, opus, number, composer);
            rep.getMusicPieces().add(temp);
        }

        Element xmlSchedule = xmlRoot.getChild(SCHEDULE);
        List<Element> xmlScheduleList = xmlSchedule.getChildren(MUSIC_PIECE);

        for (Element e : xmlScheduleList) {
            String name = e.getChildText(NAME);
            String composer = e.getChildText(COMPOSER);
            int opus = Integer.parseInt(e.getChildText(OPUS));
            int number = Integer.parseInt(e.getChildText(NUMBER));

            MusicPiece temp = new MusicPiece(name, opus, number, composer);
            schedule.getSchedule().add(temp);
        }

        int scheduleIndex = Integer.parseInt(xmlSchedule.getAttributeValue(SCHEDULE_INDEX));

        schedule.setScheduleIndex(scheduleIndex);

        return true;
    }

    public boolean saveData() {
        File saveFile = new File(appContext.getFilesDir(), FILENAME);

        Element xmlRoot = new Element(ROOT);

        for (MusicPiece mp : rep.getMusicPieces()) {
            Element temp = new Element(MUSIC_PIECE);

            Element name = new Element(NAME);
            name.addContent(mp.getName());

            Element composer = new Element(COMPOSER);
            composer.addContent(mp.getComposer());

            Element opus = new Element(OPUS);
            opus.addContent(Integer.toString(mp.getOpus()));

            Element number = new Element(NUMBER);
            number.addContent(Integer.toString(mp.getNumber()));

            temp.addContent(name);
            temp.addContent(opus);
            temp.addContent(number);
            temp.addContent(composer);

            xmlRoot.addContent(temp);
        }

        Element xmlSchedule = new Element(SCHEDULE);
        xmlSchedule.setAttribute(SCHEDULE_INDEX, Integer.toString(schedule.getScheduleIndex()));

        for (MusicPiece mp : schedule.getSchedule()) {
            Element temp = new Element(MUSIC_PIECE);

            Element name = new Element(NAME);
            name.addContent(mp.getName());

            Element composer = new Element(COMPOSER);
            composer.addContent(mp.getComposer());

            Element opus = new Element(OPUS);
            opus.addContent(Integer.toString(mp.getOpus()));

            Element number = new Element(NUMBER);
            number.addContent(Integer.toString(mp.getNumber()));

            temp.addContent(name);
            temp.addContent(opus);
            temp.addContent(number);
            temp.addContent(composer);

            xmlSchedule.addContent(temp);
        }

        xmlRoot.addContent(xmlSchedule);

        XMLOutputter xmlWriter = new XMLOutputter();
        try {
            Document tempDoc = new Document(xmlRoot);
            xmlWriter.output(tempDoc, new FileOutputStream((saveFile)));

        } catch (IOException e) {
            return false;
        }


        return true;
    }

    public Repertoire getRepertoire() {
        return rep;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setContext(Context context) {
        appContext = context;
    }

    public Context getContext() {
        return appContext;
    }

    private Context appContext;

    private static final String FILENAME = "savefile.xml";
    private static final String ROOT = "Root";
    private static final String MUSIC_PIECE = "MusicPiece";
    private static final String NAME = "Name";
    private static final String COMPOSER = "Composer";
    private static final String OPUS = "Opus";
    private static final String NUMBER = "Number";
    private static final String SCHEDULE = "Schedule";
    private static final String SCHEDULE_INDEX = "schedule-index";


}
