package com.appyhigh.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    CardView global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);

        global = findViewById(R.id.id_global);
        global.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if(null!=networkInfo) {

            switch (v.getId()) {

                case R.id.id_global:
                    startActivity(new Intent(Dashboard.this, Global.class));
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Cannot Connect. Please connect to internet..",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
