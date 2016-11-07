
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo
 * This class contains the different queries we have build.
 */

public class ConnectorQueries {
    public TransportClient client;
    String indexName;
    
    public ConnectorQueries(TransportClient client, String indexName){
        this.client=client;
        this.indexName=indexName;
    }
    
    public void getAllIds(){
        //This method gets all Ids stored under the index movies on ES.
        MatchQueryBuilder query = QueryBuilders.matchQuery("_index", indexName);
        SearchResponse response1 = client.prepareSearch().setQuery(query).setSize(2000).execute().actionGet();
        
        System.out.println("Total Hits = " +response1.getHits().getTotalHits());
        for(int i = 0; i<response1.getHits().getTotalHits(); i++){
                Map<String, Object> fields = response1.getHits().getAt(i).getSource();
                System.out.println(fields.get("id"));
                System.out.println(fields.size());
        }
        
    }

    public void getAll(){
            SearchResponse response = client.prepareSearch().execute().actionGet();
            System.out.println("Complete Response:");
            System.out.println(response.toString());
    }
    
    public void getTweetTextByKeywords(String keywordsOriginal, String outputFileNameText) throws IOException{
        //This method gets all tweet text that contains one or more of the keywords.
       
        String keywords=keywordsOriginal.replace(",", "");
        
        //Creating outputHandler instance
        OutputHandler outputH= new OutputHandler(outputFileNameText, "Basic.txt", 0);
        
        //MatchQueryBuilder query = QueryBuilders.matchQuery("text", keywords);.must(termQuery("text", keywords))
        QueryBuilder query = boolQuery().must(matchQuery("_index", indexName)).must(matchQuery("text", keywords)); 
       
        
        SearchResponse response1 = client.prepareSearch().setQuery(query).setSize(10000).execute().actionGet();
        
        //System.out.println("Complete Response");
        //System.out.println(response1.toString());
        outputH.writeOutputFileText(keywordsOriginal);
        for (SearchHit hit : response1.getHits()){
                Map<String, Object> fields = hit.getSource();
                //System.out.println(fields.get("text"));
                //System.out.println(((String)fields.get("text")).trim());
                String ids = Long.toString((long) fields.get("id"));
                String text = replaceCommas((String) fields.get("text"));
                String finalText = replaceLineBreaks(text);
                String endLine=ids + ", " + finalText;
                outputH.writeOutputFileText(endLine);
                outputH.writeOutputFileText("-----------------------------------------------------------");
        }
        
        outputH.closeOutputHanlder();
        
        
    }
    
    public void getBasicInfoByKeywords(String keywordsOriginal, String outputFileNameBasic) throws IOException{
        //This method gets basic information and retrieves an output file (csv)
       
        String keywords=keywordsOriginal.replace(",", "");
        //Creating outputHandler instance
        OutputHandler outputH= new OutputHandler("Advanced.txt", outputFileNameBasic, 1);
        outputH.writeOutputFileBasic(keywordsOriginal);
        outputH.writeOutputFileBasic("id, verified, followers_count,"
                    + "default_profile_image, retweet_count, favorites_count,"
                    + "retweeted, followers_count_original, urls, hashtags");
        //MatchQueryBuilder query = QueryBuilders.matchQuery("text", keywords);.must(termQuery("text", keywords))
        QueryBuilder query = boolQuery().must(matchQuery("_index", indexName)).must(matchQuery("text", keywords)); 
       
        SearchResponse response1 = client.prepareSearch().setQuery(query).setSize(10000).execute().actionGet();
        
        for (SearchHit hit : response1.getHits()){
                String followers_count_original="null";
                Map<String, Object> fields = hit.getSource();
                Map<String, Object> user = (Map<String, Object>) fields.get("user");
                if(fields.containsKey("retweeted_status")){
                    Map<String, Object> retweeted_status = (Map<String, Object>) fields.get("retweeted_status");
                    Map<String, Object> userO = (Map<String, Object>) retweeted_status.get("user");
                    followers_count_original=""+ userO.get("followers_count");
                }
                
                
                Map<String, Object> entities = (Map<String, Object>) fields.get("entities");
                //Map≤String, Object> userFields=hit.getSource();
                ArrayList urls = (ArrayList) entities.get("urls");
                ArrayList hash = (ArrayList) entities.get("hashtags");
                String urlString = "";
                String hashString = "";
                if(urls!=null){
                    urlString = this.ArrayListToString(urls);
                }
                
                if(hash!=null){
                    hashString = this.ArrayListToString(hash);
                }
                                
                String urls_noCommas = replaceCommas(urlString);
                String hash_noCommas = replaceCommas(hashString);
                String line=fields.get("id") + "," +
                    user.get("verified") + "," +
                    user.get("followers_count") + "," +
                    user.get("default_profile_image") + "," +
                    fields.get("retweet_count") + "," +
                    fields.get("favorite_count") + "," +
                    fields.get("retweeted") + "," +
                    followers_count_original + "," +
                    urls_noCommas + "," +
                    hash_noCommas;
                outputH.writeOutputFileBasic(line);
        }
        
        outputH.closeOutputHanlder();
        
        
    }
    
    public String replaceCommas(String line){
        String newLine = line.replace(",", " ");
        return newLine;
    }
    
    public String ArrayListToString(ArrayList al){
        String sb = "";
        if(al==null){
            return "[]";
        }
        for(int i = 0; i<al.size(); i++){
                sb=sb + "["+al.get(i)+"]";
        }
        return sb;
    }
    
    public String replaceLineBreaks(String line){
        String newLine = line.replace("\n", " ");
        String finalLine = newLine.replace("\r", " ");
        return finalLine;
    }
}
