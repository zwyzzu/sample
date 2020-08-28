package com.zhangwy.sample.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.Device;
import com.zhangwy.util.Logger;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: zhangwy(张维亚)
 * 创建时间：2017/4/19 下午3:29
 * 修改时间：2017/4/19 下午3:29
 * Description: 设备信息
 */

public class SystemMsgActivity extends BaseActivity {

    private final int LEVEL_ROOT = 0;
    private final int LEVEL_ITEM = 1;

    private class SystemMsgItem {
        int level;
        String name;
        String value;

        SystemMsgItem(int level, String name, String value) {
            this.level = level;
            this.name = name;
            this.value = value;
        }
    }

    private WRecyclerView<SystemMsgItem> recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_msg);
        recyclerView = (WRecyclerView<SystemMsgItem>) findViewById(R.id.systemMsg_recycler);
        recyclerView.loadData(new ArrayList<SystemMsgItem>(), new SystemMsgAdapter());
        recyclerView.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        this.loadMsg();
    }

    private class SystemMsgAdapter extends RecyclerAdapter.OnItemLoading<SystemMsgItem> {

        @Override
        public int getItemViewType(SystemMsgItem entity, int position) {
            return entity.level;
        }

        @Override
        public View onCreateView(ViewGroup parent, int viewType) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_msg, parent, false);
        }

        @Override
        public void onLoadView(View root, int viewType, SystemMsgItem entity, int position) {
            boolean isRoot = viewType == LEVEL_ROOT;
            root.findViewById(R.id.systemMsg_item_perch).setVisibility(isRoot ? View.GONE : View.VISIBLE);
            ((TextView) root.findViewById(R.id.systemMsg_item_name)).setText(entity.name);
            ((TextView) root.findViewById(R.id.systemMsg_item_value)).setText(isRoot ? "" : getString(R.string.colon_connect, "", entity.value));
        }
    }

    private void loadMsg() {
        this.loadBuilds();
        this.loadDevice();
        this.loadPermission();
    }

    public SystemMsgItem newRootInstance(String name) {
        return newInstance(LEVEL_ROOT, name, "");
    }

    public SystemMsgItem newItemInstance(String name, Object value) {
        return newInstance(LEVEL_ITEM, name, value);
    }

    public SystemMsgItem newInstance(int level, String name, Object value) {
        return new SystemMsgItem(level, name, String.valueOf(value));
    }

    private void loadBuilds() {
        ArrayList<SystemMsgItem> builds = new ArrayList<>();
        builds.add(newRootInstance(getString(R.string.desc_system_msg_build)));
        builds.add(newItemInstance("TAGS:", Build.TAGS));
        builds.add(newItemInstance("BOARD:", Build.BOARD));
        builds.add(newItemInstance("BOOTLOADER:", Build.BOOTLOADER));
        builds.add(newItemInstance("DEVICE", Build.DEVICE));
        builds.add(newItemInstance("DISPLAY", Build.DISPLAY));
        builds.add(newItemInstance("FINGERPRINT", Build.FINGERPRINT));
        builds.add(newItemInstance("HARDWARE", Build.HARDWARE));
        builds.add(newItemInstance("HOST", Build.HOST));
        builds.add(newItemInstance("getRadioVersion", Build.getRadioVersion()));
        builds.add(newItemInstance("ID", Build.ID));
        builds.add(newItemInstance("MANUFACTURER", Build.MANUFACTURER));
        builds.add(newItemInstance("MODEL", Build.MODEL));
        builds.add(newItemInstance("BRAND", Build.BRAND));
        builds.add(newItemInstance("PRODUCT", Build.PRODUCT));
//        builds.add(newItemInstance("SERIAL", Build.SERIAL));
        builds.add(newItemInstance("TIME", Build.TIME));
        builds.add(newItemInstance("TYPE", Build.TYPE));
        builds.add(newItemInstance("UNKNOWN", Build.UNKNOWN));
        builds.add(newItemInstance("USER", Build.USER));

        builds.add(newItemInstance("VERSION.CODENAME", Build.VERSION.CODENAME));
        builds.add(newItemInstance("VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL));
        builds.add(newItemInstance("VERSION.RELEASE", Build.VERSION.RELEASE));
        builds.add(newItemInstance("VERSION.SDK_INT", Build.VERSION.SDK_INT));
        builds.add(newItemInstance("VERSION.SDK", Build.VERSION.SDK));

        builds.add(newItemInstance("VERSION_CODES.BASE", Build.VERSION_CODES.BASE));
        builds.add(newItemInstance("VERSION_CODES.BASE_1_1", Build.VERSION_CODES.BASE_1_1));
        builds.add(newItemInstance("VERSION_CODES.CUPCAKE", Build.VERSION_CODES.CUPCAKE));
        builds.add(newItemInstance("VERSION_CODES.CUR_DEVELOPMENT", Build.VERSION_CODES.CUR_DEVELOPMENT));
        builds.add(newItemInstance("VERSION_CODES.DONUT", Build.VERSION_CODES.DONUT));
        builds.add(newItemInstance("VERSION_CODES.ECLAIR", Build.VERSION_CODES.ECLAIR));
        builds.add(newItemInstance("VERSION_CODES.ECLAIR_0_1", Build.VERSION_CODES.ECLAIR_0_1));
        builds.add(newItemInstance("VERSION_CODES.ECLAIR_MR1", Build.VERSION_CODES.ECLAIR_MR1));
        builds.add(newItemInstance("VERSION_CODES.FROYO", Build.VERSION_CODES.FROYO));
        builds.add(newItemInstance("VERSION_CODES.GINGERBREAD", Build.VERSION_CODES.GINGERBREAD));
        builds.add(newItemInstance("VERSION_CODES.GINGERBREAD_MR1", Build.VERSION_CODES.GINGERBREAD_MR1));
        builds.add(newItemInstance("VERSION_CODES.HONEYCOMB", Build.VERSION_CODES.HONEYCOMB));
        builds.add(newItemInstance("VERSION_CODES.HONEYCOMB_MR1", Build.VERSION_CODES.HONEYCOMB_MR1));
        builds.add(newItemInstance("VERSION_CODES.HONEYCOMB_MR2", Build.VERSION_CODES.HONEYCOMB_MR2));
        builds.add(newItemInstance("VERSION_CODES.ICE_CREAM_SANDWICH", Build.VERSION_CODES.ICE_CREAM_SANDWICH));
        builds.add(newItemInstance("VERSION_CODES.ICE_CREAM_SANDWICH_MR1", Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1));
        builds.add(newItemInstance("VERSION_CODES.JELLY_BEAN", Build.VERSION_CODES.JELLY_BEAN));
        builds.add(newItemInstance("VERSION_CODES.JELLY_BEAN_MR1", Build.VERSION_CODES.JELLY_BEAN_MR1));
        builds.add(newItemInstance("VERSION_CODES.JELLY_BEAN_MR2", Build.VERSION_CODES.JELLY_BEAN_MR2));
        builds.add(newItemInstance("VERSION_CODES.KITKAT", Build.VERSION_CODES.KITKAT));
        builds.add(newItemInstance("VERSION_CODES.KITKAT_WATCH", Build.VERSION_CODES.KITKAT_WATCH));
        builds.add(newItemInstance("VERSION_CODES.LOLLIPOP", Build.VERSION_CODES.LOLLIPOP));
        builds.add(newItemInstance("VERSION_CODES.LOLLIPOP_MR1", Build.VERSION_CODES.LOLLIPOP_MR1));
        builds.add(newItemInstance("VERSION_CODES.M", Build.VERSION_CODES.M));
        builds.add(newItemInstance("VERSION_CODES.N", Build.VERSION_CODES.N));
        builds.add(newItemInstance("VERSION_CODES.N_MR1", Build.VERSION_CODES.N_MR1));

        builds.add(newItemInstance("RADIO", Build.RADIO));
        builds.add(newItemInstance("CPU_ABI", Build.CPU_ABI));
        builds.add(newItemInstance("CPU_ABI2", Build.CPU_ABI2));

        builds.add(newItemInstance("ANDROID_ID", Device.Dev.getAndroidId(this)));
        recyclerView.addAll(builds);
    }

    private void loadDevice() {
        ArrayList<SystemMsgItem> devices = new ArrayList<>();
        devices.add(newRootInstance(getString(R.string.desc_system_msg_device)));
        try {
            TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            devices.add(newItemInstance("DeviceId", manager.getDeviceId()));
        } catch (Exception e) {
            Logger.d("loadDevice", e);
        }
        devices.add(newItemInstance("IMEI", Device.Dev.getDeviceID(this)));
        devices.add(newItemInstance("IMSI", Device.Dev.getOperators(this).getName()));
        devices.add(newItemInstance("SSNumber", Device.Dev.getSimSerialNumber(this)));
        for (int i = 0; i < 50; i++) {
            Device.Dev.getOperators(this);
            Device.Dev.getDeviceID(this);
        }
        recyclerView.addAll(devices);
    }

    private void loadPermission() {
        ArrayList<SystemMsgItem> permission = new ArrayList<>();
        permission.add(newRootInstance(getString(R.string.desc_system_msg_permission)));
        PackageManager pkgManager = this.getPackageManager();
        try {
            String[] array = pkgManager.getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
            for (String permissionString: array) {
                permission.add(hashPermission(this, permissionString));
            }
        } catch (PackageManager.NameNotFoundException e) {
            //
        }
        permission.add(hashPermission(this, Manifest.permission.READ_SMS));
        permission.add(hashPermission(this, Manifest.permission.RECORD_AUDIO));
        recyclerView.addAll(permission);
    }

    private SystemMsgItem hashPermission(Context context, String permission) {
        PackageManager pkgManager = context.getPackageManager();
        return newItemInstance(permission.replace("android.permission.", ""), pkgManager.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED);
    }
}
