package com.example.fitking.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fitking.Model.Note;
import com.example.fitking.Model.Workout;

import java.util.List;

public class WorkoutRepository {
    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application) {
        WorkoutDatabase database = WorkoutDatabase.getInstance(application);
        workoutDao = database.noteDao();
        allWorkouts = workoutDao.getAllExercises();
    }

    public void createExercise(Workout workout) {
        new InsertWorkoutAsyncTask(workoutDao).execute(workout);

    }

    public void editWorkout(Workout workout) {
        new UpdateWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public void removeExercise(Workout workout)
    {
        new DeleteWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public void deleteAllWorkouts() {
        new DeleteAllWorkoutsAsyncTask(workoutDao).execute();
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    private static class InsertWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {

        private WorkoutDao workoutDao;

        public InsertWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.createWorkout(workouts[0]);
            return null;
        }
    }

    private static class UpdateWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {

        private WorkoutDao workoutDao;

        public UpdateWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.editWorkout(workouts[0]);
            return null;
        }
    }

    private static class DeleteWorkoutAsyncTask extends AsyncTask<Workout , Void , Void>
    {
        private WorkoutDao workoutDao;

        public DeleteWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.removeExercise(workouts[0]);
            return null;
        }
    }

    private static class DeleteAllWorkoutsAsyncTask extends AsyncTask<Void , Void , Void>
    {

        private WorkoutDao workoutDao;

        public DeleteAllWorkoutsAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            workoutDao.deleteAllExercises();
            return null;
        }
    }


}
