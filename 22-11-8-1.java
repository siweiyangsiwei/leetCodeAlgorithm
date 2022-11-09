// leetcode: 1135 最小生成树
// 想想一下一个你是一个城市基建规划者,地图上有N个城市,他们按1到N的次序编号
// 给你一些可连接的选项connections,其中每个选项connections[i] = [city1,city2,cose]
// 表示城市city1和city2所要连接的成本为cose.
// 计算联通所有城市最小成本,如果无法联通所有城市,则请你返回-1
class Solution{
    public int minimumCost(int n, int[][] connections){
        int mst = 0;
        UF uf = new UF(n + 1);
        Arrays.sort(connections,(a,b) -> {a[2] - b[2]});
        for(int[] edge : connections){
            int x = edge[0];
            int y = edge[1];
            if(x.connected(y)) continue;
            mst += edge[2];
            uf.union(x,y);
        }
        return uf.count == 2 ? mst : -1;

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