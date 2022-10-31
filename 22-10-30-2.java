// leetcode: 215 二叉树快速排序
// 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
class Solution {
    int k = 0;
    int res = 0;
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        this.k = k;
        sort(nums,0,nums.length - 1);
        return res;
    }

    public void sort(int[] nums,int lo,int hi){
        k = nums.length - k;
        while(lo <= hi){
            int p = func(nums,lo,hi);
            if(p < k){
                lo = p + 1;
            }else if(p >  k){
                hi = p - 1;
            }else{
                res = nums[p];
                return;
            }
        }
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