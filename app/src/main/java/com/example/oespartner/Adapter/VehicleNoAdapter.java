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
import com.example.oespartner.Activity.UpdateTransportActivity;
import com.example.oespartner.Activity.UpdateVehicleNoActivity;
import com.example.oespartner.Model.TransportModel;
import com.example.oespartner.Model.VehicleNoModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
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


public class VehicleNoAdapter extends RecyclerView.Adapter<VehicleNoAdapter.MyviewHolder> {
    Context context;
    List<VehicleNoModel> vehicleNoModels;


    public VehicleNoAdapter(Context context, List<VehicleNoModel> vehicleNoModels) {
        this.context = context;
        this.vehicleNoModels = vehicleNoModels;
    }

    public void setVehicleNoList(List<VehicleNoModel> vehicleNoModels) {
        this.vehicleNoModels = vehicleNoModels;
        notifyDataSetChanged();
    }


    @Override
    public VehicleNoAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicleno,parent,false);
        return new MyviewHolder(view);
    }




    @Override
    public void onBindViewHolder(VehicleNoAdapter.MyviewHolder holder, int position) {
        holder.txtvNo.setText(vehicleNoModels.get(position).getVehicle_no());
        holder.txtvType.setText(vehicleNoModels.get(position).getVehicle_type());
        holder.txtStatus.setText(vehicleNoModels.get(position).getStatus());
        holder.btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupmenu = new PopupMenu(context, holder.btn_popup);

                popupmenu.getMenuInflater().inflate(R.menu.vehicle_no_menu, popupmenu.getMenu());
                popupmenu.getMenu().findItem(R.id.edit).setVisible(true);
                popupmenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
//                        case R.id.delete:
//                            deleteGatePass(vehicleNoModels.get(position).getId());
//                            notifyItemRemoved(position);
//                            break;

                        case R.id.edit:
                            Log.e("id", vehicleNoModels.get(position).getId());
                            editVehicleNo(vehicleNoModels.get(position).getId());


                    }
                    return true;

                });


                popupmenu.show();
            }

        });
    }

    @Override
    public int getItemCount() {
        if(vehicleNoModels != null){
            return vehicleNoModels.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtvNo,txtvType,txtStatus;
        ImageView btn_popup;
        public MyviewHolder(View itemView) {
            super(itemView);
            txtvNo = (TextView) itemView.findViewById(R.id.txtvNo);
            txtvType = (TextView) itemView.findViewById(R.id.txtvType);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);

            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }


//    public void deleteGatePass(String id) {
//        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
//        Call<TransportModel> call = apiService.DeleteTransport(id);
//        call.enqueue(new Callback<TransportModel>() {
//            @Override
//            public void onResponse(Call<TransportModel> call, Response<TransportModel> response) {
//            }
//
//            @Override
//            public void onFailure(Call<TransportModel> call, Throwable t) {
//            }
//        });
//    }

    public void editVehicleNo(String id) {
        String editVisitorGatePass_url = "http://oestech.com/management/vehicle_management/index.php/home_api/edit_vehicle";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, editVisitorGatePass_url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Log.e("response1",jsonObject.toString());
                        Intent intent=new Intent(context, UpdateVehicleNoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("response",jsonObject.toString());
                        context.startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


}