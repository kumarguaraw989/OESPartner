package com.example.oespartner.Activity;

import android.app.TimePickerDialog;
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
import com.example.oespartner.model.AddVisitorGatePassModel;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UpdateVisitorGatePassActivity extends AppCompatActivity {

    Calendar calendar;
    int DD, MM, YY;
    @BindView(R.id.edtDate)
    EditText edtDate;
    @BindView(R.id.edtTime) EditText edtTime;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.chek)
    CheckBox chek;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.tvFirmName)
    TextView tvFirmName;
    @BindView(R.id.edtAprovalBy) EditText edtAprovalBy;
    @BindView(R.id.edtReason) EditText edtReason;
    @BindView(R.id.client_name)
    TextView Clinet_name;

    @BindView(R.id.spnBranch) Spinner spnBranch;
    @BindView(R.id.spnPersonName) Spinner spnPersonName;
    @BindView(R.id.spnPersonId) Spinner spnPersonId;
    @BindView(R.id.spnDesignation) Spinner spnDesignation;
    @BindView(R.id.spnPersonVisit) Spinner spnPersonVisit;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectDesignation=new ArrayList<>();
    String id2,email,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitorgatepass);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        email=data_model.getEmail();
        role=data_model.getRole();
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, UpdateVisitorGatePassActivity.this));
        edtTime.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(UpdateVisitorGatePassActivity.this, (timePicker, selectedHour, selectedMinute) -> {

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


        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            tvFirmName.setText(jsonObject.get("firm_name").toString());
            edtAprovalBy.setText(jsonObject.get("approval").toString());
            edtReason.setText(jsonObject.get("reason").toString());
            edtDate.setText(jsonObject.get("visit_date").toString());
            edtTime.setText(jsonObject.get("visit_time").toString());
             Clinet_name.setText(jsonObject.get("client").toString());
            SelectClientBranch.add(jsonObject.get("branch").toString());
            spnBranch.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClientBranch));
            SelectPersonName.add(jsonObject.get("person_name").toString());
            spnPersonName.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonName));
            SelectPersonId.add(jsonObject.get("person_id").toString());
            spnPersonId.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonId));
            SelectPersonVisited.add(jsonObject.get("person_visited").toString());
            spnPersonVisit.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonVisited));
            SelectDesignation.add(jsonObject.get("designationnn").toString());
            spnDesignation.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectDesignation));
            id2=jsonObject.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest1=new StringRequest(Request.Method.POST, Config.URL_CLient, response -> {
            Log.e("response",response);
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
        SelectClientBranch.add("Select AnyOne");
        StringRequest stringRequest=new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                spnBranch.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
        btnRegister.setOnClickListener(v -> {
            String firm_name = tvFirmName.getText().toString();
            String approval = edtAprovalBy.getText().toString();
            String reason = edtReason.getText().toString();
            String visit_date = edtDate.getText().toString();
            String visit_time = edtTime.getText().toString();
            String declaration = chek.getText().toString();
            String client=Clinet_name.getText().toString();
            String branch=(String) spnBranch.getSelectedItem();
            String person_name=(String) spnPersonName.getSelectedItem();
            String person_id=(String) spnPersonId.getSelectedItem();
            String designation=(String) spnDesignation.getSelectedItem();
            String person_visited=(String) spnPersonVisit.getSelectedItem();

            if(client.equals("")){
                FancyToast.makeText(UpdateVisitorGatePassActivity.this,"Select Client",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }

            else
            {
                progress_bar.setVisibility(View.VISIBLE);
                postVisitorGatePass(email,role,client, branch, person_name, firm_name, designation, approval,
                        person_visited, reason, visit_date, visit_time, declaration,person_id,id2);
            }
        });
        SelectPersonName.add("Select Anyone");
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

                spnPersonName.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                spnPersonId.setAdapter(new ArrayAdapter<>(UpdateVisitorGatePassActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
         }, error -> Toast.makeText(UpdateVisitorGatePassActivity.this,error.toString(), Toast.LENGTH_SHORT).show()){
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
    }

    public void postVisitorGatePass(String email,  String role,String client, String branch, String person_name, String firm_name, String designation,
                                    String approval, String person_visited, String reason, String visit_date, String visit_time, String declaration,String personId,String id2) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
         Call<AddVisitorGatePassModel> call = apiService.UpdateVisitorGatePass( email,role,client, branch, person_name, firm_name, designation, approval, person_visited, reason, visit_date, visit_time, declaration,personId,id2
        );
        call.enqueue(new Callback<AddVisitorGatePassModel>() {
            @Override
            public void onResponse(Call<AddVisitorGatePassModel> call, Response<AddVisitorGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(UpdateVisitorGatePassActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();

            }

            @Override
            public void onFailure(Call<AddVisitorGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }

}