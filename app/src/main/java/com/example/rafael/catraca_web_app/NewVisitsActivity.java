package com.example.rafael.catraca_web_app;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import basic.Auth;
import util.Internet;

public class NewVisitsActivity extends AppCompatActivity {
    FloatingActionButton fab;
    CoordinatorLayout rootLayout;

    private Button btnCadastro;
    private boolean flag = false;

    private Internet internet;
    private Auth auth; //SingleUser

    Animation show_fab;
    Animation hide_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visits);

        rootLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        hide_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);

        initToolbar();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                //FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();

                if (flag == false) {
                    //Display FAB menu
                    expandFAB();
                    flag = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    flag = false;
                }

            }
        });
    }

    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin += (int) (fab.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab.getHeight() * 0.25);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(show_fab);
        fab.setClickable(true);

//        //Floating Action Button 2
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab.getLayoutParams();
//        layoutParams2.rightMargin += (int) (fab.getWidth() * 1.5);
//        layoutParams2.bottomMargin += (int) (fab.getHeight() * 1.5);
//        fab.setLayoutParams(layoutParams2);
//        fab.startAnimation(show_fab_2);
//        fab.setClickable(true);
//
//        //Floating Action Button 3
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab.getLayoutParams();
//        layoutParams3.rightMargin += (int) (fab.getWidth() * 0.25);
//        layoutParams3.bottomMargin += (int) (fab.getHeight() * 1.7);
//        fab.setLayoutParams(layoutParams3);
//        fab.startAnimation(show_fab_3);
//        fab.setClickable(true);
    }

    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab.getHeight() * 0.25);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(hide_fab);
        fab.setClickable(false);

//        //Floating Action Button 2
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab.getLayoutParams();
//        layoutParams2.rightMargin -= (int) (fab.getWidth() * 1.5);
//        layoutParams2.bottomMargin -= (int) (fab.getHeight() * 1.5);
//        fab.setLayoutParams(layoutParams2);
//        fab.startAnimation(hide_fab_2);
//        fab.setClickable(false);
//
//        //Floating Action Button 3
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab.getLayoutParams();
//        layoutParams3.rightMargin -= (int) (fab.getWidth() * 0.25);
//        layoutParams3.bottomMargin -= (int) (fab.getHeight() * 1.7);
//        fab.setLayoutParams(layoutParams3);
//        fab.startAnimation(hide_fab_3);
//        fab.setClickable(false);
    }


    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

}
