package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.WorkGatePassAdapter;
import com.example.oespartner.model.Data;
import com.example.oespartner.model.WorkGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkgatepassActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    WorkGatePassAdapter workGatePassAdapter;
    List<WorkGatePassModel> workGatePassModels;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workgatepass);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        workGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        workGatePassAdapter = new WorkGatePassAdapter(getApplicationContext(),workGatePassModels);
        recyclerview.setAdapter(workGatePassAdapter);
        swipeRefreshLayout.setRefreshing(false);

        imgAdd.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(WorkgatepassActivity.this, AddWorkgatepassActivity.class);
            startActivity(i);
        });

             swipeRefreshLayout.setRefreshing(true);
            RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
            Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
            Call<List<WorkGatePassModel>> call = apiService.WorkGatePass(data_model.getEmail(),data_model.getRole());

            call.enqueue(new Callback<List<WorkGatePassModel>>() {
                @Override
                public void onResponse(Call<List<WorkGatePassModel>> call, Response<List<WorkGatePassModel>> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    workGatePassModels = response.body();
                    Log.d("TAG","Response success = "+workGatePassModels);
                    workGatePassAdapter.setWorkGatePassList(workGatePassModels);
                }
                @Override
                public void onFailure(Call<List<WorkGatePassModel>> call, Throwable t) {
                    Log.d("TAG","Response = "+t.toString());
                }
            });


    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
         workGatePassModels.clear();
     }
}
