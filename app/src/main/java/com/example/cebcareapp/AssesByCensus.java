package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AssesByCensus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_by_census);

        Toolbar toolbar = findViewById(R.id.accessByCensusToolbar);
        toolbar.setTitle("Access By Census");
        setSupportActionBar(toolbar);
    }
}
