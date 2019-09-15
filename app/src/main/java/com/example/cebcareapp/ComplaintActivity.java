package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cebcareapp.Database.ComplaintDB;
import com.example.cebcareapp.Entity.Complaint;
import com.google.android.material.textfield.TextInputEditText;


public class ComplaintActivity extends AppCompatActivity {


    private TextInputEditText complaint, email, phone;
    private Button buttonComplaint;
    private TextView complaintHistory;
    private Spinner accountNodropdown;
    private Spinner complaintTypedrpdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Complaints");

        complaint = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        phone = findViewById(R.id.amountEditText);
        complaintHistory = findViewById(R.id.complaintHistoryBtn);
        buttonComplaint = findViewById(R.id.proceedToPaymentBtn);
        buttonComplaint = findViewById(R.id.proceedToPaymentBtn);
        complaintTypedrpdown = findViewById(R.id.complaintTypedrpdown);
        accountNodropdown = findViewById(R.id.accountNodropdown);

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
                                "Your Complaint goes here.",
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
                if(validate() != -1) {
                    insertData();
                }
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

    public void insertData() {
        ComplaintDB complaintDB = new ComplaintDB(getBaseContext());
        Complaint complaint = new Complaint();
        complaint.setAccount(accountNodropdown.getSelectedItem().toString());
        complaint.setComplaintType(complaintTypedrpdown.getSelectedItem().toString());
        complaint.setComplaint(this.complaint.getText().toString());
        complaint.setEmail("sathira@gmail.com");
        complaint.setPhone(00);

        if (complaintDB.create(complaint)) {
            Toast.makeText(getBaseContext(), "Complaint Sent Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ComplaintActivity.this, ComplaintHistoryActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public int validate(){

        if(accountNodropdown.getSelectedItem().toString().equals("Select Account Number")){
            Toast.makeText(getBaseContext(), "Select Account Number!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(complaintTypedrpdown.getSelectedItem().toString().equals("Select Complaint Type")){
            Toast.makeText(getBaseContext(), "Select Complaint Type!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(complaint.getText().toString().equals("")|| complaint.getText().toString() == null) {
            Toast.makeText(getBaseContext(), "Complaint field cannot be empty!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(email.getText().toString().equals("")|| email.getText().toString() == null) {
            Toast.makeText(getBaseContext(), "Email field cannot be empty!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(phone.getText().toString().equals("")|| phone.getText().toString() == null) {
            Toast.makeText(getBaseContext(), "Phone field cannot be empty!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        return 0;
    }
}
