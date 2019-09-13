package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentDetails extends AppCompatActivity {

    TextView nameText, emailText, amountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Payment Details");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String amount = bundle.getString("amount");

        nameText = findViewById(R.id.paymentDetailsNameTextView);
        emailText = findViewById(R.id.paymentDetailsEmailTextView);
        amountText = findViewById(R.id.paymentDetailsAmountTextView);

        nameText.setText(name);
        emailText.setText(email);
        String formatedAmount = "Rs. " + amount;
        amountText.setText(formatedAmount);

    }
}
