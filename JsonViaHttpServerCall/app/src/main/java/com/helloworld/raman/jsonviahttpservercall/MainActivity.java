package com.helloworld.raman.jsonviahttpservercall;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use the below lines when the call is from the Main Thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //object of textview
        TextView jsonHttpDataTextView = (TextView) findViewById(R.id.textView);

        JSONObject object = getJsonObject();

        String data = null;
        try {
            JSONArray jsonArray = object.getJSONArray("Users");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String firstname = jsonObject.getString("firstname");
                String lastName = jsonObject.getString("lastname");
                String userName = jsonObject.getString("username");

                data = "Node : "+i+" \n First Name : "+firstname+" \n Last Name : "+lastName+" \n User Name : "+userName+"\n";

            }
            jsonHttpDataTextView.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObject() {
        String url = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";
        StringBuilder builder = new StringBuilder();
        JSONObject jsonObject = null;

        try{
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            int b;

            while((b = inputStreamReader.read()) != -1){
                builder.append((char)b);

                jsonObject = new JSONObject(builder.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
