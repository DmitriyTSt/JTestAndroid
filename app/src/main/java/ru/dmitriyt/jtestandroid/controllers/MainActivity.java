package ru.dmitriyt.jtestandroid.controllers;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.controllers.task.TaskListFragment;
import ru.dmitriyt.jtestandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    TaskListFragment taskListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.title_home);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(taskListFragment)
                            .commit();
                    return true;
                case R.id.navigation_choose_test:
                    setTitle(R.string.title_choose_test);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, taskListFragment)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    setTitle(R.string.title_notifications);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(taskListFragment)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        taskListFragment = new TaskListFragment();
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
