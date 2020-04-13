package com.xyren.datastructure.tree.trie;

import java.util.TreeMap;

/**
 * Trie 结构
 *
 * @author renxiaoya
 * @date 2020-04-07
 **/
public class Trie {

    /**
     * Trie 中的节点
     */
    private class Node {
        /**
         * 是否表示一个单词
         */
        private boolean beWord;

        /**
         * map 记录某个字符对应的节点
         */
        private TreeMap<Character, Node> next;

        public Node(boolean beWord) {
            this.beWord = beWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    /**
     * Trie 的根节点
     */
    private Node root;

    /**
     * Trie 中存储的单词数量
     */
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * 获取 Trie 中存储的单词数量
     *
     * @return Trie 中存储的单词数量
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 向 Trie 中添加一个单词 word
     *
     * @param word 添加的单词
     */
    public void add(String word) {

        Node cur = this.root;
        //遍历要添加的单词
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //当前 node 的下一个节点不包含 key=c 的节点，创建一个节点 put 进 map
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            //指针后移
            cur = cur.next.get(c);
        }

        //遍历完成后，当前节点之前是否已经被标识为是单词，不是的话再标识，并将 size 加 1
        //这里是一个陷阱，如果不判断，可能出现 size 重复添加的问题
        if (!cur.beWord) {
            cur.beWord = true;
            this.size++;
        }
    }

    /**
     * 查询单词 word 是否存在于 Trie 中
     *
     * @param word 查询的单词
     * @return 是否存在
     */
    public boolean contains(String word) {

        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        //需要返回当前节点是否是 Trie 中的一个单词，不能仅返回 true。
        //因为可能在有 panda 单词的 Trie 中查 pan，pan 不存在的情况
        return cur.beWord;
    }

    /**
     * 查找是否在 Trie 中有单词以 prefix 为前缀
     *
     * @param prefix 前缀字符串
     * @return 是否存在
     */
    public boolean isPrefix(String prefix) {
        Node cur = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
