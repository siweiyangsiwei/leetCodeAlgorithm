// leetcode: 1584 最小生成树
// 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。

class Solution {
    public int minCostConnectPoints(int[][] points) {
        List<int[]>[] graph = buildGraph(points.length,points);
        Prim prim = new Prim(graph);
        return prim.weightSum();
    }

    List<int[]>[] buildGraph(int n, int[][] points) {
        List<int[]>[] graph = new LinkedList[n];
         for (int i = 0; i < n; i++) {
             graph[i] = new LinkedList<>();
        }
        // ⽣成所有边及权重
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                 int weight = Math.abs(xi - xj) + Math.abs(yi - yj);
                // ⽤ points 中的索引表示坐标点
                graph[i].add(new int[]{i, j, weight});
                graph[j].add(new int[]{j, i, weight});
            }
        }
        return graph;
    }

    class Prim{
        private PriorityQueue<int[]> pq;
        private boolean[] inMST;
        private int weightSum;
        List<int[]>[] graph;
        public Prim(List<int[]>[] graph){
            this.graph = graph;
            int n = graph.length;
            this.pq = new PriorityQueue<>((a, b) -> {
                return a[2] - b[2];
            });
            inMST = new boolean[n];
            inMST[0] = true;
            cut(0);
            while(!pq.isEmpty()){
                int[] edge  = pq.poll();
                int to = edge[1];
                int weight = edge[2];
                if(inMST[to]) continue;
                weightSum += weight;
                inMST[to] = true;
                cut(to);
            }
        }

        public void cut(int n){
            for(int[] edge : graph[n]){
                int to = edge[1];
                if(inMST[to]) continue;
                pq.offer(edge);
            }
        }
        public int weightSum() {
            return weightSum;
        }

    }
}