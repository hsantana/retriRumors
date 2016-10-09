
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;

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
    
    public void getAll(){
        
        SearchResponse response = client.prepareSearch().execute().actionGet();
        // on shutdown
        System.out.println("Complete Response");
        System.out.println(response.toString());
        
        /*
        for(int i = 0; i<response.getHits().hits().length; i++){
            
            System.out.println("Result # " + i);
            System.out.println("ID: "+response.getHits().getAt(i).getId());
            System.out.println("Index: "+response.getHits().getAt(i).getIndex());
            System.out.println("Fields: "+response.getHits().getAt(i).getFields().toString());
        }*/
    }
    
    public void getTweetTextByKeywords(String keywords){
        
    }
}
