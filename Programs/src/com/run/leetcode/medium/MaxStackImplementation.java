package com.run.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

//https://leetcode.com/problems/max-stack/
//https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
//https://www.geeksforgeeks.org/find-maximum-in-a-stack-in-o1-time-and-o1-extra-space/?ref=rp
//https://leetcode.com/problems/min-stack/solution/ best
/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5

Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 * 
 * 
 * There are 2 options to solve this 
 * 1> Using 2 stacks  - One is stack to store elements and other to store the max element.
 * Here all operations iwll be O(1) except popMax which will need iterating througha ll elements.
 * 2> Using DoubleLInkedList and TreeMap. TreeMap to get the max element and Double linked list for removing elements in
 * O(1). 
 * 
 * Intuition
Using structures like Array or Stack will never let us popMax quickly. We turn our attention to tree and linked-list structures that have a lower time complexity for removal, with the aim of making popMax faster than 
O(N)O(N)
O(N) time complexity.
Say we have a double linked list as our "stack". This reduces the problem to finding which node to remove, since we can remove nodes in 
O(1)O(1)
O(1) time.
We can use a TreeMap mapping values to a list of nodes to answer this question. TreeMap can find the largest value, insert values, and delete values, all in 
O(log⁡N)O(\log N)
O(logN) time.
Algorithm
Let's store the stack as a double linked list dll, and store a map from value to a List of Node.
When we MaxStack.push(x), we add a node to our dll, and add or update our entry map.get(x).add(node).
When we MaxStack.pop(), we find the value val = dll.pop(), and remove the node from our map, deleting the entry if it was the last one.
When we MaxStack.popMax(), we use the map to find the relevant node to unlink, and return it's value.
The above operations are more clear given that we have a working DoubleLinkedList class. The implementation provided uses head and tail sentinels to simplify the relevant DoubleLinkedList operations.	
 *
 */
public class MaxStackImplementation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap<Integer,List<Node>>();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}

/***
 * Approach #1: Two Stacks [Accepted]
Intuition and Algorithm
A regular stack already supports the first 3 operations, so we focus on the last two.
For peekMax, we remember the largest value we've seen on the side. For example if we add [2, 1, 5, 3, 9], we'll remember [2, 2, 5, 5, 9]. This works seamlessly with pop operations, and also it's easy to compute: it's just the maximum of the element we are adding and the previous maximum.
For popMax, we know what the current maximum (peekMax) is. We can pop until we find that maximum, then push the popped elements back on the stack.
Our implementation in Python will showcase extending the list class.

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }

    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(max > x ? max : x);
        stack.push(x);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack();
        while (top() != max) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }
}

Complexity Analysis
Time Complexity: 
O(N)O(N)
O(N) for the popMax operation, and 
O(1)O(1)
O(1) for the other operations, where 
NN
N is the number of operations performed.
Space Complexity: 
O(N)O(N)
O(N), the maximum size of the stack.


**/
