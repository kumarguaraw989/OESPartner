package com.example.oespartner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.App_Helper.Constants;
import com.example.oespartner.Model.AddPartnerPersonModel;
import com.example.oespartner.Model.Data;
import com.example.oespartner.R;
import com.example.oespartner.WebService.ApiClient;
import com.example.oespartner.WebService.RetrofitApi;
import com.shashank.sony.fancytoastlib.FancyToast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    Spinner nationality;
    EditText AadharNo,PanNo,PoliceVerification;
    LinearLayout layoutuploadAadharPan ,layoutindia,layoutothers;

    @BindView(R.id.edtDateofBirth) EditText edtDateofBirth;
    @BindView(R.id.edtDateVisaValidity) EditText edtDateVisaValidity;
    @BindView(R.id.edtDateofissueP) EditText edtDateofissueP;

    @BindView(R.id.person_name) EditText person_name;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.email1) EditText email1;
    @BindView(R.id.father_name) EditText father_name;
    @BindView(R.id.age) EditText age;


    @BindView(R.id.spntitle)Spinner spntitle;
    @BindView(R.id.spnGender)Spinner spnGender;
    @BindView(R.id.spnBloodGrp)Spinner spnBloodGrp;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.photo)
    ImageView photo;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    ImageView imageView ;
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    private static final int SELECT_PHOTO = 200;
    private static final int CAMERA_REQUEST = 1888;
    final int MY_PERMISSIONS_REQUEST_WRITE = 103;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner_person);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        nationality=findViewById(R.id.spinNationality);
        AadharNo=findViewById(R.id.edtAadharNo);
        PanNo=findViewById(R.id.edtPanNo);
        PoliceVerification=findViewById(R.id.edtReference_policeverification);
        layoutuploadAadharPan=findViewById(R.id.layout_AadharPanupload);
        layoutindia=findViewById(R.id.layout_india);
        layoutothers=findViewById(R.id.layoutothers);
        setupSpinners();


        edtDateofBirth.setOnClickListener(view -> Constants.DateDialog(edtDateofBirth, AddPartnerPersonActivity.this));
        edtDateVisaValidity.setOnClickListener(view -> Constants.DateDialog(edtDateVisaValidity, AddPartnerPersonActivity.this));
        edtDateofissueP.setOnClickListener(view -> Constants.DateDialog(edtDateofissueP, AddPartnerPersonActivity.this));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE);
        }




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title1 = spntitle.getSelectedItem().toString();
                String person_name1 = person_name.getText().toString();
                String phone1 = phone.getText().toString();
                String email11 = email1.getText().toString();
                String father_name1 = father_name.getText().toString();
                String dob1 = edtDateofBirth.getText().toString();
                String age1 = age.getText().toString();
                String blood_group1 = spnBloodGrp.getSelectedItem().toString();
                String gender1 = spnGender.getSelectedItem().toString();
                if(title1.equals("")){
                    FancyToast.makeText(AddPartnerPersonActivity.this,"Enter First Name",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    return;
                }


                else{
                    progress_bar.setVisibility(View.VISIBLE);
                    Data data_model= FastSave.getInstance().getObject("login_data",Data.class);

                    postVisitorGatePass(data_model.getEmail(),  data_model.getRole(),  title1,  person_name1,  phone1, email11,  photo,
                            father_name1,  dob1,  age1, blood_group1, gender1);
                    onBackPressed();
                }
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        setTitle();
        //back();

    }


    void setupSpinners(){
        nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    layoutindia.setVisibility(View.INVISIBLE);
                    layoutothers.setVisibility(View.INVISIBLE);
                }
                else if (position == 1){
                    layoutindia.setVisibility(View.VISIBLE);
                    layoutothers.setVisibility(View.INVISIBLE);
                }else if (position == 2){
                    layoutindia.setVisibility(View.INVISIBLE);
                    layoutothers.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }

    private void postVisitorGatePass(String email, String role, String title, String person_name, String phone, String email1, ImageView photo,
                                     String father_name, String dob, String age, String gender, String blood_group) {
        //LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        //String userid = model.getId();
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        data_model.getEmail();
        data_model.getRole();
        RequestBody imgFile = null;
        File imagPh = new File(String.valueOf(photo));
        //  Log.e("***********", "*************" + fileUrl);
        if (imagPh != null && (photo!=null && !photo.equals("")))
            imgFile = RequestBody.create(MediaType.parse("image/*"), imagPh);
        RequestBody requestemail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody requestrole = RequestBody.create(MediaType.parse("text/plain"), role);
        RequestBody requesttitle = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody requestperson_name = RequestBody.create(MediaType.parse("text/plain"), person_name);
        RequestBody requestphone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody requestemail1 = RequestBody.create(MediaType.parse("text/plain"), email1);
        RequestBody requestfather_name= RequestBody.create(MediaType.parse("text/plain"), father_name);
        RequestBody requestdob= RequestBody.create(MediaType.parse("text/plain"), dob);
        RequestBody requestage= RequestBody.create(MediaType.parse("text/plain"), age);
        RequestBody requesttgender= RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody requestblood_group= RequestBody.create(MediaType.parse("text/plain"), blood_group);

        RetrofitApi apiService = ApiClient.getClient().create(RetrofitApi.class);
        //Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        Call<AddPartnerPersonModel> call = apiService.AddPartnerPerson(  requestemail,  requestrole,  requesttitle,  requestperson_name,
                requestphone,requestemail1,imgFile,requestfather_name,requestdob,requestage,requesttgender,requestblood_group
        );
        call.enqueue(new Callback<AddPartnerPersonModel>() {
            @Override
            public void onResponse(Call<AddPartnerPersonModel> call, Response<AddPartnerPersonModel> response) {
                progress_bar.setVisibility(View.GONE);
                FancyToast.makeText(AddPartnerPersonActivity.this,"Data submitted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();


            }

            @Override
            public void onFailure(Call<AddPartnerPersonModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);

            }
        });
    }


    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.txttitle);
        title.setText(getString(R.string.choose_files));
    }


    Uri fileUri;

    private void selectImage() {
        final CharSequence[] options = {getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddPartnerPersonActivity.this);
        builder.setTitle(getString(R.string.add_photo));

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(getString(R.string.take_photo))) {
                    /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
                    String fileName = System.currentTimeMillis() + ".jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    fileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, CAMERA_REQUEST);
                } else if (options[item].equals(getString(R.string.choose_from_gallery))) {
                    openGallery();
                } else if (options[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    String imageImagePath = "";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            try {
                imageImagePath = getPath(fileUri);
                File file=new File(imageImagePath);
                resize(file,"");
                Bitmap b = decodeUri(fileUri);
                photo.setImageBitmap(b);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    photo.setImageURI(selectedImage);
                    imageImagePath = getPath(selectedImage);
                    File file=new File(imageImagePath);
                    resize(file,"");

                }
            }
        }
    }
    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();

        o.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(getContentResolver()
                .openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 72;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;

        int scale = 1;

        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;

            height_tmp /= 2;

            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();

        o2.inSampleSize = scale;

        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                .openInputStream(selectedImage), null, o2);

        return bitmap;
    }

    @SuppressWarnings("deprecation")
    private String getPath(Uri selectedImaeUri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(selectedImaeUri, projection, null, null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            return cursor.getString(columnIndex);
        }

        return selectedImaeUri.getPath();
    }

    BitmapFactory.Options bmOptions;
    Bitmap bitmap;
    public void resize(File file, String benchMark) {
        try {
            bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            bmOptions.inDither = true;
            bitmap = BitmapFactory.decodeFile(imageImagePath, bmOptions);
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Log.e("width & Height", "width " + bitmap.getWidth());
            if (bitmap.getWidth() > 1200) {
                w = bitmap.getWidth() * 30 / 100;
                h = bitmap.getHeight() * 30 / 100;
            }

            Log.e("width & Height", "width " + w + " height " + h);
            bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
            try {
                Log.e("Compressing", "Compressing");
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (Exception e) {
                Log.e("Exception", "Image Resizing" + e.getMessage());
            }
        } catch (
                Exception e
        ) {
            Log.e("Exception", "Exception in resizing image");
        }
    }
}

