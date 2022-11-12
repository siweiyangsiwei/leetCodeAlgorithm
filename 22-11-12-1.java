// leetcode: 743  dijkstra算法
// 有 n 个网络节点，标记为 1 到 n。

// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。

// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。

class Solution {
    List<int[]>[] graph;
    public int networkDelayTime(int[][] times, int n, int k) {
        graph = new LinkedList[n+1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] arr : times){
            int from = arr[0];
            int to = arr[1];
            int time = arr[2];
            graph[from].add(new int[]{to,time});
        }
        int[] times1 = dijkstra(k,graph);
        int res = 0;
        for(int i = 1; i < times1.length; i++){
            if (times1[i] == Integer.MAX_VALUE) {
                // 有节点不可达，返回 -1
                return -1;
            }
            res = Math.max(times1[i],res);
        }
        return res;

    }

    int[] dijkstra(int start, List<int[]>[] graph) {
        int n= graph.length;
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
             return a.distFromStart - b.distFromStart;
        });
        State s = new State(start,0);
        pq.offer(s);
        res[start]  = 0;
        while(!pq.isEmpty()){
            State cur = pq.poll();
            int curId = cur.id;
            int curDist = cur.distFromStart;
            if(curDist > res[curId]) continue;
            for(int[] next : graph[curId]){
                int nextId = next[0];
                int distToNext = res[curId] + next[1];
                if(distToNext < res[nextId]){
                    res[nextId] = distToNext;
                    pq.offer(new State(nextId,distToNext));
                }
            }
        }
        return res;
    }

    class State{
        int id;
        int distFromStart;
        public State(int id,int distFromStart){
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }
}