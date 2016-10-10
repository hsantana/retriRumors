
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
    
    public ConnectorQueries(TransportClient client){
        this.client=client;
    }
    
    public void getAllIds(){
        //This method gets all Ids stored under the index movies on ES.
        MatchQueryBuilder query = QueryBuilders.matchQuery("_index", "movies");
        SearchResponse response1 = client.prepareSearch().setQuery(query).execute().actionGet();
        
        System.out.println("Complete Response");
        System.out.println(response1.toString());
        
        for (SearchHit hit : response1.getHits()){
                Map<String, Object> fields = hit.getSource();
                System.out.println(fields.get("id"));
                System.out.println(fields.size());
        }
    }
    public void getAll(){
            SearchResponse response = client.prepareSearch().execute().actionGet();
            System.out.println("Complete Response:");
            System.out.println(response.toString());
    }
    
    public void getTweetTextByKeywords(String keywords){
        //This method gets all tweet text that contains one or more of the keywords.
        MatchQueryBuilder query = QueryBuilders.matchQuery("text", keywords);
        
        SearchResponse response1 = client.prepareSearch().setQuery(query).execute().actionGet();
        
        System.out.println("Complete Response");
        System.out.println(response1.toString());
        
        for (SearchHit hit : response1.getHits()){
                Map<String, Object> fields = hit.getSource();
                System.out.println(fields.get("text"));
        }
    }
}
