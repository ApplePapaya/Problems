package com.run.leetcode.Design;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * Medium
 *
 * 9503
 *
 * 377
 *
 * Add to List
 *
 * Share
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache146 {

        final Node head = new Node();
        final Node tail = new Node();
        int capacity;
        Map<Integer, Node> node_map;

        public LRUCache146(int capacity) {
            node_map = new HashMap<>(capacity);
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;

        }

        public int get(int key) {
            int result = -1;

            Node node = node_map.get(key);
            if(node != null)
            {
                result = node.val;
                remove(node);
                add(node);
            }
            return result;
        }

        public void put(int key, int value) {
            Node node = node_map.get(key);
            if(node != null)
            {
                remove(node);
                node.val = value;

                add(node);
            }else{
                //Check capacity
                if(node_map.size() == capacity)
                {
                    node_map.remove(tail.prev.key);
                    remove(tail.prev);
                }

                node = new Node();
                node.key = key;
                node.val = value;
                node_map.put(key, node);
                add(node);
            }
        }

        public void remove(Node node){
            Node next = node.next;
            Node prev = node.prev;
            prev.next = next;
            next.prev = prev;
        }

        public void add(Node node){
            Node head_next = head.next;
            node.next = head_next;
            head_next.prev = node;

            head.next = node;
            node.prev =head;

        }
    }

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */