package basic;

import java.util.Iterator;
//下压栈，先进后出
public class Stack<Item> implements Iterable {

    private int N = 0; //元素数量
    private Node first;//栈顶元素

    private class  Node{
         Item item;
         Node next;
    }

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N>0;
    }

    public void push(Item item){
        Node oldFirst = first;
        //注意这时的初始化不可以在声明时初始，否则后面创建所有元素都是指向这个对象
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;

    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    @Override
    public Iterator iterator() {
        return  new StackIterator() ;
    }

    private class StackIterator implements Iterator{
        private Node temp = first;
        @Override
        public boolean hasNext() {
            return temp!=null;
        }

        @Override
        public Item next() {
            Item item = temp.item;
            temp = temp.next;
            return item;
        }
    }
}

class stackRun{
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(7);
        stack.push(9);
        stack.push(3);
        for (Object item:stack) {
            System.out.println(item);
        }

    }
}
