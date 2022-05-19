package com.example.fitking.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calendar_table")
public class Calendar
{
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int day;
    private int month;
    private int year;

    public Calendar(int day , int month , int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
