package com.example.rafael.catraca_web_app;

/**
 * Created by rafael on 17/11/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class NewClient extends AppCompatActivity {
    private TextWatcher dateMask;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        final TextView txtView = this.findViewById(R.id.isClient);
        txtView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(NewClient.this,MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        final EditText dateNiver = (EditText) findViewById(R.id.input_date);
        // Armazene seus TextWatcher para posterior uso
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        Spinner spinner = (Spinner) findViewById(R.id.typeUser);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_user, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }


}


