package com.study.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.study.mobile.R;
import com.study.mobile.entity.ActionEntity;
import com.zwy.widget.adapter.BaseAdapter;
import com.zwy.widget.adapter.BaseAdapter.OnItemLoading;

public class MainActivity extends BaseActivity implements OnItemClickListener, OnItemLoading<ActionEntity>{

	private TextView desc;
	private ListView list;
	private BaseAdapter<ActionEntity> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private void initView() {
		desc = (TextView) findViewById(R.id.main_desc);
		list = (ListView) findViewById(R.id.main_list);
	}

	private void initData() {
		desc.setText(R.string.desc_main);
		mAdapter = new BaseAdapter<ActionEntity>(getItems(), this);
		list.setAdapter(mAdapter);
	}

	private List<ActionEntity> getItems(){
		List<ActionEntity> list = new ArrayList<ActionEntity>();
		list.add(new ActionEntity(list.size(), getString(R.string.name_cycle), getString(R.string.desc_cycle), CycleActivity.class));
		return list;
	}

	@Override
	public View getView(View view, ActionEntity item) {
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(new Intent(this, mAdapter.getItem(position).getClass()));
	}
}