// leetcode: 1513  dijkstra算法
// 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。

// 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。

// 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。


class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        // 构造无向图
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            // 无向图其实就是双向图
            graph[from].add(new double[]{(double)to, weight});
            graph[to].add(new double[]{(double)from, weight});
        }
        
        
        return dijkstra(start, end, graph);
    }

    class State{
        int id;
        double distFromStart;
        State(int id, double distFromStart){
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    double dijkstra(int start,int end, List<double[]>[] graph){
        int n = graph.length;
        double[] distTo = new double[n];
        Arrays.fill(distTo, -1);
        distTo[start] = 1;
         Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.distFromStart, a.distFromStart);
        });
        pq.offer(new State(start,1));
        while(!pq.isEmpty()){
            State curState = pq.poll();
            int curNodeID = curState.id;
            double curDistFromStart = curState.distFromStart;
            if(curNodeID == end) return curDistFromStart;
            if(curDistFromStart < distTo[curNodeID]) continue;
            for(double[] next: graph[curNodeID]){
                int nextNodeId = (int) next[0];
                double distToNextNode = distTo[curNodeID] * next[1];
                if(distTo[nextNodeId] < distToNextNode){
                    distTo[nextNodeId] = distToNextNode;
                    pq.offer(new State(nextNodeId,distToNextNode));
                }
            }
        
        }
        return 0.0;
    }
}