package com.example.lz.android_webview_sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewClientActivity extends AppCompatActivity {

    private WebView webView;

    private LinearLayout root;

    private String url = "https://m.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_client);
        webView = (WebView) findViewById(R.id.webview_client);
        root = (LinearLayout) findViewById(R.id.activity_webview_client);
        configSetting();
        setWebClientClient();
        webView.loadUrl(url);
    }

    public void onLoadDomain(View view) {
        webView.loadUrl(url);
    }

    public void onLoadAssets(View view) {
        webView.loadUrl("file:///android_asset/load.html");
    }

    public void onLoadSdcard(View view) {
        //需要判断SD卡所在目录是否存在
        webView.loadUrl("file:///" + Environment.getExternalStorageDirectory().getPath() + "/load.html");
    }


    private void configSetting() {
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
    }

    private void setWebClientClient() {
        webView.setWebViewClient(new WebViewClient() {
            /**
             *
             * 浏览器如下行为：前进后退（isBackForward ），刷新（isReload），Post请求（navigationParams.isPost）
             * 都不会触发shouldOverrideUrlLoading．
             * 如果都不是以上行为，还要满足isRedirect或!isLoadUrl 才能触发shouldOverrideUrlLoading．
             * isRedirect就是重定向的url,即重定向url也会触发shouldOverrideUrlLoading；
             * 凡是webview.loadUrl页面的，isLoadUrl都是true(原因是webview.loadUrl最终会调到loadUrl(LoadUrlParams params)，
             * 进而params.setTransitionType(params.getTransitionType() | PageTransition.FROM_API))．

             * return true
             * means the host application handles the url, while return false means the
             * current WebView handles the url.
             *
             * This method is not called for requests using the POST "method"
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Uri.parse(url).getHost().contains("baidu.com")) {
                    // This is my web site, so do not override; let my WebView load the page
                    return false;
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("WebViewClientActivity", "onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("WebViewClientActivity", "onPageFinished");
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //用javascript隐藏系统定义的错误提示页面
                String data = "Network Error!";
                view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");

                //super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        root.removeView(webView);
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != webView && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
