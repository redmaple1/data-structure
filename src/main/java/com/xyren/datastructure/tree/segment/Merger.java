package com.xyren.datastructure.tree.segment;

/**
 * 用于处理具体业务的融合接口
 *
 * @author renxiaoya
 * @date 2020-03-16
 **/
public interface Merger<E> {

    E merge(E a, E b);

}
