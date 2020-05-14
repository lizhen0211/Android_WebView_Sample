package com.example.lz.android_webview_sample.advanced;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lz.android_webview_sample.R;

import java.util.Set;

public class JSCallAndroidByJsDialogActivity extends Activity {

    private WebView webView;

    private LinearLayout root;

    private static final String TAG = JSCallAndroidByJsDialogActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_android3);
        webView = (WebView) findViewById(R.id.webview_interact);
        root = (LinearLayout) findViewById(R.id.activity_android_js_interact3);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/js_call_android_by_jsdialog.html");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //根据scheme（协议格式） & authority（协议名）判断是否是需要拦截的url
                //假定传入进来的 url = "myscheme://myauthority?arg1=111&arg2=222"
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Uri uri = Uri.parse(message);
                    if (uri.getScheme().equals("myscheme")) {
                        if (uri.getAuthority().equals("myauthority")) {
                            // 可以在协议上带有参数并传递到Android上
                            Set<String> collection = uri.getQueryParameterNames();
                            Log.e(TAG, "js调用了Android的方法");
                            for (String param : collection) {
                                Log.e(TAG, "传入参数:" + param);
                            }
                        }
                        //参数result:代表消息框的返回值(输入值)
                        result.confirm("js调用了Android的方法成功啦");
                        return true;
                    }
                } else {
                    Toast.makeText(JSCallAndroidByJsDialogActivity.this, "android version not support", Toast.LENGTH_SHORT);
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
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
