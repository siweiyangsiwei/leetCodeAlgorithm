// leetcode:74 数组双指针
// 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null) return false;
        int left = 0,right = matrix.length * matrix[0].length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int x = mid / matrix[0].length,y = mid % matrix[0].length;
            if(matrix[x][y] > target){
                right = mid - 1;
            }else if(matrix[x][y] < target){
                left = mid + 1;
            }else if(matrix[x][y] == target){
                return true;
            }
        }
        return false;
    }
}