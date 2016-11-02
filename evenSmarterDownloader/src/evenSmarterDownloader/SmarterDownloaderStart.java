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
	
	public static void main(String[] args) throws IOException{
		
		/*NAME YOUR OUTPUT FILE AND PATH HERE*/
        String outputFileName=args[4];
        String filePath="/Users/Massimiliano/Documents/retriRumors/evenSmarterDownloader";
        
        File outputFile = new File(outputFileName);
        
        if(!outputFile.exists()){
            outputFile.createNewFile();
            System.out.println("Creating new file for output");
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
		int size = 100;
		for(int i=0;i<10 && size != 0;i++) {
			try {
				Query query = new Query(args[5]);
				query.setCount(100);
				if(id!=null)
					query.setMaxId(Long.parseLong(id)-1);
				QueryResult result;
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				size = tweets.size();
				System.out.println("Size: " + size);
				if(tweets.size() == 0)
					break;
				for (Status tweet : tweets) {
					String jsonRaw = TwitterObjectFactory.getRawJSON(tweet);
					JSONObject json = new JSONObject(jsonRaw);
					id = json.getString("id_str");
					out.print("{\"index\":{\"_id\":" + id + "}}");
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
