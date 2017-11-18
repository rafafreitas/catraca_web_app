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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class NewClient extends AppCompatActivity {
    private TextWatcher dateMask;
    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;

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
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        final EditText cpfCnpjUser = (EditText) findViewById(R.id.input_cpf_cnpj);
        cpfMask = Mask.insert("###.###.###-##", cpfCnpjUser);
        cnpjMask = Mask.insert("##.###.###/####-##", cpfCnpjUser);

        cpfCnpjUser.addTextChangedListener(cpfMask);


        Spinner spinner = (Spinner) findViewById(R.id.typeUser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_user, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //Colocando Máscara
                        cpfCnpjUser.setText("");
                        cpfCnpjUser.removeTextChangedListener(cnpjMask);
                        cpfCnpjUser.removeTextChangedListener(cpfMask);
                        cpfCnpjUser.addTextChangedListener(cpfMask);

                        //Modificando Hint
                        dateNiver.setHint(R.string.hint_date_fis);

                    break;

                    case 1:
                        //Colocando Máscara
                        cpfCnpjUser.setText("");
                        cpfCnpjUser.removeTextChangedListener(cnpjMask);
                        cpfCnpjUser.removeTextChangedListener(cpfMask);
                        cpfCnpjUser.addTextChangedListener(cnpjMask);

                        //Modificando Hint
                        dateNiver.setHint(R.string.hint_date_jur);
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

  }


