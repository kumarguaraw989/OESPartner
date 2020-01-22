package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.MaterialGatePassAdapter;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.MaterialGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialGatepassActivity extends AppCompatActivity {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    MaterialGatePassAdapter materialGatePassAdapter;
    List<MaterialGatePassModel> materialGatePassModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_gatepass);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());


        materialGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        materialGatePassAdapter = new MaterialGatePassAdapter(getApplicationContext(),materialGatePassModels);
        recyclerview.setAdapter(materialGatePassAdapter);
        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MaterialGatepassActivity.this, AddMaterialgatepassActivity.class);
                startActivity(i);
            }
        });

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<MaterialGatePassModel>> call = apiService.MaterialGatePass(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<MaterialGatePassModel>>() {
            @Override
            public void onResponse(Call<List<MaterialGatePassModel>> call, Response<List<MaterialGatePassModel>> response) {
                materialGatePassModels = response.body();
                Log.d("TAG","Response success = "+materialGatePassModels);
                materialGatePassAdapter.setMaterialGatePassList(materialGatePassModels);
            }

            @Override
            public void onFailure(Call<List<MaterialGatePassModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
