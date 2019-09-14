package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ResetPassword extends AppCompatActivity {

    TextInputEditText currentPasswordEditText, resetPasswordEditText, confirmpasswordEditText;
    Button reset, backReset;
    String pw, cnpw, crpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.passwordResetToolbar);
        toolbar.setTitle("Password Reset");
        setSupportActionBar(toolbar);

        currentPasswordEditText = findViewById(R.id.currentPasswordEditText);
        resetPasswordEditText = findViewById(R.id.resetPasswordEditText);
        confirmpasswordEditText = findViewById(R.id.confirmpasswordEditText);

        reset = findViewById(R.id.reset);
        backReset = findViewById(R.id.backReset);

        this.buttonListner();
    }

    public void buttonListner(){
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    crpwd = currentPasswordEditText.getText().toString().trim();
                    if(crpwd.equals("1111")){
                        cnpw = confirmpasswordEditText.getText().toString().trim();
                        pw = resetPasswordEditText.getText().toString().trim();
                        if(cnpw.equals(pw)){

                        }else{
                            Toast.makeText(getBaseContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                            confirmpasswordEditText.setError("Password did not match");
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Wrong Current Password. Did not match", Toast.LENGTH_SHORT).show();
                        currentPasswordEditText.setError("Current Password Did not match");
                    }
                }
            }
        });

        backReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public Boolean isValid(){
        if(isEmptyPassword(currentPasswordEditText.getText().toString().trim())){
            if(isEmptyPassword(resetPasswordEditText.getText().toString().trim())){
                if(isEmptyPassword(confirmpasswordEditText.getText().toString().trim())){
                    return true;
                }else{
                    confirmpasswordEditText.setError("confirmation Password cannot be empty");
                    return false;
                }
            }else{
                resetPasswordEditText.setError("confirmation Password cannot be empty");
                return false;
            }
        }else{
            currentPasswordEditText.setError("confirmation Password cannot be empty");
            return false;
        }
    }

    public Boolean isEmptyPassword(String text){
        return !TextUtils.isEmpty(text);
    }
}
