import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.scribe.builder.*; //Imports to handle oath.
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class SmartDownloader extends Thread {

    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";
    public String apiKey;
    public String apiSecret;
    public String tokenValue;
    public String tokenSecret;
    public String filePath;
    public String outputFileName;
    public String topics;
    public File outputFile;
    public PrintWriter out;
    
    public SmartDownloader(String outputFileName, String apiKey, String apiSecret, String tokenValue, String tokenSecret, String filePath, String topics) throws IOException{
        this.outputFileName=outputFileName;
        this.apiKey=apiKey;
        this.apiSecret=apiSecret;
        this.tokenValue=tokenValue;
        this.tokenSecret=tokenSecret;
        this.filePath=filePath;
        this.topics=topics;
        this.out = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileName, true)));
    }
    
    public void run(){
           
            int requestCount=0;
            int numberOfTweets=0;
            try {
                this.createOutputFile();
            } catch (IOException ex) {
                Logger.getLogger(SmartDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(requestCount<1){
                try{
                    // Enter your consumer key and secret below
                    OAuthService service = new ServiceBuilder()
                            .provider(TwitterApi.class)
                            .apiKey(this.apiKey)
                            .apiSecret(this.apiSecret)
                            .build();
                    
                    // Set your access token
                    Token accessToken = new Token(this.tokenValue,  this.tokenSecret);
                    
                    // Let's generate the request
                    System.out.println("Connecting to Twitter Public Stream");
                    OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
                    request.addHeader("version", "HTTP/1.1");
                    request.addHeader("host", "stream.twitter.com");
                    request.setConnectionKeepAlive(true);
                    request.addHeader("user-agent", "Twitter Stream Reader");
                    request.addBodyParameter("track", topics); //Using topics
                    service.signRequest(accessToken, request);
                    
                    Response response = request.send();
                    
                    // Create a reader to read Twitter's stream
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));
                    
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Printing received information:");
                        System.out.println(line);
                        if(isValidResponse(line)==false){
                            System.out.println("TWITTER TIMEOUT, SLEEPING FOR 33 minutes");
                            Thread.sleep(2000000);
                        }else{
                            writeOutputFile(line);
                            numberOfTweets++;
                        }
                        if(numberOfTweets>5000){//Max number of tweets reached, ending program/connection
                            System.out.println("DONE");
                            out.close(); //Closing writer
                            requestCount++;
                        }
                        
                    }
                    
                }
                catch (IOException ioe){//
                    ioe.printStackTrace();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SmartDownloader.class.getName()).log(Level.SEVERE, null, ex);  
                }
            }
            out.close();
    }
    
    public void writeOutputFile(String line){
        
        String id = getId(line);
        out.print("{\"index\":{\"_id\":"+id+"}}");
        out.println(line);
        System.out.println("Writing new tweet.");
        
    }
    
    public String getId(String line){
        String[] splittedLine= line.split(":");
        String[] splittedLine2 = splittedLine[4].split(",");
        return splittedLine2[0];
    }
    
    public boolean isValidResponse(String line){
        if(line.length()<200){
            return false;
        }
        
        return true;
    }
    public void createOutputFile() throws IOException{
        this.outputFile = new File(outputFileName);
        
        if(! this.outputFile.exists()){
            this.outputFile.createNewFile();
            System.out.println("Creating new file for output");
        }
    }
    
}