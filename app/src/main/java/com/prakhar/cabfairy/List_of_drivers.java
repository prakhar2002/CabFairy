package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prakhar.cabfairy.sever_classes.CustomRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class List_of_drivers extends AppCompatActivity {

    ArrayList<String>id_ = new ArrayList<>();
    ArrayList<String> sno_ = new ArrayList<>();
    ArrayList<String> driver_name = new ArrayList<>();
    ArrayList<String> driver_car_name = new ArrayList<>();

    RecyclerView list_of_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_drivers);
        list_of_user=findViewById(R.id.list_of_user);
        RecyclerView.LayoutManager manager = new GridLayoutManager(List_of_drivers.this,1);
        list_of_user.setHasFixedSize(true);
        list_of_user.setLayoutManager(manager);

        driver();
    }

    void  driver(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=GetAllDataOfDriverAadharDetails";
        Map<String, String> params = new HashMap<String, String>();
/*
                SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String User_Id = sharedPreferences.getString(Config.User_Id,"-1");
*/






        final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                progressDialog.dismiss();


                try {
                    String error= response.getString("error");
                    if(error.equals("false")) {
                        id_.clear();
                        sno_.clear();
                        driver_name.clear();
                        driver_car_name.clear();
                        id_.add("0");
                        sno_.add("Sno.");
                        driver_name.add("Name");
                        driver_car_name.add("Aadhar NO.");
                        try {
                            JSONArray jsonArray = response.getJSONArray("driver");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jns = jsonArray.getJSONObject(i);
                                String name = jns.getString("name");
                                String id =jns.getString("id");
                                String aadhar_no=jns.getString("aadhar_no");
                                id_.add(id);
                                sno_.add(String.valueOf(i));
                                driver_name.add(name);
                                driver_car_name.add(aadhar_no);
                                Log.d("jsljfkldsjlkf",String.valueOf(i));
                            }
                            List_of_drivers_Adapter Struct_icons_Adapter1 = new List_of_drivers_Adapter(id_,sno_, driver_name,driver_car_name,List_of_drivers.this);
                            list_of_user.setAdapter(Struct_icons_Adapter1);
                            Struct_icons_Adapter1.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(List_of_drivers.this,"Re-Try",Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);

    }
}
