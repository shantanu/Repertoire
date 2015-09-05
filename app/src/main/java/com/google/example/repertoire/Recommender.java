package com.google.example.repertoire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class Recommender implements Comparator<ArrayList<MusicPiece>> {

    private Repertoire rep;
    private Schedule schedule;

    public Recommender() {

    }

    public Recommender(Repertoire rep, Schedule schedule) {
        this.rep = rep;
        this.schedule = schedule;
    }

    public void setRepertoire(Repertoire rep) {
        this.rep = rep;
    }

    public Repertoire getRepertoire() {
        return rep;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public MusicPiece recommend() {

        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        int curDay = gc.get(GregorianCalendar.DAY_OF_MONTH);

        MusicPiece retVal = null;

        if (curDay != schedule.getDayOfLastUpdate()) {

            if (compare(rep.getMusicPieces(), schedule.getSchedule()) != 0 || schedule.getScheduleIndex() >= rep.getMusicPieces().size()) {
                schedule.createNewSchedule();
            }

            retVal = schedule.getSchedule().get(schedule.getScheduleIndex());
            schedule.incrementScheduleIndex();

            schedule.setDayOfLastUpdate(curDay);
        } else {
            retVal = schedule.getSchedule().get(schedule.getScheduleIndex()-1);
        }

        return retVal;
    }

    @Override
    public int compare(ArrayList<MusicPiece> a, ArrayList<MusicPiece> b) {
        Collections.sort(a);
        Collections.sort(b);

        if (a.size() != b.size()) {
            return Integer.compare(a.size(), b.size());
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).compareTo(b.get(i)) != 0) {
                return a.get(i).compareTo(b.get(i));
            }
        }

        return 0;
    }
}
