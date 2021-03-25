package graph;

import basic.Bag;
//加权无向图的数据类型
public class EdgeWeightedGraph {
    private final int V; //顶点总数
    private int E;//边的总数
    private Bag<Edge>[] adj;//邻接表

    public EdgeWeightedGraph(int V){
        this.V = V;
        adj = new Bag[V];
        for (int v = 0;v<V;v++){
            adj[v] = new Bag<>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }



}
