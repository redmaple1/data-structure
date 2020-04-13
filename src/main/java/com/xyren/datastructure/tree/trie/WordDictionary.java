package com.xyren.datastructure.tree.trie;

import java.util.TreeMap;

/**
 * Leetcode 211 : 简单的模式匹配
 *
 * @author renxiaoya
 * @date 2020-04-13
 **/
public class WordDictionary {

    private class Node {
        private boolean beWord;
        private TreeMap<Character, Node> next;

        public Node(boolean beWord) {
            this.beWord = beWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    public WordDictionary() {
        this.root = new Node();
    }

    public void addWord(String word) {
        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.beWord = true;
    }

    /**
     * 查询 word
     * 模式匹配，比如 word = d..r
     *
     * @param word 模式匹配字符串
     * @return 是否存在
     */
    public boolean search(String word) {
        return match(this.root, word, 0);
    }

    private boolean match(Node node, String word, int index) {

        if (index == word.length()) {
            return node.beWord;
        }

        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

}
