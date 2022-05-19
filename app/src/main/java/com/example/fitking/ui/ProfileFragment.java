package com.example.fitking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitking.Firebase.LoginActivity;
import com.example.fitking.Model.Calendar;
import com.example.fitking.Model.CalendarAdapter;
import com.example.fitking.Model.Note;
import com.example.fitking.Model.NoteAdapter;
import com.example.fitking.Model.User;
import com.example.fitking.R;
import com.example.fitking.ViewModel.CalendarViewModel;
import com.example.fitking.ViewModel.NoteViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProfileFragment extends Fragment {

    private Button singOutBtn;
    private View mainActivity;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private CalendarViewModel viewModel;
    private TextView dayTextView , monthTextView , yearTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainActivity = getActivity().findViewById(R.id.drawer_layout);
        singOutBtn = mainActivity.findViewById(R.id.singOut);
        singOutBtn.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this.getActivity() , LoginActivity.class));
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        dayTextView = mainActivity.findViewById(R.id.day);
        monthTextView = mainActivity.findViewById(R.id.month);
        yearTextView = mainActivity.findViewById(R.id.year);

        final TextView fullNameTextView = mainActivity.findViewById(R.id.fullName);
        final TextView emailTextView = mainActivity.findViewById(R.id.emailAddress);
        final TextView ageTextView = mainActivity.findViewById(R.id.age);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null)
                {
                    String fullName = userProfile.fullName;
                    String age = userProfile.age;
                    String email = userProfile.email;

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something went wrong !", Toast.LENGTH_SHORT).show();
            }
        });

        Bundle bundle = this.getArguments();
        String getDate = bundle.getString("date");
        dayTextView.setText(getDate);



    }



}

