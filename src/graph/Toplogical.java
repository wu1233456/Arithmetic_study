package graph;
//拓扑排序，使用了 DepthFirstOrder 类和 DirectedCycle 类来返回一幅有向无环图的拓扑排序
public class Toplogical {
    private Iterable<Integer> order;//顶点的拓扑顺序

    public Toplogical(Digraph g){
        DirectedCycle directedCycle = new DirectedCycle(g);
        if(!directedCycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order!=null;
    }
}
