//package com.appyhigh.newsfeed;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.List;
//import java.util.Locale;
//
//public class FetchCordinates extends AsyncTask<String, Integer, String> {
//    ProgressDialog progDailog = null;
//
//    public double lati = 0.0;
//    public double longi = 0.0;
//
//    public LocationManager mLocationManager;
//    public VeggsterLocationListener mVeggsterLocationListener;
//
//    @Override
//    protected void onPreExecute() {
//        mVeggsterLocationListener = new VeggsterLocationListener();
//        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        mLocationManager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER, 0, 0,
//                mVeggsterLocationListener);
//
//        progDailog = new ProgressDialog(FastMainActivity.this);
//        progDailog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                FetchCordinates.this.cancel(true);
//            }
//        });
//        progDailog.setMessage("Loading...");
//        progDailog.setIndeterminate(true);
//        progDailog.setCancelable(true);
//        progDailog.show();
//
//    }
//
//    @Override
//    protected void onCancelled(){
//        System.out.println("Cancelled by user!");
//        progDialog.dismiss();
//        mLocationManager.removeUpdates(mVeggsterLocationListener);
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        progDailog.dismiss();
//
//        Toast.makeText(FastMainActivity.this,
//                "LATITUDE :" + lati + " LONGITUDE :" + longi,
//                Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected String doInBackground(String... params) {
//        // TODO Auto-generated method stub
//
//        while (this.lati == 0.0) {
//
//        }
//        return null;
//    }
//
//    public class VeggsterLocationListener implements LocationListener {
//
//        @Override
//        public void onLocationChanged(Location location) {
//
//            int lat = (int) location.getLatitude(); // * 1E6);
//            int log = (int) location.getLongitude(); // * 1E6);
//            int acc = (int) (location.getAccuracy());
//
//            String info = location.getProvider();
//            try {
//
//                // LocatorService.myLatitude=location.getLatitude();
//
//                // LocatorService.myLongitude=location.getLongitude();
//
//                lati = location.getLatitude();
//                longi = location.getLongitude();
//
//            } catch (Exception e) {
//                // progDailog.dismiss();
//                // Toast.makeText(getApplicationContext(),"Unable to get Location"
//                // , Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            Log.i("OnProviderDisabled", "OnProviderDisabled");
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            Log.i("onProviderEnabled", "onProviderEnabled");
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status,
//                                    Bundle extras) {
//            Log.i("onStatusChanged", "onStatusChanged");
//
//        }
//
//    }
//
//}
