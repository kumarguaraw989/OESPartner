package com.example.oespartner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.Model.AddWorkGatePassModel;
import com.example.oespartner.Model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateWorkgatepassActivity extends AppCompatActivity {
    ImageView imgBack;
    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();

    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectDesignation=new ArrayList<>();
    //ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectPoliceVerify = new ArrayList<>();
    ArrayList<String> SelectPartner = new ArrayList<>();

    @BindView(R.id.spnClient)Spinner spnClient;
    @BindView(R.id.spnBranch)Spinner spnBranch;
    @BindView(R.id.spnPersonName)Spinner spnPersonName;
    @BindView(R.id.spnDesignation)Spinner spnDesignation;
    @BindView(R.id.spnStackHolder)Spinner spnStackHolder;
    @BindView(R.id.spnPersonId)Spinner spnPersonId;
    @BindView(R.id.spnPoliceVerify)Spinner spnPoliceVerify;

    @BindView(R.id.edtReference) EditText edtReference;
    @BindView(R.id.edtDescription) EditText edtDescription;
    @BindView(R.id.edtWorkValidDate) EditText edtWorkValidDate;
    @BindView(R.id.edtDate) EditText edtDate;
    @BindView(R.id.edtDate2) EditText edtDate2;
    @BindView(R.id.id)
    TextView id;

    @BindView(R.id.chk1)CheckBox chk1;
    @BindView(R.id.chk2)CheckBox chk2;
    @BindView(R.id.chk3)CheckBox chk3;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    String email,role,id2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        email=data_model.getEmail();
        role=data_model.getRole();
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, UpdateWorkgatepassActivity.this));
        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            edtReference.setText(jsonObject.get("work_reference_no").toString());
            edtDescription.setText(jsonObject.get("work_description").toString());
            edtWorkValidDate.setText(jsonObject.get("work_valid_upto").toString());
            edtDate.setText(jsonObject.get("work_valid_upto").toString());
            edtDate2.setText(jsonObject.get("visa_validity").toString());
            id2=jsonObject.getString("id").toString();
            SelectClient.add(jsonObject.get("client").toString());
            spnClient.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClient));

            SelectClientBranch.add(jsonObject.get("branch").toString());
            spnBranch.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClientBranch));

            SelectPersonName.add(jsonObject.get("person_name").toString());
            spnPersonName.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonName));

            SelectPersonId.add(jsonObject.get("person_id").toString());
            spnPersonId.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonId));

            SelectDesignation.add(jsonObject.get("designation").toString());
            spnDesignation.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectDesignation));

            SelectPoliceVerify.add(jsonObject.get("police_verify").toString());
            spnPoliceVerify.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPoliceVerify));

            SelectPartner.add(jsonObject.get("stakeholder_id").toString());
            spnStackHolder.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPartner));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        SelectClient.add("Select Anyone");
        StringRequest stringRequest1=new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("company_name");
                    SelectClient.add(catogery);
                }
                spnClient.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue1= Volley.newRequestQueue(this);
        queue1.add(stringRequest1);
        SelectClientBranch.add("Select Anyone");
        StringRequest stringRequest=new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                spnBranch.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue4= Volley.newRequestQueue(this);
        queue4.add(stringRequest);
        SelectPersonName.add("Select Person Name");
        StringRequest stringRequest2=new StringRequest(Request.Method.POST, Config.URL_PersonName, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response.toString());
                for (int j=0; j<jsonArray.length(); ++j){
                    JSONObject jsonObject1=jsonArray.getJSONObject(j);
                    String catogery=jsonObject1.getString("person_name");
                    String catogery1=jsonObject1.getString("id");
                    SelectPersonName.add(catogery);
                    SelectPersonId.add(catogery1);
                }

                spnPersonName.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                spnPersonId.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(UpdateWorkgatepassActivity.this,response, Toast.LENGTH_SHORT).show();
        }, error -> Toast.makeText(UpdateWorkgatepassActivity.this,error.toString(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("email",email);
                params.put("role",role);
                return params;
            }
        };
        RequestQueue queue2=Volley.newRequestQueue(this);
        queue2.add(stringRequest2);
        btnRegister.setOnClickListener(v -> {
            String work_reference_no = edtReference.getText().toString();
            String work_description = edtDescription.getText().toString();
            String work_valid_upto = edtDate.getText().toString();
            String visa_validity = edtDate2.getText().toString();
            String declaration = chk1.getText().toString();
            String j_declaration = chk2.getText().toString();
            String h_declaration = chk3.getText().toString();
            String p_valid_upto = edtWorkValidDate.getText().toString();
            String id1 = id.getText().toString();

            String client=(String) spnClient.getSelectedItem();
            String branch=(String) spnBranch.getSelectedItem();
            String person_name=(String) spnPersonName.getSelectedItem();
            String person_id=(String) spnPersonId.getSelectedItem();
            String designation=(String) spnDesignation.getSelectedItem();
            String stakeholder_id=(String) spnStackHolder.getSelectedItem();
            String police_verify=(String) spnPoliceVerify.getSelectedItem();

            if(client.equals("")){
                FancyToast.makeText(UpdateWorkgatepassActivity.this,"Select Client",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                updateWorkGatePass(id2,email,role,  client,  branch,  person_name, person_id, designation,
                        work_reference_no, work_description,  work_valid_upto,  police_verify,  visa_validity,
                        declaration,  j_declaration,  h_declaration,stakeholder_id,p_valid_upto);
            }
        });
    }

    public void updateWorkGatePass(String id2,String email, String role, String client, String branch, String person_name,String person_id, String designation,
                                   String work_reference_no, String work_description, String work_valid_upto, String police_verify, String visa_validity,
                                   String declaration, String j_declaration, String h_declaration,String stakeholder_id,String p_valid_upto) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AddWorkGatePassModel> call = apiService.UpdateWorkGatePass( id2,email,role,  client,  branch,  person_name, person_id, designation,
                work_reference_no, work_description,  work_valid_upto,  police_verify,  visa_validity, declaration,  j_declaration,  h_declaration,stakeholder_id,p_valid_upto);
        call.enqueue(new Callback<AddWorkGatePassModel>() {
            @Override
            public void onResponse(Call<AddWorkGatePassModel> call, Response<AddWorkGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(UpdateWorkgatepassActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();

            }

            @Override
            public void onFailure(Call<AddWorkGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }



}
