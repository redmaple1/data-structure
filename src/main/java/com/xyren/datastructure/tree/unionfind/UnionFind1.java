package com.xyren.datastructure.tree.unionfind;

/**
 * 并查集实现 - v1
 * <p>
 * 0 1 2 3 4 5 6 7 8 9
 * -------------------
 * 0 1 0 1 0 1 0 1 0 1
 * <p>
 * OP                 |   时间复杂度
 * unionElement(p,q) --> O(n)
 * isConnected(p,q)  --> O(1)
 *
 * @author renxiaoya
 * @date 2020-12-06
 **/
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    /**
     * 查找元素p对应的集合编号
     *
     * @param p 元素p
     * @return 集合编号
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }
}
