package ru.dmitriyt.jtestandroid.datasource.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class Test extends BaseModel {

    private String name;
    private String desc;
    private int testType;

    private String comment; // Текст на ошибочный ответ
    private int group;
    private int timeOut; // Время на тест
    private int timePause; // Время на паузу после теста
    private int maxBall; // Максимальный балл за тест
    private int minBall; // Минимальный балл за тест

    private boolean mixed; // Нужно ли перемешивать
    private boolean order; // С учетом порядка
    private int orderFine; // Штраф за нарушение порядка
    private int cntRight; // Сколько можно выбрать вариантов

    private ArrayList<Answer> answers;

    public Test(int id, String desc) {
        super(id);
        this.desc = desc;
    }

    public Answer getAnswerById(int id) {
        for(Answer ans : answers)
            if (ans.getId() == id) return ans;
        return new Answer();
    }

    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", testType=" + testType +
                ", comment='" + comment + '\'' +
                ", group=" + group +
                ", timeOut=" + timeOut +
                ", timePause=" + timePause +
                ", maxBall=" + maxBall +
                ", minBall=" + minBall +
                ", mixed=" + mixed +
                ", order=" + order +
                ", orderFine=" + orderFine +
                ", cntRight=" + cntRight +
                ", answers=" + answers +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimePause() {
        return timePause;
    }

    public void setTimePause(int timePause) {
        this.timePause = timePause;
    }

    public int getMaxBall() {
        return maxBall;
    }

    public void setMaxBall(int maxBall) {
        this.maxBall = maxBall;
    }

    public int getMinBall() {
        return minBall;
    }

    public void setMinBall(int minBall) {
        this.minBall = minBall;
    }

    public boolean isMixed() {
        return mixed;
    }

    public void setMixed(boolean mixed) {
        this.mixed = mixed;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public int getOrderFine() {
        return orderFine;
    }

    public void setOrderFine(int orderFine) {
        this.orderFine = orderFine;
    }

    public int getCntRight() {
        return cntRight;
    }

    public void setCntRight(int cntRight) {
        this.cntRight = cntRight;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
