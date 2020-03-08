package com.zhangwy.sample.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;
import com.zhangwy.util.Ping;
import com.zhangwy.util.VSPermission;

import java.util.HashMap;

public class PingActivity extends BaseActivity implements View.OnClickListener {

    private final int requestCode = 100;
    private final String path = "www.baidu.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_ping);
        this.findViewById(R.id.pingButton).setOnClickListener(this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Manifest.permission.READ_PHONE_STATE, "读手机信息");
        hashMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写SD卡");
        hashMap.put(Manifest.permission.ACCESS_WIFI_STATE, "访问WiFi状态");
        hashMap.put(Manifest.permission.ACCESS_NETWORK_STATE, "访问网络状态");
        hashMap.put(Manifest.permission.CHANGE_NETWORK_STATE, "改变网络状态");
        hashMap.put(Manifest.permission.CHANGE_WIFI_STATE, "改变WiFi状态");
        VSPermission.applyPermission(this, requestCode, hashMap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pingButton:
                EditText editText = (EditText) this.findViewById(R.id.pingInput);
                String cPath = editText.getText().toString();
                if (TextUtils.isEmpty(cPath)) {
                    cPath = path;
                }
                try {
                    if (cPath.startsWith("http")) {
                        Uri url = Uri.parse(cPath);
                        cPath = url.getHost();
                    }
                } catch (Exception e) {
                    Logger.d("getHost", e);
                }
                if (!TextUtils.isEmpty(cPath)) {
                    new MyAsyncTask().execute(cPath);
                } else {
                    Toast.makeText(this, "请输入合法地址", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void refreshText(String string) {
        ((TextView) this.findViewById(R.id.pingText)).setText(string);
    }

    @SuppressLint("StaticFieldLeak")
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            refreshText("ping...");
        }

        @Override
        protected String doInBackground(String... paths) {
            if (paths == null || paths.length <= 0) {
                return null;
            }
            return Ping.ping(paths[0], 4, 3000);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            refreshText(s);
        }
    };
}
