package tdbank.com.tdbank;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewFragment;
import android.widget.Toast;

import java.util.ArrayList;

import tdbank.com.tdbank.services.MyLocationInfo;
import tdbank.com.tdbank.services.Weather_report;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity implements WeatherRequestFragment.RespondBack {

    Handler handler;

    String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
    final int permsRequestCode = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission())
                ActivityCompat.requestPermissions(this, perms, permsRequestCode);
            else
            {


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frame, new WeatherRequestFragment());
                ft.addToBackStack("WeatherRequestFragment");
                ft.commit();
            }


        }



    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED;
    }





    @Override
    public void ResultResponsse(ArrayList<Weather_report> list) {

        getFragmentManager();

        Bundle b = new Bundle();
        b.putParcelableArrayList("TDB", list);

        WeatherResponseFragment weather_resp = new WeatherResponseFragment();

        weather_resp.setArguments(b);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame, weather_resp);
        ft.addToBackStack("WeatherResponseFragment");
        ft.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case permsRequestCode:

                if (grantResults.length > 0) {

                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.add(R.id.frame, new WeatherRequestFragment());
                        ft.commit();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    ActivityCompat.requestPermissions(MainActivity.this,perms, permsRequestCode);
                                                }
                                            }
                                        });
                            }
                        }

                        break;
                    }


                }


                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
