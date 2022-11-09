// leetcode: 1584 最小生成树
// 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UF uf = new UF(n);
        List<int[]> edges = new LinkedList<>();
        for (int i = 0;i < n;i++){
            for(int j = i + 1; j < n; j++){
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int[] arr = new int[]{i,j,Math.abs(x1 - x2) + Math.abs(y1 - y2)};
                edges.add(arr);
            }
        }
        int mst = 0;
        Collections.sort(edges,(x,y) -> { return x[2] - y[2];});
        for(int[] edge : edges){
            int x = edge[0], y = edge[1];
            if(uf.connected(x,y)) continue;
            mst += edge[2];
            uf.union(x,y);
        }
        return uf.count == 1 ? mst: -1;
    }
    class UF{
        public int count;
        public int[] parent;
        public UF(int n){
            parent = new int[n];
            this.count = n;
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public void union(int x, int y){
            int q = find(x);
            int p = find(y);
            if (q == p) return;
            parent[p] = q;
            count--;
        }

        public int find(int x){
            if(parent[x] ==x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean connected(int x, int y){
            int q = find(x);
            int p = find(y);
            return q==p;
        }

        public int count(){
            return this.count;
        }
    }
}