// leetcode: 261 最小生成树
// 给你输⼊编号从 0 到 n - 1 的 n 个结点，和⼀个⽆向边列表 edges（每条边⽤节点⼆元组表示），请你判
// 断输⼊的这些边组成的结构是否是⼀棵树。

Class Solution{
    public boolean validTree(int n,int[][] edges){
        UF uf = new UF(n);
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            if(uf.connected(x,y)) return false;
            uf.union(x,y);
        }
        return uf.count == 1;

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