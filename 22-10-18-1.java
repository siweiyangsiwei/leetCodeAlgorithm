// leetcode:104
// 给定一个二叉树，找出其最大深度。

// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

// 说明: 叶子节点是指没有子节点的节点。
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
    int res = 0;
    int depth = 0;
    public int maxDepth(TreeNode root) {
        tarverse(root);
        return res;
    }

    public void tarverse(TreeNode root){
        if(root == null){
            return;
        }
        depth++;
        res = Math.max(depth,res);
        tarverse(root.left);
        tarverse(root.right);
        depth--;
    }
}