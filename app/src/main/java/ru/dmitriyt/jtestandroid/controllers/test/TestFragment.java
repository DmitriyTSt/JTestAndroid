package ru.dmitriyt.jtestandroid.controllers.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.controllers.task.TaskActivity;
import ru.dmitriyt.jtestandroid.databinding.FragmentTaskEndBinding;
import ru.dmitriyt.jtestandroid.databinding.FragmentTaskListBinding;
import ru.dmitriyt.jtestandroid.databinding.FragmentTestBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Answer;
import ru.dmitriyt.jtestandroid.datasource.model.Mark;
import ru.dmitriyt.jtestandroid.datasource.model.Task;
import ru.dmitriyt.jtestandroid.datasource.model.Test;
import ru.dmitriyt.jtestandroid.datasource.model.TestType;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TestFragment extends Fragment {
    private int testId;
    private TestActivity testActivity;
    private FragmentTestBinding binding;
    private Task task;
    private Test test;

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
        testActivity = (TestActivity) getActivity();
        task = testActivity.getTask();
        if (testId == -1) {
            Intent intent = new Intent();
            intent.putExtra("balls", testActivity.getBalls());
            intent.putExtra("maxBalls", testActivity.getMaxBalls());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();

            FragmentTaskEndBinding binding1;
            binding1 = DataBindingUtil.inflate(inflater, R.layout.fragment_task_end, container, false);
            testActivity.getBinding().btnTestNext.setText("Завершить");
            return binding1.getRoot();
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
            test = task.getTestById(testId);
            Toast.makeText(getContext(), String.valueOf(test.getId()), Toast.LENGTH_SHORT).show();
            binding.testName.setText(test.getDesc());

            // нарисовали название, идем к ответам
            if (test.isMixed()) {
                test.shuffleAnswers();
            }

            setAnswersView(test.getTestType());

            testActivity.findViewById(R.id.btn_test_next).setOnClickListener(view -> {
                testActivity.addBalls(currentBall);
                testActivity.addMaxBalls(test.getMaxBall());
                if (task.isShowTestMark()) {
                    showTestMark();
                } else {
                    goToNext();
                }
            });
        }
        return binding.getRoot();
    }

    private int currentBall;
    private RadioGroup rg;

    private void setAnswersView(int testType) {
        switch (testType) {
            case TestType.OneRight :
                currentBall = test.getMinBall();
                rg = new RadioGroup(getContext());
                for (Answer ans : test.getAnswers()) {
                    RadioButton rb = new RadioButton(getContext());
                    rb.setText(ans.getText());
                    rb.setId(ans.getId());
                    rg.addView(rb);
                }
                rg.setOnCheckedChangeListener((radioGroup, id) -> {
                    Log.d("RB_CLICK", "position = " + id);
                    if (test.getAnswerById(id).isCorrect()) {
                        currentBall = test.getMaxBall();
                    } else {
                        currentBall = test.getMinBall();
                    }
                });
                binding.testAnswersWrap.addView(rg);
                break;
            case TestType.SomeRight:
                currentBall = 0;
                rg = new RadioGroup(getContext());
                for (Answer ans : test.getAnswers()) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(ans.getText());
                    cb.setId(ans.getId());
                    rg.addView(cb);
                }
                rg.setOnCheckedChangeListener(((radioGroup, id) -> {
                    Log.d("CB_CLICK", "position = " + id);
                    if (test.getAnswerById(id).isCorrect()) {
                        currentBall++;
                    } else {
                        currentBall--;
                    }
                }));
                binding.testAnswersWrap.addView(rg);
                break;
            default:
                new AlertDialog.Builder(getContext())
                        .setMessage("Данный тип вопросов не поддерживается в приложении")
                        .setCancelable(false)
                        .setPositiveButton("Пропустить", (dialog, id) -> {
                            dialog.cancel();
                            goToNext();
                        }).create().show();
        }
    }

    private void showTestMark() {
        new AlertDialog.Builder(getContext())
                .setMessage("Оценка за текущий вопрос: " + new Mark(currentBall, test.getMaxBall()).getMark())
                .setCancelable(false)
                .setPositiveButton("Дальше", (dialog, id) -> {
                    dialog.cancel();
                    goToNext();
                }).create().show();
    }

    private void goToNext() {
        testActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_test, TestFragment.newInstance(task.getNextTestId(testId)))
                .commit();
    }
}
