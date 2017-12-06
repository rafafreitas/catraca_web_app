package com.example.rafael.catraca_web_app;

import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import basic.Auth;
import basic.Responsaveis;
import basic.VisitaMotivo;
import util.Internet;
import util.Util;

public class NewVisitActivity extends AppCompatActivity {
    private TextWatcher cpfMask;

    private Vibrator vib;
    Animation animShake;

    private Button btnCadastro;
    private boolean flag = false;

    private Internet internet;
    private Auth auth; //SingleUser

    Animation show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
    Animation hide_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);

        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFab);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) myFab.getLayoutParams();
                if (!flag){
                    layoutParams.rightMargin += (int) (myFab.getWidth() * 1.7);
                    layoutParams.bottomMargin += (int) (myFab.getHeight() * 0.25);
                    myFab.setLayoutParams(layoutParams);
                    myFab.startAnimation(show_fab);
                    myFab.setClickable(true);
                    flag = true;
                }if (flag){
                    layoutParams.rightMargin -= (int) (myFab.getWidth() * 1.7);
                    layoutParams.bottomMargin -= (int) (myFab.getHeight() * 0.25);
                    myFab.setLayoutParams(layoutParams);
                    myFab.startAnimation(hide_fab);
                    myFab.setClickable(false);
                    flag = false;
                }

            }
        });

        auth = Auth.getInstance();
        internet = new Internet(this);
        Util.setCtxAtual(this);

        initSpinner(auth.getResponsaveis(), auth.getVisitaMotivos());

    }


    private void initSpinner(ArrayList<Responsaveis> responsaveis, ArrayList<VisitaMotivo> visitaMotivos){

        for (Responsaveis responsavel : responsaveis){
            //Será implementado o código para alimentar o spiner
        }
        for (VisitaMotivo visitaMotivo : visitaMotivos){
            //Será implementado o código para alime//Será implementado o código para aliemntar o spinerntar o spiner
        }


    }



}
