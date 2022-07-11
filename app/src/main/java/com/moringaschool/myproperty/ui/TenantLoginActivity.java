package com.moringaschool.myproperty.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.databinding.ActivityTenantLoginBinding;

public class TenantLoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityTenantLoginBinding tenantBinding;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tenantBinding = ActivityTenantLoginBinding.inflate(getLayoutInflater());
        setContentView(tenantBinding.getRoot());

        ref = FirebaseDatabase.getInstance().getReference().child("Tenants");
        tenantBinding.login.setOnClickListener(this);

        //AUTHENTICATE TENANT
        //START NEW INTENT IF SUCCESSFULL

    }

    @Override
    public void onClick(View v) {
        if (v == tenantBinding.login){

            String tenantEmail = tenantBinding.userEmail.getEditText().getText().toString().trim();
            String tenantPassword = tenantBinding.password.getEditText().getText().toString().trim();

            Query query = ref.orderByChild("tenant_email").equalTo(tenantEmail);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        Toast.makeText(TenantLoginActivity.this, snapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }
}