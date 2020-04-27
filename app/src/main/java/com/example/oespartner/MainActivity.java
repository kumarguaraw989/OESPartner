package com.example.oespartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Activity.HomeActivity;
import com.example.oespartner.Activity.RegisterActivity;
import com.example.oespartner.model.Data;
import com.example.oespartner.model.LoginResult;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtLogin;
    EditText phone, pin;
    ProgressBar loading;
    String isRemember = "No";
    CheckBox remember;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        txtLogin = findViewById(R.id.txtLogin);
        phone = findViewById(R.id.edtPhone);
        remember = findViewById(R.id.remember);
        pin = findViewById(R.id.edtPin);
        loading = findViewById(R.id.progressbar_login);
        getSupportActionBar().hide();
        FastSave.init(MainActivity.this);
        String password1 = FastSave.getInstance().getString("Password", "");
        String ph = FastSave.getInstance().getString("Phone", "");
        String isremember = FastSave.getInstance().getString("IsRemember", "");
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        if (isremember.equals("Yes")) {
            pin.setText(password1);
            phone.setText(ph);
            remember.setChecked(true);
        }
        btnLogin.setOnClickListener(v -> {
            if (remember.isChecked()) {
                isRemember = "Yes";
            } else {
                isRemember = "No";
            }
            if (phone.getText().toString().trim().isEmpty()) {
                phone.setError("phone is required!");
                phone.requestFocus();
            } else if (pin.getText().toString().trim().isEmpty()) {
                pin.setError("pin is required!");
                pin.requestFocus();
            } else {
                String Phone = phone.getText().toString().trim();
                String Pin = pin.getText().toString().trim();
                loggedIn(Phone, Pin);
                // TODO Auto-generated method stub
            }
        });
        txtLogin.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });


    }


    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{permission},
                    requestCode);
        } else {
            Toast.makeText(MainActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    public void loggedIn(String email, String pin) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<LoginResult> call = apiService.login(email, pin);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.errorBody() == null && response.body() != null) {
                    Data data = response.body().getData();
                    FastSave.getInstance().saveObject("login_data", data);
                    if (isRemember.equalsIgnoreCase("Yes")) {
                        FastSave.getInstance().saveString("Phone", data.getPhone());
                        FastSave.getInstance().saveString("IsRemember", "Yes");
                        FastSave.getInstance().saveString("Password", data.getPassword());
                    } else {
                        FastSave.getInstance().deleteValue("Phone");
                        FastSave.getInstance().saveString("IsRemember", "No");
                        FastSave.getInstance().deleteValue("Password");
                    }

                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    FancyToast.makeText(getApplicationContext(), "Login Successfull", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                } else {
                    FancyToast.makeText(getApplicationContext(), "Check Your Credentials", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                FancyToast.makeText(getApplicationContext(), "Check Your Internet Connection", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
            }
        });
    }
}