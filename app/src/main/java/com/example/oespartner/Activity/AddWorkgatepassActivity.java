package com.example.oespartner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

    ArrayList<String> SelectPersonId = new ArrayList<>();
    ArrayList<String> SelectPersonVisited = new ArrayList<>();
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPartner = new ArrayList<>();
    ArrayList<String> SelectVehicalNo = new ArrayList<>();
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

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
    @BindView(R.id.edtDlNo)
    EditText edtDlNo;
    @BindView(R.id.edtDateValidoflicence)
    EditText edtDateValidoflicence;
    @BindView(R.id.spnVihicalNo)
    Spinner spnVihicalno;
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
    String email;
    String role;
    String work_reference_no;
    String work_description ;
    String work_valid_upto;
    String visa_validity ;
    String declaration;
    String j_declaration ;
    String h_declaration ;
    String p_valid_upto ;
    String id1 = id.getText().toString();
    String driving_license_no;
    String license_valid_upto;
    String vehicle_no;
    String helper_name;
    String eye_test_date;
    String training_certificate_no;
    String training_valid_upto;
    String client ;
    String branch ;
    String person_name;
    String person_id;
    String designation ;
    String stakeholder_id ;
    String police_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workgatepass);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        LinearLayout driverlayout = findViewById(R.id.layout_driver);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        email = data_model.getEmail();
        role = data_model.getRole();
        Calendar calendar = Calendar.getInstance();

        edtDate.setOnClickListener(v -> Constants.DateDialog(edtDate, AddWorkgatepassActivity.this));
        edtDate2.setOnClickListener(v -> Constants.DateDialog(edtDate2, AddWorkgatepassActivity.this));
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
                } else {
                    driverlayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SelectVehicalNo.add("Select");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Spinner_VehicleApi, response -> {
            Log.e("Response", response);
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
        StringRequest request = new StringRequest(Config.URL_ClientBranch, response -> {
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
        queue4.add(request);

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


        SelectPersonName.add("Select");
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Config.URL_PersonName, response -> {
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
         }, error -> Toast.makeText(AddWorkgatepassActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
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
       btnRegister.setOnClickListener(v -> {
           if (work_reference_no.equals(edtReference.getText().toString().isEmpty())) {
               edtReference.setError("!isEmpty");
           } else if (work_description.equals(edtDescription.getText().toString().isEmpty())) {
               edtDescription.setError("!isEmpty");
           } else if (work_valid_upto.equals(edtDate.getText().toString().isEmpty())) {
               edtDate.setError("!isEmpty");
           } else if (visa_validity.equals(edtDate2.getText().toString().isEmpty())) {
               edtDate2.setError("!isEmpty");
           } else if (declaration.equals(chk1.isChecked())) {
               chk1.setError("!PleaseCheckDeclaration");
           } else if (j_declaration.equals(chk2.isChecked())) {
               chk2.setError("!PleaseCheckJDeclaration");
           } else if (h_declaration.equals(chk3.isChecked())) {
               chk3.setError("!PleaseCheckHDeclaration");
           } else if (p_valid_upto.equals(edtWorkValidDate.getText().toString().isEmpty())) {
               edtWorkValidDate.setError("!isEmpty");
           } else if (driving_license_no.equals(edtDlNo.getText().toString().isEmpty())) {
               edtDlNo.setError("!isEmpty");
           } else if (license_valid_upto.equals(edtDateValidoflicence.getText().toString().isEmpty())) {
               edtDateValidoflicence.setError("!isEmpty");
           } else if (vehicle_no.equals(spnVihicalno.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnVihicalno.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (helper_name.equals(edtHelperName.getText().toString().isEmpty())) {
               edtHelperName.setError("!isEmpty");
           } else if (eye_test_date.equals(edtLastDateofEyeTest.getText().toString().isEmpty())) {
               edtLastDateofEyeTest.setError("!isEmpty");
           } else if (training_certificate_no.equals(edtTrainingCertificateno.getText().toString().isEmpty())) {
               edtTrainingCertificateno.setError("!isEmpty");
           } else if (training_valid_upto.equals(edtDateoftrainingValid.getText().toString().isEmpty())) {
               edtDateoftrainingValid.setError("!isEmpty");
           } else if (client.equals(spnClient.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnClient.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (branch.equals(spnBranch.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnBranch.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (person_name.equals(spnBranch.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnPersonName.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (person_id.equals(spnPersonId.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnPersonId.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (designation.equals(spnDesignation.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnDesignation.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (stakeholder_id.equals(spnStackHolder.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnStackHolder.getSelectedView();
               errorText.setError("!isEmpty");
           } else if (police_verify.equals(spnPoliceVerify.getSelectedItem().toString().isEmpty())) {
               TextView errorText = (TextView) spnPoliceVerify.getSelectedView();
               errorText.setError("!isEmpty");
           }

           if (client.equals("")) {
               FancyToast.makeText(AddWorkgatepassActivity.this, "Select Client", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
               return;
           } else {
               progress_bar.setVisibility(View.VISIBLE);
               postWorkGatePass(email, role, client, branch, person_name, person_id, designation,
                       work_reference_no, work_description, work_valid_upto, police_verify, visa_validity,
                       declaration, j_declaration, h_declaration, stakeholder_id, p_valid_upto,
                       id1, driving_license_no, license_valid_upto, vehicle_no, helper_name, eye_test_date, training_certificate_no, training_valid_upto);
               onBackPressed();
           }

           btn_uploadreport.setOnClickListener(v1 -> {
               Intent intent = new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 1);
           });

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
                MultipartBody.Part body = MultipartBody.Part.createFormData("security_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void postWorkGatePass(String email, String role, String client, String branch, String person_name, String person_id, String designation,
                                 String work_reference_no, String work_description, String work_valid_upto, String police_verify, String visa_validity,
                                 String declaration, String j_declaration, String h_declaration,
                                 String stakeholder_id, String p_valid_upto, String id, String driving_license_no, String license_valid_upto,
                                 String vehicle_no, String helper_name, String eye_test_date, String training_certificate_no, String training_valid_upto) {
        //LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        Call<AddWorkGatePassModel> call = apiService.AddWorkGatePass(email, role, client, branch, person_name, person_id, designation,
                work_reference_no, work_description, work_valid_upto, police_verify, visa_validity, declaration, j_declaration, h_declaration, stakeholder_id,
                p_valid_upto, id, driving_license_no, license_valid_upto, vehicle_no, helper_name, eye_test_date, training_certificate_no, training_valid_upto);
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
