package graph;

import sort.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;//距离边的最近的边
    private double[] distTo;//distTo[w]=edgeTo[w].weight()
    private boolean[] marked;//如果v在树中则为true
    private IndexMinPQ<Double> pq;//有效的横切边

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int i = 0;i<G.V();i++){
            distTo[i] = Double.POSITIVE_INFINITY;//使得每个权重为正无穷
        }
        distTo[0] = 0.0;
        pq.insert(0,0.0);//用顶点0和权重0初始化pq，因为顶点0刚开始时与树的距离只有0，所以权重肯定是0
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G,int v){
        marked[v] = true;
        for (Edge e:G.adj(v)){
            int w = e.other(v);
            if (marked[w]){
                continue;
            }
            if (e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                //注意这里是否加入队列与是否加入树中是有区别的
                if (pq.contains(w)){
                    pq.change(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }


 }
