package com.example.fitking.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitking.Model.Workout;

import java.util.List;

@Dao
public interface WorkoutDao
{
    @Insert
    void createWorkout(Workout workout);

    @Update
    void editWorkout(Workout workout);

    @Delete
    void removeExercise(Workout workout);

    @Query("DELETE FROM workout_table")
    void deleteAllExercises();

    @Query("SELECT * FROM workout_table ORDER by exerciseNoOfSets desc")
    LiveData<List<Workout>> getAllExercises();

}
