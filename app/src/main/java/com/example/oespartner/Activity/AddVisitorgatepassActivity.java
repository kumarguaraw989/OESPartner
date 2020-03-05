package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.Model.AddVisitorGatePassModel;
import com.example.oespartner.Model.Data;
import com.example.oespartner.Model.PartnerPersonModel;
import com.example.oespartner.Model.PersonNameModel;
import com.example.oespartner.Model.VisitorGatePassModel;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddVisitorgatepassActivity extends AppCompatActivity {

    Calendar calendar;
    int DD, MM, YY;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();


    String email,role;
    @BindView(R.id.edtDate) EditText edtDate;
    @BindView(R.id.edtTime) EditText edtTime;
    @BindView(R.id.imgBack) ImageView imgBack;
    @BindView(R.id.chek) CheckBox chek;
    @BindView(R.id.btnRegister) Button btnRegister;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.edtFirmName) EditText edtFirmName;
    @BindView(R.id.edtAprovalBy) EditText edtAprovalBy;
    @BindView(R.id.edtReason) EditText edtReason;
    @BindView(R.id.spnClient) Spinner spnClient;
    @BindView(R.id.spnBranch) Spinner spnBranch;
    @BindView(R.id.spnPersonName) Spinner spnPersonName;
    @BindView(R.id.spnPersonId) Spinner spnPersonId;
    @BindView(R.id.spnDesignation) Spinner spnDesignation;
    @BindView(R.id.spnPersonVisit) Spinner spnPersonVisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitorgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        FastSave.init(AddVisitorgatepassActivity.this);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        SelectClientBranch.add("Select Branch");
        StringRequest stringRequest=new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                spnBranch.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
        spnBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=spnBranch.getItemAtPosition(spnBranch.getSelectedItemPosition()).toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SelectClient.add("Select Client");
        StringRequest stringRequest1=new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("company_name");
                    SelectClient.add(catogery);
                }
                spnClient.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue1= Volley.newRequestQueue(this);
        queue1.add(stringRequest1);
        spnClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=spnClient.getItemAtPosition(spnClient.getSelectedItemPosition()).toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        SelectPersonName.add("Select Person Name");
        StringRequest stringRequest2=new StringRequest(Request.Method.POST, Config.URL_PersonName, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response.toString());
                    for (int j=0; j<jsonArray.length(); ++j){
                        JSONObject jsonObject1=jsonArray.getJSONObject(j);
                        String catogery=jsonObject1.getString("person_name");
                        String catogery1=jsonObject1.getString("id");
                        SelectPersonName.add(catogery);
                        SelectPersonId.add(catogery1);
                    }

                    spnPersonName.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                    spnPersonId.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonId));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             }
        }, error -> Toast.makeText(AddVisitorgatepassActivity.this,error.toString(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                Data data_model=FastSave.getInstance().getObject("login_data",Data.class);
                params.put("email",data_model.getEmail());
                params.put("role",data_model.getRole());
                return params;
            }
        };
        RequestQueue queue2=Volley.newRequestQueue(this);
        queue2.add(stringRequest2);
        spnPersonName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String dAdress=spnPersonName.getItemAtPosition(spnPersonName.getSelectedItemPosition()).toString();
                 if (position==1){
                    spnPersonId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String id=spnPersonId.getItemAtPosition(spnPersonId.getSelectedItemPosition()).toString();
                         }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        SelectPersonVisited.add("Select");
        StringRequest stringRequest3=new StringRequest(Config.URL_PersonVisit, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("name");
                    SelectPersonVisited.add(catogery);
                }
                spnPersonVisit.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonVisited));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue3= Volley.newRequestQueue(this);
        queue3.add(stringRequest3);
        spnPersonVisit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=spnPersonVisit.getItemAtPosition(spnPersonVisit.getSelectedItemPosition()).toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, AddVisitorgatepassActivity.this));
        edtTime.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddVisitorgatepassActivity.this, (timePicker, selectedHour, selectedMinute) -> {

                String AM_PM;
                if (selectedHour < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }
                Constants.updateTime(selectedHour, selectedMinute, edtTime);

            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        });

        btnRegister.setOnClickListener(v -> {
            String firm_name = edtFirmName.getText().toString();
            String approval = edtAprovalBy.getText().toString();
            String reason = edtReason.getText().toString();
            String visit_date = edtDate.getText().toString();
            String visit_time = edtTime.getText().toString();
            String declaration = chek.getText().toString();
            String client=(String) spnClient.getSelectedItem();
            String branch=(String) spnBranch.getSelectedItem();
            String person_name=(String) spnPersonName.getSelectedItem();
            String person_id=(String) spnPersonId.getSelectedItem();
            String designation=(String) spnDesignation.getSelectedItem();
            String person_visited=(String) spnPersonVisit.getSelectedItem();

            if(client.equals("")){
                FancyToast.makeText(AddVisitorgatepassActivity.this,"Select Client",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
                postVisitorGatePass(data_model.getEmail(),  data_model.getRole(),client, branch, person_name, firm_name, designation, approval,
                        person_visited, reason, visit_date, visit_time, declaration,person_id);
                onBackPressed();
            }
        });
    }

    public void postVisitorGatePass(String email,  String role,String client, String branch, String person_name, String firm_name, String designation,
                                    String approval, String person_visited, String reason, String visit_date, String visit_time, String declaration,String personId) {
         RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);

        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddVisitorGatePassModel> call = apiService.AddVisitorGatePass(email,role,client, branch, person_name, firm_name, designation, approval, person_visited, reason, visit_date, visit_time, declaration,personId
        );
        call.enqueue(new Callback<AddVisitorGatePassModel>() {
            @Override
            public void onResponse(Call<AddVisitorGatePassModel> call, Response<AddVisitorGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddVisitorgatepassActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
            }
            @Override
            public void onFailure(Call<AddVisitorGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }

}






