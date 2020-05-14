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

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateVehicleNoActivity extends AppCompatActivity {
    ImageView imgAdd,imgBack;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.edtVehicleNo)
    EditText edtVehicleNo;
    @BindView(R.id.edtVehicleType) EditText edtVehicleType;
    String id2;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle_no);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        Data data_model= FastSave.getInstance().getObject("login_data", Data.class);

        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            edtVehicleNo.setText(jsonObject.get("vehicle_no").toString());
            edtVehicleType.setText(jsonObject.get("vehicle_type").toString());
            id2=jsonObject.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        btnSubmit.setOnClickListener(v -> {
            String edtVehicleNo1 = edtVehicleNo.getText().toString();
            String edtVehicleType1 = edtVehicleType.getText().toString();


            if(edtVehicleNo1.equals("")){
                FancyToast.makeText(UpdateVehicleNoActivity.this,"Select Vehicle No",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                postVehicleNo(id2,data_model.getEmail(),  data_model.getRole(),edtVehicleNo1,edtVehicleType1 );
                onBackPressed();
            }
        });



    }
    public void postVehicleNo(String id2,String email,  String role, String vehicleNo,String vehicleType) {
         RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddVehicleNo> call = apiService.UpdateVehicleNo(id2, data_model.getEmail(),  data_model.getRole(),vehicleNo,vehicleType
        );
        call.enqueue(new Callback<AddVehicleNo>() {
            @Override
            public void onResponse(Call<AddVehicleNo> call, Response<AddVehicleNo> response) {
                progress_bar.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();
                FancyToast.makeText(UpdateVehicleNoActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
             }
            @Override
            public void onFailure(Call<AddVehicleNo> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }
}
