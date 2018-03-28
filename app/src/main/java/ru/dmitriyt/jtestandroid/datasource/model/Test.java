package ru.dmitriyt.jtestandroid.datasource.model;

import java.io.Serializable;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class Test extends BaseModel {
    private enum TestType {
        OneRight, SomeRight, OrderRight, TextRight
    }

    private String name;
    private String desc;
    private TestType testType;

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

    public Test(int id, String name) {
        super(id);
        this.name = name;
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (group != test.group) return false;
        if (timeOut != test.timeOut) return false;
        if (timePause != test.timePause) return false;
        if (maxBall != test.maxBall) return false;
        if (minBall != test.minBall) return false;
        if (mixed != test.mixed) return false;
        if (order != test.order) return false;
        if (orderFine != test.orderFine) return false;
        if (cntRight != test.cntRight) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        if (desc != null ? !desc.equals(test.desc) : test.desc != null) return false;
        if (testType != test.testType) return false;
        return comment != null ? comment.equals(test.comment) : test.comment == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (testType != null ? testType.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + group;
        result = 31 * result + timeOut;
        result = 31 * result + timePause;
        result = 31 * result + maxBall;
        result = 31 * result + minBall;
        result = 31 * result + (mixed ? 1 : 0);
        result = 31 * result + (order ? 1 : 0);
        result = 31 * result + orderFine;
        result = 31 * result + cntRight;
        return result;
    }

    public String getName() {
        return name;
    }
}
