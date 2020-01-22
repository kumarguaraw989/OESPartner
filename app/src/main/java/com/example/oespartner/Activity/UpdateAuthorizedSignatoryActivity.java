package com.example.oespartner.Activity;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

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

public class UpdateAuthorizedSignatoryActivity extends AppCompatActivity {
    ArrayList<String> SelectUserType = new ArrayList<>();
    String id2;
    @BindView(R.id.add_signatory)
    Button add;
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
    String email,role;

    ArrayList<String> SelectClientBranch = new ArrayList<>();
    ArrayList<String> SelectClient = new ArrayList<>();
    ArrayList<String> SelectPersonName = new ArrayList<>();
    ArrayList<String> SelectDesignation=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_authorized_signatory);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Data data_model= FastSave.getInstance().getObject("login_data", Data.class);
         email=data_model.getEmail();
         role=data_model.getRole();
        edtWorkValidUpto.setOnClickListener(view -> Constants.DateDialog(edtWorkValidUpto, UpdateAuthorizedSignatoryActivity.this));
        edtSignatoryValidupto.setOnClickListener(view -> Constants.DateDialog(edtSignatoryValidupto, UpdateAuthorizedSignatoryActivity.this));
        SelectUserType.add("Select one");
        ImgBack.setOnClickListener(v -> onBackPressed());

        add.setOnClickListener(view -> {
             String Date2 = edtSignatoryValidupto.getText().toString();
            String WorkOrderReferenceNo = edtWork_orderreference.getText().toString();
            String WorkOrderDescription = edtWork_description.getText().toString();
            String Client = (String) client.getSelectedItem();
            String BranchName = (String) spin_branchname.getSelectedItem();
            String Designation = (String) spin_designation.getSelectedItem();
            String person_name=(String)spin_nameofperson.getSelectedItem();

            if(Date2.equals("")){
                FancyToast.makeText(UpdateAuthorizedSignatoryActivity.this,"Enter Date",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                return;
            }
            else
            {
                loading.setVisibility(View.VISIBLE);
                 postAuthorizesSignatory(id2,email,  role,BranchName, person_name,WorkOrderReferenceNo,WorkOrderDescription,Client,Date2,Designation);
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String value = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            edtWorkValidUpto.setText(jsonObject.get("valid_upto").toString());
            edtSignatoryValidupto.setText(jsonObject.get("authorised_validity").toString());
            edtWork_orderreference.setText(jsonObject.get("reference_no").toString());
            edtWork_description.setText(jsonObject.get("description").toString());
            declaration1.setText(jsonObject.get("declaration1").toString());
            declaration2.setText(jsonObject.get("declaration2").toString());
            SelectClient.add(jsonObject.get("client").toString());
            client.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClient));
            SelectClient.add("Select Client");
            StringRequest stringRequest1=new StringRequest(Config.URL_CLient, response -> {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); ++i){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String catogery=jsonObject1.getString("company_name");
                        SelectClient.add(catogery);
                    }
                    client.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClient));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Log.e("error", error.toString()));
            RequestQueue queue1= Volley.newRequestQueue(this);
            queue1.add(stringRequest1);
            SelectClientBranch.add(jsonObject.get("branch").toString());
            spin_branchname.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectClientBranch));
            SelectClientBranch.add("Select Branch");
            StringRequest stringRequest2=new StringRequest(Config.URL_ClientBranch, response -> {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); ++i){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String catogery=jsonObject1.getString("branch");
                        SelectClientBranch.add(catogery);
                    }
                    spin_branchname.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectClientBranch));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Log.e("Gronzo", error.toString()));
            RequestQueue queue2= Volley.newRequestQueue(this);
            queue2.add(stringRequest2);
            SelectPersonName.add(jsonObject.get("person_name").toString());
            spin_nameofperson.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectPersonName));
            SelectPersonName.add("Select Person Name");
            StringRequest stringRequest3=new StringRequest(Request.Method.POST, Config.URL_PersonName, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray=new JSONArray(response.toString());
                        for (int j=0; j<jsonArray.length(); ++j){
                            JSONObject jsonObject1=jsonArray.getJSONObject(j);
                            String catogery=jsonObject1.getString("person_name");
                             SelectPersonName.add(catogery);
                         }

                        spin_nameofperson.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectPersonName));
                     } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(UpdateAuthorizedSignatoryActivity.this,response, Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(UpdateAuthorizedSignatoryActivity.this,error.toString(), Toast.LENGTH_SHORT).show()){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("email",email);
                    params.put("role",role);
                    return params;
                }
            };
            RequestQueue queue3=Volley.newRequestQueue(this);
            queue3.add(stringRequest3);
            SelectDesignation.add(jsonObject.get("designationnn").toString());
            spin_designation.setAdapter(new ArrayAdapter<>(UpdateAuthorizedSignatoryActivity.this,android.R.layout.simple_spinner_dropdown_item,SelectDesignation));
            String[] designation = {"Promoter/Partener/Proprietor", "Engineer", "Manager" ,"Supervisor" , "Driver" ,"Helper","Contract","Labour","Electrician","Security","Other"};
            spin_designation.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, designation));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void postAuthorizesSignatory(String id2,String email, String role, String branch, String person_name, String reference_no, String description, String client, String valid_upto, String designationnn) {
        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
         Call<AddAuthorizedSignatoryModel> call = apiService.UpdateAuthorizedSignatory(id2,email, role, branch, person_name, reference_no, description, client, valid_upto, designationnn);
        call.enqueue(new Callback<AddAuthorizedSignatoryModel>() {
            @Override
            public void onResponse(Call<AddAuthorizedSignatoryModel> call, Response<AddAuthorizedSignatoryModel> response) {
                //  System.out.println(response);
                loading.setVisibility(View.GONE);
                Toast.makeText(UpdateAuthorizedSignatoryActivity.this, "successfully  Added", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<AddAuthorizedSignatoryModel> call, Throwable t) {

            }
        });

    }

}

