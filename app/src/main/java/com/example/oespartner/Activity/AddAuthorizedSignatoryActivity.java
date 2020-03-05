package com.example.oespartner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.Model.AddAuthorizedSignatoryModel;
import com.example.oespartner.Model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.Config;
import com.example.oespartner.WebService.RetrofitApi;
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
    @BindView(R.id.spinClient)
    Spinner client;
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
        SelectClient.add("Select Client");
        StringRequest stringRequest=new StringRequest(Config.URL_CLient, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("company_name");
                    SelectClient.add(catogery);
                }
                client.setAdapter(new ArrayAdapter<>(AddAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("error", error.toString()));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
        client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=client.getItemAtPosition(client.getSelectedItemPosition()).toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SelectClientBranch.add("Select Branch");
        StringRequest stringRequest1=new StringRequest(Config.URL_ClientBranch, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0; i<jsonArray.length(); ++i){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String catogery=jsonObject1.getString("branch");
                    SelectClientBranch.add(catogery);
                }
                spin_branchname.setAdapter(new ArrayAdapter<>(AddAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Gronzo", error.toString()));
        RequestQueue queue1= Volley.newRequestQueue(this);
        queue1.add(stringRequest1);
        spin_branchname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dAdress=spin_branchname.getItemAtPosition(spin_branchname.getSelectedItemPosition()).toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
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
            String Client = (String) client.getSelectedItem();
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
                Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
                postAuthorizesSignatory(data_model.getEmail(),  data_model.getRole(),BranchName, person_name,WorkOrderReferenceNo,WorkOrderDescription,Client,Date2,Designation);
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
                Toast.makeText(AddAuthorizedSignatoryActivity.this, "successfully  Added", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<AddAuthorizedSignatoryModel> call, Throwable t) {

            }
        });

    }
}

