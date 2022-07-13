package com.moringaschool.myproperty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.models.Tenant;

import java.util.List;

public class TenantRecAdapter extends RecyclerView.Adapter<TenantRecAdapter.myHolder> {
    List<Tenant> allTenants;
    Context context;

    public TenantRecAdapter(List<Tenant> allTenants, Context context) {
        this.allTenants = allTenants;
        this.context = context;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.property, parent, false);
        return new myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.setData(allTenants.get(position));
    }

    @Override
    public int getItemCount() {
        return allTenants.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView name, description, occupied;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.propertyName);
            description =  itemView.findViewById(R.id.propertyDescription);
            occupied = itemView.findViewById(R.id.occupied);
        }

        public void setData(Tenant tenant){
            name.setText(tenant.getTenant_name());
            description.setText(tenant.getTenant_phone());
            occupied.setText(tenant.getJoin_date());
        }
    }
}
