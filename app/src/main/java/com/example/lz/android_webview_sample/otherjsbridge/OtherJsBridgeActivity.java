package com.example.lz.android_webview_sample.otherjsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_webview_sample.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

public class OtherJsBridgeActivity extends Activity {

    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_js_bridge);
        webView = (BridgeWebView) findViewById(R.id.webView);
        webView.loadUrl("http://192.168.6.49:8080/otherJsBridge");

        //js 调用 Android 默认走Default Handler
        webView.setDefaultHandler(new DefaultHandler());

        /**
         * js 调用 Android，如果注册了Handler，则不走默认的DefaultHandler
         */
        webView.registerHandler("submitFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("chrom-java", "handler = submitFromWeb, data from web = " + data);
                //此处再次调用js 注册的submitFromWeb 方法
                function.onCallBack("submitFromWeb exe, response data 中文 from Java");
            }
        });
    }

    /**
     * native 调用js 无回调
     *
     * @param view
     */
    public void callJsWithoutCallback(View view) {
        webView.send("hello");
    }

    /**
     * native 调用js 有回到
     *
     * @param view
     */
    public void callJsWithcallback(View view) {
        webView.callHandler("functionInJs", getJson(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.e("chrom-java", "webView.callHandler onCallBack  " + data);
            }
        });
    }

    public String getJson() {
        User user = new User();
        Location location = new Location();
        location.address = "shenyang";
        user.location = location;
        user.name = "zhangsan";
        return new Gson().toJson(user);
    }

    static class Location {
        String address;
    }

    static class User {
        String name;
        Location location;
        String testStr;
    }
}
