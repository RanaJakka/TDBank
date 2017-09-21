package tdbank.com.tdbank.services;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rana Prathap on 9/19/2017.
 */

public class MyLocationInfo implements LocationListener {

    private double latValue;
    private double longValue;
    private String cityName;
    private Context ct;
    public MyLocationInfo(Context c)
    {
        ct=c;
    }



    @Override


    public void onLocationChanged(Location location) {

        latValue=location.getLatitude();
        longValue=location.getLongitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public double getLatitude()
    {
        return latValue;
    }
    public double getLongitude()
    {
        return longValue;
    }


    public String getcity()
    {
        Geocoder geocoder = new Geocoder(ct, Locale.getDefault());
        List<Address> addresses = null;
        String cityName=null;
        try {
            addresses = geocoder.getFromLocation(getLatitude(),getLongitude(), 1);
            cityName = addresses.get(0).getLocality();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityName;
    }

    public boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) ct.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ct);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                ct.startActivity(intent);
                dialog.dismiss();
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public  boolean isInternetWorking() {

            ConnectivityManager cm = (ConnectivityManager)
                    ct.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

    }
}
