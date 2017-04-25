package com.zeki.rvadapter.delegate;

import android.support.v4.util.SparseArrayCompat;

import com.zeki.rvadapter.BaseViewHolder;

/**
 * Package Name:com.zeki.rvadapter.delegate
 * Author: zk
 * Time: 2017/3/14.10:17
 * Function:
 *
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class ItemViewDelegateManager<T> {

    //使用SparseArrayCompat来存放和管理ItemViewDelegate
    //优点是节省最高50%缓存，缺点是局限于Key:Integer;value:object
    private SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public ItemViewDelegateManager() {
    }

    /**
     * 添加Delegate
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = this.delegates.size();
        if(delegate != null) {
            this.delegates.put(viewType, delegate);
        }
        return this;
    }

    /**
     * 获取item某一位置position的ViewType
     * 位置在哪就是哪种类型  如 header、footer
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(T item, int position) {
        int delegatesCount = this.delegates.size();
        for(int i = delegatesCount - 1; i >= 0; --i) {
            //从后往前遍历
            ItemViewDelegate delegate = (ItemViewDelegate)this.delegates.valueAt(i);
            if(delegate.isForViewType(item, position)) {
                //如果匹配成功返回类型
                return this.delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    /**
     * ViewHolder转换
     * MultiItemTypeiAdapter中调用完成数据和事件的绑定
     * 首先判断delegate的类型  然后调用对应类型的convert方法完成绑定
     * @param holder
     * @param item
     * @param position
     */
    public void convert(BaseViewHolder holder, T item, int position) {
        int delegatesCount = this.delegates.size();

        for(int i = 0; i < delegatesCount; ++i) {
            ItemViewDelegate delegate = (ItemViewDelegate)this.delegates.valueAt(i);
            if(delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    /**
     * 根据viewType返回ItemViewDelegate
     * @param viewType
     * @return
     */
    public ItemViewDelegate getItemViewDelegate(int viewType) {
        return (ItemViewDelegate)this.delegates.get(viewType);
    }
}

