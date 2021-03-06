package ru.dmitriyt.jtestandroid.controllers.task;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.controllers.test.TestActivity;
import ru.dmitriyt.jtestandroid.databinding.ActivityTaskBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Mark;
import ru.dmitriyt.jtestandroid.datasource.model.Task;
import ru.dmitriyt.jtestandroid.datasource.model.Test;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TaskActivity extends AppCompatActivity {
    ActivityTaskBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task);
        final Task task = (Task) getIntent().getSerializableExtra("task");
        binding.taskName.setText(task.getName());
        binding.taskTheme.setText(task.getTheme());
        binding.taskAuthor.setText(task.getAuthor());
        binding.taskSize.setText(String.valueOf(task.getTests().size()));
        binding.btnStartTask.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            intent.putExtra("task", task);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            if (resultCode == RESULT_OK) {
                binding.taskComplited.setVisibility(View.VISIBLE);
                binding.taskMarkLayout.setVisibility(View.VISIBLE);
                binding.taskBallsLayout.setVisibility(View.VISIBLE);
                binding.taskBalls.setText(
                        String.valueOf(data.getIntExtra("balls", 0))
                        + "/" +
                        String.valueOf(data.getIntExtra("maxBalls", 0)));
                //String.format("%d/%d", data.getIntExtra("balls", 0), String.valueOf(data.getIntExtra("maxBalls", 0)));
                binding.taskMark.setText(String.valueOf(
                        new Mark(data.getIntExtra("balls", 0),
                                 data.getIntExtra("maxBalls", 0)).getMark()));
            }
        }

    }
}
