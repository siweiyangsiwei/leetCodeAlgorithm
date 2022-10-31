// leetcode: 912 二叉树快速排序
// 给你一个整数数组 nums，请你将该数组升序排列。
class Solution {
    public int[] sortArray(int[] nums) {
        shuffle(nums);
        tarverse(nums,0,nums.length - 1);
        return nums;
    }

    public void tarverse(int[] nums,int lo,int hi){
        if(lo >= hi) return ;
        int index = func(nums,lo,hi);
        tarverse(nums,lo,index - 1);
        tarverse(nums,index + 1,hi);
    }

    public int func(int[] nums,int lo,int hi){
        int res = nums[lo];
        int i = lo + 1,j = hi;
        while(i <= j){
            while(i < hi && nums[i] <= res){
                i++;
            }
            while(j > lo && nums[j] > res){
                j--;
            }
            if(i >= j) break;
            swap(nums,i,j);
        }
        swap(nums,lo,j);
        return  j;
    }
    
    public void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void shuffle(int[] nums){
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int r = i + rand.nextInt(n - i);
            swap(nums,i,r);
        }
    }
}