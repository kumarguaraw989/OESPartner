package com.example.oespartner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

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
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMaterialgatepassActivity extends AppCompatActivity {
ImageView imgBack;
Button btnAdd;
    Spinner select_client, branch_name, material_gatepass, vehical_load, reasonformaterialgatepass, materialbelongsto, material_returnable;
    AppCompatEditText stakeholder, partenername, vehical_no, others;
    RelativeLayout materialbelong_layout,materialreturn_layout;
    ProgressBar progress_bar;
    String date_time;
    String id2;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> Selectmaterial_gatepass=new ArrayList<>();
    ArrayList<String> SelectVehicalLoad=new ArrayList<>();
    ArrayList<String> SelectBelongsTo=new ArrayList<>();
    ArrayList<String> SelectMaterialBelongsTo=new ArrayList<>();
    ArrayList<String> SelectReturnable=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materialgatepass);
        getSupportActionBar().hide();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.getDefault());
        date_time =  dateFormat.format(date);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        materialbelong_layout=findViewById(R.id.Relative_maerialbelongstto);
        materialreturn_layout=findViewById(R.id.materialreturn_layout);
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
        Data data_model= FastSave.getInstance().getObject("login_data", Data.class);
        imgBack.setOnClickListener(v -> onBackPressed());
        progress_bar=(ProgressBar)findViewById(R.id.progress_bar);
        btnAdd.setOnClickListener(v -> {
            String partner_code = stakeholder.getText().toString();
            String partner_name = partenername.getText().toString();
            String vehicle_no = vehical_no.getText().toString();
            String client=(String) select_client.getSelectedItem();
            String branch=(String) branch_name.getSelectedItem();
            String gate_pass_type=(String) material_gatepass.getSelectedItem();
            String vehicle_load=(String) vehical_load.getSelectedItem();
            String reason=(String) reasonformaterialgatepass.getSelectedItem();
            String belong_to=(String) materialbelongsto.getSelectedItem();
            String returnable_nonreturnable=(String) material_returnable.getSelectedItem();

            if(select_client.equals("")){
                FancyToast.makeText(UpdateMaterialgatepassActivity.this,"Enter Truck Number",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }
            else {
                progress_bar.setVisibility(View.VISIBLE);
                postUpdateMaterialGatePass(id2,data_model.getEmail(),  data_model.getRole(),  client,  branch,  gate_pass_type,
                        partner_code,  partner_name,  vehicle_no,  vehicle_load,  reason,  belong_to,  returnable_nonreturnable,date_time);

            }
        });
        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            stakeholder.setText(jsonObject.get("partner_code").toString());
            partenername.setText(jsonObject.get("partner_name").toString());
            vehical_no.setText(jsonObject.get("vehicle_no").toString());
            others.setText(jsonObject.get("work_order_reference").toString());
            SelectClient.add(jsonObject.get("client").toString());
            select_client.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClient));
            Selectmaterial_gatepass.add(jsonObject.get("gate_pass_type").toString());
            SelectVehicalLoad.add(jsonObject.getString("vehicle_load").toString());
            String vehicalload[] = {"Unloaded"};
            vehical_load.setAdapter( new ArrayAdapter(this, android.R.layout.simple_list_item_1,vehicalload ));
             String mgatepassType[] = {"Outward"};
            material_gatepass.setAdapter( new ArrayAdapter(this, android.R.layout.simple_list_item_1,mgatepassType ));
            SelectClient.add("Select Client");
            StringRequest stringRequest1=new StringRequest(Config.URL_CLient, response -> {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); ++i){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String catogery=jsonObject1.getString("company_name");
                        SelectClient.add(catogery);
                    }
                    select_client.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Log.e("error", error.toString()));
            RequestQueue queue1= Volley.newRequestQueue(this);
            queue1.add(stringRequest1);
            select_client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String dAdress=select_client.getItemAtPosition(select_client.getSelectedItemPosition()).toString();
                    Toast.makeText(getApplicationContext(),dAdress,Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            SelectClientBranch.add(jsonObject.get("branch").toString());
            branch_name.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClientBranch));
            SelectClientBranch.add("Select Branch");
            StringRequest stringRequest=new StringRequest(Config.URL_ClientBranch, response -> {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); ++i){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String catogery=jsonObject1.getString("branch");
                        SelectClientBranch.add(catogery);
                    }
                    branch_name.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Log.e("error", error.toString()));
            RequestQueue queue= Volley.newRequestQueue(this);
            queue.add(stringRequest);
            branch_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String dAdress=branch_name.getItemAtPosition(branch_name.getSelectedItemPosition()).toString();
                    Toast.makeText(getApplicationContext(),dAdress,Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            Selectmaterial_gatepass.add(jsonObject.get("gate_pass_type").toString());
            material_gatepass.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,Selectmaterial_gatepass));
            SelectVehicalLoad.add(jsonObject.get("vehicle_load").toString());
            vehical_load.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectVehicalLoad));
            SelectBelongsTo.add(jsonObject.get("reason").toString());
            reasonformaterialgatepass.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectBelongsTo));
            SelectMaterialBelongsTo.add(jsonObject.get("belong_to").toString());
            materialbelongsto.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectMaterialBelongsTo));
            SelectReturnable.add(jsonObject.get("returnable_nonreturnable").toString());
            material_returnable.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectReturnable));
            id2=jsonObject.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setupSpinners() {
        reasonformaterialgatepass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0 || position ==3 || position == 4 || position == 5){
                    others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.GONE);
                    materialreturn_layout.setVisibility(View.GONE);
                }
                else if (position == 1 || position == 2){
                    others.setVisibility(View.GONE);
                    materialbelong_layout.setVisibility(View.VISIBLE);
                    materialreturn_layout.setVisibility(View.VISIBLE);
                }else if (position ==6){
                    others.setVisibility(View.VISIBLE);
                    //others.setText().toString().trim();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void postUpdateMaterialGatePass(String id2,String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to,
                                     String returnable_nonreturnable,String date_time) {
        //LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddMaterialGatePassModel> call = apiService.UpdateMaterialGatePass( id2,data_model.getEmail(),  data_model.getRole(),  client,  branch,  gate_pass_type,
                partner_code,  partner_name,  vehicle_no,  vehicle_load,  reason,  belong_to,  returnable_nonreturnable,date_time);
        call.enqueue(new Callback<AddMaterialGatePassModel>() {
            @Override
            public void onResponse(Call<AddMaterialGatePassModel> call, Response<AddMaterialGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(UpdateMaterialgatepassActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();

            }

            @Override
            public void onFailure(Call<AddMaterialGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }



}


