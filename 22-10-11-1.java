// leetcode:460 数据结构设计
// 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

// 实现 LFUCache 类：

// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
class LFUCache {

    public HashMap<Integer,Integer> keyToVal;
    public HashMap<Integer,Integer> keyToFre;
    public HashMap<Integer,LinkedHashSet<Integer>> freToKey;
    int minFre;
    int cap;

    public LFUCache(int capacity) {
            keyToFre = new HashMap();
            keyToVal = new HashMap();
            freToKey = new HashMap();
            minFre = 0;
            this.cap = capacity;
    }
    
    public int get(int key) {
        if(!keyToVal.containsKey(key)) return -1;
        increaseFre(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        if(this.cap <= 0) return ;
        if(keyToVal.containsKey(key)){
            keyToVal.put(key,value);
            increaseFre(key);
            return;
        }

        if(keyToVal.size() >= this.cap){
            removeMinFreqKey();
        }

        keyToVal.put(key,value);
        keyToFre.put(key,1);
        freToKey.putIfAbsent(1, new LinkedHashSet<>());
        freToKey.get(1).add(key);
        this.minFre = 1;
    }

    public void increaseFre(int key){
        int fre = keyToFre.get(key);
        keyToFre.put(key,fre + 1);
        freToKey.get(fre).remove(key);
        freToKey.putIfAbsent(fre + 1,new LinkedHashSet<>());
        freToKey.get(fre + 1).add(key);
        if (freToKey.get(fre).isEmpty()) {
            freToKey.remove(fre);
        // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (fre == this.minFre) {
                this.minFre++;
            }
        }
    }

    public void removeMinFreqKey(){
        LinkedHashSet<Integer> keyList = freToKey.get(this.minFre);
        int deletedKey =keyList.iterator().next();
        keyList.remove(deletedKey);
        if(keyList.isEmpty()){
            freToKey.remove(this.minFre);
        }
        keyToVal.remove(deletedKey);
        keyToFre.remove(deletedKey);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */