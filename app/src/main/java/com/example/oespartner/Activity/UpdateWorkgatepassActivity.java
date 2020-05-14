package com.example.oespartner.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.example.oespartner.model.AddWorkGatePassModel;
import com.example.oespartner.model.Data;
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
    @BindView(R.id.spnPartnerName)
    Spinner spnPartnerName;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    @BindView(R.id.tv_clientname)
    TextView tv_clientname;
    @BindView(R.id.spnBranch)Spinner spnBranch;
    @BindView(R.id.spnPersonName)Spinner spnPersonName;
    @BindView(R.id.spnDesignation)Spinner spnDesignation;
//    @BindView(R.id.spnStackHolder)Spinner spnStackHolder;
     @BindView(R.id.spnPoliceVerify)Spinner spnPoliceVerify;
    @BindView(R.id.et_personid)
    TextView et_personid;
    @BindView(R.id.edtReference) EditText edtReference;
    @BindView(R.id.edtDescription) EditText edtDescription;
    @BindView(R.id.edtWorkValidDate) EditText edtWorkValidDate;
    @BindView(R.id.edtDate) EditText edtDate;
     @BindView(R.id.id)
    TextView id;
    String person_id;
    @BindView(R.id.chk1)CheckBox chk1;
    @BindView(R.id.chk2)CheckBox chk2;
    @BindView(R.id.chk3)CheckBox chk3;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    String email,role,id2,Partener_id;
    @BindView(R.id.rl_clientname)
    RelativeLayout rl_clientname;
    @BindView(R.id.rl_branchname)
    RelativeLayout rl_branchname;
    @BindView(R.id.tv_branchname)
    TextView tv_branchname;

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
        tv_clientname.setVisibility(View.GONE);
        rl_clientname.setVisibility(View.GONE);
        tv_branchname.setVisibility(View.GONE);
        rl_branchname.setVisibility(View.GONE);
        String value = intent.getStringExtra("response");
        try {
            assert value != null;
            JSONObject jsonObject = new JSONObject(value);
            Log.e("res",value);
            edtReference.setText(jsonObject.get("work_reference_no").toString());
            edtDescription.setText(jsonObject.get("work_description").toString());
            edtWorkValidDate.setText(jsonObject.get("work_valid_upto").toString());
            edtDate.setText(jsonObject.get("work_valid_upto").toString());
             chk1.isChecked();
            chk2.isChecked();
            chk3.isChecked();
            id2=jsonObject.getString("id").toString();
            SelectClientBranch.add(jsonObject.get("branch").toString());
            spnBranch.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClientBranch));

            SelectPersonName.add(jsonObject.get("person_name").toString());
            spnPersonName.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonName));

            SelectPersonId.add(jsonObject.get("person_id").toString());

            SelectDesignation.add(jsonObject.get("designation").toString());
            spnDesignation.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectDesignation));
            String[] leftVision = {"Promoter/Partner/Proprietor", "Engineer", "Manager", "Supervisor", "Driver", "Helper", "Contract Labour","Electrician","Security","Other"};
            spnDesignation.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, leftVision));



            SelectPoliceVerify.add(jsonObject.get("police_verify").toString());
            spnPoliceVerify.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPoliceVerify));

            SelectPartner.add(jsonObject.get("stakeholder_id").toString());
            //spnStackHolder.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPartner));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest1=new StringRequest(Request.Method.POST, Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                Clinet_name.setText(jsonObject.getString("client_id"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("client", data_model.getClient());
                return params;
            }
        };
        RequestQueue requestQueue1=Volley.newRequestQueue(this);
        requestQueue1.add(stringRequest1);


        SelectClientBranch.add("Select Branch");
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SelectClientBranch.add(jsonObject.get("branch").toString());
                }
                spnBranch.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
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
             } catch (JSONException e) {
                e.printStackTrace();
            }

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
        SelectPersonName.add("Select");
        SelectPersonName.add(data_model.getName());
        StringRequest stringReques5 = new StringRequest(Request.Method.POST, Config.URL_PersonName, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    String catogery = jsonObject1.getString("person_name");
                    String catogery1 = jsonObject1.getString("person_id");
                    SelectPersonName.add(catogery);
                    et_personid.setText(catogery1);
                    person_id = jsonObject1.getString("person_id");
                }
                spnPersonName.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(UpdateWorkgatepassActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("role", role);
                return params;
            }
        };
        RequestQueue queue5 = Volley.newRequestQueue(this);
        queue5.add(stringReques5);
        SelectPartner.add("Select Partner Name");
//        SelectPartner.add(data_model.getName());
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, Config.URL_getPartnername, response -> {
            Log.e("response", response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SelectPartner.add(jsonObject.getString("partner_name"));
                    Partener_id = jsonObject.getString("partner_id");
                    Log.e("Partener_id", Partener_id);
                }
                spnPartnerName.setAdapter(new ArrayAdapter<>(UpdateWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPartner));

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
        RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        requestQueue3.add(stringRequest3);
        btnRegister.setOnClickListener(v -> {
            String work_reference_no = edtReference.getText().toString();
            String work_description = edtDescription.getText().toString();
            String work_valid_upto = edtDate.getText().toString();
             String declaration = chk1.getText().toString();
            String j_declaration = chk2.getText().toString();
            String h_declaration = chk3.getText().toString();
            String p_valid_upto = edtWorkValidDate.getText().toString();
            String id1 = id.getText().toString();

            String client=Clinet_name.getText().toString();
            String branch=(String) spnBranch.getSelectedItem();
            String person_name=(String) spnPersonName.getSelectedItem();
            String person_id=et_personid.getText().toString();
            String designation=(String) spnDesignation.getSelectedItem();
          //  String stakeholder_id=(String) spnStackHolder.getSelectedItem();
            String police_verify=(String) spnPoliceVerify.getSelectedItem();

            if(client.equals("")){
                FancyToast.makeText(UpdateWorkgatepassActivity.this,"Select Client",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                updateWorkGatePass(id2,email,role,  client,  branch,  person_name, person_id, designation,
                        work_reference_no, work_description,  work_valid_upto,  police_verify,
                        declaration,  j_declaration,  h_declaration,p_valid_upto);
            }
        });
    }

    public void updateWorkGatePass(String id2,String email, String role, String client, String branch, String person_name,String person_id, String designation,
                                   String work_reference_no, String work_description, String work_valid_upto, String police_verify,
                                   String declaration, String j_declaration, String h_declaration,String p_valid_upto) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Call<AddWorkGatePassModel> call = apiService.UpdateWorkGatePass( id2,email,role,  client,  branch,  person_name, person_id, designation,
                work_reference_no, work_description,  work_valid_upto,  police_verify, declaration,  j_declaration,  h_declaration,p_valid_upto);
        call.enqueue(new Callback<AddWorkGatePassModel>() {
            @Override
            public void onResponse(Call<AddWorkGatePassModel> call, Response<AddWorkGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();
                FancyToast.makeText(UpdateWorkgatepassActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

            }

            @Override
            public void onFailure(Call<AddWorkGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }



}
