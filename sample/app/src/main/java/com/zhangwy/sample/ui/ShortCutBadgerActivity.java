package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zhangwy.sample.R;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ShortCutBadgerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut_badger);
        this.findViewById(R.id.shortCutBadgerSend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shortCutBadgerSend) {
            ShortcutBadger.applyCount(this, getCount());
        }
    }

    private int getCount() {
        EditText text = this.findViewById(R.id.shortCutBadgerInput);
        String string = text.getText().toString();
        return Integer.parseInt(string);
    }
}
