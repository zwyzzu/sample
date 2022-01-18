package com.zhangwy.sample.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zhangwy.sample.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("FieldCanBeLocal")
public class BlankFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ViewGroup home;
    private static WebView webView;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        this.home = view.findViewById(R.id.fragmentBlankHome);
        view.setBackgroundColor(Color.RED);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (webView == null) {
            webView = new WebView(getContext());
            WebSettings webSettings = webView.getSettings();

            webSettings.setJavaScriptEnabled(true);// -> 是否开启JS支持
//            webSettings.setPluginsEnabled(true);// -> 是否开启插件支持
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// -> 是否允许JS打开新窗口

            webSettings.setUseWideViewPort(true);// -> 缩放至屏幕大小
            webSettings.setLoadWithOverviewMode(true);// -> 缩放至屏幕大小
            webSettings.setSupportZoom(true);// -> 是否支持缩放
            webSettings.setBuiltInZoomControls(true);// -> 是否支持缩放变焦，前提是支持缩放
            webSettings.setDisplayZoomControls(false);// -> 是否隐藏缩放控件

            webSettings.setAllowFileAccess(true);// -> 是否允许访问文件
            webSettings.setDomStorageEnabled(true);// -> 是否节点缓存
            webSettings.setDatabaseEnabled(true);// -> 是否数据缓存
            webSettings.setAppCacheEnabled(true);// -> 是否应用缓存
//            webSettings.setAppCachePath(uri);// -> 设置缓存路径

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                webSettings.setMediaPlaybackRequiresUserGesture(false);// -> 是否要手势触发媒体
            }
            webSettings.setStandardFontFamily("sans-serif");// -> 设置字体库格式
            webSettings.setFixedFontFamily("monospace");// -> 设置字体库格式
            webSettings.setSansSerifFontFamily("sans-serif");// -> 设置字体库格式
            webSettings.setSerifFontFamily("sans-serif");// -> 设置字体库格式
            webSettings.setCursiveFontFamily("cursive");// -> 设置字体库格式
            webSettings.setFantasyFontFamily("fantasy");// -> 设置字体库格式
            webSettings.setTextZoom(100);// -> 设置文本缩放的百分比
            webSettings.setMinimumFontSize(8);// -> 设置文本字体的最小值(1~72)
            webSettings.setDefaultFontSize(16);// -> 设置文本字体默认的大小

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);// -> 按规则重新布局
            webSettings.setLoadsImagesAutomatically(false);// -> 是否自动加载图片
            webSettings.setDefaultTextEncodingName("UTF-8");// -> 设置编码格式
            webSettings.setNeedInitialFocus(true);// -> 是否需要获取焦点
            webSettings.setGeolocationEnabled(false);// -> 设置开启定位功能
            webSettings.setBlockNetworkLoads(false);// -> 是否从网络获取资源
            webView.loadUrl("https://www.baidu.com/");
        }
        this.home.addView(webView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onDestroyView() {
        this.home.removeView(webView);
        super.onDestroyView();
    }
}