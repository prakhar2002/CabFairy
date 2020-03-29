package com.prakhar.cabfairy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.prakhar.cabfairy.sever_classes.Config;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    int current_index_id=-1;
    Bundle bundle; int get=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View header=navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        bundle=getIntent().getExtras();
        get=bundle.getInt("value");
        if(get==1){
            Profile galleryFragment = new Profile();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
        }


    }


      @Override
      public void onBackPressed() {
          /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          if (drawer.isDrawerOpen(GravityCompat.START)) {
              drawer.closeDrawer(GravityCompat.START);
          } else {
          */    Fragment fr = getSupportFragmentManager().findFragmentById(current_index_id);
              Fragment main = getSupportFragmentManager().findFragmentById(R.id.nav_home);
              if(fr==main){
                  super.onBackPressed();
              }else{
                  Home_Fragment galleryFragment = new Home_Fragment();
                  FragmentManager fragmentManager = getSupportFragmentManager();
                  getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                  fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
              }

      }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        current_index_id=id;
        if(id==R.id.nav_home){
            Home_Fragment galleryFragment = new Home_Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
      /* Toast.makeText(MainActivity.this,"bdsajkdbjx",Toast.LENGTH_LONG).show();
      */  }
        else if(id==R.id.nav_gallery){
            Transaction_history galleryFragment = new Transaction_history();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
        }
        else if(id==R.id.nav_slideshow){
            Pay_commission galleryFragment = new Pay_commission();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
        }
        else if(id==R.id.nav_share){
            Profile galleryFragment = new Profile();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();
        }else if(id==R.id.nav_send){
            HelpAndSupport galleryFragment = new HelpAndSupport();
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,galleryFragment).commit();

        }else if(id==R.id.nav_logout){
            logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return  true;
    }

    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();
                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                        //Putting blank value to email
                        editor.putString(Config.USER_SHARED_PREF, "");
                        //Saving the sharedpreferences
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this,Login_Screen.class);
                        startActivity(intent);
                        finish();
                    }
                });


        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}

