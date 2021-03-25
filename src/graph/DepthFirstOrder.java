package graph;

import basic.Queue;
import basic.Stack;

//有向图中基于深度优先搜索的顶点排序
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;//所有顶点的前序排列
    private Queue<Integer> post;//所有顶点的后序排列
    private Stack<Integer> reversePost;//所有顶点的逆后排序

    public DepthFirstOrder(Digraph g){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.V()];
        for (int s = 0;s<g.V();s++){
           if (!marked[s]){
               dfs(g,s);
           }
        }
    }

    private void dfs(Digraph g,int v){
        marked[v] = true;
        pre.enqueue(v);
        for (Integer w:g.adj(v)){
            if (!marked[w]){
                dfs(g,w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
