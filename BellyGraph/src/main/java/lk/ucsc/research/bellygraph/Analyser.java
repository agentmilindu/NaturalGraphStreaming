/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.bellygraph;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author agentmilindu
 */
class Analyser extends Thread {

    CrawlerGraph graph;

    public Analyser(CrawlerGraph graph) {
        this.graph = graph;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("-----------------distributions--------------------");

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                Map<Integer, Integer> degreeDistributionsSketch = graph.getDegreeDistributionsFromSketches();
                System.out.println(degreeDistributionsSketch);
                for (Map.Entry<Integer, Integer> entrySetS : degreeDistributionsSketch.entrySet()) {
                    Integer keyS = entrySetS.getKey();
                    Integer valueS = entrySetS.getValue();
                    dataset.setValue(valueS, "sketch", keyS);

                    Map<Integer, Integer> degreeDistributionsGraph = graph.getDegreeDistributionsFromGraph();
                    System.out.println(degreeDistributionsGraph);
                    for (Map.Entry<Integer, Integer> entrySetG : degreeDistributionsGraph.entrySet()) {
                        Integer keyG = entrySetG.getKey();
                        Integer valueG = entrySetG.getValue();
                        
                        
                        if (keyS == keyG) {
                            dataset.setValue(valueG, "actual", keyG);
                            dataset.setValue(valueS, "sketch", keyS);
                        }
                        
                        else if (keyS > keyG) {
                            dataset.setValue(valueG, "actual", keyG);
                            dataset.setValue(0, "sketch", keyS);
                        }
                        else{
                            break;
                        }
                    }
                    
                    dataset.setValue(valueS, "sketch", keyS);
                    dataset.setValue(0, "actual", keyS);

                }

                //dataset.
                JFreeChart lineChart = ChartFactory.createLineChart(
                        "Degree destributions",
                        "Degree", "Number of nodes",
                        dataset, PlotOrientation.VERTICAL, true, true, false);
                BufferedImage createBufferedImage = lineChart.createBufferedImage(1200, 300);
                File outputfile = new File("plots/plot-" + new Timestamp(new java.util.Date().getTime()) + ".png");
                ImageIO.write(createBufferedImage, "png", outputfile);

                System.out.println("-----------------/distributions--------------------");
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Analyser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Analyser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
