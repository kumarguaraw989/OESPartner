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
import com.example.oespartner.Adapter.PartnerPersonAdapter;
import com.example.oespartner.model.Data;
 import com.example.oespartner.model.PartnerPersonModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerPersonActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView  imgAdd,imgBack;
    RecyclerView recyclerview;
    PartnerPersonAdapter partnerPersonAdapter;
    List<PartnerPersonModel> partnerPersonModels;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_person);
        getSupportActionBar().hide();
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        partnerPersonModels = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        partnerPersonAdapter = new PartnerPersonAdapter(getApplicationContext(),partnerPersonModels);
        recyclerview.setAdapter(partnerPersonAdapter);

        imgAdd.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(PartnerPersonActivity.this, AddPartnerPersonActivity.class),4444
            );
        });

        swipeRefreshLayout.setRefreshing(true);
        callApi();


     }

    private void callApi() {
         RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<List<PartnerPersonModel>> call = apiService.PartnerPerson(data_model.getEmail(),data_model.getRole());
        call.enqueue(new Callback<List<PartnerPersonModel>>() {
            @Override
            public void onResponse(Call<List<PartnerPersonModel>> call, Response<List<PartnerPersonModel>> response) {
                partnerPersonModels = response.body();
                Log.d("TAG","Response success = "+partnerPersonModels);
                partnerPersonAdapter.setPartnerPersonList(partnerPersonModels);
                 swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<PartnerPersonModel>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
        partnerPersonModels.clear();
        callApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 4444) {
            swipeRefreshLayout.setRefreshing(true);
            partnerPersonModels.clear();
            callApi();
        }
    }
}


