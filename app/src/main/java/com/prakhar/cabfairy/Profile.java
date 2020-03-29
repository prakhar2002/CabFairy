package com.prakhar.cabfairy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment {

    TextView driver_name,driver_status,car_name,driving_licence,Car_number,rc_book,taxi_insurance,fitness_certificate,pan_card;
    CircleImageView profile_image;
    LinearLayout driver_details,driver_details_layout,cab_details,cab_details_layout,bank_details_layout,bank_details;
    Context context;
    TextView AadharCard;
    int open=0;
    int driver_details_layout_count=0,cab_details_layout_count=0,bank_details_layout_count=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstdriving_licenceanceState) {

        context=getActivity();
        View root = inflater.inflate(R.layout.myprofile_fragment, container, false);
        profile_image=root.findViewById(R.id.profile_image);
        pan_card=root.findViewById(R.id.pan_card);
        taxi_insurance=root.findViewById(R.id.taxi_insurance);
        fitness_certificate=root.findViewById(R.id.fitness_certificate);
        rc_book=root.findViewById(R.id.rc_book);
        driving_licence=root.findViewById(R.id.driving_licence);
        driver_name=root.findViewById(R.id.driver_name);
        driver_status=root.findViewById(R.id.driver_status);
        car_name=root.findViewById(R.id.car_name);
        driver_details=root.findViewById(R.id.driver_details);
        driver_details_layout=root.findViewById(R.id.driver_details_layout);
        driver_details_layout.setEnabled(false);
        driver_details_layout.setVisibility(View.GONE);
        cab_details_layout=root.findViewById(R.id.cab_details_layout);
        cab_details=root.findViewById(R.id.cab_details);
        cab_details_layout.setEnabled(false);
        cab_details_layout.setVisibility(View.GONE);
        bank_details_layout=root.findViewById(R.id.bank_details_layout);
        bank_details=root.findViewById(R.id.bank_details);
        bank_details_layout.setEnabled(false);
        bank_details_layout.setVisibility(View.GONE);
        Car_number=root.findViewById(R.id.Car_number);
        AadharCard=root.findViewById(R.id.AadharCard);



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Config.Login_USER_NAME,"-1");
        String vehicle_name = sharedPreferences.getString(Config.VEHICLE_NAME,"-1");
        String vehicle_no = sharedPreferences.getString(Config.VEHICLE_NO,"-1");

        driver_name.setText(name);
        car_name.setText(vehicle_name);
        Car_number.setText(vehicle_no);



        driver_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driver_details_layout_count++;

                if(driver_details_layout_count==1){
                    driver_details_layout.setEnabled(true);
                    driver_details_layout.setVisibility(View.VISIBLE);
                }else{
                    driver_details_layout.setEnabled(false);
                    driver_details_layout.setVisibility(View.GONE);
                    driver_details_layout_count=0;
                }
            }
        });

        cab_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cab_details_layout_count++;
                if(cab_details_layout_count==1){
                    cab_details_layout.setEnabled(true);
                    cab_details_layout.setVisibility(View.VISIBLE);
                }else{
                    cab_details_layout.setEnabled(false);
                    cab_details_layout.setVisibility(View.GONE);
                    cab_details_layout_count=0;
                }
            }
        });

        bank_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bank_details_layout_count++;
                if(bank_details_layout_count==1){
                    bank_details_layout.setEnabled(true);
                    bank_details_layout.setVisibility(View.VISIBLE);
                }else{
                    bank_details_layout.setEnabled(false);
                    bank_details_layout.setVisibility(View.GONE);
                    bank_details_layout_count=0;
                }
            }
        });

        AadharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=1;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);

            }
        });

        driving_licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=2;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);

            }
        });

        rc_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=4;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);



            }
        });

        taxi_insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=5;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);



              /*  final ProgressDialog   progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverAadharDetailStatusChangedByAdmin";
                Map<String, String> params = new HashMap<String, String>();
*//*
                SharedPreferences sharedPreferences = Upload_Documents.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String User_Id = sharedPreferences.getString(Config.User_Id,"-1");
*/
                /*



                params.put("id","1");
                params.put("status","2");





                final CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response: ", response.toString());
                        progressDialog.dismiss();


                        try {
                            String error= response.getString("error");
                    *//*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-"+mobile));
                    startActivity(intent);
                    *//*
                            if(error.equals("false")) {


                            }else{
                                Toast.makeText(getContext(),"Re-Try",Toast.LENGTH_LONG).show();
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
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy( 10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(jsObjRequest);
*/
            }
        });

        fitness_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=6;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);

            }
        });

        pan_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open=9;
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open",open);
                context.startActivity(intent);

            }
        });

        return root;
    }




}


