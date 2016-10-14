/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author agentmilindu
 */
class TCMGraph implements Graph{

    private TCMSketch[] sketches;
    int[] sizes;
    
    private static org.graphstream.graph.Graph graph2;

    public TCMGraph(int... sizes) {
        this.sizes = sizes;
        this.sketches = new TCMSketch[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            this.sketches[i] = new TCMSketch(size);
        }
    }

    public void addEdge(Node a, Node b, int w) {
        for (TCMSketch sketch : sketches) {
            sketch.addEdge(a, b, w);
        }
    }

    public void addEdge(Node a, Node b) {
        addEdge(a, b, 1);
    }
    
    public int getIncommingEdgesCount(Node a) {
        int count = Integer.MAX_VALUE;
        for (TCMSketch sketch : sketches) {
            int c = sketch.getIncommingEdgesCount(a);
            if(c < count){
                count = c;
            }
        }
        return count;
    }
    
    public int getOutgoingEdgesCount(Node a) {
        int count = Integer.MAX_VALUE;
        List counts = new LinkedList();
        for (TCMSketch sketch : sketches) {
            int c = sketch.getOutgoingEdgesCount(a);
            counts.add(c);
            if(c < count){
                count = c;
            }
        }
        return count;
    }

    public void print() {
        for (TCMSketch sketch : sketches) {
            sketch.print();
        }
    }

    @Override
    public void addNode(Node a) {
        
    }

}
