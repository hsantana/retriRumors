package evenSmarterDownloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class SmarterDownloaderStart {
	
	private final static int MAX_SIZE = 100;
	
	public static void main(String[] args) throws IOException{
		
		/*NAME YOUR OUTPUT FILE AND PATH HERE*/
        String outputFileName=args[4];
        File outputFile = new File(outputFileName);
        
        if(!outputFile.exists()){
            outputFile.createNewFile();
            System.out.println("--- Creating new file for output ---");
        }
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
        
		ConfigurationBuilder cb = new ConfigurationBuilder();
		/*
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("klXnwR5Vzsa60A9wVWD4gH2ID")
	      .setOAuthConsumerSecret("GKAcLcVeMJttaaEfpyEcbGkgD0noslEdVlK7QphFJy49qlL7r0")
	      .setOAuthAccessToken("141980940-RsxY9qXeEGoMT2WOWfxAOmauqaKWgjj54J1oEhuB")
	      .setOAuthAccessTokenSecret("ZWkg1CP8tW2cCShroSO2GaolBZ0irFmvVdOP0CSnGeP56");
	      */

		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(args[0])
				.setOAuthConsumerSecret(args[1])
				.setOAuthAccessToken(args[2])
				.setOAuthAccessTokenSecret(args[3]);
	    cb.setJSONStoreEnabled(true);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();

		String id = null;

		int overall_size = 0;
		int result_size = MAX_SIZE;
		System.out.println("--- Searching... ---");
		for(int i=0; i<Integer.parseInt(args[6]) && result_size != 0; i++) {
			try {
				Query query = new Query(args[5]);
				query.setCount(MAX_SIZE);
				if(id!=null)
					query.setMaxId(Long.parseLong(id)-1);
				
				QueryResult result;
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				result_size = tweets.size();
				if(tweets.size() == 0){
					System.out.println("--- End reached --- Total tweets: " + overall_size + " ---");
					break;
				} else {
					overall_size += result_size;
					System.out.println((i+1) + "^) " + result_size + " tweets / Subtotal: " + overall_size);
				}
				
				for (Status tweet : tweets) {
					String jsonRaw = TwitterObjectFactory.getRawJSON(tweet);
					JSONObject json = new JSONObject(jsonRaw);
					id = json.getString("id_str");
					out.print("{\"index\":{\"_id\":" + id + "}}\n");
					out.println(jsonRaw);
				}
				
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		out.close();
	}

}
