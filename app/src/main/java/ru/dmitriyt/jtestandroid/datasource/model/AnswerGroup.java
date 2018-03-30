package ru.dmitriyt.jtestandroid.datasource.model;

import java.util.ArrayList;

/**
 * Created by dmitriytomilov on 30.03.2018.
 */

// TODO где то были тесты где на одном экране несколько заданий
public class AnswerGroup extends BaseModel {
    private boolean mixed; // Нужно ли перемешивать
    private boolean order; // С учетом порядка
    private int orderFine; // Штраф за нарушение порядка
    private int cntRight; // Сколько можно выбрать вариантов
    private ArrayList<Answer> answers;
}
