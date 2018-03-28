package ru.dmitriyt.jtestandroid.controllers.task;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.databinding.FragmentTaskListBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;
import ru.dmitriyt.jtestandroid.datasource.model.Test;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TaskListFragment extends Fragment {
    private FragmentTaskListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);

        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Test> tests = new ArrayList<>();
        tests.add(new Test(12332, "Первый вопрос?"));
        tests.add(new Test(12532, "Второй вопрос?"));
        tests.add(new Test(21532, "Третий вопрос?"));
        tasks.add(new Task(true,"Линней, Ламарк","Эволюционное учение","DmT", 11, tests));
        tasks.add(new Task(true,"Теория Дарвина","Эволюционное учение","DmT", 12,  tests));
        tasks.add(new Task(true,"Доказательства эволюции","Эволюционное учение","DmT", 10,  tests));
        tasks.add(new Task(true,"Линней, Ламарк","Эволюционное учение","DmT", 11,  tests));
        tasks.add(new Task(true,"Теория Дарвина","Эволюционное учение","DmT", 12,  tests));
        tasks.add(new Task(true,"Доказательства эволюции","Эволюционное учение","DmT", 10,  tests));
        tasks.add(new Task(true,"Линней, Ламарк","Эволюционное учение","DmT", 11,  tests));
        tasks.add(new Task(true,"Теория Дарвина","Эволюционное учение","DmT", 12,  tests));
        tasks.add(new Task(true,"Доказательства эволюции","Эволюционное учение","DmT", 10,  tests));
        final TaskListAdapter adapter = new TaskListAdapter(tasks);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        binding.taskList.setLayoutManager(manager);
        adapter.setOnItemClickListener(new TaskListAdapter.OnItemClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                Log.d("startTaskActivity", "pos " + position);
                Intent intent = new Intent(getContext(), TaskActivity.class);
                intent.putExtra("task", adapter.getTasks().get(position));
                startActivity(intent);

            }
        });
        binding.taskList.setAdapter(adapter);
        return binding.getRoot();
    }
}
