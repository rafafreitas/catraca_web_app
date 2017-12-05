package com.example.rafael.catraca_web_app;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.animation.Animation;

import basic.Auth;
import util.Internet;

public class NewVisitActivity extends AppCompatActivity {
    private TextWatcher cpfMask;

    private Vibrator vib;
    Animation animShake;

    private Internet internet;
    private Auth auth; //SingleUser

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);
    }
}
