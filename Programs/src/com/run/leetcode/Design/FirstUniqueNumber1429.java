package com.run.leetcode.Design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1429. First Unique Number
 * Medium
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 *     FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 *     int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 *     void add(int value) insert value to the queue.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * Output:
 * [null,2,null,2,null,3,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(5);            // the queue is now [2,3,5,5]
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3
 * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // return -1
 *
 * Example 2:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
 * [[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
 * Output:
 * [null,-1,null,null,null,null,null,17]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
 * firstUnique.showFirstUnique(); // return -1
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
 * firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
 * firstUnique.showFirstUnique(); // return 17
 *
 * Example 3:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique"]
 * [[[809]],[],[809],[]]
 * Output:
 * [null,809,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([809]);
 * firstUnique.showFirstUnique(); // return 809
 * firstUnique.add(809);          // the queue is now [809,809]
 * firstUnique.showFirstUnique(); // return -1
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 10^5
 *     1 <= nums[i] <= 10^8
 *     1 <= value <= 10^8
 *     At most 50000 calls will be made to showFirstUnique and add.
 */
public class FirstUniqueNumber1429 {

    /**
     * Complexity Analysis
     *
     * Let KKK be the length of the initial array passed into the constructor.
     *
     * Let NNN be the total number of items added onto the queue so far (including those from the constructor).
     *
     *     Time complexity :
     *
     *         constructor: O(K)O(K)O(K).
     *
     *         For each of the KKK numbers passed into the constructor, we're making a call to add(). As we will determine below, each call to add() has a cost of O(1)O(1)O(1). Therefore, for the KKK numbers added in the constructor, we get a total cost of K?O(1)=O(K)K \cdot O(1) = O(K)K?O(1)=O(K).
     *
     *         add(): O(1)O(1)O(1).
     *
     *         Like before, we're performing a series of O(1)O(1)O(1) operations when add() is called. Additionally, we're also removing the number from the queue if it had been unique, but now no longer is. Seeing as the queue is implemented as a LinkedHashSet, the cost of this removal is O(1)O(1)O(1).
     *
     *         Therefore, we get an O(1)O(1)O(1) time complexity for add().
     *
     *         showFirstUnique(): O(1)O(1)O(1).
     *
     *         This time around, the implementation for showFirstUnique() is straightforward. It simply checks whether or not the queue contains any items, and if it does, it returns the first one (without removing it). This has a cost of O(1)O(1)O(1).
     *
     *     Space complexity : O(N)O(N)O(N).
     *
     *     Each number is stored up to once in the LinkedHashSet, and up to once in the HashMap. Therefore, we get an overall space complexity of O(N)O(N)O(N).
     */
    private Set<Integer> setQueue = new LinkedHashSet<>();
    private Map<Integer, Boolean> isUnique = new HashMap<>();
// Set<Integer> isUnique = new HashSet<>();
    public FirstUniqueNumber1429(int[] nums) {
        for (int num : nums) {
            this.add(num);
        }
    }

    public int showFirstUnique() {
        // If the queue contains values, we need to get the first one from it.
        // We can do this by making an iterator, and getting its first item.
        if (!setQueue.isEmpty()) {
            return setQueue.iterator().next();
        }
        return -1;
    }

    public void add(int value) {
        // Case 1: This value is not yet in the data structure.
        // It should be ADDED.
        if (!isUnique.containsKey(value)) {
            isUnique.put(value, true);
            setQueue.add(value);
            // Case 2: This value has been seen once, so is now becoming
            // non-unique. It should be REMOVED.
        } else if (isUnique.get(value)) {//this condition ensures, that we do the put and remove operation only one time when the
            //first duplicate is encountered and not when the num is added 3rd/4th time
            isUnique.put(value, false);
            setQueue.remove(value);
        }
    }
}
/**

 class FirstUnique {
 Set<Integer> isUnique = new HashSet<>();
 Set<Integer> q = new LinkedHashSet<>();

 public FirstUnique(int[] nums) {
 for(int n: nums){
 this.add(n);
 }
 }

 public int showFirstUnique() {
 if(!q.isEmpty()){
 return q.iterator().next();
 }
 return -1;
 }

 public void add(int value) {
 if(!isUnique.contains(value)){
 isUnique.add(value);
 q.add(value);
 }
 else {
 q.remove(value);
 }
 }
 }

 **/

class FirstUniqueNumber{
    //https://leetcode.com/problems/first-unique-number/discuss/601107/JavaPython-3-DoublyLinkedList-and-LinkedHashSetdict-O(n)-2-neat-codes-w-analysis.
    class DLLNode {
        int val;
        DLLNode next;
        DLLNode prev;
        public DLLNode(int val) {
            this.val = val;
        }
    }
    HashMap<Integer, DLLNode> nodeMap;
    DLLNode head;
    DLLNode tail;

    public FirstUniqueNumber(int[] nums) {
        nodeMap = new HashMap<>();
        head = new DLLNode(0);
        tail = new DLLNode(0);
        head.next = tail;
        tail.prev = head;
        for(int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        return head.next == tail ? -1 : head.next.val;
    }

    public void add(int value) {
        // DLLNode newNode = new DLLNode(value);
        // DLLNode curNode = nodeMap.putIfAbsent(value, newNode);
        // if(curNode == null) {
        //     add(newNode);
        // } else {
        //     remove(newNode);
        // }
        if(nodeMap.containsKey(value)){
            remove(nodeMap.get(value));
        } else {
            DLLNode newNode = new DLLNode(value);
            nodeMap.put(value, newNode);
            add(newNode);
        }
    }

    private void remove(DLLNode node) {
        if(node.prev == null && node.next == null) {
            return;
        }

        DLLNode prevNode = node.prev;
        DLLNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.prev = null;
        node.next = null;
    }

    private void add(DLLNode node) {
        // if(tail == null || tail.prev == null) {
        //     return;
        // }
        DLLNode prevNode = tail.prev;

        prevNode.next = node;
        node.prev = prevNode;

        node.next = tail;
        tail.prev = node;
    }
}