package com.xyren.datastructure.tree.unionfind;

/**
 * 并查集实现 - v4
 * 针对rank优化，unionElement时，深度低的树指向深度高的树
 * 这里是rank，排名、序的意思，不是绝对的高度
 *
 * @author renxiaoya
 * @date 2020-12-06
 **/
public class UnionFind4 implements UF {

    private int[] parent;

    /**
     * rank[i]表示以i为根的集合中元素的高度rank
     */
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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

        // 根据两个元素所在的树的rank不同判断合并的方向
        // 将rank低的集合指向rank高的集合
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;

        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // rank[qRoot] == rank[pRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
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
