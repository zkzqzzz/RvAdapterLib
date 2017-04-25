package com.zeki.rvadapter.adapter;

import java.util.List;

/**
 * Package Name:com.zeki.rvadapter.adapter
 * Author: zk
 * Time: 2017/3/14.10:13
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface IAdapter<E> {
    void addEntity(E var1);

    void removeEntity(E var1);

    void removeEntities(List<E> var1);

    void addEntities(List<E> var1);
}
