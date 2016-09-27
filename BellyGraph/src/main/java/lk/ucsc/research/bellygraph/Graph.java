/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.util.Arrays;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author agentmilindu
 */
class Graph {

    private int[][] sketch;
    int size;

    private static org.graphstream.graph.Graph graph1;
    private static org.graphstream.graph.Graph graph2;

    public Graph(int size) {
        this.size = size;
        this.sketch = new int[size][size];

        graph1 = new SingleGraph("WebGraph");
        graph1.setStrict(false);
        graph1.setAutoCreate(true);

        graph2 = new SingleGraph("WebGraphSketch");
        graph2.setStrict(false);
        graph2.setAutoCreate(true);

        String styles = "node { fill-color: red; }";
        graph1.addAttribute("ui.stylesheet", styles);
        graph2.addAttribute("ui.stylesheet", styles);

        graph1.display();
        graph2.display();

    }

    public void addEdge(Node a, Node b, int w) {
        System.out.println("Adding edge");
        int aHash = a.hashCode() % this.size;
        int bHash = b.hashCode() % this.size;
        sketch[aHash][bHash] = sketch[aHash][bHash] + w;

        org.graphstream.graph.Node x = graph1.addNode(a.toString());
        org.graphstream.graph.Node y = graph1.addNode(b.toString());
        graph1.addEdge(x.getId() + y.getId(), x, y);

        org.graphstream.graph.Node x2 = graph2.addNode(aHash + "");
        org.graphstream.graph.Node y2 = graph2.addNode(bHash + "");
        graph2.addEdge(aHash + "" + bHash, x2, y2).setAttribute("ui.label", sketch[aHash][bHash]);

    }

    public void addEdge(Node a, Node b) {
        addEdge(a, b, 1);
    }
    


    void print() {
        for (int[] row : this.sketch) {
            System.out.println(Arrays.toString(row));
        }
    }

}
