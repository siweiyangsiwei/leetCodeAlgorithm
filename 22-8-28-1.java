// leetcode:1352 前缀和
// 请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法：

// 1. add(int num)

// 将数字 num 添加到当前数字列表的最后面。
// 2. getProduct(int k)

// 返回当前数字列表中，最后 k 个数字的乘积。
// 你可以假设当前列表中始终 至少 包含 k 个数字。
// 题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。
class ProductOfNumbers {
    // 前缀积数组
    // preProduct[i] / preProduct[j] 就是 [i, j] 之间的元素积
    ArrayList<Integer> preProduct = new ArrayList<>();
    public ProductOfNumbers() {
    // 初始化放⼀个 1，便于计算后续添加元素的乘积
    preProduct.add(1);
    }
    public void add(int num) {
    if (num == 0) {
    // 如果添加的元素是 0，则前⾯的元素积都废了
    preProduct.clear();
    preProduct.add(1);
    return;
    }
    int n = preProduct.size();
    // 前缀积数组中每个元素
    preProduct.add(preProduct.get(n - 1) * num);
    }
    public int getProduct(int k) {
    int n = preProduct.size();
    if (k > n - 1) {
    // 不⾜ k 个元素，是因为最后 k 个元素存在 0
    return 0;
    }
    // 计算最后 k 个元素积
    return preProduct.get(n - 1) / preProduct.get(n - k - 1);
    }
   }
   
   /**
    * Your ProductOfNumbers object will be instantiated and called as such:
    * ProductOfNumbers obj = new ProductOfNumbers();
    * obj.add(num);
    * int param_2 = obj.getProduct(k);
    */