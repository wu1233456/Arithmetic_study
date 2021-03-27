package sort;

public class IndexMinPQ<Key extends Comparable<Key>>{
    private int N;//pq中的元素的数量
    private int[] pq;//二叉树，序号 ==> 索引
    private int[] qp;//索引 ==> 序号,主要是用来根据元素的索引来判断有无存在于优先队列中,不存在时值为-1
    private Key[] keys;//索引==>元素的值

    public IndexMinPQ(int maxN){
        pq = new int[maxN+1];//pq[0]不用
        qp = new int[maxN+1];
        keys = (Key[]) new Comparable[maxN+1];
        for (int i = 0;i<maxN+1;i++){
            qp[i] = -1;
        }
    }
    public boolean isEmpty(){
        return N==0;
    }
    public boolean contains(int k){
        return qp[k] != -1;
    }
    public void insert(int k,Key key){
        keys[k] = key;
        pq[++N] = k;
        qp[k] = N;
        swim(N);
    }

    private void swim(int n){
        while (n>1 && less(n,n/2)){
            exch(n,n/2);
            n = n/2;
        }
    }

    private void sink(int n){
        while (2*n+1<=N){
            int j = 2*n+1;
            if (less(2*n,2*n+1)){
                j = 2*n;
            }
            if (!less(n,j)){
                exch(n,j);
                n = j;
            }
        }
    }

    public Key min(){
        return keys[pq[1]];
    }

    public int delMin(){
        int indexOfMin = pq[1];
        exch(1,N--);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        swim(N);
        return indexOfMin;
    }

    //i是不是比j小
    private boolean less(int i,int j){
       return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i,int j){
        int temp = pq[j];
        pq[j] = pq[i];
        pq[i] = temp;

        //更新qp中索引对应的序号
        qp[pq[j]] = j;
        qp[pq[i]] = i;
    }

    public int minIndex(){
        return pq[1];
    }

    public void change(int k,Key key){
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k){
        int index = qp[k];
        exch(index,N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;

    }

    public static void main(String[] args) {
        IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<>(4);
        indexMinPQ.insert(1,3);
        indexMinPQ.insert(2,2);
        indexMinPQ.insert(3,1);
        indexMinPQ.insert(4,7);
        System.out.println(indexMinPQ.min());
        indexMinPQ.change(3,5);
        System.out.println(indexMinPQ.min());
    }
}
