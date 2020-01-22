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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddWorkgatepassActivity extends AppCompatActivity {
    ImageView imgBack;

    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPartner = new ArrayList<>();

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @BindView(R.id.spnClient)
    Spinner spnClient;
    @BindView(R.id.spnBranch)
    Spinner spnBranch;
    @BindView(R.id.spnPersonName)
    Spinner spnPersonName;
    @BindView(R.id.spnDesignation)
    Spinner spnDesignation;
    @BindView(R.id.spnStackHolder)
    Spinner spnStackHolder;
    @BindView(R.id.spnPersonId)
    Spinner spnPersonId;
    @BindView(R.id.spnPoliceVerify)
    Spinner spnPoliceVerify;
    @BindView(R.id.edtReference)
    EditText edtReference;
    @BindView(R.id.edtDescription)
    EditText edtDescription;
    @BindView(R.id.edtWorkValidDate)
    EditText edtWorkValidDate;
    @BindView(R.id.edtDate)
    EditText edtDate;
    @BindView(R.id.edtDate2)
    EditText edtDate2;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.chk1)
    CheckBox chk1;
    @BindView(R.id.chk2)
    CheckBox chk2;
    @BindView(R.id.chk3)
    CheckBox chk3;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    String email;
    String role;
    Date current_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        email = data_model.getEmail();
        role = data_model.getRole();
        Calendar calendar = Calendar.getInstance();

        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, AddWorkgatepassActivity.this));

        edtDate2.setOnClickListener(v -> Constants.DateDialog(edtDate2, AddWorkgatepassActivity.this));


        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        Date currentDate1 = new Date();
        System.out.println(dateFormat.format(currentDate));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // convert date to calendar
        Calendar c1 = Calendar.getInstance();
        c1.setTime(currentDate1);

        // manipulate date
        c.add(Calendar.YEAR,0 );
        c.add(Calendar.MONTH, 3);
        c.add(Calendar.DATE, 0); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 0);
        c.add(Calendar.MINUTE, 0);
        c.add(Calendar.SECOND, 0);

        // manipulate date
        c1.add(Calendar.YEAR, 0);
        c1.add(Calendar.MONTH, 0);
        c1.add(Calendar.DATE, 15); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c1.add(Calendar.HOUR, 0);
        c1.add(Calendar.MINUTE, 0);
        c1.add(Calendar.SECOND, 0);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        Date currentDatePlusOne1 = c1.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));
        System.out.println(dateFormat.format(currentDatePlusOne1));

//        edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne));
//        edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne1));
//
//


        spnPoliceVerify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(spnPoliceVerify.getSelectedItemId()==0){
                    edtWorkValidDate.setText("");
                }
                if(spnPoliceVerify.getSelectedItemId()==1){
                    edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne));
                }
                if(spnPoliceVerify.getSelectedItemId()==2){
                    edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne1));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                edtWorkValidDate.setText(dateFormat.format(""));
            }

        });

        SelectPartner.add("Select PartnerId");
        StringRequest stringRequest4 = new StringRequest(Config.URL_Partner, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("id");
                    SelectPartner.add(catogery);
                }
                spnStackHolder.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPartner));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest4);
        spnStackHolder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress = spnStackHolder.getItemAtPosition(spnStackHolder.getSelectedItemPosition()).toString();
                //Toast.makeText(getApplicationContext(),dAdress,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        SelectClientBranch.add("Select Branch");
        StringRequest stringRequest = new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                spnBranch.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue4 = Volley.newRequestQueue(this);
        queue4.add(stringRequest);

        SelectClient.add("Select Client");
        StringRequest stringRequest1 = new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("company_name");
                    SelectClient.add(catogery);
                }
                spnClient.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest1);


        SelectPersonName.add("Select Person Name");
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Config.URL_PersonName, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int j = 0; j < jsonArray.length(); ++j) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                        String catogery = jsonObject1.getString("person_name");
                        String catogery1 = jsonObject1.getString("id");
                        SelectPersonName.add(catogery);
                        SelectPersonId.add(catogery1);
                    }

                    spnPersonName.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                    spnPersonId.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonId));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AddWorkgatepassActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(AddWorkgatepassActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("role", role);
                return params;
            }
        };
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest2);
        spnPersonName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String dAdress = spnPersonName.getItemAtPosition(spnPersonName.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(), dAdress, Toast.LENGTH_LONG).show();
                if (position == 1) {
                    spnPersonId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String id = spnPersonId.getItemAtPosition(spnPersonId.getSelectedItemPosition()).toString();
                            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
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
            String client = (String) spnClient.getSelectedItem();
            String branch = (String) spnBranch.getSelectedItem();
            String person_name = (String) spnPersonName.getSelectedItem();
            String person_id = (String) spnPersonId.getSelectedItem();
            String designation = (String) spnDesignation.getSelectedItem();
            String stakeholder_id = (String) spnStackHolder.getSelectedItem();
            String police_verify = (String) spnPoliceVerify.getSelectedItem();

            if (client.equals("")) {
                FancyToast.makeText(AddWorkgatepassActivity.this, "Select Client", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                return;
            } else {
                progress_bar.setVisibility(View.VISIBLE);
                postWorkGatePass(email, role, client, branch, person_name, person_id, designation,
                        work_reference_no, work_description, work_valid_upto, police_verify, visa_validity,
                        declaration, j_declaration, h_declaration, stakeholder_id, p_valid_upto, id1);
                onBackPressed();
            }
        });
    }

    public void postWorkGatePass(String email, String role, String client, String branch, String person_name, String person_id, String designation,
                                 String work_reference_no, String work_description, String work_valid_upto, String police_verify, String visa_validity,
                                 String declaration, String j_declaration, String h_declaration, String stakeholder_id, String p_valid_upto, String id) {
        //LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<AddWorkGatePassModel> call = apiService.AddWorkGatePass(email, role, client, branch, person_name, person_id, designation,
                work_reference_no, work_description, work_valid_upto, police_verify, visa_validity, declaration, j_declaration, h_declaration, stakeholder_id, p_valid_upto, id
        );
        call.enqueue(new Callback<AddWorkGatePassModel>() {
            @Override
            public void onResponse(Call<AddWorkGatePassModel> call, Response<AddWorkGatePassModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddWorkgatepassActivity.this, "Data submitted successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                finish();

            }

            @Override
            public void onFailure(Call<AddWorkGatePassModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
            }
        });
    }


}
