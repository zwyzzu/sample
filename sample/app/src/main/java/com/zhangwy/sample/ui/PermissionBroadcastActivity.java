package com.zhangwy.sample.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.TimeUtil;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.WRecyclerView;

/**
 * Created by zhangwy on 2018/1/16 下午2:29.
 * Updated by zhangwy on 2018/1/16 下午2:29.
 * Description
 */

public class PermissionBroadcastActivity extends BaseActivity implements View.OnClickListener{
    public final static String PERMISSION_RECEIVE_LOG = "com.android.permission.RECEIVE_LOG";
    public final static String RECEIVE_LOG = "com.bestfudaye.PRIVATE.RECEIVE.LOG";
    public final static String EXTRA_ACTION = "com.bestfudaye.PRIVATE.EXTRA.ACTION";
    public final static String EXTRA_RESULT = "com.bestfudaye.PRIVATE.EXTRA.RESULT";
    public final static String EXTRA_DATA = "com.bestfudaye.PRIVATE.EXTRA.DATA.CONTENT";
    public final static String EXTRA_TIME = "com.bestfudaye.PRIVATE.EXTRA.DATA.TIME";
    private WRecyclerView<AutoMsgItem> recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_broadcast);
        findViewById(R.id.permission_send).setOnClickListener(this);
        this.recyclerView = (WRecyclerView<AutoMsgItem>) findViewById(R.id.permission_recycle);
        this.recyclerView.setVisibility(View.VISIBLE);
        this.recyclerView.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        this.recyclerView.loadData(null, new RecyclerAdapter.OnItemLoading<AutoMsgItem>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(PermissionBroadcastActivity.this).inflate(R.layout.item_auto_test, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, AutoMsgItem entity, int position) {
                TextView first = (TextView) root.findViewById(R.id.auto_test_first);
                TextView second = (TextView) root.findViewById(R.id.auto_test_second);
                TextView third = (TextView) root.findViewById(R.id.auto_test_third);
                first.setText(getString(R.string.permission_msg, entity.action, entity.result, (entity.useTime / (float) 1000.0)));
                second.setText(TimeUtil.dateMilliSecond2String(entity.nowTime, TimeUtil.PATTERN_DATE_MS));
                third.setText(entity.content);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVE_LOG);
        this.registerReceiver(RunTimeLogReceiver, intentFilter);
    }

    private final BroadcastReceiver RunTimeLogReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), RECEIVE_LOG)) {
                String action = intent.getStringExtra(EXTRA_ACTION);
                String result = intent.getStringExtra(EXTRA_RESULT);
                String content = intent.getStringExtra(EXTRA_DATA);
                long useTime = intent.getLongExtra(EXTRA_TIME, 0);
                if (!TextUtils.isEmpty(action) && !TextUtils.isEmpty(result) && !TextUtils.isEmpty(content)) {
                    recyclerView.add(new AutoMsgItem(action, result, content, useTime));
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.permission_send) {
            Intent intent = new Intent(RECEIVE_LOG);
            intent.putExtra(EXTRA_ACTION, "action");
            intent.putExtra(EXTRA_RESULT, "result");
            intent.putExtra(EXTRA_DATA, "content");
            intent.putExtra(EXTRA_TIME, 45);
            this.sendBroadcast(intent, PERMISSION_RECEIVE_LOG);
        }
    }

    private class AutoMsgItem {
        private String action;
        private String result;
        private String content;
        private long useTime;
        private long nowTime;
        AutoMsgItem(String action, String result, String content, long useTime) {
            this.action = action;
            this.result = result;
            this.content = content;
            this.useTime = useTime;
            this.nowTime = System.currentTimeMillis();
        }
    }

}
