
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo
 */
public class SmartDownloaderStarter {
    public static void main(String[] args) throws IOException{
        /*NAME YOUR OUTPUT FILE AND PATH HERE*/
        String outputFileName="tweetsV2-Hugo.txt";
        String filePath="/Users/Hugo/NetBeansProjects/smartDownloader/TwitterData";
        
        /*CHANGE YOUR CREDENTIALS HERE*/
        String apiKey="X5QaYPl89YnwR9qjB2ro5mN4H";//"AHLzvp7J0ONr7wXsVY3Xw"; //
        String apiSecret="xy0zCGHjhzyG9Tl96lD9CT1GtZuhKi9RSItIOZp8420KkDusZ7"; //"I3N4lCKEbsI0AAVJSpS1dUOiRwMbr07ZYGBlEA0I"; //
        String tokenValue="34494124-wZpEvlhYgFrISqCvq5ZiX8VLbPAQk386fX88uT2Ln"; //"41588502-rl79tU9e8UWTCd4G1d6CZjj8dzEZn7FB6Hecp9H3B";//"34494124-wZpEvlhYgFrISqCvq5ZiX8VLbPAQk386fX88uT2Ln";
        String tokenSecret="UUeshZc0CRsr0jDD1OSFqDJ2piVaiREaiQkueDJR7lIvQ"; //"mxZIf9CsbVctgouYDiCROKojhGO4JhII5yIJemN1n8";//"UUeshZc0CRsr0jDD1OSFqDJ2piVaiREaiQkueDJR7lIvQ";
        
        /*ADD SEARCH TOPICS FOR TWITTER HERE*/
        String topics="ecstasy halloween";
        
        int sleepTimeTweet=20000;
        int sleepTimeRequest=300000;
        final SmartDownloader smartDownloader = new SmartDownloader(outputFileName, apiKey, apiSecret, tokenValue, tokenSecret, filePath, topics);
        smartDownloader.start();
    }
}
