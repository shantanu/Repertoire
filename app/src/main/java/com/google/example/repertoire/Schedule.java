package com.google.example.repertoire;


import java.util.*;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class Schedule {
    public Schedule() {
        schedule = new ArrayList<MusicPiece>();
        scheduleIndex = 0;

    }

    public Schedule(ArrayList<MusicPiece> schedule, int scheduleIndex, int dayOfLastUpdate) {
        this.schedule = schedule;
        this.scheduleIndex = scheduleIndex;
        this.dayOfLastUpdate = dayOfLastUpdate;
    }

    public void createNewSchedule() {
        ArrayList<MusicPiece> temp = new ArrayList<>(rep.getMusicPieces());
        Collections.shuffle(temp);

        schedule = temp;
        scheduleIndex = 0;
    }

    public ArrayList<MusicPiece> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<MusicPiece> schedule) {
        this.schedule = schedule;
    }

    public int getScheduleIndex() {
        return scheduleIndex;
    }

    public void setScheduleIndex(int index) {
        scheduleIndex = index;
    }

    public void incrementScheduleIndex() {
        scheduleIndex++;
    }

    public void resetScheduleIndex() {
        scheduleIndex = 0;
    }

    public void setRepertoire(Repertoire rep) {
        this.rep = rep;
    }

    public void setDayOfLastUpdate(int day) {
        this.dayOfLastUpdate = day;
    }

    public int getDayOfLastUpdate() {
        return dayOfLastUpdate;
    }

    private Repertoire rep;
    private ArrayList<MusicPiece> schedule;
    private int scheduleIndex;
    private int dayOfLastUpdate;
}
