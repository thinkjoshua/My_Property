package com.moringaschool.myproperty.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.myproperty.adapters.DefectRecAdapter;
import com.moringaschool.myproperty.databinding.ActivityPropertiesBinding;
import com.moringaschool.myproperty.adapters.PropertyRecAdapter;
import com.moringaschool.myproperty.api.ApiCalls;
import com.moringaschool.myproperty.api.RetrofitClient;
import com.moringaschool.myproperty.models.Constants;
import com.moringaschool.myproperty.models.Defect;
import com.moringaschool.myproperty.models.Property;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertiesActivity extends AppCompatActivity {
    ActivityPropertiesBinding properBind;
    List<Property> allProperties;
    PropertyRecAdapter adapter;
    Call<List<Property>> call1;
    SharedPreferences pref;
    DefectRecAdapter adp;
    DatabaseReference ref;
    List<Defect> allDefects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        properBind = ActivityPropertiesBinding.inflate(getLayoutInflater());
        setContentView(properBind.getRoot());

        ref = FirebaseDatabase.getInstance().getReference("defects");
        pref = PreferenceManager.getDefaultSharedPreferences(this);


        allProperties = new ArrayList<>();
        allDefects = new ArrayList<>();
        ApiCalls calls = RetrofitClient.getClient();

        call1 = calls.getManagerProperties(pref.getString(Constants.NAME, "charles"));

        call1.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                allProperties = response.body();
                adapter = new PropertyRecAdapter(allProperties, PropertiesActivity.this);
                properBind.myRec.setAdapter(adapter);
                properBind.myRec.setLayoutManager(new LinearLayoutManager(PropertiesActivity.this, RecyclerView.HORIZONTAL, false));
                properBind.myRec.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(PropertiesActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot != null){
                    for (DataSnapshot singleDefect: snapshot.getChildren()){
                        Defect defect = singleDefect.getValue(Defect.class);
                        allDefects.add(defect);
                    }
                }

                adp = new DefectRecAdapter(allDefects, PropertiesActivity.this);
                properBind.recView.setAdapter(adp);
                properBind.recView.setLayoutManager(new LinearLayoutManager(PropertiesActivity.this));
                properBind.recView.setHasFixedSize(true);
                adp.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError t) {
                String error = t.getMessage();
                Toast.makeText(PropertiesActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });








    }
}