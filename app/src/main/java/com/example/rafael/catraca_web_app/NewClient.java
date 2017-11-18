package com.example.rafael.catraca_web_app;

/**
 * Created by rafael on 17/11/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        final TextView txtView = this.findViewById(R.id.isClient);
        txtView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewClient.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
