package com.study.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.study.mobile.R;
import com.study.mobile.common.Common;
import com.study.mobile.entity.ActionEntity;
import com.zwy.utils.Utils;
import com.zwy.widget.adapter.BaseAdapter;
import com.zwy.widget.adapter.BaseAdapter.OnItemLoading;

public class MainActivity extends BaseActivity implements OnItemClickListener{

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
		list.setOnItemClickListener(this);
	}

	private void initData() {
		desc.setText(R.string.desc_main);
		mAdapter = new BaseAdapter<ActionEntity>(getItems(), new ViewLoading(this));
		list.setAdapter(mAdapter);
	}

	private List<ActionEntity> getItems(){
		List<ActionEntity> list = new ArrayList<ActionEntity>();
		list.add(new ActionEntity(list.size(), getString(R.string.name_cycle), getString(R.string.desc_cycle), CycleActivity.class));
		list.add(new ActionEntity(list.size(), getString(R.string.name_service), getString(R.string.desc_service), ServiceActivity.class));
		list.add(new ActionEntity(list.size(), getString(R.string.name_broadcast), getString(R.string.desc_broadcast), BroadcastActivity.class));
		list.add(new ActionEntity(list.size(), getString(R.string.name_process), getString(R.string.desc_process), ProcessActivity.class).putString(Common.BUNDLE_PROCESS, Utils.getProcessName(getApplicationContext())));
		list.add(new ActionEntity(list.size(), getString(R.string.name_fragment), getString(R.string.desc_fragment), FragmentActivity.class));
		list.add(new ActionEntity(list.size(), getString(R.string.name_view), getString(R.string.desc_view), ViewActivity.class));
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ActionEntity entity = mAdapter.getItem(position);
		Intent intent = new Intent(this, entity.getClazz());
		if (entity.getBundle() != null)
			intent.putExtras(entity.getBundle());

		startActivity(intent);
	}

	private static class ViewLoading implements OnItemLoading<ActionEntity> {

		private Context mContext;
		public ViewLoading(Context ctx) {
			this.mContext = ctx.getApplicationContext();
		}

		@SuppressLint("InflateParams") 
		@Override
		public View getView(View view, ActionEntity item) {
			Holder holder = null;
			if (view != null && view.getTag() != null && view.getTag() instanceof Holder) {
				holder = (Holder) view.getTag();
			} else {
				view = LayoutInflater.from(mContext).inflate(R.layout.item_view_action, null, false);
				holder = new Holder();
				view.setTag(holder);
			}
			holder.id = (TextView) view.findViewById(R.id.action_id);
			holder.title = (TextView) view.findViewById(R.id.action_title);
			holder.desc = (TextView) view.findViewById(R.id.action_desc);
			holder.next = (ImageView) view.findViewById(R.id.action_next);
			holder.id.setText(item.getId());
			holder.title.setText(item.getName());
			holder.desc.setText(item.getDesc());
			holder.next.setImageResource(R.drawable.icon_next);
			return view;
		}

	}

	private static class Holder {
		public TextView id;
		public TextView title;
		public TextView desc;
		public ImageView next;
	}
}