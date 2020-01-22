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
import com.example.oespartner.Adapter.TransportAdapter;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.TransportModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportActivity extends AppCompatActivity {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    TransportAdapter transportAdapter;
    List<TransportModel> transportModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());

        transportModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        transportAdapter = new TransportAdapter(getApplicationContext(),transportModels);
        recyclerview.setAdapter(transportAdapter);
        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(TransportActivity.this, AddTransportActivity.class);
                startActivity(i);
            }
        });

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<TransportModel>> call = apiService.Transport(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<TransportModel>>() {
            @Override
            public void onResponse(Call<List<TransportModel>> call, Response<List<TransportModel>> response) {
                transportModels = response.body();
                Log.d("TAG","Response success = "+transportModels);
                transportAdapter.setTransportList(transportModels);
            }

            @Override
            public void onFailure(Call<List<TransportModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
