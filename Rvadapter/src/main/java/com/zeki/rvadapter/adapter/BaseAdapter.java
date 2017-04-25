package com.zeki.rvadapter.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.zeki.rvadapter.BaseViewHolder;
import com.zeki.rvadapter.RecyclerViewOnItemClickListener;
import com.zeki.rvadapter.RecyclerViewOnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Package Name:com.zeki.rvadapter.adapter
 * Author: zk
 * Time: 2017/3/14.10:13
 * Function:
 * BaseAdapter
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public abstract class BaseAdapter<E, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> implements IAdapter<E> {
    private static final String TAG = "BaseAdapter";
    private List<E> mEntities;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected RecyclerViewOnItemClickListener mOnItemClickListener;
    protected RecyclerViewOnItemLongClickListener mRecyclerViewOnItemLongClickListener;

    public BaseAdapter(Context context) {
        this(context, (List)null);
    }

    public BaseAdapter(Context context, List<E> entities) {
        this.mContext = context;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mEntities = entities;
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RecyclerViewOnItemLongClickListener longClickListener) {
        this.mRecyclerViewOnItemLongClickListener = longClickListener;
    }

    public E getItem(int i) {
        return this.mEntities.get(i);
    }

    public void setEntities(List<E> entities) {
        this.mEntities = entities;
    }

    public void addEntities(List<E> entities) {
        if(this.mEntities == null) {
            this.mEntities = entities;
        } else {
            this.mEntities.addAll(entities);
        }

    }

    public List<E> getEntities() {
        return this.mEntities;
    }

    public void addEntity(E entity) {
        if(this.mEntities == null) {
            this.mEntities = new ArrayList();
        }
        this.mEntities.add(entity);
    }

    public void removeEntity(E entiry) {
        if(this.mEntities != null) {
            this.mEntities.remove(entiry);
        }
    }

    public void removeEntities(List<E> entities) {
        if(this.mEntities != null) {
            this.mEntities.removeAll(entities);
        }
    }

    public void clearEntities() {
        if(this.mEntities != null) {
            this.mEntities.clear();
        }
    }

    public int getItemCount() {
        return this.mEntities == null?0:this.mEntities.size();
    }

    public void addEntity(int index, E entity) {
        this.mEntities.add(index, entity);
    }

    public void addEntities(int index, List<E> entities) {
        if(this.mEntities == null) {
            this.mEntities = entities;
        } else {
            this.mEntities.addAll(index, entities);
        }

    }

    public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{
        private int mMaxCount;
        public GridSpanSizeLookup(int maxCount){
            this.mMaxCount = maxCount;
        }
        @Override
        public int getSpanSize(int position) {
                if (position<1)return mMaxCount;

                int i = position - 1 - mEntities.size();
                if (i >= 0) {
                    return mMaxCount;
                }

            return 1;
        }
    }

    public GridSpanSizeLookup obtainGridSpanSizeLookUp(int maxCount){
        return new GridSpanSizeLookup(maxCount);
    }
}
