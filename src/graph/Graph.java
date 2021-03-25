package graph;

import basic.Bag;
//图的表示
public class Graph {
    private  int V;//顶点数目
    private int E;//边的数目
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0;i<V;i++){
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int v , int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

}


class GrapRun{
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(2,3);
        graph.addEdge(1,2);
        graph.addEdge(3,4);
        graph.addEdge(3,1);
        Iterable<Integer> adj = graph.adj(3);
        System.out.println(adj);
        for (Integer item:adj) {
            System.out.println(item);
        }
    }
}

