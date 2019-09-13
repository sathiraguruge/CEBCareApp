package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankPayment extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout cardNumberInputLayout, pinInputLayout;
    Spinner monthSpinner, yearSpinner;
    EditText cardNumber, pinNumber, amountEditText;
    String amount;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_payment);

        Bundle bundle = getIntent().getExtras();
        amount = bundle.getString("amount");

        cardNumberInputLayout = findViewById(R.id.cardNumberTextInputLayout);
        cardNumber = findViewById(R.id.cardNumberEditText);

        monthSpinner = findViewById(R.id.monthSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);

        pinInputLayout = findViewById(R.id.pinTextInputLayout);
        pinNumber = findViewById(R.id.pinEditText);

        amountEditText = findViewById(R.id.bankPayAmountEditText);

        amountEditText.setText(String.format("Rs. %s", amount));

        cardNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (cardNumber.getRight() - cardNumber.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "16 Digit Card Number goes here. Ex:- 1234 5678 1234 5678",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        pinNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (pinNumber.getRight() - pinNumber.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "3 Digit Number in the backside of the card goes here.",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });


    }


    private Boolean isValid() {
        cardNumberInputLayout = findViewById(R.id.cardNumberTextInputLayout);
        pinInputLayout = findViewById(R.id.pinTextInputLayout);

        cardNumber = findViewById(R.id.cardNumberEditText);
        pinNumber = findViewById(R.id.pinEditText);

        if (isCardNumberValid(cardNumber)) {
            cardNumberInputLayout.setErrorEnabled(false);
            if (isPinValid(pinNumber)) {
                pinInputLayout.setErrorEnabled(false);
                return true;
            } else {
                pinInputLayout.setError("Please enter a valid PIN number");
                return false;
            }
        } else {
            cardNumberInputLayout.setError("Please enter a valid card number");
            return false;
        }

    }

    public Boolean isCardNumberValid(EditText text) {
        if (text.getText().length() == 16 && !TextUtils.isEmpty(text.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isPinValid(EditText text) {
        if (text.getText().length() == 3 && !TextUtils.isEmpty(text.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bankPayBtn) {
            if (isValid()) {
                Toast.makeText(getApplicationContext(), "Payment Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    }
}

