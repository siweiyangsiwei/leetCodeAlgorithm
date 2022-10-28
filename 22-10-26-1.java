// leetcode: 1038 二叉搜索树
// 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。

// 提醒一下， 二叉搜索树 满足下列约束条件：

// 节点的左子树仅包含键 小于 节点键的节点。
// 节点的右子树仅包含键 大于 节点键的节点。
// 左右子树也必须是二叉搜索树。


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
    private int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        tarverse(root);
        return root;
    }
    
    public void tarverse(TreeNode root){
        if(root == null) return;
        tarverse(root.right);
        sum += root.val;
        root.val = sum;
        tarverse(root.left);
    }
}