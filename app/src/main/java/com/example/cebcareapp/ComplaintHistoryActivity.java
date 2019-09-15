package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import com.example.cebcareapp.Adapter.ComplaintAdapter;
import com.example.cebcareapp.Database.ComplaintDB;

public class ComplaintHistoryActivity extends AppCompatActivity {

    private ListView listViewComplaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_history);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Complaints");

        final ComplaintDB complaintDB = new ComplaintDB(this);
        this.listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
        this.listViewComplaints.setAdapter(new ComplaintAdapter(this, complaintDB.findAll()));
    }
}
