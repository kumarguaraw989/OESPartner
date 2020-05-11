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
import com.example.oespartner.Activity.UpdateWorkgatepassActivity;
import com.example.oespartner.model.WorkGatePassModel;
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
public class WorkGatePassAdapter extends RecyclerView.Adapter<WorkGatePassAdapter.MyviewHolder> {
    Context context;
    List<WorkGatePassModel> workGatePassModels;
    public WorkGatePassAdapter(Context context, List<WorkGatePassModel> workGatePassModels) {
        this.context = context;
        this.workGatePassModels = workGatePassModels;
    }
    public void setWorkGatePassList(List<WorkGatePassModel> workGatePassModels) {
        this.workGatePassModels = workGatePassModels;
        notifyDataSetChanged();
    }
    @Override
    public WorkGatePassAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_work_gatepass,parent,false);
        return new MyviewHolder(view);
    }
    @Override
    public void onBindViewHolder(WorkGatePassAdapter.MyviewHolder holder, int position) {
        holder.txtClientId.setText(workGatePassModels.get(position).getClient());
        holder.txtBranchId.setText(workGatePassModels.get(position).getBranch());
        holder.txtStackHolderId.setText(workGatePassModels.get(position).getStakeholderId());
        holder.txtPersonId.setText(workGatePassModels.get(position).getPersonId());
        holder.txtPersonName.setText(workGatePassModels.get(position).getPersonName());
        holder.txtValidity.setText(workGatePassModels.get(position).getPValidUpto());
        holder.txtStatus.setText(workGatePassModels.get(position).getStatus());
        if (workGatePassModels.get(position).getStatus().equals("1")){
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.whiteTextColor));
            holder.txtStatus.setText("Approved");
        }
        else{
            holder.txtStatus.setText("UnApproved");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.whiteTextColor));
        }
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
                            deleteGatePass(workGatePassModels.get(position).getId());
                            notifyItemRemoved(position);
                            break;
                        case R.id.edit:
                            editWorkGatePass(workGatePassModels.get(position).getId());
                    }
                    return true;
                });
                popupmenu.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        if(workGatePassModels != null){
            return workGatePassModels.size();
        }
        return 0;
    }
    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView txtClientId,txtBranchId,txtStackHolderId,txtPersonId,txtPersonName,txtValidity,txtStatus;
        ImageView btn_popup;
        public MyviewHolder(View itemView) {
            super(itemView);
            txtClientId = (TextView) itemView.findViewById(R.id.txtClientId);
            txtBranchId = (TextView) itemView.findViewById(R.id.txtBranchId);
            txtStackHolderId = (TextView) itemView.findViewById(R.id.txtStackHolderId);
            txtPersonId = (TextView) itemView.findViewById(R.id.txtPersonId);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtValidity = (TextView) itemView.findViewById(R.id.txtValidity);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            btn_popup = itemView.findViewById(R.id.btn_popup);
        }
    }
    public void deleteGatePass(String person_id) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<WorkGatePassModel> call = apiService.DeleteWorkGatePass( person_id);
        call.enqueue(new Callback<WorkGatePassModel>() {
            @Override
            public void onResponse(Call<WorkGatePassModel> call, Response<WorkGatePassModel> response) {
            }

            @Override
            public void onFailure(Call<WorkGatePassModel> call, Throwable t) {
            }
        });
    }
    //    public void editWorkGatePass(String id) {
//        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
//        Call<AddWorkGatePassModel> call = apiService.EditWorkGatePass( id);
//        call.enqueue(new Callback<AddWorkGatePassModel>() {
//            @Override
//            public void onResponse(Call<AddWorkGatePassModel> call, Response<AddWorkGatePassModel> response) {
//
//                Log.e("TAG", "response : "+new Gson().toJson(response.body()) );
//                       Intent intent=new Intent(context, UpdateWorkgatepassActivity.class);
//                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("response",response.toString());
//                        context.startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<AddWorkGatePassModel> call, Throwable t) {
//            }
//        });
//    }
    public void editWorkGatePass(String id) {
        String editVisitorGatePass_url = "http://oestech.com/index.php/home_api/get_work_gatepass";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, editVisitorGatePass_url, response -> {
            Log.e("response", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Log.e("response1",jsonObject.toString());
                    Intent intent=new Intent(context, UpdateWorkgatepassActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("response",jsonObject.toString());
                    context.startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
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