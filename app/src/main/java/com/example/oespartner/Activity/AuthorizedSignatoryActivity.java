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
import com.example.oespartner.Adapter.AuthorizedSignatoryAdapter;
import com.example.oespartner.Model.AuthorizedSignatoryModel;
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

public class AuthorizedSignatoryActivity extends AppCompatActivity {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    AuthorizedSignatoryAdapter authorizedSignatoryAdapter;
    List<AuthorizedSignatoryModel> authorizedSignatoryModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_signatory);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());

        authorizedSignatoryModels = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        authorizedSignatoryAdapter = new AuthorizedSignatoryAdapter(getApplicationContext(),authorizedSignatoryModels);
        recyclerview.setAdapter(authorizedSignatoryAdapter);

        imgAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(AuthorizedSignatoryActivity.this, AddAuthorizedSignatoryActivity.class);
                startActivity(i);
            }
        });

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<AuthorizedSignatoryModel>> call = apiService.AuthorizedSignatory(data_model.getEmail(),data_model.getRole());

        call.enqueue(new Callback<List<AuthorizedSignatoryModel>>() {
            @Override
            public void onResponse(Call<List<AuthorizedSignatoryModel>> call, Response<List<AuthorizedSignatoryModel>> response) {
                authorizedSignatoryModels = response.body();
                Log.d("TAG","Response success = "+authorizedSignatoryModels);
                authorizedSignatoryAdapter.setAuthorizedSignatoryList(authorizedSignatoryModels);
            }

            @Override
            public void onFailure(Call<List<AuthorizedSignatoryModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
