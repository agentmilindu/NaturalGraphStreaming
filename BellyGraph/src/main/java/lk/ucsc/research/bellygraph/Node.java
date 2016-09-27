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
class Node {
    private String url;

    public Node(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
    
    
}
