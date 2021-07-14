package com.run.leetcode.datastructures;

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


    private Set<Integer> setQueue = new LinkedHashSet<>();
    private Map<Integer, Boolean> isUnique = new HashMap<>();

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
