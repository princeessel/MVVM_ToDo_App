package com.example.mvvm_todo_app_impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final LayoutInflater mlayoutInflater;
    private List<Task> mTasks;

    public TaskAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= mlayoutInflater.inflate(R.layout.recycler_task_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, int i) {
        if(mTasks!=null){
           viewHolder.taskItemView.setText(mTasks.get(i).getTask());
           
        }else{
            
            viewHolder.taskItemView.setText(R.string.Enter_Future_Tasks);
        }

    }

    void setTasks(List<Task> tasks){
        mTasks=tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTasks!=null)
        return mTasks.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private final TextView taskItemView;
        public ViewHolder( View itemView) {
            super(itemView);
            taskItemView=itemView.findViewById(R.id.textView);
        }
    }
}
