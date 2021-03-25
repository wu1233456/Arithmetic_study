package basic;
//动态性连通性问题
public class UnionFind {
    private int[] parent;
    private int[] size;//记录每个根结点的对应的分量的大小,即里面包含的总结点的数量
    private int count;//区分每一个连通量

    public  UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
        }
        count=n;
    }

    /* 将 p 和 q 连接 */
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        //如果连通的话，则不做操作
        if (pRoot == qRoot) {return;}
        //将小树连接到大树上
        if (size[pRoot]>=size[qRoot]){
            parent[qRoot]= parent[pRoot];
            size[pRoot]+=size[qRoot];
        }else {
            parent[pRoot]= parent[qRoot];
            size[qRoot]+=size[pRoot];
        }
        count--;//分量的数量减少一个
    }

    //找到根结点
    private int find(int n){
        while (n != parent[n]){
            n = parent[n];
        }
        return n;
    }

    public int count(){
        return count;
    }
}
