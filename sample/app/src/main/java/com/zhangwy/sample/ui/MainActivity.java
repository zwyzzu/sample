package com.zhangwy.sample.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.sample.entity.HomeSampleItem;
import com.zwy.widget.recycler.RecyclerAdapter;
import com.zwy.widget.recycler.RecyclerDivider;
import com.zwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements RecyclerAdapter.OnItemClickListener<HomeSampleItem> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSampleItems();
    }

    private void initSampleItems() {
        WRecyclerView<HomeSampleItem> view = (WRecyclerView<HomeSampleItem>) findViewById(R.id.main_recycler);
        view.setLinearLayoutManager(WRecyclerView.VERTICAL, false);
        view.setOnItemClickListener(this);
        view.loadData(getSampleItems(), new HomeAdapter());
    }

    @Override
    public void onItemClick(View view, int viewType, HomeSampleItem entity, int position) {
        startActivity(new Intent(this, entity.getClazz()));
    }

    private static class HomeAdapter extends RecyclerAdapter.OnItemLoading<HomeSampleItem> {

        @Override
        public int getItemViewType(HomeSampleItem entity, int position) {
            return super.getItemViewType(entity, position);
        }

        @Override
        public View onCreateView(ViewGroup parent, int viewType) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sample, parent, false);
        }

        @Override
        public void onLoadView(View root, int viewType, HomeSampleItem entity, int position) {
            ((TextView) root.findViewById(R.id.home_sample_id)).setText(String.valueOf(entity.getCode()));
            ((TextView) root.findViewById(R.id.home_sample_title)).setText(entity.getName());
            ((TextView) root.findViewById(R.id.home_sample_desc)).setText(entity.getDesc());
        }
    }

    private ArrayList<HomeSampleItem> getSampleItems(){
        ArrayList<HomeSampleItem> samples = new ArrayList<>();
        samples.add(new HomeSampleItem(1, "Sample Item1", "Cycle Activity", getString(R.string.desc_cycle), CycleAActivity.class));
        samples.add(new HomeSampleItem(2, "Sample Item2", "Scroll Nested Activity", getString(R.string.desc_scroll_nested), ScrollNestedActivity.class));
        samples.add(new HomeSampleItem(3, "Sample Item3", "BaiDu Map Activity", getString(R.string.desc_map_baidu),MapBaiDuActivity.class));
        return samples;
    }
}