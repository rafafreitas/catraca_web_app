package com.example.rafael.catraca_web_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import basic.Auth;
import util.Internet;

/**
 * Created by rafael on 24/11/17.
 */

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "ActivityLoginUser";
    private Vibrator vib;
    Animation animShake;
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnLogin;
    private Internet internet;
    private Auth auth; //SingleUser


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_login);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        inputEmail = (EditText) findViewById(R.id.input_login);
        inputPassword = (EditText) findViewById(R.id.input_pass);

        btnLogin = (Button) findViewById(R.id.btn_login);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submitForm()){
                    Toast.makeText(getApplicationContext(), "Campos Válidos !!", Toast.LENGTH_SHORT).show();
                    if (!internet.verificarConexao()) {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setCancelable(false)
                                .setTitle(R.string.app_name)
                                .setMessage("Por favor, verifique sua conexao com a internet.")

                                // Positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }else {
                        
                    }


                    //Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    //startActivity(intent);
                }
            }
        });

        final TextView txtView = this.findViewById(R.id.newClient);
        txtView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "É só fazer o cadastro...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,NewClientActivity.class);
                startActivity(intent);
            }
        });

        final TextView txtforgot = this.findViewById(R.id.forgotPass);
        txtforgot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ResetPassActivity.class);
                startActivity(intent);
            }
        });

    }//onCreate

    private boolean submitForm() {

        if (!checkEmail()) {
            inputEmail.setAnimation(animShake);
            inputEmail.startAnimation(animShake);
            vib.vibrate(120);
            return false;
        }
        if (!checkPassword()) {
            inputPassword.setAnimation(animShake);
            inputPassword.startAnimation(animShake);
            vib.vibrate(120);
            return false;
        }

        inputLayoutEmail.setErrorEnabled(false);
        inputLayoutPassword.setErrorEnabled(false);

        return true;
    }

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

    private boolean checkPassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {

            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        }
        inputLayoutPassword.setErrorEnabled(false);
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

}
