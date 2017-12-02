package com.example.rafael.catraca_web_app;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import basic.Auth;
import basic.Usuario;
import util.CNP;
import util.Mask;

public class UserDataActivity extends AppCompatActivity {
    private TextWatcher dateMask;
    private TextWatcher cpfMask;

    private Vibrator vib;
    Animation animShake;
    private EditText inputName, inputEmail, inputPassword, inputCpf, inputDate;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutCpf, inputLayoutDate;
    private Button btnUpdate;

    private Auth auth; //SingleUser


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        auth = Auth.getInstance();
        Usuario usuario = new Usuario();
        usuario = auth.getUsuario();

        initToolbar();
        initInputs(usuario);
        mask();

        //Send Form
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void initInputs(Usuario usuario){
        //Pegar Valores para realizar a validação do form
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_nome);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        //inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutCpf = (TextInputLayout) findViewById(R.id.input_layout_cpf);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date);

        inputName = (EditText) findViewById(R.id.input_nome);
        inputName.setText(usuario.getUser_nome(), TextView.BufferType.EDITABLE);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputEmail.setText(usuario.getUser_email(), TextView.BufferType.EDITABLE);

        //inputPassword = (EditText) findViewById(R.id.input_password);
        inputCpf = (EditText) findViewById(R.id.input_cpf);
        inputCpf.setText(usuario.getUser_cpf(), TextView.BufferType.EDITABLE);

        inputDate = (EditText) findViewById(R.id.input_date);
        inputDate.setText(usuario.getUser_data_nasc(), TextView.BufferType.EDITABLE);

        btnUpdate = (Button) findViewById(R.id.btn_update);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void mask(){
        //Chamando Máscara do Aniversário
        final EditText dateNiver = (EditText) findViewById(R.id.input_date);
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        //Chamando Máscara do CPF
        final EditText cpfUser = (EditText) findViewById(R.id.input_cpf);
        cpfMask = Mask.insert("###.###.###-##", cpfUser);
        cpfUser.addTextChangedListener(cpfMask);

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home_white_24dp));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_myAccount:
                Intent intent = new Intent(UserDataActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_myMaps:
                //ChamarActivitydeMaps();
                return true;
            case R.id.action_about:
                //ChamarActivityFalandoSobreOaplicativo();
                return true;
            case R.id.action_exit:
                Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
        if (!checkCPF()) {
            inputCpf.setAnimation(animShake);
            inputCpf.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkDate()) {
            inputDate.setAnimation(animShake);
            inputDate.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        inputLayoutName.setErrorEnabled(false);
        inputLayoutEmail.setErrorEnabled(false);
        //inputLayoutPassword.setErrorEnabled(false);
        inputLayoutDate.setErrorEnabled(false);
        Toast.makeText(getApplicationContext(), "Campos Válidos !!", Toast.LENGTH_SHORT).show();
    }//submitForm

    private boolean checkName() {
        if (inputName.getText().toString().trim().isEmpty()) {

            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError(getString(R.string.err_msg_nome));
            inputName.setError(getString(R.string.err_msg_required));
            requestFocus(inputName);
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

    private boolean checkCPF() {
        if (inputCpf.getText().toString().trim().isEmpty()) {

            inputLayoutCpf.setErrorEnabled(true);
            inputLayoutCpf.setError(getString(R.string.err_msg_required));
            //inputCpf.setError(getString(R.string.err_msg_required));
            requestFocus(inputCpf);
            return false;
        }else{
            String Str = inputCpf.getText().toString();
            Str = Str.replace(".", "");
            Str = Str.replace("-", "");

            boolean retorno = CNP.isValidCPF(Str);
            if (!retorno) {
                inputLayoutCpf.setErrorEnabled(true);
                inputLayoutCpf.setError(getString(R.string.err_msg_cpf));
                //inputCpf.setError(getString(R.string.err_msg_required));
                requestFocus(inputCpf);
                return false;
            }
            // return CNP.isValidCPF(Str);
        }
        inputLayoutCpf.setErrorEnabled(false);
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
}
