package com.run.leetcode.hardProblems;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphColoringProblem {

	private int V;
	private LinkedList<Integer> adj[];
	
	GraphColoringProblem(int v){
		V = v;
		adj = new LinkedList[v];
		
		for(int i =0; i<v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		
	}
	
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	void greedyColoring() {
		int[] result = new int[V];// result to hold the color for each vertex in array
		Arrays.fill(result, -1);
		
		boolean[] available = new boolean[V];
		Arrays.fill(available, true);// All colors are available initially
		
		result[0] = 0;// First color
	
		for(int i =1; i < V; i++) {
			LinkedList<Integer> a = adj[i];
			
			Iterator<Integer> itr = a.iterator();
			while(itr.hasNext()) {
				int x = itr.next();
				if(result[x] != -1)
					available[result[x]] = false;
			}
			int j; // find the first available color
			for( j = 0; j< V; j++) {
				if(available[j] == true)
					break;
			}
			result[i] = j;
			
			Arrays.fill(available, true);// All colors are initially available for next iteration
			
		}
		
		for(int i =0; i < V; i++) {
			System.out.println("Vertex :" + i + " Color: " + result[i]);
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphColoringProblem gp = new GraphColoringProblem(8);
		gp.addEdge(1, 3);
		gp.addEdge(1, 2);
		gp.addEdge(1, 4);
		gp.addEdge(1, 5);
		gp.addEdge(4, 5);
		gp.addEdge(2, 3);
		gp.addEdge(3, 7);
		gp.addEdge(4, 3);
		
		gp.greedyColoring();
	}

}
