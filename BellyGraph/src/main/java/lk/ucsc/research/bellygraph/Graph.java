/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

/**
 *
 * @author agentmilindu
 */
interface Graph {

    public void addNode(Node a);

    public void addEdge(Node a, Node b, int w);

    public void addEdge(Node a, Node b);
    
    public int getIncommingEdgesCount(Node a);

    void print();

}
