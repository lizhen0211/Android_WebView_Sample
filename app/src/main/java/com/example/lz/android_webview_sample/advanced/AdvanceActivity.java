package com.example.lz.android_webview_sample.advanced;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lz.android_webview_sample.R;

public class AdvanceActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
        webView = (WebView) findViewById(R.id.webview);
        configSetting();
        webView.loadUrl("file:///android_asset/interaction.html");
        webView.setWebViewClient(new WebViewClient() {});
    }

    private void configSetting() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    public void onAndroidCallJsByEvaluateClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("onAndroidCallJsByEvaluate()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Log.e(AdvanceActivity.class.getSimpleName(), s);
                }
            });
        }
    }

    public void onAndroidCallJsByLoadUrlClick(View view) {
        String a = "abc";
        //如果JS方法有返回值，webview 页面内容会load成返回值内容
        //不加引号会报 Uncaught ReferenceError: abc is not defined
        webView.loadUrl("javascript:onAndroidCallJsByLoadUrl('" + a + "')");

    }
}
