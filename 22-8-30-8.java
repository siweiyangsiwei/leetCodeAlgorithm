// 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1,head),leftNode = dummy,rightNode = dummy;
        for(int i = 0; i < left - 1; i++){
            leftNode = leftNode.next;
        }
        for(int i = 0; i < right + 1; i++){
            rightNode = rightNode.next;
        }
        reverse(leftNode,rightNode);
        return dummy.next;
    }
    public void reverse(ListNode left,ListNode right){
        ListNode pre = right,cur = left.next;
        while(cur != right){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        left.next = pre;
        
    }
}