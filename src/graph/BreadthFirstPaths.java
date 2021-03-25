package graph;

import basic.Queue;
import basic.Stack;

//广度优先搜索查找
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }

    private void bfs(Graph g, int v){
        Queue<Integer> queue = new Queue<>();
        marked[v] = true;
        queue.enqueue(v);
        while (!queue.isEmpty()){
            Integer d = queue.dequeue();
            for (Integer w: g.adj(d)) {
                if (!marked[w]){
                    queue.enqueue(w);
                    marked[w] =true;
                    edgeTo[w] = d;
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> stack = new Stack<>();
        for (int x = v;x!=s;x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}

class BreadRun{
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
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, 0);
        Iterable<Integer> integers = paths.pathTo(5);
        for (Integer item:integers) {
            System.out.println(item);
        }
    }
}
