package graph;

import basic.Queue;
import sort.MinPQ;

public class LazyPrimMST {
    private boolean[] marked;//最小生成树的顶点
    private Queue<Edge> mst;//最小生成树的边
    private MinPQ<Edge> pq;//横向边，包括失效的

    public LazyPrimMST(EdgeWeightedGraph G){
            marked = new boolean[G.V()];
            mst = new Queue<>();
            //书中这里不需要传长度，那这样的话，这个优先队列估计应该不是用数组实现的，或者说具有动态调整数组的功能，
            //这里为了简单起见，直接设置一个最大的长度
            pq = new MinPQ<>(G.V());
            visit(G,0);
            while (!pq.isEmpty()){
                //每次都会从添加进的所有边中找出最小的边，这样就可以保证最后的总和是最小的（是跟之前的所有的进行对比，不单单是跟这个顶点的边进行对比）
                Edge e = pq.delMin();
                int v = e.either() , w = e.other(v);
                //跳过失效的边
                if (marked[v] && marked[w]){
                    continue;
                }
                //将边添加到树中
                if (!marked[v]){
                    visit(G,v);
                }
                if (!marked[w]){
                    visit(G,w);
                }
            }

    }

    //标记顶点并将所有连接v和未被标记顶点的边加入pq
    private void visit(EdgeWeightedGraph G,int v){
        marked[v] = true;
        for (Edge e:G.adj(v)) {
            if (!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
    //最小生成树的权重
    public double weight(){
        double weight = 0;
        while (!mst.isEmpty()){
            Edge e = mst.dequeue();
            weight += e.weight();
        }
        return weight;
    }



}
