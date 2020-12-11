package com.xyren.datastructure.tree.unionfind;

/**
 * UnionFind并查集接口
 *
 * @author renxiaoya
 * @date 2020-12-06
 **/
public interface UF {

    /**
     * 获取并查集中有多少元素
     *
     * @return 并查集中有多少元素
     */
    int getSize();

    /**
     * 两个元素是否相连
     *
     * @param p 元素编号p
     * @param q 元素编号q
     * @return 是否相连
     */
    boolean isConnected(int p, int q);

    /**
     * 将两个元素并在一起
     *
     * @param p 元素编号p
     * @param q 元素编号q
     */
    void unionElement(int p, int q);

}
