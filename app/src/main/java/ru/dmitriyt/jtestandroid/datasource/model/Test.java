package ru.dmitriyt.jtestandroid.datasource.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class Test extends BaseModel {
    enum TestType {
        OneRight, SomeRight, OrderRight, TextRight
    }

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
}
