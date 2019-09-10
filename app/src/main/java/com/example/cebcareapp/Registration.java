package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = findViewById(R.id.registrationToolbar);
        toolbar.setTitle("Registration");
        setSupportActionBar(toolbar);
    }
}
