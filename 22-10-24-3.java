// leetcode: 327 二叉树归并排序
// 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。

// 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

class Solution {
    long[] preSum;
    int count = 0;
    long[] temp;
    int lower,upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        preSum = new long[nums.length + 1];
        temp = new long[nums.length + 1];
        for(int i = 0 ; i < nums.length ; i++){
            preSum[i + 1] = preSum[i] + (long)nums[i];
        }
        sort(preSum,0,preSum.length - 1);
        return count;
    }

    public void sort(long[] preSum, int lo,int hi){
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(preSum,lo,mid);
        sort(preSum,mid + 1,hi);
        merge(preSum,lo,mid,hi);
    }


    public void merge(long[] preSum,int lo,int mid,int hi){
        for(int i = lo; i <= hi;i++){
            temp[i] = preSum[i];
        }
        int start = mid + 1,end = mid+1;
        for(int i = lo; i <= mid; i++){
            while(start <= hi && preSum[start] - preSum[i] < lower){
                start++;
            }
            while(end <= hi && preSum[end] - preSum[i] <= upper){
                end++;
            }
            count+=end - start;
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                preSum[p] = temp[j++];
            } else if (j == hi + 1) {
                preSum[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                preSum[p] = temp[j++];
            } else {
                preSum[p] = temp[i++];
            }
        }
    }
}