// leetcode: 797 图论
// 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）

//  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。

class Solution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        tarverse(graph,0,path);
        return res;
    }
    public void tarverse(int[][] graph,int index,LinkedList<Integer> path){
        path.add(index);
        if(index == graph.length - 1){
            res.add(new LinkedList<Integer>(path));
        }
        for(int s : graph[index]){
            tarverse(graph,s,path);
        }
        path.removeLast();
    }
}