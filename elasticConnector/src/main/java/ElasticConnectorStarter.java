/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo
 * This class establishes a connection with elasticSearch and executes the desired query
 */
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

public class ElasticConnectorStarter {
    
    
    public static void main(String[] args) throws UnknownHostException {
        
        String keywords = "bombs";
 
        TransportClient client = TransportClient.builder().build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        ConnectorQueries connectorQ= new ConnectorQueries(client);
        
        /******Executing ConnectorQueries Methods:*****/
        connectorQ.getTweetTextByKeywords(keywords);
        //connectorQ.getAllIds();
        //connectorQ.getAll();
        
        
        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
        System.out.println("Closing Connection with Elastic Search");
        client.close();
    }
}
