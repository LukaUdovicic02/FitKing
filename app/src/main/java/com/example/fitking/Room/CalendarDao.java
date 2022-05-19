package com.example.fitking.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitking.Model.Calendar;

import java.util.List;
@Dao
public interface CalendarDao
{
    @Insert
    void insert(Calendar calendar);

    @Update
    void update(Calendar calendar);

    @Delete
    void delete(Calendar calendar);

    @Query("SELECT * FROM calendar_table")
    LiveData<List<Calendar>> getAllCalendars();


}
