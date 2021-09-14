package com.run.leetcode.linkedlist;

import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */
public class MergeKSortedLists23 {
    /**

     Here we can solve this problem using priority queue.
     We are given an array of listNodes which is nothing but starting nodes of K lists of ListNodes

     If the lists is null or length = 0 return null
     Else
     Initialize a priority Queue of ListNode (NOT ARRAY) and comparator to have it in ascending order
     PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length,
     (a, b) -> a.val - b.val)
     Initialize head = new listnode(0) which is a dummy node,
     have another node p = head to compute the sorting
     For the array of List nodes, offer the node to the queue.
     This will sort the first nodes of the k lists in the queue. Here queue contains K nodes and hence the complexity is klogK. K elements one each from k lists.
     Time should be n log K where n = total elements from all lists. since we add N times

     Now while queue is not empty
     ListNode n  = queue.poll() - the first least element from the k lists in the queue.
     assign p.next to n.
     and if n.next is not null then offer it to queue. This way the second element from every list will get into the queue. but at the same time only k nodes will be rpesent in the queue.
     Once completed, return the dummynode.next.


     **/

    public ListNode mergeKLists1(ListNode[] lists) {
        int k = lists.length;
        if(lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode head = new ListNode(0);
        ListNode p = head;
        //Get the first nodes from the listnodes array and insert into priorityqueue to sort them
        for(ListNode l : lists)
        {
            if(l != null)
                pq.offer(l);
        }
        //Poll the first least elemnet from queue and push the next node of that into the queue.
        while(pq.isEmpty()== false){
            ListNode n = pq.poll();
            p.next = n;
            p = p.next;

            if(n.next != null)
                pq.offer(n.next);
        }

        return head.next;

    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists, int l,int r){
        if(r==l) return lists[l];//Base case is i have just one element and so right == left
        int mid=l+(r-l)/2;//Get the mid point for partition
        ListNode left=merge(lists,l,mid);// call recursively the sme function from l to mid
        ListNode right=merge(lists,mid+1,r);//call recursively for the second half
        return mergeTwoLists(left,right);

    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode dummy=head;
        while(l1!=null||l2!=null){
            if(l1==null){head.next=l2;l2=l2.next;}
            else if(l2==null) {head.next=l1;l1=l1.next;}
            else {
                if(l1.val<l2.val) {head.next=l1;l1=l1.next;}
                else if(l1.val>=l2.val){head.next=l2;l2=l2.next;}
            }
            head=head.next;
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
