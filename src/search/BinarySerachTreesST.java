package search;
//二叉查找树
public class BinarySerachTreesST {
    private Node root; //二叉查找树的根结点
    private class Node{
        private int key;//键
        private String val; //值
        private Node left,right;
        private int N; //以该结点为根的子树中的结点总数

        public Node(int key,String val,int N){
            this.key = key;
            this.val = val;
            this.N = N;
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

    public String get(int key){
        return get(root,key);
    }
    private String get(Node x,int key){
        if (x == null){
            return null;
        }
        if (x.key > key){
            return get(x.left,key);
        }else if(x.key < key){
            return get(x.right,key);
        }else {
            return x.val;
        }
    }

    public void put(int key,String val){
        root = put(root,key,val);
    }
    private Node put(Node x ,int key ,String val){
        if (x == null) {
            return new Node(key,val,1);
        }
        if (x.key > key){
             x.left = put(x.left,key,val);
        }else if (x.key < key){
             x.right = put(x.right,key,val);
        }else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) +1;
        return x;
    }

    public Integer min(){
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }
    public Integer max(){
        return max(root).key;
    }
    private Node max(Node x){
        if (x.left == null){
            return x;
        }
        return max(x.left);
    }
    //向下取整，找到小于等于key的值
    public Integer floor(int key){
        Node x = floor(root,key);
        if (x == null){
            return null;
        }
        return x.key;
    }
    private Node floor(Node x ,int key){
        if (x == null) {
            return null;
        }
        if (x.key > key){
            return floor(x.left,key);
        }
        if (x.key == key){
            return x;
        }
        Node t = floor(x.right, key);
        if (t != null){
            return t;
        }else {
            return x;
        }

    }

    //排名，排名是从0开始的
    public Integer select(int k){
        return  select(root,k).key;

    }
    private Node select(Node x,int k){
        //返回报名为k的结点
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t>k){
            return select(x.left,k);
        }else if (t<k){
            //注意传到右边时k的变化
            return select(x.right,k-t-1);
        }else {
            return x;
        }
    }

    //根据键来返回相对应的排名
    public int rank(int key){
        return rank(key,root);
    }
    private int rank(int key,Node x){
        //返回以x为根结点的子树中小于x.key的键的数量
        if (x == null){
            return 0;
        }
        if (x.key > key){
            return rank(key,x.left);
        }else if (x.key < key){
            return 1+size(x.left)+rank(key,x.right);
        }else {
            return size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if (x.left == null) {return x.right;}
        x.left = deleteMin(x.left);
        x.N = size(x.left) +size(x.right) +1;
        return x;
    }

    public void delete(int key){
        root = delete(root,key);
    }
    private Node delete(Node x,int key){
        if (x == null) {
            return null;
        }
        if (key < x.key) {
            x.left = delete(x.left,key);
        }else if (key > x.key){
            x.right = delete(x.right,key);
        }else {
            if (x.right == null){
                return x.left;
            }
            if (x.left == null){
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


}

class BinarySearchTreeRun{
    public static void main(String[] args) {

    }
}
