package com.example.dvg;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MainActivity extends Activity {
	
	private WebView myWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myWebView = (WebView) findViewById(R.id.webview);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.loadUrl("http://git-scm.com/");
		myWebView.setWebViewClient(new DVGWebViewClient());
	}
	
	private class DVGWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView webview, String url)
		{
			webview.loadUrl(url);
			return true;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack())
		{
			myWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
