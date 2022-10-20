// leetccode:105 二叉树
// 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

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
    HashMap<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length;i++){
            map.put(inorder[i],i);
        }
        return  build(preorder,0,preorder.length - 1,
            inorder,0,inorder.length - 1);
    }

    public TreeNode build(int[] preorder,int preHead,int preTail,
                        int[] inorder, int inHead, int inTail){
                            if (preHead > preTail) return null;
                            int rootval = preorder[preHead];
                            int index = map.get(rootval);
                            int  pre = index - inHead;
                            TreeNode root = new TreeNode(rootval);
                            root.left = build(preorder,preHead + 1,preHead + pre,
                                                inorder,inHead,index - 1);
                            root.right = build(preorder,pre + preHead + 1,preTail,
                                                inorder,index + 1,inTail);
                            return root;
                        }
}