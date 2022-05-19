package com.example.fitking.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitking.Model.Workout;
import com.example.fitking.Room.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel
{
    private WorkoutRepository repository;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);

        repository = new WorkoutRepository(application);
        allWorkouts = repository.getAllWorkouts();
    }

    public void createExercise(Workout workout)
    {
        repository.createExercise(workout);
    }

    public void editWorkout(Workout workout)
    {
        repository.editWorkout(workout);
    }

    public void removeExercise(Workout workout)
    {
        repository.removeExercise(workout);
    }

    public void deleteAllWorkouts()
    {
        repository.deleteAllWorkouts();
    }

    public LiveData<List<Workout>> getAllWorkouts()
    {
        return allWorkouts;
    }



}
