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
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

public class ElasticConnectorStarter {
    
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        
        ArrayList<String> allKeywords = new ArrayList<String>();
        allKeywords.add("ecstasy halloween candy");
        allKeywords.add("facebook check in standing rock");
        allKeywords.add("british rule united states");
        allKeywords.add("obama cancel hillary");
        allKeywords.add("amish autism");
        allKeywords.add("standing rock buffalo");
        allKeywords.add("fbi reopen hillary");
        allKeywords.add("I would like to see people hillary");
        allKeywords.add("obamacare cost");
        allKeywords.add("sweden christmas lights");
        allKeywords.add("amish trump");
        allKeywords.add("lazy American women ");
        allKeywords.add("baby wipes warning");
        allKeywords.add("chelsea clinton soros");
        allKeywords.add("trump walk of fame damage");
        allKeywords.add("white sharks mississippi");
        allKeywords.add("clinton halfway house");
        allKeywords.add("patiant fart japan");
        allKeywords.add("broomstick one");
        allKeywords.add("abedin life insurance");
        allKeywords.add("blm millbury");
        allKeywords.add("Fifth Amendment lynch");
        allKeywords.add("clinton illegal arms guns");
        allKeywords.add("carolina hillary multiple");
        allKeywords.add("comey super pac");
        allKeywords.add("clinton topless");
        allKeywords.add("obama golf woods");
        allKeywords.add("pence gay conversion");
        allKeywords.add("trump lawsuit rape");
        allKeywords.add("donna brazile fired clinton");
        allKeywords.add("hillary spirit cooking");
        allKeywords.add("hillary treason");
        allKeywords.add("dunkin dawkins");
        allKeywords.add("trump deport miranda");
        allKeywords.add("facebook electoral map");
        allKeywords.add("dnc lawsuit dies OR death");
        allKeywords.add("zervos paid");
        allKeywords.add("life expectancy obamacare");
        allKeywords.add("vote trump missisippi church");
        allKeywords.add("abedin terrorists");
        allKeywords.add("McConaughey trump");
        allKeywords.add("starbucks satanism");
        allKeywords.add("hillary paid isis");
        allKeywords.add("cubs 108 stitches ");
        allKeywords.add("cubs extra innings 2014");
        allKeywords.add("jane doe press conference");
        allKeywords.add("Marijuana legalization increase crime");
        allKeywords.add("people absorb energy from others");
        allKeywords.add("hillary cancel lock her up");
        allKeywords.add("hillary property nypd");
        allKeywords.add("bill clinton underage sex");
        allKeywords.add("obama warren unfollow hillary");
        allKeywords.add("trump kkk newspaper");
        allKeywords.add("tequila health healthy");
        allKeywords.add("social media Psychiatry");
        allKeywords.add("project veritas fundraising");
        allKeywords.add("starbucks unity");
        allKeywords.add("dinosaur brain ");
        allKeywords.add("michelle obama deleted mentions");
        allKeywords.add("standing rock bail out");
        allKeywords.add("fbi manafort russia OR russian");
        TransportClient client = TransportClient.builder().build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        
        for(int i = 0; i<allKeywords.size(); i++){
            String keywordsOriginal = allKeywords.get(i);
            String indexName="retrirumorstest";
            //Creating instance of Helper Class with queries.
            ConnectorQueries connectorQ= new ConnectorQueries(client, indexName);
            OutputHandler out=new OutputHandler();
            String outputFileNameBasic=out.getFileNameFromKeywordsBasic(keywordsOriginal);
            String outputFileNameText=out.getFileNameFromKeywordsText(keywordsOriginal);
        
        
            /******Executing ConnectorQueries Methods:*****/
            connectorQ.getBasicInfoByKeywords(keywordsOriginal,outputFileNameBasic);
            connectorQ.getTweetTextByKeywords(keywordsOriginal,outputFileNameText);
            //connectorQ.getAllIds();
            //connectorQ.getAll();
        }

        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
        System.out.println("Closing Connection with Elastic Search");
        client.close();
        System.exit(0);
    }
}
