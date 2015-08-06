package com.dvg.offlinewebpage;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
//import android.support.v7.appcompat;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.*;
import android.support.v4.*;

public class MainActivity extends AppCompatActivity 
{
	
	WebView myWebView;
	private String[] git;
	private DrawerLayout gitDrawerLayout;
	private ListView gitList;
	private String[] gitTitles;
	private CharSequence gitTitle;
	private ActionBarDrawerToggle gitDrawerToggle;
	private CharSequence gitDrawerTitle;
	private ActionBar gitActionBar;
	private int selectedPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		git = getResources().getStringArray(R.array.GIT_ARRAY);
		gitDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		gitList = (ListView) findViewById(R.id.left_drawer);
		gitTitles = getResources().getStringArray(R.array.GIT_ARRAY);
		gitTitle = gitDrawerTitle = getTitle();
		myWebView = (WebView) findViewById(R.id.WebView);

		
		WebSettings websettings = myWebView.getSettings();
		websettings.setJavaScriptEnabled(true);
		
		myWebView.loadUrl("file:///android_asset/Git/Git.html");
		
		
		
		//set a custom shadow on the main activity when the navigation drawer opens
		gitDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		//set the adapter for the list view
		gitList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_layout_list,git));
		gitList.setOnItemClickListener(new DrawerItemClickListener());
		
		//Raman's own implementation
		gitActionBar = getSupportActionBar();
		//gitActionBar.hide();
		/* Getting reference to the ActionBarDrawerToggle */
        gitDrawerToggle = new ActionBarDrawerToggle(this, gitDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /* Called when drawer is closed */
            public void onDrawerClosed(View view) {
            	super.onDrawerClosed(view);
                //Put your code here
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
            	super.onDrawerClosed(drawerView);
                //Put your code here
            }
        };
        gitDrawerLayout.setDrawerListener(gitDrawerToggle);
        	
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//gitActionBar.show();
		// enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setHomeButtonEnabled(true);
        
        
       
	}
	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        gitDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        gitDrawerToggle.onConfigurationChanged(newConfig);
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        if (gitDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }


		//set list's click listener
		//gitList.setOnItemClickListener(new DrawerItemClickListener());
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) 
	    {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
		{
            selectItem(position);
            
            /*Replace fragment content*/
      //      updateFragment();
            
            /*Closing the drawer*/
            //gitDrawerLayout.closeDrawer(gitList);
		}
	}
	//selectedPosition = 0;
	//updateFragment();	
	private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new GitFragment();
        Bundle args = new Bundle();
        args.putInt(GitFragment.ARG_GIT_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        gitList.setItemChecked(position, true);
        setTitle(gitTitles[position]);
        gitDrawerLayout.closeDrawer(gitList);
    }
	public static class GitFragment extends Fragment 
	{
		public static final String ARG_GIT_NUMBER = "planet_number";
        public GitFragment() 
        {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
        {
            View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
           int i = getArguments().getInt(ARG_GIT_NUMBER);
           String gitNumber = getResources().getStringArray(R.array.GIT_ARRAY)[i];
            
           	if(gitNumber.equals("Page 1-free and open source"))
           	{
            WebView fragmentWebView = (WebView) rootView.findViewById(R.id.webview);
            WebSettings webSettings = fragmentWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            fragmentWebView.loadUrl("file:///android_asset/Git/Git.html");
           	}
           	else if(gitNumber.equals("Page 2-easy to learn"))
           	{
           		WebView fragmentWebView = (WebView) rootView.findViewById(R.id.webview);
                WebSettings webSettings = fragmentWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                fragmentWebView.loadUrl("file:///android_asset/Easy%20to%20learn/Git%20-%20Documentation.html");
               		
           	}
           	else if(gitNumber.equals("Page 3-staging areas"))
           	{
           		WebView fragmentWebView = (WebView) rootView.findViewById(R.id.webview);
                WebSettings webSettings = fragmentWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                fragmentWebView.loadUrl("file:///android_asset/Staging%20Areas/About%20-%20Git.html");
               		
           	}
           	else if(gitNumber.equals("Page 4-multiple workflows"))
           	{
           		WebView fragmentWebView = (WebView) rootView.findViewById(R.id.webview);
                WebSettings webSettings = fragmentWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                fragmentWebView.loadUrl("file:///android_asset/MultipleWorkFlows/MultipleWorkflows.html");
               		
           	}
           	return rootView;
        }
	}
}	