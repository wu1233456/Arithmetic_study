package basic;

import java.util.Iterator;
//背包，并不关注如何取出来，所以只提供一个可供遍历的方法就行
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private class Node{
        private Item item;
        private Node next;
    }

    public void add(Item item){
        Node oldFirst = first;
        //first结点需要在这里创建，因为每一个first结点都是一个新结点
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator{
        private Node temp = first;
        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Item next() {
            Item item = temp.item;
            temp = temp.next;
            return item;
        }
    }
}

class BagRun{
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        bag.add(3);
        bag.add(4);
        bag.add(5);
        for (Integer item:bag) {
            System.out.println(item);
        }
    }
}