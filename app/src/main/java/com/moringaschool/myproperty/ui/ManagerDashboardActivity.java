package com.moringaschool.myproperty.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.databinding.ActivityMainBinding;
import com.moringaschool.myproperty.databinding.ActivityManagerDashboardBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManagerDashboardActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityManagerDashboardBinding mainBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBind = ActivityManagerDashboardBinding.inflate(getLayoutInflater());
        setContentView(mainBind.getRoot());

        mainBind.listOfPropertiesCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mainBind.listOfPropertiesCard){
            startActivity(new Intent(ManagerDashboardActivity.this, PropertiesActivity.class));
        }else if (v == mainBind.maintenanceRequestCard){
            startActivity(new Intent(ManagerDashboardActivity.this, DefectActivity.class));
        }

    }
}