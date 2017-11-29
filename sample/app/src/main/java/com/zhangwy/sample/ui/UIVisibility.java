package com.zhangwy.sample.ui;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwy on 2017/9/6 下午6:46.
 * Updated by zhangwy on 2017/9/6 下午6:46.
 * Description
 */

public class UIVisibility extends BaseActivity implements RecyclerAdapter.OnItemClickListener<Pair<Integer, String>>, View.OnClickListener {

    private Button navigationBar;
    private boolean transparent = false;
    private int navigationColor;

    private Button orientation;
    private int uiSystemVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_uivisibility);
        WRecyclerView<Pair<Integer, String>> recyclerView = (WRecyclerView<Pair<Integer, String>>) this.findViewById(R.id.uivisibility_recycler);
        recyclerView.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        recyclerView.setOnItemClickListener(this);
        recyclerView.loadData(getItems(), new RecyclerAdapter.OnItemLoading<Pair<Integer, String>>() {

            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(UIVisibility.this).inflate(R.layout.item_uivisibility, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, Pair<Integer, String> entity, int position) {
                ((TextView) root.findViewById(R.id.uivisibility_text)).setText(entity.second);
            }
        });
        navigationBar = (Button) findViewById(R.id.uivisibility_navigationBar_color);
        navigationBar.setOnClickListener(this);
        orientation = (Button) findViewById(R.id.uivisibility_orientation);
        orientation.setOnClickListener(this);
    }

    private List<Pair<Integer, String>> getItems() {
        ArrayList<Pair<Integer, String>> items = new ArrayList<>();
        items.add(Pair.create(View.SYSTEM_UI_FLAG_FULLSCREEN, "SYSTEM_UI_FLAG_FULLSCREEN"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION, "SYSTEM_UI_FLAG_HIDE_NAVIGATION"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_IMMERSIVE, "SYSTEM_UI_FLAG_IMMERSIVE"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_LAYOUT_STABLE, "SYSTEM_UI_FLAG_LAYOUT_STABLE"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR, "SYSTEM_UI_FLAG_LIGHT_STATUS_BAR"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_LOW_PROFILE, "SYSTEM_UI_FLAG_LOW_PROFILE"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_VISIBLE, "SYSTEM_UI_FLAG_VISIBLE"));
        items.add(Pair.create(View.SYSTEM_UI_LAYOUT_FLAGS, "SYSTEM_UI_LAYOUT_FLAGS"));
        items.add(Pair.create(View.SYSTEM_UI_LAYOUT_FLAGS | View.SYSTEM_UI_FLAG_FULLSCREEN, "LAYOUT_FLAGS | FLAG_FULLSCREEN"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN, "FULLSCREEN"));
        items.add(Pair.create(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_LAYOUT_FLAGS, "LAYOUT_FLAGS | FULLSCREEN | HIDE_NAVIGATION"));
        return items;
    }

    @Override
    public void onItemClick(View view, int viewType, Pair<Integer, String> entity, int position) {
        getWindow().getDecorView().setSystemUiVisibility(entity.first);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uivisibility_navigationBar_color:
                this.hideNavigation(!this.transparent);
                this.navigationBar.setText(this.transparent ? R.string.btn_text_untransparent : R.string.btn_text_transparent);
                break;
            case R.id.uivisibility_orientation:
                if (!isLandscape()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    orientation.setText(R.string.btn_text_toPortrait);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    orientation.setText(R.string.btn_text_toLandscape);
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final boolean toLandscape = isLandscape();
        if (toLandscape) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
        } else {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        this.hideNavigation(toLandscape);
    }

    private boolean isLandscape() {
        return this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void hideNavigation(boolean hide) {
        Window window = getWindow();
        if (window == null)
            return;
        View decorView = window.getDecorView();
        if (decorView != null) {
            if (hide) {
                this.uiSystemVisibility = decorView.getSystemUiVisibility();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_LAYOUT_FLAGS | View.SYSTEM_UI_FLAG_FULLSCREEN);
            } else {
                decorView.setSystemUiVisibility(this.uiSystemVisibility);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0 以上 全透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 虚拟导航键
            if (hide) {
                this.navigationColor = window.getNavigationBarColor();
                window.setNavigationBarColor(0x50000000);
            } else {
                window.setNavigationBarColor(this.navigationColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Android 4.4 以上 半透明 虚拟导航键
            if (hide) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
        this.transparent = hide;
    }
//
//    private void setTransparent() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            // Android 5.0 以上 全透明
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            // 虚拟导航键
//            if (this.transparent) {
//                window.setNavigationBarColor(this.navigationColor);
//            } else {
//                this.navigationColor = window.getNavigationBarColor();
//                window.setNavigationBarColor(0x50000000);
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // Android 4.4 以上 半透明 虚拟导航键
//            if (this.transparent) {
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            } else {
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            }
//        }
//        this.transparent = !this.transparent;
//    }
}
