package com.prakhar.cabfairy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




public class List_of_drivers_Adapter extends RecyclerView.Adapter<List_of_drivers_Adapter.viewHolder> {
    public class viewHolder extends RecyclerView.ViewHolder {

        TextView sno,driver_name,driver_car_name;
        LinearLayout layout_click;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            layout_click=itemView.findViewById(R.id.layout_click);
            sno = itemView.findViewById(R.id.sno_);
            driver_name=itemView.findViewById(R.id.driver_name);
            driver_car_name=itemView.findViewById(R.id.driver_car_name);
        }

    }
    ArrayList<String>id_ = new ArrayList<>();
    ArrayList<String> sno_ = new ArrayList<>();
    ArrayList<String> driver_name = new ArrayList<>();
    ArrayList<String> driver_car_name = new ArrayList<>();
    Context context;
    public List_of_drivers_Adapter(ArrayList<String>id_,ArrayList<String> sno_,ArrayList<String> driver_name,ArrayList<String> driver_car_name ,Context context) {
        this.id_=id_;
        this.sno_ = sno_;
        this.driver_name=driver_name;
        this.driver_car_name=driver_car_name;
        this.context = context;
    }

    @NonNull
    @Override
    public List_of_drivers_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewfull = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_drivers_adapter, viewGroup, false);
        List_of_drivers_Adapter.viewHolder v = new List_of_drivers_Adapter.viewHolder(viewfull);

        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final List_of_drivers_Adapter.viewHolder holder, final int i) {
      /*  Glide.with(context).asBitmap().load(mIcons.get(i)).into(viewHolder.imageView);
        viewHolder.textView.setText(mIconText.get(i));

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        holder.sno.setText(sno_.get(i));
        holder.driver_name.setText(driver_name.get(i));
        holder.driver_car_name.setText(driver_car_name.get(i));

        holder.layout_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Upload_Documents.class);
                intent.putExtra("open","Admin_side");
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });


    }




    @Override
    public int getItemCount() {
/*
        String s = String.valueOf(mIconText.size());
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();

*/
        return id_.size();
    }
}
