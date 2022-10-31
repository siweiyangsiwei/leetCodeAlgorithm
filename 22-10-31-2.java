// leetcode: 207   拓扑排序
// 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

class Solution {
    boolean falg = true;
    boolean[] onPath;
    boolean[] visited;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);
        for(int i = 0; i < numCourses; i++){
            tarverse(graph,i);
        }
        return falg;
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