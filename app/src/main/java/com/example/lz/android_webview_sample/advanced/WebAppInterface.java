package com.example.lz.android_webview_sample.advanced;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by lz on 2016/12/13.
 */
public class WebAppInterface {
    Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context c) {
        mContext = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
