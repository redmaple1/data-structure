package com.xyren.datastructure.tree.unionfind;

/**
 * 并查集实现 - v2
 * parent 0 1 2 3 4 5 6 7 8 9
 * ---------------------
 * 1 1 0 3 4 5 6 6 5 9
 * <p>
 * Operate            |  时间复杂度
 * unionElement(p,q) --> O(h) h: height of tree
 * isConnected(p,q)  --> O(h) h: height of tree
 *
 * @author renxiaoya
 * @date 2020-12-06
 **/
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
    }

    /**
     * 查找元素p对应的集合编号
     *
     * @param p 元素p
     * @return p对应的元素编号
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        //不断地查找根节点
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
