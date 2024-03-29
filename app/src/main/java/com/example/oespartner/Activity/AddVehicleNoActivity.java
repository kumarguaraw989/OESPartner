package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.model.AddVehicleNo;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicleNoActivity extends AppCompatActivity {

    ImageView imgAdd,imgBack;
    @BindView(R.id.edtVehicleNo)
    EditText edtVehicleNo;
    @BindView(R.id.edtVehicleType) EditText edtVehicleType;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle_no);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        imgAdd=(ImageView)findViewById(R.id.imgAdd);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());

        btnSubmit.setOnClickListener(v -> {
            String edtVehicleNo1 = edtVehicleNo.getText().toString();
            String edtVehicleType1 = edtVehicleType.getText().toString();


            if(edtVehicleNo1.equals("")){
                FancyToast.makeText(AddVehicleNoActivity.this,"Select Vehicle No",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
                postVisitorGatePass(data_model.getEmail(),  data_model.getRole(),edtVehicleNo1,edtVehicleType1 );
                //onBackPressed();
            }
        });




    }
    public void postVisitorGatePass(String email,  String role, String vehicleNo,String vehicleType) {
         RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);

        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddVehicleNo> call = apiService.AddVehicleNo( data_model.getEmail(),  data_model.getRole(),vehicleNo,vehicleType
        );
        call.enqueue(new Callback<AddVehicleNo>() {
            @Override
            public void onResponse(Call<AddVehicleNo> call, Response<AddVehicleNo> response) {
                progress_bar.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();
                FancyToast.makeText(AddVehicleNoActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
            }

            @Override
            public void onFailure(Call<AddVehicleNo> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }

}



