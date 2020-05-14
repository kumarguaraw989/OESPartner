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
            startActivityForResult(
                    new Intent(AuthorizedSignatoryActivity.this, AddAuthorizedSignatoryActivity.class),4444
            );
        });
        swipeRefreshLayout.setRefreshing(true);
        callApi();


            }

    private void callApi() {
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
                swipeRefreshLayout.setRefreshing(false);
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }


    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
//        authorizedSignatoryModels.clear();
        callApi();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 1111) {
            swipeRefreshLayout.setRefreshing(true);
            authorizedSignatoryModels.clear();
            callApi();
        }
    }
}
