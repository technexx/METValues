package tdee.calculator.met.values;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> category;
    private List<String> met;
    private List<String> hours;
    private List<String> calories;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_STAT = 1;

    CustomAdapter(Context context, List<String> category, List<String> met, List<String> hours, List<String> calories) {
        this.context = context; this.category = category; this.met = met; this.hours = hours; this.calories = calories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_header, parent, false);
            return new HeaderHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_adapter, parent, false);
            return new StatHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.categoryHeader.setText(R.string.categoryHead);
            headerHolder.metHeader.setText(R.string.metHead);
            headerHolder.hoursHeader.setText(R.string.hoursHead);
            headerHolder.caloriesHeader.setText(R.string.caloriesHead);
        } else if (holder instanceof StatHolder){
            //Todo: These are receiving an extra input because the first one is eaten above.
            StatHolder statHolder = (StatHolder) holder;
            statHolder.categoryView.setText(category.get(position -1));
            statHolder.metView.setText(met.get(position -1));
            statHolder.hoursView.setText(hours.get(position -1));
            statHolder.caloriesView.setText(calories.get(position -1));
        }
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_STAT;
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView categoryHeader;
        public TextView metHeader;
        public TextView hoursHeader;
        public TextView caloriesHeader;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            categoryHeader = itemView.findViewById(R.id.categories_header);
            metHeader = itemView.findViewById(R.id.met_header);
            hoursHeader = itemView.findViewById(R.id.hours_header);
            caloriesHeader = itemView.findViewById(R.id.calories_header);
        }
    }

    public class StatHolder extends RecyclerView.ViewHolder {
        public TextView categoryView;
        public TextView metView;
        public TextView hoursView;
        public TextView caloriesView;

        public StatHolder(@NonNull View itemView) {
            super(itemView);
            categoryView = itemView.findViewById(R.id.category_view);
            metView = itemView.findViewById(R.id.met_view);
            hoursView = itemView.findViewById(R.id.hours_view);
            caloriesView = itemView.findViewById(R.id.calories_view);
        }
    }
}
