package ru.dmitriyt.jtestandroid.datasource.network;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.dmitriyt.jtestandroid.datasource.model.Task;

/**
 * Created by dmitriytomilov on 28.03.2018.
 */

public interface MainApiInterface {

    @GET("task1/task.php")
    Call<Task> getTestTask();
}
