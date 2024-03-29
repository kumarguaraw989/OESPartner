package com.example.oespartner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
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

public class WorkgatepassActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView imgAdd, imgBack;
    RecyclerView recyclerview;
    WorkGatePassAdapter workGatePassAdapter;
    List<WorkGatePassModel> workGatePassModels;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workgatepass);
        getSupportActionBar().hide();
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        workGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        workGatePassAdapter = new WorkGatePassAdapter(getApplicationContext(), workGatePassModels);
        recyclerview.setAdapter(workGatePassAdapter);


        imgAdd.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            startActivityForResult(
                    new Intent(WorkgatepassActivity.this, AddWorkgatepassActivity.class), 2222
            );
        });
        swipeRefreshLayout.setRefreshing(true);
        callApi();
    }

    private void callApi() {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<List<WorkGatePassModel>> call = apiService.WorkGatePass(data_model.getEmail(), data_model.getRole());

        call.enqueue(new Callback<List<WorkGatePassModel>>() {
            @Override
            public void onResponse(Call<List<WorkGatePassModel>> call, Response<List<WorkGatePassModel>> response) {
                workGatePassModels = response.body();
                Log.d("TAG", "Response success = " + workGatePassModels);
                workGatePassAdapter.setWorkGatePassList(workGatePassModels);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<WorkGatePassModel>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
//        workGatePassModels.clear();
        callApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 2222) {
            swipeRefreshLayout.setRefreshing(true);
            workGatePassModels.clear();
            callApi();
        }
    }
}
