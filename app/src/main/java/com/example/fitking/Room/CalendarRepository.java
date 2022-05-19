package com.example.fitking.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fitking.Model.Calendar;
import com.example.fitking.Model.Note;

import java.util.List;

public class CalendarRepository
{
    private CalendarDao calendarDao;
    private LiveData<List<Calendar>> allCalendars;

    public CalendarRepository(Application application)
    {
        CalendarDatabase database = CalendarDatabase.getInstance(application);
        calendarDao = database.calendarDao();
        allCalendars = calendarDao.getAllCalendars();
    }

    public void insert(Calendar cal)
    {
        calendarDao.insert(cal);
    }
    public void update(Calendar cal)
    {
        calendarDao.update(cal);
    }
    public void delete(Calendar cal)
    {
        calendarDao.delete(cal);
    }

    public LiveData<List<Calendar>> getAllCalendars()
    {
        return allCalendars;
    }

}
