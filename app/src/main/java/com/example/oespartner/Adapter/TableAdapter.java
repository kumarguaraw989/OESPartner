package com.example.oespartner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oespartner.Activity.UpdatePartnerPersonActivity;
import com.example.oespartner.model.PartnerPersonModel;
import com.example.oespartner.R;


import com.example.oespartner.model.TableModel;




 import java.util.List;




public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyviewHolder> {
    Context context;
    List<TableModel> tableModels;


    public TableAdapter(Context context, List<TableModel> tableModels) {
        this.context = context;
        this.tableModels = tableModels;
    }

    public void setTableModelsList(List<TableModel> tableModels) {
        this.tableModels = tableModels;
        notifyDataSetChanged();
    }


    @Override
    public TableAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chamber_add, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(TableAdapter.MyviewHolder holder, int position) {
        holder.materialName.setText(tableModels.get(position).getMaterialName());
        holder.specification.setText(tableModels.get(position).getSpecification());
        holder.unit.setText(tableModels.get(position).getUnit());
        holder.quantity.setText(tableModels.get(position).getQuantity());
        holder.add.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            tableModels.remove(pos);
            TableAdapter.this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        if (tableModels != null) {
            return tableModels.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView materialName, specification, unit, quantity;
        ImageView add, remove;

        public MyviewHolder(View itemView) {
            super(itemView);
            materialName = itemView.findViewById(R.id.et_materialName);
            specification = itemView.findViewById(R.id.et_specification);
            unit = itemView.findViewById(R.id.et_Unit);
            quantity = itemView.findViewById(R.id.et_qty);
            add = itemView.findViewById(R.id.btn_add);
            remove = itemView.findViewById(R.id.btn_remove);
        }
    }
}