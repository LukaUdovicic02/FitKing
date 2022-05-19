package com.example.fitking.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_table")
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exerciseName;
    private String exerciseType;
    private int exerciseNoOfSets;
    private int exerciseDuration;

    public Workout(String exerciseName, String exerciseType, int exerciseNoOfSets, int exerciseDuration) {
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.exerciseNoOfSets = exerciseNoOfSets;
        this.exerciseDuration = exerciseDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getExerciseNoOfSets() {
        return exerciseNoOfSets;
    }

    public void setExerciseNoOfSets(int exerciseNoOfSets) {
        this.exerciseNoOfSets = exerciseNoOfSets;
    }

    public int getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(int exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }
}
