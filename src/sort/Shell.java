package sort;
//希尔排序，使数组中任意间隔为 h 的元素都是有序的。
public class Shell {
    public static void main(String[] args) {
        int[] n={3,1,6,24,7,9,10,11,2,5,4,3,6,2,9,7};
        sort(n);
    }
    public static void sort(int[] n){
        int h = n.length/2;
        while (h>=1) {
            for (int i = h; i < n.length; i++) {
                for (int j = i; j >= h && n[j] < n[j - h]; j -= h) {
                    int temp = n[j - h];
                    n[j - h] = n[j];
                    n[j] = temp;
                }
            }
            show(n);
            h /= 2;
        }
    }
    public static void show(int[] n){
        for(int i:n){
            System.out.print(i+" ");
        }
        System.out.println("\n");
    }
}
