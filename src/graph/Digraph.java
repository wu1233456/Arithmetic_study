package graph;

import basic.Bag;

public class Digraph  {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;//保存顶点与其他顶点的边
    public Digraph(int v){
        V = v;
        E = v;
        adj = new Bag[V];
        for (int i =0;i<v;i++){
            adj[i] = new Bag<>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    /**
     * @param v 父顶点
     * @param w 子顶点
     */
    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    //将所有的路径方向进行反转
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0;v < V;v++){
            for (Integer w:adj[v]) {
                R.addEdge(w,v);
            }
        }
        return R;
    }
}
