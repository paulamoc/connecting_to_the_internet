package com.pau.connecting_to_the_internet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricManager;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DownloadWebsiteInBackground extends AsyncTask <String, Void, String> {
    Context ctx;
    AlertDialog.Builder dialog;

    public DownloadWebsiteInBackground(Context ct) {
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String myWebsiteURL = strings[0];
        InputStream inputStream;

        try {
            URL myUrl = new URL(myWebsiteURL);
            HttpsURLConnection connection = (HttpsURLConnection) myUrl.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line + "/n");
            }

            inputStream.close();
            reader.close();

            return builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
       dialog = new AlertDialog.Builder(ctx);
       dialog.setTitle("congratulations");
       dialog.setMessage("Website Successfully Downloaded!");
       dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {


           }
       });
      dialog.create().show();
      //set EditText Text
        MainActivity.result.setText(s);
    }

}
