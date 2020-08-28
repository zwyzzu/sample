package com.zhangwy.sample.view;


import com.zhangwy.sample.entity.AppInfoEntity;

import java.util.List;

public interface AppView extends BaseView {
    void onAppList(List<AppInfoEntity> apps);
}
