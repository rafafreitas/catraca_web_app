package com.example.rafael.catraca_web_app;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import util.ConfigOrientation;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button btn_voltar = (Button)findViewById(R.id.btn_Voltar);

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Isso aqui já faz tudo XD
        Log.i("Orientação = ", ConfigOrientation.verificaOrientacao(this));

        //Comment para commitar

        /*
        getResources().getConfiguration().orientation
        ORIENTATION_UNDEFINED = 0
        ORIENTATION_PORTRAIT = 1
        ORIENTATION_LANDSCAPE = 2
        ORIENTATION_SQUARE = 3

        Configuration configuration = getResources().getConfiguration();

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //...
        }else{
            //...
        }
        */
    }
}
