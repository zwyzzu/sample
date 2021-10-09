package com.zhangwy.sample.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.VSPermission;
import com.zhangwy.widget.MultiLineRadioGroup;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.RecyclerDivider;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.zhangwy.sample.ui.PermissionApiActivity.Level.Dangerous;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.Deprecated;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.NonSupport;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.None;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.Normal;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SA;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SAPREI;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SDISTTA;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SI;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SP;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SPA;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SPD;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SPDARTD;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SPRL;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.SSUAIP23D;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.Signature;
import static com.zhangwy.sample.ui.PermissionApiActivity.Level.UnUseThirdApp;

@SuppressWarnings("FieldCanBeLocal")
public class PermissionApiActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.OnItemClickListener<PermissionApiActivity.PermissionApi> {

    private final int SPAN_COUNT = 2;
    private final int REQUEST_CODE = 10001;
    private MultiLineRadioGroup<Level> levelRadio;
    private MultiLineRadioGroup<Group> groupRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_api);
        this.initApi();
        this.initRadio();
        this.initButton();
    }

    private void initApi() {
        final WRecyclerView<PermissionApi> view = findViewById(R.id.permissionApiRecycler);
        view.setGridLayoutManager(this.SPAN_COUNT, WRecyclerView.VERTICAL, true);
        view.setOnItemClickListener(this);
        view.addItemDecoration(this.createDivider(GridLayoutManager.VERTICAL));
        view.addItemDecoration(this.createDivider(GridLayoutManager.HORIZONTAL));
        final List<PermissionApi> items = Arrays.asList(PermissionApi.values());
        view.loadData(items, new HomeAdapter());
    }

    private void initRadio() {
        this.levelRadio = this.findViewById(R.id.permissionLevel);
        this.groupRadio = this.findViewById(R.id.permissionGroup);
        this.levelRadio.setOnCheckBoxTextCallback(new MultiLineRadioGroup.OnCheckBoxTextCallback<Level>() {
            @Override
            public String onCheckBoxTextText(Level entity) {
                return entity.code;
            }
        });
        this.groupRadio.setOnCheckBoxTextCallback(new MultiLineRadioGroup.OnCheckBoxTextCallback<Group>() {
            @Override
            public String onCheckBoxTextText(Group entity) {
                return entity.code;
            }
        });
        this.levelRadio.addAll(Arrays.asList(Level.values()));
        this.groupRadio.addAll(Arrays.asList(Group.values()));
    }

    private void initButton() {
        this.findViewById(R.id.permissionApiReq).setOnClickListener(this);
    }

    private RecyclerView.ItemDecoration createDivider(int orientation) {
        Resources resources = getResources();
        int color = resources.getColor(android.R.color.transparent);
        return RecyclerDivider.create(this, orientation, 32, color);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.permissionApiReq) {
            PermissionApi[] apis = PermissionApi.values();
            HashMap<String, String> permissions = new HashMap<>();
            for (PermissionApi api : apis) {
                permissions.put("android.permission." + api.pmsn, api.desc);
            }
            VSPermission.applyPermission(this, this.REQUEST_CODE, permissions);
        }
    }

    @Override
    public void onItemClick(View view, int viewType, PermissionApi entity, int position) {
    }

    private static class HomeAdapter extends RecyclerAdapter.OnItemLoading<PermissionApi> {
        @Override
        public int getItemViewType(PermissionApi api, int position) {
            return super.getItemViewType(api, position);
        }

        @Override
        public View onCreateView(ViewGroup parent, int viewType) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permission_api, parent, false);
        }

        @Override
        public void onLoadView(View root, int viewType, PermissionApi entity, int position) {
            ((TextView) root.findViewById(R.id.permission_api_title)).setText(entity.name);
            ((TextView) root.findViewById(R.id.permission_api_desc)).setText(entity.desc);
        }
    }

    public enum PermissionApi {
        ACCEPT_HANDOVER("应用程序中启动", "允许一个正在调用的应用程序继续在另一个应用程序中启动的调用", "ACCEPT_HANDOVER", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        LOCATION_BG("后台地理位置", "后台访问地理位置的权限", "ACCESS_BACKGROUND_LOCATION", Group.Location, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_COARSE_LOCATION("ACCESS_COARSE_LOCATION", "ACCESS_COARSE_LOCATION", "ACCESS_COARSE_LOCATION", Group.Location, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_FINE_LOCATION("ACCESS_FINE_LOCATION", "ACCESS_FINE_LOCATION", "ACCESS_FINE_LOCATION", Group.Location, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_MEDIA_LOCATION("ACCESS_MEDIA_LOCATION", "ACCESS_MEDIA_LOCATION", "ACCESS_MEDIA_LOCATION", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACTIVITY_RECOGNITION("ACTIVITY_RECOGNITION", "ACTIVITY_RECOGNITION", "ACTIVITY_RECOGNITION", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ADD_VOICEMAIL("ADD_VOICEMAIL", "ADD_VOICEMAIL", "ADD_VOICEMAIL", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        ANSWER_PHONE_CALLS("ANSWER_PHONE_CALLS", "ANSWER_PHONE_CALLS", "ANSWER_PHONE_CALLS", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH_ADVERTISE("BLUETOOTH_ADVERTISE", "BLUETOOTH_ADVERTISE", "BLUETOOTH_ADVERTISE", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH_CONNECT("BLUETOOTH_CONNECT", "BLUETOOTH_CONNECT", "BLUETOOTH_CONNECT", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH_SCAN("BLUETOOTH_SCAN", "BLUETOOTH_SCAN", "BLUETOOTH_SCAN", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        BODY_SENSORS("BODY_SENSORS", "BODY_SENSORS", "BODY_SENSORS", Group.Sensors, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        CALL_PHONE("CALL_PHONE", "CALL_PHONE", "CALL_PHONE", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        CAMERA("CAMERA", "CAMERA", "CAMERA", Group.Camera, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        GET_ACCOUNTS("GET_ACCOUNTS", "GET_ACCOUNTS", "GET_ACCOUNTS", Group.Contacts, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        PROCESS_OUTGOING_CALLS("PROCESS_OUTGOING_CALLS", "PROCESS_OUTGOING_CALLS", "PROCESS_OUTGOING_CALLS", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_CALENDAR("READ_CALENDAR", "READ_CALENDAR", "READ_CALENDAR", Group.Calendar, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_CALL_LOG("READ_CALL_LOG", "READ_CALL_LOG", "READ_CALL_LOG", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_CONTACTS("READ_CONTACTS", "READ_CONTACTS", "READ_CONTACTS", Group.Contacts, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_EXTERNAL_STORAGE("READ_EXTERNAL_STORAGE", "READ_EXTERNAL_STORAGE", "READ_EXTERNAL_STORAGE", Group.Storage, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_PHONE_NUMBERS("READ_PHONE_NUMBERS", "READ_PHONE_NUMBERS", "READ_PHONE_NUMBERS", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_PHONE_STATE("READ_PHONE_STATE", "READ_PHONE_STATE", "READ_PHONE_STATE", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_SMS("READ_SMS", "READ_SMS", "READ_SMS", Group.Sms, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        RECEIVE_MMS("RECEIVE_MMS", "RECEIVE_MMS", "RECEIVE_MMS", Group.Sms, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        RECEIVE_SMS("RECEIVE_SMS", "RECEIVE_SMS", "RECEIVE_SMS", Group.Sms, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        RECEIVE_WAP_PUSH("RECEIVE_WAP_PUSH", "RECEIVE_WAP_PUSH", "RECEIVE_WAP_PUSH", Group.Sms, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        RECORD_AUDIO("RECORD_AUDIO", "RECORD_AUDIO", "RECORD_AUDIO", Group.Microphone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        SEND_SMS("SEND_SMS", "SEND_SMS", "SEND_SMS", Group.Sms, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        USE_SIP("USE_SIP", "USE_SIP", "USE_SIP", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        UWB_RANGING("UWB_RANGING", "UWB_RANGING", "UWB_RANGING", Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_CALENDAR("WRITE_CALENDAR", "WRITE_CALENDAR", "WRITE_CALENDAR", Group.Calendar, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_CALL_LOG("WRITE_CALL_LOG", "WRITE_CALL_LOG", "WRITE_CALL_LOG", Group.Phone, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_CONTACTS("WRITE_CONTACTS", "WRITE_CONTACTS", "WRITE_CONTACTS", Group.Contacts, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_EXTERNAL_STORAGE("WRITE_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE", Group.Storage, Dangerous, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CARRIER_MESSAGING_SERVICE("BIND_CARRIER_MESSAGING_SERVICE", "BIND_CARRIER_MESSAGING_SERVICE", "BIND_CARRIER_MESSAGING_SERVICE", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        GET_TASKS("GET_TASKS", "GET_TASKS", "GET_TASKS", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        PERSISTENT_ACTIVITY("PERSISTENT_ACTIVITY", "PERSISTENT_ACTIVITY", "PERSISTENT_ACTIVITY", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        RESTART_PACKAGES("RESTART_PACKAGES", "RESTART_PACKAGES", "RESTART_PACKAGES", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_PREFERRED_APPLICATIONS("SET_PREFERRED_APPLICATIONS", "SET_PREFERRED_APPLICATIONS", "SET_PREFERRED_APPLICATIONS", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        SMS_FINANCIAL_TRANSACTIONS("SMS_FINANCIAL_TRANSACTIONS", "SMS_FINANCIAL_TRANSACTIONS", "SMS_FINANCIAL_TRANSACTIONS", Deprecated, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_BLOBS_ACROSS_USERS("ACCESS_BLOBS_ACROSS_USERS", "ACCESS_BLOBS_ACROSS_USERS", "ACCESS_BLOBS_ACROSS_USERS", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_COMPANION_DEVICE_SERVICE("BIND_COMPANION_DEVICE_SERVICE", "BIND_COMPANION_DEVICE_SERVICE", "BIND_COMPANION_DEVICE_SERVICE", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CONTROLS("BIND_CONTROLS", "BIND_CONTROLS", "BIND_CONTROLS", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_QUICK_SETTINGS_TILE("BIND_QUICK_SETTINGS_TILE", "BIND_QUICK_SETTINGS_TILE", "BIND_QUICK_SETTINGS_TILE", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        HIDE_OVERLAY_WINDOWS("HIDE_OVERLAY_WINDOWS", "HIDE_OVERLAY_WINDOWS", "HIDE_OVERLAY_WINDOWS", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        INTERACT_ACROSS_PROFILES("INTERACT_ACROSS_PROFILES", "INTERACT_ACROSS_PROFILES", "INTERACT_ACROSS_PROFILES", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_PRECISE_PHONE_STATE("READ_PRECISE_PHONE_STATE", "READ_PRECISE_PHONE_STATE", "READ_PRECISE_PHONE_STATE", Group.Phone, None, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE("REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE", "REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE", "REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        SCHEDULE_EXACT_ALARM("SCHEDULE_EXACT_ALARM", "SCHEDULE_EXACT_ALARM", "SCHEDULE_EXACT_ALARM", None, new Action() {
            @Override
            public void execute() {
            }
        }),
        UNINSTALL_SHORTCUT("UNINSTALL_SHORTCUT", "UNINSTALL_SHORTCUT", "UNINSTALL_SHORTCUT", NonSupport, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_LOCATION_EXTRA_COMMANDS("ACCESS_LOCATION_EXTRA_COMMANDS", "ACCESS_LOCATION_EXTRA_COMMANDS", "ACCESS_LOCATION_EXTRA_COMMANDS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_NETWORK_STATE("ACCESS_NETWORK_STATE", "ACCESS_NETWORK_STATE", "ACCESS_NETWORK_STATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_NOTIFICATION_POLICY("ACCESS_NOTIFICATION_POLICY", "ACCESS_NOTIFICATION_POLICY", "ACCESS_NOTIFICATION_POLICY", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_WIFI_STATE("ACCESS_WIFI_STATE", "ACCESS_WIFI_STATE", "ACCESS_WIFI_STATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH("BLUETOOTH", "BLUETOOTH", "BLUETOOTH", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH_ADMIN("BLUETOOTH_ADMIN", "BLUETOOTH_ADMIN", "BLUETOOTH_ADMIN", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        BROADCAST_STICKY("BROADCAST_STICKY", "BROADCAST_STICKY", "BROADCAST_STICKY", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        CALL_COMPANION_APP("CALL_COMPANION_APP", "CALL_COMPANION_APP", "CALL_COMPANION_APP", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        CHANGE_NETWORK_STATE("CHANGE_NETWORK_STATE", "CHANGE_NETWORK_STATE", "CHANGE_NETWORK_STATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        CHANGE_WIFI_MULTICAST_STATE("CHANGE_WIFI_MULTICAST_STATE", "CHANGE_WIFI_MULTICAST_STATE", "CHANGE_WIFI_MULTICAST_STATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        CHANGE_WIFI_STATE("CHANGE_WIFI_STATE", "CHANGE_WIFI_STATE", "CHANGE_WIFI_STATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        DISABLE_KEYGUARD("DISABLE_KEYGUARD", "DISABLE_KEYGUARD", "DISABLE_KEYGUARD", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        EXPAND_STATUS_BAR("EXPAND_STATUS_BAR", "EXPAND_STATUS_BAR", "EXPAND_STATUS_BAR", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        FOREGROUND_SERVICE("FOREGROUND_SERVICE", "FOREGROUND_SERVICE", "FOREGROUND_SERVICE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        GET_PACKAGE_SIZE("GET_PACKAGE_SIZE", "GET_PACKAGE_SIZE", "GET_PACKAGE_SIZE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        HIGH_SAMPLING_RATE_SENSORS("HIGH_SAMPLING_RATE_SENSORS", "HIGH_SAMPLING_RATE_SENSORS", "HIGH_SAMPLING_RATE_SENSORS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        INSTALL_SHORTCUT("INSTALL_SHORTCUT", "INSTALL_SHORTCUT", "INSTALL_SHORTCUT", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        INTERNET("INTERNET", "INTERNET", "INTERNET", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        KILL_BACKGROUND_PROCESSES("KILL_BACKGROUND_PROCESSES", "KILL_BACKGROUND_PROCESSES", "KILL_BACKGROUND_PROCESSES", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        MANAGE_OWN_CALLS("MANAGE_OWN_CALLS", "MANAGE_OWN_CALLS", "MANAGE_OWN_CALLS", Group.Phone, Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        MODIFY_AUDIO_SETTINGS("MODIFY_AUDIO_SETTINGS", "MODIFY_AUDIO_SETTINGS", "MODIFY_AUDIO_SETTINGS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        NFC("NFC", "NFC", "NFC", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        NFC_PREFERRED_PAYMENT_INFO("NFC_PREFERRED_PAYMENT_INFO", "NFC_PREFERRED_PAYMENT_INFO", "NFC_PREFERRED_PAYMENT_INFO", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        NFC_TRANSACTION_EVENT("NFC_TRANSACTION_EVENT", "NFC_TRANSACTION_EVENT", "NFC_TRANSACTION_EVENT", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        QUERY_ALL_PACKAGES("QUERY_ALL_PACKAGES", "QUERY_ALL_PACKAGES", "QUERY_ALL_PACKAGES", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_SYNC_SETTINGS("READ_SYNC_SETTINGS", "READ_SYNC_SETTINGS", "READ_SYNC_SETTINGS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_SYNC_STATS("READ_SYNC_STATS", "READ_SYNC_STATS", "READ_SYNC_STATS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        RECEIVE_BOOT_COMPLETED("RECEIVE_BOOT_COMPLETED", "RECEIVE_BOOT_COMPLETED", "RECEIVE_BOOT_COMPLETED", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REORDER_TASKS("REORDER_TASKS", "REORDER_TASKS", "REORDER_TASKS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_COMPANION_PROFILE_WATCH("REQUEST_COMPANION_PROFILE_WATCH", "REQUEST_COMPANION_PROFILE_WATCH", "REQUEST_COMPANION_PROFILE_WATCH", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_COMPANION_RUN_IN_BACKGROUND("REQUEST_COMPANION_RUN_IN_BACKGROUND", "REQUEST_COMPANION_RUN_IN_BACKGROUND", "REQUEST_COMPANION_RUN_IN_BACKGROUND", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND("REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND", "REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND", "REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_COMPANION_USE_DATA_IN_BACKGROUND("REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_DELETE_PACKAGES("REQUEST_DELETE_PACKAGES", "REQUEST_DELETE_PACKAGES", "REQUEST_DELETE_PACKAGES", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_IGNORE_BATTERY_OPTIMIZATIONS("REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_PASSWORD_COMPLEXITY("REQUEST_PASSWORD_COMPLEXITY", "REQUEST_PASSWORD_COMPLEXITY", "REQUEST_PASSWORD_COMPLEXITY", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_ALARM("SET_ALARM", "SET_ALARM", "SET_ALARM", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_WALLPAPER("SET_WALLPAPER", "SET_WALLPAPER", "SET_WALLPAPER", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_WALLPAPER_HINTS("SET_WALLPAPER_HINTS", "SET_WALLPAPER_HINTS", "SET_WALLPAPER_HINTS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        TRANSMIT_IR("TRANSMIT_IR", "TRANSMIT_IR", "TRANSMIT_IR", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        UPDATE_PACKAGES_WITHOUT_USER_ACTION("UPDATE_PACKAGES_WITHOUT_USER_ACTION", "UPDATE_PACKAGES_WITHOUT_USER_ACTION", "UPDATE_PACKAGES_WITHOUT_USER_ACTION", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        USE_BIOMETRIC("USE_BIOMETRIC", "USE_BIOMETRIC", "USE_BIOMETRIC", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        USE_FINGERPRINT("USE_FINGERPRINT", "USE_FINGERPRINT", "USE_FINGERPRINT", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        USE_FULL_SCREEN_INTENT("USE_FULL_SCREEN_INTENT", "USE_FULL_SCREEN_INTENT", "USE_FULL_SCREEN_INTENT", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        VIBRATE("VIBRATE", "VIBRATE", "VIBRATE", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        WAKE_LOCK("WAKE_LOCK", "WAKE_LOCK", "WAKE_LOCK", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_SYNC_SETTINGS("WRITE_SYNC_SETTINGS", "WRITE_SYNC_SETTINGS", "WRITE_SYNC_SETTINGS", Normal, new Action() {
            @Override
            public void execute() {
            }
        }),
        MANAGE_ONGOING_CALLS("MANAGE_ONGOING_CALLS", "MANAGE_ONGOING_CALLS", "MANAGE_ONGOING_CALLS", Group.Phone, SA, new Action() {
            @Override
            public void execute() {
            }
        }),
        USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER("USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER", "USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER", "USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER", SA, new Action() {
            @Override
            public void execute() {
            }
        }),
        MANAGE_EXTERNAL_STORAGE("MANAGE_EXTERNAL_STORAGE", "MANAGE_EXTERNAL_STORAGE", "MANAGE_EXTERNAL_STORAGE", Group.Storage, SAPREI, new Action() {
            @Override
            public void execute() {
            }
        }),
        MANAGE_MEDIA("MANAGE_MEDIA", "MANAGE_MEDIA", "MANAGE_MEDIA", Group.Storage, SAPREI, new Action() {
            @Override
            public void execute() {
            }
        }),
        INSTANT_APP_FOREGROUND_SERVICE("INSTANT_APP_FOREGROUND_SERVICE", "INSTANT_APP_FOREGROUND_SERVICE", "INSTANT_APP_FOREGROUND_SERVICE", SDISTTA, new Action() {
            @Override
            public void execute() {
            }
        }),
        START_VIEW_PERMISSION_USAGE("START_VIEW_PERMISSION_USAGE", "START_VIEW_PERMISSION_USAGE", "START_VIEW_PERMISSION_USAGE", SI, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CALL_REDIRECTION_SERVICE("BIND_CALL_REDIRECTION_SERVICE", "BIND_CALL_REDIRECTION_SERVICE", "BIND_CALL_REDIRECTION_SERVICE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CARRIER_SERVICES("BIND_CARRIER_SERVICES", "BIND_CARRIER_SERVICES", "BIND_CARRIER_SERVICES", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_INCALL_SERVICE("BIND_INCALL_SERVICE", "BIND_INCALL_SERVICE", "BIND_INCALL_SERVICE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_REMOTEVIEWS("BIND_REMOTEVIEWS", "BIND_REMOTEVIEWS", "BIND_REMOTEVIEWS", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_SCREENING_SERVICE("BIND_SCREENING_SERVICE", "BIND_SCREENING_SERVICE", "BIND_SCREENING_SERVICE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_TELECOM_CONNECTION_SERVICE("BIND_TELECOM_CONNECTION_SERVICE", "BIND_TELECOM_CONNECTION_SERVICE", "BIND_TELECOM_CONNECTION_SERVICE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_TV_INPUT("BIND_TV_INPUT", "BIND_TV_INPUT", "BIND_TV_INPUT", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_VISUAL_VOICEMAIL_SERVICE("BIND_VISUAL_VOICEMAIL_SERVICE", "BIND_VISUAL_VOICEMAIL_SERVICE", "BIND_VISUAL_VOICEMAIL_SERVICE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_WALLPAPER("BIND_WALLPAPER", "BIND_WALLPAPER", "BIND_WALLPAPER", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        CLEAR_APP_CACHE("CLEAR_APP_CACHE", "CLEAR_APP_CACHE", "CLEAR_APP_CACHE", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        DELETE_CACHE_FILES("DELETE_CACHE_FILES", "DELETE_CACHE_FILES", "DELETE_CACHE_FILES", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        GET_ACCOUNTS_PRIVILEGED("GET_ACCOUNTS_PRIVILEGED", "GET_ACCOUNTS_PRIVILEGED", "GET_ACCOUNTS_PRIVILEGED", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        GLOBAL_SEARCH("GLOBAL_SEARCH", "GLOBAL_SEARCH", "GLOBAL_SEARCH", SP, new Action() {
            @Override
            public void execute() {
            }
        }),
        LOADER_USAGE_STATS("LOADER_USAGE_STATS", "LOADER_USAGE_STATS", "LOADER_USAGE_STATS", SPA, new Action() {
            @Override
            public void execute() {
            }
        }),
        BATTERY_STATS("BATTERY_STATS", "BATTERY_STATS", "BATTERY_STATS", SPD, new Action() {
            @Override
            public void execute() {
            }
        }),
        CHANGE_CONFIGURATION("CHANGE_CONFIGURATION", "CHANGE_CONFIGURATION", "CHANGE_CONFIGURATION", SPD, new Action() {
            @Override
            public void execute() {
            }
        }),
        PACKAGE_USAGE_STATS("PACKAGE_USAGE_STATS", "PACKAGE_USAGE_STATS", "PACKAGE_USAGE_STATS", SPDARTD, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_VOICEMAIL("READ_VOICEMAIL", "READ_VOICEMAIL", "READ_VOICEMAIL", SPRL, new Action() {
            @Override
            public void execute() {
            }
        }),
        SYSTEM_ALERT_WINDOW("SYSTEM_ALERT_WINDOW", "SYSTEM_ALERT_WINDOW", "SYSTEM_ALERT_WINDOW", SSUAIP23D, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_ACCESSIBILITY_SERVICE("BIND_ACCESSIBILITY_SERVICE", "BIND_ACCESSIBILITY_SERVICE", "BIND_ACCESSIBILITY_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_AUTOFILL_SERVICE("BIND_AUTOFILL_SERVICE", "BIND_AUTOFILL_SERVICE", "BIND_AUTOFILL_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CARRIER_MESSAGING_CLIENT_SERVICE("BIND_CARRIER_MESSAGING_CLIENT_SERVICE", "BIND_CARRIER_MESSAGING_CLIENT_SERVICE", "BIND_CARRIER_MESSAGING_CLIENT_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CHOOSER_TARGET_SERVICE("BIND_CHOOSER_TARGET_SERVICE", "BIND_CHOOSER_TARGET_SERVICE", "BIND_CHOOSER_TARGET_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_CONDITION_PROVIDER_SERVICE("BIND_CONDITION_PROVIDER_SERVICE", "BIND_CONDITION_PROVIDER_SERVICE", "BIND_CONDITION_PROVIDER_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_DEVICE_ADMIN("BIND_DEVICE_ADMIN", "BIND_DEVICE_ADMIN", "BIND_DEVICE_ADMIN", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_DREAM_SERVICE("BIND_DREAM_SERVICE", "BIND_DREAM_SERVICE", "BIND_DREAM_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_INPUT_METHOD("BIND_INPUT_METHOD", "BIND_INPUT_METHOD", "BIND_INPUT_METHOD", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_MIDI_DEVICE_SERVICE("BIND_MIDI_DEVICE_SERVICE", "BIND_MIDI_DEVICE_SERVICE", "BIND_MIDI_DEVICE_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_NFC_SERVICE("BIND_NFC_SERVICE", "BIND_NFC_SERVICE", "BIND_NFC_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_NOTIFICATION_LISTENER_SERVICE("BIND_NOTIFICATION_LISTENER_SERVICE", "BIND_NOTIFICATION_LISTENER_SERVICE", "BIND_NOTIFICATION_LISTENER_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_PRINT_SERVICE("BIND_PRINT_SERVICE", "BIND_PRINT_SERVICE", "BIND_PRINT_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_QUICK_ACCESS_WALLET_SERVICE("BIND_QUICK_ACCESS_WALLET_SERVICE", "BIND_QUICK_ACCESS_WALLET_SERVICE", "BIND_QUICK_ACCESS_WALLET_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_TEXT_SERVICE("BIND_TEXT_SERVICE", "BIND_TEXT_SERVICE", "BIND_TEXT_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_VOICE_INTERACTION("BIND_VOICE_INTERACTION", "BIND_VOICE_INTERACTION", "BIND_VOICE_INTERACTION", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_VPN_SERVICE("BIND_VPN_SERVICE", "BIND_VPN_SERVICE", "BIND_VPN_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_VR_LISTENER_SERVICE("BIND_VR_LISTENER_SERVICE", "BIND_VR_LISTENER_SERVICE", "BIND_VR_LISTENER_SERVICE", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        REQUEST_INSTALL_PACKAGES("REQUEST_INSTALL_PACKAGES", "REQUEST_INSTALL_PACKAGES", "REQUEST_INSTALL_PACKAGES", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_SETTINGS("WRITE_SETTINGS", "WRITE_SETTINGS", "WRITE_SETTINGS", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_VOICEMAIL("WRITE_VOICEMAIL", "WRITE_VOICEMAIL", "WRITE_VOICEMAIL", Signature, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCESS_CHECKIN_PROPERTIES("ACCESS_CHECKIN_PROPERTIES", "ACCESS_CHECKIN_PROPERTIES", "ACCESS_CHECKIN_PROPERTIES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        ACCOUNT_MANAGER("ACCOUNT_MANAGER", "ACCOUNT_MANAGER", "ACCOUNT_MANAGER", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        BIND_APPWIDGET("BIND_APPWIDGET", "BIND_APPWIDGET", "BIND_APPWIDGET", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        BLUETOOTH_PRIVILEGED("BLUETOOTH_PRIVILEGED", "BLUETOOTH_PRIVILEGED", "BLUETOOTH_PRIVILEGED", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        BROADCAST_PACKAGE_REMOVED("BROADCAST_PACKAGE_REMOVED", "BROADCAST_PACKAGE_REMOVED", "BROADCAST_PACKAGE_REMOVED", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        BROADCAST_SMS("BROADCAST_SMS", "BROADCAST_SMS", "BROADCAST_SMS", Group.Sms, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        BROADCAST_WAP_PUSH("BROADCAST_WAP_PUSH", "BROADCAST_WAP_PUSH", "BROADCAST_WAP_PUSH", Group.Sms, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        CALL_PRIVILEGED("CALL_PRIVILEGED", "CALL_PRIVILEGED", "CALL_PRIVILEGED", Group.Phone, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        CAPTURE_AUDIO_OUTPUT("CAPTURE_AUDIO_OUTPUT", "CAPTURE_AUDIO_OUTPUT", "CAPTURE_AUDIO_OUTPUT", Group.Camera, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        CHANGE_COMPONENT_ENABLED_STATE("CHANGE_COMPONENT_ENABLED_STATE", "CHANGE_COMPONENT_ENABLED_STATE", "CHANGE_COMPONENT_ENABLED_STATE", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        CONTROL_LOCATION_UPDATES("CONTROL_LOCATION_UPDATES", "CONTROL_LOCATION_UPDATES", "CONTROL_LOCATION_UPDATES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        DELETE_PACKAGES("DELETE_PACKAGES", "DELETE_PACKAGES", "DELETE_PACKAGES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        DIAGNOSTIC("DIAGNOSTIC", "DIAGNOSTIC", "DIAGNOSTIC", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        DUMP("DUMP", "DUMP", "DUMP", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        FACTORY_TEST("FACTORY_TEST", "FACTORY_TEST", "FACTORY_TEST", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        INSTALL_LOCATION_PROVIDER("INSTALL_LOCATION_PROVIDER", "INSTALL_LOCATION_PROVIDER", "INSTALL_LOCATION_PROVIDER", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        INSTALL_PACKAGES("INSTALL_PACKAGES", "INSTALL_PACKAGES", "INSTALL_PACKAGES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        LOCATION_HARDWARE("LOCATION_HARDWARE", "LOCATION_HARDWARE", "LOCATION_HARDWARE", Group.Location, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MANAGE_DOCUMENTS("MANAGE_DOCUMENTS", "MANAGE_DOCUMENTS", "MANAGE_DOCUMENTS", Group.Storage, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MASTER_CLEAR("MASTER_CLEAR", "MASTER_CLEAR", "MASTER_CLEAR", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MEDIA_CONTENT_CONTROL("MEDIA_CONTENT_CONTROL", "MEDIA_CONTENT_CONTROL", "MEDIA_CONTENT_CONTROL", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MODIFY_PHONE_STATE("MODIFY_PHONE_STATE", "MODIFY_PHONE_STATE", "MODIFY_PHONE_STATE", Group.Phone, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MOUNT_FORMAT_FILESYSTEMS("MOUNT_FORMAT_FILESYSTEMS", "MOUNT_FORMAT_FILESYSTEMS", "MOUNT_FORMAT_FILESYSTEMS", Group.Storage, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        MOUNT_UNMOUNT_FILESYSTEMS("MOUNT_UNMOUNT_FILESYSTEMS", "MOUNT_UNMOUNT_FILESYSTEMS", "MOUNT_UNMOUNT_FILESYSTEMS", Group.Storage, UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_INPUT_STATE("READ_INPUT_STATE", "READ_INPUT_STATE", "READ_INPUT_STATE", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        READ_LOGS("READ_LOGS", "READ_LOGS", "READ_LOGS", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        REBOOT("REBOOT", "REBOOT", "REBOOT", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SEND_RESPOND_VIA_MESSAGE("SEND_RESPOND_VIA_MESSAGE", "SEND_RESPOND_VIA_MESSAGE", "SEND_RESPOND_VIA_MESSAGE", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_ALWAYS_FINISH("SET_ALWAYS_FINISH", "SET_ALWAYS_FINISH", "SET_ALWAYS_FINISH", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_ANIMATION_SCALE("SET_ANIMATION_SCALE", "SET_ANIMATION_SCALE", "SET_ANIMATION_SCALE", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_DEBUG_APP("SET_DEBUG_APP", "SET_DEBUG_APP", "SET_DEBUG_APP", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_PROCESS_LIMIT("SET_PROCESS_LIMIT", "SET_PROCESS_LIMIT", "SET_PROCESS_LIMIT", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_TIME("SET_TIME", "SET_TIME", "SET_TIME", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SET_TIME_ZONE("SET_TIME_ZONE", "SET_TIME_ZONE", "SET_TIME_ZONE", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        SIGNAL_PERSISTENT_PROCESSES("SIGNAL_PERSISTENT_PROCESSES", "SIGNAL_PERSISTENT_PROCESSES", "SIGNAL_PERSISTENT_PROCESSES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        START_FOREGROUND_SERVICES_FROM_BACKGROUND("START_FOREGROUND_SERVICES_FROM_BACKGROUND", "START_FOREGROUND_SERVICES_FROM_BACKGROUND", "START_FOREGROUND_SERVICES_FROM_BACKGROUND", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        STATUS_BAR("STATUS_BAR", "STATUS_BAR", "STATUS_BAR", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        UPDATE_DEVICE_STATS("UPDATE_DEVICE_STATS", "UPDATE_DEVICE_STATS", "UPDATE_DEVICE_STATS", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_APN_SETTINGS("WRITE_APN_SETTINGS", "WRITE_APN_SETTINGS", "WRITE_APN_SETTINGS", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_GSERVICES("WRITE_GSERVICES", "WRITE_GSERVICES", "WRITE_GSERVICES", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        WRITE_SECURE_SETTINGS("WRITE_SECURE_SETTINGS", "WRITE_SECURE_SETTINGS", "WRITE_SECURE_SETTINGS", UnUseThirdApp, new Action() {
            @Override
            public void execute() {
            }
        }),
        ;
        public String name;
        public String desc;
        public String pmsn;
        public Group group;
        public Level level;
        public Action action;

        PermissionApi(String name, String desc, String pmsn, Level level, Action action) {
            this(name, desc, pmsn, Group.None, level, action);
        }

        PermissionApi(String name, String desc, String pmsn, Group group, Level level, Action action) {
            this.name = name;
            this.desc = desc;
            this.pmsn = pmsn;
            this.group = group;
            this.level = level;
            this.action = action;
        }

        public static PermissionApi find(String pmsn) {
            for (PermissionApi api : PermissionApi.values()) {
                if (api.pmsn.equals(pmsn)) {
                    return api;
                }
            }
            return null;
        }
    }

    public interface Action {
        void execute();
    }

    public enum Group {
        None("none", "无分组"),
        ActivityRecognition("ACTIVITY_RECOGNITION", "活动识别"),
        Calendar("CALENDAR", "日历相关"),
        CallLog("CALL_LOG", "电话功能"),
        Camera("CAMERA", "摄像机或从设备捕获图像/视频"),
        Contacts("CONTACTS", "设备上的联系人和配置文件"),
        Location("LOCATION", "允许访问设备位置"),
        Microphone("MICROPHONE", "访问麦克风音频"),
        NearbyDevices("NEARBY_DEVICES", "发现并连接到附近的蓝牙"),
        Phone("PHONE", "电话功能"),
        Sensors("SENSORS", "访问身体或环境传感器"),
        Sms("SMS", "用户短信"),
        Storage("STORAGE", "外部存储");
        public String code;
        public String desc;

        Group(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    public enum Level {
        None("none", "空"),
        Normal("normal", "正常的"),
        Dangerous("dangerous", "危险的"),
        Signature("signature", "同签名"),
        SignatureOrSystem("signatureOrSystem", "同签名或系统APP"),
        UnUseThirdApp("unUseThirdApp", "不支持三方APP"),
        NonSupport("nonSupport", "不支持"),
        Deprecated("deprecated", "过期的"),
        SAPREI("S|A|PREI", "同签名|AppOp|预安装"),
        SPREIAP23("S|PREI|A|P23", "同签名|预安装|AppOp|Pre23"),
        SPRL("S|P|RL", "同签名|Privileged|Role"),
        SI("S|I", "同签名|Installer"),
        SPA("S|P|A", "同签名|Privileged|AppOp"),
        SDISTTA("S|D|ISTT|A", "同签名|Development|Instant|AppOp"),
        SA("S|A", "同签名|AppOp"),
        SPDARTD("S|P|D|A|RTD", "同签名|Privileged|Development|AppOp|RetailDemo"),
        SSUAIP23D("S|SU|A|I|P23|D", "同签名|Setup|AppOp|Installer|Pre23|Development"),
        SPD("S|P|D", "同签名|Privileged|Development"),
        SP("S|P", "同签名|Privileged");
        public String code;
        public String desc;

        Level(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
