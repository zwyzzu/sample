package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.widget.flowlayout.FlowLayout;

import java.util.ArrayList;

public class FlowLayoutActivity extends BaseActivity implements FlowLayout.OnItemClickListener<String>, FlowLayout.OnItemLoading<String> {

    private FlowLayout<String> flowLayout;
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        this.flowLayout = (FlowLayout<String>) this.findViewById(R.id.flowLayoutView);
        this.input = (EditText) this.findViewById(R.id.flowLayoutInput);
        this.findViewById(R.id.flowLayoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    flowLayout.add(text, 0);
                }
                input.getText().clear();
            }
        });
        this.flowLayout.setOnItemListener(this);
        this.flowLayout.loadData(new ArrayList<String>(), this);
    }

    @Override
    public void onItemClick(View view, String entity, int position) {
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        return LayoutInflater.from(this).inflate(R.layout.flow_item, parent, false);
    }

    @Override
    public void onLoadView(View root, String entity) {
        View view = root.findViewById(R.id.flowItem);
        if (view != null && view instanceof TextView) {
            ((TextView) view).setText(entity);
        }
    }
}
