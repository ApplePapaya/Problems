package com.run.leetcode.graphs;

import java.util.*;

public class OptimizeWaterDistributionInVillage1168 {

    /**
     * Overview
     *
     * Since the problem description involves connecting houses (vertices) using pipes (edges), we can tell that this problem is a variant of graph problems. More precisely, we can convert it into a standard minimum spanning tree (MST) problem, which we will discuss in detail how to do so in this article.
     *
     * Concerning the MST problem, there exist several classic algorithms. In particular, we will demonstrate two of them, namely Prim's algorithm and Kruskal's algorithm, which are arguably the most popular ones and feasible to implement during an interview.
     *
     * Intuition
     *
     * First of all, let us introduce the problem of the minimum spanning tree.
     *
     *     Given a connected, edge-weighted and undirected graph, a minimum spanning tree is a subset of edges that connect all vertices while the total weights of these edges are minimum among all possible subsets.
     *
     * One can draw some similarities between the above definition and our problem here. Specifically, we can consider each house as a vertex in a graph, and the pipes between the houses as edges in the graph.
     *
     * However, there is one major difference between them. In our problem, every vertex and every edge comes with a cost. While in the setting of MST, only the edges are associated with the costs.
     *
     *     To bridge the gap, as suggested in the hints, the trick is to add one virtual vertex to the existing graph. Along with the addition of vertex, we also add edges between the virtual vertex and the rest of the vertices. Finally, we reassign the cost of each vertex to the corresponding newly-added edge.
     *
     * Here is an illustration showing how we convert the graph in the example with the above trick.
     *
     * graph conversion
     *
     * With the converted graph, we then can take into account the costs from the vertex, via the additional edges. We can focus entirely on selecting the appropriate edges to create an MST. Thus, our problem is simplified to creating an MST from a list of weighted edges.
     *
     * MST solution
     *
     * In the above graph, we demonstrate the solution that we will find after solving the MST problem, which we can translate as "to minimize the cost, we should dig a well in the house indexed with 1 (denoted by the edge between indices 1 and 0), and then supply the water to the rest of the houses."
     * Approach 1: Prim's Algorithm with Heap
     *
     * Intuition
     *
     * Prim's (also known as Jarník's) algorithm is a greedy algorithm used to find the minimum spanning tree in a weighted and undirected graph.
     *
     *     The algorithm operates by building the tree one vertex at a time, from an arbitrary starting vertex, at each step adding the cheapest possible connection from any vertex in the tree to a vertex that is not in the tree.
     *
     * Prim Demo
     *
     * The above illustration demonstrates how Prim's algorithm works. Starting from an arbitrary vertex, Prim's algorithm grows the minimum spanning tree by adding one vertex at a time to the tree. The choice of a vertex is based on the greedy strategy, i.e. the addition of the new vertex incurs the minimum cost.
     *
     * Algorithm
     *
     * To implement Prim's algorithm, essentially we will need the following three data structures:
     *
     *     adjacency list: we need this to represent the graph, i.e. vertices and edges. The adjacency list can be a list of lists or a dictionary of lists.
     *
     *     set: we need a set to maintain all the vertices that we have added to the final minimum spanning tree, during the construction of the tree. With the help of set, we can determine whether a vertex has been added or not.
     *
     *     heap: due to the nature of the greedy strategy, at each step, we can determine the best edge to be added based on the cost it will add to the tree. Heap (also known as a priority queue) is a data structure that allows us to retrieve the minimum element in constant time and to remove the minimum element in logarithmic time. This fits our need to repeatedly find the lowest cost edge perfectly.
     *
     * Implementation
     *
     * By applying the above three data structures, the following steps can be used to implement Prim's algorithm.
     *
     *     First of all, given the input, we need to build a graph representation with the adjacency list.
     *         Note that, since the graph is undirected (i.e. bidirectional), for each pipe, we need to add two entries in the adjacency list, with each end of the pipe as a starting vertex.
     *         Also, to convert our problem into the MST problem, we need to add a virtual vertex (we index it as 0) together with the additional n edges to each house.
     *
     *     Starting from the virtual vertex, we build the MST by iteratively adding one vertex at a time.
     *         Note, when using Prim's algorithm, we can use any vertex as a starting point. Here, for the sake of convenience, we start from the newly-added virtual vertex.
     *
     *     The process of building MST consists of a loop with the following substeps:
     *
     *         Each iteration, we pop an element from the heap. This element contains a vertex along with the cost that is associated with the edge that connecting the vertex to the tree. The vertex is chosen if it is not already in the tree. We know that the cost of this vertex is minimal among all choices because it was popped from the heap.
     *
     *         Once the vertex is added, we then examine its neighboring vertices. Specifically, we add these vertices along with their edges into the heap as the candidates for the next round of selection.
     *
     *         The loop terminates when we have added all the vertices from the graph into the MST.
     *         Complexity Analysis
     *
     * Let NNN be the number of houses, and MMM be the number of pipes from the input.
     *
     *     Time Complexity: O((N+M)?log?(N+M))O\big( (N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M))
     *
     *         To build the graph, we iterate through the houses and pipes in the input, which takes O(N+M)O(N + M)O(N+M) time.
     *
     *         While building the MST, we might need to iterate through all the edges in the graph in the worst case, which amounts to N+MN + MN+M in total. For each edge, it would enter and exit the heap data structure at most once. The enter of edge into heap (i.e. push operation) takes log?(N+M)\log(N+M)log(N+M) time, while the exit of edge (i.e. pop operation) takes a constant time. Therefore, the time complexity of the MST construction process is O((N+M)?log?(N+M))O\big( (N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M)).
     *
     *         To sum up, the overall time complexity of the algorithm is O((N+M)?log?(N+M))O\big( (N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M)).
     *
     *     Space Complexity: O(N+M)O(N+M)O(N+M)
     *
     *         We break down the analysis accordingly into the three major data structures that we used in the algorithm.
     *
     *         The graph that we built consists of N+1N+1N+1 vertices and 2?M2 \cdot M2?M edges (i.e. pipes are bidirectional). Therefore, the space complexity of graph is O(N+1+2?M)=O(N+M)O(N + 1 + 2 \cdot M) = O(N + M)O(N+1+2?M)=O(N+M).
     *
     *         The space complexity of the set that is used to hold the vertices in MST is O(N)O(N)O(N).
     *
     *         Finally, in the worst case, the heap we used might hold all the edges in the graph which is (N+M)(N+M)(N+M).
     *
     *         To summarize, the overall space complexity of the algorithm is O(N+M)O(N+M)O(N+M).
     * @param n
     * @param wells
     * @param pipes
     * @return
     */

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }

        //Create an adjacency map with unidirectional connections.
        for(int[] pipe : pipes) {
            adj.get(pipe[0]).add(new int[]{pipe[1], pipe[2]});
            adj.get(pipe[1]).add(new int[]{pipe[0], pipe[2]});
        }

        // PQ for selecting less expensive edge
        PriorityQueue<int[]> q =
                new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));

        //We populate q with our wells as starters. We start from them cause we need to have at least one
        //well connected.
        //Note also we don't need to have our wells in adj map as well, by adding that virtual edges.
        for(int i = 1; i <= n; i++) {
            q.add(new int[]{i, wells[i - 1]});
        }

        int sum = 0;
        while(visitedSet.size() < n) {
            //Poll until we found something not visited this neccesary cause we not only getting to nodes from the wells,
            //we can also reach them via pipes - and that's preventing building non-necessary wells.
            int[] node;
            while(visitedSet.contains((node = q.poll())[0]));
            visitedSet.add(node[0]);
            sum += node[1];
            //Expand to childs and not add already visited things for optimization.
            for(int[] child : adj.get(node[0])) {
                if(!visitedSet.contains(child[0]))
                    q.add(child);
            }
        }

        return sum;
    }

    /**
     * Approach 2: Kruskal's Algorithm with Union-Find
     *
     * Intuition
     *
     * Another classical algorithm to solve the MST problem is called Kruskal's algorithm.
     *
     *     Similiar to Prim's algorithm, Kruskal's algorithm applies the greedy strategy to incrementally add new edges to the final solution.
     *
     * Kruskal Demo
     *
     * The above animation shows how Kruskal's algorithm grows the minimum spanning tree.
     *
     *     A major difference between them is that in Prim's algorithm the MST (minimal spanning tree) remains connected as a whole throughout the entire process, while in Kruskal's algorithm, the tree is formed by merging the disjoint components together.
     *
     * Algorithm
     *
     * Rather than adding vertices as in Prim's algorithm, the Kruskal's algorithm focuses on adding edges. Furthermore, in Kruskal's algorithm, we consider all edges at once ranked by their costs, while in Prim's algorithm, although edges are ranked by costs in a heap or priority queue, at each iteration, we only explore edges that are connected to the vertices that are already in the MST.
     *
     *     The overall idea of Kruskal's algorithm is that we iterate through all the edges ordered by their costs. For each edge, we decide whether to add it to the final MST. The decision is based on whether this new addition will help to connect more dots (i.e. vertices).
     *
     * Union-Find examples
     *
     * Add or Not to Add ?
     *
     * The above diagram shows three example scenarios and for each scenario, specifies whether a new edge should be added or not. The solid edges have already been added to the MST, while the dashed edges have yet to be decided.
     *
     *     In the example on the left, we should add the new edge, since the edge bridges the gap between the two disjoint components.
     *     In the middle example, we should also add the new edge, since it connects to an unseen vertex (i.e. connecting more dots).
     *     In the example on the right, we should not add the new edge. Because it does not help us to make the current MST more connected, since all vertices are connected already.
     *
     *     A more concise criteria to determine whether we should add a new edge in Kruskal's algorithm is that whether both ends of the edge belong to the same component (group).
     *
     * Implementation
     *
     * In order to determine the membership for a collection of elements, we often apply the data structure called Disjoint Set which is also known as Union-Find data structure.
     *
     * Essentially, the Union-Find data structure provides two interfaces:
     *
     *     find(a): the function returns the id of the group where the element a belongs to.
     *     union(a, b): the function joins the two groups that the element a and b belong to. If they belong to the same group already, then the function does nothing.
     *
     * We provide a full-fledged version of the Union-Find data structure with path compression and link-by-rank in the sample implementation.
     *
     * If one would like to know more about how the Union-Find data structure works, one can refer to the solution for the problem of 323. Number of Connected Components in an Undirected Graph and a tutorial from Princeton University.
     *
     * Given the Union-Find data structure, we can implement Kruskal's algorithm with the following two steps:
     *
     *     First of all, we sort all the edges based on their costs, including the additional edges that are added with the virtual vertex.
     *
     *     We then iterate through the sorted edges. For each edge, if both ends of the edge belong to different groups, with the help of the Union-Find data structure, we then add this edge into the final MST.
     *
     *     Note: in the above implementation, we tweak the union(a, b) a bit to make the code more efficient and concise.
     *
     * In most implementations of Union-Find data structure, we do not return anything for the function of union(a, b). However, in our case, we return a flag to indicate whether the joining actually happens within the function. With this tweak, we only need to invoke the union(a,b) function in our iteration, rather than invoking find(a) == find(b) functions in addition.
     *
     * Complexity Analysis
     *
     * Since we applied the Union-Find data structure in our algorithm, let's begin with a statement on the time complexity of the data structure:
     *
     *     If KKK operations, either Union or Find, are applied to LLL elements, the total run time is O(K?log??L)\mathcal{O}(K \cdot \log^{*}{L})O(K?log?L), where log??\log^{*}log? is the iterated logarithm.
     *
     * One can refer to the proof of Union-Find complexity and the tutorial from Princeton University for more details.
     *
     * Let NNN be the number of houses, and MMM be the number of pipes from the input.
     *
     *     Time Complexity: O((N+M)?log?(N+M))O\big((N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M))
     *
     *         First, we build a list of edges, which takes O(N+M)O(N + M)O(N+M) time.
     *
     *         We then sort the list of edges, which takes O((N+M)?log?(N+M))O\big((N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M)) time.
     *
     *         At the end, we iterate through the sorted edges. For each iteration, we invoke a Union-Find operation. Hence, the time complexity for iteration is O((N+M)?log??(N))O\big( (N+M) * \log^{*}(N) \big)O((N+M)?log?(N)).
     *
     *         To sum up, the overall time complexity of the algorithm is O((N+M)?log?(N+M))O\big((N+M) \cdot \log(N+M) \big)O((N+M)?log(N+M)) which is dominated by the sorting step.
     *
     *     Space Complexity: O(N+M)O(N+M)O(N+M)
     *
     *         The space complexity of our Union-Find data structure is O(N)O(N)O(N).
     *
     *         The space required by the list of edges is O(N+M)O(N+M)O(N+M).
     *
     *         Finally, the space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, the list.sort() function in Python is implemented with the Timsort algorithm whose space complexity is O(n)\mathcal{O}(n)O(n) where nnn is the number of the elements. While in Java, the Collections.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(log?n)\mathcal{O}(\log{n})O(logn).
     *
     *         To sum up, the overall space complexity of the algorithm is O(N+M)O(N+M)O(N+M) which is dominated by the list of edges.
     */
}class UnionFind {
    /**
     * Implementation of UnionFind without load-balancing.
     */
    private int[] group;
    private int[] rank;

    public UnionFind(int size) {
        // container to hold the group id for each member
        // Note: the index of member starts from 1,
        //   thus we add one more element to the container.
        group = new int[size + 1];
        rank = new int[size + 1];
        for (int i = 0; i < size + 1; ++i) {
            group[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * return the group id that the person belongs to.
     */
    public int find(int person) {
        if (group[person] != person) {
            group[person] = find(group[person]);
        }
        return group[person];
    }

    /**
     * Join the groups together.
     * return:
     * false when the two persons belong to the same group already,
     * otherwise true
     */
    public boolean union(int person1, int person2) {
        int group1 = find(person1);
        int group2 = find(person2);
        if (group1 == group2) {
            return false;
        }

        // attach the group of lower rank to the one with higher rank
        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }

        return true;
    }
}



    public int minCostToSupplyWaterKruskal(int n, int[] wells, int[][] pipes) {
        List<int[]> orderedEdges = new ArrayList<>(n + 1 + pipes.length);

        // add the virtual vertex (index with 0) along with the new edges.
        for (int i = 0; i < wells.length; ++i) {
            orderedEdges.add(new int[]{0, i + 1, wells[i]});
        }

        // add the existing edges
        for (int i = 0; i < pipes.length; ++i) {
            int[] edge = pipes[i];
            orderedEdges.add(edge);
        }

        // sort the edges based on their cost
        Collections.sort(orderedEdges, (a, b) -> a[2] - b[2]);

        // iterate through the ordered edges
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        for (int[] edge : orderedEdges) {
            int house1 = edge[0];
            int house2 = edge[1];
            int cost = edge[2];
            // determine if we should add the new edge to the final MST
            if (uf.union(house1, house2)) {
                totalCost += cost;
            }
        }

        return totalCost;
    }

