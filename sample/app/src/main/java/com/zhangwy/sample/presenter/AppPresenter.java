package com.zhangwy.sample.presenter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zhangwy.sample.entity.AppInfoEntity;
import com.zhangwy.sample.view.AppView;

import java.util.ArrayList;
import java.util.List;

public class AppPresenter extends BasePresenter<AppView> {
    private String mSelectApp = "";

    public AppPresenter(Context context, AppView view) {
        super(view);
    }

    public void reLoadApps(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);  //获取所以已安装的包
        ArrayList<AppInfoEntity> list = new ArrayList<>();
        for (PackageInfo packageInfo : installedPackages) {
            AppInfoEntity info = new AppInfoEntity();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;  //应用信息
            // 状态机,通过01状态来表示是否具备某些属性和功能
            int flags = applicationInfo.flags;  // 获取应用标记
            info.setUser((flags & ApplicationInfo.FLAG_SYSTEM) != ApplicationInfo.FLAG_SYSTEM);
            if (!info.isUser()) {
                continue;
            }
            info.setName(applicationInfo.loadLabel(pm).toString());
            info.setPkgName(packageInfo.packageName);
            info.setIcon(applicationInfo.loadIcon(pm));
            info.setRom((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != ApplicationInfo.FLAG_EXTERNAL_STORAGE);
            list.add(info);
        }
        if (this.hasView()) {
            this.getView().onAppList(list);
        }
    }

    @Override
    void onDestroy() {
    }
}
