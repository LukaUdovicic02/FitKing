package com.example.fitking.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitking.Model.Workout;

@Database(entities = Workout.class, version = 1)
public abstract class WorkoutDatabase extends RoomDatabase
{
    private static WorkoutDatabase instance;
    public abstract WorkoutDao noteDao();

    public static synchronized WorkoutDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext() , WorkoutDatabase.class , "workout_database").
                    fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
