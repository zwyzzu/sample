package com.zhangwy.sample.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zhangwy.sample.R;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
    }


    public void parseGson(View view) {
        Context context = this.getApplicationContext();
        String json = new Gson().toJson(context);
        ((TextView) this.findViewById(R.id.jsonTextGson)).setText(json);
    }

    public void parseFastJson(View view) {
        Context context = this.getApplicationContext();
        String json = JSON.toJSONString(context);
        ((TextView) this.findViewById(R.id.jsonTextFastJson)).setText(json);
    }
}
