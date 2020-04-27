package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.model.AddVisitorGatePassModel;
import com.example.oespartner.model.BranchModel;
import com.example.oespartner.model.ClientModel;
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

public class AddVisitorgatepassActivity extends AppCompatActivity {
    Calendar calendar;
    int DD, MM, YY;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectClientType = new ArrayList<>();
    ArrayList<String> SelectBranchType = new ArrayList<>();
    String email, role;
    @BindView(R.id.edtDate)
    EditText edtDate;
    @BindView(R.id.edtTime)
    EditText edtTime;
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
    @BindView(R.id.edtAprovalBy)
    EditText edtAprovalBy;
    @BindView(R.id.edtReason)
    EditText edtReason;
    @BindView(R.id.spnBranch)
    Spinner spnBranch;
    @BindView(R.id.spnPersonName)
    Spinner spnPersonName;
    @BindView(R.id.spnPersonId)
    Spinner spnPersonId;
    @BindView(R.id.spnDesignation)
    Spinner spnDesignation;
    @BindView(R.id.spnPersonVisit)
    Spinner spnPersonVisit;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    String Branch_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitorgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        LinearLayout layputothers=findViewById(R.id.ll_othersvisitor);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        FastSave.init(AddVisitorgatepassActivity.this);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        tvFirmName.setText(data_model.getFirmName());

        spnDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==10){
                 layputothers.setVisibility(View.VISIBLE);
                }else{
                    layputothers.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SelectClientBranch.add("Select Branch");
        StringRequest request = new StringRequest(Request.Method.POST,Config.URL_ClientBranch, response -> {
            Log.e("branch",response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    SelectClientBranch.add(jsonObject.get("branch").toString());
                    Branch_id=jsonObject.get("branch_id").toString();
                }
                spnBranch.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString())){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("client", data_model.getClient());
                return params;
            }
        };
        RequestQueue queue4 = Volley.newRequestQueue(this);
        queue4.add(request);

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


        SelectPersonName.add("Select Person Name");
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Config.URL_PersonName, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    String catogery = jsonObject1.getString("person_name");
                    String catogery1 = jsonObject1.getString("id");
                    SelectPersonName.add(catogery);
                    SelectPersonId.add(catogery1);
                }

                spnPersonName.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                spnPersonId.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(AddVisitorgatepassActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
                params.put("email", data_model.getEmail());
                params.put("role", data_model.getRole());
                return params;
            }
        };
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest2);

        spnPersonName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String dAdress = spnPersonName.getItemAtPosition(spnPersonName.getSelectedItemPosition()).toString();
                if (position == 1) {
                    spnPersonId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String id = spnPersonId.getItemAtPosition(spnPersonId.getSelectedItemPosition()).toString();
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
        StringRequest stringRequest3 = new StringRequest(Config.URL_PersonVisit, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("name");
                    SelectPersonVisited.add(catogery);
                }
                spnPersonVisit.setAdapter(new ArrayAdapter<>(AddVisitorgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonVisited));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue3 = Volley.newRequestQueue(this);
        queue3.add(stringRequest3);
        spnPersonVisit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress = spnPersonVisit.getItemAtPosition(spnPersonVisit.getSelectedItemPosition()).toString();
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
            String firm_name = tvFirmName.getText().toString();
            String approval = edtAprovalBy.getText().toString();
            String reason = edtReason.getText().toString();
            String visit_date = edtDate.getText().toString();
            String visit_time = edtTime.getText().toString();
            String declaration = chek.getText().toString();
            String client =Clinet_name.getText().toString();
            String branch =Branch_id;
            String person_name = (String) spnPersonName.getSelectedItem();
            String person_id = (String) spnPersonId.getSelectedItem();
            String designation = (String) spnDesignation.getSelectedItem();
            String person_visited = (String) spnPersonVisit.getSelectedItem();

            if (client.equals("")) {
                FancyToast.makeText(AddVisitorgatepassActivity.this, "Select Client", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                postVisitorGatePass(data_model.getEmail(), data_model.getRole(), client, branch, person_name, firm_name, designation, approval,
                        person_visited, reason, visit_date, visit_time, declaration, person_id);
                onBackPressed();
            }
        });
    }

    public void postVisitorGatePass(String email, String role, String client, String branch, String person_name, String firm_name, String designation,
                                    String approval, String person_visited, String reason, String visit_date, String visit_time, String declaration, String personId) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);

        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<AddVisitorGatePassModel> call = apiService.AddVisitorGatePass(email, role, client, branch, person_name, firm_name, designation, approval, person_visited, reason, visit_date, visit_time, declaration, personId
        );
        call.enqueue(new Callback<AddVisitorGatePassModel>() {
            @Override
            public void onResponse(Call<AddVisitorGatePassModel> call, Response<AddVisitorGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddVisitorgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }

            @Override
            public void onFailure(Call<AddVisitorGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }

}






