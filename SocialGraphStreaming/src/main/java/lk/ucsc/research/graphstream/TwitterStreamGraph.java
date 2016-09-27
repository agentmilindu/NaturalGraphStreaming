package lk.ucsc.research.graphstream;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Iterator;
import java.util.Properties;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.graph.Edge;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import org.apache.flink.util.Collector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.flink.graph.Vertex;
import org.apache.flink.graph.streaming.GraphStream;
import org.apache.flink.graph.streaming.SimpleEdgeStream;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;

public class TwitterStreamGraph {

    //
    //	Program
    //
    public static void main(String[] args) throws Exception {
        
        ParameterTool parameter = ParameterTool.fromArgs(args);

        System.out.println("TwitterGraphStream");

        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment();

        //Twitter API Keys
        Properties p = new Properties();
        p.setProperty(TwitterSource.CONSUMER_KEY, "TZRQy9tABV1ncbgqrrIu9IikS");
        p.setProperty(TwitterSource.CONSUMER_SECRET, "IRTk0iNVY5Mi0krhX6q07XPo6kDPscQfXEScnc7WrkAGViFTiG");
        p.setProperty(TwitterSource.TOKEN, "373849310-V0N30kn3CyYMjREb5ybeV4nHRBwjEcg3iwFa07fc");
        p.setProperty(TwitterSource.TOKEN_SECRET, "l1aHSdZIv53RFBBCSnHiEigtJjuV5N9mwnkg0b7HoFTG2");
        DataStream<String> tweetStream = env.addSource(new TwitterSource(p));
        
        
        //Extract the stock symbols
        DataStream<Edge<Vertex<Long, String>, String>> tweetGraphStream = tweetStream.flatMap(
                new FlatMapFunction<String, Edge<Vertex<Long, String>, String>>() {
                    @Override
                    public void flatMap(String value, Collector<Edge<Vertex<Long, String>, String>> out) throws Exception {
                        //Tweet tweet = new Gson().fromJson(value, Tweet.class);
                        //System.out.println(tweet.text);

                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(value);

                        Long tweetId = (Long) jsonObject.get("id");
                        String text = (String) jsonObject.get("text");

                        JSONObject user = (JSONObject) jsonObject.get("user");

                        if (user != null) {

                            Long userId = (Long) user.get("id");
                            String userScreenName = (String) user.get("screen_name");
                            
                            out.collect(new Edge<Vertex<Long, String>, String>(new Vertex<Long, String>(userId, userScreenName), new Vertex<Long, String>(tweetId, text), "tweeted")); 
                            
                        } else {
                            //System.out.println(value);
                        }

                    }
                }); // execute program
        
        
       GraphStream g = new SimpleEdgeStream<Vertex<Long, String>, String>(tweetGraphStream
					.map(new MapFunction<Edge<Vertex<Long, String>, String>, Edge<Vertex<Long, String>, String>>() {
						@Override
						public Edge map(Edge<Vertex<Long, String>, String> e) {
							//System.out.println(e);
							return e;
						}
			}), env);
       
        
        env.execute("WordCount from SocketTextStream Example");
    }

    //
    // 	User Functions
    //
    /**
     * Implements the string tokenizer that splits sentences into words as a
     * user-defined FlatMapFunction. The function takes a line (String) and
     * splits it into multiple pairs in the form of "(word,1)"
     * (Tuple2<String, Integer>).
     */
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // normalize and split the line
            String[] tokens = value.toLowerCase().split("\\W+");

            // emit the pairs
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}
