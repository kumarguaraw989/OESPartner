package com.example.oespartner.Activity;
import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
 import android.view.View;
 import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.PartnerPersonAdapter;
import com.example.oespartner.Adapter.TableAdapter;
import com.example.oespartner.model.AddMaterialGatePassModel;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.example.oespartner.model.TableModel;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AddMaterialgatepassActivity extends AppCompatActivity {
    ImageView imgBack;
    Button btnAdd, btnUpdate;
    Spinner branch_name, material_gatepass, vehical_load, reasonformaterialgatepass, materialbelongsto, material_returnable;
    EditText partenername, vehical_no;
    TextInputLayout  others;
    AppCompatTextView stakeholder;
    RelativeLayout materialbelong_layout, materialreturn_layout;
    EditText  edtSpecification, edtUnit, edtQty;
    ArrayList<String> material_list = new ArrayList<>();
    ArrayList<String> edtSpecification1 = new ArrayList<>();
    ArrayList<String> edtUnit1 = new ArrayList<>();
    ArrayList<String> edtQty1 = new ArrayList<>();
    ProgressBar progress_bar;
    String date_time;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    String email, role;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    RecyclerView recyclerview;
    TableAdapter tableAdapter;
    List<TableModel> tableModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materialgatepass);
        getSupportActionBar().hide();
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        email = data_model.getEmail();
        ButterKnife.bind(this);
        role = data_model.getRole();
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        tableAdapter = new TableAdapter(getApplicationContext(),tableModelList);
        recyclerview.setAdapter(tableAdapter);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnupdate);
        materialbelong_layout = findViewById(R.id.Relative_maerialbelongstto);
        materialreturn_layout = findViewById(R.id.materialreturn_layout);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        imgBack.setOnClickListener(v -> onBackPressed());
        branch_name = findViewById(R.id.branch_name);
        material_gatepass = findViewById(R.id.material_gatepass);
        stakeholder = findViewById(R.id.edtStakeholder);
        stakeholder.setText(data_model.getVendorCode());
        partenername = findViewById(R.id.partener_name);
        vehical_no = findViewById(R.id.vehicle_no);
        vehical_load = findViewById(R.id.vehical_load);
        reasonformaterialgatepass = findViewById(R.id.reson_formaterialgatepass);
        others = findViewById(R.id.others);
        materialbelongsto = findViewById(R.id.material_belongsto);
        material_returnable = findViewById(R.id.material_returnable);
        btnUpdate.setVisibility(View.GONE);
        setupSpinners();
        String[] mgatepassType = {"Material Gate Pass Type", "Inward", "Outward"};
        material_gatepass.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mgatepassType));
        String[] vehicalload = {"Vehicle Load", "loaded", "Unloaded"};
        vehical_load.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicalload));
        imgBack.setOnClickListener(v -> onBackPressed());
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.getDefault());
        date_time = dateFormat.format(date);

        SelectClientBranch.add("Select Branch");
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ClientBranch, response -> {
            Log.e("branch", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    SelectClientBranch.add(jsonObject1.get("branch").toString());
                }
                branch_name.setAdapter(new ArrayAdapter<>(AddMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("client", data_model.getClient());
                return params;
            }
        };
        RequestQueue queue4 = Volley.newRequestQueue(this);
        queue4.add(request);
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Config.URL_CLient, response -> {
            Log.e("response", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                Clinet_name.setText(jsonObject.getString("client_id"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("client", data_model.getClient());
                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(stringRequest1);
        btnAdd.setOnClickListener(v -> {
            String partner_code = stakeholder.getText().toString();
            String partner_name = partenername.getText().toString();
            String vehicle_no = vehical_no.getText().toString();
            String client = Clinet_name.getText().toString();
            String branch = (String) branch_name.getSelectedItem();
            String gate_pass_type = (String) material_gatepass.getSelectedItem();
            String vehicle_load = (String) vehical_load.getSelectedItem();
            String reason = (String) reasonformaterialgatepass.getSelectedItem();
            String belong_to = (String) materialbelongsto.getSelectedItem();
            String returnable_nonreturnable = (String) material_returnable.getSelectedItem();
            if (Clinet_name.equals("")) {
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Empty", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                postMaterialGatePass(email, role, client, branch, gate_pass_type,
                        partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time, material_list, edtSpecification1, edtUnit1, edtQty1);
                onBackPressed();
            }
        });


    }
    public void postMaterialGatePass(String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to,
                                     String returnable_nonreturnable, String date_time, ArrayList<String> material_name, ArrayList<String> specification, ArrayList<String> unit, ArrayList<String> qty) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AddMaterialGatePassModel> call = apiService.AddMaterialGatePass(email, role, client, branch, gate_pass_type,
                partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time, material_name, specification, unit, qty);
        call.enqueue(new Callback<AddMaterialGatePassModel>() {
            @Override
            public void onResponse(Call<AddMaterialGatePassModel> call, Response<AddMaterialGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                finish(); }
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


