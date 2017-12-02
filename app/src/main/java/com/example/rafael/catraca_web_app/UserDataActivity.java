package com.example.rafael.catraca_web_app;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import basic.Auth;
import basic.Usuario;

public class UserDataActivity extends AppCompatActivity {

    private Auth auth; //SingleUser


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        initToolbar();

        auth = Auth.getInstance();
        Usuario usuario = new Usuario();
        usuario = auth.getUsuario();

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
}
