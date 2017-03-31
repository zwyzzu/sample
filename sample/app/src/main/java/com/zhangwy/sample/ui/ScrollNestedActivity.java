package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.widget.recycler.RecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by 张维亚(zhangwy) on 2016/12/22 下午4:18.
 * Updated by zhangwy on 2016/12/22 下午4:18.
 * Description
 */
public class ScrollNestedActivity extends BaseActivity {

    final int COUNT = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_nested);
        initHorizontalScroll();
        initRecycler();
    }

    private void initHorizontalScroll() {
        ViewGroup layout = (ViewGroup) findViewById(R.id.scroll_nested_scroll_content);
        for (int i = 0; i < COUNT; i++){
            View view = LayoutInflater.from(this).inflate(R.layout.item_scroll_nested, layout, false);
            layout.addView(view);
            ((TextView)view.findViewById(R.id.scroll_nested_num)).setText(String.valueOf(i));
        }
    }

    private void initRecycler() {

        RecyclerView recycler = (RecyclerView) findViewById(R.id.scroll_nested_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new RecyclerAdapter<Integer>(getRecyclerData(), new RecyclerAdapter.OnItemLoading<Integer>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scroll_nested, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, Integer entity, int position) {
                ((TextView)root.findViewById(R.id.scroll_nested_num)).setText(String.valueOf(entity));
            }
        }));
    }

    private ArrayList<Integer> getRecyclerData(){
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < COUNT; i++){
            array.add(i);
        }
        return array;
    }
}
