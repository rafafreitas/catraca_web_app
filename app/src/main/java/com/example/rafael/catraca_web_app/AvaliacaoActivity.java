package com.example.rafael.catraca_web_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import banco.BancoController;

/**
 * Created by matheus on 06/12/17.
 */

public class AvaliacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        /*
        Instancia dos elementos presentes no XML
        */
        Button btn_classificarAvaliacao = (Button)findViewById(R.id.btn_classificarAvaliacao);
        Button btn_voltarAvaliacao = (Button)findViewById(R.id.btn_voltarAvaliacao);

        //CHAMADA DO MÉTODO PARA INSERIR OS DADOS

        //NO CLICK DO BOTÃO DE CLASSIFICAR AVALIAÇÃO
        btn_classificarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController bancoController = new BancoController(getBaseContext());
                EditText edt_Usuario = (EditText)findViewById(R.id.editTextUsuario);
                EditText edt_Descricao = (EditText)findViewById(R.id.editTextDescricao);
                EditText edt_Nota = (EditText)findViewById(R.id.editTextNota);
                String usuarioString = edt_Usuario.getText().toString();
                String descricaoString = edt_Descricao.getText().toString();
                String notaString = edt_Nota.getText().toString();
                String resultado;

                resultado = bancoController.inserirDados(usuarioString,descricaoString,notaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}