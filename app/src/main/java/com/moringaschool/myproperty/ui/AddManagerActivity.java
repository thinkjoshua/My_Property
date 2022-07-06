package com.moringaschool.myproperty.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.myproperty.databinding.AddPropertyManagerBinding;
import com.moringaschool.myproperty.api.ApiCalls;
import com.moringaschool.myproperty.api.RetrofitClient;
import com.moringaschool.myproperty.models.Property;
import com.moringaschool.myproperty.models.PropertyManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddManagerActivity extends AppCompatActivity implements View.OnClickListener{
    AddPropertyManagerBinding addBind;
    Call<PropertyManager> call1;
    Call<Property> call2;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBind = AddPropertyManagerBinding.inflate(getLayoutInflater());
        setContentView(addBind.getRoot());

        addBind.submit.setOnClickListener(this);
        myAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View v) {
        ApiCalls calls = RetrofitClient.getClient();

        String name = addBind.managerName.getEditText().getText().toString().trim();
        String number = addBind.managerPhone.getEditText().getText().toString().trim();
        String email = addBind.managerEmail.getEditText().getText().toString().trim();
        String propertyName = addBind.managerHouseName.getEditText().getText().toString().trim();
        String propertyDescription = addBind.propertyDescription.getEditText().getText().toString().trim();
        String password = addBind.propertyManagerPassword.getEditText().getText().toString().trim();

        PropertyManager manager = new PropertyManager(name, number, email, propertyName, propertyDescription);
        Property property = new Property(propertyName, name);


        call1 = calls.addManager(manager);
        call2 = calls.addProperty(property);
        call1.enqueue(new Callback<PropertyManager>() {
            @Override
            public void onResponse(Call<PropertyManager> call, Response<PropertyManager> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddManagerActivity.this, "Tunaelekea", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddManagerActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PropertyManager> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(AddManagerActivity.this, error, Toast.LENGTH_SHORT).show();

            }
        });

        call2.enqueue(new Callback<Property>() {
            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddManagerActivity.this, "Kuja Wewe", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(AddManagerActivity.this, "Sema Kabisa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(AddManagerActivity.this, error, Toast.LENGTH_SHORT).show();

            }
        });


        myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddManagerActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddManagerActivity.this, ManagerDashboardActivity.class);
                    intent.putExtra("managerName", name);
                    startActivity(intent);

                }

            }
        });

    }


}