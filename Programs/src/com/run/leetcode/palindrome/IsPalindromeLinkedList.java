package com.run.leetcode.palindrome;
/**
 * Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

Two ways to solve it . One using reversing the second half of linked list and comparing both heads.
Or recursing. Latter better since it doesnt modify the input
 *
 */

class ListNode{
	int value;
	ListNode next;
	ListNode(){
		
	}
	ListNode(int val, ListNode next){
		this.value=val;
		this.next = next;
	}
	
}
public class IsPalindromeLinkedList {
	static ListNode ref ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         ListNode node5  = new ListNode(1,null);
         ListNode node4 = new ListNode(2,node5);
         ListNode node3 = new ListNode(3,node4);
         ListNode node2 = new ListNode(2,node3);
         ListNode node1 = new ListNode(5,node2);
         System.out.println(isPalindrome(node1));
	}
	
	public static boolean isPalindrome(ListNode head) {
		ref= head;
		return check(head);
	}
	
	public static boolean check(ListNode node) {
		if(node == null) return true;
		boolean ans = check(node.next);
		boolean isEqual = ref.value==node.value ? true : false;
		ref = ref.next;
		return ans && isEqual;
	}
	
	
	

}
