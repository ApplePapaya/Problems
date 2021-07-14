package com.run.leetcode.bfs;

import java.util.*;

/**
 * For each of the bus stop, we maintain all the buses (bus routes) that go through it. To do that, we use a HashMap, where bus stop number is the key and all the buses (bus routes) that go through it are added to an ArrayList.
 *
 * We use BFS, where we process elements in a level-wise manner. We add the Start bus stop in the queue. Next, when we enter the while loop, we add all the bus stops that are reachable by all the bus routes that go via the Start. Thus, if we have the input as [[1, 2, 7], [3, 6, 7]] and Start as 6, then upon processing bus stop 6, we would add bus stops 3 and 7.
 * With this approach, all the bus stops at a given level, are "equal distance" from the start node, in terms of number of buses that need to be changed.
 *
 * To avoid loops, we also maintain a HashSet that stores the buses that we have already visited.
 *
 * Note that while in this approach, we use each stop for doing BFS, one could also consider each bus (route) also for BFS.
 */
public class BusRoutes815 {

    public static void main(String[] args){
        int[][] routes =  {{1,2,7},{3,6,7}};
        int src = 1;
        int target = 6;

        System.out.println(numBusesToDestination(routes, src, target));


       int[][] routes2 = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        src = 15; target = 12;
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        int maxStop = -1;
        for (int[] route : routes) {
            for (int stop : route) {
                maxStop = Math.max(stop, maxStop);
            }
        }
        if (target > maxStop) {
            return -1;
        }
        int[] reaches = new int[maxStop + 1];
        Arrays.fill(reaches, n + 1);
        reaches[source] = 0;
        for (int i = 0, update = 1; i < n && update == 1; i++) {
            update = 0;
            for (int[] route : routes) {
                int fastedTake = maxStop;
                for (int stop : route) {
                    fastedTake = Math.min(fastedTake, reaches[stop]);
                }

                fastedTake++;
                for (int stop : route) {
                    if (reaches[stop] > fastedTake) {
                        reaches[stop] = fastedTake;
                        update = 1;
                    }
                }
            }
        }
        return reaches[target] == n + 1? -1 : reaches[target];
    }
    // each route[] contains list of bus stops that route 'i' stops at
    public int numBusesToDestinationBFS(int[][] routes, int source, int destination) {
        if (source == destination)
            return 0;

        Set<Integer> usedBuses = new HashSet<>();

        // ENTRY : busStop -> List of Routes
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>(); // {route: [buses]}

        for (int i = 0; i < routes.length; i++) {
            for (int route : routes[i]) {
                stopToRoutes.putIfAbsent(route, new ArrayList<>());
                stopToRoutes.get(route).add(i);
            }
        }

        int routesCount = 0;
        Queue<Integer> q = new LinkedList<>(Arrays.asList(source)); // list of bus stops

        while (!q.isEmpty()) {
            routesCount++;
            int size = q.size();
            for (int i = 0; i < size; i++) {

                for (int route : stopToRoutes.get(q.poll())) { // get all the routes at given bus stop

                    if (usedBuses.add(route)) { // if the route is not traversed before, then add it
                        for (int busStop : routes[route]) // take all new busstops yet to visit
                            if (busStop == destination)
                                return routesCount;
                            else
                                q.offer(busStop);
                    }
                }
            }
        }

        return -1;
    }
}
