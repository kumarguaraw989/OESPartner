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
import com.example.oespartner.Adapter.MaterialGatePassAdapter;
import com.example.oespartner.model.Data;
import com.example.oespartner.model.MaterialGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MaterialGatepassActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView imgAdd, imgBack;
    RecyclerView recyclerview;
    MaterialGatePassAdapter materialGatePassAdapter;
    List<MaterialGatePassModel> materialGatePassModels;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_gatepass);
        getSupportActionBar().hide();
        imgAdd = findViewById(R.id.imgAdd);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
         materialGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        materialGatePassAdapter = new MaterialGatePassAdapter(getApplicationContext(), materialGatePassModels);
        recyclerview.setAdapter(materialGatePassAdapter);

        imgAdd.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            startActivityForResult(
                    new Intent(MaterialGatepassActivity.this, AddMaterialgatepassActivity.class), 3333
            );
        });
        swipeRefreshLayout.setRefreshing(true);
        callApi();

     }

    private void callApi() {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<List<MaterialGatePassModel>> call = apiService.MaterialGatePass(data_model.getEmail(), data_model.getRole());
        call.enqueue(new Callback<List<MaterialGatePassModel>>() {
            @Override
            public void onResponse(Call<List<MaterialGatePassModel>> call, Response<List<MaterialGatePassModel>> response) {
                materialGatePassModels = response.body();
                Log.d("TAG", "Response success = " + materialGatePassModels);
                materialGatePassAdapter.setMaterialGatePassList(materialGatePassModels);
                recyclerview.setAdapter(materialGatePassAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<MaterialGatePassModel>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
//        materialGatePassModels.clear();
        callApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 3333) {
            swipeRefreshLayout.setRefreshing(true);
            materialGatePassModels.clear();
            callApi();
        }
    }
}
