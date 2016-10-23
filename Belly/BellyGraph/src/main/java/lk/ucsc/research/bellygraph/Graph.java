package lk.ucsc.research.bellygraph;

/**
 *
 * @author agentmilindu
 */
public interface Graph {

    public void addNode(Node a);

    public void addEdge(Node a, Node b, int w);

    public void addEdge(Node a, Node b);
    
    public int getIncommingEdgesCount(Node a);

    void print();

}
