package sort;
//最小值的优先队列
public class MinPQ {
    private int[] a;//用数组存储
    private int N = 0;//节点的数量

    public MinPQ(int maxN){
        a = new int[maxN];
    }

    public void insert(int v){
        a[++N] = v;
        //todo 调用上浮
    }
    //上浮
    //k表示v的位置
    private void swim(int k){
        if (a[k]<a[k/2]){
            //交换位置后，继续向上浮动，直到找到合适位置
            exch(k,k/2);
            swim(k/2);
        }
    }

    //下沉
    private void sink(int v){
        
    }
    //交换两个结点的位置
    private void exch(int i,int j){

    }

}
