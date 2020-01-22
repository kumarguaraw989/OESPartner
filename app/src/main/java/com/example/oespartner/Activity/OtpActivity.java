package com.example.oespartner.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oespartner.MainActivity;
import com.example.oespartner.Model.OtpModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
public class OtpActivity extends AppCompatActivity {
    EditText edtphone, edtverify_otp;
    Button BtngetOtp, BtnverifyOtp;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().hide();
        edtphone = findViewById(R.id.edtphone_number);
        edtverify_otp = findViewById(R.id.etd_otp);
        BtngetOtp = findViewById(R.id.btn_generate_otp);
        BtnverifyOtp = findViewById(R.id.btn_verify_otp);
        BtngetOtp.setOnClickListener(view -> {
            String PhoneNo = Objects.requireNonNull(edtphone.getText().toString());
            String EdtOtp=Objects.requireNonNull(edtverify_otp.getText().toString());
            Log.e("phone", PhoneNo);
            Log.e("editotp", EdtOtp);
            StringRequest request = new StringRequest(Request.Method.POST, Config.URL_otp, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("message", jsonObject.getString("message"));
                    FancyToast.makeText(getApplicationContext(),"Otp has been sent",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    Log.e("otp", jsonObject.getString("otp"));

                        BtnverifyOtp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FancyToast.makeText(getApplicationContext(),"Verified",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                                startActivity(new Intent(OtpActivity.this,MainActivity.class));
                            }
                        });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("contact", PhoneNo);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(OtpActivity.this);
            queue.add(request);

        });



    }
}