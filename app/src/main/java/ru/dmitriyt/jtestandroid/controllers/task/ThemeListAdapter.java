package ru.dmitriyt.jtestandroid.controllers.task;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.databinding.ItemThemeListBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;

/**
 * Created by dmitriyt on 28.03.18.
 */

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.ThemeListViewHolder> {

    ArrayList<String> mThemes;
    HashMap<String, ArrayList<Task>> mTasks;
    Context mContext;

    public ThemeListAdapter(ArrayList<String> mThemes, HashMap<String, ArrayList<Task>> mTasks, Context mContext) {
        this.mThemes = mThemes;
        this.mTasks = mTasks;
        this.mContext = mContext;
    }

    @Override
    public ThemeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemThemeListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_theme_list, parent, false);
        ThemeListViewHolder vh = new ThemeListViewHolder(binding);
        binding.getRoot().setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ThemeListViewHolder holder, int position) {
        final TaskListAdapter adapter = new TaskListAdapter(mTasks.get(mThemes.get(position)));
        adapter.setOnItemClickListener(new TaskListAdapter.OnItemClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                Log.d("startTaskActivity", "pos " + position);
                Intent intent = new Intent(mContext, TaskActivity.class);
                intent.putExtra("task", adapter.getTasks().get(position));
                mContext.startActivity(intent);

            }
        });
        holder.mBinding.themeTaskList.setAdapter(adapter);
        holder.mBinding.themeTaskList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.mBinding.themeName.setText(mThemes.get(position));
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    public class ThemeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemThemeListBinding mBinding;
        boolean isTaskShow;

        public ThemeListViewHolder(ItemThemeListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            isTaskShow = false;
        }

        @Override
        public void onClick(View view) {
            Log.d("THEME_ITEM_CLICK", "pos: " + getAdapterPosition());
            isTaskShow = !isTaskShow;
            if (isTaskShow) {
                mBinding.themeTaskList.setVisibility(View.VISIBLE);
                mBinding.themeNameWrap.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundPrimary));
            } else {
                mBinding.themeTaskList.setVisibility(View.GONE);
                mBinding.themeNameWrap.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundDefault));
            }
        }
    }
}
