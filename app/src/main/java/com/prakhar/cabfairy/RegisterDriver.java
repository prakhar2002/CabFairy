package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prakhar.cabfairy.sever_classes.CustomRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterDriver extends AppCompatActivity {


    MaterialEditText Edit_name,Edit_phone_no,Edit_email,Edit_pass,Edit_conform_pass,Edit_vehicle_no,Edit_vehicle_name;
    Button submit_register;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        Edit_name=findViewById(R.id.Edit_name);
        Edit_phone_no=findViewById(R.id.Edit_phone_no);
        Edit_vehicle_no=findViewById(R.id.Edit_vehicle_no);
        Edit_vehicle_name=findViewById(R.id.Edit_vehicle_name);
        Edit_email=findViewById(R.id.Edit_email);
        Edit_email.setVisibility(View.GONE);
        Edit_email.setEnabled(false);
        Edit_pass=findViewById(R.id.Edit_pass);
        Edit_conform_pass=findViewById(R.id.Edit_conform_pass);
        submit_register=findViewById(R.id.submit_register);

        submit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Edit_vehicle_name.getText().toString().equals("")&&!Edit_name.getText().toString().equals("")&&!Edit_phone_no.getText().toString().equals("")
                        &&!Edit_pass.getText().toString().equals("")
                &&!Edit_conform_pass.getText().toString().equals("")&&!Edit_vehicle_no.getText().toString().equals("")){
                    /*if(Edit_email.getText().toString().matches(emailPattern)){
                      */  if(Edit_pass.getText().toString().equals(Edit_conform_pass.getText().toString())){
                           /* Edit_name.getText().clear();
                            Edit_phone_no.getText().clear();
                            Edit_email.getText().clear();
                            Edit_pass.getText().clear();
                            Edit_conform_pass.getText().clear();*/
                            String name=Edit_name.getText().toString();
                            String phone_no=Edit_phone_no.getText().toString();
                            String email=Edit_email.getText().toString();
                            String pass=Edit_conform_pass.getText().toString();

                            Register_Driver(name,phone_no,email,pass);


                        }else{
                            Toast.makeText(RegisterDriver.this,"Password Miss Match Please Try Again",Toast.LENGTH_LONG).show();
                            Edit_conform_pass.requestFocus();
                        }
                    /*}else{
                        Toast.makeText(RegisterDriver.this,"Please Enter The Valid Email-ID",Toast.LENGTH_LONG).show();
                        Edit_email.requestFocus();
                    }*/
                }else{
                    if(Edit_name.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Name",Toast.LENGTH_LONG).show();
                        Edit_name.requestFocus();
                    }else if(Edit_phone_no.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Phone Number",Toast.LENGTH_LONG).show();
                        Edit_phone_no.requestFocus();

                    }/*else if(Edit_email.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Email-ID",Toast.LENGTH_LONG).show();
                        Edit_email.requestFocus();

                    }*/else if(Edit_vehicle_no.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Vehicle Number",Toast.LENGTH_LONG).show();
                        Edit_vehicle_no.requestFocus();
                    }else if(Edit_vehicle_name.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Vehicle Name",Toast.LENGTH_LONG).show();

                        Edit_vehicle_name.requestFocus();
                    }
                    else if(Edit_pass.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Password",Toast.LENGTH_LONG).show();
                        Edit_pass.requestFocus();

                    }else if(Edit_conform_pass.getText().toString().equals("")){
                        Toast.makeText(RegisterDriver.this,"Please Enter The Conform Password",Toast.LENGTH_LONG).show();
                        Edit_conform_pass.requestFocus();

                    }
                }
            }
        });



    }

    private void Register_Driver(String name, String phone_no, String email, String pass) {
       final ProgressDialog  progressDialog = new ProgressDialog(RegisterDriver.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String  url= "http://expresscab.in//CarDriving/driver_Info.php?apicall=driverprofile";

      /*  SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String institute_id = sharedPreferences.getString(Config.Institute_id,"-1");*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("image","");
        params.put("name",name);
        params.put("vehicle_no",Edit_vehicle_no.getText().toString());
        params.put("vehicle_name",Edit_vehicle_name.getText().toString().toUpperCase());
        params.put("mobile_no",phone_no);
        params.put("password",pass);


        /*  params.put("institute_id",insti_id);*/

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

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
                       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterDriver.this);
                       alertDialogBuilder.setMessage("Success-Fully Registered");
                       alertDialogBuilder.setCancelable(false);
                       alertDialogBuilder.setPositiveButton("OK",
                               new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface arg0, int arg1) {


                                       Intent intent = new Intent(RegisterDriver.this, Login_Screen.class);
                                       startActivity(intent);
                                       finish();
                                   }
                               });
                       //Showing the alert dialog
                       AlertDialog alertDialog = alertDialogBuilder.create();
                       alertDialog.show();
                   }else{
                       Toast.makeText(RegisterDriver.this,"Allready Registered Number",Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterDriver.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(RegisterDriver.this, Login_Screen.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
