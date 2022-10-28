// leetcode: 1373 二叉搜索树
// 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。

// 二叉搜索树的定义如下：

// 任意节点的左子树中的键值都 小于 此节点的键值。
// 任意节点的右子树中的键值都 大于 此节点的键值。
// 任意节点的左子树和右子树都是二叉搜索树。

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
    int max = 0;
    public int maxSumBST(TreeNode root) {
        traverse(root);
        return max;
    }

    public int[] traverse(TreeNode root){
        if(root == null){
            return new int[]{1,Integer.MAX_VALUE,Integer.MIN_VALUE,0};
        }

        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int[] res = new int[4];

        if(left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]){
            res[0] = 1;
            res[1] = Math.min(left[1],root.val);
            res[2] = Math.max(right[2],root.val);
            res[3] = left[3] + right[3] + root.val;
            max = Math.max(max,res[3]);
        }else{
            res[0] = 0;
        }
        return res;
    }
}