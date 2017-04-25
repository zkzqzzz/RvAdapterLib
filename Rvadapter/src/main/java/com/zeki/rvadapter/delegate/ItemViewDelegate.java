package com.zeki.rvadapter.delegate;

import com.zeki.rvadapter.BaseViewHolder;

/**
 * Package Name:com.zeki.rvadapter.delegate
 * Author: zk
 * Time: 2017/3/14.10:14
 * Function:
 * 多种ItemType(ItemViewDelegate)
 * viewHolder是通用的，唯一的依赖是LayoutId。
 * onCreateViewHolder中根据itemView去生成不同的ViewHolder
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface ItemViewDelegate<E> {

    /**
     * 返回准备填充itemView的LayoutId
     * @return
     */
    int getItemViewLayoutId();


    /**
     * 数据和事件的绑定 超类会给出具体的
     * @param baseViewHolder
     * @param entities
     * @param i
     */
    void convert(BaseViewHolder baseViewHolder, E entities, int i);

    /**
     * 判断entities的类型是不是和当前ViewType匹配正确
     * @param entities
     * @param i
     * @return
     */
    boolean isForViewType(E entities, int i);

    /**
     *
     * @param baseViewHolder
     */
    void onCreateViewHolder(BaseViewHolder baseViewHolder);

}