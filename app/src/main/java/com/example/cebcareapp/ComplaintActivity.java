package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cebcareapp.Database.ComplaintDB;
import com.example.cebcareapp.Entity.Complaint;
import com.google.android.material.textfield.TextInputEditText;


public class ComplaintActivity extends AppCompatActivity {


    private TextInputEditText complaint, email, phone;
    private Button buttonComplaint;
    private TextView complaintHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        complaint = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        phone = findViewById(R.id.amountEditText);
        complaintHistory = findViewById(R.id.complaintHistoryBtn);
        buttonComplaint = findViewById(R.id.proceedToPaymentBtn);
        this.startListener();

        complaint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (complaint.getRight() - complaint.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Your ComplaintDB goes here.",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (email.getRight() - email.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Your email goes here - sample@gmail.com",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (phone.getRight() - phone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Phone Number goes here",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });
    }


    public void startListener() {
        this.buttonComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(view);
            }
        });
        this.complaintHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintActivity.this, ComplaintHistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    public void insertData(View view) {
        ComplaintDB complaintDB = new ComplaintDB(getBaseContext());
        Complaint complaint = new Complaint();
        complaint.setAccount("A001");
        complaint.setComplaintType("Natural Disaster");
        complaint.setComplaint(this.complaint.getText().toString());
        complaint.setEmail("sathira@gmail.com");
complaint.setPhone(00);

        if (complaintDB.create(complaint)) {
            Toast.makeText(getBaseContext(), "Complaint Sent Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
