package lk.ucsc.research.bellygraph.example.webcrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main{

    public static void main(String args[]) throws Exception {
        
        
        CrawlerGraph graph = new CrawlerGraph(11,19, 71);

        Crawler.setGraph(graph);
        new Analyser(graph).start();
        
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
