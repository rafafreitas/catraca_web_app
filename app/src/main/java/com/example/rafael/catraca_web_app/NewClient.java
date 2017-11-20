package com.example.rafael.catraca_web_app;

/**
 * Created by rafael on 17/11/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class NewClient extends AppCompatActivity {
    private TextWatcher dateMask;
    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;

    private static final String TAG = "CadastroActivity";
    private Vibrator vib;
    Animation animShake;
    private EditText inputName, inputEmail, inputPassword, inputCpfCnpj, inputDate;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutCpfCnjp, inputLayoutDate;
    private Button btnCadastro;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        //Pegar Valores para realizar a validação do form
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_nome);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutCpfCnjp = (TextInputLayout) findViewById(R.id.input_layout_cpf_cnpj);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date);

        inputName = (EditText) findViewById(R.id.input_nome);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputCpfCnpj = (EditText) findViewById(R.id.input_cpf_cnpj);
        inputDate = (EditText) findViewById(R.id.input_date);

        btnCadastro = (Button) findViewById(R.id.btn_cadastro);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Chamando Máscara do Aniversário
        final EditText dateNiver = (EditText) findViewById(R.id.input_date);
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        //Chamando Máscara do CPF
        final EditText cpfCnpjUser = (EditText) findViewById(R.id.input_cpf_cnpj);
        cpfMask = Mask.insert("###.###.###-##", cpfCnpjUser);
        cnpjMask = Mask.insert("##.###.###/####-##", cpfCnpjUser);

        cpfCnpjUser.addTextChangedListener(cpfMask);

        //Alimentar Spinner
        Spinner spinner = (Spinner) findViewById(R.id.typeUser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_user, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //Mudar mask de acordo com a seleção do Spinner
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
        });//OnItemSelected

        //Send Form
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        //Clique do TextViewer
        final TextView txtView = this.findViewById(R.id.isClient);
        txtView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(NewClient.this,MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });


    }//onCreate

    private void submitForm() {

        if (!checkName()) {
            inputName.setAnimation(animShake);
            inputName.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkEmail()) {
            inputEmail.setAnimation(animShake);
            inputEmail.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkPassword()) {
            inputPassword.setAnimation(animShake);
            inputPassword.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkDate()) {
            inputDate.setAnimation(animShake);
            inputDate.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
//        if (!checkCPF()) {
//            inputCpfCnpj.setAnimation(animShake);
//            inputCpfCnpj.startAnimation(animShake);
//            vib.vibrate(120);
//            return;
//        }
        inputLayoutName.setErrorEnabled(false);
        inputLayoutEmail.setErrorEnabled(false);
        inputLayoutPassword.setErrorEnabled(false);
        inputLayoutDate.setErrorEnabled(false);
        Toast.makeText(getApplicationContext(), "Campos Válidos !!", Toast.LENGTH_SHORT).show();
    }//submitForm

    private boolean checkName() {
        if (inputName.getText().toString().trim().isEmpty()) {

            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError(getString(R.string.err_msg_nome));
            inputName.setError(getString(R.string.err_msg_required));
            return false;
        }
        inputLayoutName.setErrorEnabled(false);
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

    private boolean checkDate() {

        try {
            boolean isDateValid = false;
            String[] s = inputDate.getText().toString().split("/");
            int date = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);

//            Toast.makeText(getApplicationContext(), s[0], Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), s[1], Toast.LENGTH_SHORT).show();


            if (date < 32 && month < 13)
                isDateValid = true;

            if (inputDate.getText().toString().trim().isEmpty() || !isDateValid) {

                inputLayoutDate.setError(getString(R.string.err_msg_date));
                requestFocus(inputDate);
                inputDate.setError(getString(R.string.err_msg_required));

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


  }//newClientClass


