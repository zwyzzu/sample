package com.zhangwy.sample.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zhangwy.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张维亚(zhangwy) on 2016/12/13 下午2:11.
 * Updated by zhangwy on 2016/12/13 下午2:11.
 * Description RecyclerView 的适配器
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class RadioListAdapter<T> extends RecyclerView.Adapter<RadioListAdapter.RecyclerViewHolder> implements View.OnClickListener {
    private final int ADD_END = -1;
    private List<T> array = new ArrayList<>();
    private OnItemCheckedChangedListener<T> itemCheckedChangedListener;
    private OnItemLoading<T> itemLoading;
    private T selected;

    public RadioListAdapter(T select, OnItemLoading<T> itemLoading) {
        super();
        this.selected = select;
        this.itemLoading = itemLoading;
    }

    public void setItemCheckedChangedListener(OnItemCheckedChangedListener<T> listener) {
        this.itemCheckedChangedListener = listener;
    }

    public void setSelected(T select) {
        this.checked(select);
    }

    public void setData(List<T> list) {
        if (this.array == list)
            return;
        this.array.clear();
        if (Util.isEmpty(list)) {
            this.notifyDataSetChanged();
            return;
        }
        this.array.addAll(list);
        if (this.selected == null) {
            this.notifyDataSetChanged();
        } else {
            this.checked(this.selected);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.itemLoading.getItemViewType(getItem(position), position);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(this.itemLoading.onCreateView(parent, viewType), this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bindPosition(position);
        T entity = getItem(position);
        boolean isChecked = entity == this.selected;
        this.itemLoading.onLoadView(holder.itemView, holder.getItemViewType(), position, entity, isChecked);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    public T getItem(int position) {
        if (Util.isEmpty(this.array) || position > this.array.size() - 1) {
            return null;
        }
        return this.array.get(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        T entity = this.array.get(position);
        T oldEntity = checked(entity);
        if (this.itemCheckedChangedListener == null) {
            return;
        }

        this.itemCheckedChangedListener.onCheckedChanged(v, getItemViewType(position), entity, oldEntity);
    }

    private T checked(T newEntity) {
        if (!this.array.contains(newEntity)) {
            return this.selected;
        }
        T oldEntity = this.selected;
        this.selected = newEntity;
        this.itemLoading.onSort(this.array);
        this.array.remove(newEntity);
        this.array.add(0, newEntity);
        this.itemLoading.onSliding();
        this.notifyDataSetChanged();
        return oldEntity;
    }

    public static abstract class OnItemLoading<E> {
        public int getItemViewType(E entity, int position) {
            return 0;
        }

        public abstract View onCreateView(ViewGroup parent, int viewType);

        public abstract void onLoadView(View root, int viewType, int position, E entity, boolean isChecked);

        public void onSort(List<E> array) {
        }

        public void onSliding() {
        }
    }

    public interface OnItemCheckedChangedListener<E> {
        /**
         * Called when the checked state of a compound button has changed.
         * <p/>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param newView     The view within the AdapterView that was clicked (this
         *                    will be a view provided by the adapter)Ø
         * @param newViewType The view type of the new View.
         * @param newEntity   The entity within the adapter data that was clicked item
         * @param oldEntity   old entity within the adapter data that was selected item
         */
        void onCheckedChanged(View newView, int newViewType, E newEntity, E oldEntity);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder(View view, View.OnClickListener listener) {
            super(view);
            this.itemView.setOnClickListener(listener);
        }

        public void bindPosition(int position) {
            this.itemView.setTag(position);
        }
    }
}