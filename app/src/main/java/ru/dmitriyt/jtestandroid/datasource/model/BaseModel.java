package ru.dmitriyt.jtestandroid.datasource.model;

import java.io.Serializable;

/**
 * Created by dmitriyt on 23.03.18.
 */

public class BaseModel implements Serializable {
    private int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public BaseModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
