package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final List<TodoItem> items;

    public TodoAdapter(List<TodoItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoItem item = items.get(position);
        holder.tvTitle.setText(item.getName());
        holder.tvPoints.setText("Points: " + item.getPoint());
        if (item.getDeadline() != null) {
            holder.tvDeadline.setText("Deadline: " + item.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else {
            holder.tvDeadline.setText("No deadline");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPoints, tvDeadline;
        TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPoints = itemView.findViewById(R.id.tv_points);
            tvDeadline = itemView.findViewById(R.id.tv_deadline);
        }
    }
}

