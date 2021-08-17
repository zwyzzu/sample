package com.zhangwy.sample.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.sample.entity.HomeSampleItem;
import com.zhangwy.widget.recycler.RecyclerAdapter;
import com.zhangwy.widget.recycler.RecyclerDivider;
import com.zhangwy.widget.recycler.WRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings({"FieldCanBeLocal", "NullableProblems"})
public class MainActivity extends BaseActivity implements RecyclerAdapter.OnItemClickListener<HomeSampleItem> {

    private final int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSampleItems();
    }

    private void initSampleItems() {
        final WRecyclerView<HomeSampleItem> view = findViewById(R.id.main_recycler);
        view.setGridLayoutManager(this.SPAN_COUNT, WRecyclerView.VERTICAL, false);
        view.setOnItemClickListener(this);
        view.addItemDecoration(this.createDivider(GridLayoutManager.VERTICAL));
        view.addItemDecoration(this.createDivider(GridLayoutManager.HORIZONTAL));
        final ArrayList<HomeSampleItem> items = this.getSampleItems();
        view.loadData(items, new HomeAdapter());
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(items, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(items, i, i - 1);
                    }
                }
                view.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
//
//            @Override
//            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
//                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
//                }
//                super.onSelectedChanged(viewHolder, actionState);
//            }
//
//            @Override
//            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                super.clearView(recyclerView, viewHolder);
//                viewHolder.itemView.setBackgroundColor(0);
//            }
        });
        helper.attachToRecyclerView(view);
    }

    private RecyclerView.ItemDecoration createDivider(int orientation) {
        Resources resources = getResources();
        int color = resources.getColor(android.R.color.transparent);
        return RecyclerDivider.create(this, orientation, 32, color);
    }

    @Override
    public void onItemClick(View view, int viewType, HomeSampleItem entity, int position) {
        startActivity(new Intent(this, entity.getClazz()));
    }

    private static class HomeAdapter extends RecyclerAdapter.OnItemLoading<HomeSampleItem> {

        @Override
        public int getItemViewType(HomeSampleItem entity, int position) {
            return super.getItemViewType(entity, position);
        }

        @Override
        public View onCreateView(ViewGroup parent, int viewType) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sample, parent, false);
        }

        @Override
        public void onLoadView(View root, int viewType, HomeSampleItem entity, int position) {
            ((TextView) root.findViewById(R.id.home_sample_id)).setText(String.valueOf(entity.getCode()));
            ((TextView) root.findViewById(R.id.home_sample_title)).setText(entity.getName());
            ((TextView) root.findViewById(R.id.home_sample_desc)).setText(entity.getDesc());
        }
    }

    private ArrayList<HomeSampleItem> getSampleItems() {
        ArrayList<HomeSampleItem> samples = new ArrayList<>();
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Cycle", getString(R.string.desc_cycle), CycleAActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Scroll Nested", getString(R.string.desc_scroll_nested), ScrollNestedActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "BaiDu Map", getString(R.string.desc_map_baidu), MapBaiDuActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Events", getString(R.string.desc_event), EventActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "System Message", getString(R.string.desc_system_msg), SystemMsgActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Full Window", getString(R.string.desc_full_window), FullWindowActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Download", getString(R.string.desc_download), DownloadActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "GIF", getString(R.string.desc_gif), GifActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "UIVisibility", getString(R.string.desc_uivisibility), UIVisibility.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "ScaleSeekBar", getString(R.string.desc_seekbar), ScaleSeekBarActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Permission Broadcast", getString(R.string.desc_permission), PermissionBroadcastActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Widget", getString(R.string.desc_widget), WidgetActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Spannble", getString(R.string.desc_spannable), SpannableActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "drag", getString(R.string.desc_drag), DragRecyclerActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "drag", getString(R.string.desc_canvas), CanvasActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Flow", getString(R.string.desc_flow), FlowLayoutActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "File", getString(R.string.desc_file), FileActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "NetWork", getString(R.string.desc_network), NetworkActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Ping", getString(R.string.desc_ping), PingActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Alert Window", getString(R.string.desc_alert_window), SmallWindowActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Suspension", getString(R.string.desc_suspension), SuspensionBarActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "AppList", getString(R.string.desc_app_list), AppListActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Location", getString(R.string.desc_location), LocationActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "JSON", getString(R.string.desc_json), JsonActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Permission", getString(R.string.desc_permission_list), PermissionActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Short Cut Badger", getString(R.string.desc_short_cut_badger), ShortCutBadgerActivity.class));
        samples.add(new HomeSampleItem(samples.size() + 1, "Sample Item" + samples.size() + 1, "Read Contact", getString(R.string.desc_read_contact), ReadContactActivity.class));
        return samples;
    }
}