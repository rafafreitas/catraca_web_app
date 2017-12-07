package com.example.rafael.catraca_web_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import banco.BancoController;
import basic.Auth;
import basic.Avaliacao;
import basic.Usuario;

/**
 * Created by matheus on 06/12/17.
 */

public class AvaliacaoActivity extends AppCompatActivity {

    private Auth auth; //SingleUser
    BancoController bancoController;
    Avaliacao avaliacao;
    EditText edt_Usuario;
    EditText edt_Descricao;
    EditText edt_Nota;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        bancoController = new BancoController(getBaseContext());

        /*
        Instancia dos elementos presentes no XML
        */
        Button btn_classificarAvaliacao = (Button) findViewById(R.id.btn_classificarAvaliacao);
        Button btn_voltarAvaliacao = (Button) findViewById(R.id.btn_voltarAvaliacao);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);

        auth = Auth.getInstance();
        usuario = auth.getUsuario();

        edt_Usuario = (EditText) findViewById(R.id.editTextUsuario);
        edt_Descricao = (EditText) findViewById(R.id.editTextDescricao);
        edt_Nota = (EditText) findViewById(R.id.editTextNota);

        List<Avaliacao> listAvaliacao = bancoController.buscarAvaliacao(Integer.toString(usuario.getUser_id()));

        if (listAvaliacao.size() > 0) {
            avaliacao = listAvaliacao.get(0);
            edt_Usuario.setText(listAvaliacao.get(0).getUsuarioNome());
            edt_Descricao.setText(listAvaliacao.get(0).getDescricao());
            edt_Nota.setText(listAvaliacao.get(0).getNota());
        } else
            avaliacao = null;

        //CHAMADA DO MÉTODO PARA INSERIR OS DADOS

        //NO CLICK DO BOTÃO DE CLASSIFICAR AVALIAÇÃO
        btn_classificarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean alterar;

                if (avaliacao == null) {
                    avaliacao = new Avaliacao();
                    alterar = false;
                } else {
                    alterar = true;
                }

                avaliacao.setUsuarioId(usuario.getUser_id());
                avaliacao.setUsuarioNome(edt_Usuario.getText().toString());
                avaliacao.setDescricao(edt_Descricao.getText().toString());
                avaliacao.setNota(edt_Nota.getText().toString());

                //Validations

                if (avaliacao.getUsuarioNome().isEmpty()){
                    Toast.makeText(getApplication(), "O nome de usuário é obrigatório!", Toast.LENGTH_SHORT).show();
                }else if (avaliacao.getNota().isEmpty() && !avaliacao.getUsuarioNome().isEmpty()){
                    Toast.makeText(getApplication(), "A nota é obrigatória!", Toast.LENGTH_SHORT).show();
                }else{

                }

                //-------------------

                if (!avaliacao.getUsuarioNome().isEmpty() && !avaliacao.getNota().isEmpty()){
                    bancoController.salvar(avaliacao);
                    if (alterar) {
                        Toast.makeText(getApplicationContext(), "Alteração de dados completa!\n" + "Obrigado pela sua reavaliação!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dados inseridos com sucesso!\n" + "Obrigado pela sua avaliação!!!", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    avaliacao.setUsuarioNome("");
                    avaliacao.setDescricao("");
                    avaliacao.setNota("");
                    finish();
                    bancoController.excluir(avaliacao);
                Toast.makeText(AvaliacaoActivity.this, "Avaliação deletada :( \n" + "Caso queira nos avaliar, estamos esperando!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_voltarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}