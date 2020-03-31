package com.prakhar.cabfairy;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
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
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.prakhar.cabfairy.sever_classes.ImageNicer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class Profile extends Fragment {
    private static int RESULT_LOAD_IMAGE = 1;
    String extenion_photos;
    Bitmap bitmap_profile_image;
    LinearLayout get_image_from_gallery,get_student_sign;
    private static final int PERMISSION_CODE = 1001;
    TextView driver_name,driver_status,car_name,driving_licence,Car_number,rc_book,taxi_insurance,fitness_certificate,pan_card,selfie;
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
        selfie=root.findViewById(R.id.selfie);
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

        selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Context context = getActivity().getApplication();
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        String[] premission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(premission, PERMISSION_CODE);
                        Boolean b= checkWriteExternalPermission();
                        if(b.equals(true)){
                            Toast.makeText(getActivity(),"dsfdsf",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getContext(),"dsfdsf",Toast.LENGTH_LONG).show();
                        }
                    }
                }
*/

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    //your code
                }*/


                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

                        String [] premission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(premission,PERMISSION_CODE);
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    }else{
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    }
                }else{
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
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


    private boolean checkWriteExternalPermission()
    {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = getActivity().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
        };

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Log.d("hldfdshfd","jflkkdsjfldjslkfjdslkfjkdslfjl");
            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

                extenion_photos= picturePath.substring(picturePath.lastIndexOf("."));

            File sd = Environment.getExternalStorageDirectory();
            String newString = picturePath.substring(picturePath.lastIndexOf("/")+1, picturePath.indexOf("."));
            File image = new File(picturePath);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
//            bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);

//                bitmap_profile_image = BitmapFactory.decodeFile(picturePath);

//                bitmap_profile_image =  Bitmap.createScaledBitmap(bitmap_profile_image, 200, 200, true);

//                bitmap_profile_image= ImageNicer.decodeSampledBitmapFromResource(picturePath,300,200);
              profile_image.setVisibility(View.VISIBLE);
                profile_image.setImageBitmap(bitmap);
//            upload_taxi_insurance_data();


           /* final Cursor cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
            if (cursor.moveToFirst()) {


                if (Build.VERSION.SDK_INT >= 29) {
                    // You can replace '0' by 'cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)'
                    // Note that now, you read the column '_ID' and not the column 'DATA'
                    Uri imageUri= ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getInt(0));

                    // now that you have the media URI, you can decode it to a bitmap
                    try (ParcelFileDescriptor pfd = getContext().getContentResolver().openFileDescriptor(imageUri, "r")) {
                        if (pfd != null) {

                            String picturePath = String.valueOf(pfd.getFileDescriptor());

                            bitmap_profile_image = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());


                            extenion_photos= picturePath.substring(picturePath.lastIndexOf("."));

                            profile_image.setImageBitmap(bitmap_profile_image);
                            upload_taxi_insurance_data();
                        }
                    } catch (IOException ex) {

                    }
                } else {
                    // Repeat the code you already are using
                }
            }*/
        }


    }

    private void upload_taxi_insurance_data() {
        final ProgressDialog   progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        progressDialog.setCanceledOnTouchOutside(false);
        String  url= "http://expresscab.in/CarDriving/driver_Info.php?apicall=DriverProfilePic";
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String User_Id = sharedPreferences.getString(Config.User_Id,"-1");



        params.put("image", StringtoBitmap(bitmap_profile_image));
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


                        Toast.makeText(getContext(),"SuccessFully Submitted",Toast.LENGTH_LONG).show();



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
    }

    private String StringtoBitmap(Bitmap bitmap_profile_image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap_profile_image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

}


