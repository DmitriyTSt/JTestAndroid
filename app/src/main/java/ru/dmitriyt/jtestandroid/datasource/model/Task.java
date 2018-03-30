package ru.dmitriyt.jtestandroid.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class Task extends BaseModel {

    private String name;
    private String theme;
    private String author;
    private String desc;

    private int timeOutTotal;
    private int timeOut; // время на задание
    private int timePause;

    private int max; // максимальное число тестов в задании
    private boolean canMix; // можно перемешивать
    private boolean showTestMark; // показывать оценку после теста
    private boolean showTaskMark; // показывать оценку после задания
    private boolean showTrueAnswer; // показывать правильные ответы

    private ArrayList<Test> tests;

    public Task() {
    }

    public Task(boolean showTaskMark, String name, String theme, String author, int max, ArrayList<Test> tests) {
        this.name = name;
        this.theme = theme;
        this.author = author;
        this.max = max;
        this.showTaskMark = showTaskMark;
        this.tests = tests;
    }

    public Task(int id, String name, String theme, String author, String desc, int timeOutTotal,
                int timeOut, int timePause, int max, boolean canMix, boolean showTestMark,
                boolean showTaskMark, boolean showTrueAnswer, ArrayList<Test> tests) {
        super(id);
        this.name = name;
        this.theme = theme;
        this.author = author;
        this.desc = desc;
        this.timeOutTotal = timeOutTotal;
        this.timeOut = timeOut;
        this.timePause = timePause;
        this.max = max;
        this.canMix = canMix;
        this.showTestMark = showTestMark;
        this.showTaskMark = showTaskMark;
        this.showTrueAnswer = showTrueAnswer;
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", theme=" + theme +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", timeOutTotal=" + timeOutTotal +
                ", timeOut=" + timeOut +
                ", timePause=" + timePause +
                ", max=" + max +
                ", canMix=" + canMix +
                ", showTestMark=" + showTestMark +
                ", showTaskMark=" + showTaskMark +
                ", showTrueAnswer=" + showTrueAnswer +
                ", tests=" + tests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (timeOutTotal != task.timeOutTotal) return false;
        if (timeOut != task.timeOut) return false;
        if (timePause != task.timePause) return false;
        if (max != task.max) return false;
        if (canMix != task.canMix) return false;
        if (showTestMark != task.showTestMark) return false;
        if (showTaskMark != task.showTaskMark) return false;
        if (showTrueAnswer != task.showTrueAnswer) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (theme != null ? !theme.equals(task.theme) : task.theme != null) return false;
        if (author != null ? !author.equals(task.author) : task.author != null) return false;
        if (desc != null ? !desc.equals(task.desc) : task.desc != null) return false;
        return tests != null ? tests.equals(task.tests) : task.tests == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + timeOutTotal;
        result = 31 * result + timeOut;
        result = 31 * result + timePause;
        result = 31 * result + max;
        result = 31 * result + (canMix ? 1 : 0);
        result = 31 * result + (showTestMark ? 1 : 0);
        result = 31 * result + (showTaskMark ? 1 : 0);
        result = 31 * result + (showTrueAnswer ? 1 : 0);
        result = 31 * result + (tests != null ? tests.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public String getAuthor() {
        return author;
    }

    public int getMax() {
        return max;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    public boolean isCanMix() {
        return canMix;
    }

    public void shuffleTests() {
        Collections.shuffle(tests);
    }

    public Test getTestById(int id) {
        for(int i = 0; i < tests.size(); i++)
            if (tests.get(i).getId() == id) return tests.get(i);
        return null;
    }

    public int getNextTestId(int id) {
        for(int i = 0; i < tests.size(); i++)
            if (tests.get(i).getId() == id) {
                if (i + 1 < tests.size()) return tests.get(i + 1).getId();
                return -1;
            }
        return -1;
    }

    public boolean isShowTestMark() {
        return showTestMark;
    }

    public void setShowTestMark(boolean showTestMark) {
        this.showTestMark = showTestMark;
    }

    public boolean isShowTaskMark() {
        return showTaskMark;
    }

    public void setShowTaskMark(boolean showTaskMark) {
        this.showTaskMark = showTaskMark;
    }

    public boolean isShowTrueAnswer() {
        return showTrueAnswer;
    }

    public void setShowTrueAnswer(boolean showTrueAnswer) {
        this.showTrueAnswer = showTrueAnswer;
    }
}
