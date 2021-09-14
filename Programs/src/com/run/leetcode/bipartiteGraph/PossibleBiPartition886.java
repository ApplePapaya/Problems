package com.run.leetcode.bipartiteGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 * <p>
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 * <p>
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * All the pairs of dislikes are unique.
 */
public class PossibleBiPartition886 {

    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<Integer>[] graph = new List[N + 1];//because the ppl number start from 1 and not 0. so will have to ignore 0 index
        for(int i = 0; i <= N; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int dis[] : dislikes)
        {
            graph[dis[0]].add(dis[1]);
            graph[dis[1]].add(dis[0]);
        }
        int[] colors = new int[N + 1];
        int color = 1;
        for(int i = 1; i <= N; i++)
        {

            if(colors[i] == 0  && !dfs22(graph, colors, i, color))
                return false;
        }

        return true;

    }

    public boolean dfs22(List<Integer>[] graph, int[] colors, int index, int color){

        if(colors[index] != 0)
            return colors[index] == color;

        colors[index] = color;
        for(int child : graph[index]){
            if(!dfs22(graph, colors, child, -color))
                return false;
        }
        return true;
     }



    /***************************************************************************************/
    public boolean possibleBipartitionUF(int N, int[][] dislikes) {
        int[] colors = new int[N + 1];
        for(int i = 1; i <= N; ++i) colors[i] = i;
        for(int i = 0; i < dislikes.length; ++i) {
            int p1 = dislikes[i][0], p2 = dislikes[i][1];
            if(colors[p2] == p2) colors[p2] = p1;
            else {
                int[] uf1 = find(p1, colors), uf2 = find(p2, colors);
                if(uf1[0] == uf2[0] && uf1[1] == uf2[1]) return false;
            }
        }
        return true;
    }

    private int[] find(int p, int[] colors) {
        int color = 0;
        while(colors[p] != p) {
            p = colors[p];
            color = color == 0 ? 1 : 0;
        }
        return new int[] {p, color};
    }
/***********************************************************************************/
    public boolean possibleBipartitionBFS(int N, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] dis : dislikes) {
            graph[dis[0]].add(dis[1]);
            graph[dis[1]].add(dis[0]);
        }
        int[] colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (colors[i] != 0) continue;

            colors[i] = 1;

            Queue<Integer> q = new LinkedList<>();
            q.offer(i);

            while (q.isEmpty() == false) {
                int node = q.poll();

                for (int child : graph[node]) {
                    if (colors[child] != 0 && colors[child] == colors[node])
                        return false;

                    if (colors[child] == 0) {
                        colors[child] = -colors[node];
                        q.offer(child);
                    }
                }
            }
        }
        return true;
    }
}
