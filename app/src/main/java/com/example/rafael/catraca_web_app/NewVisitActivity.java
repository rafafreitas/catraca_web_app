package com.example.rafael.catraca_web_app;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.widget.Button;

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

    private Internet internet;
    private Auth auth; //SingleUser

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);

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
