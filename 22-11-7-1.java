// leetcode: 765 Union Find
// n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。

// 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。

// 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。

class Solution {
    public int minSwapsCouples(int[] row) {
        UF uf = new UF(row);
        for(int i = 0; i < row.length;i+=2){
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        return row.length - uf.count();
    }

    class UF{
        public int count;
        public int[] trees;
        public UF(int[] row){
            this.count = row.length;
            trees = new int[row.length];
            for(int i= 0; i < row.length;i++){
                trees[i] = i;
            }
        }

        public void union(int x, int y){
            int A = find(x);
            int B = find(y);
            if(A == B) return;
            trees[A] = B;
            count--;
        }

        public boolean connected(int x, int y){
            int p = find(x);
            int q = find(y);
            return q == p;
        }

        public int find(int x){
            if(trees[x] == x) return x;
            trees[x] = find(trees[x]);
            return trees[x];
        }

        public int count(){
            return count;
        }
    }
}