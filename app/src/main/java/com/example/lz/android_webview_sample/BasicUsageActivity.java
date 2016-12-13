package com.example.lz.android_webview_sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BasicUsageActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_usage);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    /**
     * By default, a WebView provides no browser-like widgets,
     * does not enable JavaScript and web page errors are ignored.
     * If your goal is only to display some HTML as a part of your UI,
     * this is probably fine; the user won't need to interact with the web page beyond reading it,
     * and the web page won't need to interact with the user. If you actually want a full-blown web browser,
     * then you probably want to invoke the Browser application with a URL Intent rather than show it with a WebView.
     */
    public void showItWithBrowser(View view) {
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void loadHtmlWithWebview(View view) {
        // Simplest usage: note that an exception will NOT be thrown
        // if there is an error loading this page (see below).
        webView.loadUrl("http://slashdot.org/");

        // OR, you can also load from an HTML string:

        //String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        //webView.loadData(summary, "text/html", null);

        // ... although note that there are restrictions on what this HTML can do.
        // See the JavaDocs for loadData() and loadDataWithBaseURL() for more info.
    }

    public void JSCallAndroid(View view) {
        webView.loadData("<input type=\"button\" value=\"Say hello\" onClick=\"showAndroidToast('Hello Android!')\" />\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    function showAndroidToast(toast) {\n" +
                "        Android.showToast(toast);\n" +
                "    }\n" +
                "</script>", "text/html", null);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }
}
