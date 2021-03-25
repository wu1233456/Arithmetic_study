package graph;
//使用深度优先找出图中所有连通分量
public class CC {
    private boolean[] marked;
    private int[] id;//记录各个顶点的连通量
    private int count;//区分不同连通

    public CC(Graph g){
        marked = new boolean[g.V()];
        count = 0;
        for (int i = 0;i<g.V();i++){
            //注意这里也要进行是否进过的判断
            if (!marked[i]){
                dfs(g,i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        for (Integer w:g.adj(v)) {
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }
    public boolean connected(int v,int w){
        return  id[w] == id[v];
    }
    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

}
