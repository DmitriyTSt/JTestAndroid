package ru.dmitriyt.jtestandroid.controllers.test;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Collections;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.databinding.ActivityTestBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = (Task) getIntent().getSerializableExtra("task");
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
}
