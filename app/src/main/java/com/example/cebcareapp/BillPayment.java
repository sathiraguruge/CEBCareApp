package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillPayment extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout nameInput, emailInput, amountInput;
    TextInputEditText name, email, amount;
    Button proceedBtn;
    Spinner accountSpinner;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Bill Payment");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameInput = findViewById(R.id.nameTextInputLayout);
        emailInput = findViewById(R.id.emailTextInputLayout);
        amountInput = findViewById(R.id.amountInputLayout);

        accountSpinner = findViewById(R.id.billPaymentAccountSpinner);

        name = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        amount = findViewById(R.id.amountEditText);

        proceedBtn = findViewById(R.id.proceedToPaymentBtn);

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Your name goes here.",
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

        amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (amount.getRight() - amount.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Amount need to pay goes here",
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
        accountSpinner = findViewById(R.id.billPaymentAccountSpinner);

        nameInput = findViewById(R.id.nameTextInputLayout);
        emailInput = findViewById(R.id.emailTextInputLayout);
        amountInput = findViewById(R.id.amountInputLayout);

        Toast.makeText(getBaseContext(),accountSpinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

        if (isNameValid(nameInput.getEditText())) {
            nameInput.setErrorEnabled(false);
            if (isEmailValid(emailInput.getEditText())) {
                emailInput.setErrorEnabled(false);
                if (isAmountValid(amountInput.getEditText())) {
                    amountInput.setErrorEnabled(false);
                    return true;
                } else {
                    amountInput.setError("Please enter a valid Email");
                    return false;
                }
            } else {
                emailInput.setError("Please enter a valid Email !");
                return false;
            }
        } else {
            nameInput.setBoxBackgroundColorResource(R.color.redColour);
            nameInput.setError("Please enter a valid name !");
            return false;
        }

    }

    public Boolean isNameValid(EditText text) {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(text.getText().toString());
        return ms.matches() && (!TextUtils.isEmpty(text.getText().toString()));
    }


    public Boolean isEmailValid(EditText text) {
        return (!TextUtils.isEmpty(text.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(text.getText().toString()).matches());
    }

    public Boolean isAmountValid(EditText text) {
        return !TextUtils.isEmpty(text.getText().toString());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.proceedToPaymentBtn) {
            Intent intent = new Intent(getApplicationContext(), BillPaymentWithHistory.class);
            Bundle bundle = new Bundle();
            String name = nameInput.getEditText().getText().toString();
            String email = emailInput.getEditText().getText().toString();
            String amount = amountInput.getEditText().getText().toString();


            if (isValid()) {
                bundle.putString("name", name);
                bundle.putString("email", email);
                bundle.putString("amount", amount);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

    }
}
