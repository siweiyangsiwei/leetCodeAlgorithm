// leetcode: 46  回溯算法
// 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

class Solution {
    List<List<Integer>> res ;
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        LinkedList<Integer> temp = new LinkedList<>();
        backtrack(nums,temp,visited);
        return res;
    }

    public void backtrack(int[] nums,LinkedList<Integer> temp,boolean[] visited){
        if(temp.size() == nums.length) {
            res.add(new LinkedList<Integer>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            temp.add(nums[i]);
            visited[i] = true;
            backtrack(nums,temp,visited);
            visited[i] = false;
            temp.removeLast();
        }
    }
}