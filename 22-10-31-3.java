// leetcode: 210   拓扑排序
// 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

class Solution {
    boolean  falg = true;
    boolean[] visited;
    boolean[] onPath;
    List<Integer> res = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);
        for(int i = 0; i < numCourses; i++){
            tarverse(graph,i);
        }
        if(!falg) return new int[]{};
        Collections.reverse(res);
        int[] r = new int[res.size()];
        for(int i = 0; i < numCourses; i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    public void tarverse(List<Integer>[] graph,int i){
        if(onPath[i]){
            falg = false;
        }
        if(visited[i] || !falg) return;
        
        visited[i] = true;
        onPath[i] = true;
        for(int t : graph[i]){
            tarverse(graph,t);
        }
        res.add(i);
        onPath[i] = false;
    }

    List<Integer>[] buildGraph(int numCourses,int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge : prerequisites){
            int from = edge[1],to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}