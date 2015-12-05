package com.helloworld.raman.jsonasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by raman on 5/12/15.
 */
public class DoInBackground extends AsyncTask<Void,Void,Void> {
    MainActivity activity;
    JSONObject jsonObject = null;
    TextView textView;
    ProgressDialog dialog;


    public DoInBackground(MainActivity activity,TextView textView) {
        this.activity = activity;
        this.textView = textView;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(activity);
        dialog.setTitle("Please Wait!!");
        dialog.setMessage("Data is getting loaded");
        dialog.show();

    }

    @Override
    protected Void doInBackground(Void... params) {

        String url = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";
        StringBuilder builder = new StringBuilder();
//        JSONObject jsonObject = null;

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            int b;

            while ((b = inputStreamReader.read()) != -1) {
                builder.append((char) b);
            }
            jsonObject = new JSONObject(builder.toString());
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        dialog.dismiss();

        String data = "";
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("Users");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String firstname = jsonObject.getString("firstname");
                String lastName = jsonObject.getString("lastname");
                String userName = jsonObject.getString("username");

                data += "Node : " + i + " \n First Name : " + firstname + " \n Last Name : " + lastName + " \n User Name : " + userName + "\n";

            }
            textView.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        return null;
    }
}
