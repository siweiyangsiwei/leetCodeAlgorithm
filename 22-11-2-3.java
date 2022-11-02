// leetcode: 773 BFS算法
// 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.

// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。

// 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。

class Solution {
    public int slidingPuzzle(int[][] board) {
        int m = 2,n = 3;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n; j++){
                sb.append(board[i][j]);
            }
        }
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        String start = sb.toString();
        String target = new String("123450");
        q.offer(start);
        visited.add(start);
        int[][] neighbor = new int[][]{
            {1,3},
            {0,4,2},
            {1,5},
            {0,4},
            {3,1,5},
            {4,2}
        };
        int step = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                String cur = q.poll();
                if(target.equals(cur)) return step;
                int idx = 0;
                for(;cur.charAt(idx) != '0';idx++);
                for(int adj : neighbor[idx]){
                    String new_board = swap(cur.toCharArray(),idx,adj);
                    if(!visited.contains(new_board)){
                        visited.add(new_board);
                        q.offer(new_board);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}