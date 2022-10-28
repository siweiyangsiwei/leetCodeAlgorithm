// leetcode: 230 二叉搜索树
// 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
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
    private int count = 0;
    private int res; 
    public int kthSmallest(TreeNode root, int k) {
        tarverse(root,k);
        return res;
    }

    public void tarverse(TreeNode root,int k){
        if (root == null) return;
        tarverse(root.left,k);
        count++;
        if(count == k) res = root.val;
        tarverse(root.right,k);
    }
}