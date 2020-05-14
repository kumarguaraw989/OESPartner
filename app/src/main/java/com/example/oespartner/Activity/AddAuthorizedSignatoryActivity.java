package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.iosprogressbarforandroid.IOSProgressHUD;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.MainActivity;
import com.example.oespartner.model.AddAuthorizedSignatoryModel;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
import com.google.android.material.textfield.TextInputLayout;
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
public class AddAuthorizedSignatoryActivity extends AppCompatActivity {
    @BindView(R.id.add_signatory)
    Button add;
    @BindView(R.id.update_signatory)
    Button update;
    @BindView(R.id.imgBack)
    ImageView ImgBack;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.client_name)
    TextView Clinet_name;
    @BindView(R.id.client_branchname)
    Spinner spin_branchname;
    @BindView(R.id.nameof_person)
    Spinner spin_nameofperson;
    @BindView(R.id.designation)
    Spinner spin_designation;
    @BindView(R.id.edtDate)
    EditText edtWorkValidUpto;

    @BindView(R.id.edtDate2)
    EditText edtSignatoryValidupto;
    @BindView(R.id.edtWorkrefno)
    EditText edtWork_orderreference;
    @BindView(R.id.edtWorkorderDes)
    EditText edtWork_description;
    @BindView(R.id.check_declaration1)
    AppCompatCheckBox declaration1;
    @BindView(R.id.check_declaration2)
    AppCompatCheckBox declaration2;
    @BindView(R.id.others_layout)
    TextInputLayout othersaddwork;
    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectPersonId = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_authorized_signatory);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        update.setVisibility(View.GONE);
        edtWorkValidUpto.setOnClickListener(view -> Constants.DateDialog(edtWorkValidUpto, AddAuthorizedSignatoryActivity.this));
        edtSignatoryValidupto.setOnClickListener(view -> Constants.DateDialog(edtSignatoryValidupto, AddAuthorizedSignatoryActivity.this));
        Data data_model = FastSave.getInstance().getObject("login_data", Data.class);
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
        spin_designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==4){
                    othersaddwork.setVisibility(View.VISIBLE);
                }else {
                    othersaddwork.setVisibility(View.GONE);
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
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    SelectClientBranch.add(jsonObject1.get("branch").toString());
                }
                spin_branchname.setAdapter(new ArrayAdapter<>(AddAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
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
        SelectPersonName.add("Select Person Name");
        StringRequest stringRequest3=new StringRequest(Request.Method.POST, Config.URL_PersonName, new com.android.volley.Response.Listener<String>() {
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

                    spin_nameofperson.setAdapter(new ArrayAdapter<>(AddAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             }
        }, error -> Toast.makeText(AddAuthorizedSignatoryActivity.this,error.toString(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params= new HashMap<>();
                Data data_model=FastSave.getInstance().getObject("login_data",Data.class);
                params.put("email",data_model.getEmail());
                params.put("role",data_model.getRole());
                return params;
            }
        };
        RequestQueue queue3=Volley.newRequestQueue(this);
        queue3.add(stringRequest3);
        spin_nameofperson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String dAdress=spin_nameofperson.getItemAtPosition(spin_nameofperson.getSelectedItemPosition()).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        add.setOnClickListener(view -> {
            //String Date = edtWorkValidUpto.getText().toString();
            String Date2 = edtSignatoryValidupto.getText().toString();
            String WorkOrderReferenceNo = edtWork_orderreference.getText().toString();
            String WorkOrderDescription = edtWork_description.getText().toString();
            String clientName=Clinet_name.getText().toString();
            String BranchName = (String) spin_branchname.getSelectedItem();
            String Designation = (String) spin_designation.getSelectedItem();
            String person_name=(String)spin_nameofperson.getSelectedItem();

            if(Date2.equals("")){
                FancyToast.makeText(AddAuthorizedSignatoryActivity.this,"Enter Date",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }
            else
            {
                loading.setVisibility(View.VISIBLE);
                 postAuthorizesSignatory(data_model.getEmail(),  data_model.getRole(),BranchName, person_name,WorkOrderReferenceNo,WorkOrderDescription,clientName,Date2,Designation);
                onBackPressed();
            }
        });
    }

    public void postAuthorizesSignatory(String email, String role, String branch, String person_name, String reference_no, String description, String client, String valid_upto, String designationnn) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddAuthorizedSignatoryModel> call = apiService.AddAuthorizedSignatory(data_model.getEmail(),  data_model.getRole(), branch, person_name, reference_no, description, client, valid_upto, designationnn);
        call.enqueue(new Callback<AddAuthorizedSignatoryModel>() {
            @Override
            public void onResponse(Call<AddAuthorizedSignatoryModel> call, Response<AddAuthorizedSignatoryModel> response) {
                //  System.out.println(response);
                loading.setVisibility(View.GONE);
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();
                Toast.makeText(AddAuthorizedSignatoryActivity.this, "successfully  Added", Toast.LENGTH_SHORT).show();
             }
            @Override
            public void onFailure(Call<AddAuthorizedSignatoryModel> call, Throwable t) {

            }
        });

    }
}

