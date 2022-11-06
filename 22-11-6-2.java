// leetcode: 886 二份图
// 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。

// 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。

class Solution {
    boolean[] visits;
    boolean[] color;
    boolean ok = true;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] arr = build(n,dislikes);
        visits = new boolean[n + 1];
        color = new boolean[n + 1];
        for(int i = 0; i < n; i++){
            if(!visits[i]){
                tarverse(arr,i);
            }
        }
        return ok;
    }

    public List<Integer>[] build(int n, int[][] dislikes){
        List<Integer>[] arr = new LinkedList[n+1];
        for(int i = 0; i<= n ; i++){
            arr[i] = new LinkedList<>();
        }
        for(int[] ints : dislikes){
            arr[ints[0]].add(ints[1]);
            arr[ints[1]].add(ints[0]);
        }
        return arr;
    }

    public void tarverse(List<Integer>[] arr,int n){
        if(!ok) return;
        visits[n] = true;
        for(Integer i: arr[n]){
            if(!visits[i]){
                color[i] = !color[n];
                tarverse(arr,i);
            }else{
                if(color[i] == color[n]){
                    ok = false;
                }
            }
        }
    }
}