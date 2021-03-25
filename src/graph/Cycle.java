package graph;

//G是无环图吗?假设不存在自环或平行边,使用深度优先搜索
public class Cycle {
    private boolean hasCycle;
    private boolean[] marked;

    public Cycle(Graph g, int v){
        marked = new boolean[g.V()];
        for (int s = 0;s<g.V();s++ ){
            if (!marked[s]){
                bfs(g,s,s);
            }
        }
    }
    //v代表当前顶点,u代表其上一级顶点
    private void bfs(Graph g, int v, int u){
        marked[v] = true;
        for (Integer w:g.adj(v)) {
            //当其下一级顶点连接到已经标记的点,证明有环
            if (!marked[w]){
                bfs(g,w,v);
            }else if (w != u){
                hasCycle = true;
            }
        }
    }

    public boolean connect(){
        return hasCycle;
    }
}
