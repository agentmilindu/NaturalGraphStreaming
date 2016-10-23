package lk.ucsc.research.bellygraph.tcm;

import java.util.Arrays;
import lk.ucsc.research.bellygraph.Graph;
import lk.ucsc.research.bellygraph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author agentmilindu
 */
class TCMSketch implements Graph{

    private int[][] sketch;
    int size;
    
    private org.graphstream.graph.Graph graph2;

    public TCMSketch(int size) {
        this.size = size;
        this.sketch = new int[size][size];

        graph2 = new SingleGraph("WebGraphSketch");
        graph2.setAttribute("ui.title", "GraphSketch["+size+"]");
        graph2.setStrict(false);
        graph2.setAutoCreate(true);

        String styles = "node { fill-color: red; }";
        graph2.addAttribute("ui.stylesheet", styles);

        graph2.display();

    }

    public void addEdge(Node a, Node b, int w) {
        int aHash = a.hashCode() % this.size;
        int bHash = b.hashCode() % this.size;
        sketch[aHash][bHash] = sketch[aHash][bHash] + w;

        org.graphstream.graph.Node x2 = graph2.addNode(aHash + "");
        org.graphstream.graph.Node y2 = graph2.addNode(bHash + "");
        graph2.addEdge(aHash + "" + bHash, x2, y2).setAttribute("ui.label", sketch[aHash][bHash]);

    }

    public void addEdge(Node a, Node b) {
        addEdge(a, b, 1);
    }
    
    public void addUndirectedEdge(Node a, Node b, int w) {
        int aHash = a.hashCode() % this.size;
        int bHash = b.hashCode() % this.size;
        sketch[aHash][bHash] = sketch[aHash][bHash] + w;
        sketch[bHash][aHash] = sketch[bHash][aHash] + w;

        org.graphstream.graph.Node x2 = graph2.addNode(aHash + "");
        org.graphstream.graph.Node y2 = graph2.addNode(bHash + "");
        graph2.addEdge(aHash + "" + bHash, x2, y2).setAttribute("ui.label", sketch[aHash][bHash]);
    }
    
    public void addUndirectedEdge(Node a, Node b) {
        addUndirectedEdge(a, b, 1);
    }
    
    public int getIncommingEdgesCount(Node a) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            count += sketch[i][a.hashCode()%this.size];
            
        }
        return count;
    }
    
    public int getOutgoingEdgesCount(Node a) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            count += sketch[a.hashCode()%this.size][i];
            
        }
        return count;
    }

    public void print() {
        if(this.size>30) return;
        for (int[] row : this.sketch) {
            System.out.println(Arrays.toString(row));
        }
    }

    @Override
    public void addNode(Node a) {
        
    }

}
