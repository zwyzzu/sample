package com.zhangwy.sample.ui;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.signalStrength.SignalStrengthControl;
import com.zhangwy.util.VSPermission;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.HashMap;

public class NetworkActivity extends BaseActivity implements Handler.Callback {

    private WRecyclerView<SignalStrengthControl.Item> recyclerView;
    private final int requestCode = 100;
    private final int WHAT = 100;
    private Handler handler = new Handler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写SD卡");
        hashMap.put(Manifest.permission.ACCESS_WIFI_STATE, "访问WiFi状态");
        hashMap.put(Manifest.permission.ACCESS_NETWORK_STATE, "访问网络状态");
        hashMap.put(Manifest.permission.CHANGE_NETWORK_STATE, "改变网络状态");
        hashMap.put(Manifest.permission.CHANGE_WIFI_STATE, "改变WiFi状态");
        VSPermission.applyPermission(this, requestCode, hashMap);
        recyclerView = (WRecyclerView<SignalStrengthControl.Item>)this.findViewById(R.id.recyclerView);
        recyclerView.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        recyclerView.loadData(null, new RecyclerAdapter.OnItemLoading<SignalStrengthControl.Item>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_network, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, SignalStrengthControl.Item entity, int position) {
                TextView network = (TextView) root.findViewById(R.id.item_network);
                TextView level = (TextView) root.findViewById(R.id.item_level);
                TextView strength = (TextView) root.findViewById(R.id.item_strength);
                network.setText(entity.network);
                level.setText(String.valueOf(entity.signallevel));
                strength.setText(String.valueOf(entity.signalstrength));
            }
        });
        SignalStrengthControl.get().init(this.getApplicationContext());
        this.handler.sendEmptyMessageDelayed(WHAT, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public boolean handleMessage(@NonNull Message msg) {
        if (msg.what == WHAT) {
            handler.removeMessages(WHAT);
            handler.sendEmptyMessageDelayed(WHAT, 1000);
            SignalStrengthControl.Item item = SignalStrengthControl.get().getCurrentValue();
            recyclerView.add(item);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(WHAT);
    }
}
