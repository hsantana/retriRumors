package evenSmarterDownloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class SmarterDownloaderStart {
	
	public static void main(String[] args) throws IOException{
		
		/*NAME YOUR OUTPUT FILE AND PATH HERE*/
        String outputFileName="test3.txt";
        String filePath="/Users/Massimiliano/Documents/retriRumors/evenSmarterDownloader";
        
        File outputFile = new File(outputFileName);
        
        if(!outputFile.exists()){
            outputFile.createNewFile();
            System.out.println("Creating new file for output");
        }
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
        
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("klXnwR5Vzsa60A9wVWD4gH2ID")
	      .setOAuthConsumerSecret("GKAcLcVeMJttaaEfpyEcbGkgD0noslEdVlK7QphFJy49qlL7r0")
	      .setOAuthAccessToken("141980940-RsxY9qXeEGoMT2WOWfxAOmauqaKWgjj54J1oEhuB")
	      .setOAuthAccessTokenSecret("ZWkg1CP8tW2cCShroSO2GaolBZ0irFmvVdOP0CSnGeP56");
	    cb.setJSONStoreEnabled(true);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	    
	    try {
	        Query query = new Query("halloween ecstasy candy");
	        query.setCount(100);
	        QueryResult result;
	        result = twitter.search(query);
	        result.
	        List<Status> tweets = result.getTweets();
	        System.out.println("Size: " + tweets.size());
	        for (Status tweet : tweets) {
	        	String json = TwitterObjectFactory.getRawJSON(tweet);
	        	out.println(json);
	            System.out.println("@" + tweet.getUser().getScreenName() + " - " + 
	            					"created at: " + tweet.getCreatedAt() + " - " +
	            					tweet.getText());
	        }
	        out.close();
	        System.exit(0);
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
	}
    
}
