package com.appyhigh.newsfeed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.onesignal.OneSignal;

import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    CardView global, countries;
    Double latitude = 0.0;
    Double longitude = 0.0;
    Location gps_loc = null, network_loc = null, final_loc = null;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setInAppMessageClickHandler(null)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(this))
                .init();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        global = findViewById(R.id.id_global);
        countries = findViewById(R.id.id_countries);
        global.setOnClickListener(this);
        countries.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (null != networkInfo) {
            switch (v.getId()) {

                case R.id.id_global:
                    Toast.makeText(getApplicationContext(), longitude.toString() + " " + latitude.toString(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Dashboard.this, Global.class));
                    break;
                case R.id.id_tech:
                    startActivity(new Intent(Dashboard.this, Global.class));
                    break;
                case R.id.id_countries:
                    startActivity(new Intent(Dashboard.this, Country_news.class));
                    break;


            }
        } else {
            Toast.makeText(getApplicationContext(), "Cannot Connect. Please connect to internet..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

    }

}
