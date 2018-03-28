package ru.dmitriyt.jtestandroid.controllers.task;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.dmitriyt.jtestandroid.R;
import ru.dmitriyt.jtestandroid.databinding.ItemTaskListBinding;
import ru.dmitriyt.jtestandroid.datasource.model.Task;

/**
 * Created by dmitriytomilov on 09.03.2018.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    OnItemClickListener onItemClickListener;
    ArrayList<Task> tasks;

    TaskListAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTaskListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_task_list, parent, false);
        return new TaskListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TaskListViewHolder holder, int position) {
        holder.itemBinding.taskName.setText(tasks.get(position).getName());
        holder.itemBinding.taskSize.setText(String.valueOf(tasks.get(position).getMax()));
        holder.itemBinding.taskTheme.setText(tasks.get(position).getTheme());
        holder.itemBinding.taskAuthor.setText(tasks.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemTaskListBinding itemBinding;

        TaskListViewHolder(ItemTaskListBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
            itemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.setOnClickListener(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void setOnClickListener (View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
