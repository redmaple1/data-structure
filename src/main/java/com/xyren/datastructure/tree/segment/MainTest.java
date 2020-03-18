package com.xyren.datastructure.tree.segment;

/**
 * 测试类
 *
 * @author renxiaoya
 * @date 2020-03-16
 **/
public class MainTest {
    public static void main(String[] args) {
        Integer[] data = {-1, 4, -2, 0, 3, 1};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(data, Integer::sum);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(1, 4));
        System.out.println(segTree.query(2, 5));

    }
}
