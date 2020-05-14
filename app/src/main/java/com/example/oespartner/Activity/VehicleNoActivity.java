package com.example.oespartner.Activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.VehicleNoAdapter;
import com.example.oespartner.model.AddVehicleNo;
import com.example.oespartner.model.Data;
import com.example.oespartner.model.VehicleNoModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class VehicleNoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView imgAdd, imgBack;
    RecyclerView recyclerview;
    VehicleNoAdapter vehicleNoAdapter;
    List<VehicleNoModel> vehicleNoModels;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_no);
        getSupportActionBar().hide();
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        vehicleNoModels = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        vehicleNoAdapter = new VehicleNoAdapter(getApplicationContext(), vehicleNoModels);
        recyclerview.setAdapter(vehicleNoAdapter);
        imgAdd.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(VehicleNoActivity.this, AddVehicleNoActivity.class),5555
            );
        });
        swipeRefreshLayout.setRefreshing(true);
        callApi();


    }

    private void callApi() {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<List<VehicleNoModel>> call = apiService.VehicleNo(data_model.getEmail(), data_model.getRole());
        call.enqueue(new Callback<List<VehicleNoModel>>() {
            @Override
            public void onResponse(Call<List<VehicleNoModel>> call, Response<List<VehicleNoModel>> response) {
                vehicleNoModels = response.body();
                Log.d("TAG", "Response success = " + vehicleNoModels);
                vehicleNoAdapter.setVehicleNoList(vehicleNoModels);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<VehicleNoModel>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        Log.d("TAG", "onRefresh");
        swipeRefreshLayout.setRefreshing(false);
//        vehicleNoModels.clear();
        callApi();
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 5555) {
            swipeRefreshLayout.setRefreshing(true);
            vehicleNoModels.clear();
            callApi();
        }
    }

/*    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> VehicleNoActivity.this.finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }*/
}



