package com.google.example.repertoire;

/**
 * Created by Yanjun on 9/5/2015.
 */
public class Tracker {
    public Tracker() {
        points = 0;
        dayOfLastUpdate = 0;
    }

    public Tracker(int points, int dayOfLastUpdate) {
        this.points = points;
        this.dayOfLastUpdate = dayOfLastUpdate;
    }

    public int getBreaks() {
        return points / 5;
    }

    public void takeBreak() {
        points -= 5;
    }

    public void practice() {
        points++;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDayOfLastUpdate() {
        return dayOfLastUpdate;
    }

    public void setDayOfLastUpdate(int dayOfLastUpdate) {
        this.dayOfLastUpdate = dayOfLastUpdate;
    }

    private int points;
    private int dayOfLastUpdate;
}
