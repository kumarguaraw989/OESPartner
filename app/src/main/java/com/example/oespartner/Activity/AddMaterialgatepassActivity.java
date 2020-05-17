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
import com.bumptech.glide.util.LogTime;
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
import java.util.Arrays;
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
    EditText partenername, vehical_no, others, work_order_referenceNo;
    TextInputLayout layout_others, layout_workReferenceno;
    AppCompatTextView stakeholder;
    RelativeLayout materialbelong_layout, materialreturn_layout;
    ProgressBar progress_bar;
    String date_time;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    TextView tv_material_belongs_to,tv_returnable;
    String email, role;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
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
        tv_material_belongs_to=findViewById(R.id.tv_belongto);
        tv_returnable=findViewById(R.id.tv_returnable);
        role = data_model.getRole();
        layout_workReferenceno = findViewById(R.id.layout_workReferenceno);
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
        work_order_referenceNo = findViewById(R.id.work_order_referenceNo);
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

        materialbelongsto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    layout_workReferenceno.setVisibility(View.VISIBLE);
                } else {
                    layout_workReferenceno.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //material table code
        tableAdapter.setTableModelsList(tableModelList);


        SelectClientBranch.add("Select Branch");
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ClientBranch, response -> {
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


//            String other=others.getText().toString();
            if (Clinet_name.equals("")) {
                FancyToast.makeText(AddMaterialgatepassActivity.this, "Empty", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                Log.e("fo =====r", tableAdapter.getData().size() + "");

                postMaterialGatePass(getParam());


            }
        });
    }

    private Map<String, String> getParam() {
        Log.d("tableAdapterDate", tableAdapter.getData().toString());
        Map<String, String> mParam = new HashMap<>();
        mParam.put("email", email);
        mParam.put("client", Clinet_name.getText().toString());
        mParam.put("role", role);
        mParam.put("branch", branch_name.getSelectedItem().toString());
        mParam.put("gate_pass_type", material_gatepass.getSelectedItem().toString());
        mParam.put("partner_code",  stakeholder.getText().toString());
        mParam.put("partner_name", partenername.getText().toString());
        mParam.put("vehicle_no", vehical_no.getText().toString());
        mParam.put("vehicle_load", vehical_load.getSelectedItem().toString());
        mParam.put("reason",  reasonformaterialgatepass.getSelectedItem().toString());
        mParam.put("belong_to",  materialbelongsto.getSelectedItem().toString());
        mParam.put("work_order_referenceNo", work_order_referenceNo.getText().toString());
        mParam.put("returnable_nonreturnable", material_returnable.getSelectedItem().toString());
        mParam.put("date_time", date_time);

        for (int i = 0; i < tableAdapter.getData().size(); i++) {
            mParam.put("material_name[" + i + "]", tableAdapter.getData().get(i).getMaterialName());
            mParam.put("specification[" + i + "]", tableAdapter.getData().get(i).getSpecification());
            mParam.put("qty[" + i + "]", tableAdapter.getData().get(i).getQuantity());
            mParam.put("unit[" + i + "]", tableAdapter.getData().get(i).getUnit());
        }

        Log.e("mapsss", mParam.toString());
        return mParam;
    }

    public void postMaterialGatePass(Map<String, String> map) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AddMaterialGatePassModel> call = apiService.AddMaterialGatePass(map);
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
                    tv_material_belongs_to.setVisibility(View.GONE);
                    tv_returnable.setVisibility(View.GONE);
                } else if (position == 1 || position == 2) {
                    layout_others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.VISIBLE);
                    materialreturn_layout.setVisibility(View.VISIBLE);
                    tv_material_belongs_to.setVisibility(View.VISIBLE);
                    tv_returnable.setVisibility(View.VISIBLE);
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


