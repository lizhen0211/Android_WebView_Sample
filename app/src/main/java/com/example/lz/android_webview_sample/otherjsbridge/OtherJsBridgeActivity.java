package com.example.lz.android_webview_sample.otherjsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.lz.android_webview_sample.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;

public class OtherJsBridgeActivity extends Activity {

    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_js_bridge);
        webView = (BridgeWebView) findViewById(R.id.webView);
        webView.loadUrl("http://192.168.6.49:8081/otherJsBridge");
    }

    public void sendHello(View view){

    }

    public void callHandler(){

    }
}
