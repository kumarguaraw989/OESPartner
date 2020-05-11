package com.example.oespartner.Activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.oespartner.model.AddChamberDetailsModel;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class AddTransportActivity extends AppCompatActivity {
    ImageView imgBack;
    Button btnRegister;
    @BindView(R.id.spnVehical)
    Spinner spnVehical;
    @BindView(R.id.edtVehicalType)
    EditText edtVehicalType;
    @BindView(R.id.edtDateValidUpto)
    EditText edtDateValidUpto;
    @BindView(R.id.edtReferenceNo)
    EditText edtReferenceNo;
    @BindView(R.id.btnChooseFile)
    Button btnChooseFile;
    @BindView(R.id.tv_tvFileChoosen)
    androidx.appcompat.widget.AppCompatTextView tv_tvFileChoosen;
    @BindView(R.id.edtDateValidUpto2)
    EditText edtDateValidUpto2;
    @BindView(R.id.edtRferenceNo2)
    EditText edtRferenceNo2;
    @BindView(R.id.btnChooseFile2)
    Button btnChooseFile2;
    @BindView(R.id.tvChoosenFile2)
    AppCompatTextView tvChoosenFile2;
    @BindView(R.id.edtDateValidUpto3)
    EditText edtDateValidUpto3;
    @BindView(R.id.edtReferenceNo3)
    EditText edtReferenceNo3;
    @BindView(R.id.btnChooseFile3)
    Button btnChooseFile3;
    @BindView(R.id.tvchoosenFile3)
    AppCompatTextView tvchoosenFile3;
    @BindView(R.id.edtDateValidUpto4)
    EditText edtDateValidUpto4;
    @BindView(R.id.edtReferenceNo4)
    EditText edtReferenceNo4;
    @BindView(R.id.btnChooseFile4)
    Button btnChooseFile4;
    @BindView(R.id.tvChoosenFile4)
    AppCompatTextView tvChoosenFile4;
    @BindView(R.id.edtDateValidUpto5)
    EditText edtDateValidUpto5;
    @BindView(R.id.edtRferenceNo5)
    EditText edtRferenceNo5;
    @BindView(R.id.btnChooseFile5)
    Button btnChooseFile5;
    @BindView(R.id.tvFileChoosen5)
    AppCompatTextView tvFileChoosen5;
    @BindView(R.id.edtDateValidUpto6)
    EditText edtDateValidUpto6;
    @BindView(R.id.edtReferenceNo6)
    EditText edtReferenceNo6;
    @BindView(R.id.btnAddChamberDetails)
    TextView btnAddChamberDetails;
    @BindView(R.id.btnRemoveChamberDetails)
    TextView btnRemoveChamberDetails;
    @BindView(R.id.edtNoOfChambers)
    EditText edtNoOfChambers;
    @BindView(R.id.edtCapacity)
    EditText edtCapacity;
    @BindView(R.id.edtTotalDipRoadLength)
    EditText edtTotalDipRoadLength;
    @BindView(R.id.edtBtnPvValveCheck)
    EditText edtBtnChoosePvValveCheck;
    @BindView(R.id.edtValidUptoPvValve)
    EditText edtValidUptoPvValve;
    @BindView(R.id.edtBtnChoosefire)
    EditText edtBtnChoosefire;
    @BindView(R.id.edtDateValidUptofire)
    EditText edtDateValidUptofire;
    @BindView(R.id.edtDateValidUptohealth)
    EditText edtDateValidUptohealth;
    @BindView(R.id.edtBtnChooseFilehealth)
    EditText edtBtnChooseFilehealth;
    @BindView(R.id.progress_bar_transport)
    ProgressBar loading;
    ArrayList<String> SelectVichelName = new ArrayList<>();
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    String email,role;
    List<AddChamberDetailsModel> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transport);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btnRegister = findViewById(R.id.btnRegister);
/*        final EditText edtNo = findViewById(R.id.material_name);
        final EditText edtcapacity = findViewById
                (R.id.edtSpecification);
        final EditText edtDlLevel = findViewById
                (R.id.edtUnit);
        final EditText edPlLevel = findViewById(R.id.edtQty);*/


        ButterKnife.bind(this);
        getSupportActionBar().hide();
        myList=new ArrayList<>();
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
//        final ViewGroup tes = (ViewGroup) findViewById(R.id.layout_addchambers);
        email=data_model.getEmail();
        role=data_model.getRole();
        imgBack.setOnClickListener(v -> onBackPressed());
        edtDateValidUpto.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto, AddTransportActivity.this));
        edtDateValidUpto2.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto2, AddTransportActivity.this));
        edtDateValidUpto3.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto3, AddTransportActivity.this));
        edtDateValidUpto4.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto4, AddTransportActivity.this));
        edtDateValidUpto5.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto5, AddTransportActivity.this));
        edtDateValidUpto6.setOnClickListener(view -> Constants.DateDialog(edtDateValidUpto6, AddTransportActivity.this));
        edtValidUptoPvValve.setOnClickListener(view -> Constants.DateDialog(edtValidUptoPvValve, AddTransportActivity.this));
        edtDateValidUptofire.setOnClickListener(view -> Constants.DateDialog(edtDateValidUptofire, AddTransportActivity.this));
        edtDateValidUptohealth.setOnClickListener(view -> Constants.DateDialog(edtDateValidUptohealth, AddTransportActivity.this));

     /*   btnRemoveChamberDetails.setOnClickListener
                (v -> tes.removeViewAt(0));
        btnAddChamberDetails.setOnClickListener(v -> {
            AddChamberDetailsModel mylist1=new AddChamberDetailsModel();
            final View extend = LayoutInflater.from(v.getContext()).inflate(R.layout.item_chamber_add, tes, false);
            tes.addView(extend);
            mylist1.setNo(edtNo.getText().toString());
            mylist1.setCapacity(edtcapacity.getText().toString());
            mylist1.setDlLenght(edtDlLevel.getText().toString());
            System.out.println(edtNo.getText().toString());
            System.out.println(edtcapacity.getText().toString());
            System.out.println(edtDlLevel.getText().toString());
            myList.add(mylist1);
        });*/



        edtNoOfChambers.setOnClickListener(v -> {
            int totalval=0;
            for (int i=0;i<myList.size();i++){
                AddChamberDetailsModel list=myList.get(i);
                String val=list.getNo();
                Log.e("val",val);
                Log.e("capacity",list.getCapacity());
                Log.e("dl",list.getDlLenght());
                totalval=Integer.valueOf(val)+totalval;
            }
            Log.e("totalval",String.valueOf(totalval));
            edtNoOfChambers.setText(String.valueOf(totalval));
         });
        edtCapacity.setOnClickListener(v -> {
            int totalval=0;
            for (int i=0;i<myList.size();i++){
                AddChamberDetailsModel list=myList.get(i);
                String val=list.getCapacity();
                Log.e("val",val);
                Log.e("capacity",list.getCapacity());
                Log.e("dl",list.getDlLenght());
                totalval=Integer.valueOf(val)+totalval;
            }
            Log.e("totalvaldllevel",String.valueOf(totalval));
            edtCapacity.setText(String.valueOf(totalval));
        });
        edtTotalDipRoadLength.setOnClickListener(v -> {
            int totalval=0;
            for (int i=0;i<myList.size();i++){
                AddChamberDetailsModel list=myList.get(i);
                String val=list.getDlLenght();
                Log.e("val",val);
                Log.e("capacity",list.getCapacity());
                Log.e("dl",list.getDlLenght());
                totalval=Integer.valueOf(val)+totalval;
            }
            Log.e("totalvaldllevel",String.valueOf(totalval));
            edtTotalDipRoadLength.setText(String.valueOf(totalval));
        });
        SelectVichelName.add("Select Anyone");
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Config.Spinner_VehicleApi, response -> {
           Log.e("response-->" ,response);
            try {
                JSONArray jsonArray=new JSONArray(response.toString());
                for (int j=0; j<jsonArray.length(); ++j){
                    JSONObject jsonObject1=jsonArray.getJSONObject(j);
                    String catogery=jsonObject1.getString("vehicle_no");
                    SelectVichelName.add(catogery);
                }
                spnVehical.setAdapter(new ArrayAdapter<>(AddTransportActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectVichelName));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("email",email);
                params.put("role",role);
                return params;
            }
        };
        RequestQueue queue2= Volley.newRequestQueue(this);
        queue2.add(stringRequest);
        btnRegister.setOnClickListener(view -> {
             builder.addFormDataPart("email", Objects.requireNonNull(email))
                    .addFormDataPart("role", Objects.requireNonNull(role))
                    .addFormDataPart("vehicle_name", Objects.requireNonNull(spnVehical.getSelectedItem().toString()))
                    .addFormDataPart("vehicle_type", Objects.requireNonNull(edtVehicalType.getText().toString()))
                    .addFormDataPart("vehicle_ref", Objects.requireNonNull(edtReferenceNo.getText().toString()))
                    .addFormDataPart("vehicle_valid", Objects.requireNonNull(edtDateValidUpto.getText().toString()))
                    .addFormDataPart("explosive_ref", Objects.requireNonNull(edtRferenceNo2.getText().toString()))
                    .addFormDataPart("explosive_valid", Objects.requireNonNull(edtDateValidUpto2.getText()).toString())
                    .addFormDataPart("calibration_ref", Objects.requireNonNull(edtReferenceNo3.getText().toString()))
                    .addFormDataPart("calibration_refcalibration_ref", Objects.requireNonNull(edtReferenceNo4.getText().toString()))
                    .addFormDataPart("calibration_ref", Objects.requireNonNull(edtReferenceNo4.getText().toString()))
                    .addFormDataPart("calibration_valid", Objects.requireNonNull(edtDateValidUpto4.getText().toString()))
                    .addFormDataPart("fitness_ref", Objects.requireNonNull(edtRferenceNo5.getText().toString()))
                    .addFormDataPart("fitness_valid", Objects.requireNonNull(edtDateValidUpto5.getText().toString()))
                    .addFormDataPart("insurance_ref", Objects.requireNonNull(edtReferenceNo6.getText().toString()))
                    .addFormDataPart("insurance_valid", Objects.requireNonNull(edtDateValidUpto6.getText().toString()))
                    .addFormDataPart("safety_ref", Objects.requireNonNull(edtRferenceNo5.getText().toString()))
                    .addFormDataPart("pv_valid", Objects.requireNonNull(edtValidUptoPvValve.getText().toString()))
                    .addFormDataPart("fire_valid", Objects.requireNonNull(edtDateValidUptofire.getText().toString()))
                    .addFormDataPart("tank_valid", Objects.requireNonNull(edtDateValidUptohealth.getText().toString()))
                    .addFormDataPart("no_of_chambers", Objects.requireNonNull(edtNoOfChambers.getText().toString()))
                    .addFormDataPart("sum_of_capacity", Objects.requireNonNull(edtCapacity.getText().toString()))
                    .addFormDataPart("edtTotalDipRoadLength ", Objects.requireNonNull(edtTotalDipRoadLength.getText().toString()));
            RetrofitApi apiService = ApiClient.getRawClient().create(RetrofitApi.class);
            apiService.AddTransport(builder.build()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e("response-->", response.body());
                    loading.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        });
        btnChooseFile.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 1);
        });

        btnChooseFile2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 2);
        });
        btnChooseFile3.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 3);
        });
        btnChooseFile4.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 4);
        });
        btnChooseFile5.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 5);
        });
        edtBtnChoosefire.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 6);
        });
        edtBtnChooseFilehealth.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 7);
        });
        edtBtnChoosePvValveCheck.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true), "Select Picture"), 8);
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
                MultipartBody.Part body = MultipartBody.Part.createFormData("vehicle_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
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
                MultipartBody.Part body = MultipartBody.Part.createFormData("explosive_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
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
                MultipartBody.Part body = MultipartBody.Part.createFormData("calibration_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (requestCode == 4)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("fitness_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
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
                MultipartBody.Part body = MultipartBody.Part.createFormData("insurance_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (requestCode == 6)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("pv_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (requestCode == 7)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData(" fire_copy[]",System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (requestCode == 8)
            try {
                Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bos.toByteArray());
                MultipartBody.Part body = MultipartBody.Part.createFormData("tank_copy[]", System.currentTimeMillis() + ".jpg", requestFile);
                builder.addPart(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}