package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cebcareapp.Database.UserDB;
import com.example.cebcareapp.Entity.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity /*implements View.OnClickListener*/ {

    TextInputLayout nameInput, emailInput, phoneInput, landInput, usernameInput, passwordInput, confirmPasswordInput;

    TextInputEditText name, email, phone, land, username, password, confirmPassword;
    TextInputEditText nameForShow, emailForShow, phoneForShow, landForShow, usernameForShow, passwordForShow, confirmPasswordForShow;

    String nameDef, emailDef, phoneDef, landDef;
    String nameForSave, emailForSave, phoneForSave, landForSave, emailForTest, usernameForSave, passwordForSave, confirmPasswordForSave, emailPattern;

    Boolean nameBool, emailBool, phoneBool, updatedFlag;

    FloatingActionButton editEnable,picUpload;

    Button register, back;

    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = findViewById(R.id.registrationToolbar);
        toolbar.setTitle("Registration");
        setSupportActionBar(toolbar);

        nameInput = findViewById(R.id.regNameTextInputLayout);
        emailInput = findViewById(R.id.regEmailTextInputLayout);
        phoneInput = findViewById(R.id.regPhoneInputLayout);
        landInput = findViewById(R.id.regLandLineLayout);
        usernameInput = findViewById(R.id.regUsernameLayout);
        passwordInput = findViewById(R.id.regPasswordLayout);
        confirmPasswordInput = findViewById(R.id.regConfirmpasswordLayout);

        name = findViewById(R.id.regNameEditText);
        email = findViewById(R.id.regEmailEditText);
        phone = findViewById(R.id.regPhoneEditText);
        land = findViewById(R.id.regLandLineEditText);
        username = findViewById(R.id.regUsernameEditText);
        password = findViewById(R.id.regPasswordEditText);
        confirmPassword = findViewById(R.id.regConfirmpasswordEditText);

        register = findViewById(R.id.registerBtn);
        back = findViewById(R.id.backFromRegForm);

        this.buttonListner();

    }

//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.register) {
//            Intent intent = new Intent(getApplicationContext(), BillPaymentWithHistory.class);
//            Bundle bundle = new Bundle();
//
//            if(this.isValid()){
//
//            }
//
//        }
//        if(view.getId() == R.id.backFromRegForm) {
//            onBackPressed();
//        }
//    }

    public void buttonListner(){
        this.register = findViewById(R.id.registerBtn);
        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Registration.this, LoginFinal.class);
                Bundle bundle1 = new Bundle();

                if(isValid()){
                    insertData();
                    bundle1.putString("name", nameInput.getEditText().getText().toString());
                    bundle1.putString("email", email.getText().toString().trim());
                    bundle1.putString("password", password.getText().toString().trim());
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    Toast.makeText(getBaseContext(), "Register Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.back = findViewById(R.id.backFromRegForm);
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private Boolean isValid() {
        nameForSave = name.getText().toString().trim();
        emailForTest = email.getText().toString().trim();
        phoneForSave = phone.getText().toString().trim();
        landForSave = land.getText().toString().trim();
        usernameForSave = username.getText().toString().trim();
        passwordForSave = password.getText().toString().trim();
        confirmPasswordForSave = confirmPassword.getText().toString().trim();

        if (isNameValid(nameForSave)) {
            nameInput.setErrorEnabled(false);
            if (isEmailValid(emailForTest)) {
                emailInput.setErrorEnabled(false);
                if (isEmptyPhone(phoneForSave)) {
                    phoneInput.setErrorEnabled(false);
                    if(isUsernameValid(usernameForSave)){
                        usernameInput.setErrorEnabled(false);
                        if(isEmptyPassword(passwordForSave)){
                            passwordInput.setErrorEnabled(false);
                            if(isEmptyPassword(confirmPasswordForSave)){
                                confirmPasswordInput.setErrorEnabled(false);
                                passwordForSave = password.getText().toString();
                                confirmPasswordForSave = confirmPassword.getText().toString();
                                if(confirmPasswordForSave.equals(passwordForSave)){
                                    confirmPasswordInput.setErrorEnabled(false);
                                    return true;
                                }else{
                                    confirmPasswordInput.setError("Password did not match");
                                    return false;
                                }
                            }else{
                                confirmPasswordInput.setError("Confirm Password cannot be empty");
                                return false;
                            }
                        }else{
                            passwordInput.setError("Password cannot be empty");
                            return false;
                        }
                    }else{
                        usernameInput.setError("Username cannot be empty");
                        return false;
                    }
                } else {
                    phoneInput.setError("Please enter a valid Phone Number");
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

    public Boolean isNameValid(String validName) {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(validName);
        return ms.matches() && (!TextUtils.isEmpty(validName));
    }

    public Boolean isEmailValid(String text) {

        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }

    public Boolean isEmptyPhone(String text){

        return !TextUtils.isEmpty(text)&& !Pattern.matches("[a-zA-Z]+", phoneForSave) && phoneForSave.length() > 6 && phoneForSave.length() <= 13;
    }

    public Boolean isUsernameValid(String text){

        return !TextUtils.isEmpty(text);
    }

    public Boolean isEmptyPassword(String text){

        return !TextUtils.isEmpty(text);
    }

    public void insertData(){
        this.user.setFullName(name.getText().toString());
        this.user.setEmail(email.getText().toString().trim());
        this.user.setPhone(Integer.parseInt(phone.getText().toString()));
        this.user.setLandPhone(Integer.parseInt(land.getText().toString()));
        this.user.setUserName(username.getText().toString().trim());
        this.user.setPassword(password.getText().toString().trim());
        UserDB userDb = new UserDB(getBaseContext());

        if(userDb.insertData(user)){
            Toast.makeText(getBaseContext(), "User "+this.user.getFullName()+" Register Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Registration.this, LoginFinal.class);
            Bundle bundle = new Bundle();
            String name1 = name.getText().toString();
            String email1 = email.getText().toString();
            String pw = password.getText().toString();

            bundle.putString("username2", name1);
            bundle.putString("email", email1);
            bundle.putString("password2", pw);
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }


}
