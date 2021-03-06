package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminStatusSumbangan extends AppCompatActivity {

    RecyclerView recViewStatus;
    AdminStatusAdapter adapter;
    ProgressBar progressBar;
    Button buttonSignOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_list_sumbangan);

        recViewStatus = (RecyclerView) findViewById(R.id.recViewStatus);
        recViewStatus.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressBarStatusBorang);
        progressBar.setVisibility(View.VISIBLE);
        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sumbangan"), UserHelperClass.class)
                        .build();


        adapter= new AdminStatusAdapter(options);
        recViewStatus.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

        buttonSignOut = findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminStatusSumbangan.this,Login.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();


    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

}