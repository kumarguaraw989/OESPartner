package com.example.oespartner.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oespartner.MainActivity;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    LinearLayout register_security, register_Dealer, register_contractor, register_consumer, register_supplier, register_transporter;
    Button btnRegister, btnUploadPhoto, btnUploadSignature;
    TextView txtRegister;
    ProgressBar loading;
    //edit text ids common for all
    EditText name, email, phone, pingenerate, designation, bloodgroup, biomatricdata, aadharno;
    //edit text ids only for security
    EditText edtSecurityGst, edtDepartmentSecurity, edtPanSecurity, edtEsiRegistrationSecurity, edtPfRegistration, edtLabourRegistrationSecurity, edtFirmEmailSecurity, edtAddressecurtiy;
    //edit text ids only for dealer
    EditText edtDealercodeDealer, edtFirmnameDelaer, edtGstnoDealer, edtPanDelaer, edtEsiregistrationDealer, edtPfregistrationDealer, edtLabourregistrationDealer, edtFirmemailDealer, edtofficeAddressDealer;
    //edit text ids only for contractor
    EditText edtVendorcodeContractor, edtFirmnameContractor, edtGstnoContractor, edtPanContractor, edtEsiregistrationContractor, edtPfregistrationContractor, edtLabourregistrationContractor, edtOfficeaddressContractor;
    //edit text ids only for consumer
    EditText edtDealerCodeConsumer, edtFirmemailContrator, edtFirmnameConsumer, edtGstnoConsumer, edtPanConsumer, edtEsiregistrationConsumer, edtPfregistrationConsumer, edtLaboutregistrationConsumer, edtFirmemailConsumer, edtOfficeaddressConsumer;
    //edit text ids only for supplier
    EditText edtVendoecodeSupplier, edtFirmnameSupplier, edtGstnoSupplier, edtPanSupplier, edtEsiregistrationSupplier, edtPfregistrationSupplier, edtLabourregistrationSupplier, edtFirmemailSupplier, edtOfficeaddressSupplier;
    //edit text ids only for transporter
    EditText edtDelaercodeTransporter, edtFirmnameTransporter, edtGstnoTransporter, edtPannoTransporter, edtEstregistrationTransporter, edtPfregistrationTransporter, edtLabourregistrationTransporter, edtFirmemailTransporter, edtOfficeAddressTransporter;
    private Spinner SelectRole, SelectClient;
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    ArrayList<String> SelectUserType = new ArrayList<>();
    ArrayList<String> SelectClientType = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        loading = findViewById(R.id.loading);
        btnRegister = findViewById(R.id.btnRegister);
        txtRegister = findViewById(R.id.txtRegister);
        SelectRole = findViewById(R.id.spnSelectUser);
        SelectClient = findViewById(R.id.spin_client);
        btnUploadPhoto = findViewById(R.id.btnPhoto);
        btnUploadSignature = findViewById(R.id.btnSignature);
        SelectUserType.add("Select user");
        SelectClientType.add("Select client");
        //all edit text id is here
        name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        phone = findViewById(R.id.edtPhoneNo);
        pingenerate = findViewById(R.id.edtPingenerate);
        designation = findViewById(R.id.edtDesignation);
        bloodgroup = findViewById(R.id.edtBloodgroup);
        biomatricdata = findViewById(R.id.edtBiomatricdata);
        aadharno = findViewById(R.id.edtAadharNo);
        //Transporter all edit text ids are here
        edtDelaercodeTransporter = findViewById(R.id.edtDelaercodeTransporter);
        edtFirmnameTransporter = findViewById(R.id.edtFirmnameTransporter);
        edtGstnoTransporter = findViewById(R.id.edtGstnoTransporter);
        edtPannoTransporter = findViewById(R.id.edtPannoTransporter);
        edtEstregistrationTransporter = findViewById(R.id.edtEstregistrationTransporter);
        edtPfregistrationTransporter = findViewById(R.id.edtPfregistrationTransporter);
        edtLabourregistrationTransporter = findViewById(R.id.edtLabourregistrationTransporter);
        edtFirmemailTransporter = findViewById(R.id.edtFirmemailTransporter);
        edtOfficeAddressTransporter = findViewById(R.id.edtOfficeAddressTransporter);

        //supplier edit text id are here
        edtVendoecodeSupplier = findViewById(R.id.edtVendoecodeSupplier);
        edtFirmnameSupplier = findViewById(R.id.edtFirmnameSupplier);
        edtGstnoSupplier = findViewById(R.id.edtGstnoSupplier);
        edtPanSupplier = findViewById(R.id.edtPanSupplier);
        edtEsiregistrationSupplier = findViewById(R.id.edtEsiregistrationSupplier);
        edtPfregistrationSupplier = findViewById(R.id.edtPfregistrationSupplier);
        edtLabourregistrationSupplier = findViewById(R.id.edtLabourregistrationSupplier);
        edtFirmemailSupplier = findViewById(R.id.edtFirmemailSupplier);
        edtOfficeaddressSupplier = findViewById(R.id.edtOfficeaddressSupplier);
        //security edit text id are here
        edtSecurityGst = findViewById(R.id.edtGstNoSecurity);
        edtDepartmentSecurity = findViewById(R.id.edtDepartmentSecurity);
        edtPanSecurity = findViewById(R.id.edtPanSecurity);
        edtEsiRegistrationSecurity = findViewById(R.id.edtEsiRegistrationSecurity);
        edtPfRegistration = findViewById(R.id.edtPfRegistration);
        edtLabourRegistrationSecurity = findViewById(R.id.edtLabourRegistrationSecurity);
        edtFirmEmailSecurity = findViewById(R.id.edtFirmEmailSecurity);
        edtAddressecurtiy = findViewById(R.id.edtAddressecurtiy);

        //all dealer edit text ids are here
        edtDealercodeDealer = findViewById(R.id.edtDealercodeDealer);
        edtFirmnameDelaer = findViewById(R.id.edtFirmnameDelaer);
        edtGstnoDealer = findViewById(R.id.edtGstnoDealer);
        edtPanDelaer = findViewById(R.id.edtPanDelaer);
        edtEsiregistrationDealer = findViewById(R.id.edtEsiregistrationDealer);
        edtPfregistrationDealer = findViewById(R.id.edtPfregistrationDealer);
        edtLabourregistrationDealer = findViewById(R.id.edtLabourregistrationDealer);
        edtFirmemailDealer = findViewById(R.id.edtFirmemailDealer);
        edtofficeAddressDealer = findViewById(R.id.edtofficeAddressDealer);

        //all contractor edit text ids are here
        edtVendorcodeContractor = findViewById(R.id.edtVendorcodeContractor);
        edtFirmnameContractor = findViewById(R.id.edtFirmnameContractor);
        edtFirmemailContrator = findViewById(R.id.edtFirmemailContractor);
        edtGstnoContractor = findViewById(R.id.edtGstnoContractor);
        edtPanContractor = findViewById(R.id.edtPanContractor);
        edtEsiregistrationContractor = findViewById(R.id.edtEsiregistrationContractor);
        edtPfregistrationContractor = findViewById(R.id.edtPfregistrationContractor);
        edtLabourregistrationContractor = findViewById(R.id.edtLabourregistrationContractor);
        edtOfficeaddressContractor = findViewById(R.id.edtOfficeaddressContractor);
        //all consumeredit text ids are here
        edtDealerCodeConsumer = findViewById(R.id.edtDealerCodeConsumer);
        edtFirmnameConsumer = findViewById(R.id.edtFirmnameConsumer);
        edtGstnoConsumer = findViewById(R.id.edtGstnoConsumer);
        edtPanConsumer = findViewById(R.id.edtPanConsumer);
        edtEsiregistrationConsumer = findViewById(R.id.edtEsiregistrationConsumer);
        edtPfregistrationConsumer = findViewById(R.id.edtPfregistrationConsumer);
        edtLaboutregistrationConsumer = findViewById(R.id.edtLaboutregistrationConsumer);
        edtFirmemailConsumer = findViewById(R.id.edtFirmemailConsumer);
        edtOfficeaddressConsumer = findViewById(R.id.edtOfficeaddressConsumer);
        //all layouts id are here
        register_security = findViewById(R.id.include_registersecurity);
        register_Dealer = findViewById(R.id.layout_register_contractor);
        register_contractor = findViewById(R.id.layout_register_contractor);
        register_consumer = findViewById(R.id.layout_register_consumer);
        register_supplier = findViewById(R.id.layout_register_supplier);
        register_transporter = findViewById(R.id.layout_register_transporter);
        //API CALLING FOR SPINNER1
        StringRequest stringRequest = new StringRequest(Config.URL_role, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("role");
                    SelectUserType.add(catogery);
                }
                SelectRole.setAdapter(new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectUserType));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        SelectRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String dAdress = SelectRole.getItemAtPosition(SelectRole.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(), dAdress, Toast.LENGTH_LONG).show();

                if (position == 1) {
                    register_transporter.setVisibility(View.VISIBLE);
                    register_security.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_contractor.setVisibility(View.GONE);
                    register_consumer.setVisibility(View.GONE);
                    register_supplier.setVisibility(View.GONE);
                }
                else if (position == 2) {
                    register_supplier.setVisibility(View.VISIBLE);
                    register_security.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_contractor.setVisibility(View.GONE);
                    register_consumer.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                }
                else  if (position == 3) {
                    register_consumer.setVisibility(View.VISIBLE);
                    register_supplier.setVisibility(View.GONE);
                    register_security.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_contractor.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                }
                else  if (position == 4) {
                    register_contractor.setVisibility(View.VISIBLE);
                    register_consumer.setVisibility(View.GONE);
                    register_supplier.setVisibility(View.GONE);
                    register_security.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                }
                else if (position == 5) {
                    register_Dealer.setVisibility(View.VISIBLE);
                    register_contractor.setVisibility(View.GONE);
                    register_consumer.setVisibility(View.GONE);
                    register_supplier.setVisibility(View.GONE);
                    register_security.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                }
                else if (position == 6) {
                    register_security.setVisibility(View.VISIBLE);
                    register_contractor.setVisibility(View.GONE);
                    register_consumer.setVisibility(View.GONE);
                    register_supplier.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                } else {
                    register_supplier.setVisibility(View.GONE);
                    register_security.setVisibility(View.GONE);
                    register_Dealer.setVisibility(View.GONE);
                    register_contractor.setVisibility(View.GONE);
                    register_consumer.setVisibility(View.GONE);
                    register_transporter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //API CALLING FOR SPINNER2
        StringRequest stringRequest1 = new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String catogery = jsonObject1.getString("company_name");
                    SelectClientType.add(catogery);
                }
                SelectClient.setAdapter(new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientType));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Gronzo", error.toString()));
        RequestQueue queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest1);
        SelectClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress = SelectClient.getItemAtPosition(SelectClient.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnRegister.setOnClickListener(v -> {
            if (SelectRole.getSelectedItem().toString().trim().equals("Select user")) {
                Toast.makeText(RegisterActivity.this, "Usertype is empty!", Toast.LENGTH_SHORT).show();
            } else if (SelectClient.getSelectedItem().toString().trim().equals("Select client")) {
                Toast.makeText(this, "ClientType is Empty", Toast.LENGTH_SHORT).show();
            } else if (name.getText().toString().trim().isEmpty()) {
                name.setError("name is required!");
                name.requestFocus();
            } else if (email.getText().toString().trim().isEmpty()) {
                email.setError("email is required!");
                email.requestFocus();
            } else if (phone.getText().toString().trim().isEmpty()) {
                phone.setError("phone is required!");
                phone.requestFocus();
            } else if (pingenerate.getText().toString().trim().isEmpty()) {
                pingenerate.setError("pin is required!");
                pingenerate.requestFocus();
            } else if (designation.getText().toString().trim().isEmpty()) {
                designation.setError("designation is required!");
                designation.requestFocus();
            } else if (bloodgroup.getText().toString().trim().isEmpty()) {
                bloodgroup.setError("bloodgroup is required!");
                bloodgroup.requestFocus();
            } else if (biomatricdata.getText().toString().trim().isEmpty()) {
                biomatricdata.setError("biomatric data is required!");
                biomatricdata.requestFocus();
            } else if (aadharno.getText().toString().trim().isEmpty()) {
                aadharno.setError("aadharno is required!");
                aadharno.requestFocus();
            } else {
                loading.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.GONE);
                builder.addFormDataPart("otp_code",Objects.requireNonNull("1234"))
                        .addFormDataPart("role", Objects.requireNonNull(SelectRole.getSelectedItem().toString()))
                        .addFormDataPart("client", Objects.requireNonNull(SelectClient.getSelectedItem().toString()))
                        .addFormDataPart("name", Objects.requireNonNull(name.getText()).toString())
                        .addFormDataPart("email", Objects.requireNonNull(email.getText()).toString())
                        .addFormDataPart("mobile", Objects.requireNonNull(phone.getText()).toString())
                        .addFormDataPart("password", Objects.requireNonNull(pingenerate.getText()).toString())
                        .addFormDataPart("partner_designation", Objects.requireNonNull(designation.getText()).toString())
                        .addFormDataPart("blood", Objects.requireNonNull(bloodgroup.getText().toString()))
                        .addFormDataPart("biomatricdata", Objects.requireNonNull(biomatricdata.getText().toString()))
                        .addFormDataPart("aadhar_no", Objects.requireNonNull(aadharno.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtSecurityGst.getText().toString()))
                        .addFormDataPart("department", Objects.requireNonNull(edtDepartmentSecurity.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPanSecurity.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEsiRegistrationSecurity.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfRegistration.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLabourRegistrationSecurity.getText().toString()))
                        .addFormDataPart("code", Objects.requireNonNull(edtDealercodeDealer.getText().toString()))
                        .addFormDataPart("firm_name", Objects.requireNonNull(edtFirmnameDelaer.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtGstnoDealer.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPanDelaer.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEsiregistrationDealer.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfregistrationDealer.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLabourregistrationDealer.getText().toString()))
                        .addFormDataPart("firm_name", Objects.requireNonNull(edtFirmnameDelaer.getText().toString()))
                        .addFormDataPart("address", Objects.requireNonNull(edtofficeAddressDealer.getText().toString()))
                        .addFormDataPart("code", Objects.requireNonNull(edtVendorcodeContractor.getText().toString()))
                        .addFormDataPart("firm_name", Objects.requireNonNull(edtFirmnameContractor.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtGstnoContractor.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPanContractor.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEsiregistrationContractor.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfregistrationContractor.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLabourregistrationContractor.getText().toString()))
                        .addFormDataPart("firm_email", Objects.requireNonNull(edtFirmemailContrator.getText().toString()))
                        .addFormDataPart("address", Objects.requireNonNull(edtOfficeaddressContractor.getText().toString()))
                        .addFormDataPart("code", Objects.requireNonNull(edtDealerCodeConsumer.getText().toString()))
                        .addFormDataPart("firm_name", Objects.requireNonNull(edtFirmnameConsumer.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtGstnoConsumer.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPanConsumer.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEsiregistrationConsumer.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfregistrationConsumer.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLaboutregistrationConsumer.getText().toString()))
                        .addFormDataPart("firm_email", Objects.requireNonNull(edtFirmemailConsumer.getText().toString()))
                        .addFormDataPart("address", Objects.requireNonNull(edtOfficeaddressConsumer.getText().toString()))
                        .addFormDataPart("code", Objects.requireNonNull(edtVendoecodeSupplier.getText().toString()))
                        .addFormDataPart("firm_email", Objects.requireNonNull(edtFirmemailSupplier.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtGstnoSupplier.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPanSupplier.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEsiregistrationSupplier.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfregistrationSupplier.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLabourregistrationSupplier.getText().toString()))
                        .addFormDataPart("firm_email", Objects.requireNonNull(edtFirmemailSupplier.getText().toString()))
                        .addFormDataPart("address", Objects.requireNonNull(edtOfficeaddressSupplier.getText().toString()))
                        .addFormDataPart("firm_name", Objects.requireNonNull(edtFirmnameTransporter.getText().toString()))
                        .addFormDataPart("code", Objects.requireNonNull(edtDelaercodeTransporter.getText().toString()))
                        .addFormDataPart("gst_no", Objects.requireNonNull(edtGstnoTransporter.getText().toString()))
                        .addFormDataPart("pan", Objects.requireNonNull(edtPannoTransporter.getText().toString()))
                        .addFormDataPart("esi", Objects.requireNonNull(edtEstregistrationTransporter.getText().toString()))
                        .addFormDataPart("pf", Objects.requireNonNull(edtPfregistrationTransporter.getText().toString()))
                        .addFormDataPart("labour", Objects.requireNonNull(edtLabourregistrationTransporter.getText().toString()))
                        .addFormDataPart("firm_email", Objects.requireNonNull(edtFirmemailTransporter.getText().toString()))
                        .addFormDataPart("address", Objects.requireNonNull(edtOfficeAddressTransporter.getText().toString()));


                RetrofitApi apiService = ApiClient.getRawClient().create(RetrofitApi.class);
                apiService.registration(builder.build()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.e("response-->",response.body());
                        loading.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, OtpActivity.class));
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loading.setVisibility(View.GONE);
                        btnRegister.setVisibility(View.VISIBLE);
                        System.out.println(t.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnUploadPhoto.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 1);
        });
        btnUploadSignature.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 2);
        });

        txtRegister.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
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
                MultipartBody.Part body = MultipartBody.Part.createFormData("photo[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 2)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("signature[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
