package lk.ucsc.research.belly.adaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lk.ucsc.research.bellygraph.Graph;
import lk.ucsc.research.bellygraph.Node;
import lk.ucsc.research.bellygraph.runners.osgi.GraphService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Belly Adaptor");
        // Query for all service references matching any language.
        ServiceReference[] refs = context.getServiceReferences(
                GraphService.class.getName(), "");

        if (refs != null) {
            try {
                System.out.println("Enter a blank line to exit.");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String line = "";

                // if the word is correct.
                GraphService graph = (GraphService) context.getService(refs[0]);
                Graph g = graph.getGraph();
                
                // Loop endlessly.
                while (true) {
                    // Ask the user to enter a word.
                    System.out.print("Enter word: ");
                    line = in.readLine();

                    // If the user entered a blank line, then
                    // exit the loop.
                    if (line.length() == 0) {
                        break;
                    }

                    String[] words = line.split(" ");
                    Node n1 = new Node(words[0]);
                    Node n2 = new Node(words[1]);
                    g.addEdge(n1, n2);
                    System.out.println(g.getIncommingEdgesCount(n1));
                    
                }
            } catch (IOException ex) {
            }
            finally{
                context.ungetService(refs[0]);
            }
        } else {
            System.out.println("Couldn't find any dictionary service...");
        }
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
