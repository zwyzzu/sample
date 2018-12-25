package com.zhangwy.sample.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zhangwy.sample.R;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.RecyclerDivider;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwy on 2018/2/1 上午11:11.
 * Updated by zhangwy on 2018/2/1 上午11:11.
 * Description
 */

public class SpannableActivity extends BaseActivity implements RecyclerAdapter.OnItemClickListener<Integer> {

    private EditText editText;
    private WRecyclerView<Integer> wRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_spannable);
        this.editText = (EditText) findViewById(R.id.spannable_content);
        this.wRecyclerView = (WRecyclerView<Integer>) findViewById(R.id.spannable_buttons);
        this.wRecyclerView.setLinearLayoutManager(WRecyclerView.HORIZONTAL, false);
        this.wRecyclerView.addItemDecoration(RecyclerDivider.create(this, LinearLayoutManager.VERTICAL, 5, Color.argb(0, 0, 0, 0)));
        this.wRecyclerView.loadData(getButtons(), new RecyclerAdapter.OnItemLoading<Integer>() {
            @Override
            public View onCreateView(ViewGroup parent, int viewType) {
                return LayoutInflater.from(SpannableActivity.this).inflate(R.layout.item_spannable_button, parent, false);
            }

            @Override
            public void onLoadView(View root, int viewType, Integer nameId, int position) {
                ((Button) root.findViewById(R.id.spannable_button)).setText(nameId);
            }
        });
        this.wRecyclerView.setOnItemClickListener(this);
    }

    /**
     * <string name="spannable_background">背景颜色</string>
     * <string name="spannable_fontColor">字体颜色</string>
     * <string name="spannable_fontStyle">字体样式</string>
     * <string name="spannable_fontFace">字体</string>
     * <string name="spannable_image">图片</string>
     * <string name="spannable_url">链接</string>
     * <string name="spannable_underline">下划线</string>
     * <string name="spannable_strikeThrough">删除线</string>
     */
    @Override
    public void onItemClick(View view, int viewType, Integer nameId, int position) {
        switch (nameId) {
            case R.string.spannable_background: {
                break;
            }
            case R.string.spannable_fontColor: {
                break;
            }
            case R.string.spannable_fontStyle: {
                break;
            }
            case R.string.spannable_fontFace: {
                break;
            }
            case R.string.spannable_image: {
                break;
            }
            case R.string.spannable_url: {
                break;
            }
            case R.string.spannable_underline: {
                break;
            }
            case R.string.spannable_strikeThrough: {
                Settings.System.putInt(getContentResolver(),android.provider.Settings.System.SCREEN_OFF_TIMEOUT,-1);
                break;
            }
        }
    }

    private List<Integer> getButtons() {
        List<Integer> array = new ArrayList<>();
        array.add(R.string.spannable_background);
        array.add(R.string.spannable_fontColor);
        array.add(R.string.spannable_fontStyle);
        array.add(R.string.spannable_fontFace);
        array.add(R.string.spannable_image);
        array.add(R.string.spannable_url);
        array.add(R.string.spannable_underline);
        array.add(R.string.spannable_strikeThrough);
        return array;
    }
}
