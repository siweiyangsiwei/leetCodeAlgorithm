// leetcode: 990 UnionFind
// 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

// 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

class Solution {
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for(String s : equations){
            if(s.charAt(1) == '='){
                uf.union(s.charAt(0) - 'a',s.charAt(3) - 'a');
            }
        }
        for(String s : equations){
            if(s.charAt(1) == '!'){
                if(uf.connected(s.charAt(0) - 'a',s.charAt(3) - 'a')){
                    return false;
                }
            }
        }
        return true ;
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