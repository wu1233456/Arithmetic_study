package graph;

import basic.Stack;
//寻找有向环
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;//递归调用的栈中的所有顶点（如果存在）,用来标记当前环，如果访问到的已标点的点存在于这个栈中的话，则表示构成一个“有向环”
    private Stack<Integer> cycle;//有向环中的所有顶点（如果存在）

    public DirectedCycle(Digraph g){
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for (int s=0;s < g.V();s++){
                if (!marked[s]){
                    dfs(g,s);
                }
        }
    }

    private void dfs(Digraph g,int v){
        marked[v] = true;
        onStack[v] = true;
        for (Integer w:g.adj(v)){
            if (hasCycle()){
                return;
            }else if (!marked[w]){
                edgeTo[w]=v;
                dfs(g,w);
            }else if (onStack[w]){
                cycle = new Stack<>();
                for (int x = v;x!=w;x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                //环，所以要封闭的
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }
}

class DirectedCycleRun{
    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(3,4);
        G.addEdge(4,5);
        DirectedCycle directedCycle = new DirectedCycle(G);
    }
}
