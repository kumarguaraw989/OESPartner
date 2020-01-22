package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Model.AddMaterialGatePassModel;
import com.example.oespartner.Model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddMaterialgatepassActivity extends AppCompatActivity {
    ImageView imgBack;
    Button btnAdd;
    Spinner select_client, branch_name, material_gatepass, vehical_load, reasonformaterialgatepass, materialbelongsto, material_returnable;
    AppCompatEditText stakeholder, partenername, vehical_no, others;
    RelativeLayout materialbelong_layout, materialreturn_layout;
    ProgressBar progress_bar;
    String date_time;
    String email, role;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materialgatepass);
        getSupportActionBar().hide();
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        email=data_model.getEmail();
        role=data_model.getRole();
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        materialbelong_layout = findViewById(R.id.Relative_maerialbelongstto);
        materialreturn_layout = findViewById(R.id.materialreturn_layout);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        imgBack.setOnClickListener(v -> onBackPressed());
        select_client = findViewById(R.id.select_client);
        branch_name = findViewById(R.id.branch_name);
        material_gatepass = findViewById(R.id.material_gatepass);
        stakeholder = findViewById(R.id.edtStakeholder);
        partenername = findViewById(R.id.partener_name);
        vehical_no = findViewById(R.id.vehicle_no);
        vehical_load = findViewById(R.id.vehical_load);
        reasonformaterialgatepass = findViewById(R.id.reson_formaterialgatepass);
        others = findViewById(R.id.others);
        materialbelongsto = findViewById(R.id.material_belongsto);
        material_returnable = findViewById(R.id.material_returnable);
        setupSpinners();
        String[] mgatepassType = {"Material Gate Pass Type", "Inward", "Outward"};
        material_gatepass.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mgatepassType));
        String[] vehicalload = {"Vehicle Load", "loaded", "Unloaded"};
        vehical_load.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicalload));
        imgBack.setOnClickListener(v -> onBackPressed());
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.getDefault());
        date_time =  dateFormat.format(date);
        SelectClientBranch.add("Select Branch");
        StringRequest stringRequest = new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                branch_name.setAdapter(new ArrayAdapter<>(AddMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Error", error.toString()));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        branch_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress = branch_name.getItemAtPosition(branch_name.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(), dAdress, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SelectClient.add("Select Client");
        StringRequest stringRequest1 = new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("company_name");
                    SelectClient.add(catogery);
                }
                select_client.setAdapter(new ArrayAdapter<>(AddMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest1);
        select_client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress = select_client.getItemAtPosition(select_client.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(), dAdress, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(v -> {
            String partner_code = stakeholder.getText().toString();
            String partner_name = partenername.getText().toString();
            String vehicle_no = vehical_no.getText().toString();
            String client = (String) select_client.getSelectedItem();
            String branch = (String) branch_name.getSelectedItem();
            String gate_pass_type = (String) material_gatepass.getSelectedItem();
            String vehicle_load = (String) vehical_load.getSelectedItem();
            String reason = (String) reasonformaterialgatepass.getSelectedItem();
            String belong_to = (String) materialbelongsto.getSelectedItem();
            String returnable_nonreturnable = (String) material_returnable.getSelectedItem();
            if (select_client.equals("")) {
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Empty", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                postMaterialGatePass(email, role, client, branch, gate_pass_type,
                        partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time);
                onBackPressed();
            }
        });
    }

    public void postMaterialGatePass(String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to,
                                     String returnable_nonreturnable, String date_time) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
         Call<AddMaterialGatePassModel> call = apiService.AddMaterialGatePass(email,role, client, branch, gate_pass_type,
                partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time);
        call.enqueue(new Callback<AddMaterialGatePassModel>() {
            @Override
            public void onResponse(Call<AddMaterialGatePassModel> call, Response<AddMaterialGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                finish();

            }
            @Override
            public void onFailure(Call<AddMaterialGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }
    void setupSpinners() {
        reasonformaterialgatepass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0 || position == 3 || position == 4 || position == 5) {
                    others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.GONE);
                    materialreturn_layout.setVisibility(View.GONE);
                } else if (position == 1 || position == 2) {
                    others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.VISIBLE);
                    materialreturn_layout.setVisibility(View.VISIBLE);
                } else if (position == 6) {
                    others.setVisibility(View.VISIBLE);
                 }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}


