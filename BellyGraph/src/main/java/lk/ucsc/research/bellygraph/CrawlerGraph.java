/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author agentmilindu
 */
class CrawlerGraph implements Graph{

    private TCMGraph tcmSketch;
    private Graph originalGraph;
    private Set<Node> nodes = new HashSet<Node>();


    public CrawlerGraph(int size) {
        this.tcmSketch = new TCMGraph(size);
        this.originalGraph = new SimpleGraph(size);

    }
    
    public void addNode(Node a) {
        System.out.println("Adding node:" + a.toString());
        this.nodes.add(a);
        this.originalGraph.addNode(a);
    }

    public void addEdge(Node a, Node b, int w) {
        System.out.println("Adding edge");
        
        this.tcmSketch.addEdge(a, b, w);
        this.originalGraph.addEdge(a, b, w);

    }

    public void addEdge(Node a, Node b) {
        addEdge(a, b, 1);
    }
    
    public int getIncommingEdgesCount(Node a) {
        return this.originalGraph.getIncommingEdgesCount(a);
    }

    public void print() {
        this.originalGraph.print();
        this.tcmSketch.print();
    }

    void printDegreeDistributions() {
        Map<Integer,Integer> degrees = new TreeMap<>();
        
        this.tcmSketch.print();
        
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node next = iterator.next();
            int degree = this.tcmSketch.getOutgoingEdgesCount(next);
            if (degrees.get(degree) == null) {
                degrees.put(degree, 1);
            }else{
                degrees.put(degree, degrees.get(degree)+1);
            }
            System.out.println(degree+""+degrees.get(degree));
        }
        System.out.println(degrees);
        for (int i = 0; i < degrees.size(); i++) {
            if (degrees.get(i) == null) {
                System.out.println(i+" : 0");
            }else{
                System.out.println(i+" : "+degrees.get(i));
            }
            
        }
        System.out.println("degrees size: "+degrees.size());
        
    }

}
