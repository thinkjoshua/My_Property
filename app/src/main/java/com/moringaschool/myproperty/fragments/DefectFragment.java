package com.moringaschool.myproperty.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.databinding.FragmentDefectBinding;
import com.moringaschool.myproperty.models.Constants;
import com.moringaschool.myproperty.models.Defect;
import com.moringaschool.myproperty.models.Tenant;
import com.moringaschool.myproperty.ui.TenantDashboardActivity;
import com.moringaschool.myproperty.ui.TenantLoginActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class DefectFragment extends Fragment {
    FragmentDefectBinding defBind;
    Defect defect;
    DatabaseReference ref;
    Tenant tenant;




    public DefectFragment() {
        // Required empty public constructor
    }


    public static DefectFragment newInstance(Defect defect) {
        DefectFragment fragment = new DefectFragment();
        Bundle args = new Bundle();
        args.putSerializable("defect", (Serializable) defect);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            defect = (Defect) getArguments().getSerializable("defect");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        defBind = FragmentDefectBinding.inflate(inflater, container, false);
        View v = defBind.getRoot();

        Picasso.get().load(defect.getString_uri()).into(defBind.animationView);
        defBind.catName.setText(defect.getDescription());
        defBind.questType.setText(defect.getProperty_name());

        ref = FirebaseDatabase.getInstance().getReference().child("Tenants");

        Query query = ref.orderByChild("tenant_id").equalTo(defect.getTenant_id());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    String tenantName = snapshot.child(defect.getTenant_id()).child("tenant_name").getValue(String.class);
                    String tenantPhone = snapshot.child(defect.getTenant_id()).child("tenant_phone").getValue(String.class);
                    String tenantEmail = snapshot.child(defect.getTenant_id()).child("tenant_email").getValue(String.class);

                    defBind.questAns.setText(tenantName);
                    defBind.questMode.setText(tenantPhone);
                    defBind.location.setText(tenantEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError t) {
                String error = t.getMessage();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}