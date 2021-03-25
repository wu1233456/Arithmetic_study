package search;

public class LinearProBingHashMap<K,V> {
    private int num;//键值对的数量
    private int capacity;//散列表的大小
    private K[] keys;
    private V[] values;

    public LinearProBingHashMap(int capacity) {
        this.num = 0;
        this.capacity = capacity;
        keys =  (K[])new Object[capacity];
        values = (V[])new Object[capacity];
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key,V value){
        if (num > capacity / 2){
            resize(capacity*2);
        }

        int index = hash(key);
        while (keys[index] != null && keys[index] != key){
            index = (index+1) % capacity;
        }

        values[index] = value;
        //为空时则插入，如果不为空时只需要更新值就可以了
        if (keys[index] == null){
            keys[index] = key;
            return;
        }
        num++;
    }

    public V get(K key){
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])){
            index = (index+1) % capacity;
        }
        // 若给定key在散列表中存在会返回相应value，否则这里返回的是null
        return values[index];
    }

    public void delete(K key){
        int index = hash(key);
        while (keys[index] != null && keys[index] != key){
            index = (index+1) % capacity;
        }

        keys[index] = null;
        values[index] = null;
        //i代表index的下一个
        for (int i = (index+1) % capacity;keys[i] !=null ;i=(index+1) % capacity){
            keys[index] = keys[i];
            values[index] = values[i];
            keys[i]=null;
            values[i]=null;
            index = (index+1) % capacity;
        }
        num--;
        if (num > 0 && num<capacity/8){
            resize(capacity/4);
        }
    }

    private void resize(int newCapacity){
        LinearProBingHashMap<K, V> newMap = new LinearProBingHashMap<>(newCapacity);
        for (int i=0;i<capacity;i++){
            if (keys[i]!=null){
                newMap.put(keys[i],values[i]);
            }
        }
        keys = newMap.keys;
        values = newMap.values;
        capacity = newCapacity;
    }
}
class LinearRun{
    public static void main(String[] args) {
        LinearProBingHashMap<Integer, String> map = new LinearProBingHashMap<>(9);
        map.put(2,"吴春霖");
        map.put(3,"吴春春");
        map.put(4,"吴春小");
        map.delete(4);
        String s = map.get(2);
        System.out.println(s);

    }
}
