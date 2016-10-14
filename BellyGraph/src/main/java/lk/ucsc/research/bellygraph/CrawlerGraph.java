/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author agentmilindu
 */
class CrawlerGraph implements Graph{

    private TCMGraph tcmGraph;
    private SimpleGraph originalGraph;
    private Set<Node> nodes = new HashSet<Node>();


    public CrawlerGraph(int... size) {
        this.tcmGraph = new TCMGraph(size);
        this.originalGraph = new SimpleGraph(size[0]);

    }
    
    public CrawlerGraph(int size) {
        this(size, 2*size, size*size);
    }
    
    public void addNode(Node a) {
        //System.out.println("Adding node:" + a.toString());
        this.nodes.add(a);
        this.originalGraph.addNode(a);
    }

    public void addEdge(Node a, Node b, int w) {
        //System.out.println("Adding edge");
        
        this.tcmGraph.addEdge(a, b, w);
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
        this.tcmGraph.print();
    }

    void printDegreeDistributions() {
        Map<Integer,Integer> degrees = new TreeMap<>();
        
        //this.tcmGraph.print();
        
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node next = iterator.next();
            int degree = this.tcmGraph.getOutgoingEdgesCount(next);
            if (degrees.get(degree) == null) {
                degrees.put(degree, 1);
            }else{
                degrees.put(degree, degrees.get(degree)+1);
            }
        }
        System.out.println(degrees);
        
    }
    
    public Map<Integer, Integer> getDegreeDistributionsFromSketches() {
        Map<Integer,Integer> degrees = new TreeMap<>();
        
        //this.tcmGraph.print();
        
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node next = iterator.next();
            int degree = this.tcmGraph.getOutgoingEdgesCount(next);
            if (degrees.get(degree) == null) {
                degrees.put(degree, 1);
            }else{
                degrees.put(degree, degrees.get(degree)+1);
            }
        }
        return degrees;
        
    }
    
    public Map<Integer, Integer> getDegreeDistributionsFromGraph() {
        Map<Integer,Integer> degrees = new TreeMap<>();
        
        //this.tcmGraph.print();
        
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node next = iterator.next();
            int degree = this.originalGraph.getOutgoingEdgesCount(next);
            if (degrees.get(degree) == null) {
                degrees.put(degree, 1);
            }else{
                degrees.put(degree, degrees.get(degree)+1);
            }
        }
        return degrees;
        
    }
    
    

    private CrawlerGraph(int size, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
