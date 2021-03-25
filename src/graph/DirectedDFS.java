package graph;
//有向图的可传达性，判断从给定的一个或者一组顶点能到达哪些其他顶点
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Graph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    public DirectedDFS(Graph g,Iterable<Integer> sources){
        marked = new boolean[g.V()];
        for (Integer v:sources) {
            if (!marked[v]){
                dfs(g,v);
            }
        }
    }

    private void dfs(Graph g,int v){
        marked[v] = true;
        for (Integer w:g.adj(v)) {
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(3,4);
        G.addEdge(4,5);

    }
}
