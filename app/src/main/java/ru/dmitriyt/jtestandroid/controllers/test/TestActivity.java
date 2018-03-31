package ru.dmitriyt.jtestandroid.controllers.test;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.databinding.ActivityTestBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;
    private Task task;
    private int balls;
    private int maxBalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = (Task) getIntent().getSerializableExtra("task");
        balls = 0;
        maxBalls = 0;
        if (task.isCanMix() && task.getTests() != null) task.shuffleTests();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_test, TestFragment.newInstance(task.getTests().get(0).getId()))
                .commit();
    }
    // чтобы из фрагмента доставать кнопку
    public ActivityTestBinding getBinding() {
        return binding;
    }

    // чтобы из фрагмента доставать задание
    public Task getTask() {
        return task;
    }

    public int getBalls() {
        return balls;
    }

    public int getMaxBalls() {
        return maxBalls;
    }

    public void addMaxBalls(int maxBalls) {
        this.maxBalls += maxBalls;
    }

    public void addBalls(int balls) {
        this.balls += balls;
    }
}
