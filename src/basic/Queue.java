package basic;

import java.util.Iterator;
//队列，先进先出
public class Queue<Item> implements Iterable<Item>{
    private int N;
    private Node first;
    private Node last;


    private class Node{
        Item item;
        Node next;
    }

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return !(N>0);
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        private  Node temp = first;
        @Override
        public boolean hasNext() {
            return temp == null;
        }

        @Override
        public Item next() {
            Item item = temp.item;
            temp = temp.next;
            return item;
        }
    }
}

class QueueRun{
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(4);
        for (Integer item:queue) {
            System.out.println(item);
        }


    }
}


