package com.example.lz.android_webview_sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class BasicWebviewActivity extends Activity {

    private WebView webView;

    private RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_webview);
        root = (RelativeLayout) findViewById(R.id.activity_basic_webview);
        webView = (WebView) findViewById(R.id.basic_webview);
        webView.loadUrl("https://m.baidu.com");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        webView.onResume();
        //恢复pauseTimers状态
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        webView.onPause();

        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
        //它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
        webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁Webview
        //在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
        //但是注意：webview调用destory时,webview仍绑定在Activity上
        //这是由于自定义webview构建时传入了该Activity的context对象
        //因此需要先从父容器中移除webview,然后再销毁webview:
        root.removeView(webView);
        webView.destroy();
    }

    /**
     * 是否可以后退
     * webview.canGoBack()
     * 后退网页
     * webview.goBack()
     * <p>
     * 是否可以前进
     * webview.canGoForward()
     * 前进网页
     * webview.goForward()
     * <p>
     * 以当前的index为起始点前进或者后退到历史记录中指定的steps
     * 如果steps为负数则为后退，正数则为前进
     * webview.goBackOrForward(intsteps)
     *
     * @param keyCode
     * @param event
     * @return
     */
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
