package com.run.leetcode.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStops787 {

    /**
     *
     Time Complexity: O(E?K)O(\text{E} \cdot \text{K})O(E?K) since we can process each edge multiple times depending upon the improvement in the shortest distances. However, the maximum number of times an edge would be processed is bounded by K + 1\text{K + 1}K + 1 since that's the number of levels we are going to explore in this algorithm.
     Space Complexity: O(V2+V?K)O(\text{V}^2 + \text{V} \cdot \text{K})O(V2+V?K). The first part is the standard memory occupied by the adjacency matrix and in addition to that, the distances dictionary can occupy a maximum of O(V?K)O(\text{V} \cdot \text{K})O(V?K).
Time Compleit = O( E * K) where k is number of steps, we dont go beyond that And E is number of edges. if edges
     connect all nodes at all levels, this is the worst case we have.

     Space is due to queue and adjaceny list - V * K . For queue as well as adjaceny list
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //Create the adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] f : flights) {
            graph.get(f[0]).add(new int[]{f[1], f[2]});
        }
        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        q.add(new int[]{src, 0, K + 1});

        while (!q.isEmpty()) {
            int[] top = q.remove();
            int city = top[0];
            int price = top[1];
            int stops = top[2];

            if (city == dst)
                return price;

            if (stops > 0) {
                List<int[]> children = graph.get(city);

                for (int[] child : children) {
                    q.add(new int[]{child[0], child[1] + price, stops - 1});
                }
            }
        }
        return -1;

    }
}
