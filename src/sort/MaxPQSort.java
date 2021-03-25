package sort;
//堆排序
public class MaxPQSort {
    public   MaxPQSort(int[] a){
        int N = a.length-1;
        //从最后一个非叶子节点开始往前，构造堆
        for (int k = N/2;k>=1;k--){
            sink(a,k,N);
        }
        while (N > 1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }

    //自上向下
    private void sink(int[] a,int k,int N){
        while (2*k <= N){
            int j = 2*k;
            //找出两个子节点中最大的那个
            if (j<N && less(a,j,j+1)){
                j++;
            }
            //如果这个元素比子节点中最大的那个都大的话，就不用退出循环
            if (!less(a,k,j)){
                break;
            }
            exch(a,k,j);
            k = j;
        }
    }

    void exch(int[] a,int i, int j)
    {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    boolean less(int[] a,int i, int j)
    {
        if (a[i] < a[j]) {return true;}
        else {return false;}
    }
    private static void show(int[] n){
        for(int i:n){
            System.out.print(i+" ");
        }
        System.out.println("\n");
    }
}
 class MaxPQSortRun{
    public static void main(String[] args) {
        //a[0]是不参与排序的
        int[] a = {0,29,11,56,34,56,22,35,1,7,9,25};
        MaxPQSort maxPQSort = new MaxPQSort(a);
        show(a);
    }
     public static void show(int[] n){
         for(int i:n){
             System.out.print(i+" ");
         }
         System.out.println("\n");
     }
}