package com.example.oespartner.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oespartner.R;
import com.example.oespartner.model.TableModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyviewHolder> {
    private Context context;
    ArrayList<TableModel> tableModels = new ArrayList();


    public TableAdapter(Context context, ArrayList<TableModel> tableModels) {
        this.context = context;
        this.tableModels = tableModels;
    }

    public void setTableModelsList(ArrayList<TableModel> tableModels) {
        this.tableModels = tableModels;
        notifyDataSetChanged();
    }

    public ArrayList<TableModel> getData() {
        new Gson().toJson(tableModels);
        return tableModels;
    }


    @Override
    public TableAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chamber_add, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(TableAdapter.MyviewHolder holder, int position) {
        TableModel model = tableModels.get(position);
        holder.materialName.setText(model.getMaterialName());
        holder.specification.setText(model.getSpecification());
        holder.quantity.setText(model.getQuantity());
        holder.unit.setText(model.getUnit());

        if (position == 0) {
            holder.remove.setVisibility(View.GONE);
            holder.add.setVisibility(View.VISIBLE);
        } else {
            holder.remove.setVisibility(View.VISIBLE);
            holder.add.setVisibility(View.GONE);
        }


        holder.add.setOnClickListener(v -> {
            tableModels.add(tableModels.size() - 1, new TableModel("", "", "", ""));

            holder.materialName.setText(tableModels.get(position).getMaterialName());
            holder.specification.setText(tableModels.get(position).getSpecification());
            holder.unit.setText(tableModels.get(position).getUnit());
            holder.quantity.setText(tableModels.get(position).getQuantity());
            notifyItemInserted(tableModels.size() - 1);
        });

        holder.remove.setOnClickListener(v -> {
            holder.add.setVisibility(View.GONE);


            tableModels.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });


        holder.materialName.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tableModels.get(holder.getAdapterPosition()).setMaterialName(holder.materialName.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.specification.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tableModels.get(holder.getAdapterPosition()).setSpecification(holder.specification.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.unit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tableModels.get(holder.getAdapterPosition()).setUnit(holder.unit.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.quantity.clearComposingText();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tableModels.get(holder.getAdapterPosition()).setQuantity(holder.quantity.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return tableModels.size();
    }

    static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView materialName, specification, unit, quantity;
        ImageView add, remove;

        MyviewHolder(View itemView) {
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
