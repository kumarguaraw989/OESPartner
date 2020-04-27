package com.example.oespartner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;


import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPartnerPersonActivity extends AppCompatActivity {
    ImageView imgBack;
    EditText PoliceVerification;
    LinearLayout layoutuploadAadharPan, layoutindia, layoutothers;
    @BindView(R.id.edtDateofBirth)
    EditText edtDateofBirth;
    @BindView(R.id.edtDateVisaValidity)
    EditText edtDateVisaValidity;
    @BindView(R.id.edtState)
    EditText edtState;
    @BindView(R.id.person_name)
    EditText person_name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.email1)
    EditText email1;
    @BindView(R.id.father_name)
    EditText father_name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtIdentificationMark)
    EditText edtIdentificationMark;
    @BindView(R.id.edtPostOffice)
    EditText edtPostOffice;
    @BindView(R.id.edtVillage)
    EditText edtVillage;
    @BindView(R.id.spnWearGlass)
    Spinner spnWearGlass;
    @BindView(R.id.edtPoliceStation)
    EditText edtPoliceStation;
    @BindView(R.id.edtpPoliceStation)
    EditText edtpPoliceStation;
    @BindView(R.id.spntitle)
    Spinner spntitle;
    @BindView(R.id.spnGender)
    Spinner spnGender;
    @BindView(R.id.spnBloodGrp)
    Spinner spnBloodGrp;
    @BindView(R.id.edtDistrict)
    EditText edtDistrict;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.edtPinNo)
    EditText edtPinNo;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.weatherStaying)
    Spinner weatherStaying;
    @BindView(R.id.pAddress)
    EditText pAddress;
    @BindView(R.id.edtpVillage)
    EditText edtpVillage;
    @BindView(R.id.edtEmployee_detail)
    EditText edtEmployee_detail;
    @BindView(R.id.edtpPostOffice)
    EditText edtpPostOffice;
    @BindView(R.id.edtPState)
    EditText edtPState;
    @BindView(R.id.edtPDistrict)
    EditText edtPDistrict;
    @BindView(R.id.edtPpinNo)
    EditText edtPpinNo;
    @BindView(R.id.edtPMobile)
    EditText edtPMobile;
    @BindView(R.id.edtDob)
    EditText edtDob;
    @BindView(R.id.edtpf)
    EditText edtpf;
    @BindView(R.id.edtParticularPlace)
    EditText edtParticularPlace;
    @BindView(R.id.edtschool_name_address)
    EditText edtschool_name_address;
    @BindView(R.id.edtQualification)
    EditText edtQualification;
    @BindView(R.id.edtDuration)
    EditText edtDuration;
    @BindView(R.id.edtReferenceName1)
    EditText edtReferenceName1;
    @BindView(R.id.edtreferencePhone1)
    EditText edtreferencePhone1;
    @BindView(R.id.edtreferenceName2)
    EditText getEdtReferenceName2;
    @BindView(R.id.edtReferencePhone2)
    EditText edtReferencePhone2;
    @BindView(R.id.edtBankName)
    EditText edtBankName;
    @BindView(R.id.edtAccountNo)
    EditText edtAccountNo;
    @BindView(R.id.edtIfscCode)
    EditText edtIfscCode;
    @BindView(R.id.spinArested)
    Spinner spinArested;
    @BindView(R.id.edtcaseDetails)
    EditText edtcaseDetails;
    @BindView(R.id.spinNationality)
    Spinner spinNationality;
    @BindView(R.id.edtAadharNo)
    EditText edtAadharNo;
    @BindView(R.id.edtPanNo2)
    EditText edtPanNo2;
    @BindView(R.id.btn_uploadAadhar)
    Button upload_aadhar;
    @BindView(R.id.btn_upload_pan)
    Button btn_upload_pan;
    @BindView(R.id.spnValidPoliceVerification)
    Spinner spnValidPoliceVerification;
    @BindView(R.id.edtReference_policeverification)
    EditText edtReference_policeverification;
    @BindView(R.id.edtDateofissue)
    EditText edtDateofissue;
    @BindView(R.id.upload_signature)
    Button btn_uploadSignature;
    @BindView(R.id.btn_uploadPoliceVerification)
    Button btn_uploadPoliceVerification;
    @BindView(R.id.upload_BiometricSignature)
    Button upload_BiometricSignature;
    @BindView(R.id.edtPassportNo_)
    EditText edtPassportNo;
    @BindView(R.id.edtVisaNo)
    EditText edtVisaNo;
    @BindView(R.id.edtExpertInField)
    EditText edtExpertInField;
    @BindView(R.id.chkDeclaration)
    CheckBox chkDeclaration;
    String DecAcepted;
    @BindView(R.id.edtPowerLeftEye)
    EditText edtPowerLeftEye;
    @BindView(R.id.edtPowerRightEye)
    EditText edtPowerRightEye;
    @BindView(R.id.spnLeftEyeVision)
    Spinner spnLeftEyeVision;
    @BindView(R.id.spnRightEyeVision)
    Spinner spnRightEyeVision;
    @BindView(R.id.layout_partenerpersonglassyes)
    LinearLayout layout_partenerpersonglassyes;
    ArrayList<String> SelectVesionLeft=new ArrayList<>();
    ArrayList<String> SelectVesionRight=new ArrayList<>();
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner_person);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        PoliceVerification = findViewById(R.id.edtReference_policeverification);
        layoutuploadAadharPan = findViewById(R.id.layout_AadharPanupload);
        layoutindia = findViewById(R.id.layout_india);
        layoutothers = findViewById(R.id.layoutothers);
        if (chkDeclaration.isChecked()) {
            DecAcepted = "Declared";
        } else {
            DecAcepted = "NotDeclared";
        }
        setupSpinners();
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
        String email = data_model.getEmail();
        String role = data_model.getRole();

        edtDateofBirth.setOnClickListener(view -> Constants.DateDialog(edtDateofBirth, AddPartnerPersonActivity.this));
        edtDateVisaValidity.setOnClickListener(view -> Constants.DateDialog(edtDateVisaValidity, AddPartnerPersonActivity.this));
        edtDateofissue.setOnClickListener(v -> Constants.DateDialog(edtDateofissue, AddPartnerPersonActivity.this));


        SelectVesionLeft.add("Select");
        spnLeftEyeVision.setAdapter(new ArrayAdapter<>(AddPartnerPersonActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectVesionLeft));
        String[] leftVision = {"6/3","6/6","6/7.5","6/9.5","6/12","6/15","6/18"};
        spnLeftEyeVision.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, leftVision));

        SelectVesionRight.add("Select");
        spnRightEyeVision.setAdapter(new ArrayAdapter<>(AddPartnerPersonActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectVesionRight));
        String[] rightVision={"6/3","6/6","6/7.5","6/9.5","6/12","6/15","6/18"};
        spnRightEyeVision.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,rightVision));
        btnSubmit.setOnClickListener(v -> {
            progress_bar.setVisibility(View.VISIBLE);
            builder.addFormDataPart("session_email", Objects.requireNonNull(email))
                    .addFormDataPart("email", Objects.requireNonNull(email1.getText().toString()))
                    .addFormDataPart("role", Objects.requireNonNull(role))
                    .addFormDataPart("title", Objects.requireNonNull(spntitle.getSelectedItem().toString()))
                    .addFormDataPart("person_name", Objects.requireNonNull(person_name.getText().toString()))
                    .addFormDataPart("phone", Objects.requireNonNull(phone.getText().toString()))
                    .addFormDataPart("father_name", Objects.requireNonNull(father_name.getText().toString()))
                    .addFormDataPart("dob", Objects.requireNonNull(edtDateofBirth.getText().toString()))
                    .addFormDataPart("age", Objects.requireNonNull(age.getText().toString()))
                    .addFormDataPart("gender", Objects.requireNonNull(spnGender.getSelectedItem().toString()))
                    .addFormDataPart("blood_group", Objects.requireNonNull(spnBloodGrp.getSelectedItem().toString()))
                    .addFormDataPart("wear_glass", Objects.requireNonNull(spnWearGlass.getSelectedItem().toString()))
                    .addFormDataPart("left_eye_power",Objects.requireNonNull(edtPowerLeftEye.getText().toString()))
                    .addFormDataPart("right_eye_power",Objects.requireNonNull(edtPowerRightEye.getText().toString()))
                    .addFormDataPart("left_eye",Objects.requireNonNull(spnLeftEyeVision.getSelectedItem().toString()))
                    .addFormDataPart("right_eye",Objects.requireNonNull(spnRightEyeVision.getSelectedItem().toString()))
                    .addFormDataPart("mark", Objects.requireNonNull(edtIdentificationMark.getText().toString()))
                    .addFormDataPart("address", Objects.requireNonNull(edtAddress.getText().toString()))
                    .addFormDataPart("village", Objects.requireNonNull(edtVillage.getText().toString()))
                    .addFormDataPart("post_office", Objects.requireNonNull(edtPostOffice.getText().toString()))
                    .addFormDataPart("police_station", Objects.requireNonNull(edtPoliceStation.getText().toString()))
                    .addFormDataPart("state", Objects.requireNonNull(edtState.getText().toString()))
                    .addFormDataPart("visa_no", Objects.requireNonNull(edtVisaNo.getText().toString()))
                    .addFormDataPart("district", Objects.requireNonNull(edtDistrict.getText().toString()))
                    .addFormDataPart("pin_no", Objects.requireNonNull(edtPinNo.getText().toString()))
                    .addFormDataPart("mobile", Objects.requireNonNull(edtMobile.getText().toString()))
                    .addFormDataPart("whether_staying", Objects.requireNonNull(weatherStaying.getSelectedItem().toString()))
                    .addFormDataPart("validity_of_visa", Objects.requireNonNull(edtDateVisaValidity.getText().toString()))
                    .addFormDataPart("p_address", Objects.requireNonNull(pAddress.getText().toString()))
                    .addFormDataPart("p_village", Objects.requireNonNull(edtpVillage.getText().toString()))
                    .addFormDataPart("p_post_office", Objects.requireNonNull(edtpPostOffice.getText().toString()))
                    .addFormDataPart("p_police_station", Objects.requireNonNull(edtpPoliceStation.getText().toString()))
                    .addFormDataPart("passport_no", Objects.requireNonNull(edtPassportNo.getText().toString()))
                    .addFormDataPart("p_state", Objects.requireNonNull(edtPState.getText().toString()))
                    .addFormDataPart("p_district", Objects.requireNonNull(edtPDistrict.getText().toString()))
                    .addFormDataPart("p_pin_no", Objects.requireNonNull(edtPpinNo.getText().toString()))
                    .addFormDataPart("p_mobile", Objects.requireNonNull(edtPMobile.getText().toString()))
                    .addFormDataPart("place_of_birth", Objects.requireNonNull(edtDob.getText().toString()))
                    .addFormDataPart("particular_place", Objects.requireNonNull(edtParticularPlace.getText().toString()))
                    .addFormDataPart("qualification", Objects.requireNonNull(edtQualification.getText().toString()))
                    .addFormDataPart("school_name_address", Objects.requireNonNull(edtschool_name_address.getText().toString()))
                    .addFormDataPart("employee_detail", Objects.requireNonNull(edtEmployee_detail.getText().toString()))
                    .addFormDataPart("duration", Objects.requireNonNull(edtDuration.getText().toString()))
                    .addFormDataPart("pf", Objects.requireNonNull(edtpf.getText().toString()))
                    .addFormDataPart("expert_field", Objects.requireNonNull(edtExpertInField.getText().toString()))
                    .addFormDataPart("reference_name1", Objects.requireNonNull(edtReferenceName1.getText().toString()))
                    .addFormDataPart("reference_phone1", Objects.requireNonNull(edtreferencePhone1.getText().toString()))
                    .addFormDataPart("reference_name2", Objects.requireNonNull(getEdtReferenceName2.getText().toString()))
                    .addFormDataPart("reference_phone2", Objects.requireNonNull(edtReferencePhone2.getText().toString()))
                    .addFormDataPart("bank_name", Objects.requireNonNull(edtBankName.getText().toString()))
                    .addFormDataPart("account_no", Objects.requireNonNull(edtAccountNo.getText().toString()))
                    .addFormDataPart("ifsci_code", Objects.requireNonNull(edtIfscCode.getText().toString()))
                    .addFormDataPart("ever_arrested", Objects.requireNonNull(spinArested.getSelectedItem().toString()))
                    .addFormDataPart("case_details", Objects.requireNonNull(edtcaseDetails.getText().toString()))
                    .addFormDataPart("nationality", Objects.requireNonNull(spinNationality.getSelectedItem().toString()))
                    .addFormDataPart("aadhar_no", Objects.requireNonNull(edtAadharNo.getText().toString()))
                    .addFormDataPart("declaration_by_person", Objects.requireNonNull(DecAcepted))
                    .addFormDataPart("pan", Objects.requireNonNull(edtPanNo2.getText().toString()))
                    .addFormDataPart("valid_police", Objects.requireNonNull(spnValidPoliceVerification.getSelectedItem().toString()))
                    .addFormDataPart("reference_no", Objects.requireNonNull(edtReference_policeverification.getText().toString()))
                    .addFormDataPart("issuance_date", Objects.requireNonNull(edtDateofissue.getText().toString()));
            RetrofitApi apiService = ApiClient.getRawClient().create(RetrofitApi.class);
            apiService.AddPartnerPerson(builder.build()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    progress_bar.setVisibility(View.GONE);
                    FancyToast.makeText(getApplicationContext(), "Added Successfull", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        });
        photo.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 1);
        });

        upload_aadhar.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 2);

        });

        btn_upload_pan.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 3);
        });
        btn_uploadSignature.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 4);
        });
        btn_uploadPoliceVerification.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 5);

        });
        upload_BiometricSignature.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 6);

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
                MultipartBody.Part body = MultipartBody.Part.createFormData("aadhar_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (requestCode == 3)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("pan_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (requestCode == 4)
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

        if (requestCode == 5)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("police_varification_upload[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (requestCode == 6)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(Objects.requireNonNull(data).getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("biometric_signature[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch(Exception e){
                e.printStackTrace();
            }
    }

    void setupSpinners() {
        spinNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    layoutindia.setVisibility(View.GONE);
                    layoutothers.setVisibility(View.GONE);
                } else if (position == 1) {
                    layoutindia.setVisibility(View.VISIBLE);
                    layoutothers.setVisibility(View.GONE);
                } else if (position == 2) {
                    layoutindia.setVisibility(View.GONE);
                    layoutothers.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnWearGlass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    layout_partenerpersonglassyes.setVisibility(View.GONE);
                }
                else if (position == 1){
                    layout_partenerpersonglassyes.setVisibility(View.VISIBLE);
                }else if (position == 2){
                    layout_partenerpersonglassyes.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

