package sort;
//优先队列,可以更高效地删除最大元素和插入元素。调整的过程没有使用递归调用，而是使用while循环
public class MaxPQ {
    private int[] a;
    private int N = 0;//a[0]不使用

    public MaxPQ(int maxN){
        a = new int[maxN];
    }

    public void insert(int v){
        a[++N] = v;
        swim(N);
    }

    public int delMax(){
        int max = a[1];
        exch(1,N--);
        sink(1);
        return max;
    }

    //自下向上
    private void swim(int k){
         while (k>1 && less(k/2,k)){
             exch(k/2,k);
             k = k/2;
         }
    }

    //自上向下
    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            //找出两个子节点中最大的那个
            if (j<N && less(j,j+1)){
                j++;
            }
            //如果这个元素比子节点中最大的那个都大的话，就不用退出循环
            if (!less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

     void exch(int i, int j)
    {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

     boolean less(int i, int j)
    {
        if (a[i] < a[j]) {return true;}
        else {return false;}
    }

}

class Run {
    public static void main(String[] args) {
        MaxPQ maxPQ = new MaxPQ(6);
        maxPQ.insert(3);
        maxPQ.insert(4);
        maxPQ.insert(6);
        maxPQ.insert(2);
        maxPQ.insert(9);
        int i = maxPQ.delMax();
        System.out.println(i);
    }
}