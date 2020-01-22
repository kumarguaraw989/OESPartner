package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.VisitorGatePassAdapter;
import com.example.oespartner.App_Helper.PrefManager;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.VisitorGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorgatepassActivity extends AppCompatActivity {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    VisitorGatePassAdapter visitorGatePassAdapter;
    List<VisitorGatePassModel> visitorGatePassModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorgatepass);
        FastSave.init(VisitorgatepassActivity.this);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        FastSave.init(VisitorgatepassActivity.this);
        visitorGatePassModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        visitorGatePassAdapter = new VisitorGatePassAdapter(getApplicationContext(),visitorGatePassModels);
        recyclerview.setAdapter(visitorGatePassAdapter);

        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(VisitorgatepassActivity.this, AddVisitorgatepassActivity.class);
                startActivity(i);
            }
        });



        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model=FastSave.getInstance().getObject("login_data",Data.class);
        Log.e("email",data_model.getEmail());
        Log.e("role",data_model.getRole());
        Call<List<VisitorGatePassModel>> call = apiService.VisitorGatePass(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<VisitorGatePassModel>>() {
            @Override
            public void onResponse(Call<List<VisitorGatePassModel>> call, Response<List<VisitorGatePassModel>> response) {
                visitorGatePassModels = response.body();
                Log.d("TAG","Response success = "+visitorGatePassModels);
                visitorGatePassAdapter.setVisitorGatePassList(visitorGatePassModels);
            }

            @Override
            public void onFailure(Call<List<VisitorGatePassModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
