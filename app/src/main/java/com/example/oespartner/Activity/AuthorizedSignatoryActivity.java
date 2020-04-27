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
import com.example.oespartner.Adapter.AuthorizedSignatoryAdapter;
import com.example.oespartner.model.AuthorizedSignatoryModel;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizedSignatoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    AuthorizedSignatoryAdapter authorizedSignatoryAdapter;
    List<AuthorizedSignatoryModel> authorizedSignatoryModels;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_signatory);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        authorizedSignatoryModels = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        authorizedSignatoryAdapter = new AuthorizedSignatoryAdapter(getApplicationContext(),authorizedSignatoryModels);
        recyclerview.setAdapter(authorizedSignatoryAdapter);

        imgAdd.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(AuthorizedSignatoryActivity.this, AddAuthorizedSignatoryActivity.class);
            startActivity(i);
        });
        Handler mhandler=new Handler();
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
                Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
                Call<List<AuthorizedSignatoryModel>> call = apiService.AuthorizedSignatory(data_model.getEmail(),data_model.getRole());

                call.enqueue(new Callback<List<AuthorizedSignatoryModel>>() {
                    @Override
                    public void onResponse(Call<List<AuthorizedSignatoryModel>> call, Response<List<AuthorizedSignatoryModel>> response) {
                        authorizedSignatoryModels = response.body();
                        Log.d("TAG","Response success = "+authorizedSignatoryModels);
                        authorizedSignatoryAdapter.setAuthorizedSignatoryList(authorizedSignatoryModels);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Call<List<AuthorizedSignatoryModel>> call, Throwable t) {
                        Log.d("TAG","Response = "+t.toString());
                    }
                });
            }
        },1000);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
