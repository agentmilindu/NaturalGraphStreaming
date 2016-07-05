package lk.ucsc.research.graphstream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agentmilindu
 */
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Main{

    public static void main(String args[]) throws Exception {
        
        
        Graph graph = new SingleGraph("SocialGraph");
        graph.setStrict(false);
        graph.setAutoCreate( true );
        
        try {
            String styles = new String(Files.readAllBytes(Paths.get(Main.class.getClassLoader().getResource("stylesheet.css").getPath())), StandardCharsets.UTF_8);
            graph.addAttribute("ui.stylesheet", styles);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Crawler.setGraph(graph);
        
        graph.display();        
        
        
        String crawlStorageFolder = "/data/crawl";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        
        //controller.addSeed("http://www.ics.uci.edu/~lopes/");
        //controller.addSeed("http://www.ics.uci.edu/~welling/");
        controller.addSeed("http://www.ics.uci.edu");

        System.out.println("Starting");
        controller.start(Crawler.class, numberOfCrawlers);
        System.out.println("Done");
        
       
    }
}
