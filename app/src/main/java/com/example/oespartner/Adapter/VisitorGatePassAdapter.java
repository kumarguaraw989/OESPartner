package com.example.oespartner.Adapter;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oespartner.Activity.AddVisitorgatepassActivity;
import com.example.oespartner.Activity.HomeActivity;
import com.example.oespartner.Activity.UpdateVisitorGatePassActivity;
import com.example.oespartner.Activity.VisitorgatepassActivity;
import com.example.oespartner.Model.AddVisitorGatePassModel;

import com.example.oespartner.Model.VisitorGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VisitorGatePassAdapter extends RecyclerView.Adapter<VisitorGatePassAdapter.MyviewHolder> {
    Context context;
    List<VisitorGatePassModel> visitorGatePassModels;


    public VisitorGatePassAdapter(Context context, List<VisitorGatePassModel> visitorGatePassModels) {
        this.context = context;
        this.visitorGatePassModels = visitorGatePassModels;

    }

    public void setVisitorGatePassList(List<VisitorGatePassModel> visitorGatePassModels) {
        this.visitorGatePassModels = visitorGatePassModels;
        notifyDataSetChanged();
    }


    @Override
    public VisitorGatePassAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visitor_gatepass, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(VisitorGatePassAdapter.MyviewHolder holder, int position) {
        holder.txtClientId.setText(visitorGatePassModels.get(position).getClient());
        holder.txtBranchId.setText(visitorGatePassModels.get(position).getBranch());
        holder.txtPersonId.setText(visitorGatePassModels.get(position).getPerson_id());
        holder.txtPersonName.setText(visitorGatePassModels.get(position).getPerson_name());
        holder.txtValidUpto.setText(visitorGatePassModels.get(position).getVisit_date());
        holder.txtStatus.setText(visitorGatePassModels.get(position).getStatus());

        holder.btn_popup.setOnClickListener(v -> {
            PopupMenu popupmenu = new PopupMenu(context, holder.btn_popup);

            popupmenu.getMenuInflater().inflate(R.menu.visitor_menu, popupmenu.getMenu());
            popupmenu.getMenu().findItem(R.id.edit).setVisible(true);
            popupmenu.getMenu().findItem(R.id.delete).setVisible(true);
            popupmenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.delete:
                        deleteGatePass(visitorGatePassModels.get(position).getId());
                        notifyItemRemoved(position);
                        break;

                    case R.id.edit:
                        Log.e("id", visitorGatePassModels.get(position).getId());
                        editVisitorGatePass(visitorGatePassModels.get(position).getId());


                }
                return true;

            });


            popupmenu.show();
        });

    }

    @Override
    public int getItemCount() {
        if (visitorGatePassModels != null) {
            return visitorGatePassModels.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtClientId, txtBranchId, txtPersonId, txtPersonName, txtValidUpto, txtStatus;
        ImageView btn_popup;

        public MyviewHolder(View itemView) {
            super(itemView);
            txtClientId = (TextView) itemView.findViewById(R.id.txtClientId);
            txtBranchId = (TextView) itemView.findViewById(R.id.txtBranchId);
            txtPersonId = (TextView) itemView.findViewById(R.id.txtPersonId);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtValidUpto = (TextView) itemView.findViewById(R.id.txtValidUpto);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }

    public void deleteGatePass(String person_id) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<VisitorGatePassModel> call = apiService.DeleteVisitorGatePass(person_id);
        call.enqueue(new Callback<VisitorGatePassModel>() {
            @Override
            public void onResponse(Call<VisitorGatePassModel> call, Response<VisitorGatePassModel> response) {
                Log.e("response-->",response.toString());

            }

            @Override
            public void onFailure(Call<VisitorGatePassModel> call, Throwable t) {
            }
        });
    }
    public void editVisitorGatePass(String id) {
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.editVisitorGatePass_url, response -> {
             Log.e("response", response);
             try {
                 JSONArray jsonArray = new JSONArray(response);
                 for(int i=0;i<jsonArray.length();i++){
                     JSONObject jsonObject=jsonArray.getJSONObject(i);
                     Log.e("response1",jsonObject.toString());
                     Intent intent=new Intent(context,UpdateVisitorGatePassActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     intent.putExtra("response",jsonObject.toString());
                     context.startActivity(intent);

                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }, error -> {

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