package tdbank.com.tdbank;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tdbank.com.tdbank.services.MyAsycTask;
import tdbank.com.tdbank.services.MyLocationInfo;
import tdbank.com.tdbank.services.NetworkCallResponse;
import tdbank.com.tdbank.services.ServicesBean;
import tdbank.com.tdbank.services.Weather_report;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by Rana Prathap on 9/19/2017.
 */

public class WeatherRequestFragment extends Fragment {


    LocationManager lm;
    public MyLocationInfo locaion_info;
    Button btn_weather;
    TextView tx;
    Activity ac;
    Handler handl;
    ArrayList<Weather_report> list_weather = new ArrayList<>();

    RespondBack obj_sendback;
    private final String FLAG="LOC";

    interface RespondBack {
        void ResultResponsse(ArrayList<Weather_report> list);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_report_request, container, false);
        btn_weather = (Button) view.findViewById(R.id.request_btn);
        handl = new Handler();
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locaion_info = new MyLocationInfo(getActivity());
        tx = (TextView) view.findViewById(R.id.text);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locaion_info);
            if(locaion_info.isGPSEnabled()) {
                handl.postDelayed(WeatherRequestFragment.this.runn, 1000);
            }else {
                locaion_info.showSettingsAlert();
            }


        }


        btn_weather.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(locaion_info.isInternetWorking()) {
                    new MyAsycTask(getActivity(), resp_1).execute(ServicesBean.Http_URL + locaion_info.getLatitude() + "," + locaion_info.getLongitude());
                }else
                    Toast.makeText(getActivity(),"No internet connection",Toast.LENGTH_LONG).show();

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locaion_info);
            if (locaion_info.isGPSEnabled()) {
                handl.postDelayed(WeatherRequestFragment.this.runn, 1000);
            } else {
                locaion_info.showSettingsAlert();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity act = (Activity) context;
        obj_sendback = (RespondBack) act;


    }

    NetworkCallResponse resp_1 = new NetworkCallResponse() {
        @Override
        public void ResponseInfo(String resp) {


            if (resp != null) {
                try {
                    Weather_report wr = new Weather_report();
                    JSONObject js = new JSONObject(resp);
                    wr.setLat_val(js.getDouble("latitude"));
                    wr.setLong_val(js.getDouble("longitude"));
                    wr.setCur_summary(js.getJSONObject("currently").getString("summary"));
                    wr.setIcon(js.getJSONObject("currently").getString("icon").replace("-", "_"));

                    long dv = Long.valueOf(js.getJSONObject("currently").getString("time")) * 1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String vv = new SimpleDateFormat("MM dd, yyyy hh:mma").format(df);
                    wr.setTime_zone(vv);

                    list_weather.add(wr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new MyAsycTask(getActivity(), resp_2).execute(ServicesBean.Http_URL + "16.5556876,79.6361748");

            }
        }
    };

    NetworkCallResponse resp_2 = new NetworkCallResponse() {
        @Override
        public void ResponseInfo(String resp) {


            if (resp != null) {
                try {
                    Weather_report wr = new Weather_report();
                    JSONObject js = new JSONObject(resp);
                    wr.setLat_val(js.getDouble("latitude"));
                    wr.setLong_val(js.getDouble("longitude"));
                    wr.setCur_summary(js.getJSONObject("currently").getString("summary"));
                    wr.setIcon(js.getJSONObject("currently").getString("icon").replace("-", "_"));

                    long dv = Long.valueOf(js.getJSONObject("currently").getString("time")) * 1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String vv = new SimpleDateFormat("MM dd, yyyy hh:mma").format(df);
                    wr.setTime_zone(vv);

                    list_weather.add(wr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new MyAsycTask(getActivity(), resp_3).execute(ServicesBean.Http_URL + "39.125212,-94.551136");

            }
        }
    };


    NetworkCallResponse resp_3 = new NetworkCallResponse() {
        @Override
        public void ResponseInfo(String resp) {
            {


                if (resp != null) {
                    try {
                        Weather_report wr = new Weather_report();
                        JSONObject js = new JSONObject(resp);
                        wr.setLat_val(js.getDouble("latitude"));
                        wr.setLong_val(js.getDouble("longitude"));
                        wr.setCur_summary(js.getJSONObject("currently").getString("summary"));
                        wr.setIcon(js.getJSONObject("currently").getString("icon").replace("-", "_"));

                        long dv = Long.valueOf(js.getJSONObject("currently").getString("time")) * 1000;// its need to be in milisecond
                        Date df = new java.util.Date(dv);
                        String vv = new SimpleDateFormat("MM dd, yyyy hh:mma").format(df);
                        wr.setTime_zone(vv);

                        list_weather.add(wr);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    obj_sendback.ResultResponsse(list_weather);
                }
            }

        }
    };


    public Runnable runn = new Runnable() {


        @Override
        public void run() {


            if (locaion_info.getLatitude() == 0.0 && locaion_info.getLongitude() == 0.0) {
                tx.setText(R.string.gps_wait);
                handl.postDelayed(WeatherRequestFragment.this.runn, 5000);
                btn_weather.setVisibility(View.GONE);
            } else {
                btn_weather.setVisibility(View.VISIBLE);
                tx.setText("Your location at:" +"\n"+ locaion_info.getLatitude() + "\n" + locaion_info.getLongitude());

            }


        }
    };


}
