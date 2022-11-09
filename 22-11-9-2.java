// leetcode: 1135 最小生成树
// 想想一下一个你是一个城市基建规划者,地图上有N个城市,他们按1到N的次序编号
// 给你一些可连接的选项connections,其中每个选项connections[i] = [city1,city2,cose]
// 表示城市city1和city2所要连接的成本为cose.
// 计算联通所有城市最小成本,如果无法联通所有城市,则请你返回-1
class Solution {
    public int minimumCost(int n, int[][] connections){
        List<int[]>[] graph = build(n,connections);
        Prim prim = new Prim(graph);
        return prim.weightSum();
    }

    public List<int[]>[] build(int n, int[][] connections){
        List<int[]>[] graph = new LinkedList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge : connections){
            int u = conn[0] - 1;
            int v = conn[1] - 1;
            int weight = conn[2];
            // 「⽆向图」其实就是「双向图」
            // ⼀条边表示为 int[]{from, to, weight}
            graph[u].add(new int[]{u, v, weight});
            graph[v].add(new int[]{v, u, weight});
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