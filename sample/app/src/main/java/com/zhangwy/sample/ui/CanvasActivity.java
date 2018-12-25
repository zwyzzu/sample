package com.zhangwy.sample.ui;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.zhangwy.sample.R;
import com.zhangwy.widget.WImageView;

public class CanvasActivity extends BaseActivity implements View.OnClickListener {

    WImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        imageView = (WImageView) findViewById(R.id.canvasImage);
        imageView.setRoundAsCircle(true);
        findViewById(R.id.canvasButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.canvasButton:
                Drawable drawable = imageView.getDrawable();
                if (drawable != null && drawable instanceof Animatable) {
                    Animatable animatable = (Animatable) drawable;
                    if (animatable.isRunning()) {
                        animatable.stop();
                    } else {
                        animatable.start();
                    }
                }
                break;
        }
    }
}
