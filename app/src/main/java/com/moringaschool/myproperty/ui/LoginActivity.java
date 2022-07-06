package com.moringaschool.myproperty.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityLoginBinding logBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        logBind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(logBind.getRoot());

        logBind.addManager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == logBind.addManager){
            Intent intent = new Intent(LoginActivity.this, AddManagerActivity.class);
            startActivity(intent);
        }
    }
}