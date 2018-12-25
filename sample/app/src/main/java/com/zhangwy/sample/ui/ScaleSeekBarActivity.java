package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.widget.SeekBar;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;
import com.zhangwy.util.Util;
import com.zhangwy.widget.CustomSeekBar;
import com.zhangwy.widget.ScaleSeekBar;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by zhangwy on 2017/11/29 下午5:21.
 * Updated by zhangwy on 2017/11/29 下午5:21.
 * Description
 */

public class ScaleSeekBarActivity extends BaseActivity implements CustomSeekBar.OnSeekBarChangeListener, SeekBar.OnSeekBarChangeListener {

    ArrayList<String> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_scale_seekbar);
        ScaleSeekBar scaleSeekBar = (ScaleSeekBar) findViewById(R.id.scaleSeekBar);
        this.array = getArray(200, 1800, 100);
        Logger.d(Util.array2Strings(this.array, ','));
        scaleSeekBar.setSections(this.array);
        scaleSeekBar.setProgress(0);
        scaleSeekBar.setOnSeekBarChangeListener(this);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(array.size() - 1);
        scaleSeekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(this);
    }

    private ArrayList<String> getArray(int min, int max, int interval) {
        ArrayList<String> array = new ArrayList<>();
        for (int item = min; item <= max; item += interval) {
            array.add(String.valueOf(item));
        }
        return array;
    }

    @Override
    public void onProgressChanged(CustomSeekBar seekBar, int progress, boolean fromUser) {
        Logger.d(String.format(Locale.getDefault(), "onProgressChanged(CustomSeekBar seekBar, progress : %1$d, fromUser : %2$b) value : %3$s", progress, fromUser, findValue(this.array, progress, "100")));
    }

    @Override
    public void onStartTrackingTouch(CustomSeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(CustomSeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Logger.d(String.format(Locale.getDefault(), "onProgressChanged(SeekBar seekBar, progress : %1$d, fromUser : %2$b) value : %3$s", progress, fromUser, findValue(this.array, progress, "100")));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private String findValue(ArrayList<String> array, int position, String def) {
        if (!Util.isEmpty(array)) {
            if (position < array.size()) {
                return array.get(position);
            } else {
                return array.get(0);
            }
        }
        return def;
    }
}
