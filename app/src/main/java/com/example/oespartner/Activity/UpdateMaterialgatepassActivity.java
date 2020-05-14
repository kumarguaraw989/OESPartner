package com.example.oespartner.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
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
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMaterialgatepassActivity extends AppCompatActivity {
    ImageView imgBack;
    Button btnAdd, btnUpdate;

    Spinner branch_name, material_gatepass, vehical_load, reasonformaterialgatepass, materialbelongsto, material_returnable;
    EditText partenername, vehical_no, others;
    TextInputLayout layout_others;
    RelativeLayout materialbelong_layout, materialreturn_layout;
    ProgressBar progress_bar;
    AppCompatTextView stakeholder;
    String date_time;
    RecyclerView recyclerview;
    TableAdapter tableAdapter;
    ArrayList<TableModel> tableModelList;
    String id2;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> Selectmaterial_gatepass = new ArrayList<>();
    ArrayList<String> SelectVehicalLoad = new ArrayList<>();
    ArrayList<String> SelectBelongsTo = new ArrayList<>();
    ArrayList<String> SelectMaterialBelongsTo = new ArrayList<>();
    ArrayList<String> SelectReturnable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materialgatepass);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
/*        TextView btnAddMaterial=findViewById(R.id.btnAddMaterial);
        TextView btnRemoveMaterial=findViewById(R.id.btnRemoveMaterial);*/
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.getDefault());
        date_time = dateFormat.format(date);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        layout_others = findViewById(R.id.layout_others);


        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        tableModelList= new ArrayList<>();
        tableModelList.add(new TableModel());
        tableAdapter = new TableAdapter(getApplicationContext(),tableModelList);
        recyclerview.setAdapter(tableAdapter);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        materialbelong_layout = findViewById(R.id.Relative_maerialbelongstto);
        materialreturn_layout = findViewById(R.id.materialreturn_layout);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnUpdate = findViewById(R.id.btnupdate);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        imgBack.setOnClickListener(v -> onBackPressed());
        branch_name = findViewById(R.id.branch_name);
        material_gatepass = findViewById(R.id.material_gatepass);
        stakeholder = findViewById(R.id.edtStakeholder);
        partenername = findViewById(R.id.partener_name);
        vehical_no = findViewById(R.id.vehicle_no);
        vehical_load = findViewById(R.id.vehical_load);
        reasonformaterialgatepass = findViewById(R.id.reson_formaterialgatepass);
        others = findViewById(R.id.others);
        btnAdd.setVisibility(View.GONE);
        materialbelongsto = findViewById(R.id.material_belongsto);
        material_returnable = findViewById(R.id.material_returnable);
        stakeholder.setText(data_model.getVendorCode());
        setupSpinners();

        //material table code
        tableAdapter.setTableModelsList(tableModelList);
//        final ViewGroup tes = (ViewGroup) findViewById(R.id.layout_addchambers);

        imgBack.setOnClickListener(v -> onBackPressed());
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        btnUpdate.setOnClickListener(v -> {
            String partner_code = stakeholder.getText().toString();
            String partner_name = partenername.getText().toString();
            String vehicle_no = vehical_no.getText().toString();
            String client = Clinet_name.getText().toString();
            String branch = (String) branch_name.getSelectedItem().toString();
            String gate_pass_type = (String) material_gatepass.getSelectedItem().toString();
            String vehicle_load = (String) vehical_load.getSelectedItem().toString();
            String reason = (String) reasonformaterialgatepass.getSelectedItem().toString();
            String belong_to = (String) materialbelongsto.getSelectedItem().toString();
            String returnable_nonreturnable = material_returnable.getSelectedItem().toString();
       /*     btnAddMaterial.setOnClickListener(v1 -> {
                final View extend = LayoutInflater.from(v.getContext()).inflate(R.layout.item_chamber_add, tes, false);
                tes.addView(extend);
            });
            btnRemoveMaterial.setOnClickListener(v1 -> tes.removeViewAt(1));*/
            if (Clinet_name.equals("")) {
                FancyToast.makeText(UpdateMaterialgatepassActivity.this, "", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);

                Log.e("fo =====r",tableAdapter.getData().size()+"");

                for(int i=0;i< tableAdapter.getData().size();i++){
                    Log.e("for",i+"");
                    postUpdateMaterialGatePass(id2, data_model.getEmail(), data_model.getRole(), client, branch, gate_pass_type,
                            partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time,tableAdapter.getData().get(i).getMaterialName(),  tableAdapter.getData().get(i).getSpecification(),tableAdapter.getData().get(i).getUnit(),tableAdapter.getData().get(i).getQuantity());

                }


            }
        });
        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        Log.e("res_material", value);
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            stakeholder.setText(jsonObject.get("partner_code").toString());
            partenername.setText(jsonObject.get("partner_name").toString());
            vehical_no.setText(jsonObject.get("vehicle_no").toString());
            others.setText(jsonObject.get("work_order_reference").toString());
            SelectClient.add(jsonObject.get("client").toString());
            Clinet_name.setText(jsonObject.getString("client"));
            Selectmaterial_gatepass.add(jsonObject.get("gate_pass_type").toString());
            SelectVehicalLoad.add(jsonObject.getString("vehicle_load"));
            String vehicalload[] = {"Unloaded"};
            vehical_load.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicalload));
            String mgatepassType[] = {"Outward"};
            material_gatepass.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mgatepassType));
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Config.URL_CLient, response -> {
                Log.e("response", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    Clinet_name.setText(jsonObject1.getString("client_id"));

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
            SelectClientBranch.add(jsonObject.get("branch").toString());
            branch_name.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            SelectClientBranch.add("Select Branch");
            StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ClientBranch, response -> {
                Log.e("branch", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        SelectClientBranch.add(jsonObject1.get("branch").toString());
                    }
                    branch_name.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
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
            Selectmaterial_gatepass.add(jsonObject.get("gate_pass_type").toString());
            material_gatepass.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, Selectmaterial_gatepass));
            SelectVehicalLoad.add(jsonObject.get("vehicle_load").toString());
            vehical_load.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectVehicalLoad));
            SelectBelongsTo.add(jsonObject.get("reason").toString());
            reasonformaterialgatepass.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectBelongsTo));
            SelectMaterialBelongsTo.add(jsonObject.get("belong_to").toString());
            materialbelongsto.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectMaterialBelongsTo));
            SelectReturnable.add(jsonObject.get("returnable_nonreturnable").toString());
            material_returnable.setAdapter(new ArrayAdapter<>(UpdateMaterialgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectReturnable));
            /*String material_returnable1[] = {"Material Loading","Material Unloading","Vehical Checking-Checking","Vehical Checking-Calibration","Vehical Checking-Lock"};

            material_returnable.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,material_returnable1 ));*/


            id2 = jsonObject.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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
                    //others.setText().toString().trim();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void postUpdateMaterialGatePass(String id2, String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to,
                                           String returnable_nonreturnable, String date_time,String material_name,String Specification,String quantity,String unit) {
        //LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<AddMaterialGatePassModel> call = apiService.UpdateMaterialGatePass(id2, data_model.getEmail(), data_model.getRole(), client, branch, gate_pass_type,
                partner_code, partner_name, vehicle_no, vehicle_load, reason, belong_to, returnable_nonreturnable, date_time,material_name,Specification,quantity,unit);
        call.enqueue(new Callback<AddMaterialGatePassModel>() {
            @Override
            public void onResponse(Call<AddMaterialGatePassModel> call, Response<AddMaterialGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
                FancyToast.makeText(UpdateMaterialgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

            }

            @Override
            public void onFailure(Call<AddMaterialGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }


}


