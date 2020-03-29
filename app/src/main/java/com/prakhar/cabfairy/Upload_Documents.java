package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prakhar.cabfairy.sever_classes.Config;
import com.prakhar.cabfairy.sever_classes.CustomRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Upload_Documents extends AppCompatActivity {

    Bundle bundle;
    int open=0;
    TextView forntside,backside;
    private static int RESULT_LOAD_IMAGE = 1;
    LinearLayout get_image_from_gallery,get_student_sign;
    private static final int PERMISSION_CODE = 1001;
    ArrayAdapter shortcuts;

    Spinner choose_category;

    ImageView profile_image,student_sign;
    Bitmap bitmap_profile_image,bitmap_signature;
    Button submit;
    ProgressDialog progressDialog;
    Context context;
    String extenion_photos,extenion_sign;
    int postion_check=-1,check_image=0,check_sign=0;
    int view_list=0,iiiiii=0,iiiip=0;
    ViewPager viewPager;
    TableLayout tabLayout;
    String photooo="",signnn="";
    View view;
    MaterialEditText card_num,name,issue_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__documents);
        bundle=getIntent().getExtras();
        open=bundle.getInt("open");
        forntside=findViewById(R.id.forntside);
        backside=findViewById(R.id.backside);


        card_num=findViewById(R.id.card_num);
        name=findViewById(R.id.name);
        issue_date=findViewById(R.id.issue_date);
        profile_image=findViewById(R.id.profile_image);
        student_sign=findViewById(R.id.student_sign);
        submit=findViewById(R.id.submit_register);
        get_image_from_gallery=findViewById(R.id.get_image_from_gallery);
        get_student_sign=findViewById(R.id.get_student_sign);

        if(open==1){
            forntside.setText("Upload Front Side Of Adhar Card");
            backside.setText("Upload Back Side Of Adhar Card");
        }else if(open ==2){
            forntside.setText("Upload Front Side Of Licence Card");
            backside.setText("Upload Back Side Of Licence Card");
        }else if(open==4){
            forntside.setText("Upload Front Side Of RC BOOK");
            backside.setText("Upload Back Side Of RC BOOK");
        }else if(open==5){
            forntside.setText("Upload Front Side Of Taxi Insurance");
            backside.setText("Upload Back Side Of Taxi Insurance");
        }else if(open==6){
            forntside.setText("Upload Front Side Of Fitness Certificate");
            backside.setText("Upload Back Side Of Fitness Certificate");
        }else if(open==9){
            forntside.setText("Upload Front Side Of Pan Card");
            backside.setText("Upload Back Side Of Pan Card");
        }

        issue_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //setting data to array, when changed
/*
                if(issue_date.getText().toString().length()==2){
                    issue_date.setText(issue_date.getText().toString()+"-");
                }else if(issue_date.getText().toString().length()==5){
                    issue_date.setText(issue_date.getText().toString()+"-");
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                //blank
            }

        });


        get_image_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion_check=1;
                check_image=0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Context context = getApplication();
                    if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        String[] premission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(premission, PERMISSION_CODE);
                       Boolean b= checkWriteExternalPermission();
                       if(b.equals(true)){
                           Toast.makeText(Upload_Documents.this,"dsfdsf",Toast.LENGTH_LONG).show();
                       }
                    }
                    else {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        String[] premission1 = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(premission1, PERMISSION_CODE);
                        Boolean b= checkWriteExternalPermission();
                        if(b.equals(true)){
                            Toast.makeText(Upload_Documents.this,"dsfdsf",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {

                        Toast.makeText(Upload_Documents.this,"abc",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });



        get_student_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion_check=0;
                check_sign=0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Context context = getApplication();
                    if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        String[] premission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(premission, PERMISSION_CODE);

                    }
                    else {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);

                    }
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        String[] premission1 = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(premission1, PERMISSION_CODE);

                    }
                    else {

                        Toast.makeText(Upload_Documents.this,"abc",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!name.getText().toString().equals("")&&!card_num.getText().toString().equals("")&&!issue_date.getText().toString().equals(""))
            {
                if(open ==1) {
                    upload_data();
                }else if(open ==2){

                    upload_Licence_data();
                }else if(open==4){
                    upload_RCBook_data();
                }else if(open ==5){
                    upload_taxi_insurance_data();
                }else if(open==6){
                    upload_fitness_certificate_data();
                }else if(open==9){
                    upload_pan_card_data();
                }
            }else{
                if(name.getText().toString().equals("")){
                    Toast.makeText(Upload_Documents.this,"Please Enter the name",Toast.LENGTH_LONG).show();
                    name.requestFocus();

                }else if(card_num.getText().toString().equals("")){
                    Toast.makeText(Upload_Documents.this,"Please Enter the Card Number",Toast.LENGTH_LONG).show();
                    card_num.requestFocus();

                }else if(issue_date.getText().toString().equals("")){
                    Toast.makeText(Upload_Documents.this,"Please Enter the Issued Date",Toast.LENGTH_LONG).show();
                    issue_date.requestFocus();
                }
            }

        }
    });

    }

    private void upload_pan_card_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=PANCardDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("pan_font", StringtoBitmap(bitmap_profile_image));
        params.put("pan_back", StringtoBitmap(bitmap_signature));
        params.put("pan_no  ",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("mfd_date",issue_date.getText().toString());
        params.put("exp_date","");
        params.put("status","0");
        params.put("driver_id",User_Id);




        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {


                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();

                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    private void upload_fitness_certificate_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverFitnessDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("certificate_front", StringtoBitmap(bitmap_profile_image));
        params.put("certificate_back", StringtoBitmap(bitmap_signature));
        params.put("certificate_no ",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("mfd_date",issue_date.getText().toString());
        params.put("exp_date","");
        params.put("status","0");
        params.put("driver_id",User_Id);




        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {


                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();

                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    private void upload_taxi_insurance_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverTaxiInsuranceDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("insurance_front", StringtoBitmap(bitmap_profile_image));
        params.put("insurance_back", StringtoBitmap(bitmap_signature));
        params.put("insurance_no",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("mfd_date",issue_date.getText().toString());
        params.put("exp_date","");
        params.put("status","0");
        params.put("driver_id",User_Id);




        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {


                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();

                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    private void upload_RCBook_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverRcDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("rc_front", StringtoBitmap(bitmap_profile_image));
        params.put("rc_back", StringtoBitmap(bitmap_signature));
        params.put("rc_no",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("mfd_date",issue_date.getText().toString());
        params.put("status","0");
        params.put("driver_id",User_Id);




        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {


                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();

                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    private void upload_Licence_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverLicenseDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("driving_license_front", StringtoBitmap(bitmap_profile_image));
        params.put("driving_license_back", StringtoBitmap(bitmap_signature));
        params.put("license_no",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("mfd_date",issue_date.getText().toString());
        params.put("status","0");
        params.put("driver_id",User_Id);




        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {

                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();


                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    private void upload_data() {


     final ProgressDialog   progressDialog = new ProgressDialog(Upload_Documents.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverAadharDetails";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("aadhar_front_image", StringtoBitmap(bitmap_profile_image));
        params.put("aadhar_back_image", StringtoBitmap(bitmap_signature));
        params.put("aadhar_no",card_num.getText().toString());
        params.put("name",name.getText().toString());
        params.put("Issue_Date",issue_date.getText().toString());
        params.put("status","0");
        params.put("driver_id",User_Id);





        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    */
                    if(error.equals("false")) {
                        Toast.makeText(Upload_Documents.this,"SuccessFully Submitted",Toast.LENGTH_LONG).show();

                        onBackPressed();

                    }else{
                        Toast.makeText(Upload_Documents.this,"Re-Try",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Upload_Documents.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);

    }




    private String StringtoBitmap(Bitmap bitmap_profile_image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap_profile_image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = Upload_Documents.this.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            if(postion_check==1) {
                check_image=1;
                extenion_photos= picturePath.substring(picturePath.lastIndexOf("."));

                bitmap_profile_image = BitmapFactory.decodeFile(picturePath);

                bitmap_profile_image =  Bitmap.createScaledBitmap(bitmap_profile_image, 200, 200, true);
/*
                bitmap_profile_image= ImageNicer.decodeSampledBitmapFromResource(picturePath,300,200);
*/              profile_image.setVisibility(View.VISIBLE);
                iiiip=2;
                profile_image.setImageBitmap(bitmap_profile_image);
            }
            if(postion_check==0){
                check_sign=1;
                extenion_sign= picturePath.substring(picturePath.lastIndexOf("."));
                bitmap_signature=BitmapFactory.decodeFile(picturePath);
                bitmap_signature =  Bitmap.createScaledBitmap(bitmap_signature, 200, 200, true);
                student_sign.setVisibility(View.VISIBLE);
                iiiiii=2;
                student_sign.setImageBitmap(bitmap_signature);
            }
        }


    }


    private boolean checkWriteExternalPermission()
    {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = Upload_Documents.this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(Upload_Documents.this, MainActivity.class);
            intent.putExtra("value",1);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
