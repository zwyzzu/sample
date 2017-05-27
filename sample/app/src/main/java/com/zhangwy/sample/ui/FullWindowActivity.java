package com.zhangwy.sample.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;
import com.zhangwy.util.Screen;

import java.lang.ref.WeakReference;

/**
 * Author: zhangwy(张维亚)
 * Email:  zhangweiya@yixia.com
 * 创建时间:2017/5/4 下午4:20
 * 修改时间:2017/5/4 下午4:20
 * Description:
 */

public class FullWindowActivity extends BaseActivity implements View.OnClickListener {
    private final int MESSAGE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_window);
        findViewById(R.id.full_window_dialog).setOnClickListener(this);
        findViewById(R.id.full_window_popup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_window_dialog:
                this.createDialog(v);
                break;
            case R.id.full_window_popup:
                this.createPopup(v);
                break;
        }
    }

    private void createDialog(View v) {
        final Dialog dialog = new Dialog(this, R.style.fullscreen_dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.widget_full_window, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Screen.getScreenWidth(this), Screen.getScreenHeight(this));
        dialog.setContentView(view, params);
        dialog.show();

        loadView(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void createPopup(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.widget_full_window, null);
        final PopupWindow popupWindow = new PopupWindow(view, Screen.getScreenWidth(this), Screen.getScreenHeight(this), true);
        loadView(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        if (v == null) {
            return;
        }

        popupWindow.showAtLocation(v.getRootView(), Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void loadView(View view, View.OnClickListener closeListener) {
        view.findViewById(R.id.topbar_left).setOnClickListener(closeListener);
        view.findViewById(R.id.topbar_right).setVisibility(View.GONE);
        WebView webView = (WebView) view.findViewById(R.id.full_window_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDomStorageEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(false);
        // 不保存表单数据
        settings.setSaveFormData(false);
        // 不保存密码
        settings.setSavePassword(false);
        // 支持页面放大功能
        settings.setSupportZoom(true);
        // 设置webview自适应屏幕大小
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setInitialScale(100);
        // mWebView.addJavascriptInterface(new JSObject(), "FS");
        // 设置监听
        // 设置WebViewClient对象
        webView.setWebViewClient(new FullWebViewClient());
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(new FullWebChromeClient(this, new FullHandler((TextView) view.findViewById(R.id.topbar_title))));
        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Logger.d(TAG, "url=" + url);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });
        // 显示页面
        ((WebView) view.findViewById(R.id.full_window_webview)).loadUrl("http://www.youku.com/");
    }

    private class FullWebViewClient extends WebViewClient {
        /**
         * 点击请求的是链接是才会调用;
         * 重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

    }

    private class FullHandler extends Handler {
        private WeakReference<TextView> reference;

        private FullHandler(TextView activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE:
                    TextView textView = reference.get();
                    if (textView != null) {
                        if (null != msg.getData()) {
                            textView.setText(msg.getData().getString("title"));
                        }
                    }
                    break;
            }

        }
    }

    private class FullWebChromeClient extends WebChromeClient {
        private Context mContext;
        private Handler mHandler;

        private FullWebChromeClient(Context mContext, Handler mHandler) {
            this.mContext = mContext;
            this.mHandler = mHandler;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            Logger.d(TAG, "onJsAlert");
            Dialog dialog = new AlertDialog.Builder(mContext)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            }).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);

            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Logger.d(TAG, "onJsConfirm");
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Logger.d(TAG, "onJsPrompt");
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (null != mHandler && !TextUtils.isEmpty(title)) {
                Message msg = new Message();
                msg.what = MESSAGE_STATE;
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
            super.onReceivedTitle(view, title);
        }
    }
}
