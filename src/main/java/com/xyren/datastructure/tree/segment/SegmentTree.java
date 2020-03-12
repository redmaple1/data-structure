package com.xyren.datastructure.tree.segment;

/**
 * 线段树
 *
 * @author renxiaoya
 * @date 2020-03-12
 **/
public class SegmentTree<E> {

    /**
     * 树结构 - 这里使用数组
     */
    private E[] tree;
    /**
     * 真正的数据
     */
    private E[] data;

    public SegmentTree(E[] arr) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 通过归纳，4倍的data长度用来创建线段树比较合适，可能会有空间的浪费，但是可以接受
        tree = (E[]) new Object[4 * arr.length];
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
     * @param index 索引值
     * @return 左孩子的索引
     */
    public int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 返回一个索引表示的元素的右孩子的索引
     * @param index 索引值
     * @return you'hai'zi
     */
    public int rightChild(int index){
        return 2 * index + 2;
    }

}
