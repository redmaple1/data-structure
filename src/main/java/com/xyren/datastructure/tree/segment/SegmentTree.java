package com.xyren.datastructure.tree.segment;

/**
 * 线段树
 *
 * @author renxiaoya
 * @date 2020-03-12
 **/
public class SegmentTree<E> {

    private Merger<E> merger;

    /**
     * 树结构 - 这里使用数组
     */
    private E[] tree;
    /**
     * 真正的数据
     */
    private E[] data;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 通过归纳，4倍的data长度用来创建线段树比较合适，可能会有空间的浪费，但是可以接受
        tree = (E[]) new Object[4 * arr.length];
        this.merger = merger;

        // 创建线段树
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 返回区间[queryL...queryR]的值
     *
     * @param queryL 左边界
     * @param queryR 右边界
     * @return 返回值
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 将 index 位置的节点更新成 e
     *
     * @param index 待更新节点的索引值
     * @param e     要更新成的值
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        //首先更新 data 对应索引的值
        data[index] = e;
        //更新 tree 数组（线段树）中所有关联的值
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以 treeIndex 为根的线段树中更新 index 的值为 e
     *
     * @param treeIndex 正在处理的线段树的根节点索引
     * @param l         左边界
     * @param r         右边界
     * @param index     待更新的索引值
     * @param e         要更新成的值
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            //index 比中间节点位置还靠右，去右子树操作
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            //否则去左子树操作
            set(leftTreeIndex, l, mid, index, e);
        }
        //set过相应节点的值后不要忘记合并左右子树的结果，否则被操作的节点的父辈节点值将不会更新
        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 在以 treeIndex 为根的线段树[l...r]中的范围里，搜索区间[queryL...queryR]的值
     *
     * @param treeIndex 正在处理的树的根节点索引
     * @param l         正在处理的线段树的左边界
     * @param r         正在处理的线段树的右边界
     * @param queryL    查询左边界
     * @param queryR    查询右边界
     * @return 搜索到的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 在 treeIndex 的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex 正在创建的线段树的根节点
     * @param l         表示区间的左边界
     * @param r         表示区间的右边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取数据大小
     *
     * @return 数据大小
     */
    public int getSize() {
        return data.length;
    }

    /**
     * 根据索引获得数据
     *
     * @param index 索引值
     * @return 数据
     */
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回一个索引表示的元素的左孩子的索引
     *
     * @param index 索引值
     * @return 左孩子的索引
     */
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回一个索引表示的元素的右孩子的索引
     *
     * @param index 索引值
     * @return 右孩子的索引
     */
    public int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
