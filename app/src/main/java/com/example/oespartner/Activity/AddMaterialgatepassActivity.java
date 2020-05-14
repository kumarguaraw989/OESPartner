package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
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
    EditText partenername, vehical_no, others;
    TextInputLayout layout_others;
    AppCompatTextView stakeholder;
    RelativeLayout materialbelong_layout, materialreturn_layout;

    ProgressBar progress_bar;
    String date_time;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    String email, role;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    RecyclerView recyclerview;
    TableAdapter tableAdapter;
    ArrayList<TableModel> tableModelList;


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        tableModelList = new ArrayList<>();
        tableModelList.add(new TableModel());
        tableAdapter = new TableAdapter(getApplicationContext(), tableModelList);
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
        layout_others = findViewById(R.id.layout_others);
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


        //material table code
        tableAdapter.setTableModelsList(tableModelList);


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
            String branch = branch_name.getSelectedItem().toString();
            String gate_pass_type = material_gatepass.getSelectedItem().toString();
            String vehicle_load = vehical_load.getSelectedItem().toString();
            String reason = reasonformaterialgatepass.getSelectedItem().toString();
            String belong_to = materialbelongsto.getSelectedItem().toString();
            String returnable_nonreturnable = material_returnable.getSelectedItem().toString();
            if (Clinet_name.equals("")) {
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Empty", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                Log.e("fo =====r", tableAdapter.getData().size() + "");

                for (int i = 0; i < tableAdapter.getData().size(); i++) {
                    Log.e("for", i + "");


             /*       Log.e("material_name",tableAdapter.getData().get(i).getMaterialName());
                    Log.e("specification",tableAdapter.getData().get(i).getSpecification());
                    Log.e("unit",tableAdapter.getData().get(i).getUnit());
                    Log.e("qty",tableAdapter.getData().get(i).getQuantity());*/


                    postMaterialGatePass(email, role, client, branch, gate_pass_type,
                            partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time, tableAdapter.getData().get(i).getMaterialName(), tableAdapter.getData().get(i).getSpecification()
                            , tableAdapter.getData().get(i).getUnit(), tableAdapter.getData().get(i).getQuantity());
                }

//                tableAdapter.getData()
//                onBackPressed();
            }
        });


    }

    public void postMaterialGatePass(String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to,
                                     String returnable_nonreturnable, String date_time, String material_name, String specification, String unit, String qty) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AddMaterialGatePassModel> call = apiService.AddMaterialGatePass(email, role, client, branch, gate_pass_type,
                partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time, material_name, specification, unit, qty);
        call.enqueue(new Callback<AddMaterialGatePassModel>() {
            @Override
            public void onResponse(Call<AddMaterialGatePassModel> call, Response<AddMaterialGatePassModel> response) {
                Log.e("response successful", "correct" + response.body());
                progress_bar.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }

            @Override
            public void onFailure(Call<AddMaterialGatePassModel> call, Throwable t) {
                Log.e("error", t.getMessage());
                progress_bar.setVisibility(View.GONE);
            }
        });
    }

//    [{"material_name":"ijaoisj","specification":"2","unit":"2","qty":"2"},{"material_name":"ijaoisj","specification":"2","unit":"2","qty":"2"},]

    void setupSpinners() {
        reasonformaterialgatepass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0 || position == 3 || position == 4 || position == 5) {
                    layout_others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.GONE);
                    materialreturn_layout.setVisibility(View.GONE);
                } else if (position == 1 || position == 2) {
                    layout_others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.VISIBLE);
                    materialreturn_layout.setVisibility(View.VISIBLE);
                } else if (position == 6) {
                    layout_others.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}


