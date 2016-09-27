/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agentmilindu
 */
class Analyser extends Thread{
    
    CrawlerGraph graph;

    public Analyser(CrawlerGraph graph) {
        this.graph = graph;
    }

    @Override
    public void run() {
        while (true) {            
            try {
                System.out.println("-----------------distributions--------------------");
                graph.printDegreeDistributions();
                System.out.println("-----------------/distributions--------------------");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Analyser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}
