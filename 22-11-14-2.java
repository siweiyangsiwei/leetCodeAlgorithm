// leetcode: 51  回溯算法
// 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
class Solution {
    List<List<String>> res ;
    public List<List<String>> solveNQueens(int n) {
        res = new LinkedList<>();
        ArrayList<char[]> temp  = new ArrayList<>();
        for(int i = 0; i < n; i++){
            char[] temp1 = new char[n];
            for(int j = 0; j < n; j++){
                temp1[j] = '.';
            }
            temp.add(temp1);
        }
        backtrack(temp,0);
        return res;
    }

    public void backtrack(ArrayList<char[]> temp,int row){
        if(row == temp.size()){
            ArrayList<String> arr = new ArrayList<>();
            for(int i = 0; i < temp.size();i++){
                char[] temp1 = temp.get(i);
                arr.add(new String(temp1));
            }
            res.add(arr);
            return;
        }
        char[] chars = temp.get(row);
        for(int col = 0; col < chars.length; col++){
            if(!isValid(temp,row,col)) continue;
            chars[col] = 'Q';
            backtrack(temp,row + 1);
            chars[col] = '.';
        }
    }

    public boolean isValid(ArrayList<char[]> temp,int row,int col){
        int n = temp.size();
        for(int i = 0; i < row; i++){
            if(temp.get(i)[col] == 'Q'){
                return false;
            }
        }
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (temp.get(i)[j] == 'Q')
                return false;
        }
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (temp.get(i)[j] == 'Q')
                return false;
        }
        return true;
    }
}