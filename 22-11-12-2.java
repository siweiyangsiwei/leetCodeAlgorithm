// leetcode: 1631   dijkstra算法
// 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

// 请你返回从左上角走到右下角的最小 体力消耗值 。


class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // 定义：从 (0, 0) 到 (i, j) 的最小体力消耗是 effortTo[i][j]
        int[][] effortTo = new int[m][n];
        // dp table 初始化为正无穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        effortTo[0][0] = 0;
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });
        pq.offer(new State(0,0,0));
        while(!pq.isEmpty()){
            State curState = pq.poll();
            int curx = curState.x;
            int cury = curState.y;
            int curEfforFromStart = curState.effortFromStart;
            if(curx == m - 1 &&  cury == n - 1) return curEfforFromStart;
            if(curEfforFromStart > effortTo[curx][cury]) continue;
            for(int[] edge : adj(heights,curx,cury)){
                int nextx = edge[0];
                int nexty = edge[1];
                int effortToNextNode = Math.max(
                    effortTo[curx][cury],
                    Math.abs(heights[curx][cury] - heights[nextx][nexty])
                );
                if(effortTo[nextx][nexty] > effortToNextNode){
                    effortTo[nextx][nexty] = effortToNextNode;
                    pq.offer(new State(nextx,nexty,effortToNextNode));
                }
            }
        }
        return -1;

    }

    // 方向数组，上下左右的坐标偏移量
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // 返回坐标 (x, y) 的上下左右相邻坐标
    List<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                // 索引越界
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }

    class State {
        // 矩阵中的一个位置
        int x, y;
        // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
        int effortFromStart;

        State(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }
}