package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cebcareapp.Database.UserDB;
import com.example.cebcareapp.Entity.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {

    TextInputLayout nameInput, emailInput, amountInput, phoneInput, landInput;

    TextInputEditText name, email, amount, phone, land;
    TextInputEditText nameForShow, emailForShow, phoneForShow, landForShow;

    String nameDef, emailDef, phoneDef, landDef, usernameFromLogin, passwordFromLogin;
    String nameForSave, emailForSave, phoneForSave, landForSave, emailForTest, emailPattern;

    Boolean nameBool, emailBool, phoneBool, updatedFlag;

    FloatingActionButton editEnable,picUpload;

    Button saveChanges, resetPasswordBtn;

    User user = new User();
    Bundle bundleFromLogin =new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);



        name = nameForShow = (TextInputEditText)findViewById(R.id.nameEditText);
        email = emailForShow = (TextInputEditText)findViewById(R.id.emailEditText);
        phone = phoneForShow = (TextInputEditText)findViewById(R.id.phoneEditText);
        land = landForShow = (TextInputEditText)findViewById(R.id.landLineEditText);

        editEnable = findViewById(R.id.editEnable);
        picUpload = findViewById(R.id.picUpload);

        saveChanges = findViewById(R.id.saveChanges);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);



        nameInput = findViewById(R.id.nameTextInputLayout);
        emailInput = findViewById(R.id.emailTextInputLayout);
        phoneInput = findViewById(R.id.phoneInputLayout);

        bundleFromLogin = getIntent().getExtras();
        usernameFromLogin = bundleFromLogin.getString("username");
        passwordFromLogin = bundleFromLogin.getString("password");
        UserDB userDb = new UserDB(getBaseContext());
        user = userDb.getOneUser(usernameFromLogin);
        System.out.println("*****************get from DB***************************************");
        System.out.println(user.getPassword());
        System.out.println(user.getFullName());
        System.out.println(user.getUserName());
        System.out.println("********************************************************");
        nameDef = user.getFullName();
        emailDef = user.getEmail();
        phoneDef = Integer.toString(user.getPhone());
        landDef = Integer.toString(user.getLandPhone());

        nameForShow.setText(nameDef);
        nameForShow.setEnabled(false);
//        nameForShow.setFocusable(false);

        emailForShow.setText(emailDef);
        emailForShow.setEnabled(false);

        phoneForShow.setText(phoneDef);
        phoneForShow.setEnabled(false);

        landForShow.setText(landDef);
        landForShow.setEnabled(false);
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

        picUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Profile Picture uploaded",
                        Toast.LENGTH_SHORT);

                toast.show();
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

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameForSave = name.getText().toString().trim();
                emailForTest = email.getText().toString().trim();
                phoneForSave = phone.getText().toString().trim();
                landForSave = land.getText().toString().trim();

                Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                Matcher ms = ps.matcher(nameForSave);

                nameBool = ms.matches() && (!TextUtils.isEmpty(nameForSave));
                emailBool = (!TextUtils.isEmpty(emailForTest) && Patterns.EMAIL_ADDRESS.matcher(emailForTest).matches());
                phoneBool = (!TextUtils.isEmpty(phoneForSave)) && !Pattern.matches("[a-zA-Z]+", phoneForSave) && phoneForSave.length() > 6 && phoneForSave.length() <= 13;

                if (nameBool) {
                    nameInput.setErrorEnabled(false);
                    if (emailBool) {
                        emailInput.setErrorEnabled(false);
                        if (phoneBool) {
                            phoneInput.setErrorEnabled(false);

                            nameDef = nameForSave = name.getText().toString().trim();
                            emailForTest = email.getText().toString().trim();
                            phoneForSave = phone.getText().toString();
                            landForSave = land.getText().toString().trim();

                            user.setFullName(nameForSave);
                            user.setEmail(emailForTest);
                            user.setPhone(Integer.parseInt(phoneForSave));
                            user.setLandPhone(Integer.parseInt(landForSave));
                            UserDB userDb1 = new UserDB(getBaseContext());
                            userDb1.updateProfile(user);
                            System.out.println("in phone true");
                            updatedFlag=true;

                        } else {
                            phoneInput.setError("Please enter a valid Phone Number");
                            System.out.println("in phone false");
                            updatedFlag=false;
                        }
                    } else {
                        emailInput.setError("Please enter a valid Email !");
                        updatedFlag=false;
                    }
                } else {
                    nameInput.setBoxBackgroundColorResource(R.color.redColour);
                    nameInput.setError("Please enter a valid name !");
                    updatedFlag=false;
                }

                if(updatedFlag){
                    Intent intent = new Intent(Profile.this, Profile.class);
                    intent.putExtras(bundleFromLogin);
                    startActivity(intent);
                    finish();
                }
            }
        });
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Profile.this, ResetPassword.class);
                intent2.putExtras(bundleFromLogin);
                startActivity(intent2);
            }
        });
    }


}
