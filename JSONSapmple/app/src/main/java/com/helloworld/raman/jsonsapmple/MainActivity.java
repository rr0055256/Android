package com.helloworld.raman.jsonsapmple;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView jsonData = (TextView)findViewById(R.id.json);
        String jsonString = "{\"Users\" : [{\"firstname\":\"Mike\",\"lastname\":\"Procter\",\"username\":\"mike143\"},{\"firstname\":\"Graeme\",\"lastname\":\"Smith\",\"username\":\"graeme249\"},{\"firstname\":\"Sachin\",\"lastname\":\"Tendulkar\",\"username\":\"sachin369\"}]}";

        String data = "";
        try{
            JSONObject jsonRootObject = new JSONObject(jsonString);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Users");

            //Iterate over JSON Array and print JSON Objects
            for(int i=0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //get first name
                String firstName = jsonObject.optString("firstname").toString();
                //get last name
                String lastName = jsonObject.optString("lastname").toString();
                //get user name
                String userName = jsonObject.optString("username").toString();

                data += "Node"+i+" : \n First Name = "+firstName+"\n Last Name = "+lastName+" \n User Name = "+userName+"\n";
            }
            jsonData.setText(data);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
