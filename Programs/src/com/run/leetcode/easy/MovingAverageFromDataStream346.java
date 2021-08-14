package com.run.leetcode.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MovingAverageFromDataStream346 {
    public static void main(String[] args) {

    }
}

/**
 *

 Complexity

 Time Complexity: O(1)\mathcal{O}(1)O(1), as we explained in intuition.
 Space Complexity: O(N)\mathcal{O}(N)O(N), where NNN is the size of the moving window.

 */
class MovingAverageDeque {
    int size, windowSum = 0, count = 0;
    Deque queue = new ArrayDeque<Integer>();

    public MovingAverageDeque(int size) {
        this.size = size;
    }

    public double next(int val) {
        ++count;
        // calculate the new sum by shifting the window
        queue.add(val);
        int tail = count > size ? (int)queue.poll() : 0;

        windowSum = windowSum - tail + val;

        return windowSum * 1.0 / Math.min(size, count);
    }
}

/**
 * Complexity
 *
 *     Time Complexity: O(1)\mathcal{O}(1)O(1), as we can see that there is no loop in the next(val) function.
 *
 * Circular queue
 *     Space Complexity: O(N)\mathcal{O}(N)O(N), where NNN is the size of the circular queue.
 */
class MovingAverage {
    int size, head = 0, windowSum = 0, count = 0;
    int[] queue;
    public MovingAverage(int size) {
        this.size = size;
        queue = new int[size];
    }

    public double next(int val) {
        ++count;
        // calculate the new sum by shifting the window
        int tail = (head + 1) % size;
        windowSum = windowSum - queue[tail] + val;
        // move on to the next head
        head = (head + 1) % size;
        queue[head] = val;
        return windowSum * 1.0 / Math.min(size, count);
    }
}

/**
 *
 Time Complexity: O(N)\mathcal{O}(N)O(N) where NNN is the size of the moving window, since we need to retrieve NNN elements from the queue at each invocation of next(val) function.
 Space Complexity: O(M)\mathcal{O}(M)O(M), where MMM is the length of the queue which would grow at each invocation of the next(val) function.


 */
class MovingAverageArrayList {
    int size;
    List queue = new ArrayList<Integer>();
    public MovingAverageArrayList(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        // calculate the sum of the moving window
        int windowSum = 0;
        for(int i = Math.max(0, queue.size() - size); i < queue.size(); ++i)
            windowSum += (int)queue.get(i);

        return windowSum * 1.0 / Math.min(queue.size(), size);
    }
}