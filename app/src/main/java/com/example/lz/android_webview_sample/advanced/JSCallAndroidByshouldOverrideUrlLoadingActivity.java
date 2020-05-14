package com.example.lz.android_webview_sample.advanced;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lz.android_webview_sample.R;

import java.util.HashMap;
import java.util.Set;

public class JSCallAndroidByshouldOverrideUrlLoadingActivity extends Activity {

    private WebView webView;

    private LinearLayout root;
    private static final String TAG = JSCallAndroidByshouldOverrideUrlLoadingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_call_android2);
        webView = (WebView) findViewById(R.id.webview_interact);
        root = (LinearLayout) findViewById(R.id.activity_android_js_interact);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //根据scheme（协议格式） & authority（协议名）判断是否是需要拦截的url
                //假定传入进来的 url = "myscheme://myauthority?arg1=111&arg2=222"
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Uri uri = request.getUrl();
                    if (uri.getScheme().equals("myscheme")) {
                        if (uri.getAuthority().equals("myauthority")) {
                            // 可以在协议上带有参数并传递到Android上
                            HashMap<String, String> params = new HashMap<>();
                            Set<String> collection = uri.getQueryParameterNames();
                            Log.e(TAG, "js调用了Android的方法");
                            for (String param : collection) {
                                Log.e(TAG, "传入参数:" + param);
                            }
                        }
                        return true;
                    }
                } else {
                    Toast.makeText(JSCallAndroidByshouldOverrideUrlLoadingActivity.this, "android version not support", Toast.LENGTH_SHORT);
                    return super.shouldOverrideUrlLoading(view, request);
                }

                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.loadUrl("file:///android_asset/js_call_android_by_loadurl.html");
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
