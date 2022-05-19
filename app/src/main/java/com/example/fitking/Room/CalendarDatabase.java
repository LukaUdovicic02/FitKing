package com.example.fitking.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitking.Model.Calendar;

@Database(entities = Calendar.class, version = 1)
public abstract class CalendarDatabase extends RoomDatabase
{
    private static CalendarDatabase instance;

   public abstract CalendarDao calendarDao();

    public static synchronized CalendarDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext() , CalendarDatabase.class , "calendar_database").
                    fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static Callback roomCallBack = new Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void , Void , Void>
    {
        private CalendarDao calendarDao;

        public PopulateDbAsyncTask(CalendarDatabase db)
        {
            this.calendarDao = db.calendarDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
