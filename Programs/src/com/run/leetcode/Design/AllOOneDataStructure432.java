package com.run.leetcode.Design;

import java.util.HashMap;

/**
 * 432. All O`one Data Structure
 * Hard
 *
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 *     AllOne() Initializes the object of the data structure.
 *     inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 *     dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 *     getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 *     getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 *
 * Constraints:
 *
 *     1 <= key.length <= 10
 *     key consists of lowercase English letters.
 *     It is guaranteed that for each call to dec, key is existing in the data structure.
 *     At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
public class AllOOneDataStructure432 {
    class Node{
        Node prev, next;
        String s;
        int count;
        public Node(String str, int c) {
            s = str;
            count = c;
        }
    }
    HashMap<String, Node> map;
    Node head, tail;

    /** Initialize your data structure here. */
    public AllOOneDataStructure432() {
        map = new HashMap<>();
        head = new Node("", 0);
        tail = new Node("", 0);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        node.prev.next = next;
        node.next.prev = prev;
    }

    private void insertNode(Node node, Node prev) {
        Node next = prev.next;
        prev.next = node;
        node.prev = prev;
        node.next = next;
        next.prev = node;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)) {
            Node node = new Node(key, 1);
            map.put(key, node);
            insertNode(node, head);
        } else {
            Node node = map.get(key);
            removeNode(node);
            Node current = node;
            while(current.count == node.count) current = current.next;
            node.count++;
            insertNode(node, current.prev);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) return;
        Node node = map.get(key);
        if(node.count == 1) {
            map.remove(node.s);
            removeNode(node);
        } else {
            removeNode(node);
            Node current = node;
            while(current.count == node.count) current = current.prev;
            node.count--;
            insertNode(node, current);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.prev.s;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next.s;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
