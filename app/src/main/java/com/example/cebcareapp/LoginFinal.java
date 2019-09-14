package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cebcareapp.Database.UserDB;
import com.example.cebcareapp.Entity.User;

public class LoginFinal extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    String usernameForSave, passwordForSave, confirmPasswordForSave, emailPattern;
    ImageButton passwordRecover, userRegister, loginButton;

    User user = new User();
    UserDB userDb = new UserDB(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_final);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);

        passwordRecover = findViewById(R.id.passwordRecover);
        userRegister = findViewById(R.id.userRegister);
        loginButton = findViewById(R.id.loginButton);

        this.buttonListner();
    }

    public void buttonListner(){
        this.userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LoginFinal.this, Registration.class);
                startActivity(intent1);
            }
        });

        this.passwordRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent(LoginFinal.this, Registration.class);

                if (isValid()){
                    Toast.makeText(getBaseContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(LoginFinal.this, MainActivity.class);
                    Bundle bundle1 = new Bundle();

                    user = userDb.getOneUser(loginUsername.getText().toString());
                    passwordForSave = loginPassword.getText().toString().trim();

                    if(passwordForSave.equals(/*user.getPassword()*/"1111")){
                        bundle1.putString("username", loginUsername.getText().toString());
                        bundle1.putString("password", loginPassword.getText().toString());
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                    }else {
                        Toast.makeText(getBaseContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                        loginUsername.setError("Incorrect Password");
                    }

                }
            }
        });

    }

    public Boolean isValid(){
        usernameForSave = loginUsername.getText().toString();
        passwordForSave = loginPassword.getText().toString();
//        confirmPasswordForSave =;
        if(this.isUsernameValid(usernameForSave)){
            if(this.isEmptyPassword(passwordForSave)){
                return true;
            }else{
                loginPassword.setError("Password can not be empty");
                return false;
            }
        }else{
            loginUsername.setError("Username can not be empty");
            return false;
        }
    }

    public Boolean isUsernameValid(String text){

        return !TextUtils.isEmpty(text);
    }

    public Boolean isEmptyPassword(String text){

        return !TextUtils.isEmpty(text);
    }
}
