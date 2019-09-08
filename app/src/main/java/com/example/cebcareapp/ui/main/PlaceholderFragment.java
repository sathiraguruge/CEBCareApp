package com.example.cebcareapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import com.example.cebcareapp.BillPaymentWithHistory;
import com.example.cebcareapp.PaymentDetails;
import com.example.cebcareapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    TextInputLayout nameInput, emailInput, amountInput;
    TextInputEditText name, email, amount;
    Button proceedBtn;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bill_payment_with_history, container, false);
        nameInput = root.findViewById(R.id.nameTextInputLayoutTab);
        emailInput = root.findViewById(R.id.emailTextInputLayoutTab);
        amountInput = root.findViewById(R.id.amountInputLayoutTab);

        name = root.findViewById(R.id.nameEditTextTab);
        email = root.findViewById(R.id.emailEditTextTab);
        amount = root.findViewById(R.id.amountEditTextTab);

        proceedBtn = root.findViewById(R.id.proceedToPaymentBtnTab);


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
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
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
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
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
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                "Amount need to pay goes here",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PaymentDetails.class);
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
        });


        return root;
    }

    private Boolean isValid() {

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


}