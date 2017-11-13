package com.example.lz.android_webview_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBasicWebviewBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, BasicWebviewActivity.class);
        startActivity(intent);
    }

    public void onWebviewSettingBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, WebSettingActivity.class);
        startActivity(intent);
    }

    public void onWebviewClientBtnClick(View view) {
        Intent intent = new Intent(this, WebViewClientActivity.class);
        startActivity(intent);
    }

    public void onWebChromeClientBtnClick(View view) {
        Intent intent = new Intent(this, WebChromeClientActivity.class);
        startActivity(intent);
    }
}
