package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.PartnerPersonAdapter;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.PartnerCategory;
import com.example.oespartner.Model.PartnerPersonModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerPersonActivity extends AppCompatActivity {
    ImageView  imgAdd,imgBack;

    SearchView searchView;
     RecyclerView recyclerview;
    PartnerPersonAdapter partnerPersonAdapter;
    List<PartnerPersonModel> partnerPersonModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_person);
        getSupportActionBar().hide();
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        partnerPersonModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        partnerPersonAdapter = new PartnerPersonAdapter(getApplicationContext(),partnerPersonModels);
        recyclerview.setAdapter(partnerPersonAdapter);


        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(PartnerPersonActivity.this, AddPartnerPersonActivity.class);
                startActivity(i);
            }
        });

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<PartnerPersonModel>> call = apiService.PartnerPerson(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<PartnerPersonModel>>() {
            @Override
            public void onResponse(Call<List<PartnerPersonModel>> call, Response<List<PartnerPersonModel>> response) {
                partnerPersonModels = response.body();
                Log.d("TAG","Response success = "+partnerPersonModels);
                partnerPersonAdapter.setPartnerPersonList(partnerPersonModels);
            }

            @Override
            public void onFailure(Call<List<PartnerPersonModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}


