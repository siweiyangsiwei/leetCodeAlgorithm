// leetcode:1011 数组双指针
// 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

// 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。


class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0,right = 0;
        for(int i = 0; i < weights.length;i++){
            left = Math.max(left,weights[i]);
            right += weights[i];
        }
        
        while(left < right){
            int mid = left + (right - left) / 2;
            if(func(weights,mid) <= days){
                right = mid;
            }else if(func(weights,mid) > days){
                left = mid + 1;
            }
        }
        return right;
    }

    public int func(int[] weights,int store){
        int cap = 0;
        int day = 0;
        for(int i = 0; i < weights.length;){
            cap = 0;
            while(i < weights.length){
                if(cap + weights[i] > store) break;
                else cap += weights[i];
                    i++;
                
            }
            day++;
        }
        return day;
    }
}