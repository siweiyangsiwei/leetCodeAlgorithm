// leetcode:493 二叉树归并排序
// 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

// 你需要返回给定数组中的重要翻转对的数量。

class Solution {
    public int count;
    public int[] temp;
    public int reversePairs(int[] nums) {
        count = 0;
        temp = new int[nums.length];
        sort(nums,0,nums.length - 1);
        return count;
    }
    public void sort(int[] nums,int lo, int hi){
        if(lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums,lo, mid);
        sort(nums,mid + 1, hi);
        merge(nums,lo,mid,hi);
    }
    public void merge(int[] nums,int lo,int mid,int hi){
        for(int i = lo; i <= hi; i++){
            temp[i] = nums[i];
        }
        int end = mid + 1;
        for(int i = lo;i <= mid;i++){
            while(end <= hi && (long)nums[i] > (long)nums[end] * 2){
                end++;
            }
            count += end - (mid + 1);
        }
        int i = lo,j = mid + 1;
        for(int p = lo;p <= hi;p++){
            if(i == mid + 1){
                nums[p] = temp[j++];
            }else if(j == hi + 1){
                nums[p] = temp[i++];
            }else if(temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else{
                nums[p] = temp[i++];
            }
        }
    }
}