package search;
//散列表
class ChainingHashSet<K, V> {

    private int num;       			// 当前散列表中的键值对总数
    private int capacity;  			// 散列表的大小
    private SeqSearchST<K,V>[] st;   // 链表对象数组

    // 构造函数
    public ChainingHashSet(int initialCapacity){
        capacity = initialCapacity;
        st =new  SeqSearchST[capacity];
        for(int i = 0; i < capacity; i++){
            st[i] = new SeqSearchST<>();
        }
    }

    // hash()方法
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public V get(K key){
        return  st[hash(key)].get(key);
    }

    public void put(K key, V value){
        st[hash(key)].put(key, value);
    }
}

// SeqSearchST基于链表的符号表实现
class SeqSearchST<K, V>{
    private Node first;
    // 结点类
    private class Node{
        K key;
        V value;
        Node next;
        // 构造函数
        public Node(K key, V val, Node next){
            this.key = key;
            this.value = val;
            this.next = next;
        }
    }

    // get()方法
    public V get(K key) {
        for(Node node = first; node != null; node = node.next){
            if(key.equals(node.key)){
                return node.value;
            }
        }
        return null;
    }
    // put()方法
    public void put(K key, V value) {
        // 先查找表中是否已经存在相应的key
        Node node;
        for(node = first; node != null; node = node.next){
            if(key.equals(key)){
                node.value = value;  // 如果key存在，就把当前value插入node.next中
                return;
            }
        }
        // 表中不存在相应的key，直接插入表头
        first = new Node(key, value, first);
    }
}

class SeqRun{
    public static void main(String[] args) {
        ChainingHashSet se = new ChainingHashSet<>(90);
        se.put(1,"吴春霖");
        se.put(2,"丁华丽");
        se.put(3,"吴春春");
        String s = (String) se.get(1);
        System.out.println(s);
    }
}
