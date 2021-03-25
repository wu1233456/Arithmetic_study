package search;
//红黑树，这里又是2-3树，因为不能存在4节点

/**
 * 要求：
 * 1、不能存在右红色结点
 * 2、不能在左边存在两个连续的红色结点
 * 3、根结点必须要是黑色
 */
public class RedBlackBST {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
     private Node root;
    private class Node{
         int key;//键
         String val; //值
         Node left,right;
         boolean color;
         int N;
        public Node(int key,String val,int N,boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    public Integer size(){
        return size(root);
    }
    private Integer size(Node x){
        if (x == null){
            return 0;
        }else {
            return x.N;
        }
    }

    private boolean isRed(Node h){
        if (h == null){
            return false;
        }
        return h.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
    //用来转换一个结点的两个红色子结点的颜色
    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(int key,String val){
        root = put(root,key,val);
        //当两个子节点都是红色时，这时需要进行颜色转换，就会导致根结点变成红色，所以一定要在最后把根结点设为黑色，这时它们的高度会加一
        root.color = BLACK;
    }
    private Node put(Node h,int key,String val){
        if (h == null){
            return new Node(key,val,1,RED);
        }
        if (key < h.key){
            h.left = put(h.left,key,val);
        }else if (key > h.key){
            h.right = put(h.right,key,val);
        }else {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) +1;
        return h;
    }

    //删除最小键
    private Node moveRedLeft(Node h){
        flipColors(h);
        //只有左键才会是红色，所以只用判断右边的左键是不是红色就可以知道右边是不是3节点了，
        // 如果是的话，就可以从中借一个，如果不是的话，就直接把这个节点合并为与父节点一起合并为一个4节点
        if (isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public void delMin(){
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delMin(root);
        if (size(root) !=0){
            root.color = BLACK;
        }
    }

    private Node delMin(Node h){
        if (h.left == null){
            //当这个节点的左侧没有了，则该节点是最小的，返回null，删除这个最小键
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)){
            //h的左子节点为一个2节点,则此时需要将其转成3或者4节点
            h = moveRedLeft(h);
        }
        h.left = delMin(h.left);
        return balance(h);
    }
    private Node balance(Node h){
        if (isRed(h.right)){
            h = rotateLeft(h);
        }
        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
     }

     public void delMax(){
         if (!isRed(root.left) && !isRed(root.right)){
             root.color = RED;
         }
         root = delMax(root);
         if (size(root) !=0){
             root.color = BLACK;
         }
     }

     private Node delMax(Node x){
        if (isRed(x.left)){
            x = rotateRight(x);//保证方向的一致性
        }
        if (x.right == null){
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)){
            //右子节点为2节点的话就运行这个函数将其变成至少3节点
            x = moveRedRight(x);
        }
        x.right = delMax(x.right);
        return balance(x);

     }

     private Node moveRedRight(Node h){
        flipColors(h);
        if (isRed(h.left.left)){
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
     }

     public void delete(int key){
        if (!isRed(root.right) && !isRed(root.left)){
            root.color = RED;
        }
        root = delete(root,key);
         System.out.println(root.val);
         if (size(root) !=0){
             root.color = BLACK;
         }
     }

     private Node delete(Node h,int key){
         System.out.println(h.val);
        if (key < h.key){
            if (!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left ,key);
        }else {
            if (isRed(h.left)){
                h = rotateRight(h);
            }
            if (key == h.key && h.right == null){
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if (key == h.key){
                Node min = min(h.right);
                 h.val = min.val;
                 h.key = min.key;
                 h.right = delMin(h.right);

            }else{
                h.right = delete(h.right,key);
            }
        }
        return balance(h);
     }

    private Node min(Node x){
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }
}

class RedBlackRun{
    public static void main(String[] args) {
        RedBlackBST redBlackBST = new RedBlackBST();
        redBlackBST.put(5,"5");
        redBlackBST.put(6,"6");
        redBlackBST.put(1,"1");
        redBlackBST.put(39,"39");
        Integer size = redBlackBST.size();
        System.out.println(size);
    }

}
