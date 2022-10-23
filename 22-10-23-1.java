// leetcode:652
// 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。

// 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。

// 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。

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
    HashMap<String,Integer> memo = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }
    public String traverse(TreeNode root){
        if (root == null){
            return "#";
        }
        String left =  traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;
        int freq = memo.getOrDefault(subTree,0);
        if(freq == 1) {
            res.add(root);
        }
        memo.put(subTree,freq + 1);
        return subTree;
    }

}