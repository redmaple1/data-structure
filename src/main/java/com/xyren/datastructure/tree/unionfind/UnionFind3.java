package com.xyren.datastructure.tree.unionfind;

/**
 * 并查集实现 - v3
 * 与v2相比，优化了size，unionElement时，将节点数少的树指向节点数多的树
 *
 * @author renxiaoya
 * @date 2020-12-06
 **/
public class UnionFind3 implements UF {

    private int[] parent;

    /**
     * sz[i]表示以i为根的集合中元素的个数
     */
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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

        // 若pRoot所在元素比qRoot所在元素少，将pRoot指向qRoot，并维护sz
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            // 否则将qRoot指向pRoot
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
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
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
