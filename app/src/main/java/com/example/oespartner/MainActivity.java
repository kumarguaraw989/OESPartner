package com.example.oespartner;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Activity.HomeActivity;
import com.example.oespartner.Activity.RegisterActivity;
import com.example.oespartner.App_Helper.PrefManager;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.LoginResult;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import com.google.gson.JsonObject;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtLogin;
    EditText phone,pin;
    Spinner usertype;
    ProgressBar loading;
    String URL="http://oestech.com/management/vehicle_management/index.php/home_api/get_role";
    ArrayList<String> SelectUserType = new ArrayList<>();
    String isRemember="No";
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.btnLogin);
        txtLogin=findViewById(R.id.txtLogin);
        usertype=findViewById(R.id.catogery);
        phone=findViewById(R.id.edtPhone);
        remember=findViewById(R.id.remember);
        pin=findViewById(R.id.edtPin);
        loading=findViewById(R.id.progressbar_login);
        getSupportActionBar().hide();
        FastSave.init(MainActivity.this);
        String password1=FastSave.getInstance().getString("Password","");
        String ph=FastSave.getInstance().getString("Phone","");
        String isremember=FastSave.getInstance().getString("IsRemember","");
        if(isremember.equals("Yes")){
            pin.setText(password1);
            phone.setText(ph);
            remember.setChecked(true);
        }
        SelectUserType.add("Select UserType");
        StringRequest stringRequest=new StringRequest(URL, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("role");
                    SelectUserType.add(catogery);
                }
                usertype.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectUserType));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Gronzo", error.toString()));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=usertype.getItemAtPosition(usertype.getSelectedItemPosition()).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnLogin.setOnClickListener(v -> {
            if(remember.isChecked()){
                isRemember="Yes";
            }else{
                isRemember="No";

            }
            if (usertype.getSelectedItem().toString().trim().equals("Select UserType")) {
                Toast.makeText(MainActivity.this, "Usertype is empty!", Toast.LENGTH_SHORT).show();
            }
            else if (phone.getText().toString().trim().isEmpty()) {
                phone.setError( "phone is required!" );
                phone.requestFocus();
            }else if (pin.getText().toString().trim().isEmpty()){
                pin.setError("pin is required!");
                pin.requestFocus();
            }
            else {
                String Phone = phone.getText().toString().trim();
                String Pin = pin.getText().toString().trim();
                String role = (String) usertype.getSelectedItem();
                loggedIn(role, Phone, Pin);
                // TODO Auto-generated method stub
//                loading.setVisibility(View.VISIBLE);
//                btnLogin.setVisibility(View.GONE);
            }});
        txtLogin.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }
    public void loggedIn(String role,String email,String pin){
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<LoginResult> call=apiService.login(role,email,pin);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.errorBody()==null&&response.body()!=null ){
                    Data data=response.body().getData();
                    FastSave.getInstance().saveObject("login_data",data);
                    if(isRemember.equalsIgnoreCase("Yes")){
                        FastSave.getInstance().saveString("Role",data.getRole());
                        FastSave.getInstance().saveString("Phone",data.getPhone());
                        FastSave.getInstance().saveString("IsRemember","Yes");
                        FastSave.getInstance().saveString("Password",data.getPassword());
                    }else {
                        FastSave.getInstance().deleteValue("Role");
                        FastSave.getInstance().deleteValue("Phone");
                        FastSave.getInstance().saveString("IsRemember","No");
                        FastSave.getInstance().deleteValue("Password");
                    }

                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    FancyToast.makeText(getApplicationContext(),"Login Successfull",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }else{
                    FancyToast.makeText(getApplicationContext(),"Check Your Credentials",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }

            }
            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                FancyToast.makeText(getApplicationContext(),"Check Your Internet Connection",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
            }
        });

    }




}