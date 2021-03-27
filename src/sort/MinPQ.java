package sort;
//最小值的优先队列，没有跟最大值的优先队列一样，使用while循环来调整，这里我使用了递归来进行调用
public class  MinPQ<Key extends Comparable<Key>> {
    private Key[] a;//用数组存储
    private int N = 0;//节点的数量

    public MinPQ(int maxN){
        a = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }
    public void insert(Key v){
        a[++N] = v;
        swim(N);
    }

    public Key delMin(){
        Key res = a[1];
        a[1] = a[N--];
        a[N+1] = null;
        sink(1);
        return res;
    }
    //上浮
    //k表示v的位置
    private void swim(int k){
        //如果此时的k>1不放在前面的话，就会导致后面的空指针，因为只有前面的条件成立了才能保证后面的条件的正常判断
        if (k > 1 && !less(k,k/2)){
            //交换位置后，继续向上浮动，直到找到合适位置
            exch(k,k/2);
            swim(k/2);
        }
    }

    //下沉
    private void sink(int k){
        if (2*k+1>N){
            return;
        }
        int j = 2*k;
        //找出子节点中最小的那个
        if (less(2*k,2*k+1)){
            j ++ ;
        }
        if (less(k,j)){
            exch(k,j);
            sink(j);
        }
    }
    //交换两个结点的位置
    private void exch(int i,int j){
        Key temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(int i,int j){
        return a[i].compareTo(a[j]) > 0;
    }

    public static void main(String[] args) {
        MinPQ<Integer> minPQ = new MinPQ<>(8);
        minPQ.insert(1);
        minPQ.insert(5);
        minPQ.insert(4);
        minPQ.insert(3);
        minPQ.insert(7);
        System.out.println(minPQ.delMin());
        System.out.println(minPQ.delMin());
    }

}
