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
import com.example.oespartner.Activity.UpdatePartnerPersonActivity;
import com.example.oespartner.model.PartnerPersonModel;
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


public class PartnerPersonAdapter extends RecyclerView.Adapter<PartnerPersonAdapter.MyviewHolder> {
    Context context;
    List<PartnerPersonModel> partnerPersonModels;


    public PartnerPersonAdapter(Context context, List<PartnerPersonModel> partnerPersonModels) {
        this.context = context;
        this.partnerPersonModels = partnerPersonModels;
    }

    public void setPartnerPersonList(List<PartnerPersonModel> partnerPersonModels) {
        this.partnerPersonModels = partnerPersonModels;
        notifyDataSetChanged();
    }


    @Override
    public PartnerPersonAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_partner_person,parent,false);
        return new MyviewHolder(view);
    }




    @Override
    public void onBindViewHolder(PartnerPersonAdapter.MyviewHolder holder, int position) {
        holder.txtId.setText(partnerPersonModels.get(position).getId());
        holder.txtName.setText(partnerPersonModels.get(position).getPerson_name());
        holder.txtPhone.setText(partnerPersonModels.get(position).getPhone());
        holder.txtEmail.setText(partnerPersonModels.get(position).getEmail());

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
                            deletePartnerPerson(partnerPersonModels.get(position).getId());
                            notifyItemRemoved(position);
                            break;

                        case R.id.edit:
                            Log.e("id", partnerPersonModels.get(position).getId());
                            editPartnerPerson(partnerPersonModels.get(position).getId());


                    }
                    return true;

                });


                popupmenu.show();
            }

        });

    }

    @Override
    public int getItemCount() {
        if(partnerPersonModels != null){
            return partnerPersonModels.size();
        }
        return partnerPersonModels.size();

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtId,txtName,txtPhone,txtEmail;
        ImageView btn_popup;
        public MyviewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }

    public void deletePartnerPerson(String person_id) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<PartnerPersonModel> call = apiService.DeletePartnerPerson(person_id);
        call.enqueue(new Callback<PartnerPersonModel>() {
            @Override
            public void onResponse(Call<PartnerPersonModel> call, Response<PartnerPersonModel> response) {
            }

            @Override
            public void onFailure(Call<PartnerPersonModel> call, Throwable t) {
            }
        });
    }

    public void editPartnerPerson(String id) {
        String editVisitorGatePass_url = "http://oestech.com/index.php/home_api/update_person_master";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, editVisitorGatePass_url, response -> {
            Log.e("response", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Log.e("response1",jsonObject.toString());
                    Intent intent=new Intent(context, UpdatePartnerPersonActivity.class);
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