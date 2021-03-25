package graph;

import basic.Stack;
//深度搜索查找，用来解决最短路径问题
public class DepthFirstPaths {
    private boolean[] marked;//标记是否经过该顶点
    private int[] edgeTo;//保存到父顶点的链接
    private int s;//起点

    public DepthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g,s);
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for (Integer w: g.adj(v)) {
            //后面到达的路径不进行保存，只保留之前先到达的
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Object> paths = new Stack<>();
        for (int x = v;x!=s;x = edgeTo[x]){
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }

}

class DepthRun{
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0,5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.addEdge(2,1);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        DepthFirstPaths paths = new DepthFirstPaths(graph, 0);
        Iterable<Integer> integers = paths.pathTo(4);
        for (Integer item:integers) {
            System.out.println(item);
        }
    }
}
