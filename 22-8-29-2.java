// leetcode:1109 前缀和
// 这里有 n 个航班，它们分别从 1 到 n 进行编号。

// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n + 1];
        for(int[] booking : bookings){
            int i = booking[0];
            int j = booking[1];
            int x = booking[2];
            arr[i] += x;
            if(j + 1 > n) continue;
            arr[j + 1] -= x;
        }
        int[] result = new int[n];
        result[0] = arr[1];
        for(int i = 1; i < n; i++){
            result[i] = result[i - 1] + arr[i + 1];
        }
        return result;
    }
}
