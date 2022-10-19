// leetcode:114
// 给你二叉树的根结点 root ，请你将它展开为一个单链表：

// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
/* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode() {}
*     TreeNode(int val) { this.val = val; }
*     TreeNode(int val, TreeNode left, TreeNode right) {
*         this.val = val;
*         this.left = left;
*         this.right = right;
*     }
* }
*/
class Solution {
   public void flatten(TreeNode root) {
       if(root == null) return;

        flatten(root.left);
        flatten(root.right);
       TreeNode left = root.left;
       TreeNode right = root.right;
       root.left = null;
       root.right = left;
       TreeNode p = root;
       while(p.right != null){
           p = p.right;
       }
       p.right = right;
   }
}