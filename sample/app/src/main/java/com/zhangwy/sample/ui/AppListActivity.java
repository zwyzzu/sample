package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.sample.entity.AppInfoEntity;
import com.zhangwy.sample.presenter.AppPresenter;
import com.zhangwy.sample.recycler.RadioListAdapter;
import com.zhangwy.sample.view.AppView;

import java.util.Collections;
import java.util.List;

public class AppListActivity extends AppCompatActivity implements AppView {

    private RecyclerView recyclerView;
    private RadioListAdapter<AppInfoEntity> adapter;
    private AppPresenter presenter;
    private AppInfoEntity selected;
    private String pkgName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        initActionBar();
        this.presenter = new AppPresenter(this, this);
        this.recyclerView = this.findViewById(R.id.appListRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.adapter = new RadioListAdapter<>(null, new RadioListAdapter.OnItemLoading<AppInfoEntity>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(AppListActivity.this).inflate(R.layout.item_app_info, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, int position, AppInfoEntity entity, boolean isChecked) {
                AppCompatImageView imageView = root.findViewById(R.id.appInfoIcon);
                TextView name = root.findViewById(R.id.appInfoName);
                TextView pkg = root.findViewById(R.id.appInfoPackage);
                CheckBox checkBox = root.findViewById(R.id.appInfoChecked);
                checkBox.setClickable(false);
                checkBox.setChecked(isChecked);
                name.setText(entity.getName());
                pkg.setText(entity.getPkgName());
                imageView.setImageDrawable(entity.getIcon());
            }

            @Override
            public void onSort(List<AppInfoEntity> array) {
                super.onSort(array);
                Collections.sort(array);
            }

            @Override
            public void onSliding() {
                super.onSliding();
                recyclerView.scrollToPosition(0);
            }
        });
        this.adapter.setItemCheckedChangedListener(new RadioListAdapter.OnItemCheckedChangedListener<AppInfoEntity>() {
            @Override
            public void onCheckedChanged(View newView, int newViewType, AppInfoEntity newEntity, AppInfoEntity oldEntity) {
                selected = newEntity;
            }
        });
        this.recyclerView.setAdapter(this.adapter);
//        this.pkgName = Common.getAppPackage();
        this.presenter.reLoadApps(this);
    }

    private void initActionBar() {
        Toolbar toolbar = this.findViewById(R.id.appListToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.setResult(RESULT_CANCELED);
                this.finish();
                break;
            case R.id.appInfoSave:
                if (this.selected == null || TextUtils.equals(this.pkgName, this.selected.getPkgName())) {
                    this.setResult(RESULT_CANCELED);
                } else {
//                    Common.saveApp(this, this.selected.getPkgName());
                    this.setResult(RESULT_OK);
                }
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAppList(List<AppInfoEntity> apps) {
        this.adapter.setData(apps);
        final String packageName = this.selected == null ? this.pkgName : this.selected.getPkgName();
        if (TextUtils.isEmpty(packageName)) {
            return;
        }

        for (AppInfoEntity entity : apps) {
            if (TextUtils.equals(entity.getPkgName(), packageName)) {
                this.adapter.setSelected(entity);
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }
}
