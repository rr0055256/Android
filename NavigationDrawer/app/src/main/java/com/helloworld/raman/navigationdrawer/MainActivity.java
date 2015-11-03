package com.helloworld.raman.navigationdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] cricketer_names;
    private DrawerLayout cDrawerLayout;
    private ListView cricketerList;
    private String[] cricketerImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //retrieve values from the strings xml
        cricketer_names = getResources().getStringArray(R.array.cricketer_names);

        cricketerImages = getResources().getStringArray(R.array.cricketer_images);

        //define DrawerLayout Object
        cDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //object of listview
        cricketerList = (ListView) findViewById(R.id.list_slidermenu);

        CustomDrawer drawer = new CustomDrawer(this);

        cricketerList.setAdapter(drawer);

        cricketerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    class CustomDrawer extends ArrayAdapter<String>{

        public CustomDrawer(Context context) {
            super(context, 0);
        }

        @Override
        public int getPosition(String item) {
            return super.getPosition(item);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView ==null) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                convertView = inflater.inflate(R.layout.drawer_list_item, null, true);
            }
            ImageView cricketerImage = (ImageView) convertView.findViewById(R.id.cricketerImage);

            TextView cricTextView = (TextView) convertView.findViewById(R.id.cricketerName);

            cricTextView.setText(cricketer_names[position]);

            cricketerImage.setImageResource(getPosition(cricketerImages[position]));

            return convertView;
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
