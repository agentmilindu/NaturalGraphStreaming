/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.belly.runner;

import lk.ucsc.research.bellygraph.Graph;
import lk.ucsc.research.bellygraph.runners.osgi.GraphService;
import lk.ucsc.research.bellygraph.tcm.TCMGraph;

/**
 *
 * @author agentmilindu
 */
class GraphImpl implements GraphService{
    
    Graph g = new TCMGraph(7, 23);

    public GraphImpl() {
        
    }
    
    public Graph getGraph(){
        return g;
    }
    
}
