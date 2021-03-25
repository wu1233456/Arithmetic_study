package sort;
//自顶向下的归并排序，要将一个数组排序，可以先（递归地）将它分成两半分别排序，然后将结果归并起来。
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {3,2,8,4,6,5,1,9,8,3,5,2,4,7,1};
        sort(a);
        show(a);
    }
    public static void sort(int[] n ){
        int[] temp = new int[n.length];
        sort(n,0,n.length-1,temp);
    }
    public static void sort(int[] n,int left,int right,int[] temp){
        if (left>=right){return;}
        int mid = (right+left)/2;
        //左边排序
        sort(n,left,mid,temp);
        //右边排序
        sort(n,mid+1,right,temp);
        //合并
        merge(n,temp,left,right,mid);
    }
    private static void merge(int[] n,int[] temp,int left,int right,int mid){
       int i = left;
       int j = mid+1;
       int t = 0;
       while (i<=mid && j<=right){
           if (n[i]<=n[j]){
               temp[t++] = n[i++];
           }else {
               temp[t++] = n[j++];
           }
       }
       while (i<=mid){
           temp[t++] = n[i++];
       }
       while (j<=right){
           temp[t++] = n[j++];
       }
       t=0;
       while (left<=right){
           n[left++] = temp[t++];
       }
    }
    public static void show(int[] n){
        for(int i:n){
            System.out.print(i+" ");
        }
        System.out.println("\n");
    }
}
