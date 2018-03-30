package ru.dmitriyt.jtestandroid.datasource.model;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class Answer extends BaseModel {
    private String text;
    private boolean correct;

    public Answer() {
    }

    public Answer(int id, String text, boolean correct) {
        super(id);
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }
}
