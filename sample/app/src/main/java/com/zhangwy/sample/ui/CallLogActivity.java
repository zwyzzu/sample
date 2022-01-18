package com.zhangwy.sample.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.sample.entity.CallLogEntity;
import com.zhangwy.util.TimeUtil;
import com.zhangwy.util.Util;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class CallLogActivity extends BaseActivity {

    private WRecyclerView<CallLogEntity> recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        this.recyclerView = this.findViewById(R.id.callLogList);
        this.recyclerView.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        this.recyclerView.loadData(null, new RecyclerAdapter.OnItemLoading<CallLogEntity>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return Util.inflate(CallLogActivity.this, R.layout.item_call_log, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, CallLogEntity entity, int position) {
                TextView name = root.findViewById(R.id.itemCallLogName);
                TextView date = root.findViewById(R.id.itemCallLogDate);
                TextView number = root.findViewById(R.id.itemCallLogNumber);
                TextView duration = root.findViewById(R.id.itemCallLogDuration);
                name.setText(entity.getName());
                date.setText(TimeUtil.dateMilliSecond2String(entity.getDateLong(), TimeUtil.PATTERN_DATE));
                number.setText(entity.getNumber());
                duration.setText(TimeUtil.dateMilliSecond2String(entity.getDateLong(), TimeUtil.PATTERN_TIME));
            }
        });
    }

    private void loadData() {
        List<CallLogEntity> callLogs = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, //查询通话记录的URL
                new String[]{
                        CallLog.Calls.CACHED_NAME, //通话记录的联系人
                        CallLog.Calls.NUMBER, //通话记录的电话号码
                        CallLog.Calls.DATE, //通话记录的日期
                        CallLog.Calls.DURATION, //通话时长
                        CallLog.Calls.TYPE//通话记录类型
                },
                null, null, CallLog.Calls.DEFAULT_SORT_ORDER);//按照时间逆顺排列，最近打的最先列出
        if (cursor != null) {
            while (cursor.moveToNext()) {
                callLogs.add(CallLogEntity
                        .newBuilder()
                        .setName(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)))
                        .setNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)))
                        .setDateLong(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)))
                        .setDuration(cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION)))
                        .setType(cursor.getColumnIndex(CallLog.Calls.TYPE)).build());
            }
        }
        this.recyclerView.reload(callLogs);
    }
}
