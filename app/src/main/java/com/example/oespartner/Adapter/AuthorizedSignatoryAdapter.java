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
import com.example.oespartner.Activity.UpdateAuthorizedSignatoryActivity;
import com.example.oespartner.model.AuthorizedSignatoryModel;
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


public class AuthorizedSignatoryAdapter extends RecyclerView.Adapter<AuthorizedSignatoryAdapter.MyviewHolder> {
    Context context;
    List<AuthorizedSignatoryModel> authorizedSignatoryModels;


    public AuthorizedSignatoryAdapter(Context context, List<AuthorizedSignatoryModel> authorizedSignatoryModels) {
        this.context = context;
        this.authorizedSignatoryModels = authorizedSignatoryModels;
    }

    public void setAuthorizedSignatoryList(List<AuthorizedSignatoryModel> authorizedSignatoryModels) {
        this.authorizedSignatoryModels = authorizedSignatoryModels;
        notifyDataSetChanged();
    }


    @Override
    public AuthorizedSignatoryAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_authorised_signatory,parent,false);
        return new MyviewHolder(view);
    }




    @Override
    public void onBindViewHolder(AuthorizedSignatoryAdapter.MyviewHolder holder, int position) {
        holder.txtClientId.setText(authorizedSignatoryModels.get(position).getClient());
        holder.txtBranchId.setText(authorizedSignatoryModels.get(position).getBranch());
        holder.txtPersonName.setText(authorizedSignatoryModels.get(position).getPerson_name());
        holder.txtValidity.setText(authorizedSignatoryModels.get(position).getValid_upto());

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
                            deleteGatePass(authorizedSignatoryModels.get(position).getId());
                            notifyItemRemoved(position);
                            break;

                        case R.id.edit:
                            //Log.e("id", authorizedSignatoryModels.get(position).getId());
                            editAuthorizedSignatory(authorizedSignatoryModels.get(position).getId());


                    }
                    return true;

                });


                popupmenu.show();
            }

        });

    }

    @Override
    public int getItemCount() {
        if(authorizedSignatoryModels != null){
            return authorizedSignatoryModels.size();
        }
        return authorizedSignatoryModels.size();

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtClientId,txtBranchId,txtPersonName,txtValidity;

        ImageView btn_popup;
        public MyviewHolder(View itemView) {
            super(itemView);
            txtClientId = (TextView) itemView.findViewById(R.id.txtClientId);
            txtBranchId = (TextView) itemView.findViewById(R.id.txtBranchId);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtValidity = (TextView) itemView.findViewById(R.id.txtValidity);

            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }

    public void deleteGatePass(String person_id) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AuthorizedSignatoryModel> call = apiService.DeleteAuthorized( person_id);
        call.enqueue(new Callback<AuthorizedSignatoryModel>() {
            @Override
            public void onResponse(Call<AuthorizedSignatoryModel> call, Response<AuthorizedSignatoryModel> response) {
            }

            @Override
            public void onFailure(Call<AuthorizedSignatoryModel> call, Throwable t) {
            }
        });
    }

    public void editAuthorizedSignatory(String id) {
        String editVisitorGatePass_url = "http://oestech.com/index.php/home_api/get_authorised_signatory";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, editVisitorGatePass_url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Log.e("response1",jsonObject.toString());
                        Intent intent=new Intent(context, UpdateAuthorizedSignatoryActivity.class);
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