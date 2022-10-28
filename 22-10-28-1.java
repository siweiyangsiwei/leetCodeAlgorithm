// leetcode: 95 二叉搜索树
// 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
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
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new LinkedList<>();
        return build(1,n);
    }

    public List<TreeNode> build(int lo , int hi){
        List<TreeNode> res = new LinkedList<>();

        if ( lo > hi){
            res.add(null);
            return res;    
        }

        for(int i = lo; i <= hi; i++){
            List<TreeNode> leftTree = build(lo,i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            for(TreeNode left : leftTree){
                for(TreeNode right : rightTree){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}