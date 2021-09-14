package com.run.leetcode.intervalProblems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 *
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 *
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1 <= events.length <= 105
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 105
 */
public class MaximumNumberOfEventsThatCanBeAttended1353 {

    /**

     #1. Sort the events based on starting day of the event
     #2. Now once you have this sorted events, every day check what are the events that can start today
     #3. for all the events that can be started today, keep their ending time in heap.

     - Wait why we only need ending times ?
     i) from today onwards, we already know this event started in the past and all we need to know is when this event will finish
     ii) Also, another key to this algorithm is being greedy, meaning I want to pick the event which is going to end the soonest.
     - So how do we find the event which is going to end the soonest?
     i) brute force way would be to look at all the event's ending time and find the minimum, this is probably ok for 1 day but as we can only attend 1 event a day,
     we will end up repeating this for every day and that's why we can utilize heap(min heap to be precise) to solve the problem of finding the event with earliest ending time
     #4. There is one more house cleaning step, the event whose ending time is in the past, we no longer can attend those event
     #5. Last but very important step, Let's attend the event if any event to attend in the heap.

     The first version's runtime should be O(D + NlogN) since though loop through 1 to D, every event can only be inserted to the heap and deleted once which is O(NlogN).
     Looping through 1 to D is O(D), so should be O(max(D, NlogN)) or O(D + NlogN).
     **/
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // sort events increasing by start time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ans = 0, i = 0, n = events.length;
        for (int d = 1; d <= 100000; d++) {
            while (i < n && events[i][0] == d) { // Add new events that can attend on day `d`
                minHeap.add(events[i++][1]);
            }
            while (!minHeap.isEmpty() && minHeap.peek() < d) { // Remove events that are already closed
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) { // Use day `d` to attend to the event that closes earlier
                ans++;
                minHeap.poll();
            }

            if (minHeap.isEmpty() && i >= n){
                break;
            }
        }
        return ans;
    }

    /**
     * Same idea, though people worry about the time complexity of iteration all days.
     * We can easily improve it to strict O(nlogn)
     * @param A
     * @return
     */
    public int maxEvents2(int[][] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, d = 0, n = A.length;
        while (!pq.isEmpty() || i < n) {
            if (pq.isEmpty())
                d = A[i][0];
            while (i < n && A[i][0] <= d)
                pq.offer(A[i++][1]);
            pq.poll();
            ++res; ++d;
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
        }
        return res;
    }
}
