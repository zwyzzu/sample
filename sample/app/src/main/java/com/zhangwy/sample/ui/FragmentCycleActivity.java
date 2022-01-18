package com.zhangwy.sample.ui;

import android.os.Bundle;

import com.zhangwy.sample.R;
import com.zhangwy.sample.ui.fragment.BlankFragment;

public class FragmentCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_cycle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentCycleHome, BlankFragment.newInstance("", ""))
                .commit();
    }
}