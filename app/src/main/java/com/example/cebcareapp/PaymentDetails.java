package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentDetails extends AppCompatActivity implements View.OnClickListener {

    TextView nameText, emailText, amountText;
    Button pay;
    String name, email, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Payment Details");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        name = bundle.getString("name");
        email = bundle.getString("email");
        amount = bundle.getString("amount");

        nameText = findViewById(R.id.paymentDetailsNameTextView);
        emailText = findViewById(R.id.paymentDetailsEmailTextView);
        amountText = findViewById(R.id.paymentDetailsAmountTextView);

        pay = findViewById(R.id.payBtn);

        nameText.setText(name);
        emailText.setText(email);
        String formatedAmount = "Rs. " + amount;
        amountText.setText(formatedAmount);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.payBtn) {
            Intent intent = new Intent(this, BankPayment.class);
            Bundle bundle = new Bundle();
            bundle.putString("amount", amount);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
