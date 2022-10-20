// leetcode:106
// 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。


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
    public HashMap<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length - 1,
                    postorder,0,postorder.length - 1);
    }

    public TreeNode build(int[] inorder,int inHead,int inTail,
                            int[] postorder,int postHead,int postTail){
                                if(postHead > postTail) return null;
                                int rootval = postorder[postTail];
                                int index = map.get(rootval);
                                int pre = index - inHead;
                                TreeNode root = new TreeNode(rootval);
                                root.left = build(inorder,inHead,index - 1,
                                                postorder,postHead,postHead + pre -1);
                                root.right = build(inorder,index + 1, inTail,
                                                    postorder,postHead + pre,postTail - 1);
                                return root;

                            }
}