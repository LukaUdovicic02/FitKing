package com.example.fitking.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitking.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder>
{
    private List<Workout> workouts = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item , parent , false);
        return new WorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutHolder holder, int position)
    {
        Workout currentWorkout = workouts.get(position);
        holder.textViewExerciseDuration.setText(String.valueOf(currentWorkout.getExerciseDuration()));
        holder.textViewExerciseNoOfSets.setText(String.valueOf(currentWorkout.getExerciseNoOfSets()));
        holder.textViewExerciseName.setText(currentWorkout.getExerciseName());
        holder.textViewExerciseType.setText(currentWorkout.getExerciseType());
    }

    @Override
    public int getItemCount()
    {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts)
    {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    public Workout getWorkoutAt(int position)
    {
        return workouts.get(position);
    }


    class WorkoutHolder extends RecyclerView.ViewHolder {
        private TextView textViewExerciseName;
        private TextView textViewExerciseType;
        private TextView textViewExerciseNoOfSets;
        private TextView textViewExerciseDuration;

        public WorkoutHolder(@NonNull View itemView) {
            super(itemView);

            textViewExerciseNoOfSets = itemView.findViewById(R.id.text_view_exerciseNoOfSets);
            textViewExerciseName = itemView.findViewById(R.id.text_view_exerciseName);
            textViewExerciseType = itemView.findViewById(R.id.text_view_exerciseType);
            textViewExerciseDuration = itemView.findViewById(R.id.text_view_exerciseDuration);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(workouts.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {

    void onItemClick(Workout workout);
}

    public void setOnItemClickListener(OnItemClickListener listener) {
       this.listener = listener;
    }



}
