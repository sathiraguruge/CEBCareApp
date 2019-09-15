package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView appBg, clover;
    Animation bgAnim, cloverAnim, fromButton, menuAnim;
    LinearLayout appNameSplash, homeTitle, menu;

    int numOfSessions, count; // This is just for GIT demo purpose, delete later

    //Menu Icons
    LinearLayout complains, calender, payment, profile, calculator, assess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromButton = AnimationUtils.loadAnimation(this, R.anim.frombuttomanim);
        menuAnim = AnimationUtils.loadAnimation(this, R.anim.bottomupmenuanim);

        //Getting the references of the buttons
        complains = findViewById(R.id.complains);
        payment = findViewById(R.id.payment);
        profile = findViewById(R.id.profile);
        calculator = findViewById(R.id.calculator);
        assess = findViewById(R.id.asses);

        appBg = findViewById(R.id.appBackground);
        clover = findViewById(R.id.clover);
        appNameSplash = findViewById(R.id.appNameSplash);
        homeTitle = findViewById(R.id.homeTitle);
        menu = findViewById(R.id.cebMenu);

//        bgAnim = AnimationUtils.loadAnimation(this, R.anim.homeanimation);
//        cloverAnim = AnimationUtils.loadAnimation(this, R.anim.cloveranim);

        appBg.animate().translationY(-2100).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        appNameSplash.animate().translationY(140).alpha(0).setStartDelay(500);

        homeTitle.startAnimation(fromButton);
        menu.startAnimation(menuAnim);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.complains) {
            Intent intent = new Intent(MainActivity.this, ComplaintActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.payment) {
            Intent intent = new Intent(MainActivity.this, BillPaymentWithHistory.class);
            startActivity(intent);

        } else if (v.getId() == R.id.profile) {

            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);

        } else if (v.getId() == R.id.calculator) {
            Intent intent = new Intent(getApplicationContext(), BillCalculator.class);
            startActivity(intent);

        } else if (v.getId() == R.id.asses) {
            Intent intent = new Intent(MainActivity.this, AssesByCensus.class);
            startActivity(intent);
        }
    }
}
