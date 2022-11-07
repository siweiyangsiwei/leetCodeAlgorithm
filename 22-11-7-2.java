// leetcode: 130 UnionFinds
// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。?
class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        UF uf = new UF(n*m + 1);
        int[][] d = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int dummy = m*n;
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O'){
                uf.union(i * m,dummy);
            }
            if(board[i][m - 1] == 'O'){
                uf.union(i * m + m - 1,dummy);
            }
        }
        for(int i = 0; i < m; i++){
            if(board[0][i] == 'O'){
                uf.union(i,dummy);
            }
            if(board[n  - 1][i] == 'O'){
                uf.union(m*(n - 1) + i,dummy);
            }
        }
        for(int i = 1; i < n - 1; i++){
            for(int j = 1; j < m - 1; j++){
                if(board[i][j] == 'O'){
                    for(int k = 0; k < 4; k++){
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if(board[x][y] == 'O'){
                            uf.union(m * i + j,m * x + y);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n - 1;i++){
            for(int j = 1; j < m - 1;j++){
                if(!uf.connected(dummy,i*m + j)){
                    board[i][j] = 'X';
                }
            }
        }
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