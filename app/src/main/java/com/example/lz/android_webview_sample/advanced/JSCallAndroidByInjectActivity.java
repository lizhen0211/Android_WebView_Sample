package com.example.lz.android_webview_sample.advanced;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.lz.android_webview_sample.R;

/**
 * 对于Android调用JS代码的方法有2种：
 * 1. 通过WebView的loadUrl()
 * 2. 通过WebView的evaluateJavascript()
 * <p>
 * 对于JS调用Android代码的方法有3种：
 * 1. 通过WebView的addJavascriptInterface()进行对象映射
 * 2. 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
 * 3. 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt()方法回调拦截JS对话框alert()、confirm()、prompt() 消息
 */
public class JSCallAndroidByInjectActivity extends Activity {

    private WebView webView;

    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_call_android1);
        webView = (WebView) findViewById(R.id.webview_interact);
        root = (LinearLayout) findViewById(R.id.activity_android_js_interact);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    /**
     * 方法一：通过WebView的addJavascriptInterface（）进行对象映射
     *
     * @param view
     */
    public void onJsCallAndroid1(View view) {
        /*webView.loadData("<input type=\"button\" value=\"Say hello\" onClick=\"showAndroidToast('Hello Android!')\" />\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    function showAndroidToast(toast) {\n" +
                "        Android.showToast(toast);\n" +
                "    }\n" +
                "</script>", "text/html", null);*/
        webView.loadUrl("file:///android_asset/js_call_android_by_inject.html");
        //Injects the supplied Java object into this WebView
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
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
