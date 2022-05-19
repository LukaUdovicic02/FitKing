package com.example.fitking.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fitking.Model.Note;
import com.example.fitking.Model.Workout;
import com.example.fitking.Model.WorkoutAdapter;
import com.example.fitking.R;
import com.example.fitking.ViewModel.WorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WorkoutFragment extends Fragment
{

    public static final int ADD_WORKOUT_REQUEST = 1;
    public static final int EDIT_WORKOUT_REQUEST = 2;
    private WorkoutViewModel workoutViewModel;
    private View mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mainActivity = getActivity().findViewById(R.id.drawer_layout);


        FloatingActionButton floatingActionButton = mainActivity.findViewById(R.id.button_create_workout);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity() , AddEditWorkoutActivity.class);

            startActivityForResult(intent, ADD_WORKOUT_REQUEST);
        });

        RecyclerView recyclerView = mainActivity.findViewById(R.id.recycler_view_workout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);


        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        workoutViewModel.getAllWorkouts().observe(this.getActivity(), new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                adapter.setWorkouts(workouts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                workoutViewModel.removeExercise(adapter.getWorkoutAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "exercise deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                Intent intent = new Intent(getActivity(), AddEditWorkoutActivity.class);
                intent.putExtra(AddEditWorkoutActivity.EXTRA_NAME, workout.getExerciseName());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_TYPE, workout.getExerciseType());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_WRKD_ID, workout.getId());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_NOOFSETS, workout.getExerciseNoOfSets());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_DURATION, workout.getExerciseDuration());
                startActivityForResult(intent, EDIT_WORKOUT_REQUEST);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detete_all_workout:
                workoutViewModel.deleteAllWorkouts();
                Toast.makeText(getActivity(), "All exercises deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_WORKOUT_REQUEST && resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra(AddEditWorkoutActivity.EXTRA_NAME);
            String type = data.getStringExtra(AddEditWorkoutActivity.EXTRA_TYPE);
            String duration = data.getStringExtra(AddEditWorkoutActivity.EXTRA_DURATION);
            int set = data.getIntExtra(AddEditWorkoutActivity.EXTRA_NOOFSETS , 5);

            Workout workout = new Workout(name , type, set , Integer.parseInt(duration));
            workoutViewModel.createExercise(workout);

            Toast.makeText(getActivity(), "workout saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_WORKOUT_REQUEST && resultCode == Activity.RESULT_OK)
        {
            assert data != null;
            int id = data.getIntExtra(AddEditWorkoutActivity.EXTRA_WRKD_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Workout can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditWorkoutActivity.EXTRA_NAME);
            String type = data.getStringExtra(AddEditWorkoutActivity.EXTRA_TYPE);
            String duration = data.getStringExtra(AddEditWorkoutActivity.EXTRA_DURATION);
            int set = data.getIntExtra(AddEditWorkoutActivity.EXTRA_NOOFSETS , 5);

            Workout workout = new Workout(name , type, set , Integer.parseInt(duration));
            workout.setId(id);
            workoutViewModel.editWorkout(workout);

            Toast.makeText(getActivity(), "Workout updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Workout not saved", Toast.LENGTH_SHORT).show();
        }
    }

}
