package lk.ucsc.research.belly.runner;

import java.util.Hashtable;
import lk.ucsc.research.bellygraph.Graph;
import lk.ucsc.research.bellygraph.runners.osgi.GraphService;
import lk.ucsc.research.bellygraph.tcm.TCMGraph;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

public class Activator implements BundleActivator, ServiceListener {

    public void start(BundleContext context) throws Exception {
        Graph g = new TCMGraph(30, 40);
        Hashtable<String, String> props = new Hashtable<String, String>();
        context.registerService(
            GraphService.class.getName(), new GraphImpl(), props);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

    public void serviceChanged(ServiceEvent se) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
