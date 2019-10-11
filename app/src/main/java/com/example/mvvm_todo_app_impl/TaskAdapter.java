package com.example.mvvm_todo_app_impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final LayoutInflater mlayoutInflater;
    private List<Task> mTasks = new ArrayList<>();
    Context context;
    private OnItemClickListener listener;

    public TaskAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mlayoutInflater.inflate(R.layout.recycler_task_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, final int position) {
        final Task task = mTasks.get(position);
        if (mTasks != null) {
            viewHolder.taskItemView.setText(mTasks.get(position).getTask());

        } else {

            viewHolder.taskItemView.setText(R.string.Enter_Future_Tasks);
        }

        viewHolder.checkBox.setChecked(mTasks.get(position).isSelected());
        viewHolder.checkBox.setTag(mTasks.get(position));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox mCheckBox=(CheckBox)v;
                Task task = (Task) mCheckBox.getTag();
                task.setSelected(mCheckBox.isChecked());
                mTasks.get(position).setSelected(mCheckBox.isChecked());

                Toast.makeText(v.getContext(), "Task Done: " + mCheckBox.getText() + " is "
                        + mCheckBox.isChecked(), Toast.LENGTH_LONG).show();

            }
        });

    }

    void setTasks(List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    public Task getTaskAt(int position){
        return mTasks.get(position);
    }

    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskItemView;
        public CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            taskItemView = itemView.findViewById(R.id.textView);
            checkBox = itemView.findViewById(R.id.completed_task);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mTasks.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
