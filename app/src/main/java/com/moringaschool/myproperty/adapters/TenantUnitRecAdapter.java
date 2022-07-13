package com.moringaschool.myproperty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.models.Unit;

import java.util.List;

public class TenantUnitRecAdapter extends RecyclerView.Adapter<TenantUnitRecAdapter.myHolder> {
    List<Unit> allUnits;
    Context cont;

    public TenantUnitRecAdapter(List<Unit> allUnits, Context cont) {
        this.allUnits = allUnits;
        this.cont = cont;
    }

    @NonNull
    @Override
    public TenantUnitRecAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.activity_unit, parent, false);
        return new myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TenantUnitRecAdapter.myHolder holder, int position) {
        holder.setData(allUnits.get(position));

    }

    @Override
    public int getItemCount() {

        return allUnits.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView name, tenantName1, tenantPhone2, unitRooms;
        ImageView add, remove;



        public myHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.quest_type);
            tenantName1 = itemView.findViewById(R.id.quest_ans);
            tenantPhone2 = itemView.findViewById(R.id.quest_mode);
            unitRooms = itemView.findViewById(R.id.location);
            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);

            add.setVisibility(View.GONE);

        }

        public void setData(Unit unit) {
            name.setText(unit.getUnit_name());
            tenantName1.setText("Vacant");
            tenantPhone2.setVisibility(View.GONE);
            unitRooms.setText("This unit contains " + unit.getUnit_rooms() + " rooms");
        }
    }
}
