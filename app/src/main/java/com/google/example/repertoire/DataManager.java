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
    private Tracker tracker;

    public DataManager() {
        rep = new Repertoire();
        schedule = new Schedule();
        tracker = new Tracker();
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

        Element xmlRepertoire = xmlRoot.getChild(REPERTOIRE);
        List<Element> xmlMusicPieceList = xmlRepertoire.getChildren(MUSIC_PIECE);

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
        int dayOfLastUpdate = Integer.parseInt(xmlSchedule.getAttributeValue(DAY_OF_LAST_UPDATE));

        schedule.setScheduleIndex(scheduleIndex);

        schedule.setRepertoire(rep);

        schedule.setDayOfLastUpdate(dayOfLastUpdate);

        Element xmlTracker = xmlRoot.getChild(TRACKER);
        int points = Integer.parseInt(xmlTracker.getAttributeValue(POINTS));
        dayOfLastUpdate = Integer.parseInt(xmlTracker.getAttributeValue(DAY_OF_LAST_UPDATE));

        tracker.setDayOfLastUpdate(dayOfLastUpdate);
        tracker.setPoints(points);

        return true;
    }

    public boolean saveData() {
        File saveFile = new File(appContext.getFilesDir(), FILENAME);

        Element xmlRoot = new Element(ROOT);

        Element xmlRepertoire = new Element(REPERTOIRE);

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

            xmlRepertoire.addContent(temp);
        }

        xmlRoot.addContent(xmlRepertoire);

        Element xmlSchedule = new Element(SCHEDULE);
        xmlSchedule.setAttribute(SCHEDULE_INDEX, Integer.toString(schedule.getScheduleIndex()));
        xmlSchedule.setAttribute(DAY_OF_LAST_UPDATE, Integer.toString(schedule.getDayOfLastUpdate()));

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

        Element xmlTracker = new Element(TRACKER);
        xmlTracker.setAttribute(POINTS, Integer.toString(tracker.getPoints()));
        xmlTracker.setAttribute(DAY_OF_LAST_UPDATE, Integer.toString(tracker.getDayOfLastUpdate()));

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

    public Tracker getTracker() {
        return tracker;
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
    private static final String REPERTOIRE = "Repertoire";
    private static final String MUSIC_PIECE = "MusicPiece";
    private static final String NAME = "Name";
    private static final String COMPOSER = "Composer";
    private static final String OPUS = "Opus";
    private static final String NUMBER = "Number";
    private static final String SCHEDULE = "Schedule";
    private static final String SCHEDULE_INDEX = "schedule-index";
    private static final String DAY_OF_LAST_UPDATE = "day-of-last-update";
    private static final String TRACKER = "Tracker";
    private static final String POINTS = "points";

}
