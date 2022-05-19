package com.example.fitking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitking.R;

public class AddEditWorkoutActivity extends AppCompatActivity
{
    public static final String EXTRA_WRKD_ID = "com.example.fitking.EXTRA_WRKD_ID";
    public static final String EXTRA_NAME = "com.example.fitking.EXTRA_NAME";
    public static final String EXTRA_TYPE = "com.example.fitking.EXTRA_TYPE";
    public static final String EXTRA_NOOFSETS = "com.example.fitking.EXTRA_NOOFSETS";
    public static final String EXTRA_DURATION = "com.example.fitking.EXTRA_DURATION";

    private EditText editTextName, editTextType , editTextDuration;
    private NumberPicker numberPickerSets;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addedit_workout);

        editTextName = findViewById(R.id.edit_text_name);
        editTextType = findViewById(R.id.edit_text_type);
        editTextDuration = findViewById(R.id.edit_text_duration);
        numberPickerSets = findViewById(R.id.number_picker_noofsets);

        numberPickerSets.setMinValue(1);
        numberPickerSets.setMaxValue(15);

        Toolbar toolbar = findViewById(R.id.saveToolBarWrk);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_WRKD_ID)) {
            setTitle("Edit Workout");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextType.setText(intent.getStringExtra(EXTRA_TYPE));
            editTextDuration.setText(intent.getStringExtra(EXTRA_DURATION));
            numberPickerSets.setValue(intent.getIntExtra(EXTRA_NOOFSETS, 1));

        } else setTitle("Add Workout");

    }

    private void saveNote() {
        String name = editTextName.getText().toString();
        String type = editTextType.getText().toString();
        String duration = editTextDuration.getText().toString();
        int set = numberPickerSets.getValue();

        if (name.trim().isEmpty() || type.trim().isEmpty() || duration.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title , description and duration", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_TYPE, type);
        data.putExtra(EXTRA_DURATION, duration);
        data.putExtra(EXTRA_NOOFSETS , set);

        int id = getIntent().getIntExtra(EXTRA_WRKD_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_WRKD_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_workout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_workout:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
