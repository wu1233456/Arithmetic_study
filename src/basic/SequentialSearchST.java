package basic;
//链表，可以搜索与插入
public class SequentialSearchST<K ,V> {
    private Node head ;
    private class Node{
         Node next;
         V value;
         K key;
         public Node(V value,K key,Node next){
             this.key = key;
             this.value = value;
             this.next = next;
         }
    }

    public V get(K key){
        for (Node temp = head;temp != null;temp= temp.next){
            if (temp.key.equals(key)){
                return temp.value;
            }
        }
        return null;
    }

    public void put(K key,V value) {
        // 先查找表中是否已经存在相应的key
        Node temp;
        for ( temp= head;temp != null;temp= temp.next){
            if (temp.key.equals(key)){
              temp.value = value;
              return;
            }
        }
        temp.key = key;
        temp.value = value;
    }

}
