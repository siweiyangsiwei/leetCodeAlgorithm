// leetcode:34 数组双指针
// 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

// 如果数组中不存在目标值 target，返回 [-1, -1]。

// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length ==0) return new int[]{-1,-1};
        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] >= target){
                right = mid;
            }
        }
        int left1 = 0,right1 = nums.length - 1;
        while(left1 <= right1){
            int mid = left1 + (right1 - left1) / 2;
            if(nums[mid] <= target){
                left1 = mid + 1;
            }else if(nums[mid] > target){
                right1 = mid - 1;
            }
        }
        if(nums[left] != target) return new int[]{-1,-1};
        return new int[]{left,right1};
    }
}