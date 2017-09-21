package tdbank.com.tdbank.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rana Prathap on 9/19/2017.
 */

public class MyAsycTask extends AsyncTask<String, Void, String> {
    ProgressDialog dialog;
    WeakReference<Context> cont;
    WeakReference<NetworkCallResponse> net_resp;


    public MyAsycTask(Context c, NetworkCallResponse r) {

        cont=new WeakReference<Context>(c);
        net_resp=new WeakReference<NetworkCallResponse>(r);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(cont.get());
        dialog.setMessage("Loading ... Please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL obj = null;

            obj = new URL(strings[0]);

            HttpURLConnection con = null;

            con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            return response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        net_resp.get().ResponseInfo(s);
        dialog.dismiss();
    }
}
