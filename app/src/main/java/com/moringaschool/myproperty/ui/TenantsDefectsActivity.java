package com.moringaschool.myproperty.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.adapters.DefectRecAdapter;
import com.moringaschool.myproperty.api.ApiCalls;
import com.moringaschool.myproperty.api.RetrofitClient;
import com.moringaschool.myproperty.databinding.ActivityTenantsDefectsBinding;
import com.moringaschool.myproperty.models.Constants;
import com.moringaschool.myproperty.models.Defect;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantsDefectsActivity extends AppCompatActivity {
    ActivityTenantsDefectsBinding tenBind;
    SharedPreferences pref;
    String tenantName, tenantId;
    Call<List<Defect>> call;
    ApiCalls calls;
    List<Defect> allDefects;
    DefectRecAdapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tenBind = ActivityTenantsDefectsBinding.inflate(getLayoutInflater());
        setContentView(tenBind.getRoot());

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        tenantName = pref.getString(Constants.TENANT_NAME, "");
        tenantId = pref.getString(Constants.TENANT_ID, "");

        allDefects = new ArrayList<>();

        tenBind.managerName.setText("Hey "+ tenantName);

        calls = RetrofitClient.getClient();
        call = calls.tenantDefects(tenantId);

        call.clone().enqueue(new Callback<List<Defect>>() {
            @Override
            public void onResponse(Call<List<Defect>> call, Response<List<Defect>> response) {
                if (response.isSuccessful()){
                    allDefects = response.body();
                    adp = new DefectRecAdapter(allDefects, TenantsDefectsActivity.this);
                    tenBind.myRec.setAdapter(adp);
                    tenBind.myRec.setHasFixedSize(true);
                    tenBind.myRec.setLayoutManager(new LinearLayoutManager(TenantsDefectsActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<Defect>> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(TenantsDefectsActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });


    }
}