package com.example.lz.android_webview_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lz.android_webview_sample.advanced.AndroidCallJsActivity;
import com.example.lz.android_webview_sample.advanced.JSCallAndroidByInjectActivity;
import com.example.lz.android_webview_sample.advanced.JSCallAndroidByJsDialogActivity;
import com.example.lz.android_webview_sample.advanced.JSCallAndroidByshouldOverrideUrlLoadingActivity;
import com.example.lz.android_webview_sample.simplejsbridge.SimpleJsBridgeActivity;

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

    public void onJSCallAndroidByInject(View view) {
        Intent intent = new Intent(this, JSCallAndroidByInjectActivity.class);
        startActivity(intent);
    }

    public void onJSCallAndroidByUrlLoading(View view) {
        Intent intent = new Intent(this, JSCallAndroidByshouldOverrideUrlLoadingActivity.class);
        startActivity(intent);
    }

    public void onJSCallAndroidByJsDialog(View view) {
        Intent intent = new Intent(this, JSCallAndroidByJsDialogActivity.class);
        startActivity(intent);
    }

    public void onAndroidCallJsClick(View view) {
        Intent intent = new Intent(this, AndroidCallJsActivity.class);
        startActivity(intent);
    }

    public void onSimpleJsBridgeClick(View view){
        Intent intent = new Intent(this, SimpleJsBridgeActivity.class);
        startActivity(intent);
    }

}
