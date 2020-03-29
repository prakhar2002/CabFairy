package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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

public class ForgetPassword extends AppCompatActivity {

    String driver_id="";
    RelativeLayout login_layout,login_layout1;
    MaterialEditText mobile_num,password,passtextinput;
    Button submit,change_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        login_layout=findViewById(R.id.login_layout);
        login_layout1=findViewById(R.id.login_layout1);

        login_layout1.setEnabled(false);
        login_layout1.setVisibility(View.GONE);
        login_layout.setVisibility(View.VISIBLE);
        login_layout.setEnabled(true);


        mobile_num=findViewById(R.id.mobile_num);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mobile_num.getText().toString().equals("")||mobile_num.getText().toString().length()<10){
                    Toast.makeText(ForgetPassword.this,"Please Enter Mobile Number",Toast.LENGTH_LONG).show();
                }else{
                    Log.d("nndnfns","abc");
                    check_num();
                }
            }
        });


        password=findViewById(R.id.password);
        passtextinput=findViewById(R.id.passtextinput);
        change_password=findViewById(R.id.change_password);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals("")||passtextinput.getText().toString().equals("")){
                    if(password.getText().toString().equals("")){
                        Toast.makeText(ForgetPassword.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                        password.requestFocus();

                    }else{
                        Toast.makeText(ForgetPassword.this,"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
                        passtextinput.requestFocus();
                    }
                }else if(!password.getText().toString().equals(passtextinput.getText().toString())){
                    Toast.makeText(ForgetPassword.this,"Password Does Not Matched",Toast.LENGTH_LONG).show();
                    passtextinput.requestFocus();
                }else{
                    change_pass();
                }
            }
        });




    }

    private void change_pass() {

        final ProgressDialog progressDialog = new ProgressDialog(ForgetPassword.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String  url= "http://indiaprocess.com/CarDriving/driver_Info.php?apicall=ChangePassword";

      /*  SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String institute_id = sharedPreferences.getString(Config.Institute_id,"-1");*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("id",driver_id);
        params.put("password",passtextinput.getText().toString());
        Log.d("password",passtextinput.getText().toString());
        /*params.put("vehicle_no",Edit_vehicle_no.getText().toString());
        params.put("admin_id","1");
        params.put("mobile_no",phone_no);
*/

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
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ForgetPassword.this);
                        alertDialogBuilder.setMessage("Password Success-Fully Changed");
                        alertDialogBuilder.setCancelable(false);
                        alertDialogBuilder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {


                                        Intent intent = new Intent(ForgetPassword.this, Login_Screen.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                        //Showing the alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }else{
                        Toast.makeText(ForgetPassword.this,"RE-TRY",Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(ForgetPassword.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);

    }

    private void check_num() {
        final ProgressDialog progressDialog = new ProgressDialog(ForgetPassword.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String  url= "http://indiaprocess.com/CarDriving/driver_Info.php?apicall=checkexistnumber";

      /*  SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String institute_id = sharedPreferences.getString(Config.Institute_id,"-1");*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile_no",mobile_num.getText().toString());
        /*params.put("vehicle_no",Edit_vehicle_no.getText().toString());
        params.put("admin_id","1");
        params.put("mobile_no",phone_no);
*/

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
                    if(error.equals("true")){
                        Toast.makeText(ForgetPassword.this,"Number Is Not Registered",Toast.LENGTH_LONG).show();
                    }else {
                        driver_id=response.getString("id");
                        login_layout.setVisibility(View.GONE);
                        login_layout.setEnabled(false);
                        login_layout1.setEnabled(true);
                        login_layout1.setVisibility(View.VISIBLE);
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
        RequestQueue requestQueue = Volley.newRequestQueue(ForgetPassword.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(ForgetPassword.this, Login_Screen.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
