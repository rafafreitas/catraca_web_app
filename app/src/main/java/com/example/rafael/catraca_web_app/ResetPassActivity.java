package com.example.rafael.catraca_web_app;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import util.Mask;

/**
 * Created by Rafael Freitas on 21/11/2017.
 */

public class ResetPassActivity extends AppCompatActivity {
    private TextWatcher dateMask;
    private static final String TAG = "ResetActivity";

    private Vibrator vib;
    Animation animShake;
    private EditText inputEmail, inputDate;
    private TextInputLayout inputLayoutEmail, inputLayoutDate;
    private Button btnReset;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputDate = (EditText) findViewById(R.id.input_date);

        btnReset = (Button) findViewById(R.id.btn_reset);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Chamando Máscara de Data
        final EditText dateNiver = (EditText) findViewById(R.id.input_date);
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        //Send Form
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        //Clique do TextViewer Login
        final TextView txtView = this.findViewById(R.id.isClient);
        txtView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Clique do TextViewer Dúvida
        final TextView txtViewDate = this.findViewById(R.id.questionDate);
        txtViewDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.info_reset_pass_04, Toast.LENGTH_SHORT).show();
            }
        });

    }//OnCreate

    private void submitForm() {

        if (!checkEmail()) {
            inputEmail.setAnimation(animShake);
            inputEmail.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (!checkDate()) {
            inputDate.setAnimation(animShake);
            inputDate.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        inputLayoutEmail.setErrorEnabled(false);
        inputLayoutDate.setErrorEnabled(false);
        Toast.makeText(getApplicationContext(), "Campos Válidos !!", Toast.LENGTH_SHORT).show();
    }//submitForm

    private boolean checkEmail() {
        String email = inputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            inputLayoutEmail.setErrorEnabled(true);
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            inputEmail.setError(getString(R.string.err_msg_required));
            requestFocus(inputEmail);
            return false;
        }
        inputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkDate() {

        try {
            boolean isDateValid = false;
            String[] s = inputDate.getText().toString().split("/");
            int date = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);
            int year = Integer.parseInt(s[2]);

            if (date < 32 && month < 13 && year > 1900 && year < 2019)
                isDateValid = true;

            if (inputDate.getText().toString().trim().isEmpty() || !isDateValid) {

                inputLayoutDate.setErrorEnabled(true);
                inputLayoutDate.setError(getString(R.string.err_msg_date));
                inputDate.setError(getString(R.string.err_msg_required));
                requestFocus(inputDate);

                return false;
            }
        }catch(Exception ex){
            inputLayoutDate.setError(getString(R.string.err_msg_date));
            requestFocus(inputDate);
            return false;
        }

        inputDate.setError(null);
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}//ResetClass
