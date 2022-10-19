// leetcode:19 链表双指针
// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        ListNode target = dummy;
        for(int i = 0 ; i < n+1 ;i++){
            node = node.next;
        }
        while(node != null){
            node = node.next;
            target = target.next;
        }
        target.next = target.next.next;
        return dummy.next;
    }
}