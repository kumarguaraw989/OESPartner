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

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oespartner.Activity.UpdateMaterialgatepassActivity;
import com.example.oespartner.Activity.UpdateVisitorGatePassActivity;
import com.example.oespartner.Model.AddMaterialGatePassModel;
import com.example.oespartner.Model.MaterialGatePassModel;
import com.example.oespartner.Model.VisitorGatePassModel;
import com.example.oespartner.Model.WorkGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MaterialGatePassAdapter extends RecyclerView.Adapter<MaterialGatePassAdapter.MyviewHolder> {
    Context context;
    List<MaterialGatePassModel> materialGatePassModels;


    public MaterialGatePassAdapter(Context context, List<MaterialGatePassModel> materialGatePassModels) {
        this.context = context;
        this.materialGatePassModels = materialGatePassModels;
    }

    public void setMaterialGatePassList(List<MaterialGatePassModel> materialGatePassModels) {
        this.materialGatePassModels = materialGatePassModels;
        notifyDataSetChanged();
    }


    @Override
    public MaterialGatePassAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_material_gatepass, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(MaterialGatePassAdapter.MyviewHolder holder, int position) {
        holder.txtId.setText(materialGatePassModels.get(position).getId());
        holder.txtDateTime.setText(materialGatePassModels.get(position).getDate_time());
        holder.txtCategory.setText(materialGatePassModels.get(position).getMaterial_category());
        holder.VehicleNo.setText(materialGatePassModels.get(position).getVehicle_no());
        holder.txtType.setText(materialGatePassModels.get(position).getType_of_material());
        holder.txtReason.setText(materialGatePassModels.get(position).getReason());
        holder.txtStatus.setText(materialGatePassModels.get(position).getStatus());
        holder.btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupmenu = new PopupMenu(context, holder.btn_popup);

                popupmenu.getMenuInflater().inflate(R.menu.visitor_menu, popupmenu.getMenu());
                popupmenu.getMenu().findItem(R.id.edit).setVisible(true);
                popupmenu.getMenu().findItem(R.id.delete).setVisible(true);
                popupmenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            deleteMaterialGatePass(materialGatePassModels.get(position).getId());
                            notifyItemRemoved(position);
                            break;

                        case R.id.edit:

                            Log.e("id", materialGatePassModels.get(position).getId());
                            editMaterialGatePass(materialGatePassModels.get(position).getId());

                    }
                    return true;

                });


                popupmenu.show();
            }

        });

    }

    @Override
    public int getItemCount() {
        if (materialGatePassModels != null) {
            return materialGatePassModels.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtDateTime, txtCategory, VehicleNo, txtType, txtReason, txtStatus;
        ImageView btn_popup;

        public MyviewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            VehicleNo = itemView.findViewById(R.id.VehicleNo);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtReason = (TextView) itemView.findViewById(R.id.txtReason);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }

    public void deleteMaterialGatePass(String id) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<MaterialGatePassModel> call = apiService.DeleteMaterialGatePass(id);
        call.enqueue(new Callback<MaterialGatePassModel>() {
            @Override
            public void onResponse(Call<MaterialGatePassModel> call, Response<MaterialGatePassModel> response) {
            }
            @Override
            public void onFailure(Call<MaterialGatePassModel> call, Throwable t) {
            }
        });
    }
    public void editMaterialGatePass(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.editVisitorGatePass_url, response -> {
            Log.e("response", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.e("response1", jsonObject.toString());
                    Intent intent = new Intent(context, UpdateMaterialgatepassActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("response", jsonObject.toString());
                    context.startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error",error.getMessage())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}