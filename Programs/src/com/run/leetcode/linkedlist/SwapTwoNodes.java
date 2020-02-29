package com.run.leetcode.linkedlist;

class Node{
	int val;
	Node next;
	
	Node(int val1){
		this.val = val1;
	}
}

public class SwapTwoNodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Node swapTwoNodes(Node root) {
		Node temp = new Node(0);
		temp.next = root;
		Node current = temp;
		while(current.next != null && current.next.next != null) {
			Node first = current.next;
			Node second = current.next.next;
			
			//Swap
			first.next = second.next;
			second.next = first;
			current.next = second;
			
			//reassign current
			current = first;
			
		}
		
		return temp.next;
	}
	
}
