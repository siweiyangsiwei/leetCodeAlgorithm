// leetcode:315 二叉树归并排序
//给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： 
//counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

class Solution {
    class Pair{
        int val;
        int index;
        public Pair(int val, int index){
            this.val = val;
            this.index = index;
        }
    }
    public Pair[] arr;
    public Pair[] temp;
    public int[] count;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        arr = new Pair[n];
        temp = new Pair[n];
        count = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Pair(nums[i], i);
        }
        sort(arr,0,n - 1);
        LinkedList<Integer> list = new LinkedList<>();
        for (int c : count){
            list.add(c);
        }
        return list;
    }

    public void sort(Pair[] arr,int lo,int hi){
        if(lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr,lo,mid);
        sort(arr,mid + 1,hi);
        merge(arr,lo,mid,hi);
    }

    public void merge(Pair[] nums,int lo, int mid,int hi){
        for(int i = lo; i <= hi; i++){
            temp[i] = nums[i];
        }

        int i = lo,j = mid + 1;
        for(int p = lo; p <= hi; p++){
            if(i == mid + 1){
                arr[p] = temp[j++];
            }else if(j == hi + 1){
                arr[p] = temp[i++];
                count[arr[p].index] += j - mid - 1;
            }else if(temp[i].val > temp[j].val){
                arr[p] = temp[j++];
            }else{
                arr[p] = temp[i++];
                count[arr[p].index] += j - mid - 1;
            }
        }
    }
}