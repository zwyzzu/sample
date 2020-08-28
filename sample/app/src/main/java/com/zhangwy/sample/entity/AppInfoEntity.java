package com.zhangwy.sample.entity;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Locale;

@SuppressWarnings("unused")
public class AppInfoEntity extends BaseEntity implements Comparable<AppInfoEntity> {
    private String name;
    private String pkgName;
    private Drawable icon;
    private boolean isRom = false;//安装到rom还是SD卡
    private boolean isUser = true; //是否为用户安装应用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isRom() {
        return isRom;
    }

    public void setRom(boolean rom) {
        isRom = rom;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    @Override
    public int compareTo(AppInfoEntity o) {
        if (o == null) {
            return 0;
        }
        Collator collator = Collator.getInstance(Locale.CHINA);
        return collator.compare(this.getName(), o.getName());
    }
}