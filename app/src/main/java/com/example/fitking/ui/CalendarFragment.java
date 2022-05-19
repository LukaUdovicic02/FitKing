package com.example.fitking.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitking.R;
import com.example.fitking.ViewModel.CalendarViewModel;

import java.util.Date;

public class CalendarFragment extends Fragment
{
    private CalendarView calendarView;
    private View mainActivity;
    private TextView myDate;
    private CalendarViewModel calendarViewModel;
    private Button saveDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mainActivity = inflater.inflate(R.layout.calendar_fragment, container, false);

        return mainActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = getActivity().findViewById(R.id.drawer_layout);

        calendarView = mainActivity.findViewById(R.id.calendarView);
        myDate = mainActivity.findViewById(R.id.myDate);

        saveDate = mainActivity.findViewById(R.id.saveDate);
        saveDate.setOnClickListener(v -> {
          Bundle bundle = new Bundle();
          String date = myDate.getText().toString().trim();
          bundle.putString("date" , date);
          ProfileFragment profileFragment = new ProfileFragment();
          profileFragment.setArguments(bundle);

          getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , profileFragment).commit();

        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                myDate.setText(date);
            }
        });

        saveDate = mainActivity.findViewById(R.id.saveDate);

    }


}
