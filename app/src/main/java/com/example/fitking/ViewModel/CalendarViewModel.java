package com.example.fitking.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitking.Model.Calendar;

import com.example.fitking.Room.CalendarRepository;

import java.util.List;

public class CalendarViewModel extends AndroidViewModel
{

    private CalendarRepository repository;
    private LiveData<List<Calendar>> allCalendars;

    public CalendarViewModel(@NonNull Application application) {
        super(application);

        repository = new CalendarRepository(application);
        allCalendars = repository.getAllCalendars();
    }

    public void insert(Calendar calendar) {
        repository.insert(calendar);
    }

    public void update(Calendar calendar) {
        repository.update(calendar);
    }

    public void delete(Calendar calendar) {
        repository.delete(calendar);
    }

    public LiveData<List<Calendar>> getAllCalendars()
    {
        return allCalendars;
    }


}
