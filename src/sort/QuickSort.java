package sort;
//快速排序，快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {7,14,9,3,5,10,3,6,48,25,69,24,11};
        sort(a,0,a.length-1);
        show(a);
    }
    public static void sort(int[] arr,int low ,int high){
        if (low>=high){return;}
//        int index = quicksort(arr, low, high);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                //        sort(arr,low,index-1);
//        sort(arr,index+1,high);
        //优化，从的排序长度较小时使用插入排序
        if (high-low<4) {
            insertsort(arr, low, high);
        }else {
            int index = quicksort(arr, low, high);
            sort(arr,low,index-1);
            sort(arr,index+1,high);
        }

    }
    public static int quicksort(int[] arr,int low,int high){
        int temp = arr[low];
        while (low<high){
            while (low<high && arr[high]>= temp){
                high--;
            }
            arr[low]=arr[high];
            while (low<high && arr[low]<= temp){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=temp;
        return low;
    }
    //插入排序
    public static void insertsort(int[] arr,int low,int high){
        for (int i = low+1;i<=high;i++){
            for(int j = i;j>low && arr[j] < arr[j-1];j--){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
            }
        }
    }
    public static void show(int[] n){
        for(int i:n){
            System.out.print(i+" ");
        }
        System.out.println("\n");
    }
}
