package basic;

import java.util.Iterator;
//可以动态改变长度的下压栈
public class ResizingArrayStack<Item> implements Iterable{
    private int N = 0;//元素数量
    private Item[] a = (Item[]) new Object[1];

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }

    public void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0;i<N;i++ ){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item){
        if (N == a.length){
            resize(a.length*2);
        }
        a[N++] = item;
    }
    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N < a.length /8){
            resize(a.length/2);
        }
        return item;
    }



    @Override
    public Iterator iterator() {
        return  new ResizingArrayIterator();
    }

    private class  ResizingArrayIterator implements Iterator{
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Object next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}

class ResizingRun{
    public static void main(String[] args) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        stack.push(1);
        stack.push(9);
        stack.push(3);
        System.out.println(stack.pop());
    }
}