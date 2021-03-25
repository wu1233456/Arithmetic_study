package graph;
//G是二分图吗？（双色问题），使用深度搜索查找
public class TwoColor {
    private boolean[] marked;
    private boolean[] color; //标记结点的颜色
    private boolean isTwoColorable = true;

    public TwoColor(Graph g){
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int s = 0;s<g.V();s++){
            if (!marked[s]){
                dfs(g,s);
            }
        }
    }

    private void dfs(Graph g,int v){
        marked[v] = true;
        for (Integer w:g.adj(v)) {
            if (!marked[w]){
                color[w] = !color[v];
                dfs(g,w);
            }else {
                if (color[w] == color[v]){
                    isTwoColorable = false;
                }
            }
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }

}
