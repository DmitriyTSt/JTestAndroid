package ru.dmitriyt.jtestandroid;

import android.app.Application;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dmitriyt.jtestandroid.datasource.network.MainApiInterface;

/**
 * Created by dmitriytomilov on 28.03.2018.
 */

public class MyApplication extends Application {
    public static MyApplication sInstance;

    private MainApiInterface mainApiInterface;

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getContext() {
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mainApiInterface = retrofit.create(MainApiInterface.class);
    }

    public String getApiUrl() {
        return "http://dmitriyt.ru/jtestandroid/";
    }

    public MainApiInterface getMainApiInterface() {
        return mainApiInterface;
    }
}
