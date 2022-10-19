// leetcode:543 二叉树
// 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

 /**
 * Definition for a binary tree node.
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
    int maxDepth = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        tarverse(root);
        return maxDepth;
    }
    public int tarverse(TreeNode root){
        if(root == null) return 0;
        int leftMax = tarverse(root.left);
        int rightMax = tarverse(root.right);
        maxDepth = Math.max(leftMax + rightMax,maxDepth);
        return Math.max(leftMax,rightMax) + 1;

    }
}