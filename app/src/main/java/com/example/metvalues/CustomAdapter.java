package com.example.metvalues;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> category;
    private List<String> met;
    private List<Integer> minutes;
    private List<String> hours;
    private List<Integer> calories;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_STAT = 1;

    CustomAdapter(List<String> category, List<String> met, List<Integer> minutes, List<String> hours, List<Integer> calories) {
        this.category = category; this.met = met; this.minutes = minutes; this.hours = hours; this.calories = calories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_STAT;
        }
    }

    public class StatHolder extends RecyclerView.ViewHolder {
        public TextView catView;
        public TextView metView;
        public TextView minutesView;
        public TextView hoursView;
        public TextView caloriesView;

        public StatHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
