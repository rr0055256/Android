package com.helloworld.raman.navigationdrawer;

import android.app.Fragment;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] cricketer_names;
    private DrawerLayout cDrawerLayout;
    private ListView cricketerList;
    private Integer[] cricketerImages = {R.drawable.shikar,R.drawable.rohit,R.drawable.jinks,R.drawable.virat,R.drawable.ms};

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

        //define DrawerLayout Object
        cDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //object of listview
        cricketerList = (ListView) findViewById(R.id.list_slidermenu);

        CustomDrawer drawer = new CustomDrawer(MainActivity.this,cricketer_names,cricketerImages);

        cricketerList.setAdapter(drawer);

        /*cricketerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectItem(position);
            }


        });*/


    }



}
