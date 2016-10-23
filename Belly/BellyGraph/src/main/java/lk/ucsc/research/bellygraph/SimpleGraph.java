package lk.ucsc.research.bellygraph;

import java.util.Collection;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author agentmilindu
 */
public class SimpleGraph implements Graph{
    
    int size;

    private static org.graphstream.graph.Graph graph1;

    public SimpleGraph(int size) {
        this.size = size;

        graph1 = new SingleGraph("WebGraph");
        graph1.setAttribute("ui.title", "Graph");
        graph1.setStrict(false);
        graph1.setAutoCreate(true);

        String styles = "node { fill-color: red; }";
        graph1.addAttribute("ui.stylesheet", styles);

        graph1.display();

    }

    public void addEdge(Node a, Node b, int w) {

        org.graphstream.graph.Node x = graph1.addNode(a.toString());
        org.graphstream.graph.Node y = graph1.addNode(b.toString());
        graph1.addEdge(x.getId() + y.getId(), x, y);

    }

    public void addEdge(Node a, Node b) {
        addEdge(a, b, 1);
    }
    
    public int getIncommingEdgesCount(Node a) {
        return 1;
    }
    
    public int getOutgoingEdgesCount(Node a) {
        return graph1.getNode(a.toString()).getInDegree();
    }

    public void print() {
        Collection<org.graphstream.graph.Node> nodeSet = graph1.getNodeSet();
        System.out.println(nodeSet);
    }

    @Override
    public void addNode(Node a) {
        graph1.addNode(a.toString());
    }

}
