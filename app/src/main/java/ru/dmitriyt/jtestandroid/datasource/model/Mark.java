package ru.dmitriyt.jtestandroid.datasource.model;

/**
 * Created by dmitriytomilov on 30.03.2018.
 */

public class Mark {
    private static  int gA = 90;
    private static int gB = 75;
    private static int gC = 50;

    private int mark;

    public Mark(int current, int max) {
        int percent = current * 100 / max;
        if (percent >= gA) mark = 5;
        else if (percent >= gB) mark = 4;
        else if (percent >= gC) mark = 3;
        else mark = 2;
    }

    public int getMark() {
        return mark;
    }
}
