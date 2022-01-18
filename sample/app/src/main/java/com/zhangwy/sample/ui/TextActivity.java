package com.zhangwy.sample.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangwy.sample.R;

/**
 * CreateTime 2021/12/2 16:13
 * Author zhangwy
 * desc:
 *
 * -------------------------------------------------------------------------------------------------
 * use:
 *
 **/
public class TextActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 100;
        AppCompatTextView textView1 = new AppCompatTextView(this);
        textView1.setBackgroundColor(Color.GRAY);
        textView1.setSingleLine(true);
        textView1.setMaxLines(1);
        textView1.setLines(1);
        textView1.setText(R.string.desc_scroll_nested);
//        textView1.setEllipsize(TruncateAt.END);

        TextView textView2 = new TextView(this);
        textView2.setBackgroundColor(Color.GRAY);
        textView2.setSingleLine(true);
        textView2.setMaxLines(1);
        textView2.setLines(1);
        textView2.setText(R.string.desc_scroll_nested);
//        textView2.setEllipsize(TruncateAt.END);

        AppCompatTextView textView3 = new AppCompatTextView(this);
        textView3.setBackgroundColor(Color.GRAY);
        textView3.setSingleLine(true);
        textView3.setMaxLines(1);
        textView3.setLines(1);
        textView3.setGravity(Gravity.CENTER);
        textView3.setText(R.string.desc_scroll_nested);
//        textView3.setEllipsize(TruncateAt.END);

        TextView textView4 = new TextView(this);
        textView4.setBackgroundColor(Color.GRAY);
        textView4.setSingleLine(true);
        textView4.setMaxLines(1);
        textView4.setLines(1);
        textView4.setGravity(Gravity.CENTER);
        textView4.setText(R.string.desc_scroll_nested);
//        textView4.setEllipsize(TruncateAt.END);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(textView1, params);
        linearLayout.addView(textView2, params);
        linearLayout.addView(textView3, params);
        linearLayout.addView(textView4, params);
        linearLayout.setGravity(Gravity.CENTER);
        setContentView(linearLayout);
    }
}
