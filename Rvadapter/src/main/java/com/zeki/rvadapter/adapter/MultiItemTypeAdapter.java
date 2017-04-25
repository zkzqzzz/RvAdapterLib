package com.zeki.rvadapter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zeki.rvadapter.BaseViewHolder;
import com.zeki.rvadapter.delegate.ItemViewDelegate;
import com.zeki.rvadapter.delegate.ItemViewDelegateManager;

import java.util.List;

/**
 * Package Name:com.zeki.rvadapter.adapter
 * Author: zk
 * Time: 2017/3/14.10:14
 * Function:
 *  多种ItemViewType
 *      1.复写getItemViewType，根据实体bean的不同返回不同的类型
 *      2.onCreateViewHolder中根据itemView去生成不同的ViewHolder
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class MultiItemTypeAdapter<E> extends BaseAdapter<E, BaseViewHolder> {

    protected ItemViewDelegateManager<E> mItemViewDelegateManager;

    public MultiItemTypeAdapter(Context context) {
        this(context, (List)null);
    }

    public MultiItemTypeAdapter(Context context, List<E> entities) {
        super(context, entities);
        this.mItemViewDelegateManager = new ItemViewDelegateManager();
    }


    /***
     * getItemViewType，根据位置不同返回不同的类型delegate
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return this.mItemViewDelegateManager.getItemViewType(this.getItem(position), position);
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<E> itemViewDelegate) {
        this.mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    /**
     * 将数据绑定到ViewHolder
     * 因为所有的ItemViewDelegate都交给Manager管理
     * 所以涉及到delegate的操作全部交给Manager完成
     *
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        this.convert(holder, this.getItem(position));
    }

    /**
     * ListView中convertView是复用的 RecyclerView中 把ViewHolder作为缓存单元了
     * 然后把convertView作为ViewHolder的成员变量保存在ViewHolder中，也就是说，假设屏幕显示
     * 10个条目，则会创建10个ViewHolder缓存起来，每次复用的是ViewHolder
     * 所以把ListView中的getView改变为了onCreateViewHolder   而holder可以调用getConvertView
     *
     * 根据itemView去生成不同的ViewHolder
     * 创建新View时，被LayoutManager自动调用
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = this.mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();//R.layout.item_weibo
        BaseViewHolder holder = BaseViewHolder.createViewHolder(this.mContext, parent, layoutId);
        //添加点击事件
        this.onViewHolderCreated(holder, holder.getConvertView());
        //调用itemViewDelegate的创建ViewHolder函数
        itemViewDelegate.onCreateViewHolder(holder);
        return holder;
    }

    public void onViewHolderCreated(final BaseViewHolder holder, View itemView) {
        if(this.mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MultiItemTypeAdapter.this.mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }

        if(this.mRecyclerViewOnItemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    return MultiItemTypeAdapter.this.mRecyclerViewOnItemLongClickListener.onItemLongClick(v, holder.getLayoutPosition());
                }
            });
        }

    }


    public void convert(BaseViewHolder holder, E entities) {
        this.mItemViewDelegateManager.convert(holder, entities, holder.getAdapterPosition());
    }



}
