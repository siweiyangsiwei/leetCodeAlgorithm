// leetcode:96 二叉搜索树
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
class Solution {
    int[][] arr;
    public int numTrees(int n) {
        arr = new int[n+1][n+1];
        return count(1,n);
    }

    public int count(int lo,int hi){
        int res = 0;
        if(lo > hi) return 1;
        if(arr[lo][hi] != 0) return arr[lo][hi];
        for(int i = lo; i <= hi; i++){
            int left  = count(lo , i - 1);
            int right = count(i+1,hi);
            res += left * right;
        }
        arr[lo][hi] = res;
        return res;
    }
}