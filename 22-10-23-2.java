// leetcode:912
//给你一个整数数组 nums，请你将该数组升序排列。
class Solution {
    int[] res;
    public int[] sortArray(int[] nums) {
        res = new int[nums.length];
        sort(nums,0,nums.length - 1);
        return nums;
    }

    public void sort(int[] nums,int lo,int hi){
        if(lo == hi) return;
        int min = lo + (hi - lo) / 2;
        sort(nums,lo,min);
        sort(nums,min+1,hi);
        merge(nums,lo,min,hi);
    }

    public void merge(int[] nums,int lo,int min,int hi){
        for(int i = lo ;i <=  hi ;i++){
            res[i] = nums[i];  
        }
        int i = lo,j = min + 1;
        for(int p = lo;p <= hi;p++){
            if(i == min + 1){
                nums[p] = res[j++];
            }else if(j == hi + 1){
                nums[p] = res[i++];
            }else if(res[i] > res[j]){
                nums[p] = res[j++];
            }else{
                nums[p] = res[i++];
            }
        }
    }
}