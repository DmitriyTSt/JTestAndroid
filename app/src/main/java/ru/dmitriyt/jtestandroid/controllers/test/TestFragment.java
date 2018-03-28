package ru.dmitriyt.jtestandroid.controllers.test;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.controllers.task.TaskActivity;
import ru.dmitriyt.jtestandroid.databinding.FragmentTaskEndBinding;
import ru.dmitriyt.jtestandroid.databinding.FragmentTaskListBinding;
import ru.dmitriyt.jtestandroid.databinding.FragmentTestBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;
import ru.dmitriyt.jtestandroid.datasource.model.Test;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TestFragment extends Fragment {
    private int testId;
    private TestActivity testActivity;

    public static Fragment newInstance(int testId) {
        Bundle args = new Bundle();
        args.putInt("testId", testId);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            testId = getArguments().getInt("testId");
        }
        FragmentTestBinding binding;
        testActivity = (TestActivity) getActivity();
        final Task task = testActivity.getTask();
        if (testId == -1) {
            Intent intent = new Intent();
            intent.putExtra("mark", 12);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();

            FragmentTaskEndBinding binding1;
            binding1 = DataBindingUtil.inflate(inflater, R.layout.fragment_task_end, container, false);
            testActivity.getBinding().btnTestNext.setText("Завершить");
            return binding1.getRoot();
        } else {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
            final Test test = task.getTestById(testId);
            Toast.makeText(getContext(), String.valueOf(test.getId()), Toast.LENGTH_SHORT).show();
            binding.testName.setText(test.getName());

            testActivity.findViewById(R.id.btn_test_next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    testActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_test, TestFragment.newInstance(task.getNextTestId(testId)))
                            .commit();
                }
            });
        }
        return binding.getRoot();
    }
}
