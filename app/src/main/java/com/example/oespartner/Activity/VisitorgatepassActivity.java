package com.example.oespartner.Activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.VisitorGatePassAdapter;
import com.example.oespartner.model.Data;
import com.example.oespartner.model.VisitorGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class VisitorgatepassActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView imgAdd, imgBack;
    RecyclerView recyclerview;
    VisitorGatePassAdapter visitorGatePassAdapter;
    List<VisitorGatePassModel> visitorGatePassModels;
     SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorgatepass);
        FastSave.init(VisitorgatepassActivity.this);
        getSupportActionBar().hide();
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        imgBack.setOnClickListener(v -> onBackPressed());
        FastSave.init(VisitorgatepassActivity.this);
        visitorGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        visitorGatePassAdapter = new VisitorGatePassAdapter(getApplicationContext(), visitorGatePassModels);
        recyclerview.setAdapter(visitorGatePassAdapter);

        imgAdd.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(VisitorgatepassActivity.this, AddVisitorgatepassActivity.class),1111
            );
        });
                swipeRefreshLayout.setRefreshing(true);
               callApi();
             }

    private void callApi() {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Log.e("email", data_model.getEmail());
        Log.e("role", data_model.getRole());
        Call<List<VisitorGatePassModel>> call = apiService.VisitorGatePass(data_model.getEmail(), data_model.getRole());
        call.enqueue(new Callback<List<VisitorGatePassModel>>() {
            @Override
            public void onResponse(Call<List<VisitorGatePassModel>> call, Response<List<VisitorGatePassModel>> response) {
                visitorGatePassModels= response.body();
                Log.d("TAG", "Response success = " + visitorGatePassModels);
                visitorGatePassAdapter.setVisitorGatePassList(visitorGatePassModels);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<VisitorGatePassModel>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
//        visitorGatePassModels.clear();
        callApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 1111) {
            swipeRefreshLayout.setRefreshing(true);
            visitorGatePassModels.clear();
            callApi();
        }
    }
}
