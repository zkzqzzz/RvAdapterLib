package com.zeki.rvadapter.delegate;

/**
 * Package Name:com.zeki.rvadapter.delegate
 * Author: zk
 * Time: 2017/3/14.10:18
 * Function:
 * 当页面内容只有一种情形时使用这个
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public abstract class SingleItemViewDelegate<E> implements ItemViewDelegate<E> {
    private int mLayoutId;

    public SingleItemViewDelegate(int layoutId) {
        this.mLayoutId = layoutId;
    }

    /**
     * 返回负责填充item的LayoutId
     * @return
     */
    public int getItemViewLayoutId() {
        return this.mLayoutId;
    }

    /**
     * 判断ViewType是不是和微博类型匹配正确
     * @param item
     * @param position
     * @return
     */
    public boolean isForViewType(E item, int position) {
        return true;
    }

}
