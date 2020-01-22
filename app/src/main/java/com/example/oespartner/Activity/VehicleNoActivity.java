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
import com.example.oespartner.Adapter.VehicleNoAdapter;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.TransportModel;
import com.example.oespartner.Model.VehicleNoModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleNoActivity extends AppCompatActivity {

    ImageView imgAdd,imgBack;
    RecyclerView recyclerview;
    VehicleNoAdapter vehicleNoAdapter;
    List<VehicleNoModel> vehicleNoModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_no);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());

        vehicleNoModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        vehicleNoAdapter = new VehicleNoAdapter(getApplicationContext(),vehicleNoModels);
        recyclerview.setAdapter(vehicleNoAdapter);
        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(VehicleNoActivity.this, AddVehicleNoActivity.class);
                startActivity(i);
            }
        });

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<VehicleNoModel>> call = apiService.VehicleNo(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<VehicleNoModel>>() {
            @Override
            public void onResponse(Call<List<VehicleNoModel>> call, Response<List<VehicleNoModel>> response) {
                vehicleNoModels = response.body();
                Log.d("TAG","Response success = "+vehicleNoModels);
                vehicleNoAdapter.setVehicleNoList(vehicleNoModels);
            }

            @Override
            public void onFailure(Call<List<VehicleNoModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}



