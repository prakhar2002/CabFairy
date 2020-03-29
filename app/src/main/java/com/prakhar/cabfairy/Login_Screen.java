package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prakhar.cabfairy.sever_classes.Config;
import com.prakhar.cabfairy.sever_classes.CustomRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Screen extends AppCompatActivity {

    TextView register_ur_self,forget_password;
    MaterialEditText userid_textinput,passtextinput;
    private boolean loggedIn = false;

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);
        register_ur_self=findViewById(R.id.register_ur_self);
        forget_password=findViewById(R.id.forget_password);
        userid_textinput=findViewById(R.id.userid_textinput);
        passtextinput=findViewById(R.id.passtextinput);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userid_textinput.getText().toString().equals("")||passtextinput.getText().toString().equals("")){
                    if(userid_textinput.getText().toString().equals("")){
                        Toast.makeText(Login_Screen.this,"Please Enter Mobile Number",Toast.LENGTH_LONG).show();
                        userid_textinput.requestFocus();
                    }else if(passtextinput.getText().toString().equals("")){
                        Toast.makeText(Login_Screen.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                        passtextinput.requestFocus();
                    }
                }else{
                    if(userid_textinput.getText().toString().length()==10) {

                        user_login();

                    }else{
                        Toast.makeText(Login_Screen.this,"Please Enter Proper Mobile Number",Toast.LENGTH_LONG).show();
                        userid_textinput.requestFocus();
                    }

                }
            }
        });

        register_ur_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Screen.this,RegisterDriver.class);
                startActivity(intent);
                finish();
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Screen.this,ForgetPassword.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if(loggedIn){
            String id = sharedPreferences.getString(Config.User_Id, "NUll");
            if(id.equals("1")){
                Intent intent = new Intent(Login_Screen.this, AdminMainActivity.class);
                startActivity(intent);
                finish();

            }
            else {
                Intent intent = new Intent(Login_Screen.this, MainActivity.class);
                intent.putExtra("value", 0);
                startActivity(intent);
                finish();
            }
        }
    }

    private void user_login() {

        final ProgressDialog progressDialog = new ProgressDialog(Login_Screen.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=driverinfo";

      /*SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String institute_id = sharedPreferences.getString(Config.Institute_id,"-1");*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile_no",userid_textinput.getText().toString());
        params.put("password",passtextinput.getText().toString());
        Log.d("password",passtextinput.getText().toString());

        /*params.put("vehicle_no",Edit_vehicle_no.getText().toString());
        params.put("admin_id","1");
        params.put("mobile_no",phone_no);*/
        /*  params.put("institute_id",insti_id);*/

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

                        JSONObject jsonObject = response.getJSONObject("driver");
                        String id =jsonObject.getString("id");
                        String name =jsonObject.getString("name");
                        String vehicle_name =jsonObject.getString("vehicle_name");
                        String vehicle_no =jsonObject.getString("vehicle_no");
                        String mobile_no =jsonObject.getString("mobile_no");

                        SharedPreferences sharedPreferences = Login_Screen.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF,true);
                        editor.putString(Config.SHARED_PREF_NAME,name);
                        editor.putString(Config.USER_SHARED_PREF, name);
                        editor.putString(Config.Login_USER_NAME, name);
                        editor.putString(Config.User_Id, id);
                        editor.putString(Config.VEHICLE_NAME, vehicle_name);
                        editor.putString(Config.VEHICLE_NO,vehicle_no);
                        editor.putString(Config.MOBILE_NUM, mobile_no);
                        editor.commit();
                        if(id.equals("1")){
                            Intent intent = new Intent(Login_Screen.this, AdminMainActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Intent intent = new Intent(Login_Screen.this, MainActivity.class);
                            intent.putExtra("value", 0);
                            startActivity(intent);
                            finish();
                        }


                    }else{
                        Toast.makeText(Login_Screen.this,"IN-Valid Number Or Password",Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(Login_Screen.this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);

    }
}
