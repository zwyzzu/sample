package com.zwy.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.zwy.demo.R;
import com.zwy.demo.entity.StartActEntity;
import com.zwy.widget.adapter.BaseAdapter;
import com.zwy.widget.adapter.BaseAdapter.OnItemLoading;
/**
 * Author: zhangwy
 * 创建时间：2015年9月17日 下午3:29:13
 * 修改时间：2015年9月17日 下午3:29:13
 * Description: 主Activity
 **/
public class MainActivity extends BaseActivity implements OnItemClickListener, OnItemLoading<StartActEntity>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(new BaseAdapter<StartActEntity>(getItems(), this));
		listView.setOnItemClickListener(this);
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

	private List<StartActEntity> getItems() {
		List<StartActEntity> list = new ArrayList<StartActEntity>();
		list.add(new StartActEntity(0, "activity cycle", "调研生命Activity周期", CycleAActivity.class));
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

	@Override
	public View getView(View convertView, StartActEntity item) {
		return null;
	}
}