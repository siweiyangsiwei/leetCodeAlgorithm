// leetcode 1094
// 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）

// 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。

// 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[1000];
        int[] result = new int[1000];
        for(int[] trip: trips){
            arr[trip[1]] += trip[0];
            if(trip[2]>= arr.length) continue;
            arr[trip[2]] -= trip[0];
        }
        result[0] = arr[0];
        if(result[0] > capacity) return false;
        for(int i = 1; i < arr.length;i++){
            result[i] = result[i - 1] + arr[i];
            if(result[i] > capacity) return false;
        }
        return true;
    }
}