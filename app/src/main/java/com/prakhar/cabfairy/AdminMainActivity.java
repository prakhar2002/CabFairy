package com.prakhar.cabfairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AdminMainActivity extends AppCompatActivity {

    RelativeLayout aadharCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        aadharCard=findViewById(R.id.aadharCard);

        aadharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainActivity.this,List_of_drivers.class);
                startActivity(intent);
            }
        });
    }
}
