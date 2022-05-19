package com.example.fitking.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitking.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder>
{
    private List<Calendar> calendars = new ArrayList<>();
    private OnItemClickListeners listeners;

    class CalendarHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewDay;
        private TextView textViewMonth;
        private TextView textViewYear;

        public CalendarHolder(View itemView)
        {
            super(itemView);
            textViewDay = itemView.findViewById(R.id.day);
            textViewMonth = itemView.findViewById(R.id.month);
            textViewYear = itemView.findViewById(R.id.year);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listeners != null && position != RecyclerView.NO_POSITION)
                {
                    listeners.onItemClick(calendars.get(position));
                }
            });
        }
    }

    public interface OnItemClickListeners {

        void onItemClick(Calendar calendar);
    }

    public void setOnItemClickListeners(OnItemClickListeners listeners) {
        this.listeners = listeners;
    }


    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_fragment , parent , false);
        return new CalendarHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarHolder holder, int position) {
        Calendar currentCal = calendars.get(position);
        holder.textViewDay.setText(String.valueOf(currentCal.getDay()));
        holder.textViewMonth.setText(String.valueOf(currentCal.getMonth()));
        holder.textViewYear.setText(String.valueOf(currentCal.getYear()));

    }

    public void setCalendars(List<Calendar> calendars)
    {
            this.calendars = calendars;
            notifyDataSetChanged();
    }

    public Calendar getCalAt(int position) {
        return calendars.get(position);
    }

    @Override
    public int getItemCount() {
        return calendars.size();
    }
}
