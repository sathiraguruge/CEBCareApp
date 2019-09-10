package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Profile extends AppCompatActivity {

    TextInputLayout nameInput, emailInput, amountInput, phoneInput, landInput;
    TextInputEditText name, email, amount, phone, land;
    TextInputEditText nameForShow, emailForShow, phoneForShow, landForShow;
    String nameDef, emailDef, phoneDef, landDef, emailForTest, emailPattern;
    String nameForSave, emailForSave, phoneForSave, landForSave;
    FloatingActionButton editEnable;
    Button saveChanges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        nameDef = "Ashhar";
        emailDef = "ashharahmed00@gmail.com";
        phoneDef = "0777191214";
        landDef = "0413495321";

        name = nameForShow = (TextInputEditText)findViewById(R.id.nameEditText);
        email = emailForShow = (TextInputEditText)findViewById(R.id.emailEditText);
        phone = phoneForShow = (TextInputEditText)findViewById(R.id.phoneEditText);
        land = landForShow = (TextInputEditText)findViewById(R.id.landLineEditText);

        editEnable=findViewById(R.id.editEnable);

        saveChanges=findViewById(R.id.saveChanges);

        emailInput = findViewById(R.id.emailTextInputLayout);

        nameForShow.setText(nameDef);
        nameForShow.setEnabled(false);
//        nameForShow.setFocusable(false);

        emailForShow.setText(emailDef);
        emailForShow.setEnabled(false);

        phoneForShow.setText(phoneDef);
        phoneForShow.setEnabled(false);

        landForShow.setText(landDef);
        landForShow.setEnabled(false);

        nameInput = findViewById(R.id.nameTextInputLayout);
        emailInput = findViewById(R.id.emailTextInputLayout);

        name.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Name Editing Enable",
                                Toast.LENGTH_SHORT);
                        toast.show();
//                        nameForShow.setFocusable(true);
                        nameForShow.setEnabled(true);
//                        nameForShow.setCursorVisible(false);
//                        nameForShow.setEnabled(false);
                        return true;
                    }
                }
                return false;
            }
        });

        editEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "All fields were Enabled for Editing",
                        Toast.LENGTH_SHORT);
                toast.show();
                nameForShow.setEnabled(true);
                emailForShow.setEnabled(true);
                phoneForShow.setEnabled(true);
                landForShow.setEnabled(true);

                nameForShow.setFocusable(true);
            }
        });



        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                emailForTest = email.getText().toString().trim();

                if (emailForTest.matches(emailPattern) && s.length() > 0)
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    // or
                    //textView.setText("valid email");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or
                    //textView.setText("invalid email");
                }
            }
        });


        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameForSave = name.getText().toString().trim();
                emailForTest = email.getText().toString().trim();

                if (emailForTest.matches(emailPattern) && emailForTest.length() > 0)
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    // or
                    //textView.setText("valid email");
                    emailForSave = email.getText().toString().trim();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or
                    //textView.setText("invalid email");

                    emailInput.setFocusable(true);
                    email.setCursorVisible(true);
                    email.setFocusable(true);
                    emailInput.setBoxBackgroundColor(16777215);

                    email.setError("Enter Valid Email address");
                }
                    /*CharSequence email = email.getText().toString();
                    if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    }
*/

                phoneForSave = phone.getText().toString().trim();
                landForSave = land.getText().toString().trim();

                Intent intent = new Intent(Profile.this, Registration.class);
                startActivity(intent);
            }
        });
    }
}
