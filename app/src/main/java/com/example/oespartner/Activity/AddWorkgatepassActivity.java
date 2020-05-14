package com.example.oespartner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
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
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkgatepassActivity extends AppCompatActivity {
    ImageView imgBack;
    Button upload_security;
    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPartner = new ArrayList<>();
    ArrayList<String> SelectVehicalNo = new ArrayList<>();
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @BindView(R.id.spnBranch)
    Spinner spnBranch;
    @BindView(R.id.spnPersonName)
    Spinner spnPersonName;
    @BindView(R.id.spnDesignation)
    Spinner spnDesignation;
    @BindView(R.id.spn_security)
    Spinner spn_security;
    @BindView(R.id.client_name)
    TextView Clinet_name;
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
    @BindView(R.id.edtDlNo)
    EditText edtDlNo;
    @BindView(R.id.edtDateValidoflicence)
    EditText edtDateValidoflicence;
    @BindView(R.id.spnVihicalNo)
    Spinner spnVihicalno;
    @BindView(R.id.spnPartnerName)
    Spinner spnPartnerName;
    @BindView(R.id.edtHelperName)
    EditText edtHelperName;
    @BindView(R.id.edtLastDateofEyeTest)
    EditText edtLastDateofEyeTest;
    @BindView(R.id.edtTrainingCertificateno)
    EditText edtTrainingCertificateno;
    @BindView(R.id.btn_uploadreport)
    Button btn_uploadreport;
    @BindView(R.id.edtDateoftrainingValid)
    EditText edtDateoftrainingValid;
    @BindView(R.id.et_personid)
    TextView et_personid;
    @BindView(R.id.edtReferencesecurity)
    EditText edtReferencesecurity;
    @BindView(R.id.othersaddwork)
    TextInputLayout othersaddwork;
    @BindView(R.id.tv_upload_security)
    TextView tv_upload_security;
    String email;
    String role;
    String work_reference_no;
    String work_description;
    String work_valid_upto;
    String visa_validity;
    String declaration;
    String j_declaration;
    String h_declaration;
    String p_valid_upto;
    String id1;
    String driving_license_no;
    String license_valid_upto;
    String vehicle_no;
    String helper_name;
    String eye_test_date;
    String training_certificate_no;
    String training_valid_upto;
    String clientName;
    String branch;
    String person_name;
    String person_id;
    String designation;
    String stakeholder_id;
    String police_verify;
    String Client;
    String Partener_id;
    String PartnerName;
    String security_reference_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        LinearLayout driverlayout = findViewById(R.id.layout_driver);
        LinearLayout security_layout = findViewById(R.id.security_layout);
        LinearLayout security_referenceNo = findViewById(R.id.security_referenceNo);
        LinearLayout security_filechoose = findViewById(R.id.security_filechoose);
        upload_security = findViewById(R.id.upload_security);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        email = data_model.getEmail();
        role = data_model.getRole();
        Client = data_model.getClient();
        Calendar calendar = Calendar.getInstance();
        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, AddWorkgatepassActivity.this));
        // edtDate2.setOnClickListener(v -> Constants.DateDialog(edtDate2, AddWorkgatepassActivity.this));
        edtDateoftrainingValid.setOnClickListener(v -> Constants.DateDialog(edtDateoftrainingValid, AddWorkgatepassActivity.this));
        edtLastDateofEyeTest.setOnClickListener(v -> Constants.DateDialog(edtLastDateofEyeTest, AddWorkgatepassActivity.this));
        edtDateValidoflicence.setOnClickListener(v -> Constants.DateDialog(edtDateValidoflicence, AddWorkgatepassActivity.this));
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
        c.add(Calendar.YEAR, 0);
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


        spnPoliceVerify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (spnPoliceVerify.getSelectedItemId() == 0) {
                    edtWorkValidDate.setText("");
                }
                if (spnPoliceVerify.getSelectedItemId() == 1) {
                    edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne));
                }
                if (spnPoliceVerify.getSelectedItemId() == 2) {
                    edtWorkValidDate.setText(dateFormat.format(currentDatePlusOne1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                edtWorkValidDate.setText(dateFormat.format(""));
            }

        });
        spnDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 5) {
                    driverlayout.setVisibility(View.VISIBLE);
                } else if (position == 9) {
                    security_layout.setVisibility(View.VISIBLE);
                } else if (position == 10) {
                    othersaddwork.setVisibility(View.VISIBLE);
                } else {
                    driverlayout.setVisibility(View.GONE);
                    security_layout.setVisibility(View.GONE);
                    othersaddwork.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spn_security.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    security_referenceNo.setVisibility(View.VISIBLE);
                    security_filechoose.setVisibility(View.VISIBLE);
                } else {
                    security_referenceNo.setVisibility(View.GONE);
                    security_filechoose.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SelectVehicalNo.add("Select");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Spinner_VehicleApi, response -> {
             try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("vehicle_no");
                    SelectVehicalNo.add(catogery);
                }
                spnVihicalno.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectVehicalNo));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Log.e("error", error.getMessage())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("role", role);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
                spnPartnerName.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPartner));

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

        SelectClientBranch.add("Select Branch");
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ClientBranch, response -> {
             try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SelectClientBranch.add(jsonObject.get("branch").toString());
                }
                spnBranch.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
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

        SelectPersonName.add("Select");
        SelectPersonName.add(data_model.getName());
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Config.URL_PersonName, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    String catogery = jsonObject1.getString("person_name");
                    String catogery1 = jsonObject1.getString("person_id");
                    SelectPersonName.add(catogery);
                    et_personid.setText(catogery1);
                    person_id = jsonObject1.getString("person_id");
                }
                spnPersonName.setAdapter(new ArrayAdapter<>(AddWorkgatepassActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()) ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("role", role);
                return params;
            }
        };
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest2);

        btnRegister.setOnClickListener(v -> {
            builder.addFormDataPart("client", Objects.requireNonNull(Clinet_name.getText().toString()))
                    .addFormDataPart("branch", Objects.requireNonNull(spnBranch.getSelectedItem().toString()))
                    .addFormDataPart("person_name", Objects.requireNonNull(spnPersonName.getSelectedItem().toString()))
                    .addFormDataPart("person_id", Objects.requireNonNull(et_personid.getText().toString()))
                    .addFormDataPart("designation", Objects.requireNonNull(spnDesignation.getSelectedItem().toString()))
                    .addFormDataPart("driving_license_no", Objects.requireNonNull(edtDlNo.getText().toString()))
                    .addFormDataPart("license_valid_upto", Objects.requireNonNull(edtDateValidoflicence.getText().toString()))
                    .addFormDataPart("vehicle_no", Objects.requireNonNull(Objects.requireNonNull(spnVihicalno.getSelectedItem().toString())))
                    .addFormDataPart("helper_name", Objects.requireNonNull(Objects.requireNonNull(edtHelperName.getText().toString())))
                    .addFormDataPart("eye_test_date", Objects.requireNonNull(Objects.requireNonNull(edtLastDateofEyeTest.getText().toString())))
                    .addFormDataPart("training_certificate_no", Objects.requireNonNull(Objects.requireNonNull(edtTrainingCertificateno.getText().toString())))
                    .addFormDataPart("training_valid_upto", Objects.requireNonNull(Objects.requireNonNull(edtDateoftrainingValid.getText().toString())))
                    .addFormDataPart("ex_armed", Objects.requireNonNull(Objects.requireNonNull(spn_security.getSelectedItem().toString())))
                    .addFormDataPart("security_reference_no", Objects.requireNonNull(Objects.requireNonNull(edtReferencesecurity.getText().toString())))
                    .addFormDataPart("work_reference_no", Objects.requireNonNull(Objects.requireNonNull(edtReference.getText().toString())))
                    .addFormDataPart("work_description", Objects.requireNonNull(Objects.requireNonNull(edtDescription.getText().toString())))
                    .addFormDataPart("work_valid_upto", Objects.requireNonNull(Objects.requireNonNull(edtDate.getText().toString())))
                    .addFormDataPart("visa_validity", Objects.requireNonNull(Objects.requireNonNull(edtWorkValidDate.getText().toString())))
                    .addFormDataPart("p_valid_upto", Objects.requireNonNull(Objects.requireNonNull(edtWorkValidDate.getText().toString())))
                    .addFormDataPart("p_eye_test_date", Objects.requireNonNull(Objects.requireNonNull(edtLastDateofEyeTest.getText().toString())))
                    .addFormDataPart("p_training_certificate_validity", Objects.requireNonNull(Objects.requireNonNull(edtDateoftrainingValid.getText().toString())))
                    .addFormDataPart("stakeholder_id", Objects.requireNonNull(Objects.requireNonNull(Partener_id)))
                    .addFormDataPart("role", Objects.requireNonNull(Objects.requireNonNull(data_model.getRole())))
                    .addFormDataPart("email", Objects.requireNonNull(Objects.requireNonNull(data_model.getEmail())))
                    .addFormDataPart("police_verify", Objects.requireNonNull(Objects.requireNonNull(spnPoliceVerify.getSelectedItem().toString())))
                    .addFormDataPart("firm_name", Objects.requireNonNull(Objects.requireNonNull(data_model.getFirmName())));

            new ApiClient().service.AddWorkGatePass(builder.build()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Intent i = new Intent();
                    setResult(Activity.RESULT_OK,i);
                    finish();
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btn_uploadreport.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 1);
        });
        upload_security.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == 1) {
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("report[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 2) {
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("security_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
