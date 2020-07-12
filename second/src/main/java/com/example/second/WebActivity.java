package com.example.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bean.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class WebActivity extends AppCompatActivity {

    private WebView webView;
    String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        Event event = new Event(link);
        EventBus.getDefault().post(event);

        webView = new WebView(this);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mUrl);
        setContentView(webView);

    }

    @Subscribe
    public void getData(Event event) {
        mUrl=event.getLink();
        Log.d(TAG, "getData: "+event.getLink());
    }

    private static final String TAG = "WebActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
