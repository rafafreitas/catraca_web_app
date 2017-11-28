package com.example.rafael.catraca_web_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import basic.Auth;
import basic.Usuario;


public class HomeActivity extends AppCompatActivity {
    private Auth auth; //SingleUser


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        auth = Auth.getInstance();
        Usuario usuario = new Usuario();
        usuario = auth.getUsuario();
        Toast.makeText(getApplicationContext(), usuario.getUser_data_nasc(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
