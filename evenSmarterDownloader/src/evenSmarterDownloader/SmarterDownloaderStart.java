package evenSmarterDownloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class SmarterDownloaderStart {
	
	private final static int MAX_RESULT_SIZE = 100;
	private final static int MAX_QUERIES = 180;
	private final static long FIVE_MINS_IN_MILLIS = 300000;
	private final static long FIFTEEN_MINS_IN_MILLIS = 900000;
	
	/**
	 * @author Massimiliano
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
        
		// twitter api authentication
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(args[0])
				.setOAuthConsumerSecret(args[1])
				.setOAuthAccessToken(args[2])
				.setOAuthAccessTokenSecret(args[3]);
	    cb.setJSONStoreEnabled(true);
	    TwitterFactory tf = new TwitterFactory(cb.build());
<<<<<<< HEAD
	    Twitter twitter = tf.getInstance();	
        
        long startTimeInMillis = Calendar.getInstance().getTimeInMillis();
        int totalQueries = 0;
        
        // split keywords related to different rumors
        String[] rumor = args[4].split(",");
        
        // for loop going through rumors
        for(int i=0; i<rumor.length; i++){
        	
        	long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
            long runningTime = (currentTimeInMillis - startTimeInMillis) % 1000;
        	System.out.println("----------------------------------------------------------------");
        	System.out.println("--- So far " + totalQueries + " queries ---");
        	System.out.println("----------------------------------------------------------------");
        	
        	String outputFileName = "";
        	
        	String[] rumorKeyword = rumor[i].split(" ");	// split keywords related to the same rumor
        	System.out.println("--------- " + (i+1) + "^ rumor --------- ");
        	System.out.println("---" + rumorKeyword.length + " Keyword(s): ");
        	
        	// per each rumor create appropriate file name
        	// name the file as keyword1_keword2_..._keywordN.txt
        	for(int j=0; j<rumorKeyword.length; j++){
        		if(j==rumorKeyword.length-1){
        			System.out.println(rumorKeyword[j]);
            		outputFileName += rumorKeyword[j] + ".txt";	
            	} else if (!rumorKeyword.equals("") && !rumorKeyword.equals(" ")){
            		System.out.print(rumorKeyword[j] + ", ");
            		outputFileName += rumorKeyword[j] + "_";
            	}
        	}
        	outputFileName.replace("\"", "");
        	outputFileName.replace("\'", "");
        	
        	// per each rumor create corresponding file to write the output
        	File outputFile = new File(outputFileName);
			PrintWriter out = null;
            if(!outputFile.exists()){
                outputFile.createNewFile();					// create the file
                System.out.println("--- Creating new file for output: " + outputFileName);
            }
            out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
            
            /* at this point we have a file per rumor, now we have to download the corresponding tweets */
            
            // per each rumor download the tweets that match the keywords
            String id = null;
    		int overall_size = 0;
    		int result_size = MAX_RESULT_SIZE;
    		System.out.print("--- Don't panic! I'm searching...");
    		
    		for(int k=1; k <= Integer.parseInt(args[5]) && result_size != 0; k++) {
    			
    			currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
    			
    			// if you're inside the 15mins window and you already made 180 queries 
    			// then downloader should sleep for some time
    			if((currentTimeInMillis - startTimeInMillis) <= FIFTEEN_MINS_IN_MILLIS		 
    					&& totalQueries+1 >= MAX_QUERIES ){
    				try {
    					long sleepTime = FIFTEEN_MINS_IN_MILLIS;
						System.out.println("!!! Hey boy, calm down! We don't want Twitter to take you down !!!");
						System.out.println("!!! Downloader will sleep for 15mins");
						Thread.sleep(sleepTime);
						System.out.println("!!! Ok man, go ahead and query safe !!!");
						
						// after sleep all defaults values should be restored
						startTimeInMillis = Calendar.getInstance().getTimeInMillis();
						totalQueries = 0;
						k--;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			
    			// else it should continue downloading
    			} else {
    				
    				totalQueries++;
    				
    				try {
        				Query query = new Query(rumor[i]);
        				query.setCount(MAX_RESULT_SIZE);
        				if(id!=null)
        					query.setMaxId(Long.parseLong(id)-1);
        				
        				QueryResult result;
        				result = twitter.search(query);
        				List<Status> tweets = result.getTweets();
        				result_size = tweets.size();
        				if(tweets.size() == 0){
        					System.out.println();
        					System.out.println("--- End reached: no more relevant results --- Total tweets: " + overall_size + " ---");
        					break;
        				} else {
        					overall_size += result_size;
        					if (k==Integer.parseInt(args[5])){
        						System.out.println();
        						System.out.println("--- End reached: maximum number of queries reached --- Total tweets: " + overall_size + " ---");
            				}
        					//System.out.println((k) + "^) " + result_size + " tweets / Subtotal: " + overall_size);
        				}
        				
        				for (Status tweet : tweets) {
        					String jsonRaw = TwitterObjectFactory.getRawJSON(tweet);
        					JSONObject json = new JSONObject(jsonRaw);
        					id = json.getString("id_str");
        					out.print("{\"index\":{\"_id\":" + id + "}}");
        					out.println(jsonRaw);
        				}
        				
        				
        					
        			} catch (TwitterException te) {
        				te.printStackTrace();
        				System.out.println("!!! Failed to search tweets: " + te.getMessage());
        				System.exit(-1);
        			} catch (JSONException e) {
        				e.printStackTrace();
        			}
        		}
    			
=======
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
					out.print("{\"index\":{\"_id\":" + id + "}}");
					out.println(jsonRaw);
				}
				
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			} catch (JSONException e) {
				e.printStackTrace();
>>>>>>> 29751e9042ded7dabd111dc818c89e301eff5146
			}
    		
    		out.close();
    			
        }
		
	}

}
